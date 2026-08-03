package gendhiramona.spring.core.client;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class PaymentGatewayClient {
    // Misal saja class ini punya third party library yang digunakan
    // dan tidak bisa menggunakan @Component atau dependency injection
    // maka class ini harus dibuat sebagai bean tanpa harus menggunakan @Bean hanya menggunakan @Component saja
    // maka kita harus membuat sebuah FactoryBean.
    // refers to PaymentGatewayClientFactoryBean.java, FactoryConfiguration.java
    private String endpoint;

    private String privateKey;

    private String publicKey;
}
