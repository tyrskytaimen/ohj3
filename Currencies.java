import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.TreeMap;

public class Currencies {
    public static void main(String args[]) throws IOException{
        TreeMap<String, Double> currencies = new TreeMap<>();
                
        BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            System.out.print("Enter command: ");
            String input = user.readLine();
            String[] parts = input.split(" ");
            String command = parts[0];
            System.out.println(input);
            if("quit".equalsIgnoreCase(command)) {
                System.out.println("Quit-command received, exiting...");
              break;
            }
            else if("rate".equalsIgnoreCase(command) && parts.length == 3) {
              currencies.put(parts[1].toUpperCase(),Double.parseDouble(parts[2]));
              System.out.format("Stored the rate 1 EUR = %.3f %s%n", Double.parseDouble(parts[2]), parts[1].toUpperCase());
            }   
            else if("convert".equalsIgnoreCase(command) && parts.length == 3) {
                if(!currencies.containsKey(parts[2].toUpperCase())) {
                    System.out.println("No rate for "+parts[2].toUpperCase()+" has been stored!");
                }
                else {
                    double money = Double.parseDouble(parts[1])/currencies.get(parts[2].toUpperCase());
                    System.out.format("%.3f %s = %.3f EUR%n", Double.parseDouble(parts[1]), parts[2].toUpperCase(), money);
                }
            }
            else if("rates".equalsIgnoreCase(command) && parts.length == 1) {
              System.out.println("Stored euro rates:");
              for(String key : currencies.keySet()) {
                  System.out.format("  %s %.3f%n",key, currencies.get(key));
              }
            }
            else {
                System.err.println("Unknown or illegal command!");
            }
        }
    }
}
