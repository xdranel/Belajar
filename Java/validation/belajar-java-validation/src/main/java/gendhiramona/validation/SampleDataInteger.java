package gendhiramona.validation;

import gendhiramona.validation.container.DataInteger;
import jakarta.validation.constraints.Min;

public class SampleDataInteger {

    @Min(value = 10)
    private DataInteger data;

    public DataInteger getData() {
        return data;
    }

    public void setData(DataInteger data) {
        this.data = data;
    }
}
