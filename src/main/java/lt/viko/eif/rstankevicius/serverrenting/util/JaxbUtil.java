package lt.viko.eif.rstankevicius.serverrenting.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lt.viko.eif.rstankevicius.serverrenting.model.UserData;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Jaxb utility class.
 *
 * @author Rokas Stankevičius
 * @since 1.0
 */
public class JaxbUtil {
    public static void convertToXML(UserData user) {
        try{
            JAXBContext context = JAXBContext.newInstance(UserData.class);

            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);

            marshaller.marshal(user, System.out);
        } catch (JAXBException e) {
            throw new RuntimeException();
        }
    }

    public static void validateXML(String fileName){
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(UserData.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema employeeSchema = sf.newSchema(new File("generate.xsd"));
            jaxbUnmarshaller.setSchema(employeeSchema);
            UserData user = (UserData) jaxbUnmarshaller.unmarshal(new File(fileName));


            System.out.println("XML validated.");
            System.out.println(user);
        }
        catch (JAXBException | SAXException e)
        {
            e.printStackTrace();
        }
    }

    public static UserData transformToPOJO(String fileName) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(UserData.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Path path = Path.of(fileName);
        String xmlContent = Files.readString(path);
        //System.out.print(xmlContent);
        StringReader reader = new StringReader(xmlContent);
        UserData user = (UserData) unmarshaller.unmarshal(reader);

        return user;
    }

    public static void transformToXML(UserData user) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(UserData.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
        OutputStream os = new FileOutputStream("generate.xml");
        marshaller.marshal(user, System.out);
        marshaller.marshal(user, os);
    }
}
