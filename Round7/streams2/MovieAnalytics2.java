import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieAnalytics2 {
    private ArrayList<Movie> movies;

    public MovieAnalytics2() {
        this.movies = new ArrayList<>();
    }

    public void populateWithData(String fileName) throws IOException {
        try(var br = new BufferedReader(new FileReader(fileName))) {
            ArrayList<Movie> newMovies = br.lines()
                    .map(line -> line.split(";"))
                    .map(parts -> new Movie(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
                        parts[3], Double.parseDouble(parts[4]), parts[5]))
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            movies.addAll(newMovies);
        }
    }

    public void printCountByDirector(int n) {
        Map<String, Long> guys = movies.stream()
                .collect(Collectors.groupingBy(Movie::getDirector,Collectors.counting()));
        ArrayList<String> sortedGuys = new ArrayList<>(guys.keySet());
        sortedGuys.sort((a,b) -> {
            if(guys.get(a) < guys.get(b)) {
                return 1;
            }
            else if(guys.get(a) > guys.get(b)) {
                return -1;
            }
            else {
                return a.compareToIgnoreCase(b);
            }
        });
        sortedGuys.subList(n, sortedGuys.size()).clear();
        sortedGuys.forEach(name -> System.out.format("%s: %d movies%n",name,guys.get(name)));
    }

    public void printAverageDurationByGenre() {
        Map<String, Double> guys = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre,Collectors.averagingDouble(Movie::getDuration)));
        ArrayList<String> sortedGuys = new ArrayList<>(guys.keySet());
        sortedGuys.sort((a,b) -> {
            if(guys.get(a) > guys.get(b)) {
                return 1;
            }
            else if(guys.get(a) < guys.get(b)) {
                return -1;
            }
            else {
                return a.compareToIgnoreCase(b);
            }
        });
        sortedGuys.forEach(genre -> System.out.format("%s: %.2f%n",genre, guys.get(genre)));
    }

    public void printAverageScoreByGenre() {
        Map<String, Double> guys = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre,Collectors.averagingDouble(Movie::getScore)));
        ArrayList<String> sortedGuys = new ArrayList<>(guys.keySet());
        sortedGuys.sort((a,b) -> {
            if(guys.get(a) < guys.get(b)) {
                return 1;
            }
            else if(guys.get(a) > guys.get(b)) {
                return -1;
            }
            else {
                return a.compareToIgnoreCase(b);
            }
        });
        sortedGuys.forEach(genre -> System.out.format("%s: %.2f%n",genre, guys.get(genre)));
    }
}
