package Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.System.out;

public class PanelWest extends JPanel {
    JPanel panel = new JPanel();
    JLabel labelName, labelAge, labelAddress, labelGPA;
    JTextField textFieldName, textFieldAge, textFieldAddress, textFieldGPA;
    JButton buttonAdd, buttonDelete, buttonEdit, buttonSave;
    public static ArrayList<DataModel> arrayList_DataUser = new ArrayList<>();
    static File fileData = new File("Data.txt");
    static BufferedWriter bufferedWriter;
    static String space = ("\n");

    public PanelWest() throws IOException {
        setComponent();
        setPanel();
        setButtonAdd();
        setButtonSave();
        setButtonDelete();
        setButtonEdit();
        checkFile();
    }

    private void setComponent() {
        setLayout(new GridLayout(6, 1));
        setPreferredSize(new Dimension(450, 420));
        add(new JLabel("Name"));
        add(textFieldName = new JTextField());
        add(new JLabel("Age"));
        add(textFieldAge = new JTextField());
        add(new JLabel("Address"));
        add(textFieldAddress = new JTextField());
        add(new JLabel("GPA"));
        add(textFieldGPA = new JTextField());
        add(panel);
    }

    private void setPanel() {
        panel.setLayout(new FlowLayout(3));
        panel.add(buttonAdd = new JButton("Add"));
        panel.add(buttonEdit = new JButton("Edit"));
        panel.add(buttonDelete = new JButton("Delete"));
        panel.add(buttonSave = new JButton("Save into MySQL"));
    }

    private void setButtonAdd() {
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    arrayList_DataUser.clear();
                    readFileToArrayList();
                    arrayList_DataUser.add(new DataModel(ControllerCenter.System.checkId_user, textFieldName.getText(), Integer.parseInt(textFieldAge.getText()), textFieldAddress.getText(), Integer.parseInt(textFieldGPA.getText())));
                    PanelCenter.array2d = new Object[arrayList_DataUser.size()][5];
                    Collections.reverse(arrayList_DataUser); // reverse 1 (important line)
                    for (int i = 0; i < arrayList_DataUser.size(); i++) {
                        PanelCenter.Id = PanelCenter.array2d[i][0] = arrayList_DataUser.get(i).getId();
                        PanelCenter.Name = PanelCenter.array2d[i][1] = arrayList_DataUser.get(i).getName();
                        PanelCenter.Age = PanelCenter.array2d[i][2] = arrayList_DataUser.get(i).getAge();
                        PanelCenter.Address = PanelCenter.array2d[i][3] = arrayList_DataUser.get(i).getAddress();
                        PanelCenter.GPA = PanelCenter.array2d[i][4] = arrayList_DataUser.get(i).getGPA();
                        PanelCenter.row = new Object[]{PanelCenter.Id, PanelCenter.Name, PanelCenter.Age, PanelCenter.Address, PanelCenter.GPA};
                        PanelCenter.model.addRow(PanelCenter.row);
                        textFieldName.setText("");
                        textFieldAge.setText("");
                        textFieldAddress.setText("");
                        textFieldGPA.setText("");
                        Collections.reverse(arrayList_DataUser); // reverse 2 (important line)
                        writeToFile();
                        break;
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đúng dữ liệu và ko đc để ô trống", "Title", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void setButtonSave() {
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ControllerCenter.System.Save_System_MySQL();
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    private void setButtonDelete() {
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Data.PanelCenter.model.removeRow(Data.PanelCenter.table.getSelectedRow());
            }
        });
    }

    private void setButtonEdit() {
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Data.PanelCenter.table.setEnabled(true);
            }
        });
    }

    public static void readFileToArrayList() throws FileNotFoundException {
        Scanner scanner = new Scanner(fileData);
        while (scanner.hasNext()) {
            arrayList_DataUser.add(new DataModel(scanner.nextInt(), scanner.next(), scanner.nextInt(), scanner.next(), scanner.nextInt()));
        }
        scanner.close();
    }

    public static void writeToFile() throws IOException {
        if (PanelCenter.table.getModel().getRowCount() == 0) {
            return;
        } else if (PanelCenter.table.getModel().getRowCount() >= 1) {
            //readFileToArrayList(); // warning line
            bufferedWriter = new BufferedWriter(new FileWriter(fileData));
            for (int i = 0; i < arrayList_DataUser.size(); i++) {
                bufferedWriter.write(arrayList_DataUser.get(i).getId() + " " + arrayList_DataUser.get(i).getName() + " " + arrayList_DataUser.get(i).getAge() + " " + arrayList_DataUser.get(i).getAddress() + " " + arrayList_DataUser.get(i).getGPA() + space);
            }
            bufferedWriter.close();
        }
    }

    private void checkFile() throws IOException {
        if (fileData.exists()) {
        } else {
            fileData.createNewFile();
        }
    }

    public static void clearFile() throws IOException {
        bufferedWriter = new BufferedWriter(new FileWriter(fileData));
        bufferedWriter.write("");
        bufferedWriter.close();
    }

    public static void clearArrayList() throws IOException {
        arrayList_DataUser.clear();
    }

    public static void outputCheck() {
        for (int i = 0; i < arrayList_DataUser.size(); i++) {
            out.println(arrayList_DataUser.get(i).getId() + " " + arrayList_DataUser.get(i).getName() + " " + arrayList_DataUser.get(i).getAge() + " " + arrayList_DataUser.get(i).getAddress() + " " + arrayList_DataUser.get(i).getGPA());
        }
    }
}
