package Data;


import SignUp.SignUpView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

public class DataView extends JFrame {

    public DataView() throws IOException {
        setFrame();
        setButtonLogout();
    }

    public void setFrame() throws IOException {
        setTitle("Data");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 130, 1300, 700);
        setVisible(true);
        add(new PanelWest(), BorderLayout.WEST);
        add(new PanelCenter(), BorderLayout.CENTER);
        add(new PanelSouth(), BorderLayout.SOUTH);
    }

    private void setButtonLogout() {
        Data.PanelSouth.buttonLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login.LoginView();
                try {
                    PanelWest.clearArrayList();
                    Data.PanelWest.clearFile();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                Login.LoginView.textFieldName.setText(ControllerCenter.System.checkUser_Name);
            }
        });
    }
}

