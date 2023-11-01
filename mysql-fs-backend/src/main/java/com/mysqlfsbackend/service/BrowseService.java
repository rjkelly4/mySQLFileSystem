package com.mysqlfsbackend.service;

import com.mysqlfsbackend.model.DirectoryEntity;
import com.mysqlfsbackend.model.FileEntity;
import com.mysqlfsbackend.repository.DirectoryDao;
import com.mysqlfsbackend.repository.FileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrowseService {
    private final DirectoryDao directoryDao;
    private final FileDao fileDao;

    @Autowired
    public BrowseService(DirectoryDao directoryDao, FileDao fileDao) {
        this.directoryDao = directoryDao;
        this.fileDao = fileDao;
    }

    /**
     * Fetch the root directory in MySQLFSBackend.
     *
     * @return The root directory.
     */
    public DirectoryEntity getRoot() { return directoryDao.getRoot(); }

    /**
     * Fetch all directories in MySQLFSBackend.
     *
     * @return A list of found directory objects
     */
    public List<DirectoryEntity> getAllDirectories() {
        return directoryDao.findAll();
    }

    /**
     * Fetch all files in MySQLFSBackend.
     *
     * @return A list of found file objects
     */
    public List<FileEntity> getAllFiles() {
        return fileDao.findAll();
    }

    /**
     * Fetch a directory from a given relative path and working directory. For absolute
     * path, set working directory to the root.
     *
     * @param workingDirectory The current working directory for browsing.
     * @param path The path for fetching the target directory.
     * @return The target directory object if it exists, otherwise null.
     */
    public Optional<DirectoryEntity> getDirectoryFromPath(DirectoryEntity workingDirectory, List<String> path) {
        DirectoryEntity currDirectory = workingDirectory;

        for (String directory : path) {
            currDirectory = directoryDao.getChildByName(currDirectory.getId(), directory);
        }

        return Optional.ofNullable(currDirectory);
    }

    /**
     * Fetch a file from a given relative path and working directory. For absolute
     * path, set working directory to the root.
     *
     * @param workingDirectory The current working directory for browsing.
     * @param path The path for fetching the target file.
     * @return The target file object if it exists, otherwise null.
     */
    public Optional<FileEntity> getFileFromPath(DirectoryEntity workingDirectory, List<String> path) {
        if (path.isEmpty()) {
            return Optional.empty();
        }

        String fileName = path.get(path.size() - 1);
        List<String> directoryPath = path.subList(0, path.size() - 1);

        Optional<DirectoryEntity> currDirectory = getDirectoryFromPath(workingDirectory, directoryPath);

        if (currDirectory.isEmpty()) {
            return Optional.empty();
        }

        FileEntity targetFile = fileDao.getChildByName(currDirectory.get().getId(), fileName);

        return Optional.ofNullable(targetFile);
    }
}
