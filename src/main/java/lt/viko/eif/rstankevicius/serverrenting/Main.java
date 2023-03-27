package lt.viko.eif.rstankevicius.serverrenting;

import lt.viko.eif.rstankevicius.serverrenting.model.Package;
import lt.viko.eif.rstankevicius.serverrenting.model.DedicatedServer;
import lt.viko.eif.rstankevicius.serverrenting.model.CloudServer;
import lt.viko.eif.rstankevicius.serverrenting.model.UserData;

import jakarta.xml.bind.*;
import lt.viko.eif.rstankevicius.serverrenting.service.client;
import lt.viko.eif.rstankevicius.serverrenting.service.server;
import lt.viko.eif.rstankevicius.serverrenting.util.JaxbUtil;

import javax.jms.JMSException;
import java.io.*;
import java.util.List;

/**
 * The main class.
 * This class has a main method.
 *
 * @author Rokas Stankevičius
 * @since 1.0
 */
public class Main {
    public static void main(String[] args) throws JAXBException, IOException, JMSException {
        DedicatedServer server1 = new DedicatedServer(1, 1, "AMD Ryzen™ 5 PRO 3600", "32 GB DDR4 3400","Windows Server");
        DedicatedServer server2 = new DedicatedServer(2, 2, "AMD Ryzen™ 9 PRO 3900", "128 GB DDR4 3400", "Ubuntu");
        CloudServer cloudServer1 = new CloudServer(1,2,80,"4 GB DDR4 3400");
        Package package1 = new Package(1, "Gold", (float) 169.99, List.of(server2), List.of(cloudServer1));
        Package package2 = new Package(2,"Bronze", (float) 69.99, List.of(server1),List.of(cloudServer1));
        UserData user1 = new UserData(1,"Jeff","ToBBy25",package1);
        UserData user2 = new UserData(2,"Bob","bIsTheBestb1",package2);

        JaxbUtil.transformToXML(user2);

        server.main(null);

        client.main(null);

        JaxbUtil.validateXML("received.xml");
    }
}

