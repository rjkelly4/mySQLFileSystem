package com.mysqlfsbackend.service;

import com.mysqlfsbackend.repository.DirectoryDao;
import com.mysqlfsbackend.repository.FileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatchService {
    private final DirectoryDao directoryDao;
    private final FileDao fileDao;

    @Autowired
    public PatchService(DirectoryDao directoryDao, FileDao fileDao) {
        this.directoryDao = directoryDao;
        this.fileDao = fileDao;
    }

    public void patchDirParent(String newParentDirId, String id){
        directoryDao.patchParentDir(newParentDirId, id);
    }

    public void patchFileParent(String newParentDirId, String id){
        fileDao.patchParentDir(newParentDirId, id);
    }

    public void patchDirName(String name, String id) {
        directoryDao.patchName(name, id);
    }

    public void patchFileName(String name, String id) {
        fileDao.patchName(name, id);
    }
}
