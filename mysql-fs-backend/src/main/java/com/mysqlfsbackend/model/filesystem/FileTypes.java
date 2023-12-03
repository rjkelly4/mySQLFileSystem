package com.mysqlfsbackend.model.filesystem;

/**
 * This enumeration defines the file types (and corresponding extensions) that is managed by MySQLFSBackend
 */
public enum FileTypes {
    NONE(""),
    TXT(".txt");

    private final String fileExtension;

    /**
     * Initialize each file type enum instance with a given file extension.
     *
     * @param fileExtension The file extension that will be associated with the enum.
     */
    FileTypes(final String fileExtension) {
        this.fileExtension = fileExtension;
    }

    /**
     * Return the file extension string associated with the enum.
     *
     * @return The corresponding file extension string.
     */
    @Override
    public String toString() {
        return fileExtension;
    }
}
