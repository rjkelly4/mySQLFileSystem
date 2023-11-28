package com.mysqlfsbackend.model.dto.filesystem;

import com.mysqlfsbackend.model.filesystem.DirectoryEntity;
import com.mysqlfsbackend.model.filesystem.FileEntity;
import com.mysqlfsbackend.model.filesystem.FileSystemObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FileSystemObjectDtoFactory {
    public static DirectoryDto generateDirectoryDto(FileSystemObject workingDirectory,
                                                                  List<List<FileSystemObject>> browseContent) {
        DirectoryDto result =  new DirectoryDto(
                workingDirectory.getId(), // TODO: mask real id
                workingDirectory.getName(),
                workingDirectory.getSize(),
                workingDirectory.getPermission().toString(), // TODO: change to String
                workingDirectory.getOwnerUserId(), // TODO: get user name
                workingDirectory.getOwnerGroupId(), // TODO: get group name,
                new ArrayList<>()
        );
        List<DirectoryDto> parentDirs = new ArrayList<>(Collections.singleton(result));
        List<List<FileSystemObjectDto>> subContent;

        for (List<FileSystemObject> layer : browseContent) {
            // Divide layer into content groups
            subContent = groupContentByParent(layer);
            int groupIndex = 0;

            // Filter out empty parent directories and sort by id
            List<String> parentDirIds = layer.stream().map(FileSystemObject::getParentDirId).distinct().toList();
            parentDirs = parentDirs.stream()
                    .filter(directoryDto -> binSearch(parentDirIds, directoryDto.getId()))
                    .sorted(Comparator.comparing(FileSystemObjectDto::getId))
                    .toList();

            // Match parent directories (sorted by id) with content groups (sorted by parentId)
            for (DirectoryDto parentDir : parentDirs) {
                parentDir.setContent(subContent.get(groupIndex++));
            }

            // Extract directories from current layer
            parentDirs = subContent.stream()
                    .flatMap(List::stream)
                    .filter(FileSystemObjectDto::isDirectory)
                    .map(directory -> (DirectoryDto) directory)
                    .toList();
        }

        return result;
    }

    public static FileSystemObjectDto convertToDto(FileSystemObject object) {
        if (object instanceof FileEntity file) {
            return new FileDto(file.getId(), file.getName(), file.getSize(), file.getPermission().toString(),
                    file.getOwnerUserId(), file.getOwnerGroupId(), file.getFileType().toString(), file.getContent());
        }
        if (object instanceof DirectoryEntity directory) {
            return new DirectoryDto(directory.getId(), directory.getName(), directory.getSize(),
                    directory.getPermission().toString(), directory.getOwnerUserId(),
                    directory.getOwnerGroupId(), new ArrayList<>());
        }
        return null;
    }

    private static List<List<FileSystemObjectDto>> groupContentByParent(List<FileSystemObject> layer) {
        List<List<FileSystemObjectDto>> contentGroups = new ArrayList<>();

        for (int i = 0; i <= layer.size() - 1; ++i) {
            FileSystemObjectDto fileSystemObjectDto= convertToDto(layer.get(i));
            if (i > 0 && layer.get(i).getParentDirId().equals(layer.get(i-1).getParentDirId())) {
                contentGroups.get(contentGroups.size() - 1).add(fileSystemObjectDto);
            } else {
                contentGroups.add(new ArrayList<>(Collections.singleton(fileSystemObjectDto)));
            }
        }

        return contentGroups;
    }

    private static boolean binSearch(List<String> list, String target) {
        int l = 0;
        int r = list.size() - 1;

        while (l <= r) {
            int mid = (l + r) / 2;
            int compare = list.get(mid).compareTo(target);

            if (compare == 0) {
                return true;
            }
            if (compare < 0) {
                l = mid + 1;
            }
            if (compare > 0) {
                r = mid - 1;
            }
        }

        return false;
    }
}
