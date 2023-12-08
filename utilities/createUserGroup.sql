USE SQLFSDev;

-- Group

INSERT INTO `UserGroup` (id, name) VALUES (UUID(), 'Group A');
INSERT INTO `UserGroup` (id, name) VALUES (UUID(), 'Group B');
INSERT INTO `UserGroup` (id, name) VALUES (UUID(), 'Group C');

-- User

INSERT INTO `User` (id, name, email, password, groupId) 
VALUES (UUID(), 'John Doe', 'john@example.com', 'password123', (SELECT id FROM `UserGroup` where name='Group A' LIMIT 1));
INSERT INTO `User` (id, name, email, password, groupId)
VALUES (UUID(), 'Jane Smith', 'jane@example.com', 'securepass', (SELECT id FROM `UserGroup` where name='Group A' LIMIT 1));
INSERT INTO `User` (id, name, email, password, groupId)
VALUES (UUID(), 'Bob Johnson', 'bob@example.com', 'bobpass', (SELECT id FROM `UserGroup` where name='Group B' LIMIT 1));
INSERT INTO `User` (id, name, email, password, groupId)
VALUES (UUID(), 'Alice Williams', 'alice@example.com', 'alicerocks', (SELECT id FROM `UserGroup` where name='Group C' LIMIT 1));