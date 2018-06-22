# Properties Parser

Exercise of emergentTech

## Prerequisites
JDK 1.8

## Executing the code
### by JAR
rename propertiesParser_jar to propertiesParser.jar
Move the folder of test "configs" to the same path of propertiesParser.jar
Execute the following command line :

java -cp propertiesParser.jar KeyValueParser "${PATH}"

for example:
java -cp propertiesParser.jar KeyValueParser "/configs/dev/east/node1"

### by source code
Open the IDE and import the project
run the KeyValueParser.main with program arguments: "/configs/dev/east/node1"


## Running the tests
Execute all the JUnit tests in KeyValueParserTest.java
