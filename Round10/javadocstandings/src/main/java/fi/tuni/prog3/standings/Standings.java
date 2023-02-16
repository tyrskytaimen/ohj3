package fi.tuni.prog3.standings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * A class for maintaining team statistics and standings. Team standings are determined by the following rules:
 * <ul>
 *     <li>Primary rule: points total. Higher points come first.</li>
 *     <li>Secondary rule: goal difference (scored minus allowed). Higher difference comes first.</li>
 *     <li>Tertiary rule: number of goals scored. Higher number comes first.</li>
 *     <li>Last rule: natural String order of team names.</li>
 * </ul>
 */
public class Standings {
    /**
     * A class for storing statistics of a single team. The class offers only public getter functions. The enclosing class Standings is responsible for setting and updating team statistics.
     */
    public static class Team {
        private final String teamName;
        private int wins = 0;
        private int ties = 0;
        private int losses = 0;
        private int scored = 0;
        private int allowed = 0;
        private int points = 0;

        /**
         * Constructs a Team object for storing statistics of the named team.
         * @param name the name of the team whose statistics the new team object stores.
         */
        public Team(String name) {
            this.teamName = name;
        }

        /**
         * Returns the name of the team.
         * @return the name of the team.
         */
        public String getName() {
            return this.teamName;
        }

        /**
         * Returns the number of wins of the team.
         * @return the number of wins of the team.
         */
        public int getWins() {
            return this.wins;
        }

        /**
         * Returns the number of ties of the team.
         * @return the number of ties of the team.
         */
        public int getTies() {
            return this.ties;
        }

        /**
         * Returns the number of losses of the team.
         * @return the number of losses of the team.
         */
        public int getLosses() {
            return this.losses;
        }

        /**
         * Returns the number of goals scored by the team.
         * @return the number of goals scored by the team.
         */
        public int getScored() {
            return this.scored;
        }

        /**
         * Returns the number of goals allowed (conceded) by the team.
         * @return the number of goals allowed (conceded) by the team.
         */
        public int getAllowed() {
            return this.allowed;
        }

        /**
         * Returns the overall number of points of the team.
         * @return the overall number of points of the team.
         */
        public int getPoints() {
            return this.points;
        }
        private void addScored(int scored) {
            this.scored += scored;
        }
        private void addAllowed(int allowed) {
            this.allowed += allowed;
        }
        private void addPoints(int points) {
            this.points += points;
            if(points == 3) {
                this.wins++;
            }
            else if(points == 1) {
                this.ties++;
            }
            else {
                this.losses++;
            }
        }
        private int getDifference() {
            return scored-allowed;
        }
        private int getMatches() {
            return wins+losses+ties;
        }
    }//Teams ends

    private TreeMap<String, Team> teams = new TreeMap<>();

    /**
     * Constructs an empty Standings object.
     */
    public Standings() {

    }

    /**
     * Constructs a Standings object that is initialized with the game data read from the specified file. The result is identical to first constructing an empty Standing object and then calling {@link #readMatchData(String) readMatchData(filename)}.
     * @param filename the name of the game data file to read.
     * @throws IOException if there is some kind of an IO error (e.g. if the specified file does not exist).
     */
    public Standings(String filename) throws IOException {
        if(filename != null) {
            readMatchData(filename);
        }
    }

    /**
     * <p>Reads game data from the specified file and updates the team statistics and standings accordingly.</p>
     * <p>The match data file is expected to contain lines of form "teamNameA\tgoalsA-goalsB\tteamNameB". Note that the '\t' are tabulator characters.</p>
     * <p>E.g. the line "Iceland\t3-2\tFinland" would describe a match between Iceland and Finland where Iceland scored 3 and Finland 2 goals.</p>
     * @param filename the name of the game data file to read.
     * @throws IOException if there is some kind of an IO error (e.g. if the specified file does not exist).
     */
    public final void readMatchData(String filename) throws IOException{
        try(var file = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = file.readLine()) != null) {

                String[] parts = line.split("[\t]");
                String[] scores = parts[1].split("-");
                addMatchResult(parts[0], Integer.parseInt(scores[0]), Integer.parseInt(scores[1]), parts[2]);
            }
        }
    }

    /**
     * Updates the team statistics and standings according to the match result described by the parameters.
     * @param teamNameA the name of the first ("home") team.
     * @param goalsA the number of goals scored by the first team.
     * @param goalsB the number of goals scored by the second team.
     * @param teamNameB the name of the second ("away") team.
     */
    public void addMatchResult(String teamNameA, int goalsA, int goalsB, String teamNameB) {
        if(teams.get(teamNameA) == null) {
            Team team = new Team(teamNameA);
            teams.put(teamNameA,team);
        }
        if(teams.get(teamNameB) == null) {
            Team team = new Team(teamNameB);
            teams.put(teamNameB,team);
        }
        teams.get(teamNameA).addScored(goalsA);
        teams.get(teamNameA).addAllowed(goalsB);
        teams.get(teamNameB).addScored(goalsB);
        teams.get(teamNameB).addAllowed(goalsA);

        if(goalsA == goalsB) {
            teams.get(teamNameA).addPoints(1);
            teams.get(teamNameB).addPoints(1);
        }
        else if(goalsA > goalsB) {
            teams.get(teamNameA).addPoints(3);
            teams.get(teamNameB).addPoints(0);
        }
        else {
            teams.get(teamNameA).addPoints(0);
            teams.get(teamNameB).addPoints(3);
        }
    }

    /**
     * Returns a list of the teams in the same order as they would appear in a standings table.
     * @return a list of the teams in the same order as they would appear in a standings table.
     */
    public List<Team> getTeams() {
        ArrayList<Team> list = new ArrayList<>();
        for(String team : teams.keySet()) {
            list.add(teams.get(team));
        }
        list.sort((a, b) -> {
            if (a.getPoints() != b.getPoints()) {
                return b.getPoints() - a.getPoints();
            }
            if (a.getDifference() != b.getDifference()) {
                if (b.getDifference() > a.getDifference()) {
                    return 1;
                }
                return -1;
            }
            if (b.getScored() != a.getScored()) {
                return b.getScored() - a.getScored();
            }
            return b.getName().compareToIgnoreCase(a.getName());
        });
        return list;
    }

    /**
     * Prints a formatted standings table to the provided output stream.
     * @param out the output stream to use when printing the standings table.
     */
    public void printStandings(PrintStream out) {
        int width = 0;
        for(Team team : getTeams()) {
            if(team.teamName.length() > width) {
                width = team.teamName.length();
            }
        }
        for(Team team : getTeams()) {
            String s = team.scored + "-" + team.allowed;
            System.out.format("%-"+width+"s %3d %3d %3d %3d %6s %3d%n",
                    team.teamName, team.getMatches(), team.wins, team.ties, team.losses, s, team.points);
        }
    }
}
