package lt.viko.eif.rstankevicius.serverrenting;

import lt.viko.eif.rstankevicius.serverrenting.model.CloudServer;
import lt.viko.eif.rstankevicius.serverrenting.model.Package;
import lt.viko.eif.rstankevicius.serverrenting.model.DedicatedServer;
import lt.viko.eif.rstankevicius.serverrenting.model.UserData;
import lt.viko.eif.rstankevicius.serverrenting.util.HibernateUtil;
import lt.viko.eif.rstankevicius.serverrenting.util.JaxbUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
/**
 * Send POJOs converted into XML to a database.
 * This class has a main method.
 * This class uses the Hibernate framework.
 *
 * @author Rokas Stankevičius
 * @since 1.0
 */
public class HibernateApp {
    public static void main(String[] args){
        DedicatedServer server1 = new DedicatedServer( 1, "AMD Ryzen™ 5 PRO 3600", "32 GB DDR4 3400","Windows Server");
        DedicatedServer server2 = new DedicatedServer( 2, "AMD Ryzen™ 9 PRO 3900", "128 GB DDR4 3400", "Ubuntu");
        CloudServer cloudServer1 = new CloudServer(2,80,"4 GB DDR4 3400");
        Package package1 = new Package( "Gold", (float) 169.99, List.of(server2), List.of(cloudServer1));
        Package package2 = new Package("Bronze", (float) 69.99, List.of(server1),List.of(cloudServer1));
        UserData user1 = new UserData("Jeff","ToBBy25",package1);
        UserData user2 = new UserData("Bob","bIsTheBestb1",package2);

        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(user1);
            transaction.commit();
        }catch(Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            List<UserData> users = session.createQuery("from UserData", UserData.class).list();
            users.forEach(usr -> System.out.println(usr));
            System.out.println("______________________________");
            users.forEach(usr -> JaxbUtil.convertToXML(usr));

            System.in.read();
        } catch (Exception e){
            if(transaction != null){
                System.out.println(e.getMessage());
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            //server.shutdown();
        }
    }

}
