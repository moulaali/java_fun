import java.util.Arrays;
/**
 * This program rotate's a matrix 90 degrees in clockwise direction.
 *
 * <p>We do this rotating each outer layer of the matrix at a time. Once a layer is rotated, we do
 * the same with the layer nested under it. Run time is O(n^2). This problem is similar to rotating
 * the pixels in a given image.
 * 
 * @author Moulaali Shaik
 */
public class RotateMatrix {

  public static void main(String[] args) throws Exception {
    int[][] input =
        new int[][] {
          {1, 2, 3, 4},
          {5, 6, 7, 8},
          {9, 10, 11, 12},
          {13, 14, 15, 16},
        };

    rotate(input);
    System.out.println(
        "Matrix after rotation : \n" + Arrays.deepToString(input).replaceAll("],", "],\n"));
  }

  private static void rotate(int[][] input) throws Exception {
    int i = 0;
    int n = input.length;

    // Validate that matrix is square
    for (int j = 0; j < n; j++) {
      if (input[j].length != n) {
        throw new Exception("The given matrix is not a square matix.");
      }
    }

    // Rotate one outer layer at a time
    for (int layer = 0; layer < n / 2; layer++) {
      int matrixSize = n - layer - 1;
      for (i = layer; i < matrixSize; i++) {
        // pick the four elements to swap
        MatrixElement first = new MatrixElement(layer, i);
        MatrixElement second = new MatrixElement(i, matrixSize);
        MatrixElement third = new MatrixElement(matrixSize, matrixSize - i);
        MatrixElement fourth = new MatrixElement(matrixSize - i, layer);

        swap(input, first, fourth);
        swap(input, fourth, third);
        swap(input, third, second);
      }
    }
  }

  /** Abstract a Matrix element with it's corresponding coordinates */
  private static void swap(int[][] input, MatrixElement first, MatrixElement second) {
    int temp = input[first.i][first.j];
    input[first.i][first.j] = input[second.i][second.j];
    input[second.i][second.j] = temp;
  }

  static class MatrixElement {
    final int i;
    final int j;

    public MatrixElement(int i, int j) {
      this.i = i;
      this.j = j;
    }
  }
}
