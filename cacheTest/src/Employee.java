/**
 * Created by rohini on 9/9/19.
 */
public class Employee {
    public Employee(Integer rollno,String name){
        this.rollno=rollno;
        this.name=name;

    }

    @Override
    public String toString() {
        return "Employee{" +
                "rollno=" + rollno +
                ", name='" + name + '\'' +
                '}';
    }

    Integer rollno;

    public Integer getRollno() {
        return rollno;
    }

    public void setRollno(Integer rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
}
