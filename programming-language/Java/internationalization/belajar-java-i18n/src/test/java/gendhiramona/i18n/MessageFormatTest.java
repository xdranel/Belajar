package gendhiramona.i18n;

import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageFormatTest {

    @Test
    void testMessageFormat() {
        var pattern = "Hi {0}, Anda bisa mencari data Anda dengan mengetikan \"{1}\" di kolom pencarian.";
        var messageFormat = new MessageFormat(pattern);
        System.out.println(
                messageFormat.format(new Object[]{"Joko", "data"})
        );
    }

    @Test
    void testMessageFormatResourceBundle() {
        Locale locale = new Locale.Builder()
                .setLanguage("en")
                .setRegion("US")
                .build();
        var resourceBundle = ResourceBundle.getBundle("message", locale);

        var pattern = resourceBundle.getString("welcome.message");

        var messageFormat = new MessageFormat(pattern);
        var format = messageFormat.format(new Object[]{
                "Joko", "Laundry Online"
        });
        System.out.println(format);
    }
}
