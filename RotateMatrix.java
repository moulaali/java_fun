import java.util.Arrays;
/**
 * This program rotate's a matrix 90 degrees in clockwise direction.
 * 
 * <P> We do this rotating each outer layer of the matrix at a time.
 * Once a layer is rotated, we do the same with the layer nested under
 * it. Run time is O(n^2)
 * 
 * @author Moulaali Shaik
 */
public class RotateMatrix {

  static class MatrixElement {
    int i;
    int j;
    
    public MatrixElement(int i, int j) {
      this.i = i;
      this.j = j;
    }
  }
  
  public static void main(String[] args) throws Exception {
    int[][] input = new int[][] {
      {1, 2, 3, 4},
      {5, 6, 7, 8},
      {9, 10, 11, 12},
      {13, 14, 15, 16},
    };
    
    rotate(input);
    System.out.println("Matrix after rotation : \n" + Arrays.deepToString(input).replaceAll("],", "],\n"));
  }

  private static void rotate(int[][] input) throws Exception {
    int i = 0;
    int n = input.length;
    
    // Validate that matrix is square
    int columnCount = 0;
    for (int j = 0; j < input.length; j++) {
        if (j ==0) {
            columnCount = input[j].length;
            continue;
        }
        if (input[j].length != columnCount) {
            throw new Exception("The given matrix is not a square matix.");
        }
    }
    
    // Rotate one outer layer at a time
    for (int layer = 0; layer < n - 2; layer ++) {
        for (i = layer; i < input.length - layer - 1; i++) {
          // Swap the four elements
          MatrixElement first = new MatrixElement(layer, i);
          MatrixElement second = new MatrixElement(i, n - layer - 1);
          MatrixElement third = new MatrixElement(n - layer - 1, n - layer - i - 1);
          MatrixElement fourth = new MatrixElement(n - layer - i - 1, layer);
          
          swap(input, first, fourth);
          swap(input, fourth, third);
          swap(input, third, second);
        }
    }
  }

  private static void swap(int[][] input, MatrixElement first, MatrixElement second) {
    int temp = input[first.i][first.j];
    input[first.i][first.j] = input[second.i][second.j];
    input[second.i][second.j] = temp;
  }
}
