# Bank Reconciliation Application

This application includes the initial setup and implementation of the bank reconciliation application. The following components are included:

## Project Structure

### Source Code
Located in the `src/main/java/com/tools/bank` directory.

- **App**: Main application class.
- **BankStatement**: Represents a bank statement.
- **TallyStatement**: Represents a tally statement.
- **MatchedStatement**: Represents a matched statement.
- **AppUtil**: Utility class with methods for processing statements.

### Test Code
Located in the `src/test/java/com/tools/bank` directory.

- **AppTest**: Unit tests for the main application class.

### Configuration Files

- **Maven Configuration**: `pom.xml` for managing project dependencies and build configuration.
- **Git Ignore**: `.gitignore` file to exclude unnecessary files and directories from version control.

### Additional Files

- **README**: `README.md` with a brief description of the project.
- **Log Files**: `application.log` and `matched_statements.log` for logging application activities.

## Directory Structure

```
.gitignore
application.log
files/
    833/
        ~$statement 833 bu tally 23-24.xlsx
        0776XXXXXXX083321-09-2024 by bank.xls
        statement-833-by-tally-23-24.xlsx
matched_statements.log
pom.xml
README.md
src/
    main/
        java/
            com/
                tools/
                    bank/
    test/
        java/
            com/
                tools/
                    bank/
target/
    classes/
        com/
            tools/
                bank/
                    App.class
                    BankStatement.class
                    MatchedStatement.class
                    TallyStatement.class
                    util/
    test-classes/
        com/
            tools/
                bank/
                    AppTest.class
```