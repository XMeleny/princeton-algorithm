import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.DepthFirstOrder;

import java.util.ArrayList;
import java.util.HashMap;


public class WordNet {
    private final ST<Integer, String[]> symbolTable;
    private final Digraph digraph;
    private final HashMap<String, ArrayList<Integer>> nouns;
    private final Iterable<Integer> path;

    // constructor takes the name of the two input file
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) throw new IllegalArgumentException();

        //init
        symbolTable = new ST<>();
        nouns = new HashMap<>();

        //synsets
        In inSyn = new In(synsets);
        while (inSyn.hasNextLine()) {
            String[] strings = inSyn.readLine().split(",");  //0: id, 1: syn, 2: gloss
            String[] syn = strings[1].split(" ");            //同义词们

            int id = Integer.parseInt(strings[0]);

            symbolTable.put(id, syn);

            for (int i = 0; i < syn.length; i++) {
                if (nouns.containsKey(syn[i])) {
                    nouns.get(syn[i]).add(id);
                } else {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(id);
                    nouns.put(syn[i], arrayList);
                }
            }
        }

        //hypernyms
        digraph = new Digraph(symbolTable.size());
        boolean[] isNotRoot = new boolean[symbolTable.size()];
        int numOfRoot = 0;

        In inHyper = new In(hypernyms);
        while (inHyper.hasNextLine()) {
            String[] strings = inHyper.readLine().split(",");

            int start = Integer.parseInt(strings[0]);
            isNotRoot[start] = true;
            for (int i = 1; i < strings.length; i++) {
                int end = Integer.parseInt(strings[i]);
                digraph.addEdge(start, end);
            }
        }

        for (int i = 0; i < symbolTable.size(); i++) {
            if (isNotRoot[i] == false) numOfRoot++;
        }

        DirectedCycle directedCycle = new DirectedCycle(digraph);
        if (numOfRoot > 1 || directedCycle.hasCycle()) throw new IllegalArgumentException();

        DepthFirstOrder dfo = new DepthFirstOrder(digraph);
        path = dfo.reversePost();
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nouns.keySet();
    }

    // is the word a WordNet noun?
    // is it using search?
    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException();
        return nouns.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new IllegalArgumentException();

        ArrayList<Integer> idOfA = nouns.get(nounA);
        ArrayList<Integer> idOfB = nouns.get(nounB);

        int result = Integer.MAX_VALUE;
        BreadthFirstDirectedPaths a = new BreadthFirstDirectedPaths(digraph, idOfA);
        BreadthFirstDirectedPaths b = new BreadthFirstDirectedPaths(digraph, idOfB);

        for (int item : path) {
            if (a.hasPathTo(item) && b.hasPathTo(item)) {
                int dis = a.distTo(item) + b.distTo(item);
                result = Math.min(result, dis);
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new IllegalArgumentException();

        ArrayList<Integer> idOfA = nouns.get(nounA);
        ArrayList<Integer> idOfB = nouns.get(nounB);

        int minDis = Integer.MAX_VALUE;
        String result = null;
        BreadthFirstDirectedPaths a = new BreadthFirstDirectedPaths(digraph, idOfA);
        BreadthFirstDirectedPaths b = new BreadthFirstDirectedPaths(digraph, idOfB);

        for (int item : path) {
            if (a.hasPathTo(item) && b.hasPathTo(item)) {
                int dis = a.distTo(item) + b.distTo(item);
                if (dis < minDis) {
                    minDis = dis;
                    result = String.join(" ", symbolTable.get(item));
                }
            }
        }

        return result;
    }

    // do unit testing of this class
    public static void main(String[] args) {
//        WordNet wordNet=new WordNet("synsets.txt","hypernyms.txt");
//        System.out.println(wordNet.distance("quellung","Cyrtomium_aculeatum"));//12
//        System.out.println(wordNet.sap("quellung","Cyrtomium_aculeatum"));
    }
}
