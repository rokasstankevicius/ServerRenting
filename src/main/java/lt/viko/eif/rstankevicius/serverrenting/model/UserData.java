package lt.viko.eif.rstankevicius.serverrenting.model;

import jakarta.xml.bind.annotation.*;

import javax.persistence.*;

/**
 * Holds the information of a user and contains their package.
 *
 * @author Rokas Stankevičius
 * @since 1.0
 */

@XmlRootElement ( name = "UserData" )
@XmlType ( propOrder = { "id", "username", "password", "userPackage" } )
@XmlAccessorType (XmlAccessType.FIELD)
@Entity
@Table( name = "userdata" )
public class UserData {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private int id;
    private String username;
    private String password;

    @OneToOne( targetEntity = Package.class,cascade = CascadeType.ALL )
    private Package userPackage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Package getUserPackage() {
        return userPackage;
    }

    public void setUserPackage(Package userPackage) {
        this.userPackage = userPackage;
    }

    public UserData() {

    }

    public UserData(int id, String username, String password, Package userPackage) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userPackage = userPackage;
    }

    public UserData(String username, String password, Package userPackage) {
        this.username = username;
        this.password = password;
        this.userPackage = userPackage;
    }

    @Override
    public String toString() {
        return String.format("User:\n\t" +
                        "Username = %s\n\t" +
                        "Password = %s\n" +
                        "\tPackage:\n%s",
                this.username,
                this.password,
                this.userPackage);
    }
}
