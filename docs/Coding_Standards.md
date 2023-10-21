# Coding Standards and Guildlines
### CS 506 T_04 | MySQL File System
***
## Introduction
> The coding standards and guidelines outlined in this document serve as a reference for our development team, providing a consistent set of best practices to follow when writing code for projects involving React, JavaScript, Java, Spring, and MySQL.
References: [Google Java Guide](https://google.github.io/styleguide/javaguide.html), [Google JS Guide](https://google.github.io/styleguide/jsguide.html)

## Java Coding Standards
  - #### General Guidlines
    - **OOP**
    - **Consistency**
      - Follow a consistent code style throughout the project.
      - Adopt widely recognized Java coding conventions and stick to them.
    - **Code Resusability**
      - Encourage code reusability by creating reusable classes and components.
      - Favor composition over inheritance.
    - **Error Handling**
      - Use error handling mechanisms to catch all potential exceptions.
      - Provide meaningful error messages and logs for troubleshooting.
    - **Descriptive Names**
      - Use meaningful and descriptive names for classes, methods, variables, and packages for code readability
      - Generally avoid vauge/single-letter/abbreviated names.
    - **Code comments**
      - Use comments sparingly
      - Keep comments up-to-date as code changes.
      - Document public APIs and interfaces with JavaDoc comments.
  - #### Code formatting
    - **Line format**
      - Use 4 spaces (avoid /t) for code indentation
      - Indent line wrapping
      - Set line wrap limit at 100-120 characters 
      - Break long statements (e.g. >2 if clauses, >5 funcion arguments) into multiple lines
    - **White space**
      - Use a linebreak between methods, classes, interfaces, etc
    - **Braces**
      - Use K&R style braces
      - Can use `{}` on the same line for empty blocks 
    - **Imports**
      - Organize imports by grouping them into sections (e.g., Java standard library, third-party libraries, project-specific classes).
      - Disable/avoid wildcard imports
  - #### Naming Conventions
    - **Class Names**
      - Use nouns and PascalCase
    - **Method Names**
      - Use camelCase and start with verbs
    - **Variables**
      - Use camelCase
      - Class variables should have the `this.` prefix
    - **Constants**
      - Use uppercase letters with underscores
  - #### Documentation
    - **JavaDoc**
      - Document public classes, interfaces, methods, and fields
      - Include descriptions of functionality, parameters, return value, and explanations for exceptions 
    - **Inline Comments**
      - Use inline comments to clarify the intention and logic (not implementation) in complex algorithms or non-obvious code


## React & JavaScript Coding Standards
  - #### General Guidlines
    - **Consistency**
      - Follow a consistent code style throughout the project.
      - Adopt widely recognized JavaScript coding conventions and stick to them.
    - **Modularization**
      - Break down code into smaller, reusable modules and components.
      - Promote separation of concerns and single responsibility principles.
    - **Error Handling**
      - Use error handling mechanisms to catch all potential exceptions.
      - Provide meaningful error messages and logs for troubleshooting.
      - Use error boundaries for React components to gracefully handle errors
    - **Code comments**
      - Use comments sparingly, focus on clarifying the intention and logic (not implementation) in complex algorithms or non-obvious code
      - Keep comments up-to-date as code changes.
      - Document React component usage, props, and expected behavior in component comments.
  - #### Code formatting
    - **Line format**
      - Use 4 spaces (avoid /t) for code indentation
      - Indent line wrapping
      - Set line wrap limit at 100-120 characters 
      - Break long statements (e.g. >2 if clauses, >5 funcion arguments) into multiple lines
    - **Braces**
      - Use K&R style braces
      - Can use `{}` on the same line for empty blocks 
    - **Imports/Exports**
      - Use ES modules
      - Organize imports by grouping them into sections
      - Disable/avoid wildcard imports
  - #### Naming Conventions
    - **Component Names**
      - Use nouns and PascalCase
    - **Method Names**
      - Use camelCase and start with verbs
    - **Variables**
      - Use camelCase
    - **Constants**
      - Use uppercase letters with underscores
  - #### Documentation
    - **React Component Comments**
      - Document React component usage, props, and expected behavior in component comments.
      - Provide types for props
    - **Inline Comments**
      - Use inline comments to clarify the intention and logic (not implementation) in complex algorithms or non-obvious code