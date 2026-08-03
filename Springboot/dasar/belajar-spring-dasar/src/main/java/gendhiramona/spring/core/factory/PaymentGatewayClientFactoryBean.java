package gendhiramona.spring.core.factory;

import gendhiramona.spring.core.client.PaymentGatewayClient;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component("paymentGatewayClient")
public class PaymentGatewayClientFactoryBean implements FactoryBean<PaymentGatewayClient> {

    // Juga bisa ditambahkan sebuah dependency injection
    // seperti constructor, setter, field, atau method.
    // apapun yang bisa di @Component bisa juga di FactoryBean
    // yang membedakan adalah objectnya jika @Component objectnya class itu sendiri
    // jika FactoryBean objectnya adalah object yang dibuat melalui getObject()

    @Nullable
    @Override
    public PaymentGatewayClient getObject() throws Exception {
        PaymentGatewayClient client = new PaymentGatewayClient();
        client.setEndpoint("https://example.com/api/v1/payment");
        client.setPrivateKey("private");
        client.setPublicKey("public");
        return client;
    }

    @Nullable
    @Override
    public Class<?> getObjectType() {
        return PaymentGatewayClient.class;
    }
}
