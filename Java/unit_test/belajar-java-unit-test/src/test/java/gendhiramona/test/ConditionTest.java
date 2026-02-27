package gendhiramona.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.util.Properties;

public class ConditionTest {

    @Test
    public void testSystemProperties() {
        Properties props = System.getProperties();
        props.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    @Test
    @EnabledOnOs({OS.WINDOWS})
    public void testRunOnlyOnWindows() {
    }

    @Test
    @DisabledOnOs({OS.WINDOWS})
    public void testDisabledOnWindows() {
    }

    @Test
    @EnabledOnJre({JRE.JAVA_8})
    public void testEnabledOnJava() {
    }

    @Test
    @DisabledOnJre({JRE.JAVA_11})
    public void testDisabledOnJava() {
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_11)
    public void testEnabledOnJavaRange811 () {
    }

    @Test
    @DisabledForJreRange(min = JRE.JAVA_11, max = JRE.JAVA_15)
    void testDisabledOnJavaRange1115() {
    }

    @Test
    @EnabledIfSystemProperties({
            @EnabledIfSystemProperty(named = "java.vendor", matches = "Oracle Corporation")
    })
    void testEnabledOnJavaVendor() {
    }

    @Test
    @DisabledIfSystemProperties({
            @DisabledIfSystemProperty(named = "java.vendor", matches = "Oracle Corporation")
    })
    void testDisabledOnJavaVendor() {
    }

    @Test
    @EnabledIfEnvironmentVariables({
            @EnabledIfEnvironmentVariable(named = "PROFILE", matches = "DEV")
    })
    void testEnabledOnProfile() {
    }

    @Test
    @DisabledIfEnvironmentVariables({
            @DisabledIfEnvironmentVariable(named = "PROFILE", matches = "DEV")
    })
    void testDisabledOnProfile() {
    }
}
