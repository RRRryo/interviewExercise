# Properties Parser

Exercise of emergentTech

## Prerequisites
JRE 1.8 is required for executing JAR

JDK 1.8 and Maven 3 is required for source code

Using IDE is recommended for executing the source code

## Executing the code
### by JAR
Rename propertiesParser_jar to propertiesParser.jar (the extension .jar was renamed as _jar for attachment transfer)

Move the folder of test "configs" to the same path of propertiesParser.jar

Execute the following command line :

java -cp propertiesParser.jar PropertiesParser ${PATH}

For example:

java -cp propertiesParser.jar PropertiesParser "/configs/dev/east/node1"

### by source code
Open the IDE and import the project

Import the dependencies by Maven

Build the project with JDK1.8 and language level >= 8

Run PropertiesParser.main() with program arguments: ${PATH}


## Running the tests
Execute all JUnit tests in PropertiesParserTest.java
