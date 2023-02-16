
import java.util.ArrayList;

public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        ArrayList<ArrayList<Character>> board = new ArrayList<>();
    
        for(int r=0; r<9; r++) {
                ArrayList<Character> rows = new ArrayList<>();
                for(int c=0; c<9; c++) {
                    rows.add(' ');
                }
                board.add(rows);
            }
        String s = "---+---+---";
        String aita = "#####################################";

            System.out.println(aita);
            for(int r=0; r<9; r++) {
                System.out.print("# ");
                for(int i=0; i<9; i++) {
                    System.out.print(board.get(r).get(i));
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
            System.out.println("#####################################");
    }
}
