/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author katar
 */
import java.util.ArrayList;
import java.util.Collections;

public class Median {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        ArrayList<Double> numbers = new ArrayList<>();
        
        for(String s : args) {
            numbers.add(Double.parseDouble(s));
        }
        
        Collections.sort(numbers);
        Integer size = numbers.size();
        Double median;
        if(size % 2  == 0) {
            median = (numbers.get(size/2) + numbers.get(size/2-1)) / 2;
        }
        else {
            median = numbers.get(size/2);
        }
        System.out.print("Median: " + median);
    }
}
