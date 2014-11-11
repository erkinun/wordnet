import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by ERKIN on 09/11/14.
 */
public class WordNet {

    private Map<Integer, Synset> synsetMap;
    private Set<String> nouns = new HashSet<String>();
    private Digraph hypernyms;

    // constructor takes the name of the two input files
    public WordNet(String synsetFileName, String hypernymFile) {

        synsetMap = handleSynsets(synsetFileName, nouns);

        hypernyms = handleHypernyms(hypernymFile, nouns.size());
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nouns;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        throw new IllegalStateException("Not Implemented");
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        throw new IllegalStateException("Not Implemented");
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        throw new IllegalStateException("Not Implemented");
    }

    // do unit testing of this class
    public static void main(String[] args) {
        //WordNet wordNet = new WordNet("files/synsets15.txt", "files/hypernyms15Tree.txt");
    }

    private Map<Integer, Synset> handleSynsets(String synsetFileName, Set<String> nounSet) {

        In in = new In(synsetFileName);

        Map<Integer, Synset> synsets = new HashMap<Integer, Synset>();

        while (in.hasNextLine()) {
            String line = in.readLine();

            String[] synsetInfo = line.split(",");

            int synsetId = Integer.parseInt(synsetInfo[0]);
            synsets.put(synsetId, new Synset(synsetId, synsetInfo[1]));

            String[] synonyms = synsetInfo[1].split(" ");
            for (String synonym : synonyms) {
                nounSet.add(synonym);
            }
        }

        return synsets;
    }

    private Digraph handleHypernyms(String hypernymFile, int graphSize) {

        In in = new In(hypernymFile);

        Digraph localDigraph = new Digraph(graphSize);

        while (in.hasNextLine()) {
            String line = in.readLine();

            String[] info = line.split(",");

            int synsetid = Integer.parseInt(info[0]);

            for (int i = 1; i < info.length; i++) {
                int hypernymId = Integer.parseInt(info[i]);
                localDigraph.addEdge(synsetid, hypernymId);
            }
        }

        return localDigraph;
    }

    private static class Synset {
        private int id;
        private String set;

        private Synset(int id, String set) {
            this.id = id;
            this.set = set;
        }
    }
}
