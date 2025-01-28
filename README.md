# Automation Engineer Task

## Overview

This project is designed to demonstrate automation engineering skills through a series of tasks. The repository contains the necessary code and resources to perform automated testing on a sample application.

## Project Structure

- **src/**: Contains the source code for the automation scripts.
- **pom.xml**: Maven configuration file for managing project dependencies and build configurations.

## Prerequisites

Before running the automation tests, ensure you have the following installed:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) (version 8 or higher)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [Google Chrome Browser](https://www.google.com/chrome/)
- [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/) (compatible with your Chrome browser version)

## Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/Manaf-darabseh/Automation-Engineer-Task.git
   cd Automation-Engineer-Task
   ```

2. **Install Dependencies**:

   Use Maven to install the required dependencies:

   ```bash
   mvn clean install
   ```

## Running the Tests

To execute the automation tests, run the following command:

```bash
mvn test
```

This command will compile the project and run the test cases defined in the `src/test` directory.

## Test Reports

After running the tests, reports will be generated in the `target/surefire-reports` directory. You can view these reports to get detailed insights into the test execution results.

## Contributing

Contributions are welcome! If you have suggestions or improvements, please fork the repository and submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
