package ragna.c04;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import org.junit.Test;

public class CalculateNAVTest {

    @Test
    public void computeStockWorth() {
        final CalculateNAV calculateNAV =
                new CalculateNAV(ticker -> new BigDecimal("6.01"));

        BigDecimal expected = new BigDecimal("6010.00");

        assertEquals(0,
                calculateNAV.computeStockWorth("GOOG", 1000).compareTo(expected));

    }

}
