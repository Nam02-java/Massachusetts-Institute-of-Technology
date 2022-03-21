package Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.ArrayList;

public class PanelCenter extends JPanel {
    static DefaultTableModel model;
    public static JTable table;
    static String[] column;
    static Object[][] array2d;
    static Object[] row;
    static Object Id, Name, Age, Address, GPA;

    public PanelCenter() throws IOException {
        setComponent();
        display_All_Data_After_Login();
    }

    private void setComponent() {
        column = new String[]{"ID_Teacher", "Student_Name", "Student_Age", "Student_City", "Student_GPA"};
        model = new DefaultTableModel(array2d, column);
        table = new JTable(model);
        add(new JScrollPane(table));
        table.setEnabled(false);
    }

    public static void clearTable_Before_Data_Into() {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public static void display_All_Data_After_Login() throws IOException {
        clearTable_Before_Data_Into();
        array2d = new Object[Data.PanelWest.arrayList_DataUser.size()][5];
        for (int i = 0; i < Data.PanelWest.arrayList_DataUser.size(); i++) {
            Id = array2d[i][0] = Data.PanelWest.arrayList_DataUser.get(i).getId();
            Name = array2d[i][1] = Data.PanelWest.arrayList_DataUser.get(i).getName();
            Age = array2d[i][2] = Data.PanelWest.arrayList_DataUser.get(i).getAge();
            Address = array2d[i][3] = Data.PanelWest.arrayList_DataUser.get(i).getAddress();
            GPA = array2d[i][4] = Data.PanelWest.arrayList_DataUser.get(i).getGPA();
            row = new Object[]{Id, Name, Age, Address, GPA};
            model.addRow(row);
            Data.PanelWest.writeToFile();
        }
    }
}
