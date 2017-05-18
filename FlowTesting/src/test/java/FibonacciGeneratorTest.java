import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by Vladislav Jakushin on 11/5/2017.
 */
public class FibonacciGeneratorTest {

    @Test
    public void testNoIterations(){
        FibonacciGenerator g = new FibonacciGenerator();
        int result = g.getNumber(1);
        assertThat(result, is(1));
    }

    @Test
    public void testOneIteration(){
        FibonacciGenerator g = new FibonacciGenerator();
        int result = g.getNumber(3);
        assertThat(result, is(2));
    }

    @Test
    public void testThreeIterations(){
        FibonacciGenerator g = new FibonacciGenerator();
        int result = g.getNumber(4);
        assertThat(result, is(3));
    }

}