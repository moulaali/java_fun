/**
 * Finds subarray with sum k for positive integers in O(n) time.
 * 
 * Idea is to use a sliding window with start and end and move the end if sum is less
 * than target and move the start if sum is less.
 */
import java.util.Stack;
 
class SubArrayWithSumK
{
    /* Returns true if the there is a subarray of arr[] with sum equal to
       'sum' otherwise returns false.  Also, prints the result */
    void subArraySum(int arr[], int n, int sum) 
    {
        int curr_sum = arr[0];
        int start = 0;
        int end = 0;
 
        // Pick a starting point
        while (start < n && end < n) 
        {
            System.out.println("curr_sum " + curr_sum + " sum " + sum + " start " + start + " end " + end);
            if (curr_sum < sum) {
                end++;
                curr_sum += arr[end];
            } else if (curr_sum > sum) {
                curr_sum -= arr[start];
                start++;
            } else {
                System.out.println("Sub-array found at : " + start + " " + end);
                return;
            }
        }
 
        System.out.println("No subarray found");
    }
 
    public static void main(String[] args) 
    {
        SubarraySum arraysum = new SubarraySum();
        int arr[] = {5, 101, 1, 3, 2, 6, 9, 50};
        int n = arr.length;
        int sum = 65;
        arraysum.subArraySum(arr, n, sum);
    }
}
