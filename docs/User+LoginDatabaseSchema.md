# Database Design - MySQL User Accounts and Login Information

### Purpose of This Document

>The web application should have user login and handle permissions of the file system. 
>For example, only users having the permission to update a file can do so.

The handling of login and user account information can be handled by a mySQL database outlining
the login credentials and the permissions available to each user. This format 
will ensure consistency in the handling of user information.

### MySQL Tables

The mySQL tables must, as a minimum, hold a user's login credentials (with some form of
security features to ensure that the user's account is not easily accessed) as well as their
access level and permissions.

The implementation may use a link to the files and directories databases/tables for each
user to correctly open that user's individual filesystem.

### Sample tables

#### User information
| Primary Key | Username | User Email             | Password Hash     | Password Salt | Permission(s)   | Filesystem Access |
|------------:|----------|------------------------|-------------------|---------------|-----------------|-------------------|
|           1 | *User1*  | *ex@database.com*      | *Hashed password* |               | *Foreign Key A* | *Foreign Key 1*   |
|           2 | *User2*  | *otheremail@email.com* |                   |               | *Foreign Key B* | *Foreign Key 2*   |

#### Permissions

| Primary Key | Permission Level | Which Actions are Available?   |
|------------:|------------------|--------------------------------|
|           1 | User             | *Upload, read, delete*         |
|           2 | Administrator    | *Add, remove user permissions* |
|           3 | Banned           | *No access to any filesystems* |

### Alternatives

A number of alternative formatting options for the user permissions handling exist.
For example, sharing of filesystems may require different user permissions depending
upon which filesystem they try to access. If this is to be the case, it may make more
sense for the permissions fields to be included within the filesystem database on a 
per-case basis.