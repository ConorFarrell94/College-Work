package com.oopAssignment.topicModeller;

import java.util.*;

public class fileProcessor {

    public static HashMap<String, Integer> processFile(String file) {
        HashMap<String, Integer> wordOccurrence = new HashMap<>(); // found at https://www.w3schools.com/java/java_hashmap.asp
        int x;
        String[] myFile = file.split(" ");

        for (int i = 0; i < myFile.length; i++) {
            myFile[i] = myFile[i].replaceAll(" ", "");
        }

        /* I did have this here originally:
        for (int i = 0; i < myFile.length; i++) {
            if (wordOccurrence.containsKey(myFile[i])) {
                x = wordOccurrence.get(myFile[i]);
                wordOccurrence.put(myFile[i], x + 1);
            } else {
                wordOccurrence.put(myFile[i], 1);
            }
        }
        But my IDE said I can replace it with an enhanced loop which seems to be a lot cleaner than what I had
        */
        for (String s : myFile) {
            if (wordOccurrence.containsKey(s)) {
                x = wordOccurrence.get(s);
                wordOccurrence.put(s, x + 1);
            }
            else {
                wordOccurrence.put(s, 1);
            }
        }

        return wordOccurrence;

    }

    // began to really see the use of snippets that IDE's can generate here
    public static LinkedHashMap<String, Integer> topTen(HashMap<String, Integer> file) {
        /*
        Map<String, Integer> treeMap = new TreeMap<>(Collections.reverseOrder());
        treeMap.putAll(file);

        for (Map.Entry entry : treeMap.entrySet())
        {
            System.out.println("Word: " + entry.getKey() + " | Occurrences: " + entry.getValue());
        }
        */

        Map<String, Integer> unSorted = file;

        System.out.println("Unsorted Map: " + unSorted);

        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

        unSorted.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x->sortedMap.put(x.getKey(), x.getValue()));

        System.out.println("Sorted Map: " + sortedMap);

        return sortedMap;

    }

}
