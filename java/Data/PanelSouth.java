package Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.System.out;

public class PanelSouth extends JPanel {

    public static JButton buttonLogout, buttonDisplay, button_Sort_By_GPA, button_Sort_By_Name;
    JPanel panel = new JPanel();

    public PanelSouth() {
        setComponent();
        setPanel();
        setButtonDisplay();
        setButton_Sort_By_GPA();
        setButton_Sort_By_Name();
    }

    private void setComponent() {
        add(panel);
    }

    private void setPanel() {
        panel.setLayout(new FlowLayout(1));
        panel.add(buttonLogout = new JButton("Log out"));
        panel.add(buttonDisplay = new JButton("Display"));
        panel.add(button_Sort_By_GPA = new JButton("GPA sort"));
        panel.add(button_Sort_By_Name = new JButton("Name sort"));
    }


    //hàm này chuyển sang class DataView vì ko đáp ứng đủ nhu cầu ở class PanelSouth
    private void setButtonLogout() {
    }

    private void setButtonDisplay() {
        buttonDisplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ControllerCenter.System.Display_System_MySQL();
                    Data.PanelCenter.display_All_Data_After_Login();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    private void setButton_Sort_By_GPA() {
        button_Sort_By_GPA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(Data.PanelWest.arrayList_DataUser, new Comparator<DataModel>() {
                    @Override
                    public int compare(DataModel o1, DataModel o2) {
                        if (o1.getGPA() - o2.getGPA() > 0) {
                            return 1;
                        } else if (o1.getGPA() - o2.getGPA() < 0) {
                            return -1;
                        } else {
                            if (o1.getName().compareTo(o2.getName()) > 0) {
                                return 1;
                            } else if (o1.getName().compareTo(o2.getName()) < 0) {
                                return -1;
                            } else {
                                if (o1.getAge() - o2.getAge() > 0) {
                                    return 1;
                                } else if (o1.getAge() - o2.getAge() < 0) {
                                    return -1;
                                } else {
                                    if (o1.getAddress().compareTo(o2.getAddress()) > 0) {
                                        return 1;
                                    } else if (o1.getAddress().compareTo(o2.getAddress()) < 0) {
                                        return -1;
                                    } else {
                                        return 0;
                                    }
                                }
                            }
                        }
                    }
                });
                try {
                    PanelCenter.display_All_Data_After_Login();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    private void setButton_Sort_By_Name() {
        button_Sort_By_Name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(Data.PanelWest.arrayList_DataUser, new Comparator<DataModel>() {
                    @Override
                    public int compare(DataModel o1, DataModel o2) {
                        if (o1.getName().compareTo(o2.getName()) > 0) {
                            return 1;
                        } else if (o1.getName().compareTo(o2.getName()) < 0) {
                            return -1;
                        } else {
                            if (o1.getGPA() - o2.getGPA() > 0) {
                                return 1;
                            } else if (o1.getGPA() - o2.getGPA() < 0) {
                                return -1;
                            } else {
                                if (o1.getAge() - o2.getAge() > 0) {
                                    return 1;
                                } else if (o1.getAge() - o2.getAge() < 0) {
                                    return -1;
                                } else {
                                    if (o1.getAddress().compareTo(o2.getAddress()) > 0) {
                                        return 1;
                                    } else if (o1.getAddress().compareTo(o2.getAddress()) < 0) {
                                        return -1;
                                    } else {
                                        return 0;
                                    }
                                }
                            }
                        }
                    }
                });
                try {
                    Data.PanelCenter.display_All_Data_After_Login();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}
