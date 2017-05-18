/**
 * Created by Vladislav Jakushin on 11/5/2017.
 */
public class FibonacciGenerator {

    public int getNumber(int index){
        if (index <= 0) throw new IllegalArgumentException();

        int previous = 1;
        int current = 1;
        int computedNumber;
        int i = 2;

        while (i < index){
            computedNumber = previous + current;
            previous = current;
            current = computedNumber;
            i++;
        }
        return current;
    }
}
