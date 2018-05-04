package ragna.c08;

import java.math.BigDecimal;

public class StockInfo {
    public final String ticker;
    public final BigDecimal price;

    public StockInfo(String ticker, BigDecimal price) {
        this.ticker = ticker;
        this.price = price;
    }

    @Override
    public String toString() {
        return "StockInfo{" +
                "ticker='" + ticker + '\'' +
                ", price=" + price +
                '}';
    }
}
