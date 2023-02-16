import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MovieAnalytics {
    private ArrayList<Movie> movies;
    private final Comparator<Movie> comp = (a,b) -> {
        if (a.getReleaseYear() > b.getReleaseYear()) {
            return 1;
        }
        else if (a.getReleaseYear() < b.getReleaseYear()) {
            return -1;
        }
        else {
            return a.getTitle().compareTo(b.getTitle());
        }
    };

    MovieAnalytics() {
        this.movies = new ArrayList<>();
    }

    public static Consumer<Movie> showInfo() {
        Consumer<Movie> display = t -> System.out.format("%s (By %s, %d)%n", t.getTitle(), t.getDirector(), t.getReleaseYear());
        return display;
    }

    public void populateWithData(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null) {
            String[] parts= st.split(";");
            Movie newMovie = new Movie(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
                    parts[3], Double.parseDouble(parts[4]), parts[5]);
            movies.add(newMovie);
        }
    }

    public Stream<Movie> moviesAfter(int year) {
        return movies.stream().filter(m -> m.getReleaseYear() >= year).sorted(comp);
    }

    public Stream<Movie> moviesBefore(int year) {
        return movies.stream().filter(m -> m.getReleaseYear() <= year).sorted(comp);
    }

    public Stream<Movie> moviesBetween(int yearA, int yearB) {
        return movies.stream().filter(m -> m.getReleaseYear() >= yearA && m.getReleaseYear() <= yearB).sorted(comp);
    }

    public Stream<Movie> moviesByDirector(String director) {
        return movies.stream().filter(m -> m.getDirector().equalsIgnoreCase(director)).sorted(comp);
    }
}
