import java.util.HashMap;
import java.util.Map;

/**
 * Created by unlue on 12/11/14.
 */
public class Outcast {

    private final WordNet wordNet;

    public Outcast(WordNet wordnet) {
        // constructor takes a WordNet object
        this.wordNet = wordnet;
    }
    public String outcast(String[] nouns) {
        // given an array of WordNet nouns, return an outcast

        Map<String, Integer> nounDistance = new HashMap<String, Integer>();

        for (String noun : nouns) {
            int distance = 0;
            for (String otherNoun : nouns) {
                distance += wordNet.distance(noun, otherNoun);
            }

            nounDistance.put(noun, distance);
        }

        int max = Integer.MIN_VALUE;
        String outcast = null;
        for (Map.Entry<String, Integer> entry: nounDistance.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                outcast = entry.getKey();
            }
        }

        return outcast;
    }
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
