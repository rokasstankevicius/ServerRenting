package lt.viko.eif.rstankevicius.serverrenting.model;

import jakarta.xml.bind.annotation.XmlType;

import javax.persistence.*;

/**
 * Holds the information of a dedicated server.
 *
 * @author Rokas Stankevičius
 * @since 1.0
 */
@Entity
@XmlType( propOrder = { "id", "storageSpace", "cpu", "ram", "os" } )
@Table( name = "dedicatedserver" )
public class DedicatedServer {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private int id;
    private int storageSpace;
    private String cpu;
    private String ram;

    private String os;

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

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public DedicatedServer() {
    }

    public DedicatedServer(int id, int storageSpace, String cpu, String ram, String os) {
        this.id = id;
        this.storageSpace = storageSpace;
        this.os = os;
        this.cpu = cpu;
        this.ram = ram;
    }

    public DedicatedServer(int storageSpace, String cpu, String ram, String os) {
        this.storageSpace = storageSpace;
        this.os = os;
        this.cpu = cpu;
        this.ram = ram;
    }

    @Override
    public String toString() {
        return String.format(
                "\t\t\tDedicated Server: \n" +
                        "\t\t\t\tCPU = %s\n\t" +
                        "\t\t\tRAM = %s\n" +
                        "\t\t\t\tOperating System = %s\n" +
                                "\t\t\t\tStorage = %s TB\n",
                this.cpu,
                this.ram,
                this.os,
                this.storageSpace);
    }
}
