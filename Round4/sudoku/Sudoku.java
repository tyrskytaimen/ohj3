import java.util.ArrayList;

public class Sudoku {
    private ArrayList<ArrayList<Character>> Board = new ArrayList<>();
    final private ArrayList<Character> AllowedChars = new ArrayList<>();
    
    public Sudoku() {
        //Add empty rows
        for(int r=0; r<9; r++) {
            ArrayList<Character> rows = new ArrayList<>();
            for(int c=0; c<9; c++) {
                rows.add(' ');
                AllowedChars.add((char)(c+48));  //Add allowed characters
            }
            this.Board.add(rows);
        }
        AllowedChars.add(' ');
        AllowedChars.add('9');
    }
    public void set(int i, int j, char c) {
        if(i<0 || i>8 || j<0 || j>8) {
            System.out.println("Trying to access illegal cell ("+i+", "+j+")!");
        }
        else {
            if(AllowedChars.contains(c)) {
                this.Board.get(i).set(j, c);
            }
            else {
                System.out.println("Trying to set illegal character "+c+" to ("+i+", "+j+")!");
            }
        }

    }
    public boolean check() {
        //Rows
        for(int r=0; r<9; r++) { //Row
            ArrayList<Character> row = Board.get(r);
            for(int i=1; i<10; i++) { //Number
                int x = 0;
                for(char c : row) { //Char in row
                    if(c == (char)(i+48)) {
                        x++;
                    }
                }
                if(x > 1) {
                    System.out.println("Row "+r+" has multiple "+i+"'s!");
                    return false;
                }
            }
        }
        //Columns
        for(int i=0; i<9; i++) { //Column
            for(int n=1; n<10; n++) { //Number
                int x = 0;
                for(int r=0; r<9; r++) { //Char in column
                    if(Board.get(r).get(i) == (char)(n+48)) {
                        x++;
                    }
                }
                if(x > 1) {
                    System.out.println("Column "+i+" has multiple "+n+"'s!");
                    return false;
                }
            }

        }
        //Blocks
        for(int r=0; r<9; r+=3) {
            for(int c=0; c<9; c+=3) {
                int n = checkBlocks(r,c);
                if(n != -1) {
                    System.out.println("Block at ("+r+", "+c+") has multiple "+n+"'s!");
                    return false;
                }
            }
        }
        return true;
    }
    public void print() {
        String s = "---+---+---";
        String aita = "#####################################";
        
        System.out.println(aita);
        for(int r=0; r<9; r++) {
            System.out.print("# ");
            for(int i=0; i<9; i++) {
                System.out.print(Board.get(r).get(i));
                if(i==2 || i==5) {
                    System.out.print(" # ");
                }
                else if(i != 8) {
                    System.out.print(" | ");
                }
            }
            System.out.println(" #");
            if(r==2 || r==5) {
                    System.out.println(aita);
                }
            else if(r != 8) {
                System.out.println("#"+s+"#"+s+"#"+s+"#");
            }
        }
        System.out.println(aita);
    }
    private int checkBlocks(int row, int col) {
        for(int n=1; n<10; n++) {
            int x = 0;
            for(int r=row; r<row+3; r++) {
                for(int i=col; i<col+3; i++) {
                    if(Board.get(r).get(i) == (char)(n+48)) {
                        x++;
                    }
                }
            }
            if(x > 1) {
                return n;
            }
        }
        return -1;
    }
}
