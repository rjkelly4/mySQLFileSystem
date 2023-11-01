CREATE TABLE Directory
(
    id BINARY(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parentDirId BINARY(36),
    permission INT CHECK (
        permission >= 0 AND permission <= 777 AND
        CAST(permission AS CHAR(3)) REGEXP '^[0-7]{1,3}$'
    ),
    ownerUserId INT NOT NULL,
    ownerGroupId INT,
    size BIGINT CHECK (size >= 0),
    FOREIGN KEY (parentDirId) REFERENCES Directory(id)
);

CREATE TABLE File
(
    id BINARY(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parentDirId BINARY(36),
    permission INT CHECK (
        permission >= 0 AND permission <= 777 AND
        CAST(permission AS CHAR(3)) REGEXP '^[0-7]{1,3}$'
    ),
    ownerUserId INT NOT NULL,
    ownerGroupId INT,
    size BIGINT CHECK (size >= 0),
    fileType ENUM('NONE', 'TXT'),
    content VARCHAR(255),
    FOREIGN KEY (parentDirId) REFERENCES Directory(id)
);
