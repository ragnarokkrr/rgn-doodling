package ragna.c08;

import java.util.stream.Stream;

public class PickStockFunctional {

    public static void findHighPriced(final Stream<String> symbols) {
        final StockInfo highPriced =
                symbols
                .map(StockUtil::getPrice)
                .filter(StockUtil.isPricelessThan(85000))
                .reduce(StockUtil::pickHigh)
                .get();
        System.out.println("High priced under $500 is " + highPriced);
    }
    public static void main(String[] args) {
        findHighPriced(Tickers.symbols.parallelStream());
    }

}
