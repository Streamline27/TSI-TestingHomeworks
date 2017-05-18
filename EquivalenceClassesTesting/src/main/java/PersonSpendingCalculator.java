import java.util.*;

/**
 * Created by Vladislav on 6/1/2016.
 */


public class PersonSpendingCalculator {


    public List<Reckoning> getPersonalSpending(List<Reckoning> reckonings) {

        Map<String, Reckoning> groupedExpenses = new HashMap<>();

        initializeAccumulatorEntries(reckonings, groupedExpenses);
        accumulateInCorrespondingEntries(reckonings, groupedExpenses);

        groupedExpenses.entrySet().removeIf(e -> e.getValue().getAmount().equals(0D));

        return new ArrayList<>(groupedExpenses.values());
    }


    private void initializeAccumulatorEntries(List<Reckoning> reckonings, Map<String, Reckoning> groupedExpenses) {
        for (Reckoning reckoning: reckonings){
            groupedExpenses.put(reckoning.getPerson(), new Reckoning(reckoning.getPerson(), 0D));
        }
    }

    private void accumulateInCorrespondingEntries(List<Reckoning> reckonings, Map<String, Reckoning> groupedExpenses) {
        for (Reckoning reckoning: reckonings){
            groupedExpenses.get(reckoning.getPerson()).add(reckoning.getAmount());
        }
    }

}
