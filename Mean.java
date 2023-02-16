/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author katar
 */
public class Mean {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        double mean = 0;
        for(String i : args) {
            mean += Double.parseDouble(i);
        }
        System.out.println("Mean: " + mean/args.length);
    }
}
