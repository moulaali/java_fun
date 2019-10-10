import java.util.HashMap;
import java.util.Map;

/**
 * Finds max population year for a given set of birth and death years.
 *
 * Run time : O(num_of_people * 100)
 */
public class YearWithLargestPopulation {

  public static void main(String[] args) {

    int[] births = {1, 3, 6, 2};
    int[] deaths = {5, 7, 9, 4};

    int curMax = 0;
    int curMaxYear = -1;

    Map<Integer, Integer> popMap = new HashMap<>();

    for (int i = 0; i < births.length; i++) {
      System.out.printf("Handling user number %d, active (%d-%d) \n", i, births[i], deaths[i]);
      for (int j = births[i]; j <= deaths[i]; j++) {
        int newPop = popMap.getOrDefault(j, 0) + 1;
        popMap.put(j, newPop);

        if (newPop > curMax) {
          System.out.printf("Found new max pop year %d with pop", j, newPop);
          curMax = newPop;
          curMaxYear = j;
        }
      }
    }

    System.out.println("popMap" + popMap);
    System.out.printf("Max Year: %d, Max Pop: %d" , curMaxYear, curMax);
  }
}
