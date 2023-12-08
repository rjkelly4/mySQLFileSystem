USE SQLFSDev;

-- using one group and one user
SET @userId = (
    SELECT id FROM `User` LIMIT 1
);
SET @groupId = (
    SELECT id FROM `UserGroup` LIMIT 1
);

-- Directory

INSERT INTO `Directory` (id, name, parentDirId, permission, ownerUserId, ownerGroupId, size)
VALUES 
	(0, "root", null, 777, @userId, @groupId, 0),
	(1, "Documents", 0, 660, @userId, @groupId, 0),
	(2, "Personal", 1, 660, @userId, @groupId, 0),
	(3, "tutorials", 0, 660, @userId, @groupId, 0);

-- File

describe File;

INSERT INTO `File` (id, name, parentDirId, permission, ownerUserId, ownerGroupId, size, fileType, content)
VALUES 
	(1, 'favorite_recipes.txt', 2, 644, @userId, @groupId, 0, "TXT", "a"),
	(2, 'letter.txt', 2, 644, @userId, @groupId, 0, "TXT", "a"),
	(3, 'Paper 2 Draft.txt', 1, 644, @userId, @groupId, 0, "TXT", "a"),
	(4, 'Paper 2 Final.txt', 1, 644, @userId, @groupId, 0, "TXT", "a"),
	(5, 'pacman_tutorial.txt', 3, 644, @userId, @groupId, 0, "TXT", "a"),
	(6, 'minecraft_tutorial.txt', 3, 644, @userId, @groupId, 0, "TXT", "a");