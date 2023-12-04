# Backend Setup Instructions

### Overview

The backend uses Maven and Spring Boot to run and connect to a mysql 
database that runs in a Docker container. For dev work, two primary options 
are available to test the program: running everything locally, or running 
the program on the 506 vm. Other combinations are possible with ssh 
tunneling or other options, but will not be covered in these instructions.

### Considerations

The considerations when setting up your development environment are 
primarily based on ease of use and (theoretical) stability, though this 
second concern should be of minimal consequence if the environment is 
properly set up.

## Machine Choices

### 506 VM
The 506 vm is most likely going to be the machine that will run the backend 
for the purposes of the demo. This means that any code that runs well on the 
cs machine should not have any additional issues when running a demo.

### Local setup
The local setup is quite a bit faster in compiling code and executing http 
requests via PostMan or other tools. However, you will need to install 
docker - this is free, but is a consideration.

## Setup

The setup for both options is similar, though running locally will require 
additional initial setup. If using the 506 vm, docker should be already set 
up; you may or may not have to set up an instance of mySQL, and you will 
likely have to modify application-dev.properties.

### Docker

Docker desktop can be installed with or without a GUI, and for the purposes 
of this guide will be used to host the mySQL database to better mirror the 
setup of the 506 VM.

### Maven

Maven is required to build and run the backend program. Follow [these 
instructions](https://maven.apache.org/install.html) to install maven. If 
you're using a unix system, you can use apt (linux) or homebrew (macOS) to 
install.

### mySQL 

##### Starting mySQL & PHPMyAdmin in Docker

Save the following as TestServer.yml after entering a password in both 
PASSWORD fields. Then, execute the command on line 5 of the document to 
start the docker containers.
```
## TestServer.yml ##
#    IF USING ON 506 VM:
#    Please use the 'docker ps' command to ensure that the
#    server and PHPAdmin are not running before opening with the command:
#    docker compose -f TestServer.yml -p testserver up -d
version: '3.8'
services:
  db2:
    image: mysql/mysql-server:latest
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: #SET A NEW ROOT PASSWORD HERE
      MYSQL_DATABASE: testFS
      MYSQL_ROOT_HOST: '%'
    volumes:
      - testserv:/var/lib/mysql
    ports:
      - "13306:3306"
  phpmyadmin2:
    image: phpmyadmin/phpmyadmin:latest
    restart: always
    depends_on:
      - db2
    environment:
      PMA_HOST: db2
      PMA_USER: root
      PMA_PASSWORD: #SET TO MATCH THE MYSQL_ROOT_PASSWORD
    ports:
      - "8081:80"
volumes:
  testserv:
```

The commands in the comments of the above file should be run to instantiate 
the docker container with mySQL and PHPMyAdmin. With this config, the mySQL 
database will communicate at 127.0.0.1:13306. PHPMyAdmin is reachable at 127.
0.0.1:8081; going to this address on a web browser will open the PHPMyAdmin GUI.

##### Creating initial database tables
This step is most easily done in the PHPMyAdmin GUI. Navigate to the SQL tab 
on the top bar, or go to http://127.0.0.1:8081/index.php?route=/server/sql; 
then, enter the following SQL commands to initialize both the Directory and 
File tables and populate the Directory table with test rows.

```
--- CreateTables.sql ---

CREATE TABLE Directory 
(
    id INT(20) AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    parentDirId INT(20),
    permission INT CHECK (
        permission >= 0 AND permission <= 777 AND
        CAST(permission AS CHAR(3)) REGEXP '^[0-7]{1,3}$'
    ),
    ownerUserId INT NOT NULL,
    ownerGroupId INT NOT NULL,
    size BIGINT CHECK (size >= 0) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE File
(
    id INT(20) AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    parentDirId INT(20),
    permission INT CHECK (
        permission >= 0 AND permission <= 777 AND
        CAST(permission AS CHAR(3)) REGEXP '^[0-7]{1,3}$'
    ),
    ownerUserId VARCHAR(50) NOT NULL,
    ownerGroupId VARCHAR(50) NOT NULL ,
    size BIGINT CHECK (size >= 0) NOT NULL,
    fileType ENUM('NONE', 'TXT') NOT NULL,
    content VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (parentDirId) REFERENCES Directory(id)
);



-- ROOT DIRECTORY
INSERT INTO Directory (name, permission, ownerUserId, ownerGroupId, size) VALUES ("root", 1, 1, 1, 50);

-- Child directories
INSERT INTO Directory (name, parentDirId, ownerUserId, ownerGroupId, size) 
    VALUES ("Desktop", 1, 1, 1, 50),
        ("Documents", 1, 1, 1, 50),
        ("Downloads", 1, 1, 1, 50),
        ("506", 3, 1, 1, 50),
        ("TheSequel", 5, 1, 1, 50),
        ("Music", 2, 1, 1, 50),
        ("John Prine", 7, 1, 1, 50),
        ("Bruised Orange", 8, 1, 1, 150)
        ;
```

### Running the Spring Boot App

The database should now be ready to be accessed by the application. Checkout 
the feat/5 or feat/79 branches (as of 11/14/23, liable to change) for a 
working version of the backend, contained in 
mysql-file-system/mysql-fs-backend. Then:
1. Using your favorite IDE, open 
/mysql-fs-backend/pom.xml as a project.
2. To mysql-fs-backend/src/main/resources (you may have to create this 
   folder) you need to add two documents:
   1. *application.properties*
      ```
      ## application.properties ##
      spring.profiles.active=dev
      ```
   2. *application-dev.properties*
      ```
      ## application-dev.properties ##
        ## This is the port that the application will listen on, and the port 
        ##      that
        ##      HTTP requests will send to - if on the 506 vm, try to use a 
        ##      unique one
        ##      (And let us all know in the slack!)
      server.port=8088
        
        ## This is the url for the mysql database. The address
        ##      should be jdcb:mysql://localhost/MYSQL-DOCKER-CONTAINER'S-PORT/MYSQL-DATABASE-NAME
      spring.datasource.url=jdbc:mysql://localhost:13306/testFS
      
        ## The username and password for the mysql server you're using
      spring.datasource.username=root
      
      
      spring.datasource.password= # YOUR PASSWORD FROM TestServer.yml ABOVE
      
      
        ## Everything from here on down probably shouldn't need to be changed (Hopefully, otherwise
        ##      you're outside of the scope of this instructional)
      spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
      spring.datasource.hikari.maximumPoolSize=2
      spring.jpa.hibernate.ddl-auto=none
      spring.jpa.show-sql=true
      spring.jpa.open-in-view=true
      spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
      spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
      spring.session.jdbc.initialize-schema=always
        
      mysqlfs.api.config.maxBrowseDepth=10
      ```

3. From /mysql-fs-backend, run ```mvn clean install```
   - The build should succeed. So far, the most common error seems to be:
     ```
     Failed to execute goal org.apache.maven.plugins:
     maven-surefire-plugin:3.0.0:test (default-test)
     ```
     If the build fails, check that your ports are correct and that your 
     root password is the same in all spots - there are 3 fields that need 
     to match, two in TestServ.yml and one in application-dev.properties.
4. Run ```mvn spring-boot:run``` The program should build without errors.
5. If you've reached this spot, you're all done - you can use Postman or 
   similar to start sending http requests to the backend at 127.0.0.1:8088 
   (unless you change the port in application-dev.properties).