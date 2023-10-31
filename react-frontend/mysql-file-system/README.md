# About this App
For now, this app makes use of a faux file system (defined in `src/data/testFileTree.js`), 
but it has been written to be easily converted to work with SQL and our backend database.
You can navigate in two ways so far:
- Follow links within the displayed browser
- Type file paths directly into the URL bar
  - The proper format is `localhost:3000/browse/<path>` where path is the path you would like
to open.
# Running the React App
1. Ensure `npm` is installed.
2. From `mysql-file-system/react-frontend/mysql-file-system`, run `npm install`.
3. Run `npm start`.
4. Visit `localhost:3000` in the web browser of your choice.