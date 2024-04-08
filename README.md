# Test Helper with Database Integration

## Overview

The Test Helper with Database Integration is an enhanced version of the original project. In this version, the application interacts with a MySQL database for storing and managing questions, question stocks, and user authentication. It features role-based access control, allowing different staff members to perform various tasks based on their roles. The roles include practitioner, professor, and senior professor, each with specific permissions. Additionally, the application provides the functionality to print tests directly from the database.
* Side note: an "American Question" means a multiple choice question.

## Features

- **Database Integration**: Utilizes MySQL database for storing questions, question stocks, and user information.
- **Role-Based Access Control**: Staff members are assigned roles with specific permissions.
- **User Authentication**: Secure login system for staff members.
- **Printing Tests**: Ability to print tests directly from the database.

## Usage

### User Authentication

1. Launch the application.
2. Enter your username and password to log in.

### Role-Based Actions

#### Practitioner

- View questions.

![1](https://github.com/ItsSnirLevi/TestHelper-SQL/assets/127433228/1328b268-1f79-44e6-8787-10a7a6f126b8)

#### Professor

- View question stocks.
- Add and update questions.

![2](https://github.com/ItsSnirLevi/TestHelper-SQL/assets/127433228/71581db1-dfcb-4762-8e40-718ae3c93e47)

#### Senior Professor

- Perform all actions available to the professor.
- Create tests.

![3](https://github.com/ItsSnirLevi/TestHelper-SQL/assets/127433228/dc8bb8de-56b1-4dcf-b4cf-b4f78c6da1b6)

### Printing Tests

1. Select the option to print tests from the menu.
2. Follow the prompts to specify the test details.
3. The test will be printed directly from the database.

## Installation

1. Clone the repository to your local machine.
`git clone https://github.com/ItsSnirLevi/TestHelper-SQL.git`
2. Set up a MySQL database and import the provided schema.
3. Update the database connection details in the application configuration.
4. Compile and run the application.

## Dependencies

- MySQL database
- JDBC driver for MySQL

## Contributing

Contributions are welcome! If you'd like to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add new feature'`).
5. Push to the branch (`git push origin feature/your-feature`).
6. Create a new Pull Request.
