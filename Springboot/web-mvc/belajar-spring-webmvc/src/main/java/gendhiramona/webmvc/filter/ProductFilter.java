package gendhiramona.webmvc.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ProductFilter {

    public String getMinPrice() {
        return "10";
    }
}
