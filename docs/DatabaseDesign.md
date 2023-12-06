# Database Design - MySQL File System

### Purpose of This Document

> The idea of this project is Implementing an efficient ... filesystem using a relational database
> as the underlying storage system. The project should have a web interface that allows users to
> create, read, update and delete files, directories or subdirectories. Users should be able to
> visualize any directory or subdirectory as a tree structure in the web interface. The basic file
> structure of the mySQL database must reflect the files, their directories, and their
> subdirectories. This will be managed through a set of relational database tables.

To implement this filesystem, mySQL relational databases will be used. As such, this document is
intented to outline the database structure, and will be updated to reflect changes in the database
design as the team works on implementation.

### [ER Diagram](https://lucid.app/lucidchart/4e16ef6d-6159-47d8-bf4c-d395b8b7c4ba/edit?viewport_loc=-858%2C-286%2C2478%2C1504%2C0_0&invitationId=inv_ed58b048-6c63-44ff-af09-f71d9f0e2d43)

-   #### Entities
    -   User
    -   Group
    -   UserThirdParty
    -   Directory
    -   File
-   #### Relationships
    -   UserThirdParty <-> User
    -   Group <- User
    -   User <- Directory
    -   UserGroup <- Directory
    -   User <- File
    -   Group <- File
    -   Directory <- File
    -   Directory <-> Directory (parent)
-   #### Constraints
    -   Foreign key `Directory.parentDirId` and `File.parentDirId` -> `Directory.id`
    -   Foreign key `UserThirdParty.userId`, `Directory.ownerUserId`, and `File.ownerUserId` -> `User.id`
    -   Foreign key `Directory.ownerGroupId` and `File.ownerGroupId` -> `Group.id`
    -   Foreign key `UserThirdParty.userId` 
-   #### Data Schema
    -   See ER Diagram

### MySQL Schema

#### UserGroup

| Field        | Type         | Null | Key | Default                                             |
| ------------ | ------------ | ---- | --- | --------------------------------------------------- |
| id           | binary(36)   | No   | PRI | uuid                                                |
| name         | varchar(255) | No   |     |                                                     |

#### User

| Field        | Type         | Null | Key | Default                                             |
| ------------ | ------------ | ---- | --- | --------------------------------------------------- |
| id           | binary(36)   | No   | PRI | uuid                                                |
| name         | varchar(255) | No   |     |                                                     |
| email        | varchar(255) | No   |     | unique, follows email format                        |
| password     | varchar(255) | No   |     | hashed                                              |
| groupId      | binary(36)   | No   | MUL | uuid                                                |

#### UserThirdParty

| Field        | Type         | Null | Key | Default                                             |
| ------------ | ------------ | ---- | --- | --------------------------------------------------- |
| thirdPartyId | binary(36)   | No   | PRI | uuid                                                |
| userId       | binary(36)   | No   | UNI | uuid                                                |


#### Directory

| Field        | Type         | Null | Key | Default                                             |
| ------------ | ------------ | ---- | --- | --------------------------------------------------- |
| id           | binary(36)   | No   | PRI | uuid                                                |
| name         | varchar(255) | No   |     | unique accross files and dirs within a path         |
| parentDirId  | binary(36)   | Yes  | MUL | uuid                                                |
| permission   | varchar(255) | No   |     | non-negative, at most 3 digits, each digit in 0 - 7 |
| ownerUserId  | binary(36)   | No   | MUL | uuid                                                |
| ownerGroupId | binary(36)   | No   | MUL | uuid                                                |
| size         | bigint       | No   |     | non-negative, in byte(s)                            |

#### File

| Field        | Type                | Null | Key | Default                                             |
| ------------ | ------------------- | ---- | --- | --------------------------------------------------- |
| id           | binary(36)          | No   | PRI | uuid                                                |
| name         | varchar(255)        | No   |     | unique accross files and dirs within a path         |
| parentDirId  | binary(36)          | Yes  | MUL | uuid                                                |
| permission   | varchar(255)        | No   |     | non-negative, at most 3 digits, each digit in 0 - 7 |
| ownerUserId  | binary(36)          | No   | MUL | uuid                                                |
| ownerGroupId | binary(36)          | No   | MUL | uuid                                                |
| size         | bigint              | No   |     | non-negative, in byte(s)                            |
| fileType     | enum('NONE', 'TXT') | No   |     |                                                     |
| content      | varchar(255)        | Yes  |     |                                                     |

### MySQL Commands

```sql
CREATE TABLE UserGroup
(
    id BINARY(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
```

```sql
CREATE TABLE User
(
    id BINARY(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
        CHECK (email REGEXP '^[a-z0-9!#$%&''*+/=?^_`{|}~-]+(\.[a-z0-9!#$%&''*+/=?^_`{|}~-]+)*@([a-z0-9]+[a-z0-9-]*)*[a-z0-9]+(\.([a-z0-9]+[a-z0-9-]*)*[a-z0-9]+)*\.[a-z]{2,6}$'),
    password VARCHAR(255) NOT NULL,
    groupId BINARY(36),
    FOREIGN KEY (groupId) REFERENCES UserGroup(id)
);
```

```sql
CREATE TABLE UserThirdParty (
    thirdPartyId VARCHAR(255) PRIMARY KEY,
    userId BINARY(36) UNIQUE NOT NULL,
    FOREIGN KEY (userId) REFERENCES User(id),
    INDEX (userId)
);
```

```sql
CREATE TABLE Directory
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
```

```sql
CREATE TABLE File
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
```

### Future Designs

#### User and Group tables for AAA

#### Filesystem table for managing distributed/separate fs

| Primary Key | Maximum allowed size | Current Size | Filesystem Users<sup>1</sup> | Root Directory<sup>2</sup> |
| ----------: | -------------------- | ------------ | ---------------------------- | -------------------------- |
|           1 |                      |              | _Foreign Key 1_              | _Foreign Key A_            |
|           2 |                      |              | _Foreign Key 2_              | _Foreign Key B_            |

<sup>1</sup> See _User+LoginDatabaseSchema.md_ for more information on the users tables.
