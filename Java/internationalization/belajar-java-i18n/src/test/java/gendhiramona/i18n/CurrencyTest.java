package gendhiramona.i18n;

import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

public class CurrencyTest {

    @Test
    void testCurrencyIndonesia() {
        var indonesia = new Locale.Builder()
                .setLanguage("id")
                .setRegion("ID")
                .build();
        var currency = Currency.getInstance(indonesia);
        System.out.println(currency.getSymbol(indonesia));
        System.out.println(currency.getDisplayName());
        System.out.println(currency.getCurrencyCode());
    }

    @Test
    void testCurrencyUSA() {
        var america = new Locale.Builder()
                .setLanguage("en")
                .setRegion("US")
                .build();
        var currency = Currency.getInstance(america);
        System.out.println(currency.getSymbol(america));
        System.out.println(currency.getDisplayName());
        System.out.println(currency.getCurrencyCode());
    }

    @Test
    void testNumberFormatCurrencyIndonesia() {
        var indonesia = new Locale.Builder()
                .setLanguage("id")
                .setRegion("ID")
                .build();
        var numberFormat = NumberFormat.getCurrencyInstance(indonesia);
        var format = numberFormat.format(10000000.333);
        System.out.println(format);
    }

    @Test
    void testNumberFormatCurrencyParseIndonesia() {
        var indonesia = new Locale.Builder()
                .setLanguage("id")
                .setRegion("ID")
                .build();
        var numberFormat = NumberFormat.getCurrencyInstance(indonesia);

        try {
            var parse = numberFormat.parse("Rp1.000.000,25").doubleValue();
            System.out.println(parse);
        } catch (ParseException e) {
            System.out.println("Parse error" + e.getMessage());
        }
    }
}
