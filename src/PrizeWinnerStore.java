import java.io.IOException;

public class PrizeWinnerStore extends ReadingListItemStore {


    public PrizeWinnerStore() {
        super();

    }
    public PrizeWinnerStore(String filename) throws IOException {
        super(filename);
    }
    public PrizeWinnerStore(String filename, int x) throws IOException {
        super(filename, x);
    }


    @Override
    public String getRandomItem(String key) {
        String item = super.getRandomItem(key);
        if (item != null) {
            return item + " (prize-winner)";
        } else {
            return null;
        }
    }
}
