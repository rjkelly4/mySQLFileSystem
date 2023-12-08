USE SQLFSDev;

-- using one group and one user

SET @userId = (
    SELECT id FROM `User` LIMIT 1
);
SET @groupId = (
    SELECT id FROM `UserGroup` LIMIT 1
);

-- Directory

INSERT INTO `Directory` (id, name, parentDirID, permission, ownerUserId, ownerGroupId, size)
VALUES 
	(0, "root", NULL, 777, @userId, @groupId, 0),
    (1, "Documents", 0, 660, @userId, @groupId, 0),
    (2, "Personal", 1, 660, @userId, @groupId, 0),
    (3, "journal", 2, 660, @userId, @groupId, 0),
    (4, "Vacation", 1, 660, @userId, @groupId, 0),
    (5, "Projects", 0, 660, @userId, @groupId, 0),
    (6, "scripts", 0, 660, @userId, @groupId, 0),
    (7, "READMEs", 5, 660, @userId, @groupId, 0);
	
-- `File`s
INSERT INTO `File` (id, name, parentDirId, permission, ownerUserId, ownerGroupId, size, fileType, content)
VALUES 
    (1, 'essay.txt', 2, 644, @userId, @groupId, 0, "TXT", "a"),
    (2, 'grocery.txt', 2, 755, @userId, @groupId, 0, "TXT", "a"),
    (3, 'letter.txt', 2, 644, @userId, @groupId, 0, "TXT", "a"),
    (4, 'todo.txt', 2, 660, @userId, @groupId, 0, "TXT", "a"),
    (5, 'Oct28.txt', 3, 600, @userId, @groupId, 0, "TXT", "a"),
    (6, 'Nov10.txt', 3, 600, @userId, @groupId, 0, "TXT", "a"),
    (7, 'Nov11.txt', 3, 600, @userId, @groupId, 0, "TXT", "a"),
    (8, 'Nov12.txt', 3, 600, @userId, @groupId, 0, "TXT", "a"),
    (9, 'Nov13.txt', 3, 600, @userId, @groupId, 0, "TXT", "a"),
    (10, 'Nov14.txt', 3, 660, @userId, @groupId, 0, "TXT", "a"),
    (11, 'deploy.sh', 6, 666, @userId, @groupId, 0, "TXT", "a"),
    (12, 'setup.sh', 6, 666, @userId, @groupId, 0, "TXT", "a"),
    (13, 'install_program.sh', 6, 666, @userId, @groupId, 0, "TXT", "a"),
    (14, 'read.c', 5, 660, @userId, @groupId, 0, "TXT", "a"),
    (15, 'java`File`.java', 5, 660, @userId, @groupId, 0, "TXT", "a"),
    (16, 'factorial.py', 5, 666, @userId, @groupId, 0, "TXT", "a"),
    (17, 'README1.md', 7, 664, @userId, @groupId, 0, "TXT", "a"),
    (18, 'README2.md', 7, 664, @userId, @groupId, 0, "TXT", "a"),
    (19, 'README3.md', 7, 664, @userId, @groupId, 0, "TXT", "a"),
    (20, 'Italy Plans.txt', 4, 660, @userId, @groupId, 0, "TXT", "a"),
    (21, 'Japan Plans.txt', 4, 660, @userId, @groupId, 0, "TXT", "a"),
    (22, 'Mexico Plans.txt', 4, 660, @userId, @groupId, 0, "TXT", "a"),
    (23, 'notes.txt', 3, 660, @userId, @groupId, 0, "TXT", "a");