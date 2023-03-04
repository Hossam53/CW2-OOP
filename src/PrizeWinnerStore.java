import java.io.IOException;

// A class that extends the ReadingListItemStore class and represents a store for prize-winning reading items
public class PrizeWinnerStore extends ReadingListItemStore {

    // Default constructor that calls the constructor of the superclass
    public PrizeWinnerStore() {
        super();
    }

    // Constructor that takes a filename as a parameter and calls the corresponding constructor of the superclass
    public PrizeWinnerStore(String filename) throws IOException {
        super(filename);
    }

    // Constructor that takes a filename and a prefix length as parameters and calls the corresponding constructor of the superclass
    public PrizeWinnerStore(String filename, int x) throws IOException {
        super(filename, x);
    }

    // Method that overrides the getRandomItem method of the superclass and returns a random prize-winning item
    @Override
    public String getRandomItem(String key) {
        String item = super.getRandomItem(key); // call the getRandomItem method of the superclass
        if (item != null) {
            return item + " (prize-winner)"; // append the string "(prize-winner)" to the item
        } else {
            return null;
        }
    }
}
