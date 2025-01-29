import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CollectionPanel extends JPanel {
    public void newCollection() {
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        String greenTextColor = "#33FF00";

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setForeground(Color.decode(greenTextColor));
        JLabel nameOfOwnerLabel = new JLabel("Owner's name:");
        nameOfOwnerLabel.setForeground(Color.decode(greenTextColor));

        JTextArea titleInput = new JTextArea();
        titleInput.setBorder(new LineBorder(Color.decode(greenTextColor)));
        titleInput.setBackground(Color.BLACK);
        titleInput.setForeground(Color.decode(greenTextColor));
        titleInput.setLineWrap(true);

        JTextArea nameOfOwnerInput = new JTextArea();
        nameOfOwnerInput.setBorder(new LineBorder(Color.decode(greenTextColor)));
        nameOfOwnerInput.setBackground(Color.BLACK);
        nameOfOwnerInput.setForeground(Color.decode(greenTextColor));
        nameOfOwnerInput.setLineWrap(true);

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (titleInput.getText().equals("") && nameOfOwnerInput.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Input field is empty.");
                    return;
                }

                CollectionORM.newCollection(titleInput.getText(), nameOfOwnerInput.getText());

                //Close panel
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        this.add(titleLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        this.add(nameOfOwnerLabel, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(1,5, 1, 1);

        this.add(titleInput, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(1,5, 1, 1);

        this.add(nameOfOwnerInput, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(5,5, 1, 1);

        this.add(submit, gbc);

    }

    public void updateCollection(int id, String title, String nameOfOwner) {
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        String greenTextColor = "#33FF00";

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setForeground(Color.decode(greenTextColor));
        JLabel nameOfOwnerLabel = new JLabel("Owner's name:");
        nameOfOwnerLabel.setForeground(Color.decode(greenTextColor));

        JTextArea titleInput = new JTextArea();
        titleInput.setBorder(new LineBorder(Color.decode(greenTextColor)));
        titleInput.setBackground(Color.BLACK);
        titleInput.setForeground(Color.decode(greenTextColor));
        titleInput.setText(title);
        titleInput.setLineWrap(true);

        JTextArea nameOfOwnerInput = new JTextArea();
        nameOfOwnerInput.setBorder(new LineBorder(Color.decode(greenTextColor)));
        nameOfOwnerInput.setBackground(Color.BLACK);
        nameOfOwnerInput.setForeground(Color.decode(greenTextColor));
        nameOfOwnerInput.setText(nameOfOwner);
        nameOfOwnerInput.setLineWrap(true);

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (titleInput.getText().equals("") && nameOfOwnerInput.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Input field is empty.");
                    return;
                }

                CollectionORM.updateCollection(id, titleInput.getText(), nameOfOwnerInput.getText());

                //Close panel
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        this.add(titleLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        this.add(nameOfOwnerLabel, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(1,5, 1, 1);

        this.add(titleInput, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(1,5, 1, 1);

        this.add(nameOfOwnerInput, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(5,5, 1, 1);

        this.add(submit, gbc);
    }
}
