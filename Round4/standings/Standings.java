import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class Standings {
    public static class Team {
        private final String teamName;
        private int wins = 0;
        private int ties = 0;
        private int losses = 0;
        private int scored = 0;
        private int allowed = 0;
        private int points = 0;

        public Team(String name) {
            this.teamName = name;
        }
        public String getName() {
            return this.teamName;
        }
        public int getWins() {
            return this.wins;
        }
        public int getTies() {
            return this.ties;
        }
        public int getLosses() {
            return this.losses;
        }
        public int getScored() {
            return this.scored;
        }
        public int getAllowed() {
            return this.allowed;
        }
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

    public Standings(String filename) throws IOException {
        if(filename != null) {
            readMatchData(filename);
        }
    }

    public Standings() throws IOException {
        this(null);
    }

    public void readMatchData(String filename) throws IOException{
        try(var file = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = file.readLine()) != null) {

                String[] parts = line.split("[\t]");
                String[] scores = parts[1].split("-");
                addMatchResult(parts[0], Integer.parseInt(scores[0]), Integer.parseInt(scores[1]), parts[2]);
            }
        }
    }

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

    public ArrayList<Team> getTeams() {
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

    public void printStandings() {
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
