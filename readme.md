# Properties Parser

Exercise of emergentTech

## Prerequisites
JDK 1.8
Maven 3

## Executing the code
### by JAR
Rename propertiesParser_jar to propertiesParser.jar
Move the folder of test "configs" to the same path of propertiesParser.jar
Execute the following command line :
java -cp propertiesParser.jar KeyValueParser ${PATH}

For example:
java -cp propertiesParser.jar KeyValueParser "/configs/dev/east/node1"

### by source code
Open the IDE and import the project
Import the dependencies by Maven
Build the project
Run KeyValueParser.main() with program arguments: ${PATH}


## Running the tests
Execute all the JUnit tests in KeyValueParserTest.java
