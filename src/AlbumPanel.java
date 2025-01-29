import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;

public class AlbumPanel extends JPanel {
    public void newAlbum() {
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        String greenTextColor = "#33FF00";

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setForeground(Color.decode(greenTextColor));
        JLabel authorLabel = new JLabel("Author(s):");
        authorLabel.setForeground(Color.decode(greenTextColor));
        JLabel genreLabel = new JLabel("Genre:");
        genreLabel.setForeground(Color.decode(greenTextColor));
        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setForeground(Color.decode(greenTextColor));

        JTextArea titleInput = new JTextArea();
        titleInput.setBorder(new LineBorder(Color.decode(greenTextColor)));
        titleInput.setBackground(Color.BLACK);
        titleInput.setForeground(Color.decode(greenTextColor));
        titleInput.setLineWrap(true);

        JTextArea authorInput = new JTextArea();
        authorInput.setBorder(new LineBorder(Color.decode(greenTextColor)));
        authorInput.setBackground(Color.BLACK);
        authorInput.setForeground(Color.decode(greenTextColor));
        authorInput.setLineWrap(true);

        JTextArea genreInput = new JTextArea();
        genreInput.setBorder(new LineBorder(Color.decode(greenTextColor)));
        genreInput.setBackground(Color.BLACK);
        genreInput.setForeground(Color.decode(greenTextColor));
        genreInput.setLineWrap(true);

        int currentYear = Year.now().getValue();
        SpinnerModel model = new SpinnerNumberModel(currentYear, 1, currentYear, 1);
        JSpinner yearSpin = new JSpinner(model);
        yearSpin.setBorder(new LineBorder(Color.decode(greenTextColor)));
        yearSpin.getEditor().getComponent(0).setBackground(Color.BLACK);
        yearSpin.getEditor().getComponent(0).setForeground(Color.decode(greenTextColor));

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (titleInput.getText().equals("") && authorInput.getText().equals("") && genreInput.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Input field is empty.");
                    return;
                }

                // From Object to String
                String year = "" + yearSpin.getValue();
                // From String o int
                int yearInt = Integer.parseInt(year);
                

                AlbumORM.newAlbum(titleInput.getText(), authorInput.getText(), genreInput.getText(), yearInt);

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

        this.add(authorLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;

        this.add(genreLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;

        this.add(yearLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(1,5, 1, 1);

        this.add(titleInput, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(1,5, 1, 1);

        this.add(authorInput, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(1,5, 1, 1);

        this.add(genreInput, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(1,5, 1, 1);

        this.add(yearSpin, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(5,5, 1, 1);

        this.add(submit, gbc);

    }

    public void updateAlbum(int id, String title, String author, String genre, int year) {
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        String greenTextColor = "#33FF00";

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setForeground(Color.decode(greenTextColor));
        JLabel authorLabel = new JLabel("Author(s):");
        authorLabel.setForeground(Color.decode(greenTextColor));
        JLabel genreLabel = new JLabel("Genre:");
        genreLabel.setForeground(Color.decode(greenTextColor));
        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setForeground(Color.decode(greenTextColor));

        JTextArea titleInput = new JTextArea();
        titleInput.setBorder(new LineBorder(Color.decode(greenTextColor)));
        titleInput.setBackground(Color.BLACK);
        titleInput.setForeground(Color.decode(greenTextColor));
        titleInput.setText(title);
        titleInput.setLineWrap(true);

        JTextArea authorInput = new JTextArea();
        authorInput.setBorder(new LineBorder(Color.decode(greenTextColor)));
        authorInput.setBackground(Color.BLACK);
        authorInput.setForeground(Color.decode(greenTextColor));
        authorInput.setText(author);
        authorInput.setLineWrap(true);

        JTextArea genreInput = new JTextArea();
        genreInput.setBorder(new LineBorder(Color.decode(greenTextColor)));
        genreInput.setBackground(Color.BLACK);
        genreInput.setForeground(Color.decode(greenTextColor));
        genreInput.setText(genre);
        genreInput.setLineWrap(true);

        int currentYear = Year.now().getValue();
        SpinnerModel model = new SpinnerNumberModel(currentYear, 1, currentYear, 1);
        JSpinner yearSpin = new JSpinner(model);
        yearSpin.setBorder(new LineBorder(Color.decode(greenTextColor)));
        yearSpin.getEditor().getComponent(0).setBackground(Color.BLACK);
        yearSpin.getEditor().getComponent(0).setForeground(Color.decode(greenTextColor));
        yearSpin.setValue(year);


        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (titleInput.getText().equals("") && authorInput.getText().equals("") && genreInput.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Input field is empty.");
                    return;
                }

                // From Object to String
                String year = "" + yearSpin.getValue();
                // From String o int
                int yearInt = Integer.parseInt(year);

                AlbumORM.updateAlbum(id, titleInput.getText(), authorInput.getText(), genreInput.getText(), yearInt);

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

        this.add(authorLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;

        this.add(genreLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;

        this.add(yearLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(1,5, 1, 1);

        this.add(titleInput, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(1,5, 1, 1);

        this.add(authorInput, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(1,5, 1, 1);

        this.add(genreInput, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(1,5, 1, 1);

        this.add(yearSpin, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(5,5, 1, 1);

        this.add(submit, gbc);
    }
}
