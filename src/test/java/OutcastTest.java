import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class OutcastTest {

    private WordNet wordNet;

    @Before
    public void setUp() {
        wordNet = new WordNet("files/synsets.txt", "files/hypernyms.txt");
    }

    @Test
    public void testOutcast5() throws Exception {

        Outcast outcast = new Outcast(wordNet);

        In in = new In("files/outcast5.txt");
        String[] nouns = in.readAllStrings();

        Assert.assertEquals(5, nouns.length);

        Assert.assertEquals("table", outcast.outcast(nouns));
    }

    @Test
    public void testOutcast8() throws Exception {

        Outcast outcast = new Outcast(wordNet);

        In in = new In("files/outcast8.txt");
        String[] nouns = in.readAllStrings();

        Assert.assertEquals("bed", outcast.outcast(nouns));
    }

    @Test
    public void testOutcast11() throws Exception {

        Outcast outcast = new Outcast(wordNet);

        In in = new In("files/outcast11.txt");
        String[] nouns = in.readAllStrings();

        Assert.assertEquals("potato", outcast.outcast(nouns));
    }

}