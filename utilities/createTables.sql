USE SQLFSDev;

CREATE TABLE `UserGroup`
(
    id BINARY(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE `User`
(
    id BINARY(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
        CHECK (email REGEXP '^[a-z0-9!#$%&''*+/=?^_`{|}~-]+(\.[a-z0-9!#$%&''*+/=?^_`{|}~-]+)*@([a-z0-9]+[a-z0-9-]*)*[a-z0-9]+(\.([a-z0-9]+[a-z0-9-]*)*[a-z0-9]+)*\.[a-z]{2,6}$'),
    password VARCHAR(255) NOT NULL,
    groupId BINARY(36),
    FOREIGN KEY (groupId) REFERENCES UserGroup(id)
);

CREATE TABLE `UserThirdParty`
(
    thirdPartyId VARCHAR(255) PRIMARY KEY,
    userId BINARY(36) UNIQUE NOT NULL,
    FOREIGN KEY (userId) REFERENCES User(id),
    INDEX (userId)
);

CREATE TABLE `Directory`
(
    id BINARY(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parentDirId BINARY(36),
    permission VARCHAR(3) NOT NULL CHECK (permission REGEXP '^[0-7][0-7][0-7]$'),
    ownerUserId BINARY(36) NOT NULL,
    ownerGroupId BINARY(36) NOT NULL,  # TODO: Learn how group works
    size BIGINT CHECK (size >= 0) NOT NULL,
    FOREIGN KEY (parentDirId) REFERENCES Directory(id),
    FOREIGN KEY (ownerUserId) REFERENCES User(id),
    FOREIGN KEY (ownerGroupId) REFERENCES UserGroup(id)
);

CREATE TABLE `File`
(
    id BINARY(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parentDirId BINARY(36),
    permission VARCHAR(3) NOT NULL CHECK (permission REGEXP '^[0-7][0-7][0-7]$'),
    ownerUserId BINARY(36) NOT NULL,
    ownerGroupId BINARY(36) NOT NULL,
    size BIGINT CHECK (size >= 0) NOT NULL,
    fileType ENUM('NONE', 'TXT') NOT NULL,
    content VARCHAR(255),
    FOREIGN KEY (parentDirId) REFERENCES Directory(id),
    FOREIGN KEY (ownerUserId) REFERENCES User(id),
    FOREIGN KEY (ownerGroupId) REFERENCES UserGroup(id)
);