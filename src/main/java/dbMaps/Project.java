package dbMaps;


import java.io.Serializable;


public class Project implements Serializable {

    private int id;
    private float budget;
    private String name;


    public Project() {
    }

    public Project(int id, String name, float budget) {
        this.name = name;
        this.id = id;
        this.budget = budget;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

}
