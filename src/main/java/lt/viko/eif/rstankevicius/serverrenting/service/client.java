package lt.viko.eif.rstankevicius.serverrenting.service;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Receives messages (.xml files) from the {@link lt.viko.eif.rstankevicius.serverrenting.service.server } class, generates "received.xml" with the contents of the received message.
 * Messages are received using ActiveMQ.
 * This class has a main method.
 *
 * @author Rokas Stankevičius
 * @since 1.0
 */

public class client {
    private static final String QUEUE_NAME = "MY_QUEUE1";

    public static void main(String[] args) throws JMSException, IOException { //throws JMSException

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(QUEUE_NAME);

        MessageConsumer consumer = session.createConsumer(destination);

        Message message = consumer.receive();

        if(message instanceof  TextMessage){
            TextMessage textMessage = (TextMessage) message;
            Writer f = new FileWriter("received.xml");
            f.write(textMessage.getText());
            f.close();
        }

        connection.close();
    }
}
