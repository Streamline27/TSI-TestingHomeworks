import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

/**
 * Created by Vladislav Jakushin on 9/5/2017.
 */
public class SplittingAlgorithmTest {


    @Test
    public void noCreditsAndNoDepts_ResultInNoTransactions(){

        List<Transaction> transactions = getAlgorithm(new ArrayList<>(), new ArrayList<>()).execute();
        assertThat(transactions, hasSize(0));
    }


    @Test
    public void equalCreditAndDept_ReturnsTransactionFromDebtorToCreditor(){
        Reckoning debt    = new Reckoning("Debtor", 4D);
        Reckoning credit  = new Reckoning("Creditor", 4D);

        List<Reckoning> debts  = new ArrayList<>();
        List<Reckoning> credits = new ArrayList<>();

        debts.add(debt);
        credits.add(credit);

        List<Transaction> resultingTransaction = getAlgorithm(debts, credits).execute();
        assertThat(resultingTransaction, hasItem(new Transaction("Debtor", "Creditor", 4D)));

    }

    @Test
    public void ifThereIsNoDebtMatchingMaxCredit_ShouldLookForCreditMatchingMaxDebt(){
        List<Reckoning> debts   = new ArrayList<>();
        List<Reckoning> credits = new ArrayList<>();
        
        debts.add(new Reckoning("D1", 3D));
        debts.add(new Reckoning("D2", 2D));
        debts.add(new Reckoning("D3", 2D));

        credits.add(new Reckoning("C1", 4D));
        credits.add(new Reckoning("C2", 3D));

        List<Transaction> resultingTransaction = getAlgorithm(debts, credits).execute();
        assertThat(resultingTransaction, hasItem(new Transaction("D1", "C2", 3D)));

    }

    @Test
    public void ifThereIsNoEqualCreditsAndDebts_AndMaxCreditIsGreater_ReturnsTransactionBalancingMaxDebtFromIt(){
        List<Reckoning> debts   = new ArrayList<>();
        List<Reckoning> credits = new ArrayList<>();

        debts.add(new Reckoning("D1", 3D));

        credits.add(new Reckoning("C1", 2D));
        credits.add(new Reckoning("C2", 1D));

        List<Transaction> resultingTransaction = getAlgorithm(debts, credits).execute();

        assertThat(resultingTransaction, hasItems(new Transaction("D1", "C1", 2D),
                                                  new Transaction("D1", "C2", 1D)));
    }

    @Test
    public void ifThereIsNoEqualCreditsAndDebts_AndMaxDebtIsGreater_ReturnsTransactionBalancingMaxCreditFromIt(){
        List<Reckoning> debts   = new ArrayList<>();
        List<Reckoning> credits = new ArrayList<>();

        debts.add(new Reckoning("D1", 2D));
        debts.add(new Reckoning("D2", 1D));

        credits.add(new Reckoning("C1", 3D));

        List<Transaction> resultingTransaction = getAlgorithm(debts, credits).execute();

        assertThat(resultingTransaction, hasItems(new Transaction("D1", "C1", 2D),
                                                  new Transaction("D2", "C1", 1D)));
    }


    private SplittingAlgorithm getAlgorithm(List<Reckoning> debitors, List<Reckoning> creditors){
        return new SplittingAlgorithm(debitors, creditors);
    }

}