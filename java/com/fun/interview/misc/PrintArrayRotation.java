package fun;

public class PrintArrayRotation {
  public static void main(String[] args) {
    int[] array = new int[] {5, 6, 7, 8, 9, 10};
    printOverflow(array, 1);
  }
  
  private static void printOverflow(int[] array, int positionIndex) {
    System.out.println("printing");
    for (int k = positionIndex + 1; k != positionIndex; k = (k+1)%array.length) {
      System.out.println(array[k] + ",");
    }
  }
}

