/**
 * Created by rohini on 10/9/19.
 */
public class Company {

    public Company(String name,Integer noOfEmployees,String type,String founder){
        this.name=name;
        this.noOfEmployees=noOfEmployees;
        this.type=type;
        this.founder=founder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", noOfEmployees=" + noOfEmployees +
                ", type='" + type + '\'' +
                ", founder='" + founder + '\'' +
                '}';
    }

    public Integer getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(Integer noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    String name;
    Integer noOfEmployees;
    String type;
    String founder;

}
