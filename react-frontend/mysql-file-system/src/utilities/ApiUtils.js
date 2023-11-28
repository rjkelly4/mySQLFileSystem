
const api = "//localhost:8088/api";

const makeRequest = async (endpoint, request) => {
    const response = await fetch(endpoint, request);
    if (!response.ok) {
        alert("Error detected in browseFolders");
        return;
    }
    const data = response.json();

    // TODO: Filter data before returning

    return data;
}

//////////////////////
// Browse Functions //
//////////////////////

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
}

export const getFileContent = async (path) => {
    const endpoint = `${api}/browse/folders`;
    const request = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            path: path
        })
    }

    const response = await fetch(endpoint, request);
    if (!response.ok) {
        alert("Error detected in browseFolders");
        return;
    }
    const data = response.json();

    // TODO: Filter data before returning

    return data;
}