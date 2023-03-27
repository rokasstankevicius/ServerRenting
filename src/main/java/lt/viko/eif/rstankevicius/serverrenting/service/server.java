package lt.viko.eif.rstankevicius.serverrenting.service;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.BlobMessage;

import javax.jms.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Sends messages (.xml files) to the {@link lt.viko.eif.rstankevicius.serverrenting.service.client } class, a message is the content of the "generated.xml" file.
 * Messages are sent using ActiveMQ.
 * This class has a main method.
 *
 * @author Rokas Stankevičius
 * @since 1.0
 */
public class server {
    private static final String QUEUE_NAME = "MY_QUEUE1";

    public static void main(String[] args) throws JMSException, IOException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(QUEUE_NAME);

        MessageProducer producer = session.createProducer(destination);

        Path path = Path.of("generate.xml");
        String xmlContent = Files.readString(path);

        TextMessage message = session.createTextMessage(xmlContent);
        producer.send(message);

        connection.close();
    }
}
