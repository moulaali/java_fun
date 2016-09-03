/**
 * Compute the parity of a given integer
 * 
 * <P> The parity of a sequence of bits is 1 if the number of 1s 
 * in the sequence is odd; otherwise, it is 0.
 * 
 * @author Moulaali Shaik
 */
class Parity {
  public static void main(String[] args) {
    int test = Integer.parseInt("11111", 2);
    System.out.println("parity of " + test + " " + getParity(test));
  }
  
  private static int getParity(int input) {
    int parity = 0;
    for (int temp = input; temp != 0; temp >>= 1) {
      if ((temp & 1) != 0) {
        parity ^= 1;  // flips 1 <-> 0
      }
    }
    return parity;
  }
}
