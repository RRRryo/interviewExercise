package main.java;

import org.apache.commons.lang.text.StrSubstitutor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by a542901 on 22/06/2018.
 */
public class KeyValueParser {

    private static final String FILE_NAME = "config.properties";
    private static final String PATH_PREFIX= "src/";
    private static final String TEMPLATE_REGEX_PATTERN = "\\$\\{(.*?)}";


    public Map<String,String> getPathListForAllLevel(String inputStr) {

        List<String> pathList = new ArrayList(Arrays.asList(inputStr.split("/")));
        Iterator<String> iterator = pathList.iterator();
        StringBuilder subPathSB = new StringBuilder().append(PATH_PREFIX);
        //use treeMap to sort the properties in alphabetical order on key-name
        Map<String,String> properties = new TreeMap<>();

        while (iterator.hasNext()){
            String str = iterator.next();
            if ("".equals(str)){
                iterator.remove();
            } else {
                subPathSB.append(str).append("/");
                if (Files.isReadable(Paths.get(subPathSB+FILE_NAME))) {
                    try {
                        List<String> strlist = Files.readAllLines(Paths.get(subPathSB+FILE_NAME));
                        for (String string : strlist) {
                            String[] keyValueArray = string.split("=");
                            if (keyValueArray.length > 1) {
                                //store the key as low case
                                properties.put(keyValueArray[0].toLowerCase(), keyValueArray[1]);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        System.out.println("properties before replacement of template:\n");
        for (Map.Entry<String,String> entry: properties.entrySet()) {
            System.out.println(entry.getKey() + "="+entry.getValue());

        }

        Pattern pattern = Pattern.compile(TEMPLATE_REGEX_PATTERN);

        //find and replace all the template ley & value
        StrSubstitutor sub = new StrSubstitutor(properties);

        for (Map.Entry<String,String> entry: properties.entrySet()) {
            Matcher matcher =  pattern.matcher(entry.getValue());
            while(matcher.find()) {
                entry.setValue(sub.replace(entry.getValue()));
            }

        }

        System.out.println("===========================================");
        System.out.println("properties after replacement of template:\n");
        for (Map.Entry<String,String> entry : properties.entrySet()) {
            System.out.println(entry.getKey() + "="+entry.getValue());
        }

        return properties;
    }


    public static void main(String[] args) {
        //INPUT
        String inputStr = "/configs/dev/east/node1";
        KeyValueParser keyValueParser = new KeyValueParser();
        Map<String, String> properties = keyValueParser.getPathListForAllLevel(inputStr);

    }
}
