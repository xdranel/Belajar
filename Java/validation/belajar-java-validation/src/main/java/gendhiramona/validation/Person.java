package gendhiramona.validation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class Person {

    @NotBlank(message = "{person.firstName.notblank}")
    @Size(max = 20, message = "{person.firstName.size}")
    private String firstName;

    @NotBlank(message = "{person.lastName.notblank}")
    @Size(max = 20, message = "{person.lastName.size}")
    private String lastName;

    @NotNull(message = "{person.address.notnull}")
    @Valid
    private Address address;

    private List<@NotBlank(message = "{person.hobbies.notblank}") String> hobbies;

    @Valid
    public Person() {
    }

    @Valid
    public Person(@NotBlank(message = "{person.firstName.constructor.notblank}") String firstName,
                  @NotBlank(message = "{person.lastName.constructor.notblank}") String lastName,
                  @NotNull(message = "{person.address.constructor.notnull}") @Valid Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public void sayHello(@NotBlank(message = "{person.sayHello.name.notblank}") String name) {
        System.out.println("Hello " + name + ", my name is " + firstName);
    }

    @NotBlank(message = "{person.fullName.notblank}")
    public String fullName() {
        return firstName + " " + lastName;
    }

    ;
}
