package com.website.hoho.dao;

import com.github.pagehelper.Page;
import com.website.hoho.pojo.Archives;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
@Mapper
public interface ArchivesDao{
    /**
     *查询所有的文章
     * @return
     */
    List<Archives> getArchives();

    /**
     *查询前n条的文章
     * @return
     */
    List<Archives> getArchivesByLimit();

    /**
     *通过文章id查询文章
     * @return
     */
    Archives getArchivesById(@Param("id") int id);

    /**
     *通过文章标题,分类查询文章
     * @return
     */
    List<Archives> getArchivesByName(@Param("searchName") String searchName,@Param("searchType") String searchType);
    /**
     * 增加文章
     * @param archives
     * @return
     */
    int addArchives(Archives archives);

    /**
     * 客户端关键字搜索文章
     * @param searchContent
     * @return
     */
    List<Archives> getArchivesByContent(@Param("searchContent") String searchContent);



    /**
     * 修改文章
     * @param archives
     * @return
     */
    int updateArchives(Archives archives);


    /**
     * 删除文章
     * @param id
     * @return
     */
    int deleteArchives(@RequestParam("id") int id);


}
