package dbMaps;


public class Person {


    private String tin;
    private int id;
    private int employee;
    private String name;
    private String last;
    private String email;
    private String phone;


    public Person(int id, String tin, String name, String last, String email, String phone) {
        this.id = id;
        this.tin = tin;
        this.employee = employee;
        this.name = name;
        this.last = last;
        this.email = email;
        this.phone = phone;
    }

    public String getTin() {
        return tin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name + " " + last + " " + phone + " " + email;
    }
}
