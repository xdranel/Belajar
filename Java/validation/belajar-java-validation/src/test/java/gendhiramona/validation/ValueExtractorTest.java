package gendhiramona.validation;

import gendhiramona.validation.container.Data;
import gendhiramona.validation.container.DataInteger;
import gendhiramona.validation.container.Entry;
import org.junit.jupiter.api.Test;

public class ValueExtractorTest extends AbstractValidatorTest {

    @Test
    void testSampleData() {

        SampleData data = new SampleData();
        data.setData(new Data<>());
        data.getData().setData("  ");

        validate(data);
    }

    @Test
    void testSampleEntry() {

        SampleEntry entry = new SampleEntry();
        entry.setEntry(new Entry<>());
        entry.getEntry().setKey("  ");
        entry.getEntry().setValue("  ");

        validate(entry);
    }

    @Test
    void testSampleDataInteger() {
        SampleDataInteger data = new SampleDataInteger();
        data.setData(new DataInteger());
        data.getData().setData(0);

        validate(data);
    }
}
