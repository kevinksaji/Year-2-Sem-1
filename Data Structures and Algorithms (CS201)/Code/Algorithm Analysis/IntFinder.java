
public class IntFinder {
    public static int intFinder( int [] arr) {
        int greatest = arr[0];
        for (int i = 1; i<arr.length;i++) {
            if (arr[i] > greatest) {
                greatest = arr[i];
            }
        }
        return (greatest * 2);
    
    }
    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 6, 5};
        int result = intFinder(arr);
        System.out.println(result);
    }
}