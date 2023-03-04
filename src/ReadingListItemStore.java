import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadingListItemStore {

    private final Map<String, List<String>> map;

    // Map to store the items by their corresponding key

    public ReadingListItemStore() {
        this.map = new HashMap<>();
    }
    public ReadingListItemStore(String fileName) {
        this();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Read each line and convert it to lowercase
                String item = line.toLowerCase();

                // Extract the first letter of the item and convert it to uppercase
                String key = item.substring(0, 1).toUpperCase();
                // Add the item to the map
                put(key, item);
            }
        } catch (IOException e) {
            // If there is an exception reading the file, throw a runtime exception
            System.out.println("problem reading the file");
            throw new RuntimeException(e);
        }
    }

    public ReadingListItemStore(String fileName, int PrefixLength) {

        this();
        try {

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // For each line in the file, create keys of length up to PrefixLength
                    for (int i = 1; i <= PrefixLength && i <= line.length(); i++) {
                        String key = line.substring(0, i).toUpperCase();
                        // Add the item to the map
                        put(key, line);
                    }
                }
            } catch (IOException e) {
                // If there is an exception reading the file, throw a runtime exception
                throw new RuntimeException(e);


            }


        } catch (NullPointerException e) {
            // If the prefix length is null, throw a runtime exception
            throw new RuntimeException(e);

        }
    }



    // Check if the store contains any items for the specified key
    public boolean containsKey(String key) {
        return map.containsKey(key.toLowerCase());
    }

    // Add an item to the store, under the specified key
    public void put(String key, String item) {
        // Convert the key to lowercase
        String lowerKey = key.toLowerCase();
        // If the key is not in the map, add an empty list for it
        if (!map.containsKey(lowerKey)) {
            map.put(lowerKey, new ArrayList<>());
        }
        // Add the item to the list for the key
        map.get(lowerKey).add(item);
    }

    // Get a random item from the store, for the specified key
    // The returned item will have the specified key as a prefix, followed by the rest of the item
    public String getRandomItem(String key) {
        // Convert the key to lowercase
        String lowerKey = key.toLowerCase();
        // If the key is not in the map, return null
        if (!map.containsKey(lowerKey)) {
            return null;
        }
        // Get the list of items for the key
        List<String> items = map.get(lowerKey);
        // If the list is empty, return null
        if (items.isEmpty()) {
            return null;
        }

        String randomItem = items.get((int) (Math.random() * items.size()));
        // get random item from list
        return key.toUpperCase() + randomItem.substring(key.length());
        // return item with key prefix in uppercase
    }




}
