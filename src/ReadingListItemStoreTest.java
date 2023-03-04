public class ReadingListItemStoreTest {
    public static void main(String[] args) {

        // Create a new instance of the ReadingListItemStore class
        ReadingListItemStore store = new ReadingListItemStore();

        // Add some items to the store
        store.put("a", "Alexandre Dumas");
        store.put("ph", "Philip Larkin");

        // Get a random item from the store for the key "a" and print it to the console
        System.out.println(store.getRandomItem("a"));

        // Get a random item from the store for the key "ph" and print it to the console
        System.out.println(store.getRandomItem("ph"));

    }
}
