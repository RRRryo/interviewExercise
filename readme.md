# Properties Parser

Exercise of emergentTech

## Prerequisites
JRE 1.8 is required for executing JAR

JDK 1.8 and Maven 3 are required for the source code

Using IDE is recommended for executing the source code

## Executing the code
### by JAR
Rename propertiesParser_jar to propertiesParser.jar (the extension .jar was renamed as _jar for attachment transfer)

Move the folder of test "configs" to the same path of propertiesParser.jar

Execute the following command in the command line (replace ${PATH} by the path you want to input):

java -cp propertiesParser.jar PropertiesParser ${PATH}

For example:

java -cp propertiesParser.jar PropertiesParser "/configs/dev/east/node1"

### by source code
Open the IDE and import the project

Import the dependencies by Maven

Build the project with JDK1.8 and language level >= 8

Run PropertiesParser.main() with program arguments: ${PATH}


## Running the tests
### by JUnit
Execute all JUnit tests in PropertiesParserTest.java
### by command line
case 1: null input

java -cp propertiesParser.jar PropertiesParser

case 2: empty input

java -cp propertiesParser.jar PropertiesParser ""

case 3: no exist path

java -cp propertiesParser.jar PropertiesParser "/configs/dev/south/node1"

case 4: correct path /configs/dev/east/node1

java -cp propertiesParser.jar PropertiesParser "/configs/dev/east/node1"

case 5: correct path /configs/dev/west

java -cp propertiesParser.jar PropertiesParser "/configs/dev/west"