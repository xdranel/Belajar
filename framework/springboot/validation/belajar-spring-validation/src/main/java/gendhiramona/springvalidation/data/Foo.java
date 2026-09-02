package gendhiramona.springvalidation.data;

import gendhiramona.springvalidation.validation.Palindrome;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Foo {

    @Palindrome
    private String bar;
}
