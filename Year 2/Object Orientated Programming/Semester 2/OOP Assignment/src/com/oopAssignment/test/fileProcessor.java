package com.oopAssignment.test;

import javax.swing.*;
import java.util.HashMap;

public class fileProcessor {

    public HashMap<String, Integer> wordOccurance1 = new HashMap<String, Integer>(); // found at https://www.w3schools.com/java/java_hashmap.asp

    public void processFile(String file) {
        int x;
        String[] splitted = file.split(" ");

        for (int i = 0; i < splitted.length; i++) {

            if (wordOccurance1.containsKey(splitted[i])) {
                x = wordOccurance1.get(splitted[i]);
                wordOccurance1.put(splitted[i], x + 1);
            } else {
                wordOccurance1.put(splitted[i], 1);
            }
        }

        for (String key : wordOccurance1.keySet()) {

            System.out.println(key + " " + wordOccurance1.get(key));
        }
    }
}
