import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadingListItemStore {

    private final Map<String, List<String>> map;

    public ReadingListItemStore() {
        this.map = new HashMap<>();
    }
    public ReadingListItemStore(String fileName) {
        this();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String item = line.trim().toLowerCase();
                String key = item.substring(0, 1).toUpperCase();
                put(key, item);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ReadingListItemStore(String fileName, int PrefixLength) {

        this();
        try {

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String item = line.trim().toLowerCase();
                    for (int i = 1; i <= PrefixLength && i <= item.length(); i++) {
                        String key = item.substring(0, i).toUpperCase();
                        put(key, item);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (NullPointerException e) {
            System.out.println("A null pointer exception occurred: " + e.getMessage());



        }



    }



    public  boolean containsKey(String key) {
        return map.containsKey(key.toLowerCase());
    }

    public void put(String key, String item) {
        String lowerKey = key.toLowerCase();
        if (!map.containsKey(lowerKey)) {
            map.put(lowerKey, new ArrayList<>());
        }
        map.get(lowerKey).add(item);
    }

    public String getRandomItem(String key) {
        String lowerKey = key.toLowerCase();
        if (!map.containsKey(lowerKey)) {
            return null;
        }
        List<String> items = map.get(lowerKey);
        if (items.isEmpty()) {
            return null;
        }

        String randomItem = items.get((int) (Math.random() * items.size()));
        return key.toUpperCase()+ randomItem.substring(key.length()) ;
    }


    public String getRandomItemsWithPrefix(String key, int n){
        String lowerKey = key.toLowerCase();
        if (!map.containsKey(lowerKey)) {
            return null;
        }
        List<String> items = map.get(lowerKey);
        if (items.isEmpty()) {
            return null;
        }

        String randomItem = items.get((int) (Math.random() * items.size()));
         //key.toUpperCase()+ randomItem.substring(key.length()) ;
        return randomItem.substring(0, n).toUpperCase() + randomItem.substring(n);


    }



}
