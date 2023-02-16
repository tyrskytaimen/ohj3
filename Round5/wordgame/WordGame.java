import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Character.toLowerCase;

public class WordGame {
    private ArrayList<String> words = new ArrayList<>();
    private WordGameState currentGame = new WordGameState();
    private String theWord;

    public static class WordGameState {
        private ArrayList<String> word = new ArrayList<>();
        private String correctWord;
        private int mistakes;
        private int mistakeLimit;
        private ArrayList<Character> missingChars = new ArrayList<>();

        private WordGameState() {

        }

        public String getWord() {
            if(mistakes > mistakeLimit) {
                return correctWord;
            }
            return String.join("", word);
        }

        public int getMistakes() {
            return mistakes;
        }

        public int getMistakeLimit() {
            return mistakeLimit;
        }

        public int getMissingChars() {
            return Collections.frequency(word, "_");
        }
    }

    public WordGame(String wordFilename) throws IOException{
        try(var file = new BufferedReader(new FileReader(wordFilename))) {
            String line;
            while ((line = file.readLine()) != null) {
                words.add(line);
            }
        }
    }

    public void initGame(int wordIndex, int mistakeLimit) {
        currentGame = new WordGameState();
        currentGame.mistakeLimit = mistakeLimit;
        theWord = words.get(wordIndex % words.size());
        currentGame.correctWord = theWord;
        for(int i=0; i<theWord.length(); i++) {
            currentGame.word.add("_");
            if(!currentGame.missingChars.contains(theWord.charAt(i))) {
                currentGame.missingChars.add(theWord.charAt(i));
            }
        }
    }

    public boolean isGameActive() {
        if(currentGame.getMissingChars() == 0) {
            return false;
        }
        else if(currentGame.getMistakes() > currentGame.getMistakeLimit()) {
            return false;
        }
        return true;
    }

    public WordGameState getGameState() throws GameStateException{
        if(!isGameActive()) {
            throw new GameStateException("There is currently no active word game!");
        }
        return currentGame;
    }

    public WordGameState guess(char c) throws GameStateException{
        char guess = toLowerCase(c);
        if(!isGameActive()) {
            throw new GameStateException("There is currently no active word game!");
        }
        if(currentGame.missingChars.contains(guess)) {
            currentGame.missingChars.remove(currentGame.missingChars.indexOf(guess));
            for(int i=0; i<theWord.length(); i++) {
                if(theWord.charAt(i) == guess) {
                    currentGame.word.set(i, Character.toString(guess));
                }
            }
        }
        else {
            currentGame.mistakes++;
        }
        return currentGame;
    }

    public WordGameState guess(String word) throws GameStateException{
        if(!isGameActive()) {
            throw new GameStateException("There is currently no active word game!");
        }
        if(word.equalsIgnoreCase(theWord)) {
            for(int i=0; i<theWord.length(); i++) {
                currentGame.word.set(i, Character.toString(word.charAt(i)));
                currentGame.missingChars.clear();
            }
        }
        else {
            currentGame.mistakes++;
        }
        return currentGame;
    }
}
