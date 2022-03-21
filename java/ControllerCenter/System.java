package ControllerCenter;

import Login.LoginView;
import SignUp.SignUpView;
import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static java.lang.System.out;

public class System {
    public static String url = "jdbc:mysql://localhost:3306/students_data";
    public static String user = "root";
    public static String password = "123";
    private static int id_user, id, age, gpa;
    private static String query, user_name, user_password, student_name, address;
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static ArrayList<Login.LoginModel> arrayList_Login = new ArrayList<>();
    private static ArrayList<SignUp.SignUpModel> arrayList_SignUp = new ArrayList<>();
    public static int checkId_user;
    public static String checkUser_Name;
    public static Boolean check;

    public static Connection getJDBCConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void SignUp_System_MySQL() throws SQLException {
        arrayList_SignUp.clear();
        connection = getJDBCConnection();
        statement = connection.createStatement();
        query = "SELECT * FROM table_user;";
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            id_user = resultSet.getInt(1);
            user_name = resultSet.getString(2);
            user_password = resultSet.getString(3);
            arrayList_SignUp.add(new SignUp.SignUpModel(user_name, user_password));
        }
        if (arrayList_SignUp.size() == 0) {
            id_user = 1;
            query = "INSERT INTO `table_user`(`id`, `user_name`,`password`) VALUES (" + "\"" + id_user + "\", " + "\"" + SignUpView.textFieldName.getText() + "\"," + "" + "\"" + SignUpView.passwordField.getText() + "\"" + ")";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Tạo tài khoản thành công", "Title", JOptionPane.INFORMATION_MESSAGE);
            SignUpView.textFieldName.setText("");
            SignUpView.passwordField.setText("");
            arrayList_SignUp.clear();
        } else if (arrayList_SignUp.size() >= 1) {
            for (int i = 0; i < arrayList_SignUp.size(); i++) {
                if (arrayList_SignUp.get(i).getName().equals(SignUpView.textFieldName.getText())) {
                    JOptionPane.showMessageDialog(null, "Tên đã có người sử dụng", "Title", JOptionPane.INFORMATION_MESSAGE);
                    SignUpView.textFieldName.setText("");
                    arrayList_SignUp.clear();
                    return;
                }
            }
            id_user += 1;
            query = "INSERT INTO `table_user`(`id`, `user_name`,`password`) VALUES (" + "\"" + id_user + "\", " + "\"" + SignUpView.textFieldName.getText() + "\"," + "" + "\"" + SignUpView.passwordField.getText() + "\"" + ")";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Tạo tài khoản thành công", "Title", JOptionPane.INFORMATION_MESSAGE);
            SignUpView.textFieldName.setText("");
            SignUpView.passwordField.setText("");
            arrayList_SignUp.clear();
        }
        connection.close();
    }

    public static void Login_System_MySQL() throws SQLException, IOException {
        arrayList_Login.clear();
        connection = getJDBCConnection();
        statement = connection.createStatement();
        query = "SELECT * FROM table_user";
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            id_user = resultSet.getInt(1);
            user_name = resultSet.getString(2);
            user_password = resultSet.getString(3);
            arrayList_Login.add(new Login.LoginModel(id_user, user_name, user_password));
        }
        if (arrayList_Login.size() == 0) {
            JOptionPane.showMessageDialog(null, "tên đăng nhập hoặc mật khẩu không đúng", "Title", JOptionPane.INFORMATION_MESSAGE);
            check = false;
            return;
        } else if (arrayList_Login.size() >= 1) {
            for (int i = 0; i < arrayList_Login.size(); i++) {
                if (arrayList_Login.get(i).getUser_name().equals(LoginView.textFieldName.getText())) {
                    if (arrayList_Login.get(i).getUser_password().equals(LoginView.passwordField.getText())) {
                        checkId_user = arrayList_Login.get(i).getId_user();
                        checkUser_Name = arrayList_Login.get(i).getUser_name();
                        JOptionPane.showMessageDialog(null, "Đăng nhập thành công", "Title", JOptionPane.INFORMATION_MESSAGE);
                        Data.PanelWest.clearArrayList();
                        Data.PanelWest.clearFile();
                        arrayList_Login.clear();
                        check = true;
                        query = "SELECT d.id , e.student_name , e.age , e.address , e.gpa FROM students_data.table_data e INNER JOIN students_data.table_user d ON e.id = d.id HAVING d.id = " + checkId_user + ";";
                        resultSet = statement.executeQuery(query);
                        while (resultSet.next()) {
                            id_user = resultSet.getInt(1);
                            student_name = resultSet.getString(2);
                            age = resultSet.getInt(3);
                            address = resultSet.getString(4);
                            gpa = resultSet.getInt(5);
                            Data.PanelWest.arrayList_DataUser.add(new Data.DataModel(id_user, student_name, age, address, gpa));
                        }
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "tên đăng nhập hoặc mật khẩu không đúng", "Title", JOptionPane.INFORMATION_MESSAGE);
            check = false;
            return;
        }
        connection.close();
    }

    public static void Save_System_MySQL() throws IOException, SQLException {
        Data.PanelWest.clearArrayList();
        for (int row = 0; row < Data.PanelCenter.table.getModel().getRowCount(); row++) {
            int ID = (Integer) Data.PanelCenter.table.getModel().getValueAt(row, 0);
            String Name = (String) Data.PanelCenter.table.getModel().getValueAt(row, 1);
            int Age = (Integer) Data.PanelCenter.table.getModel().getValueAt(row, 2);
            String Address = (String) Data.PanelCenter.table.getModel().getValueAt(row, 3);
            int GPA = (Integer) Data.PanelCenter.table.getModel().getValueAt(row, 4);
            Data.PanelWest.arrayList_DataUser.add(new Data.DataModel(ID, Name, Age, Address, GPA));
        }
        Data.PanelWest.writeToFile(); //warning line
        connection = getJDBCConnection();
        statement = connection.createStatement();
        query = "DELETE FROM table_data WHERE id =" + checkId_user + " ;";
        statement.executeUpdate(query);
        if (Data.PanelCenter.table.getModel().getRowCount() == 0) {
            return;
        } else if (Data.PanelCenter.table.getModel().getRowCount() >= 1) {
            for (int j = 0; j < Data.PanelWest.arrayList_DataUser.size(); j++) {
                query = "INSERT INTO table_data (id, student_name,age,address,gpa) VALUES (" + checkId_user + ", " + "\"" + Data.PanelWest.arrayList_DataUser.get(j).getName() + "\"" + ", " + Data.PanelWest.arrayList_DataUser.get(j).getAge() + ", " + "\"" + Data.PanelWest.arrayList_DataUser.get(j).getAddress() + "\"" + ", " + Data.PanelWest.arrayList_DataUser.get(j).getGPA() + ");";
                statement.executeUpdate(query);
            }
        }
        Data.PanelCenter.table.setEnabled(false);
        connection.close();
    }

    public static void Display_System_MySQL() throws IOException, SQLException {
        Data.PanelWest.clearArrayList();
        Data.PanelWest.clearFile();
        Data.PanelCenter.clearTable_Before_Data_Into();
        connection = getJDBCConnection();
        statement = connection.createStatement();
        query = "SELECT d.id , e.student_name , e.age , e.address , e.gpa FROM students_data.table_data e INNER JOIN students_data.table_user d ON e.id = d.id HAVING d.id = " + checkId_user + ";";
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            id_user = resultSet.getInt(1);
            student_name = resultSet.getString(2);
            age = resultSet.getInt(3);
            address = resultSet.getString(4);
            gpa = resultSet.getInt(5);
            Data.PanelWest.arrayList_DataUser.add(new Data.DataModel(id_user, student_name, age, address, gpa));
        }
        connection.close();
    }
}

