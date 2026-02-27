package gendhiramona.i18n;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatTest {

    @Test
    void testDateFormat() {
        var pattern = "EEEE, dd MMMM yyyy 'at' HH:mm:ss z";
        var dateFormat = new SimpleDateFormat(pattern);

        var format = dateFormat.format(new Date());
        System.out.println(format);
    }

    @Test
    void testDateFormatIndonesia() {
        var pattern = "EEEE, dd MMMM yyyy 'at' HH:mm:ss z";
        Locale indonesia = new Locale.Builder()
                .setLanguage("id")
                .setRegion("ID")
                .build();
        var dateFormat = new SimpleDateFormat(pattern, indonesia);

        var format = dateFormat.format(new Date());
        System.out.println(format);
    }

    @Test
    void testDateFormatJapanese() {
        var pattern = "EEEE, dd MMMM yyyy 'at' HH:mm:ss z";
        Locale japanese = new Locale.Builder()
                .setLanguage("ja")
                .setRegion("JP")
                .build();
        var dateFormat = new SimpleDateFormat(pattern, japanese);

        var format = dateFormat.format(new Date());
        System.out.println(format);
    }

    @Test
    void testDateFormatParseIndonesia() {
        var pattern = "EEEE, dd MMMM yyyy 'at' HH:mm:ss";
        Locale indonesia = new Locale.Builder()
                .setLanguage("id")
                .setRegion("ID")
                .build();
        var dateFormat = new SimpleDateFormat(pattern, indonesia);


        try {
            var date = dateFormat.parse("Senin, 01 Maret 2021 at 12:00:00");
            System.out.println(date);
        } catch (ParseException e) {
            System.out.println("Parse error" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    void testDateFormatParseJapanese() {
        var pattern = "EEEE, dd MMMM yyyy 'at' HH:mm:ss z";
        Locale japanese = new Locale.Builder()
                .setLanguage("ja")
                .setRegion("JP")
                .build();
        var dateFormat = new SimpleDateFormat(pattern, japanese);

        try {
            var date = dateFormat.parse("火曜日, 17 6月 2025 at 05:34:08 WIB");
            System.out.println(date);
        } catch (ParseException e) {
            System.out.println("Parse error" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
