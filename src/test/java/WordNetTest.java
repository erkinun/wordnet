import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class WordNetTest {

    private WordNet wordNet;

    @Before
    public void setUp() {
        wordNet = new WordNet("files/synsets.txt", "files/hypernyms.txt");
    }

    @Test(expected = NullPointerException.class)
    public void testConstructor() {
        wordNet = new WordNet(null, null);
    }

    @Test
    public void testNouns() {
        Iterable<String> nouns = wordNet.nouns();

        Assert.assertNotNull(nouns);
    }

    @Test
    public void testANoun() {
        String noun = "Abkhaz";

        Assert.assertTrue(wordNet.isNoun(noun));
    }

    @Test
    public void testANounNonExistent() {
        String noun = "hebele";

        Assert.assertFalse(wordNet.isNoun(noun));
    }

    @Test(expected = NullPointerException.class)
    public void nullArgTestNoun() {

        wordNet.isNoun(null);
    }
}