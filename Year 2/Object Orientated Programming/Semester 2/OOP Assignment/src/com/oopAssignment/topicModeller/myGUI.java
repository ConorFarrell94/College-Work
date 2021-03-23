package com.oopAssignment.topicModeller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class myGUI extends JFrame {

    public Path path1, path2;
    public String file1Text, file2Text;
    public HashMap<String, Integer> processedFile1, processedFile2;
    public ArrayList<String> topTenFile1;
    public ArrayList<String> topTenFile2;

    myGUI(String title) {

        super(title);

        setLayout(new BorderLayout());
        setSize(1000, 1000);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        JTextArea textAreaFile1 = new JTextArea();
        textAreaFile1.setEditable(false);
        JScrollPane scroll1 = new JScrollPane(textAreaFile1);
        scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JTextArea textAreaFile2 = new JTextArea();
        textAreaFile2.setEditable(false);
        JScrollPane scroll2 = new JScrollPane(textAreaFile2);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JTextArea textAreaTopTen1 = new JTextArea();
        textAreaTopTen1.setEditable(false);
        JScrollPane scroll3 = new JScrollPane(textAreaTopTen1);
        scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JTextArea textAreaTopTen2 = new JTextArea();
        textAreaTopTen2.setEditable(false);
        JScrollPane scroll4 = new JScrollPane(textAreaTopTen2);
        scroll4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JTextArea textAreaCompare = new JTextArea();
        textAreaCompare.setEditable(false);
        JScrollPane scroll5 = new JScrollPane(textAreaCompare);
        scroll5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel title1 = new JLabel("Topic Modeller");
        JLabel label1 = new JLabel("File 1");
        JLabel label2 = new JLabel("File 2");
        JLabel label3 = new JLabel("Top 10 File 1");
        JLabel label4 = new JLabel("Top 10 File 2");
        JLabel label5 = new JLabel("Top Ten Compared");

        JButton button1 = new JButton("Select File 1");
        JButton button2 = new JButton("Select File 2");
        JButton button3 = new JButton("Top 10 File 1");
        JButton button4 = new JButton("Top 10 File 2");
        JButton button5 = new JButton("Compare Top Ten");
        JButton button6 = new JButton("Exit");

        /* Originally here I had:
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser1 = new JFileChooser();
                int result = fileChooser1.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    path1 = fileChooser1.getSelectedFile().toPath();
                    System.out.println(path1);
                    try {
                        file1Text = Files.readString(path1);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    processedFile1 = fileProcessor.processFile(file1Text);
                    textAreaFile1.setText(file1Text);
                }
            }
        });

        But my IDE suggested I replace it with lambda which looks cleaner
        */
        button1.addActionListener(e -> {
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
                textAreaFile1.setText(file1Text);
            }
        });
        button2.addActionListener(e -> {
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
                textAreaFile2.setText(file2Text);
            }
        });

        button3.addActionListener(e -> {
            LinkedHashMap<String, Integer> topTen1 = fileProcessor.topTen(processedFile1);
            textAreaTopTen1.setText(null); // putting this here in-case you want to run it multiple times without closing program
            int i = 1; // starting at 1 so as when it gets printed out it doesn't begin with 0
            for (Map.Entry<String, Integer> entry : topTen1.entrySet())
            {
                if(i < 11) {
                    textAreaTopTen1.append("[" + (i) + "] " + entry.getKey() + " | Occurrences: " + entry.getValue() + "\n");
                    topTenFile1.add(entry.getKey());
                    i++;
                }
                else {
                    break;
                }
            }
            System.out.println(topTenFile1);
        });
        button4.addActionListener(e -> {
            LinkedHashMap<String, Integer> topTen2 = fileProcessor.topTen(processedFile2);
            textAreaTopTen2.setText(null);
            int i = 1;
            for (Map.Entry<String, Integer> entry : topTen2.entrySet())
            {
                if(i < 11) {
                    textAreaTopTen2.append("[" + (i) + "] " + entry.getKey() + " | Occurrences: " + entry.getValue() + "\n");
                    topTenFile2.add(entry.getKey());
                    i++;
                }
                else {
                    break;
                }
            }
            System.out.println(topTenFile2);
        });

        button5.addActionListener(e -> {
            // this was pretty difficult to do for me, took many attempts. Most useful information was found at : https://www.geeksforgeeks.org/arraylist-retainall-method-in-java/
            topTenFile1.retainAll(topTenFile2);
            textAreaCompare.append("Words in common between the two documents are: " + topTenFile1);
            System.out.println(topTenFile1);
        });

        // button 6 adds a confirmation pop-up to close the program, could possibly see if theres a way to add this to the "X" on top right of program too
        button6.addActionListener(e -> {
            String[] options = {"Yes","No"};
            int result = JOptionPane.showOptionDialog(null, "Are you sure?", "Confirm Exit",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[1]);
            if(result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        panel1.setBackground(Color.BLUE);
        title1.setForeground(Color.WHITE);

        panel1.add(title1);

        panel2.setLayout(new GridLayout(6,1));
        panel2.setBackground(Color.BLUE);
        panel2.setBorder(new EmptyBorder(0,0,0,10));

        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        panel2.add(button4);
        panel2.add(button5);
        panel2.add(button6);

        panel3.setLayout(new GridLayout(10, 1));
        panel3.setBackground(Color.BLACK);

        panel3.add(label1);
        panel3.add(scroll1);
        panel3.add(label2);
        panel3.add(scroll2);
        panel3.add(label3);
        panel3.add(scroll3);
        panel3.add(label4);
        panel3.add(scroll4);
        panel3.add(label5);
        panel3.add(scroll5);

        label1.setForeground(Color.WHITE);
        label1.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        label2.setForeground(Color.WHITE);
        label2.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        label3.setForeground(Color.WHITE);
        label3.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        label4.setForeground(Color.WHITE);
        label4.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        label5.setForeground(Color.WHITE);
        label5.setHorizontalAlignment((int) CENTER_ALIGNMENT);

        textAreaFile1.setBackground(Color.darkGray);
        textAreaFile1.setForeground(Color.white);
        textAreaFile2.setBackground(Color.darkGray);
        textAreaFile2.setForeground(Color.white);
        textAreaTopTen1.setBackground(Color.darkGray);
        textAreaTopTen1.setForeground(Color.white);
        textAreaTopTen2.setBackground(Color.darkGray);
        textAreaTopTen2.setForeground(Color.white);
        textAreaCompare.setBackground(Color.darkGray);
        textAreaCompare.setForeground(Color.white);

        add(panel1, BorderLayout.PAGE_START);
        add(panel2, BorderLayout.WEST);
        add(panel3, BorderLayout.CENTER);

        setVisible(true);
    }
}