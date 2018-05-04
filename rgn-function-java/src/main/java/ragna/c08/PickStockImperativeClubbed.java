package ragna.c08;

import java.math.BigDecimal;
import java.util.function.Predicate;

public class PickStockImperativeClubbed {

    public static void main(String[] args) {
        StockInfo highPriced = new StockInfo("", BigDecimal.ZERO);
        final Predicate<StockInfo> isPriceLessThan500 = StockUtil.isPricelessThan(85000);

        for (String symbol :Tickers.symbols) {
            StockInfo stockInfo = StockUtil.getPrice(symbol);

            if(isPriceLessThan500.test(stockInfo))
                highPriced = StockUtil.pickHigh(highPriced, stockInfo);
        }
        System.out.println("Hihgh priced under $500 is " + highPriced);
    }

}
