# Simple Servlet based server, meant for XSS demoes ONLY
The project is Created from smaller samples, and therefore has a lot of code-duplication. Live with that, our write your own server(s) ;-)

The project uses server side rendering only, that is no JavaScript, somehow similar to what you did on second semester
  
## Setting up
This project can be used with an in-memory database (default), or an external MySQL database

### In Memory Database
- This is the default, and you should be able to run the project right out of the box, after having forked it.
- **Note** that ALL data will be lost, when you restart the server

### Using an external (MySQL) database
To use an external database you must do the following:
- Create your own database, and change the _persistence-unit_ called `pu-mysql` to reflect settings in your DB
- Change the declaration of `pu_name` in Settings.java to `String pu_name = "pu-mysql"`

### Prevent Stored XSS
Set the variable `escape` in the class `Settings` to `true` to escape user data before adding it to the database
