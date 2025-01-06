import org.apache.pulsar.client.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleConsumer.class);

    public static void main(String[] args) {
        try (PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://127.0.0.1:56726")
                .build();
             Consumer<String> consumer = client.newConsumer(Schema.STRING)
                     .topic("apache/pulsar/test-topic")
                     .subscriptionName("hello")
                     .subscriptionType(SubscriptionType.Exclusive)
                     .subscribe()) {

            Message<String> message = consumer.receive();
            LOGGER.info("Received message: {}", message.getValue());

        } catch (PulsarClientException e) {
            throw new RuntimeException(e);
        }
    }
}
