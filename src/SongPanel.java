import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SongPanel extends JPanel {
    public void newSong(int albumId) {
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        String greenTextColor = "#33FF00";

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setForeground(Color.decode(greenTextColor));
        JLabel durationMinutesLabel = new JLabel("Duration Minutes:");
        durationMinutesLabel.setForeground(Color.decode(greenTextColor));
        JLabel durationSecondsLabel = new JLabel("Duration Seconds:");
        durationSecondsLabel.setForeground(Color.decode(greenTextColor));

        JTextArea titleInput = new JTextArea();
        titleInput.setBorder(new LineBorder(Color.decode(greenTextColor)));
        titleInput.setBackground(Color.BLACK);
        titleInput.setForeground(Color.decode(greenTextColor));
        titleInput.setLineWrap(true);

        SpinnerModel minutesModel = new SpinnerNumberModel(0, 0, 10000, 1);
        JSpinner minutesSpin = new JSpinner(minutesModel);
        minutesSpin.setBorder(new LineBorder(Color.decode(greenTextColor)));
        minutesSpin.getEditor().getComponent(0).setBackground(Color.BLACK);
        minutesSpin.getEditor().getComponent(0).setForeground(Color.decode(greenTextColor));

        SpinnerModel secondsModel = new SpinnerNumberModel(0, 0, 59, 1);
        JSpinner secondsSpin = new JSpinner(secondsModel);
        secondsSpin.setBorder(new LineBorder(Color.decode(greenTextColor)));
        secondsSpin.getEditor().getComponent(0).setBackground(Color.BLACK);
        secondsSpin.getEditor().getComponent(0).setForeground(Color.decode(greenTextColor));

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (titleInput.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Input field is empty.");
                    return;
                }

                // From Object to String
                String minutesStr = "" + minutesSpin.getValue();
                String secondsStr = "" + secondsSpin.getValue();
                // From String o int
                int minutes = Integer.parseInt(minutesStr);
                int seconds = Integer.parseInt(secondsStr);

                int duration = (minutes * 60) + seconds;
                

                SongORM.newSong(albumId, titleInput.getText(), duration);

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

        this.add(durationMinutesLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;

        this.add(durationSecondsLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(1,5, 1, 1);

        this.add(titleInput, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(1,5, 1, 1);

        this.add(minutesSpin, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(1,5, 1, 1);

        this.add(secondsSpin, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(5,5, 1, 1);

        this.add(submit, gbc);

    }

    public void updateSong(int id, String title, int duration) {
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        String greenTextColor = "#33FF00";

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setForeground(Color.decode(greenTextColor));
        JLabel durationMinutesLabel = new JLabel("Duration Minutes:");
        durationMinutesLabel.setForeground(Color.decode(greenTextColor));
        JLabel durationSecondsLabel = new JLabel("Duration Seconds:");
        durationSecondsLabel.setForeground(Color.decode(greenTextColor));

        JTextArea titleInput = new JTextArea();
        titleInput.setBorder(new LineBorder(Color.decode(greenTextColor)));
        titleInput.setBackground(Color.BLACK);
        titleInput.setForeground(Color.decode(greenTextColor));
        titleInput.setText(title);
        titleInput.setLineWrap(true);

        int minutes = duration / 60;
        int seconds = duration % 60;

        SpinnerModel minutesModel = new SpinnerNumberModel(0, 0, 10000, 1);
        JSpinner minutesSpin = new JSpinner(minutesModel);
        minutesSpin.setBorder(new LineBorder(Color.decode(greenTextColor)));
        minutesSpin.setValue(minutes);
        minutesSpin.getEditor().getComponent(0).setBackground(Color.BLACK);
        minutesSpin.getEditor().getComponent(0).setForeground(Color.decode(greenTextColor));

        SpinnerModel secondsModel = new SpinnerNumberModel(0, 0, 59, 1);
        JSpinner secondsSpin = new JSpinner(secondsModel);
        secondsSpin.setBorder(new LineBorder(Color.decode(greenTextColor)));
        secondsSpin.setValue(seconds);
        secondsSpin.getEditor().getComponent(0).setBackground(Color.BLACK);
        secondsSpin.getEditor().getComponent(0).setForeground(Color.decode(greenTextColor));

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (titleInput.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Input field is empty.");
                    return;
                }

                // From Object to String
                String minutesStr = "" + minutesSpin.getValue();
                String secondsStr = "" + secondsSpin.getValue();
                // From String o int
                int minutes = Integer.parseInt(minutesStr);
                int seconds = Integer.parseInt(secondsStr);

                int duration = (minutes * 60) + seconds;

                SongORM.updateSong(id, titleInput.getText(), duration);

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

        this.add(durationMinutesLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;

        this.add(durationSecondsLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(1,5, 1, 1);

        this.add(titleInput, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(1,5, 1, 1);

        this.add(minutesSpin, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(1,5, 1, 1);

        this.add(secondsSpin, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(5,5, 1, 1);

        this.add(submit, gbc);

    }
}
