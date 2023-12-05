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

    public void putDirectory(String id, String name, String parentDirId,
                              String permission, String ownerUserId,
                              String ownerGroupId, String size) {

        directoryDao.put(id, name, parentDirId, permission, ownerUserId, ownerGroupId, size);
    }

    /**
     * Modifies all the file's content if an id match is found. Should be idempotent, ie multiple requests will only
     * create a single object.
     */
    public void putFile(String id, String name, String parentDirId,
                         String permission, String ownerUserId,
                         String ownerGroupId, String size, String fileType, String content) {

        fileDao.put(id, name, parentDirId, permission, ownerUserId, ownerGroupId, size, fileType, content);
    }


}