import java.util.Arrays;

public class Parameters {
    public static void main(String args[]) {
        int numberLength = String.valueOf(args.length).length();
        int wordLength = 0;
        
        for(String arg : args) {
            if(arg.length() > wordLength) {
                wordLength = arg.length();
            }
        }
        
        String ends = new String();
        for(int i=0; i<(numberLength+wordLength+7); i++) {
            ends += "#";
        }
        
        String middle = "#";
        for(int i=0; i<(numberLength+2); i++) {
            middle += "-";
        }
        middle += "+";
        for(int i=0; i<(wordLength+2); i++) {
            middle += "-";
        }
        middle += "#";
        
        //Print first line
        System.out.println(ends);
        
        Arrays.sort(args);
        for(int i=0; i<args.length; i++) {
            System.out.format("# %"+numberLength+"d | %s%"+(wordLength-args[i].length()+1)+"s#%n", i+1, args[i], " ");
            
            //Print last line
            if(i == args.length-1) {
                System.out.println(ends);
            }
            else {
                System.out.println(middle);
            }
        } 
    }
}
