import dto.Expense;
import dto.Transaction;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Vladislav Jakushin on 18/5/2017.
 */
public class SplittingEngineTest {

    @Test
    public void whenNoExpenses_ShouldReturnNoTransactions(){

        List<Transaction> transactions = getRequiredTransactions(new ArrayList<>());

        assert(transactions.isEmpty());
    }

    @Test
    public void whenOneNegativeExpense_ShouldReturnNoTransactions(){
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("P1", "", -1D));

        List<Transaction> transactions = getRequiredTransactions(expenses);

        assert(transactions.isEmpty());
    }

    @Test
    public void whenOnePositiveExpense_ShouldReturnNoTransactions(){
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("P1", "", 1D));

        List<Transaction> transactions = getRequiredTransactions(expenses);

        assert(transactions.isEmpty());
    }

    @Test
    public void whenSomeNegativeExpenses_ShouldReturnCorrectTransactions(){
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("P1", "", 5D));
        expenses.add(new Expense("P2", "", -3D));
        expenses.add(new Expense("P3", "", -2D));

        List<Transaction> transactions = getRequiredTransactions(expenses);

        Transaction fromP2toP1 = findTransactionFromTo(transactions, "P2", "P1");
        Transaction fromP3toP1 = findTransactionFromTo(transactions, "P3", "P1");

        assertThat(transactions.size(), is(2));
        assertThat(fromP2toP1.getAmount(), is(3D));
        assertThat(fromP3toP1.getAmount(), is(2D));

    }

    @Test
    public void whenAllPositiveExpenses_ShouldReturnCorrectTransactions() {
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("P1", "", 11D));
        expenses.add(new Expense("P2", "", 3D));
        expenses.add(new Expense("P3", "", 4D));

        List<Transaction> transactions = getRequiredTransactions(expenses);

        Transaction fromP2toP1 = findTransactionFromTo(transactions, "P2", "P1");
        Transaction fromP3toP1 = findTransactionFromTo(transactions, "P3", "P1");

        assertThat(transactions.size(), is(2));
        assertThat(fromP2toP1.getAmount(), is(3D));
        assertThat(fromP3toP1.getAmount(), is(2D));
    }



    private List<Transaction> getRequiredTransactions(List<Expense> expenses) {
        SplittingEngine e = new SplittingEngine();
        return e.getRequiredTransactions(expenses);
    }

    private Transaction findTransactionFromTo(List<Transaction> transactions, String from, String to) {
        return transactions.stream()
                    .filter(t -> t.getDebtor().equals(from) && t.getCreditor().equals(to))
                    .findFirst().orElseGet(null);
    }


}