package com.mysqlfsbackend.service;

import com.mysqlfsbackend.repository.DirectoryDao;
import com.mysqlfsbackend.repository.FileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PutService {
    private final DirectoryDao directoryDao;
    private final FileDao fileDao;

    @Autowired
    public PutService(DirectoryDao directoryDao, FileDao fileDao) {
        this.directoryDao = directoryDao;
        this.fileDao = fileDao;
    }

    public void putDirectory(String name, String parentDirId,
                              int permission, String ownerUserId,
                              String ownerGroupId, int size) {

        directoryDao.customInsert(name, parentDirId, permission, ownerUserId, ownerGroupId, size);
    }

    public void putFile(String name, String parentDirId,
                         int permission, String ownerUserId,
                         String ownerGroupId, int size, String fileType, String content) {

        fileDao.customInsert(name, parentDirId, permission, ownerUserId, ownerGroupId, size, fileType, content);
    }


}