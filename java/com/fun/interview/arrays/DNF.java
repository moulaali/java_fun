import java.util.Arrays;

/**
 * This program does a three way partitioning aka Dutch National Flag problem.
 * 
 * <P> Also see:
 * http://www.geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/
 *
 * @author Moulaali Shaik
 */
public class DNF {

  public static void main(String[] args) {
    int[] input = new int[] {0, 1, 2, 1, 1, 2, 2, 0};
    doDnf(input);
    // This should print : Array after partitoning : [0, 0, 1, 1, 1, 2, 2, 2]
    System.out.println("Array after partitoning : " + Arrays.toString(input));
  }

  private static void doDnf(int[] input) {
    int low = 0;
    int mid = 0;
    int high = input.length - 1;
    
    while (mid <= high) {
      if (input[mid] == 0) {
        swap(input, low++, mid++);
      } else if (input[mid] == 1) {
        mid++;
      } else if (input[mid] == 2) {
        swap(input, mid, high--);
      }
    }
  }

  private static void swap(int[] input, int i, int j) {
    int temp = input[i];
    input[i] = input[j];
    input[j] = temp;
  }
}
