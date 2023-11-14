package com.mysqlfsbackend.service;

import com.mysqlfsbackend.repository.DirectoryDao;
import com.mysqlfsbackend.repository.FileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class PostService {
    private final DirectoryDao directoryDao;
    private final FileDao fileDao;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostService(DirectoryDao directoryDao, FileDao fileDao, JdbcTemplate jdbcTemplate) {
        this.directoryDao = directoryDao;
        this.fileDao = fileDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void postDirectory(String name, String parentDirId,
                                        int permission, String ownerUserId,
                                        String ownerGroupId, int size) {

        jdbcTemplate.update("INSERT INTO Directory (name, parentDirId, permission, ownerUserId, ownerGroupId, size) "
                        + "VALUES (?, ?, ?, ?, ?, ?);", name, parentDirId, permission, ownerUserId, ownerGroupId, size);
    }

    public void postFile(String name, String parentDirId,
                              int permission, String ownerUserId,
                              String ownerGroupId, int size, String fileType, String content) {

        jdbcTemplate.update("INSERT INTO File (name, parentDirId, permission, ownerUserId, ownerGroupId, size, fileType, content) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);",
                name, parentDirId, permission, ownerUserId, ownerGroupId, size, fileType, content);
    }
}