# Requirements and Specification Document 

2023-09-29, version 1.0.1


# Project Description

A file system is used to store, organize, and manage files and directories in an operating system. The idea of this project is to implement an efficient filesystem using a relational database as the underlying storage system. The project should have a web interface that allows users to create, read, update, and delete files, directories, or subdirectories. Users should be able to visualize any directory or subdirectory as a tree structure in the web interface.

Tech Stack: Java, JavaScript, React, HTML, and CSS


# Document Revision History

Your first version of this document is version 1.0. After that minor changes increment the minor version number (e.g., 1.1, 1.2, ...) and major changes increment the major version number and set the minor number to zero (e.g., 2.0, 3.0, ...). We will follow this convention with other documents as well. 

Rev. 1.0 &lt;2023-02-27>: initial version 
>Add details to Product Backlog, User Requirements, System Requirements, and Security requirements

Rev. 1.0.1 \<2023-10-10>: Convert to .md format


# User Requirements


- Log-in / Sign-up 
    1. Register a new account with a username and password 
    2. Store the First, Middle, and Last name of the user
    3. Store the email of the user 
- Create, read, update, and delete operations in the file system
    1. Upload a new file that contains text
    2. Create a new directory or subdirectory
        - Cut, copy, paste file or directory 
    3. Delete file or directory
        - View recently deleted files or directories in a trash folder 
    4. Move file(s) from one directory to another
    5. Be able to modify permissions for content
- Visualize any directory or subdirectory as a tree structure
    1. View the files and folders in a directory when clicked on it
    2. View the contents of the file in the web application when clicked on it
    3. Visualize any directory in a tree format


# Security Requirements



- One role with different levels of permissions: User
- User authentication and authorization are managed by Duo, which makes use of MFA
- Uses a mobile device with Duo App to receive push notifications
    - Can also use a fob to generate a passcode
- User has access to their file system and only theirs unless sharing permissions are authorized by another user


# System Requirements 



- Server Requirements 
    - The following are hosted in Team 04 CSL machine running Ubuntu 22.04 LTS 
        - Backend: Java
        - Front End: JavaScript, React, HTML, CSS
        - Minimum Server Specs: JDK 19, 2 CPU Cores, 2 GB RAM 
    - Database: MySQL Docker Container
        - Docker Container
        - Minimum Server Specs: 2 CPU cores, 2 GB RAM, 40 GB Disk Space 
        - Two ports allocated for the stacks above 
    - User Requirements 
        - Web Browser (Desktop)
        - Internet connection


# Project Features include:



* Use a relational database system to store the structure of files and directories, data in files, metadata about files and directories like permission, date created/updated, etc.
* Perform create, read, update, and delete operations in the file system using a user-friendly web application.
    * _Upload a new file that contains text_
    * _Create a new directory or subdirectory_
    * _Delete file or directory_
    * _Move file(s) from one directory to another_
* The web application should have a user login and handle permissions of the file system. For example, only users who have permission to update a file can do so.
    * _Create an account in the application_
    * _Login into the web application_
    * _View content that I have the permission for_
    * _Be able to modify permissions for contents I have full admin access to_
* Users should be able to visualize any directory or subdirectory as a tree structure in the web application. This can be implemented from scratch or use any pre-existing tools.
    * _View the files and folders in a directory when clicked on it_
    * _View the contents of the file in the web application when clicked on it_
    * _Visualize any directory in a tree format_
    * _Do all these actions in a user-friendly UI that resembles the file system in the operating systems._
* Implement additional operations like renaming, copying, pasting, cutting, and moving files between directories.
    * _Cut, copy, paste file or directory_
    * _View recently deleted files or directories in a trash folder_


# Use Cases

[//]: # (Converted to markdown format 10/17 - Alex M)

| Name              | Create file                                                             |
|-------------------|-------------------------------------------------------------------------|
| Actors            | Web clients                                                             |
| Triggers          | Clicking on “create/add” button                                         |
| Events            | Users are able to add information about their file, as well as the file |
| Exit Conditions   | File and all info fields are correctly filled out.                      |
| Post-Conditions   | File is part of the file tree and can be viewed/accessed.               |
| Acceptance Test   | File is now in the file system                                          |

<br>

| Name              | User log in                                                                 |
|-------------------|-----------------------------------------------------------------------------|
| Actors            | Web clients                                                                 |
| Triggers          | Click “log in” button                                                       |
| Events            | Username and password are valid                                             |
| Exit Conditions   | Users are able to access their file system, as well as any relevant options |
| Post-Conditions   | User is logged in.                                                          |
| Acceptance Test   | User can log in, access the correct file systems and permissions            |

<br>

| Name              | Visualize directory or sub-directory                                  |
|-------------------|-----------------------------------------------------------------------|
| Actors            | Web clients                                                           |
| Triggers          | Clicking on “view directory” button                                   |
| Events            | Directory is displayed graphically to the user                        |
| Exit Conditions   | User is attempting to access a directory they have permission to view |
| Post-Conditions   | Visualization of the directory is shown to the user                   |
| Acceptance Test   | Directory/files are correct and displayed in the correct order        |

<br>

| Name              | Delete file                                                                                                   |
|-------------------|---------------------------------------------------------------------------------------------------------------|
| Actors            | Web clients                                                                                                   |
| Triggers          | Clicking on “delete” button                                                                                   |
| Events            | File is removed from the filesystem, space is recognized as free, user can no longer see info about the file. |
| Exit Conditions   | Delete button is pressed and confirmed by the user.                                                           |
| Post-Conditions   | File is no longer in the file system, and the system recognizes the space as free.                            |
| Acceptance Test   | File and its information are no longer in the file system.                                                    |

<br>

| Name              | Modify permissions                                                                                |
|-------------------|---------------------------------------------------------------------------------------------------|
| Actors            | Administrators                                                                                    |
| Triggers          | Interacting with permissions menu/"save" button                                                   |
| Events            | User permissions are modified                                                                     |
| Exit Conditions   | Each user has permissions filled out.                                                             |
| Post-Conditions   | User permissions are modified.                                                                    |
| Acceptance Test   | Users can access what they have permissions for and are denied access for the rest of the system. |

<br>

| Name            | Create Directory                                                     |
|-----------------|----------------------------------------------------------------------|
| Actors          | Web clients                                                          |
| Triggers        | Interacting with a “create directory” button                         |
| Events          | Creates a new directory or subdirectory                              |
| Exit Conditions | Users are able to create new directories or subdirectories           |
| Post-Conditions | Directories are created                                              |
| Acceptance Test | User can create new directories and subdirectories within the system |
