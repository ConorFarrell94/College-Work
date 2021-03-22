package com.oopAssignment.topicModeller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class myGUI extends JFrame {

    private Path path1, path2;
    private String file1Text, file2Text;
    private HashMap<String, Integer> processedFile1;
    private HashMap<String, Integer> processedFile2;


    myGUI(String title) {

        super(title);

        setLayout(new BorderLayout());
        setSize(800, 600);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JTextField textField1 = new JTextField("Testing"); // text-fields may not be the best thing to use here considering they can be edited by the user and arent the best for displaying info
        JTextField textField2 = new JTextField("Testing");

        JLabel label1 = new JLabel("Topic Modeller");

        JButton button1 = new JButton("Select File 1");
        JButton button2 = new JButton("Select File 2");
        JButton button3 = new JButton("Top 10 File 1");
        JButton button4 = new JButton("Top 10 File 2");
        JButton button5 = new JButton("Compare Top Ten");
        JButton button6 = new JButton("Exit");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser1 = new JFileChooser();
                int result = fileChooser1.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    path1 = fileChooser1.getSelectedFile().toPath();
                    System.out.println(path1);
                    try {
                        file1Text = Files.readString(path1); // found at https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    processedFile1 = fileProcessor.processFile(file1Text);
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser2 = new JFileChooser();
                int result = fileChooser2.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    path2 = fileChooser2.getSelectedFile().toPath();
                    System.out.println(path1);
                    try {
                        file2Text = Files.readString(path2);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    processedFile2 = fileProcessor.processFile(file2Text);
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileProcessor.topTen(processedFile1);
                textField1.setText("Complete 1");
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileProcessor.topTen(processedFile2);
                textField2.setText("Complete 2");
            }
        });

        // button 6 adds a confirmation pop-up to close the program, could possibly see if theres a way to add this to the "X" on top right of program too
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Yes","No"};
                int result = JOptionPane.showOptionDialog(null, "Are you sure?", "Confirm Exit",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[1]);
                if(result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        panel1.add(label1);
        panel1.setBackground(Color.BLUE);
        label1.setForeground(Color.WHITE);

        panel2.setLayout(new GridLayout(6,1));
        panel2.setBackground(Color.BLUE);
        panel2.setBorder(new EmptyBorder(0,0,0,10));
        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        panel2.add(button4);
        panel2.add(button5);
        panel2.add(button6);

        panel3.setBackground(Color.BLACK);
        panel3.add(textField1);
        panel3.add(textField2);
        textField1.setBackground(Color.DARK_GRAY);
        textField1.setForeground(Color.WHITE);
        textField1.setHorizontalAlignment(JTextField.CENTER);
        textField2.setBackground(Color.DARK_GRAY);
        textField2.setForeground(Color.WHITE);
        textField2.setHorizontalAlignment(JTextField.CENTER);

        add(panel1, BorderLayout.PAGE_START);
        add(panel2, BorderLayout.WEST);
        add(panel3, BorderLayout.CENTER);
        setVisible(true);
    }
}