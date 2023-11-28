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
    ownerGroupId INT NOT NULL,
    size BIGINT CHECK (size >= 0) NOT NULL,
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
    ownerGroupId INT NOT NULL ,
    size BIGINT CHECK (size >= 0) NOT NULL,
    fileType ENUM('NONE', 'TXT') NOT NULL,
    content VARCHAR(255),
    FOREIGN KEY (parentDirId) REFERENCES Directory(id)
);