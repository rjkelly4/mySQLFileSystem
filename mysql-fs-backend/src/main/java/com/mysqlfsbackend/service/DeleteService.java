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
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DeleteService(DirectoryDao directoryDao, FileDao fileDao, JdbcTemplate jdbcTemplate) {
        this.directoryDao = directoryDao;
        this.fileDao = fileDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void deleteDirectory(String id, String name, String parentDirId) {
        jdbcTemplate.update("DELETE FROM Directory WHERE id = ? AND name = ? AND parentDirId = ?;",
                id, name, parentDirId);
    }

    public void deleteFile(String id, String name, String parentDirId) {
        jdbcTemplate.update("DELETE FROM File WHERE parentDirId = ? AND name = ? AND parentDirId = ?;",
                id, name, parentDirId);
    }
}