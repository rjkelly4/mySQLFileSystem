package com.mysqlfsbackend.service;

import com.mysqlfsbackend.repository.DirectoryDao;
import com.mysqlfsbackend.repository.FileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class DeleteService {
    private final DirectoryDao directoryDao;
    private final FileDao fileDao;

    @Autowired
    public DeleteService(DirectoryDao directoryDao, FileDao fileDao) {
        this.directoryDao = directoryDao;
        this.fileDao = fileDao;
    }

    public void deleteDirectory(String id) {
        directoryDao.delete(id);
    }

    public void deleteFile(String id) {
        fileDao.delete(id);
    }
}