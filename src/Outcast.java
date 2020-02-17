public class Outcast {
    private final WordNet wordNet;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        if (wordnet == null) throw new IllegalArgumentException();
        this.wordNet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {

        int maxDis = 0;
        String result = null;

        for (int i = 0; i < nouns.length; i++) {
            int dis = 0;
            for (int j = 0; j < nouns.length; j++) {
                dis += wordNet.distance(nouns[i], nouns[j]);
            }
            if (dis > maxDis) {
                maxDis = dis;
                result = nouns[i];
            }

        }
        return result;
    }

    // see test client below
    public static void main(String[] args) {

    }
}
