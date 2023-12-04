CREATE TABLE Directory
(
    id BINARY(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parentDirId BINARY(36),
    permission VARCHAR(3) NOT NULL CHECK (permission REGEXP '^[0-7][0-7][0-7]$'),
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
    permission VARCHAR(3) NOT NULL CHECK (permission REGEXP '^[0-7][0-7][0-7]$'),
    ownerUserId INT NOT NULL,
    ownerGroupId INT NOT NULL ,
    size BIGINT CHECK (size >= 0) NOT NULL,
    fileType ENUM('NONE', 'TXT') NOT NULL,
    content VARCHAR(255),
    FOREIGN KEY (parentDirId) REFERENCES Directory(id)
);