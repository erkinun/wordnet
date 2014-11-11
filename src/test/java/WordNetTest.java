import org.junit.Test;

public class WordNetTest {

    @Test(expected = NullPointerException.class)
    public void testConstructor() {
        WordNet wordNet = new WordNet(null, null);
    }
}