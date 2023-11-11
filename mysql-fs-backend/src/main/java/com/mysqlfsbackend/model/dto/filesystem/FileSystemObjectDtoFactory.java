package com.mysqlfsbackend.model.dto.filesystem;

import com.mysqlfsbackend.model.filesystem.DirectoryEntity;
import com.mysqlfsbackend.model.filesystem.FileEntity;
import com.mysqlfsbackend.model.filesystem.FileSystemObject;
import com.mysqlfsbackend.util.Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This is a factory class for FileSystemObject DTOs. It contains methods for converting/parsing entities
 * to their respective DTOs - Directory or File. For directories, it also enables the generation of
 * nested FileSystemObject contents.
 */
public class FileSystemObjectDtoFactory {
    /**
     * Generate a Directory DTO with nested contents from given directory and entity content layers.
     *
     * @param workingDirectory The directory entity that will be parsed.
     * @param browseContent The content layers of given directory.
     * @return A corresponding directory DTO with nested content.
     */
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
                    .filter(directoryDto -> Algorithm.binSearch(parentDirIds, directoryDto.getId()))
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

    /**
     * Convert a FileSystemObject entity to its respective DTO. A file DTO will also contain the original
     * content, while a directory DTO will be initialized as empty.
     *
     * @param object The FileSystemObject that will be converted/
     * @return The corresponding FileSystemObject DTO.
     */
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

    /**
     * Convert and organize a content layer (sorted by their parent directories' ids) into
     * their respective groups of DTOs based on their parent directories.
     *
     * @param layer A list of FileSystemObjects in the same layer (sorted by their parent directories' ids).
     * @return A list of lists of corresponding DTOs.
     */
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
}
