
public class TwoDArraySum {
    public static int sum2DArray(int[][] arr, int i, int j) {
        // Base case: If we're at the end of the array, return 0.
        if (i == arr.length) {
            return 0;
        }

        // If we've reached the end of a row, move to the next row (increment i, reset
        // j).
        if (j == arr[i].length) {
            return sum2DArray(arr, i + 1, 0);
        }

        // Add the current element to the sum and move to the next element in the row
        // (increment j).
        return arr[i][j] + sum2DArray(arr, i, j + 1);
    }

    public static void main(String[] args) {
        // Example 3x3 array
        int[][] exampleArray = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        int sum = sum2DArray(exampleArray, 0, 0);
        System.out.println("Sum of elements in the 3x3 array: " + sum);
    }
}
