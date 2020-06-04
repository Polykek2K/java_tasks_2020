package lab5;

import lab5.DB.DBClass;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

public class Grid {
    public DBClass db;

    public JPanel mainPanel;
    private JTextField fieldTitle;
    private JTextField fieldPrice;
    private JButton buttonAdd;
    private JSpinner spinnerMin;
    private JSpinner spinnerMax;
    public JList<String> goodsList;
    private JCheckBox checkBoxFilter;
    private JButton buttonDelete;
    private JButton buttonUpdate;

    public Grid() {
        fieldTitle.addActionListener(fieldAction);
        fieldPrice.addActionListener(fieldAction);

        spinnerMin.addChangeListener(spinnerChanged);
        spinnerMax.addChangeListener(spinnerChanged);

        checkBoxFilter.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                spinnerMin.setEnabled(checkBoxFilter.isSelected());
                spinnerMax.setEnabled(checkBoxFilter.isSelected());
                updateList();
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fieldTitle.getText().isEmpty()) {
                    return;
                }

                if (fieldPrice.getText().isEmpty()) {
                    return;
                }

                int price;
                try {
                    price = Integer.parseInt(fieldPrice.getText());
                } catch (NumberFormatException exc) {
                    exc.printStackTrace();
                    return;
                }

                db.addItem(fieldTitle.getText(), price);
                updateList();
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = goodsList.getSelectedValue();
                if (selected != null) {
                    Scanner scanner = new Scanner(selected);
                    db.deleteItem(scanner.next());
                    updateList();
                }
            }
        });

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = goodsList.getSelectedValue();

                if (fieldPrice.getText().isEmpty()) {
                    return;
                }

                int price;
                try {
                    price = Integer.parseInt(fieldPrice.getText());
                } catch (NumberFormatException exc) {
                    exc.printStackTrace();
                    return;
                }

                if (selected != null) {
                    Scanner scanner = new Scanner(selected);
                    db.updateItem(scanner.next(), price);
                    updateList();
                }
            }
        });
    }

    public void updateList() {
        String query = "SELECT title, price FROM goods";
        if (checkBoxFilter.isSelected()) {
            int a = (int)spinnerMin.getValue();
            int b = (int)spinnerMax.getValue();
            query += " WHERE price BETWEEN " + Math.min(a, b) + " AND " + Math.max(a, b);
        }
        ResultSet rs = db.getByQuery(query);
        Vector<String> vec = new Vector<String>();
        try {
            while (rs.next()) {
                vec.add(rs.getString(1) + " : " + rs.getInt(2) + " units.");
            }
        } catch (SQLException e) {
            System.out.println("Why?");
        }
        goodsList.setListData(vec);
    }

    Action fieldAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField field = (JTextField)e.getSource();
            if (field.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Field cannot be empty!");
            } else {
                updateList();
            }
        }
    };

    ChangeListener spinnerChanged = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner spinner = (JSpinner)e.getSource();
            if ((int)spinner.getValue() < 0) {
                spinner.setValue(0);
            } else {
                updateList();
            }
        }
    };
}
