package services;

import dto.Reckoning;

import java.util.List;

/**
 * Created by Vladislav on 6/1/2016.
 */

public class AverageCalculator{

    public double getAverage(List<Reckoning> reckonings) {
        return reckonings.stream().mapToDouble(Reckoning::getAmount).average().orElse(0);
    }
}
