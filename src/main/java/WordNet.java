import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by ERKIN on 09/11/14.
 */
public class WordNet {

    private Map<Integer, Synset> synsetMap;
    private Map<String, Set<Integer>> nouns = new HashMap<String, Set<Integer>>();
    private Digraph hypernyms;

    // constructor takes the name of the two input files
    public WordNet(String synsetFileName, String hypernymFile) {

        synsetMap = handleSynsets(synsetFileName
                ,
                nouns);

        hypernyms = handleHypernyms(hypernymFile, nouns.keySet().size());
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nouns.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {

        if (word == null) {
            throw new NullPointerException("arg is null");
        }

        return nouns.keySet().contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {

        if (!isNoun(nounA)) {
            throw new IllegalArgumentException("not existing noun: " + nounA);
        }

        if (!isNoun(nounB)) {
            throw new IllegalArgumentException("not existing noun: " + nounB);
        }

        SAP sap = new SAP(hypernyms);

        return sap.length(nouns.get(nounA), nouns.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {

        if (!isNoun(nounA)) {
            throw new IllegalArgumentException("not existing noun: " + nounA);
        }

        if (!isNoun(nounB)) {
            throw new IllegalArgumentException("not existing noun: " + nounB);
        }

        SAP sap = new SAP(hypernyms);

        int index = sap.ancestor(nouns.get(nounA), nouns.get(nounB));

        return synsetMap.get(index).getSet();
    }

    // do unit testing of this class
    public static void main(String[] args) {
        //WordNet wordNet = new WordNet("files/synsets15.txt", "files/hypernyms15Tree.txt");
    }

    private Map<Integer, Synset> handleSynsets(String synsetFileName,
                                               Map<String, Set<Integer>> nounSet) {

        In in = new In(synsetFileName);

        Map<Integer, Synset> synsets = new HashMap<Integer, Synset>();

        while (in.hasNextLine()) {
            String line = in.readLine();

            String[] synsetInfo = line.split(",");

            int synsetId = Integer.parseInt(synsetInfo[0]);
            synsets.put(synsetId, new Synset(synsetInfo[1]));

            String[] synonyms = synsetInfo[1].split(" ");
            for (String synonym : synonyms) {
                Set<Integer> ids = nounSet.get(synonym);

                if (ids == null) {
                    ids = new HashSet<Integer>();
                }

                ids.add(synsetId);

                nounSet.put(synonym, ids);
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
        private String set;

        private Synset(String set) {
            this.set = set;
        }

        public String getSet() {
            return set;
        }
    }
}
