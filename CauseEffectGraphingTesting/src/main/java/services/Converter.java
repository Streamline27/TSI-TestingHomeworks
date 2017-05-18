package services;

import dto.Expense;
import dto.Reckoning;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Vladislav on 6/5/2016.
 */
public class Converter {

    public static <T, R> R convert(T entry, Function<T, R> transformation){
        return transformation.apply(entry);
    }

    public static <T, R> List<R> convert(List<T> entries, Function<T, R> transformation){
        return entries.stream().map(transformation::apply).collect(Collectors.toList());
    }

    public final static Function<Expense, Reckoning> FROM_EXPENSE_TO_RECKONING =
            expense -> new Reckoning(expense.getName(), expense.getAmount());

}
