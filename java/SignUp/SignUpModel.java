package SignUp;

public class SignUpModel {
    String name , password;

    public SignUpModel(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
