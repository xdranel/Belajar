package gendhiramona.i18n;

import org.junit.jupiter.api.Test;

import java.util.Locale;

public class LocaleTest {

    @Test
        // Deprecated
    void testNewLocale() {
        var language = "id";
        var country = "ID";

        Locale locale = new Locale(language, country);

        System.out.println(locale.getLanguage());
        System.out.println(locale.getCountry());

        System.out.println(locale.getDisplayLanguage());
        System.out.println(locale.getDisplayCountry());
    }

    @Test
    void testNewLocaleNewerVersion() {
        var language = "id";
        var country = "ID";

        Locale localeWithBuilder = new Locale.Builder()
                .setLanguage(language)
                .setRegion(country)
                .build();

        System.out.println(localeWithBuilder.getLanguage());
        System.out.println(localeWithBuilder.getCountry());

        System.out.println(localeWithBuilder.getDisplayLanguage());
        System.out.println(localeWithBuilder.getDisplayCountry());
    }
}
