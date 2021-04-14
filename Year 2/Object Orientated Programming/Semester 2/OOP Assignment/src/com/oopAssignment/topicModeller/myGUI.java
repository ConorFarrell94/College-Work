package com.oopAssignment.topicModeller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class myGUI extends JFrame {

    public String x;
    public Path path1, path2;
    public String file1Text, file2Text;
    public HashMap<String, Integer> processedFile1, processedFile2, stopWordsList;
    public ArrayList topTenFile1 = new ArrayList();
    public ArrayList topTenFile2 = new ArrayList();

    myGUI(String title) {

        super(title);

        setLayout(new BorderLayout());
        setSize(1200, 1000);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        // creating scroll panels that contain text areas which in turn cannot be edited. scroll bars will only appear when needed
        JTextArea textAreaFile1 = new JTextArea();
        textAreaFile1.setEditable(false);
        JScrollPane scroll1 = new JScrollPane(textAreaFile1);
        scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        JTextArea textAreaFile2 = new JTextArea();
        textAreaFile2.setEditable(false);
        JScrollPane scroll2 = new JScrollPane(textAreaFile2);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        JTextArea textAreaTopTen1 = new JTextArea();
        textAreaTopTen1.setEditable(false);
        JScrollPane scroll3 = new JScrollPane(textAreaTopTen1);
        scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        JTextArea textAreaTopTen2 = new JTextArea();
        textAreaTopTen2.setEditable(false);
        JScrollPane scroll4 = new JScrollPane(textAreaTopTen2);
        scroll4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        JTextArea textAreaCompare = new JTextArea();
        textAreaCompare.setEditable(false);
        JScrollPane scroll5 = new JScrollPane(textAreaCompare);
        scroll5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        JLabel title1 = new JLabel("Topic Modeller");

        JButton selectFileOneButton = new JButton("Select File 1");
        JButton selectFileTwoButton = new JButton("Select File 2");
        JButton fileOneTopTenButton = new JButton("Top 10 - File 1");
        JButton file2TopTenButton = new JButton("Top 10 - File 2");
        JButton compareButton = new JButton("Compare Top Ten");
        JButton exitButton = new JButton("Exit");
        JButton resetButton = new JButton("Reset");
        JButton stopWordsButton = new JButton("StopWords");

        // opens a file chooser so as the user can select any .txt file to use in the program.
        // uses "fileProcessor" class to parse through the file and format it in such a way we can use
        // if stop-words have also been loaded they will be removed from the file in this function
        selectFileOneButton.addActionListener(e -> {
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
            scroll1.setVisible(true);
            processedFile1.keySet().removeAll(stopWordsList.keySet());
        });

        // opens a file chooser so as the user can select any .txt file to use in the program.
        // uses "fileProcessor" class to parse through the file and format it in such a way we can use
        // if stop-words have also been loaded they will be removed from the file in this function
        selectFileTwoButton.addActionListener(e -> {
            JFileChooser fileChooser2 = new JFileChooser();
            int result = fileChooser2.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                path2 = fileChooser2.getSelectedFile().toPath();
                System.out.println(path2);
                try {
                    file2Text = Files.readString(path2);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                processedFile2 = fileProcessor.processFile(file2Text);
                textAreaFile2.setText(file2Text);
            }
            scroll2.setVisible(true);
            processedFile2.keySet().removeAll(stopWordsList.keySet());
        });

        // takes file one processed file. sorts it and then gives back out the top ten results
        fileOneTopTenButton.addActionListener(e -> {
            LinkedHashMap<String, Integer> topTen2 = fileProcessor.topTen(processedFile1);
            textAreaTopTen1.setText(null); // putting this here in-case you want to run it multiple times without closing program
            int i = 1; // starting at 1 so as when it gets printed out it doesn't begin with 0
            for (Map.Entry entry : topTen2.entrySet())
            {
                if(i < 11) {
                    textAreaTopTen1.append("[" + (i) + "] " + entry.getKey() + " | Occurrences: " + entry.getValue() + "\n");
                    x = (String) entry.getKey();
                    topTenFile1.add(entry.getKey());
                    i++;
                }
                else {
                    break;
                }
            }
            System.out.println(topTenFile1);
            scroll3.setVisible(true);
        });

        // takes file two processed file. sorts it and then gives back out the top ten results
        file2TopTenButton.addActionListener(e -> {
            LinkedHashMap<String, Integer> topTen2 = fileProcessor.topTen(processedFile2);
            textAreaTopTen2.setText(null);
            int i = 1;
            for (Map.Entry entry : topTen2.entrySet())
            {
                if(i < 11) {
                        textAreaTopTen2.append("[" + (i) + "] " + entry.getKey() + " | Occurrences: " + entry.getValue() + "\n");
                        x = (String) entry.getKey();
                        topTenFile2.add(entry.getKey());
                        i++;
                    }
                else {
                    break;
                }
            }
            System.out.println(topTenFile2);
            scroll4.setVisible(true);
        });

        // compares the top then words of each file to determine if they are similar/about the same topic
        compareButton.addActionListener(e -> {
            // this was pretty difficult to do for me, took many attempts. Most useful information was found at : https://www.geeksforgeeks.org/arraylist-retainall-method-in-java/
            textAreaCompare.setText(null);
            topTenFile1.retainAll(topTenFile2);
            textAreaCompare.setText("Words in common between the two documents are: ");
            for (int i = 0; i < topTenFile1.size(); i ++) {
                textAreaCompare.append("\n[" + (i + 1) + "] " + topTenFile1.get(i));
            }
            if (topTenFile1.size() > 7) {
                textAreaCompare.append("\nThese two documents have 70% or more words in common.\nVery likely to be about the same topic.");
            }
            else if (topTenFile1.size() < 7) {
                textAreaCompare.append("\nThese two documents have less than 70% words in common.\nIt's unlikely these are about the same topic");
            }
            System.out.println(topTenFile1);
            scroll5.setVisible(true);
            topTenFile1.removeAll(topTenFile2);
        });

        // adds a confirmation pop-up to close the program
        exitButton.addActionListener(e -> {
            String[] options = {"Yes","No"};
            int result = JOptionPane.showOptionDialog(null, "Are you sure?", "Confirm Exit",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[1]);
            if(result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // resets the screen and clears data holders so you can use the program again without having to close and re-open
        resetButton.addActionListener(e-> {
            scroll1.setVisible(false);
            scroll2.setVisible(false);
            scroll3.setVisible(false);
            scroll4.setVisible(false);
            scroll5.setVisible(false);

            textAreaCompare.setText(null);
            textAreaFile1.setText(null);
            textAreaFile2.setText(null);
            textAreaTopTen1.setText(null);
            textAreaTopTen2.setText(null);

            topTenFile1.clear();
            topTenFile2.clear();
            processedFile1.clear();
            processedFile2.clear();
        });

        // opens a file chooser for the user to select their own text file of stop-words to use in the program
        stopWordsButton.addActionListener(e-> {

            JFileChooser fileChooser3 = new JFileChooser();
            int result = fileChooser3.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {

                Path path1 = fileChooser3.getSelectedFile().toPath();
                String stopWordsFile = "";

                try {
                    stopWordsFile = Files.readString(path1);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                stopWordsFile.toLowerCase(Locale.ROOT);
                stopWordsFile.replaceAll("\n", " ");
                stopWordsFile.split(" ");

                Scanner scanner1 = new Scanner(stopWordsFile);
                stopWordsList = new HashMap<>();

                while (scanner1.hasNextLine()) {
                    stopWordsList.put(scanner1.next(), 1); // putting them into a hashmap because its easier to compare it against the other files
                }
                JOptionPane.showMessageDialog(null, "Stop words loaded successfully");
            }
        });

        // adding title to the center of the program
        panel1.setBackground(Color.BLUE);
        title1.setForeground(Color.WHITE);
        title1.setFont(new Font("Courier", Font.BOLD, 18));

        panel1.add(title1);

        panel2.setLayout(new GridLayout(5,1));
        panel2.setBackground(Color.BLUE);
        panel2.setBorder(new EmptyBorder(0,0,0,10));

        // adding buttons to the left hand side and setting colors
        panel2.add(selectFileOneButton);
        selectFileOneButton.setBackground(Color.darkGray);
        selectFileOneButton.setForeground(Color.white);
        panel2.add(selectFileTwoButton);
        selectFileTwoButton.setBackground(Color.darkGray);
        selectFileTwoButton.setForeground(Color.white);
        panel2.add(fileOneTopTenButton);
        fileOneTopTenButton.setBackground(Color.darkGray);
        fileOneTopTenButton.setForeground(Color.white);
        panel2.add(file2TopTenButton);
        file2TopTenButton.setBackground(Color.darkGray);
        file2TopTenButton.setForeground(Color.white);
        panel2.add(compareButton);
        compareButton.setBackground(Color.darkGray);
        compareButton.setForeground(Color.white);
        panel2.add(exitButton);
        exitButton.setBackground(Color.darkGray);
        exitButton.setForeground(Color.white);
        resetButton.setBackground(Color.darkGray);
        resetButton.setForeground(Color.white);
        stopWordsButton.setBackground(Color.darkGray);
        stopWordsButton.setForeground(Color.white);

        panel3.setLayout(new GridLayout(5, 1));
        panel3.setBackground(Color.BLUE);

        // adding scroll panels that will pop up when information is added to them
        // use of titled borders allows us to use grid layout nicely here so as the displays are all evenly sized
        panel3.add(scroll1);
        scroll1.setBorder(BorderFactory.createTitledBorder("File 1"));
        scroll1.setVisible(false);
        panel3.add(scroll2);
        scroll2.setBorder(BorderFactory.createTitledBorder("File 2"));
        scroll2.setVisible(false);
        panel3.add(scroll3);
        scroll3.setBorder(BorderFactory.createTitledBorder("Top Ten - File 1"));
        scroll3.setVisible(false);
        panel3.add(scroll4);
        scroll4.setBorder(BorderFactory.createTitledBorder("Top Ten - File 2"));
        scroll4.setVisible(false);
        panel3.add(scroll5);
        scroll5.setBorder(BorderFactory.createTitledBorder("Top Ten Compared"));
        scroll5.setVisible(false);

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

        panel4.setLayout(new GridLayout(3,1));
        panel4.setBorder(new EmptyBorder(0,10,0,0));
        panel4.setBackground(Color.BLUE);
        panel4.add(exitButton);
        panel4.add(resetButton);
        panel4.add(stopWordsButton);

        add(panel1, BorderLayout.PAGE_START);
        add(panel2, BorderLayout.WEST);
        add(panel3, BorderLayout.CENTER);
        add(panel4, BorderLayout.EAST);

        setVisible(true);
    }
}