import org.apache.commons.lang.text.StrSubstitutor;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Liang on 22/06/2018.
 */
public class PropertiesParser {

    private static final String FILE_NAME = "config.properties";
    private static final String PATH_PREFIX= System.getProperty("user.dir");
    private static final String TEMPLATE_REGEX_PATTERN = "\\$\\{(.*?)}";

    private final static Logger logger = Logger.getLogger(PropertiesParser.class);


    public static void main(String[] args) {
        if (args.length == 0) {
            logger.fatal("please input a path, for example: /configs/dev/east/node1");
            return;
        }

        String inputStr = args[0];

        PropertiesParser propertiesParser = new PropertiesParser();

        propertiesParser.process(inputStr);

    }

    public Map<String,String> process(String inputStr) {

        inputStr = processInputString(inputStr);
        if (inputStr == null)
            return null;

        //store the properties into treeMap, order by key-name
        Map<String,String> properties = new TreeMap<>();

        completePropertiesForPath(inputStr, properties);

        treatTemplate(properties);

        display(properties);

        return properties;
    }


    private String processInputString(String inputStr) {

        if (inputStr == null || inputStr.isEmpty()) {
            logger.error("input path should not be empty");
            return null;
        } else if (!inputStr.startsWith("/")) {
            inputStr = "/" + inputStr;
        }

        if (!Files.exists(Paths.get(PATH_PREFIX+inputStr))) {
            logger.error("this path does not exist!");
            return null;
        }

        return  inputStr;
    }


    /**
     * pass the properties keys to low case
     * leaf values override values in files closer to the trunk of the folder structure
     */
    private Map<String,String> completePropertiesForPath(String inputStr, Map<String, String> properties){

        List<String> pathList = new ArrayList<>(Arrays.asList(inputStr.split("/")));
        Iterator<String> iterator = pathList.iterator();
        StringBuilder subPathSB = new StringBuilder().append(PATH_PREFIX).append("/");

        while (iterator.hasNext()) {
            String str = iterator.next();
            if (str.isEmpty()) {
                iterator.remove();
            } else {
                subPathSB.append(str).append("/");
                if (Files.isReadable(Paths.get(subPathSB + FILE_NAME))) {
                    try {
                        List<String> strList = Files.readAllLines(Paths.get(subPathSB + FILE_NAME));
                        for (String string : strList) {
                            String[] keyValueArray = string.split("=");
                            if (keyValueArray.length > 1) {
                                //pass the key as low case
                                properties.put(keyValueArray[0].toLowerCase(), keyValueArray[1]);
                            }
                        }
                    } catch (IOException e) {
                        logger.error(e);
                    }
                }

            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("============properties before replacement of template:==============\n");
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                logger.debug(entry.getKey() + "=" + entry.getValue());

            }
        }

        return properties;
    }

    /**
     * find and try to replace templates
     */
    private void treatTemplate(Map<String, String> properties) {
        Pattern pattern = Pattern.compile(TEMPLATE_REGEX_PATTERN);

        StrSubstitutor sub = new StrSubstitutor(properties);

        for (Map.Entry<String, String> entry : properties.entrySet()) {
            Matcher matcher = pattern.matcher(entry.getValue());
            if (matcher.find()) {
                entry.setValue(sub.replace(entry.getValue()));
            }
        }

    }

    private void display(Map<String, String> properties) {
        logger.fatal("\n=================properties==========================");
        if (properties.size() > 0 ) {
            for (Map.Entry<String,String> entry : properties.entrySet()) {
                logger.fatal(entry.getKey() + "=" + entry.getValue());
            }
        } else
            logger.fatal("no property is found for this path.");
    }


}
