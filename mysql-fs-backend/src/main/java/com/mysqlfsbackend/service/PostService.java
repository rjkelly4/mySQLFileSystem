package com.mysqlfsbackend.service;

import com.mysqlfsbackend.repository.DirectoryDao;
import com.mysqlfsbackend.repository.FileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final DirectoryDao directoryDao;
    private final FileDao fileDao;

    @Autowired
    public PostService(DirectoryDao directoryDao, FileDao fileDao) {
        this.directoryDao = directoryDao;
        this.fileDao = fileDao;
    }

    public void postNewDirectory(String name, String parentDirId,
                                        int permission, String ownerUserId,
                                        String ownerGroupId, int size) {

        directoryDao.customInsert(name, parentDirId, permission, ownerUserId,
                ownerGroupId, size);
    }
}