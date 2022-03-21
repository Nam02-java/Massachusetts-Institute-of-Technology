package Data;

public class DataModel {
    int id , age , GPA ;
    String name , address;

    public DataModel (int id , String name , int age , String address , int GPA){
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.GPA = GPA;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "id=" + id +
                ", age=" + age +
                ", GPA=" + GPA +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGPA() {
        return GPA;
    }

    public void setGPA(int GPA) {
        this.GPA = GPA;
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
}
