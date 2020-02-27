import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.HashMap;

public class BaseballElimination {
    private FlowNetwork flowNetwork;
    private int num;
    private HashMap<String, Integer> teams;
    private int[] wins;
    private int[] losses;
    private int[] remains;
    private int[][] games;

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
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


        //test
        System.out.println(teams.entrySet());
        for (int i = 0; i < num; i++) {
            System.out.print(wins[i]);
            System.out.print("  ");
            System.out.print(losses[i]);
            System.out.print("  ");
            System.out.print(remains[i]);
            System.out.println();
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
        return false;

    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        return null;

    }

    public static void main(String[] args) {
        BaseballElimination baseballElimination = new BaseballElimination("teams4.txt");
    }
}
