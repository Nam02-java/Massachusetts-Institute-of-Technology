package SignUp;

import Login.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static java.lang.System.out;


public class SignUpView extends JFrame {

    static public JTextField textFieldName;
    static public JPasswordField passwordField;
    static public JLabel labelUserName, labelPassword, labelLogo;
    static public JButton buttonRegister, buttonPrevious;
    static public ImageIcon imageIcon;

    public SignUpView() {
        setFrame();
        setButtonRegister();
        setButtonPrevious();
    }

    private void setFrame() {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(1350, 300, 300, 400);
        getContentPane().setBackground(Color.darkGray);
        setLayout(null);
        setVisible(true);
        labelLogo = new JLabel("Create Account");
        labelLogo.setFont(new Font("MV Boli", Font.PLAIN, 22));
        labelLogo.setForeground(Color.WHITE);
        labelLogo.setBounds(60, 30, 170, 50);
        textFieldName = new JTextField();
        textFieldName.setBounds(60, 100, 170, 30);
        textFieldName.setBackground(Color.black);
        textFieldName.setCaretColor(Color.WHITE);
        textFieldName.setForeground(Color.WHITE);
        labelUserName = new JLabel();
        labelUserName.setIcon(imageIcon = new ImageIcon("C:\\Users\\User\\Downloads\\icons8-user-24.png"));
        labelUserName.setBounds(10, 100, 24, 24);
        passwordField = new JPasswordField();
        passwordField.setBounds(60, 145, 170, 30);
        passwordField.setBackground(Color.black);
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setForeground(Color.WHITE);
        labelPassword = new JLabel();
        labelPassword.setIcon(imageIcon = new ImageIcon("C:\\Users\\User\\Downloads\\icons8-password-24.png"));
        labelPassword.setBounds(10, 147, 24, 24);
        buttonRegister = new JButton("Register");
        buttonRegister.setBounds(90, 200, 100, 25);
        buttonRegister.setForeground(Color.WHITE);
        buttonRegister.setBackground(Color.BLACK);
        buttonPrevious = new JButton("Previous");
        buttonPrevious.setBounds(15, 320, 100, 25);
        buttonPrevious.setForeground(Color.WHITE);
        buttonPrevious.setBackground(Color.BLACK);
        add(labelLogo);
        add(textFieldName);
        add(labelUserName);
        add(passwordField);
        add(labelPassword);
        add(buttonRegister);
        add(buttonPrevious);
    }

    private void setButtonPrevious() {
        buttonPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new LoginView();
            }
        });
    }

    private void setButtonRegister() {
        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldName.getText().equals("") && passwordField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Không được để ô trống", "Title", JOptionPane.INFORMATION_MESSAGE);
                } else if (textFieldName.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Không được để ô tên trống", "Title", JOptionPane.INFORMATION_MESSAGE);
                } else if (passwordField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Không được để ô mật khẩu trống", "Title", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        ControllerCenter.System.SignUp_System_MySQL();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }
}
