package com.website.hoho.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.website.hoho.pojo.Archives;
import com.website.hoho.pojo.dto.UserDto;
import com.website.hoho.service.ArchivesService;
import com.website.hoho.service.ArchivesServiceImpl;
import com.website.hoho.service.TypeServiceImpl;
import com.website.hoho.service.UserServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.omg.CORBA.UnknownUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ArchivesController {
    @Autowired
    ArchivesServiceImpl archivesService;

    @Autowired
    TypeServiceImpl typeService;

    @Autowired
    UserServiceImpl userService;

    //跳转到后台管理登录页
    @RequestMapping({"","/index"})
    public String admin(Model model){
        return "redirect:/admin/login";
    }
    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }


    //搜索分页一体化操作
    @RequestMapping("/main")
    public String aechivesList(Model model,
                               @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                               @RequestParam(defaultValue="4",value="pageSize")Integer pageSize,
                               @RequestParam(defaultValue="",value="searchName") String searchName,
                               @RequestParam(defaultValue="请选择",value="searchType") String searchType){
        //为了程序的严谨性，判断非空：
        //设置默认当前页
        if(pageNum==null || pageNum<=0){
            pageNum = 1;
        }
        //设置默认每页显示的数据数
        if(pageSize == null){
            pageSize = 1;
        }
        System.out.println("当前页是："+pageNum+"显示条数是："+pageSize);
        System.out.println("选择的分类："+searchType);

        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum,pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Archives> archives = archivesService.getArchivesByName(searchName,searchType);
            System.out.println("分页数据："+archives);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Archives> pageInfo = new PageInfo<Archives>(archives,pageSize);
            //4.使用model传参数回前端
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("archive",archives);
            model.addAttribute("searchType",searchType);
            model.addAttribute("searchName",searchName);
            model.addAttribute("types",typeService.getTypes());
        }finally {
            //清理 ThreadLocal 存储的分页参数,保证线程安全
            PageHelper.clearPage();
        }
        return "admin/admin";
    }



    /**
     * 登录验证操作
     * @return
     */
    @PostMapping("/login_submit")
    public String login(String username,String password,Integer rememberMe,Model model){

        //获取当前用户的对象
        Subject subject = SecurityUtils.getSubject();
        //封装用户的数据,然后在UserRealm中验证用户
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //记住我功能
        if (rememberMe!=null && rememberMe==1){
            token.setRememberMe(true);
        }
        try {
            subject.login(token);
            model.addAttribute("archive",archivesService.getArchives());
            return "redirect:/admin/main";
        }catch (UnknownAccountException e){
            model.addAttribute("errMsg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("errMsg","密码错误");
            return "login";
        }

    }

    @RequestMapping("/logout")
    public String logout(){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //注销实现：
        subject.logout();
        return "redirect:/admin/index";
    }




    /**
     *跳转到增加页面
     * @return
     */
    @GetMapping("/toAdd")
    public String toAddPage(Model model){
        model.addAttribute("types",typeService.getTypes());
        model.addAttribute("currentTime",new Date());
        return "admin/addArchives";
    }

    /**
     * 增加文章
     * @param archives
     * @return
     */
    @PostMapping("/add")
    public String addArchives(Archives archives, Model model){
        System.out.println("archives:"+archives.toString());
        model.addAttribute("archive",archivesService.getArchives());
        archivesService.addArchives(archives);
        return "redirect:/admin/main";
    }

    /**
     *跳转到修改页面
     * @return
     */
    @GetMapping("/toUpdate/{id}")
    public String toUpdatePage(Model model,@PathVariable("id") int id){
        Archives archive = archivesService.getArchivesById(id);
        model.addAttribute("types",typeService.getTypes());
        model.addAttribute("archive",archive);
        return "admin/updateArchives";
    }

    /**
     *修改页面
     * @return
     */
    @PostMapping("/update")
    public String updateArchives(Model model,Archives archives){
        model.addAttribute("archive",archivesService.getArchives());
        System.out.println("archives::"+archives.toString());
        archivesService.updateArchives(archives);
        return "redirect:/admin/main";
    }

    /**
     *删除文章
     * @return
     */
    @GetMapping("/delete/{id}")
    public String deleteArchives(@PathVariable("id") int id){
        archivesService.deleteArchives(id);
        return "redirect:/admin/main";
    }







}
