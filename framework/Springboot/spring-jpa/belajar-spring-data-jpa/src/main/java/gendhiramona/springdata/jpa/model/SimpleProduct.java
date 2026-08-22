package gendhiramona.springdata.jpa.model;

// by using interface Spring Data will uuse Proxy(Reflection)
// but if you using Java 17+ you can use record so the instance auto generated
public record SimpleProduct(Long id, String name) {

//    Long getId();
//
//    String getName();
}
