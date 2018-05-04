package ragna.c04;

import java.math.BigDecimal;
import java.util.function.Function;

public class CalculateNAV {
    //delegating using Lambda Expressions


    private Function<String, BigDecimal> priceFinder;

    public CalculateNAV(Function<String, BigDecimal> priceFinder) {
        this.priceFinder = priceFinder;
    }

    public BigDecimal computeStockWorth(final String ticker, final int shares) {
        return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(shares));
    }


    public static void main(String[] args) {
        //dependency inversion
        final CalculateNAV calculateNAV = new CalculateNAV(YahooFinance::getPrice);

        System.out.println(String.format("100 shares of Google worth: $%.2f",
                calculateNAV.computeStockWorth("GOOG", 100)));
    }

}
