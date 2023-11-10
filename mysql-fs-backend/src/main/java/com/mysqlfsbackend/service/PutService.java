package com.mysqlfsbackend.service;

import com.mysqlfsbackend.model.filesystem.DirectoryEntity;
import com.mysqlfsbackend.model.filesystem.FileEntity;
import com.mysqlfsbackend.model.filesystem.FileSystemObject;
import com.mysqlfsbackend.repository.DirectoryDao;
import com.mysqlfsbackend.repository.FileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PutService {
    private final DirectoryDao directoryDao;
    private final FileDao fileDao;

    @Autowired
    public PutService(DirectoryDao directoryDao, FileDao fileDao) {
        this.directoryDao = directoryDao;
        this.fileDao = fileDao;
    }

    public void putNewDirectory(String name, String parentDirId,
                                        int permission, String ownerUserId,
                                        String ownerGroupId, int size) {
        directoryDao.customInsert(name, parentDirId, permission, ownerUserId,
                ownerGroupId, size);
    }
}