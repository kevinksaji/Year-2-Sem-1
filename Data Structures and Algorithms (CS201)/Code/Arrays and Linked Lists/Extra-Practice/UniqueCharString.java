// Write a method that takes two three-dimensional integer arrays and adds them component wise.
// Track a specific operator (e.g., >, +, -) that occurs the most often in your algorithm. Assume both arrays
// are of the same size. How many times does the operator repeat?

import java.util.*;
public class UniqueCharString {
    public static boolean checker(String test) {
        Set<Character> set = new HashSet<>();
        char[] testArr = test.toCharArray();
        for (int i = 0; i< testArr.length; i++) {
            set.add(testArr[i]);
        }
        if (set.size() == testArr.length) {
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        String test = "qwertyy";
        System.out.println(checker(test));
    }
}
