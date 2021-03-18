package com.oopAssignment.test;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class myGUI extends JFrame {

    private Path path1, path2;
    private String file1Text, file2Text;


    myGUI(String title) {

        super(title);

        setLayout(new BorderLayout());
        setSize(800, 600);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JTextField textField1 = new JTextField("Testing");

        JLabel label1 = new JLabel("Text Topic Analyser");

        JButton button1 = new JButton("File 1");
        JButton button2 = new JButton("File 2");
        JButton button3 = new JButton("button3");
        JButton button4 = new JButton("button4");
        JButton button5 = new JButton("button5");
        JButton button6 = new JButton("button6");

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
                    System.out.println(file1Text);
                    fileProcessor processedFile1 = new fileProcessor();
                    processedFile1.processFile(file1Text);
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
                    System.out.println(file2Text);
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

        textField1.setBackground(Color.DARK_GRAY);
        textField1.setForeground(Color.WHITE);
        textField1.setHorizontalAlignment(JTextField.CENTER);

        add(panel1, BorderLayout.PAGE_START);
        add(panel2, BorderLayout.WEST);
        add(textField1, BorderLayout.CENTER);
        setVisible(true);
    }
}