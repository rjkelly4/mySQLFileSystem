
const api = "//localhost:8088/api";

/**
 * Makes a request to the specified endpoint and returns a Promise that resolves
 * to the JSON object contained within the response.
 *
 * @param endpoint The endpoint to be requested
 * @param request The request object
 * @returns {Promise<any>} Resolves to the JSON object contained within the response.
 */
const makeRequest = async (endpoint, request) => {
    const response = await fetch(endpoint, request);
    return response.json();
}

//////////////////////
// Browse Functions //
//////////////////////

/**
 * Assigns the appropriate childrenLoaded flag to the provided directory and its children,
 * up to the specified depth. Assigns false if the current depth is equal to the last depth,
 * true if the current depth is less. This function does nothing if current depth is greater
 * than last depth.
 *
 * @param dir The directory that is to be assigned a value on this recursion.
 * @param currDepth The level of depth of dir
 * @param lastDepth The final level of depth to check
 */
const assignChildrenLoaded = (dir, currDepth, lastDepth) => {
    if (currDepth > lastDepth) return;

    if (currDepth === lastDepth) {
        dir.childrenLoaded = false;
    } else {
        dir.childrenLoaded = true;
        for (let child of dir.content) {
            if (child.isDirectory)
                assignChildrenLoaded(child, currDepth + 1, lastDepth);
        }
    }
}

/**
 * Requests a directory from the /browse/folders API endpoint with its descendants
 * up to the specified depth. Also assigns a childrenLoaded flag to each directory object.
 *
 * @param path The path to initially search
 * @param depth The number of generations of descendants to request
 * @returns {Promise<*>} Resolves to a JSON object associated with the directory at path.
 */
export const getDirectoryContent = async (path, depth) => {
    const endpoint = `${api}/browse/folders`;
    const request = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            path: path,
            browseDepth: depth
        })
    }

    const data = (await makeRequest(endpoint, request)).payload;

    assignChildrenLoaded(data, 0, depth);

    return data;
}

/**
 * Requests a file's contents from the /browse/files API endpoint.
 *
 * @param path
 * @returns {Promise<*>}
 */
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