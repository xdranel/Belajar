package gendhiramona.i18n;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleTest {

    @Test
    void testResourceBundle() {

        var resourceBundle = ResourceBundle.getBundle("message");

        System.out.println(resourceBundle.getString("hello"));
        System.out.println(resourceBundle.getString("goodbye"));
    }

    @Test
    void testResourceBundleIndonesia() {

        var indonesia = new Locale.Builder()
                .setLanguage("id")
                .setRegion("ID")
                .build();
        var resourceBundle = ResourceBundle.getBundle("message", indonesia);

        System.out.println(resourceBundle.getString("hello"));
        System.out.println(resourceBundle.getString("goodbye"));
    }

    @Test
    void testResourceBundleNotFound() {

        var unitedStates = new Locale.Builder()
                .setLanguage("en")
                .setRegion("US")
                .build();
        var resourceBundle = ResourceBundle.getBundle("message", unitedStates);

        System.out.println(resourceBundle.getString("hello"));
        System.out.println(resourceBundle.getString("goodbye"));
    }

    @Test
    void testResourceBundleMultipleTimes() {

        var unitedStates = new Locale.Builder()
                .setLanguage("en")
                .setRegion("US")
                .build();
        var resourceBundle1 = ResourceBundle.getBundle("message", unitedStates);
        var resourceBundle2 = ResourceBundle.getBundle("message", unitedStates);

        System.out.println(resourceBundle1 == resourceBundle2);
    }
}
