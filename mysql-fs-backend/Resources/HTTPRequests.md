# List of Current HTTP Request Formats

### /api/browse/folders

### /api/browse/files

### /api/postFile

| Request Param | Type    | Notes            |
|---------------|---------|------------------|
| name          | String  |                  |
| parentDirId   | int     |                  |
| permission    | int     |                  |
| ownerUserId   | String  |                  |
| ownerGroupId  | String  |                  |
| size          | int     |                  |
| fileType      | enum    | 'NONE' or 'TXT'  | 
| content       | String  |                  |


### /api/postDirectory
| Request Param | Type    | Notes            |
|---------------|---------|------------------|
| name          | String  |                  |
| parentDirId   | int     |                  |
| permission    | int     |                  |
| ownerUserId   | String  |                  |
| ownerGroupId  | String  |                  |
| size          | int     |                  |

### /api/deleteFile

| Request Param | Type   | Notes |
|---------------|--------|-------|
| id            | int    |       |
| name          | String |       |
| parentDirId   | int    |       |


### /api/deleteDirectory

| Request Param | Type   | Notes |
|---------------|--------|-------|
| id            | int    |       |
| name          | String |       |
| parentDirId   | int    |       |
