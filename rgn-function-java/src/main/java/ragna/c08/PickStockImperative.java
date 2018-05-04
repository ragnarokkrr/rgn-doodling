package ragna.c08;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PickStockImperative {

    public static void main(String[] args) {
        final List<StockInfo> stocks = new ArrayList<>();
        for(String symbol : Tickers.symbols) {
            stocks.add(StockUtil.getPrice(symbol));
        }


        final List<StockInfo> stockPricedUnder500 = new ArrayList<>();

        final Predicate<StockInfo> isPricelessThan500 = StockUtil.isPricelessThan(85000);
        for (StockInfo stock : stocks) {
            if (isPricelessThan500.test(stock))
                stockPricedUnder500.add(stock);
        }

        StockInfo highPriced = new StockInfo("", BigDecimal.ZERO);
        for(StockInfo stock : stockPricedUnder500) {
            highPriced = StockUtil.pickHigh(highPriced, stock);
        }

        System.out.println("High priced under $500 is " + highPriced);
    }

}
