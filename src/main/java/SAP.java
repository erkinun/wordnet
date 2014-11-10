import java.util.ArrayList;
import java.util.List;

/**
 * Created by ERKIN on 09/11/14.
 */
public class SAP {

    private Digraph digraph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        digraph = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {

        List<Ancestor> ancestorV = findAncestors(v);
        List<Ancestor> ancestorW = findAncestors(w);

        int min = -1;
        for (Ancestor ancestor : ancestorV) {
            for (Ancestor other: ancestorW) {
                if (ancestor.getIndex() == other.getIndex()) {
                    int distance = ancestor.getDistance() + other.getDistance();

                    if (min == -1 || distance < min) {
                        min = distance;
                    }
                }
            }
        }

        return min;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        throw new IllegalStateException("not implemented");
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        throw new IllegalStateException("not implemented");
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        throw new IllegalStateException("not implemented");
    }

    // do unit testing of this class
    public static void main(String[] args) {
        throw new IllegalStateException("not implemented");
    }

    private List<Ancestor> findAncestors(int vertex) {
        List<Ancestor> ancestors = new ArrayList<Ancestor>();
        Ancestor root = new Ancestor(vertex, 0);
        ancestors.add(root); // it is the closest ancestor to itself

        Queue<Ancestor> bfsQueue = new Queue<Ancestor>();
        bfsQueue.enqueue(root);

        while (!bfsQueue.isEmpty()) {
            Ancestor vtx = bfsQueue.dequeue();
            for (int index : digraph.adj(vtx.getIndex())) {
                Ancestor ancestor = new Ancestor(index, vtx.getDistance() + 1);
                bfsQueue.enqueue(ancestor);
                ancestors.add(ancestor);
            }
        }

        return ancestors;
    }

    private static class Ancestor {
        private final int index;
        private final int distance;

        private Ancestor(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        public int getIndex() {
            return index;
        }

        public int getDistance() {
            return distance;
        }
    }
}
