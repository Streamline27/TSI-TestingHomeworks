import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Vladislav Jakushin on 17/5/2017.
 */
public class PersonSpendingCalculatorTest {

    @Test
    public void whenNoElements_ShouldReturnNoSpendings(){
        PersonSpendingCalculator c = new PersonSpendingCalculator();

        List<Reckoning> spendings = c.getPersonalSpending(new ArrayList<>());

        assert(spendings.isEmpty());
    }

    @Test
    public void whenDifferentNames_and_NegativeElements_ShouldReturnSumsByNames(){

        List<Reckoning> spendings = new ArrayList<>();
        spendings.add(new Reckoning("firstPerson", -2D));
        spendings.add(new Reckoning("firstPerson", -5D));
        spendings.add(new Reckoning("secondPerson", -7D));
        spendings.add(new Reckoning("secondPerson", -7D));

        List<Reckoning> personalSpendings = computePersonalSpendings(spendings);
        Reckoning firstPersonsReckoning  = getByName(personalSpendings, "firstPerson");
        Reckoning secondPersonsReckoning = getByName(personalSpendings, "secondPerson");


        assertThat(personalSpendings.size(), is(2));
        assertThat(firstPersonsReckoning.getAmount(),  is(-7D));
        assertThat(secondPersonsReckoning.getAmount(), is(-14D));

    }

    @Test
    public void whenDifferentNames_and_PositiveElements_ShouldReturnSumsByNames(){

        List<Reckoning> spendings = new ArrayList<>();
        spendings.add(new Reckoning("firstPerson",  2D));
        spendings.add(new Reckoning("firstPerson",  5D));
        spendings.add(new Reckoning("secondPerson", 7D));
        spendings.add(new Reckoning("secondPerson", 7D));

        List<Reckoning> personalSpendings = computePersonalSpendings(spendings);
        Reckoning firstPersonsReckoning  = getByName(personalSpendings, "firstPerson");
        Reckoning secondPersonsReckoning = getByName(personalSpendings, "secondPerson");


        assertThat(personalSpendings.size(), is(2));
        assertThat(firstPersonsReckoning.getAmount(),  is(7D));
        assertThat(secondPersonsReckoning.getAmount(), is(14D));

    }

    @Test
    public void whenDifferentNames_and_ZeroElements_ShouldReturnNothing(){

        List<Reckoning> spendings = new ArrayList<>();
        spendings.add(new Reckoning("P1", 0D));
        spendings.add(new Reckoning("P2", 0D));

        List<Reckoning> personalSpendings = computePersonalSpendings(spendings);

        assertThat(personalSpendings.size(), is(0));

    }


    @Test
    public void whenSameNames_and_PositiveElements_ShouldReturnSumByName(){

        List<Reckoning> spendings = new ArrayList<>();
        spendings.add(new Reckoning("P1", 2D));
        spendings.add(new Reckoning("P1", 3D));

        List<Reckoning> personalSpendings = computePersonalSpendings(spendings);

        assertThat(personalSpendings.size(), is(1));
        assertThat(personalSpendings.get(0).getAmount(), is(5D));

    }

    @Test
    public void whenSameNames_and_NegativeElements_ShouldReturnSumByNames(){

        List<Reckoning> spendings = new ArrayList<>();
        spendings.add(new Reckoning("P1", -2D));
        spendings.add(new Reckoning("P1", -3D));

        List<Reckoning> personalSpendings = computePersonalSpendings(spendings);

        assertThat(personalSpendings.size(), is(1));
        assertThat(personalSpendings.get(0).getAmount(), is(-5D));

    }

    @Test
    public void whenSameNames_and_ZeroElements_ShouldReturnNothing(){

        List<Reckoning> spendings = new ArrayList<>();
        spendings.add(new Reckoning("P1", 0D));
        spendings.add(new Reckoning("P1", 0D));

        List<Reckoning> personalSpendings = computePersonalSpendings(spendings);

        assertThat(personalSpendings.size(), is(0));

    }


    private List<Reckoning> computePersonalSpendings(List<Reckoning> spendings){
        PersonSpendingCalculator c = new PersonSpendingCalculator();
        return c.getPersonalSpending(spendings);
    }


    private Reckoning getByName(List<Reckoning> personalSpendings, String name) {
        return personalSpendings.stream()
                .filter(reckoning -> reckoning.getPerson().equals(name))
                .findFirst().orElseGet(null);
    }
}