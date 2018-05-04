package ragna.c08;

import static java.util.stream.Collectors.joining;

import java.math.BigDecimal;

public class Stocks100 {

    public static void main(String[] args) {
        final BigDecimal HUNDRED = new BigDecimal("100");

        System.out.println("Stocks priced over $100 are :" +
        Tickers.symbols.stream()
                .peek(symbol -> System.out.println(symbol +  " " + symbol.hashCode()))
                .filter(symbol -> YahooFinance.getPrice(symbol).compareTo(HUNDRED) > 0)
                .sorted()
                .collect(joining(", ")));
    }



}
