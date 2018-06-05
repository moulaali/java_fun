/**
 * Identifies max rectangle under under the histogram
 * 
 * https://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 * 
 * <P>The idea involves computing the largest possible area which includes a given bar.
 * This can be computed by finding the smallest bar on right and smallest bar on left.
 * We maintain a stack of bar indices whose heights/values are non-increasing. 
 * 
 * <P>When we hit a smaller bar, we take the current position of bar and subtract the
 * index of the smallest bar before that and compute the area.
 */
import java.util.Stack;
 
public class LargestRectangleInHistogram 
{
    // The main function to find the maximum rectangular area under given
    // histogram with n bars
    static int getMaxArea(int hist[], int n) 
    {
       int maxArea = 0;
       int i = 0;
       Stack<Integer> s = new Stack<>();
       
       for (i = 0; i < n; ) {
           if (s.empty() || (hist[i] >= hist[s.peek()])) {
               System.out.println("Element at i=" + i + " is " + hist[i] + " is larger than top or emptyStack. pushing to stack");
               s.push(i++);
           } else {
               System.out.println("Element at i=" + i + " is " + hist[i] + " is smaller than top. Compute the new area with top bar");
               // Hit a smaller bar. Find the previous bars in stack and compute their max area
               int area = computeMaxAreaForBar(s, hist, i);
               if (area > maxArea) {
                   maxArea = area;
                   System.out.println("Updated maxArea to: " + maxArea);
               }
           }
           System.out.println("----------------------------------------------------------------------------------------------");
       }
       
       System.out.println("Handling left over elements in stack");       
       while (!s.empty()) {
           int area = computeMaxAreaForBar(s, hist, i);
           if (area > maxArea) {
               maxArea = area;
               System.out.println("Updated maxArea to: " + maxArea);
           }
           System.out.println("----------------------------------------------------------------------------------------------");
       }
       
       return maxArea;
    }
    
    static int computeMaxAreaForBar(Stack<Integer> s, int[] hist, int i) {
        System.out.println("Element at top of stack is : " + hist[s.peek()]);
        int topIndex = s.pop();
        int width = (s.empty() ? i : (i - s.peek() - 1));
        int area = hist[topIndex] *  width;
        System.out.println("Computed area value : " + area);
        return area;
    }
     
    // Driver program to test above function
    public static void main(String[] args) 
    {
        int hist[] = { 6, 2, 5, 4, 5, 1, 6 };
        System.out.println("Maximum area is " + getMaxArea(hist, hist.length));
    }
}
