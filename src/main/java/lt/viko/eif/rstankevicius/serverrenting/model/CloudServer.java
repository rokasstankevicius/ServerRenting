package lt.viko.eif.rstankevicius.serverrenting.model;

import jakarta.xml.bind.annotation.XmlType;

import javax.persistence.*;


/**
 * Holds the information of a cloud server.
 *
 * @author Rokas Stankevičius
 * @since 1.0
 */
@Entity
@XmlType( propOrder = { "id", "vCores", "storageSpace", "ram", } )
@Table( name = "cloudserver" )
public class CloudServer {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private int id;

    private int vCores;
    private int storageSpace;
    private String ram;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStorageSpace() {
        return storageSpace;
    }

    public void setStorageSpace(int storageSpace) {
        this.storageSpace = storageSpace;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public int getvCores() {
        return vCores;
    }

    public void setvCores(int vCores) {
        this.vCores = vCores;
    }

    public CloudServer() {
    }

    public CloudServer(int id, int vCores, int storageSpace, String ram) {
        this.id = id;
        this.vCores = vCores;
        this.storageSpace = storageSpace;
        this.ram = ram;
    }

    public CloudServer(int vCores, int storageSpace, String ram) {
        this.vCores = vCores;
        this.storageSpace = storageSpace;
        this.ram = ram;
    }

    @Override
    public String toString() {
        return String.format(
                "\t\t\tCloud Server: \n" +
                        "\t\t\t\tvCores = %s\n\t" +
                        "\t\t\tRAM = %s\n" +
                        "\t\t\t\tStorage = %s GB\n",
                this.vCores,
                this.ram,
                this.storageSpace);
    }
}
