// Let B be an array of size n ≥ 6 containing integers from 1 to n − 5 inclusive, five of which are
// repeated.
// For e.g. if n is 7, the array would contain integers 1 to 2, and this array would contain an integer (1 or
// 2) which is repeated another 5 times, thus total 6 occurrences of this integer in the array.
// One possible array would contain 1, 1, 1, 1, 1, 1, 2.
// Another possibility is 1, 2, 2, 2, 2, 2, 2.
// a. Describe an algorithm for finding the five integers in B that are repeated.
// b. Track a specific operator (e.g., >, +, −) that occurs the most often in your algorithm. How
// many times does it repeat?

import java.util.*;

public class RepeatedInteger {
    public static void repeatTracker(int[] arr) {
        // create a new hashset to store repeated integers indexes
        Set<Integer> set = new HashSet<>();
        // to track the number of times + operator is used.
        int addCounter = 0;
        int repeatedInteger = 0;

        //nested for loop to compare every element in the array to check for repetition
        for(int i = 0; i<arr.length; i++) {
            for (int j = 0; j<arr.length; j++) {
                addCounter++;
                if (arr[i] == arr[j] && i != j) {
                    repeatedInteger = arr[i];
                    set.add(i);

                    break;
                }
            }
        }
        // printing output
        System.out.println("Repeated integer is: " + repeatedInteger);
        System.out.println("Repeated integers indexes:");
        for (Integer i : set) {
            System.out.println(i);
        }
        System.out.println("Number of times the + operator is used in this algorithm: " + addCounter);
    }
    public static void main(String[] args) {
        
        int[] arr = {2, 2, 2, 1, 2, 2, 2};
        repeatTracker(arr);
    }
    
}