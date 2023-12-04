
const api = "//localhost:8088/api";

const makeRequest = async (endpoint, request) => {
    const response = await fetch(endpoint, request);
    if (!response.ok) {
        alert("API Response Error Occurred!");
        return;
    }
    return response.json();
}

//////////////////////
// Browse Functions //
//////////////////////

const assignChildrenLoaded = (dir, currDepth, lastDepth) => {
    if (currDepth > lastDepth) return;

    if (currDepth === lastDepth) {
        dir.childrenLoaded = false;
    } else {
        dir.childrenLoaded = true;
        for (let child of dir.children) {
            assignChildrenLoaded(child, currDepth + 1, lastDepth);
        }
    }
}

export const getDirectoryContent = async (path, depth) => {
    const endpoint = `${api}/browse/folders`;
    const request = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            path: path,
            depth: depth
        })
    }

    const data = await makeRequest(endpoint, request);

    assignChildrenLoaded(data, 0, depth);

    return data;
}

export const getFileContent = async (path) => {
    const endpoint = `${api}/browse/files`;
    const request = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            path: path
        })
    }

    const data = await makeRequest(endpoint, request);

    // TODO: Filter data before returning

    return data;
}

//////////////////////
// Delete Functions //
//////////////////////

export const deleteDirectory = async (id, dirName, parentDirId) => {
    const endpoint = `${api}/deleteDirectory`;
    const request = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: id,
            name: dirName,
            parentDirId: parentDirId
        })
    }

    const data = await makeRequest(endpoint, request);

    // TODO: Filter data before returning

    return data;
}

export const deleteFile = async (id, fileName, parentDirId) => {
    const endpoint = `${api}/deleteFile`;
    const request = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: id,
            name: fileName,
            parentDirId: parentDirId
        })
    }

    const data = await makeRequest(endpoint, request);

    // TODO: Filter data before returning

    return data;
}

/////////////////////
// Patch Functions //
/////////////////////

export const patchParent = async (isDirectory, id, newParentId) => {
    const endpoint = isDirectory ?
            `${api}/patchDirectoryParent` :
            `${api}/patchFileParent`;
    const request = {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: id,
            newParentId: newParentId
        })
    }

    const data = await makeRequest(endpoint, request);

    // TODO: Filter data before returning

    return data;
}

export const patchName = async (isDirectory, id, newName) => {
    const endpoint = isDirectory ?
            `${api}/patchDirectoryName` :
            `${api}/patchFileName`;
    const request = {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: id,
            newParentId: newName
        })
    }

    const data = await makeRequest(endpoint, request);

    // TODO: Filter data before returning

    return data;
}

////////////////////
// Post Functions //
////////////////////

export const postDirectory = async (dirName, parentDirId) => {
    const endpoint = `${api}/postDirectory`
    const request = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: dirName,
            parentDirId: parentDirId,
            permission: 1,
            ownerUserId: 1,
            ownerGroupId: 1,
            size: 50
        })
    }

    const data = await makeRequest(endpoint, request);

    // TODO: Filter data before returning

    return data;
}

export const postFile = async (fileName, parentDirId, content) => {
    const endpoint = `${api}/postFile`
    const request = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: fileName,
            parentDirId: parentDirId,
            permission: 1,
            ownerUserId: 1,
            ownerGroupId: 1,
            size: 50,
            fileType: "text",
            content: content
        })
    }

    const data = await makeRequest(endpoint, request);

    // TODO: Filter data before returning

    return data;
}