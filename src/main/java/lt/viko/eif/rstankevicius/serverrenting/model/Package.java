package lt.viko.eif.rstankevicius.serverrenting.model;

import jakarta.xml.bind.annotation.XmlType;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Holds the information of a product package, contains the lists of the dedicated servers and cloud servers.
 *
 * @author Rokas Stankevičius
 * @since 1.0
 */
@Entity
@XmlType( propOrder = { "id", "name", "price", "dedicatedServers", "cloudServers" } )
@Table( name = "package" )
public class Package {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private int id;
    private String name;
    private Float price;

    @XmlElementWrapper( name = "dedicatedserver" ) //
    @XmlElement( name = "dedicatedserver" )
    @OneToMany( targetEntity = DedicatedServer.class,cascade = CascadeType.ALL )
    private List<DedicatedServer> dedicatedServers;

    @XmlElementWrapper( name = "cloudserver" ) //
    @XmlElement( name = "cloudserver" )
    @OneToMany( targetEntity = CloudServer.class,cascade = CascadeType.ALL )
    private List<CloudServer> cloudServers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<DedicatedServer> getDedicatedServers() {
        return dedicatedServers;
    }

    public void setDedicatedServers(List<DedicatedServer> servers) {
        this.dedicatedServers = servers;
    }

    public List<CloudServer> getCloudServers() {
        return cloudServers;
    }

    public void setCloudServers(List<CloudServer> cloudServers) {
        this.cloudServers = cloudServers;
    }

    public Package() {
    }


    public Package(int id, String name, Float price, List<DedicatedServer> dedicatedServers, List<CloudServer> cloudServers) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.dedicatedServers = dedicatedServers;
        this.cloudServers = cloudServers;
    }

    public Package(String name, Float price, List<DedicatedServer> dedicatedServers, List<CloudServer> cloudServers) {
        this.name = name;
        this.price = price;
        this.dedicatedServers = dedicatedServers;
        this.cloudServers = cloudServers;
    }

    private String constructServerString(){
        String result = "";
        if(this.dedicatedServers != null)
            for (DedicatedServer server : this.dedicatedServers){
                result += server.toString();
            }
        if(this.cloudServers != null)
            for (CloudServer server : this.cloudServers){
                result += server.toString();
            }
        return result;
    }

    @Override
    public String toString() {
        return String.format("\t\tName = %s\n\t\t" +
                             "Price = %s\n" +
                             "\t\tServers: \n%s",
                this.name,
                this.price,
                constructServerString());
    }
}
