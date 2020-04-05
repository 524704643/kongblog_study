package com.website.hoho.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.website.hoho.pojo.Archives;
import com.website.hoho.service.ArchivesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    ArchivesServiceImpl archivesService;


    //进入客户端主页
    @RequestMapping({"/","/index.html"})
    public String index(Model model){
        model.addAttribute("archives",archivesService.getArchivesByLimit());
        return "client/index";
    }

//    //进入客户端博客页
//    @RequestMapping("/blog")
//    public String blog(Model model){
//        model.addAttribute("archives",archivesService.getArchives());
//        return "client/blog";
//    }

    //进入文章详情
    @RequestMapping("/archives/{id}")
    public String index(Model model,@PathVariable("id") int id){
        model.addAttribute("archives",archivesService.getArchivesById(id));
        return "client/content";
    }

    //客户端搜索
    @GetMapping("/blog/search")
    public String blog(Model model,
                       @RequestParam(defaultValue="",value="searchContent") @PathVariable("searchContent") String searchContent){
        System.out.println("searchContent"+searchContent);
        List<Archives> content = archivesService.getArchivesByContent(searchContent);
        model.addAttribute("archives",content);
        model.addAttribute("searchContent",searchContent);
        if (searchContent.equals("")||content.equals("")){
            return "redirect:/blog/pages/1";
        }else {
            return "client/blog";
        }

    }


    @RequestMapping("/blog/pages/{pages}")
    public String aechivesList(Model model,
                               @PathVariable("pages") Integer pages,
                               @RequestParam(defaultValue="4",value="pageSize")Integer pageSize){
        //为了程序的严谨性，判断非空：
        //设置默认当前页
        if(pages==null || pages<=0){
            pages = 1;
        }
        //设置默认每页显示的数据数
        if(pageSize == null){
            pageSize = 1;
        }
        System.out.println("当前页是："+pages+"显示条数是："+pageSize);

        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pages,pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Archives> archives = archivesService.getArchives();
//            System.out.println("分页数据："+archives);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Archives> pageInfo = new PageInfo<Archives>(archives,pageSize);
            //4.使用model传参数回前端
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("archives",archives);

        }finally {
            //清理 ThreadLocal 存储的分页参数,保证线程安全
            PageHelper.clearPage();
        }
        return "client/blog";
    }



}
