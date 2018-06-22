import junit.framework.TestCase;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by a542901 on 22/06/2018.
 */
public class KeyValueParserTest extends TestCase {

    private KeyValueParser keyValueParser = new KeyValueParser();

    public void testNormalInput() throws Exception {
        String input = "/configs/dev/east/node1";
        Map<String, String> result = keyValueParser.getPathListForAllLevel(input);

        Map<String, String> expectedResult = new TreeMap<>();
        expectedResult.put("email.host.address","smpt.east.google.com");
        expectedResult.put("email.host.port","25");
        expectedResult.put("email.host.protocol","https");
        expectedResult.put("email.url","https://smpt.east.google.com:25/node1");
        expectedResult.put("host.name","node1");

        //ust hamcrest to test the element key/value and the order of treeMap
        Assert.assertThat(result, Matchers.equalTo(expectedResult));

    }

    public void testNullInput() throws Exception {
        Map<String, String> result = keyValueParser.getPathListForAllLevel(null);
        assert result == null;
    }

    public void testEmptyInput() throws Exception {
        Map<String, String> result = keyValueParser.getPathListForAllLevel("");
        assert result == null;
    }

    public void testNoExistPathInput() throws Exception {
        String input = "/configs/dev/south/node1";
        Map<String, String> result = keyValueParser.getPathListForAllLevel(input);
        assert result == null;
    }





}