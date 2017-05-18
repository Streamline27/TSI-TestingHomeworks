import dto.Expense;
import dto.Reckoning;
import dto.Transaction;
import services.AverageCalculator;
import services.Converter;
import services.PersonSpendingCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Vladislav on 5/31/2016.
 */

public class SplittingEngine{


    public List<Transaction> getRequiredTransactions(List<Expense> expenses){
        PersonSpendingCalculator personSpendingCalculator = new PersonSpendingCalculator();
        AverageCalculator averageCalculator = new AverageCalculator();

        List<Reckoning> reckonings = Converter.convert(expenses, Converter.FROM_EXPENSE_TO_RECKONING);

        List<Reckoning> totalSpendingByPeople = personSpendingCalculator.getPersonalSpending(reckonings);
        Double average = averageCalculator.getAverage(totalSpendingByPeople);

        return getRequiredTransactions(totalSpendingByPeople, average);
    }

    public List<Transaction> getRequiredTransactions(List<Reckoning> totalSpendings, Double average){
        if(isUnprocessable(totalSpendings)) return new ArrayList<>();

        List<Reckoning> reckonings = getPreparedReckonings(totalSpendings, average);

        List<Reckoning> debts = getBelowZero(reckonings);
        List<Reckoning> credits = getAboweZero(reckonings);

        SplittingAlgorithm splittingAlgorithm = new SplittingAlgorithm(credits, debts);
        return splittingAlgorithm.execute();
    }

    /*
        Private helper methods
    */
    private List<Reckoning> getPreparedReckonings(List<Reckoning> summariesByPeople, Double average) {
        return summariesByPeople.stream()
                                .map(r -> new Reckoning(r.getPerson(), r.getAmount() - average))
                                .collect(Collectors.toList());
    }

    private List<Reckoning> getAboweZero(List<Reckoning> reckonings) {

        List<Reckoning> debtors = reckonings.stream().filter(Reckoning::isNegative).collect(Collectors.toList());
        debtors.forEach(Reckoning::inverseSign);
        return debtors;
    }

    private List<Reckoning> getBelowZero(List<Reckoning> reckonings) {
        return reckonings.stream().filter(Reckoning::isPositive).collect(Collectors.toList());
    }

    private boolean isUnprocessable(List<Reckoning> expenses) {
        return expenses==null || expenses.isEmpty() || expenses.size()==1;
    }

}

