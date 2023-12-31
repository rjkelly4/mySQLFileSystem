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

    public void postDirectory(String id, String name, String parentDirId,
                                        String permission, String ownerUserId,
                                        String ownerGroupId, String size) {

        directoryDao.customInsert(id, name, parentDirId, permission, ownerUserId, ownerGroupId, size);
    }

    public void postFile(String id, String name, String parentDirId,
                              String permission, String ownerUserId,
                              String ownerGroupId, String size, String fileType, String content) {

        fileDao.customInsert(id, name, parentDirId, permission, ownerUserId, ownerGroupId, size, fileType, content);
    }
}