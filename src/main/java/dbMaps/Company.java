package dbMaps;


public class Company {

    private String tin;
    private String name;
    private String address;


    public Company() {
    }

    public Company(String tin, String name, String address) {
        this.tin = tin;
        this.name = name;
        this.address = address;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return tin + ", " + name + ", " + address;
    }
}
