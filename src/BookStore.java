import java.io.IOException;
public class BookStore extends ReadingListItemStore {


    public BookStore(){
        super();
    }
    public BookStore(String filename) throws IOException {
        super(filename);
    }
    public BookStore(String filename, int length) throws IOException {
        super(filename, length);
    }


    @Override
    public String getRandomItem(String key) {
        String item = super.getRandomItem(key);
        if (item != null) {
            String[] parts = item.split(" ", 2);
            String titleAuthor = parts[0];
            String yearString = parts[1].replaceAll("\\D", ""); // remove all non-digits from year string
            int year = Integer.parseInt(yearString);
            String yearCategory;
            if (year >= 1990) {
                yearCategory = "(contemporary)";
            } else if (year >= 1900) {
                yearCategory = "(modern)";
            } else {
                yearCategory = "(classic)";
            }
            return titleAuthor + " " + yearCategory;
        } else {
            return null;
        }
    }

}
