import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'minimumBribes' function below.
     *
     * The function accepts INTEGER_ARRAY q as parameter.
     */

    public static void minimumBribes(List<Integer> q) {
        int n = q.size();
        Integer[] a = q.toArray(new Integer[n]).clone();
        int counter = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (a[i] != i + 1) {
                if (i - 1 >= 0 && a[i-1] == i + 1) {
                    counter++;
                    swap(a, i, i-1);
                } else if (i - 2 >= 0 && a[i-2] == i + 1) {
                    counter += 2;
                    a[i-2] = a[i-1]; //erase i + 1 at a[i-2]
                    a[i-1] = a[i];
                    a[i] = i + 1; //bring back i + 1 to its proper position
                } else {
                    System.out.println("Too chaotic");
                    return;
                }
            }
        }
        System.out.println(counter);
    }

    static void swap(Integer[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> q = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                Result.minimumBribes(q);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
