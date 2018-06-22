package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by a542901 on 22/06/2018.
 */
public class KeyValueParser {

    private static final String FILE_NAME = "config.properties";

    public static void main(String[] args) {
        String path = "/configs/dev/east/node1";
        List<String> pathList = new ArrayList(Arrays.asList(path.split("/")));
        Iterator<String> it = pathList.iterator();
        StringBuilder sb = new StringBuilder();

        while (it.hasNext()){
            String str = it.next();
            if (str.equals("")){
                it.remove();
            } else {
                sb.append(str).append("/");
                System.out.println(sb);
            }
        }



        try {
            List<String> strlist = Files.readAllLines(Paths.get("src/configs/config.properties"));
            strlist.forEach(s -> System.out.println(s));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
