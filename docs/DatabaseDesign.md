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
    -   Directory
    -   File
-   #### Relationships
    -   Group <- User
    -   User <- Directory
    -   UserGroup <- Directory
    -   User <- File
    -   Group <- File
    -   Directory <- File
    -   Directory <-> Directory (parent)
-   #### Constraints
    -   Foreign key `Directory.parentDirId` and `File.parentDirId` -> `Directory.id`
    -   Foreign key `Directory.ownerUserId` and `File.ownerUserId` -> `User.id`
    -   Foreign key `Directory.ownerGroupId` and `File.ownerGroupId` -> `Group.id`
-   #### Data Schema
    -   See ER Diagram

### MySQL Schema

#### Directory

| Field        | Type         | Null | Key | Default                                             |
| ------------ | ------------ | ---- | --- | --------------------------------------------------- |
| id           | binary(36)   | No   | PRI | uuid                                                |
| name         | varchar(255) | No   |     | unique accross files and dirs within a path         |
| parentDirId  | binary(36)   | Yes  | MUL | uuid                                                |
| permission   | int          | Yes  |     | non-negative, at most 3 digits, each digit in 0 - 7 |
| ownerUserId  | int          | No   |     |                                                     |
| ownerGroupId | int          | No   |     |                                                     |
| size         | bigint       | No   |     | non-negative, in byte(s)                            |

#### File

| Field        | Type                | Null | Key | Default                                             |
| ------------ | ------------------- | ---- | --- | --------------------------------------------------- |
| id           | binary(36)          | No   | PRI | uuid                                                |
| name         | varchar(255)        | No   |     | unique accross files and dirs within a path         |
| parentDirId  | binary(36)          | Yes  | MUL | uuid                                                |
| permission   | int                 | Yes  |     | non-negative, at most 3 digits, each digit in 0 - 7 |
| ownerUserId  | int                 | No   |     |                                                     |
| ownerGroupId | int                 | No   |     |                                                     |
| size         | bigint              | No   |     | non-negative, in byte(s)                            |
| fileType     | enum('NONE', 'TXT') | No   |     |                                                     |
| content      | varchar(255)        | Yes  |     |                                                     |

### MySQL Commands

```sql
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
```

```sql
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
```

### Future Designs

#### User and Group tables for AAA

#### Filesystem table for managing distributed/separate fs

| Primary Key | Maximum allowed size | Current Size | Filesystem Users<sup>1</sup> | Root Directory<sup>2</sup> |
| ----------: | -------------------- | ------------ | ---------------------------- | -------------------------- |
|           1 |                      |              | _Foreign Key 1_              | _Foreign Key A_            |
|           2 |                      |              | _Foreign Key 2_              | _Foreign Key B_            |

<sup>1</sup> See _User+LoginDatabaseSchema.md_ for more information on the users tables.
