import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;

public class BaseballElimination {

    private final int num;
    private final HashMap<String, Integer> teams;
    private final int[] wins;
    private final int[] losses;
    private final int[] remains;
    private final int[][] games;

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        //get the information from the file
        In in = new In(filename);

        num = in.readInt();

        teams = new HashMap<>();
        wins = new int[num];
        losses = new int[num];
        remains = new int[num];
        games = new int[num][num];

        for (int i = 0; i < num; i++) {
            teams.put(in.readString(), i);
            wins[i] = in.readInt();
            losses[i] = in.readInt();
            remains[i] = in.readInt();
            for (int j = 0; j < num; j++) {
                games[i][j] = in.readInt();
            }
        }
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

        int V = 2 + num * num + num;
        int s = num + num * num;
        int t = num + num * num + 1;

        FlowNetwork flowNetwork = new FlowNetwork(V);

        for (int i = 0; i < num; i++) {
            if (i == order) continue;
            for (int j = i + 1; j < num; j++) {
                if (j == order) continue;
                int game = num + num * i + j;
                //from s to game
                flowNetwork.addEdge(new FlowEdge(s, game, games[i][j]));
                //from game to team
                flowNetwork.addEdge(new FlowEdge(game, i, Integer.MAX_VALUE));
                flowNetwork.addEdge(new FlowEdge(game, j, Integer.MAX_VALUE));
            }
        }

        //add edges from team to t
        for (int i = 0; i < num; i++) {
            if (i == order) continue;
            flowNetwork.addEdge(new FlowEdge(i, t, wins[order] + remains[order] - wins[i]));
        }

        FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, s, t);

        //if every game in the min cut, then the team is eliminated
        for (int i = 0; i < num; i++) {
            if (i == order) continue;
            if (!fordFulkerson.inCut(i)) return true;
        }
        return false;
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        return null;

    }

}
