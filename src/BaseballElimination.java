import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BaseballElimination {

    private final int num;
    private final HashMap<String, Integer> teams;
    private final String[] names;
    private final int[] wins;
    private final int[] losses;
    private final int[] remains;
    private final int[][] games;
    private final HashMap<String, Set<String>> eliminate;


    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        //get the information from the file
        In in = new In(filename);

        num = in.readInt();

        teams = new HashMap<>();
        names = new String[num];
        wins = new int[num];
        losses = new int[num];
        remains = new int[num];
        games = new int[num][num];
        eliminate = new HashMap<>();

        for (int i = 0; i < num; i++) {
            String name = in.readString();
            teams.put(name, i);
            names[i] = name;
            wins[i] = in.readInt();
            losses[i] = in.readInt();
            remains[i] = in.readInt();
            for (int j = 0; j < num; j++) {
                games[i][j] = in.readInt();
            }
        }
        testTrivialElimination();
    }

    // number of teams
    public int numberOfTeams() {
        return num;
    }

    // all teams
    public Iterable<String> teams() {
        return teams.keySet();
    }

    // number of wins for given team
    public int wins(String team) {
        if (!teams.containsKey(team)) throw new IllegalArgumentException();
        return wins[teams.get(team)];
    }

    // number of losses for given team
    public int losses(String team) {
        if (!teams.containsKey(team)) throw new IllegalArgumentException();
        return losses[teams.get(team)];
    }

    // number of remaining games for given team
    public int remaining(String team) {
        if (!teams.containsKey(team)) throw new IllegalArgumentException();
        return remains[teams.get(team)];
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        if ((!teams.containsKey(team1)) || (!teams.containsKey(team2))) throw new IllegalArgumentException();
        int i = teams.get(team1);
        int j = teams.get(team2);
        return games[i][j];
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        if (!teams.containsKey(team)) throw new IllegalArgumentException();
        int order = teams.get(team);

        //平凡淘汰
        if (eliminate.containsKey(team)) return true;

        //非平凡淘汰
        int V = 2 + num * num + num;
        int s = num + num * num;
        int t = num + num * num + 1;

        FlowNetwork flowNetwork = new FlowNetwork(V);
        int sum = 0;
        for (int i = 0; i < num; i++) {
            if (i == order || eliminate.containsKey(i)) continue;
            for (int j = i + 1; j < num; j++) {
                if (j == order || eliminate.containsKey(j) || games[i][j] <= 0) continue;

                int game = num + num * i + j;

                //add edges from s to game
                flowNetwork.addEdge(new FlowEdge(s, game, games[i][j]));
                sum += games[i][j];

                //add edges from game to team
                flowNetwork.addEdge(new FlowEdge(game, i, Integer.MAX_VALUE));
                flowNetwork.addEdge(new FlowEdge(game, j, Integer.MAX_VALUE));
            }

            //add edges from team to t
            if (wins[order] + remains[order] - wins[i] > 0)
                flowNetwork.addEdge(new FlowEdge(i, t, wins[order] + remains[order] - wins[i]));
        }

        FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, s, t);

        //假如maxFlow不为剩余game总和，意味着有一些队伍没有进行完比赛就已经成功淘汰该队伍
        if (fordFulkerson.value() < sum) {
            Set<String> set = new HashSet<>();
            for (int i = 0; i < num; i++) {
                if (fordFulkerson.inCut(i)) {
                    set.add(names[i]);
                }
            }
            eliminate.put(team, set);

            return true;
        }
        return false;
    }

    private void testTrivialElimination() {
        HashSet<String> set;
        for (int i = 0; i < num; i++) {
            set = new HashSet<>();
            int max = wins[i] + remains[i];
            for (int j = 0; j < num; j++) {
                if (i == j) continue;
                if (wins[j] > max) {
                    set.add(names[j]);
                }
            }
            if (!set.isEmpty()) {
//                System.out.println(names[i]+"is trivial");
                eliminate.put(names[i], set);
            }
        }
    }


    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        if (isEliminated(team)) return eliminate.get(team);
        return null;
    }

    public static void main(String[] args) {

    }

}
