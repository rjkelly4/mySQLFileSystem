import mysql.connector
import argparse

def run_sql_script(path, v):
    with open(path, "r") as file:
        connection = None
        try:
            connection = mysql.connector.connect(
                user='root',
                password='notguest',
                host='127.0.0.1',
                port='5066',
            )
            cursor = connection.cursor()
           
            print("Connection Established") if v else None

            # Execute the SQL script
            operation = file.read()
            print("Loaded script") if v else None
            
            for result in cursor.execute(operation, multi=True):
                if result.with_rows:
                    print("Rows produced by statement '{}':".format(result.statement)) if v else None
                    print(result.fetchall()) if v else None
                else:
                    print("Number of rows affected by statement '{}': {}".format(result.statement, result.rowcount)) if v else None
            
            connection.commit()
            print("Committed") if v else None

        except mysql.connector.Error as error:
            print(f"Error: {error}")

        if connection != None and connection.is_connected():
            cursor.close()
            connection.close()
            print("MySQL connection terminated") if v else None

def run_named():
    # checks if the passed in named script is in the list
    if args.name not in ["reset", "usergroup", "normal", "simple", "deep"]:
        print("Try one of these instead: reset, usergroup, tables, normal, simple, deep")
        return
    
    # reset the table before doing anything
    run_sql_script("./clearTables.sql", False)
    print("Cleared tables") if args.verbose else None
    run_sql_script("./createTables.sql", False)
    print("Created tables") if args.verbose else None
    
    # create usergroup for the rest
    if args.name != "reset":
        run_sql_script("./createUserGroup.sql", False)
        print("Created users and groups") if args.verbose else None

    # the mocked data scripts
    if args.name == "normal":
        run_sql_script("./mockData.sql", False)
        print("Loaded the normal mocked filesystem data") if args.verbose else None
    elif args.name == "simple":
        run_sql_script("./mockDataSimple.sql", False)
        print("Loaded the simple mocked filesystem data") if args.verbose else None
    elif args.name == "deep":
        run_sql_script("./mockDeepTree.sql", False)
        print("Loaded the deep tree mocked filesystem data") if args.verbose else None

if __name__ == "__main__":
    # args parser
    parser = argparse.ArgumentParser(description='Script for running SQL scripts for filesystem mocking')
    parser.add_argument("-n", "--name", help="quick usage scripts such as simple, deep, and normal")
    parser.add_argument("-s", "--specific", help="specific script in utilities")
    parser.add_argument("-v", "--verbose", help="verbose output (only works with -n)", action="store_true")
    args = parser.parse_args()

    # prints help when no arguments given
    if args.name is None and args.specific is None:
        parser.print_help()
        parser.exit(1)

    # sees if it a specific script or one already named
    if args.specific is not None:
        run_sql_script("./" + args.specific, args.verbose)
    else:
        if args.name is not None:
            run_named()
    print("Terminated")
