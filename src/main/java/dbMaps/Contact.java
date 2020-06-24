package dbMaps;


import java.sql.Date;


public class Contact {

    private int id;
    private Date date;
    private int idContactPerson;
    private String note;


    public Contact() {
    }

    public Contact(int id, Date date, int idContactPerson, String note) {
        this.id = id;
        this.date = date;
        this.idContactPerson = idContactPerson;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdContactPerson() {
        return idContactPerson;
    }

    public void setIdContactPerson(int idContactPerson) {
        this.idContactPerson = idContactPerson;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
