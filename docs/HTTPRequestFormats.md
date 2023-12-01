# List of Current HTTP Request Formats
## Design Notes
The http request formats for the filesystem are based on three parts: the 
request methods, the url, and the request body.
#### Request URL
When dealing with api requests, this should start with /api/ for uniformity. 
After this, try to make the language reasonably easy to parse, preferably 
including the method name it is mapped to in the url.
#### Request Methods
We're roughly following REST methods here, with the addition of PATCH.  
PUT methods should include parameters to update all fields for the object, 
but do not create a new object (idempotent).  
POST methods should create a new object (non-idempotent).  
PATCH methods are for updating a single field within the object, and all use 
the same PatchBody class to avoid making specific but near-identical classes 
for each request. If more than one field is to be updated, using a PUT 
method and passing in the current value for any fields that are to remain 
the same is the preferable option.
#### Request Body
The body is in a JSON format, with the params being passed in the format of:  
{  
    "requestParam1": value,  
    "requestParam2": value  
} 

It is worthwhile to note that not all params have to be passed to create or 
change a row, as empty params will be treated as NULL. However, be sure to 
check that the mySQL schema allows for that field to be NULL before 
attempting this, and it is likely worth testing some different, and 
hopefully more elegant, solutions instead of leaving out params.

#### General design notes
1. The id for files and directories is a UUID, stored as a binary with 
   length of 36 in the MySQL database but treated as a String in Java.

### Get
#### /api/browse/folders
| Request Param | Type    | Notes            |
|---------------|---------|------------------|
| path          | String  |                  |
| depth         | int     |                  |

#### /api/browse/files
| Request Param | Type    | Notes            |
|---------------|---------|------------------|
| path          | String  |                  |

### Delete 
#### /api/deleteFile

| Request Param | Type   | Notes                         |
|---------------|--------|-------------------------------|
| id            | String | See "General Design Notes: 1" |


#### /api/deleteDirectory

| Request Param | Type   | Notes                          |
|---------------|--------|--------------------------------|
| id            | String | See "General Design Notes: 1"  |


### Post
#### /api/postFile

| Request Param | Type   | Notes                                      |
|---------------|--------|--------------------------------------------|
| name          | String |                                            |
| parentDirId   | String | Foreign key. See "General Design Notes: 1" |
| permission    | int    |                                            |
| ownerUserId   | String |                                            |
| ownerGroupId  | String |                                            |
| size          | int    |                                            |
| fileType      | String | 'NONE' or 'TXT'                            | 
| content       | String |                                            |


#### /api/postDirectory
| Request Param | Type   | Notes                                      |
|---------------|--------|--------------------------------------------|
| name          | String |                                            |
| parentDirId   | String | Foreign key. See "General Design Notes: 1" |
| permission    | int    |                                            |
| ownerUserId   | String |                                            |
| ownerGroupId  | String |                                            |
| size          | int    |                                            |

### Patch

#### /api/patchDirectoryParent,
#### /api/patchFileParent,
#### /api/patchDirectoryName,
#### /api/patchFileName
| Request Param | Type   | Notes                                                                                                                                                                                 |
|---------------|--------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| modifiedField | String | This is the field to be modified based on the field to be patched; the same name is used for each request in this case to allow for one java class to be used for all patch requests. |
| id            | String | See "General Design Notes: 1"                                                                                                                                                         |