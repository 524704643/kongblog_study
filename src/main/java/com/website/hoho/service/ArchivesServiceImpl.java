package com.website.hoho.service;

import com.github.pagehelper.Page;
import com.website.hoho.dao.ArchivesDao;
import com.website.hoho.pojo.Archives;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class ArchivesServiceImpl implements ArchivesService {

    @Autowired
    ArchivesDao archivesDao;
    @Override
    public List<Archives> getArchives() {
        return archivesDao.getArchives();
    }

    @Override
    public List<Archives> getArchivesByLimit() {
        return archivesDao.getArchivesByLimit();
    }

    @Override
    public Archives getArchivesById(int id) {
        return archivesDao.getArchivesById(id);
    }

    @Override
    public List<Archives> getArchivesByName(String searchName, String searchType) {
        return archivesDao.getArchivesByName(searchName,searchType);
    }

    @Override
    public List<Archives> getArchivesByContent(String searchContent) {
        return archivesDao.getArchivesByContent(searchContent);
    }


    @Override
    public int addArchives(Archives archives) {
        return archivesDao.addArchives(archives);
    }


    @Override
    public int updateArchives(Archives archives) {
        return archivesDao.updateArchives(archives);
    }

    @Override
    public int deleteArchives(int id) {
        return archivesDao.deleteArchives(id);
    }

}
