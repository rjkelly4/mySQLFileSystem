USE SQLFSDev;

SET @userId = (
    SELECT id FROM `User` LIMIT 1
);
SET @groupId = (
    SELECT id FROM `UserGroup` LIMIT 1
);

-- Directory

INSERT INTO `Directory` (id, name, parentDirId, permission, ownerUserId, ownerGroupId, size)
VALUES 
	(0, "root", NULL, 666, @userId, @groupId, 0),
	(1, "Folder1", 0, 666, @userId, @groupId, 0),
    (2, "Folder2", 1, 666, @userId, @groupId, 0),
    (3, "Folder3", 2, 666, @userId, @groupId, 0),
    (4, "Folder4", 3, 666, @userId, @groupId, 0),
    (5, "Folder5a", 4, 666, @userId, @groupId, 0),
    (6, "Folder5b", 4, 666, @userId, @groupId, 0),
    (7, "Folder6", 5, 666, @userId, @groupId, 0),
    (8, "Folder7", 7, 666, @userId, @groupId, 0),
    (9, "Folder8", 8, 666, @userId, @groupId, 0);

-- File

INSERT INTO `File` (id, name, parentDirId, permission, ownerUserId, ownerGroupId, size, fileType, content)
VALUES (1, 'deep_file.txt', 9, 644, @userId, @groupId, 0, "TXT", "a");
