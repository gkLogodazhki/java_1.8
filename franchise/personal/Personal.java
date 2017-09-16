package franchise.personal;

import franchise.Franchise;
import org.jetbrains.annotations.NotNull;

public abstract class Personal implements IPersonal, Comparable<Personal>{

    public double getSalary() {
        calculateSalary();
        return this.salary;
    }

    protected void setSalary(double salary){
        if (salary > 0){
            this.salary = salary;
        }
    }
    public enum PersonalType{
        WAITER,MANAGER
    }
    public interface IPersonalKind{
        enum Waiter implements IPersonalKind{
            JUNIOR, MEDIUM, SENIOR
        }

        enum Manager implements IPersonalKind{
            SHIP_MANAGER, REGIONAL_MANAGER
        }
    }

    private String name;
    private PersonalType type;
    private IPersonalKind kind;
    private double salary;
    private double percent;
    private Franchise franchise;

    public Personal(String name,PersonalType type, IPersonalKind kind, double salary,double percent) {
        this.name = (!"".equals(name)) ? name : "No name";
        this.type = type;
        this.kind = kind;
        this.salary = (salary > 0) ? salary : 0;
        this.percent = (percent >=0 && percent <= 1) ? percent : 0;
    }
    public void setFranchise(Franchise franchise){
        this.franchise = franchise;
    }

    public void decreaseSalary(int money){
        if (this.salary >= money){
            this.salary -= money;
        }
    }

    public String getName() { return this.name; }
    public PersonalType getType() { return this.type; }
    public IPersonalKind getKind(){
        return this.kind;
    }
    public Franchise getFranchise(){
        return this.franchise;
    }


    @Override
    public int compareTo(@NotNull Personal o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public void increaseSalary(double percent) {
        if (percent >= 0 && percent <= 1){
            calculateSalary();
            this.salary +=  this.salary*percent;
        }
    }

    @Override
    public void calculateSalary(){
        this.salary += this.getRev()*this.percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personal personal = (Personal) o;

        return name != null ? name.equals(personal.name) : personal.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        calculateSalary();
        return "Personal{" + "name='" + name + ", kind=" + kind + ", salary=" + salary + '}';
    }
}
