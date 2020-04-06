package com.website.hoho;

import com.github.pagehelper.Page;
import com.website.hoho.dao.ArchivesDao;
import com.website.hoho.dao.UserDao;
import com.website.hoho.pojo.Archives;
import com.website.hoho.pojo.Type;
import com.website.hoho.pojo.User;
import com.website.hoho.pojo.dto.UserDto;
import com.website.hoho.service.ArchivesServiceImpl;
import com.website.hoho.service.TypeServiceImpl;
import com.website.hoho.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class KongblogApplicationTests {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    ArchivesServiceImpl archivesService;
    @Test
    void contextLoads() {
        List<Archives> archives = archivesService.getArchivesByContent("架构");
        for (Archives archive : archives) {
            System.out.println(archive);
        }

    }


}
