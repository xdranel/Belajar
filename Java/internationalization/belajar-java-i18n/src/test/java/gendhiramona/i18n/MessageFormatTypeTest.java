package gendhiramona.i18n;

import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageFormatTypeTest {

    @Test
    void testMessageFormatType() {
        Locale locale = new Locale.Builder()
                .setLanguage("id")
                .setRegion("ID")
                .build();

        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", locale);
        String pattern = resourceBundle.getString("status");

        MessageFormat messageFormat = new MessageFormat(pattern, locale);
        String format = messageFormat.format(new Object[]{
                "John", new Date(), 10000000
        });
        System.out.println(format);
    }

    @Test
    void testMessageFormatTypeUS() {
        Locale locale = new Locale.Builder()
                .setLanguage("en")
                .setRegion("US")
                .build();

        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", locale);
        String pattern = resourceBundle.getString("status");

        MessageFormat messageFormat = new MessageFormat(pattern, locale);
        String format = messageFormat.format(new Object[]{
                "John", new Date(), 10000000
        });
        System.out.println(format);
    }
}
