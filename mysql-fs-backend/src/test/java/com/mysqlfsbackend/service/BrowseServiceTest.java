package com.mysqlfsbackend.service;


import com.mysqlfsbackend.model.filesystem.DirectoryEntity;
import com.mysqlfsbackend.model.filesystem.FileEntity;
import com.mysqlfsbackend.model.filesystem.FileSystemObject;
import com.mysqlfsbackend.model.filesystem.FileTypes;
import com.mysqlfsbackend.repository.DirectoryDao;
import com.mysqlfsbackend.repository.FileDao;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = BrowseServiceTest.class)
@ActiveProfiles("test")
public class BrowseServiceTest {
    private final String testOUId = "eef533d0-95db-11ee-a9d1-0242ac120003";
    private final String testOGId = "eeefc2a6-95db-11ee-a9d1-0242ac120003";
    @Mock
    private DirectoryDao directoryDao;

    @Mock
    private FileDao fileDao;

    @InjectMocks
    private BrowseService browseService;

    @Test
    void testGetDirectoryFromPath() {
        DirectoryEntity root = new DirectoryEntity("0", "root", null, "000", testOUId, testOGId, 0);
        DirectoryEntity dirA = new DirectoryEntity("1", "A", "0", "700", testOUId, testOGId, 0);
        DirectoryEntity dirB = new DirectoryEntity("2", "B", "1", "700", testOUId, testOGId, 0);

        when(directoryDao.getChildByName(anyString(), anyString())).thenReturn(dirA, dirB);

        List<String> path = new ArrayList<>();
        path.add("dirA");
        path.add("dirB");
        Optional<DirectoryEntity> actual = browseService.getDirectoryFromPath(root, path);

        assertTrue(actual.isPresent());
        assertEquals(dirB, actual.get());
        verify(directoryDao, times(2)).getChildByName(anyString(), anyString());
    }

    @Test
    void testGetFileFromPath() {
        DirectoryEntity root = new DirectoryEntity("0", "root", null, "000", testOUId, testOGId, 0);
        DirectoryEntity dirA = new DirectoryEntity("1", "A", "0", "700", testOUId, testOGId, 0);
        DirectoryEntity dirB = new DirectoryEntity("2", "B", "1", "700", testOUId, testOGId, 0);
        FileEntity fileA = new FileEntity("0", "A", "2", "700", testOUId, testOGId, 3, FileTypes.NONE,"lol");

        when(directoryDao.getChildByName(anyString(), anyString())).thenReturn(dirA, dirB);
        when(fileDao.getChildByName(anyString(), anyString())).thenReturn(fileA);

        List<String> path = new ArrayList<>();
        path.add("dirA");
        path.add("dirB");
        path.add("fileA");
        Optional<FileEntity> actual = browseService.getFileFromPath(root, path);

        assertTrue(actual.isPresent());
        assertEquals(fileA, actual.get());
        verify(directoryDao, times(2)).getChildByName(anyString(), anyString());
        verify(fileDao, times(1)).getChildByName(anyString(), anyString());
    }

    @Test
    void testBrowseDirectory() {
        DirectoryEntity root = new DirectoryEntity("0", "root", null, "000", testOUId, testOGId, 12);
        DirectoryEntity dirA = new DirectoryEntity("1", "A", "0", "700", testOUId, testOGId, 6);
        DirectoryEntity dirB = new DirectoryEntity("2", "B", "1", "700", testOUId, testOGId, 3);
        FileEntity fileA = new FileEntity("3", "A", "1", "700", testOUId, testOGId, 3, FileTypes.NONE,"lol");
        FileEntity fileB = new FileEntity("4", "B", "1", "700", testOUId, testOGId, 3, FileTypes.NONE,"lol");
        FileEntity fileC = new FileEntity("5", "C", "2", "700", testOUId, testOGId, 3, FileTypes.NONE,"lol");
        FileEntity fileD = new FileEntity("6", "D", "0", "700", testOUId, testOGId, 3, FileTypes.NONE,"lol");

        List<DirectoryEntity> layer1Dirs = new ArrayList<>();
        List<FileEntity> layer1Files = new ArrayList<>();
        List<FileEntity> layer2Files = new ArrayList<>();

        layer1Files.add(fileD);

        layer1Dirs.add(dirA);
        layer1Dirs.add(dirB);

        layer2Files.add(fileA);
        layer2Files.add(fileB);
        layer2Files.add(fileC);

        when(directoryDao.getOrderedChildLayer(anyList())).thenReturn(layer1Dirs, new ArrayList<>());
        when(fileDao.getOrderedChildLayer(anyList())).thenReturn(layer1Files, layer2Files, new ArrayList<>());

        List<List<FileSystemObject>> expected = new ArrayList<>();
        List<FileSystemObject> temp_expected = new ArrayList<>();
        temp_expected.add(dirA);
        temp_expected.add(fileD);
        temp_expected.add(dirB);
        expected.add(temp_expected);

        temp_expected = new ArrayList<>();
        temp_expected.add(fileA);
        temp_expected.add(fileB);
        temp_expected.add(fileC);
        expected.add(temp_expected);

        List<List<FileSystemObject>> actual = browseService.browseDirectory(root, 4);
        assertEquals(expected, actual);
        verify(directoryDao, times(2)).getOrderedChildLayer(anyList());
        verify(fileDao, times(2)).getOrderedChildLayer(anyList());
    }
}
