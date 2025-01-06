import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;

import java.util.logging.Logger;

public class SampleProducer {
    private static final Logger LOGGER = Logger.getLogger(SampleProducer.class.getName());
    public static void main(String[] args) {
        try (PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://127.0.0.1:56726")
                .build();
             Producer<String> stringProducer = client.newProducer(Schema.STRING)
                     .topic("apache/pulsar/test-topic")
                     .create()) {

            stringProducer.send("My message");
            LOGGER.info("Sent message");

        } catch (PulsarClientException e) {
            throw new RuntimeException(e);
        }

    }
}
