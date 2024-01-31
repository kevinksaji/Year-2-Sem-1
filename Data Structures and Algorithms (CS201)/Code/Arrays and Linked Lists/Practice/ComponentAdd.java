// add the elements in the corresponding slots in the 3d arrays

import java.util.*;
public class ComponentAdd {
    public static void adder(int[][][] arr1, int[][][] arr2) {
        // variable to count number of + operators used
        int addCounter = 0;
        // triple nested for loop to iterate through 3d arrays
        for (int i = 0; i<arr1.length; i++) {
            for (int j = 0; j<arr1[i].length; j++) {
                for (int k = 0; k<arr1[i][j].length; k++) {
                    addCounter++;
                    arr1[i][j][k] += arr2[i][j][k];
                }
            }
        }
        // printing output (counting + operators in execution)
        System.out.println("Number of times + operator was used in execution: " + addCounter);
        System.out.println(Arrays.deepToString(arr1));

        
    }
    public static void main(String[] args) {
    // test1 and test2 are 3d arrays
    int[][][] test1 = {
        {
        {1, -2, 3}, 
        {2, 3, 4}
        }, 
        { 
        {-4, -5, 6, 9}, 
        {1}, 
        {2, 3}
        } 
    };
    int[][][] test2 = {
        {
        {0, -5, -4}, 
        {2, 7, 9}
        }, 
        { 
        {-5, -5, 2, 2}, 
        {1}, 
        {3, -9}
        } 
    };
    adder(test1, test2);
            
    }
}
