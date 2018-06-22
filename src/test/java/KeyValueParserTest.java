import junit.framework.TestCase;
import org.hamcrest.Matchers;
import org.junit.Assert;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by a542901 on 22/06/2018.
 */
public class KeyValueParserTest extends TestCase {

    private KeyValueParser keyValueParser = new KeyValueParser();


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

    public void testRootInput() throws Exception {
        String input = "/";
        Map<String, String> result = keyValueParser.getPathListForAllLevel(input);

        Map<String, String> expectedResult = new TreeMap<>();

        Assert.assertThat(result, Matchers.equalTo(expectedResult));

    }

    public void testNormalInputNode1() throws Exception {
        String input = "/configs/dev/east/node1";
        Map<String, String> result = keyValueParser.getPathListForAllLevel(input);

        Map<String, String> expectedResult = new TreeMap<>();
        expectedResult.put("email.host.address","smpt.east.google.com");
        expectedResult.put("email.host.port","25");
        expectedResult.put("email.host.protocol","https");
        expectedResult.put("email.url","https://smpt.east.google.com:25/node1");
        expectedResult.put("host.name","node1");

        Assert.assertThat(result, Matchers.equalTo(expectedResult));

    }

    public void testNormalInputNode2() throws Exception {
        String input = "/configs/dev/east/node2";
        Map<String, String> result = keyValueParser.getPathListForAllLevel(input);

        Map<String, String> expectedResult = new TreeMap<>();
        expectedResult.put("email.host.address","smpt.east.google.com");
        expectedResult.put("email.host.port","25");
        expectedResult.put("email.host.protocol","http");
        expectedResult.put("email.url","http://smpt.east.google.com:25/node2");
        expectedResult.put("host.name","node2");

        Assert.assertThat(result, Matchers.equalTo(expectedResult));

    }

    public void testNormalInputEndwithSlash() throws Exception {
        String input = "/configs/dev/east/";
        Map<String, String> result = keyValueParser.getPathListForAllLevel(input);

        Map<String, String> expectedResult = new TreeMap<>();
        expectedResult.put("email.host.address","smpt.east.google.com");
        expectedResult.put("email.host.port","25");
        expectedResult.put("email.host.protocol","http");
        expectedResult.put("email.url","http://smpt.east.google.com:25/${host.name}");

        Assert.assertThat(result, Matchers.equalTo(expectedResult));

    }

    public void testNormalInputEndWithoutSlash() throws Exception {
        String input = "/configs/dev/east";
        Map<String, String> result = keyValueParser.getPathListForAllLevel(input);

        Map<String, String> expectedResult = new TreeMap<>();
        expectedResult.put("email.host.address","smpt.east.google.com");
        expectedResult.put("email.host.port","25");
        expectedResult.put("email.host.protocol","http");
        expectedResult.put("email.url","http://smpt.east.google.com:25/${host.name}");

        Assert.assertThat(result, Matchers.equalTo(expectedResult));

    }


    public void testConfigsInput() throws Exception {
        String input = "/configs";
        Map<String, String> result = keyValueParser.getPathListForAllLevel(input);

        Map<String, String> expectedResult = new TreeMap<>();
        expectedResult.put("email.host.port","25");
        expectedResult.put("email.host.address","smpt.google.com");
        expectedResult.put("email.host.protocol","http");
        expectedResult.put("email.url","http://smpt.google.com:25/${host.name}");

        Assert.assertThat(result, Matchers.equalTo(expectedResult));

    }
}