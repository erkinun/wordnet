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

        BreadthFirstDirectedPaths bfdpV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfdpW = new BreadthFirstDirectedPaths(digraph, w);

        List<Integer> ancestors = new ArrayList<Integer>();
        for (int i = 0; i < digraph.V(); i++) {
            if (bfdpV.hasPathTo(i) && bfdpW.hasPathTo(w)) {
                ancestors.add(i);
            }
        }

        int min = Integer.MAX_VALUE;

        for (int ancestor : ancestors) {
            int distance = bfdpV.distTo(ancestor) + bfdpW.distTo(ancestor);

            if (distance < min) {
                min = distance;
            }
        }

        return min;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {

        BreadthFirstDirectedPaths bfdpV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfdpW = new BreadthFirstDirectedPaths(digraph, w);

        List<Integer> ancestors = new ArrayList<Integer>();
        for (int i = 0; i < digraph.V(); i++) {
            if (bfdpV.hasPathTo(i) && bfdpW.hasPathTo(w)) {
                ancestors.add(i);
            }
        }

        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int ancestor : ancestors) {
            int distance = bfdpV.distTo(ancestor) + bfdpW.distTo(ancestor);

            if (distance < min) {
                min = distance;
                minIndex = ancestor;
            }
        }

        return minIndex;
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
}
