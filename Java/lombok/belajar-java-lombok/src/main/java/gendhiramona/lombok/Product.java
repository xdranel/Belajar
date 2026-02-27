package gendhiramona.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {
        "price"
})
public class Product {

    private final String id;

    private String name;

    private Long price;
}
