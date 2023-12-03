package com.mysqlfsbackend.model;

import java.sql.*;
/**
 * JDBCConnectionMaker is a utility class for managing the details of
 *   instantiating a JDBC Connection object.
 * Although it resembles an Object Pool class, it is not quite that.
 * Mainly, its purpose is to centralize the information required to
 *   to connect to a DB, so that the information is not duplicated
 *   across any other classes/methods that would need to instantiate
 *   a JDBC Connection.
 * None of this has been tested, so it may contain minor or major errors.
 *
 * @author Scott Swanson
 * @version 2023.10.08
 */
public class JDBCConnectionMaker {
    private String dbName;
    private String hostName;
    private String portNumber;
    private String userName;
    private String password;

    /**
     * Constructor requires all information needed to instantiate a
     *   functional Connection object.
     *   Note that the constructor makes no attempt to validate
     *     arguments.
     * @param   dbName_new      also called the schema
     * @param   hostName_new
     * @param   portNumber_new
     * @param   userName_new
     * @param   password_new
     * @return  a valid, initialized JDBCConnectionMaker object
     */
    public JDBCConnectionMaker (
            String dbName_new,
            String hostName_new,
            String portNumber_new,
            String userName_new,
            String password_new
    ) {
        this.dbName = dbName_new;
        this.hostName = hostName_new;
        this.portNumber = portNumber_new;
        this.userName = userName_new;
        this.password = password_new;
    }

    /**
     * Attempts to instantiate a JDBC Connection object.
     * @return  a valid Connection object for the DB configuration
     *            the JDBCConnectionMaker was constructed with.
     *          null if the connection failed for any reason.
     */
    public Connection getConnection () {
        Connection dbCxn = null;
        try {
            String jdbcURL =
                    "jdbc:mySQL://"
                            + hostName
                            + ":"
                            + portNumber
                            + "/"
                            + dbName;
            dbCxn = DriverManager.getConnection(
                    jdbcURL,
                    userName,
                    password
            );
        }
        catch(SQLException e){
            System.out.println(e);
            System.out.println("Database connection failed!");
        }

        return dbCxn;
    }
}