package gendhiramona.test;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MockTest {

    @Test
    void testMock() {

        List<String> list = Mockito.mock(List.class);

        Mockito.when(list.get(0)).thenReturn("John");
        Mockito.when(list.get(1)).thenReturn("Jane");

        System.out.println(list.get(0));
        System.out.println(list.get(1));

        Mockito.verify(list, Mockito.times(1)).get(0);
        Mockito.verify(list, Mockito.times(1)).get(1);
    }
}
