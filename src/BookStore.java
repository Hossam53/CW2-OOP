public class BookStore extends ReadingListItemStore {

    // Default constructor, calls super constructor with no arguments
    public BookStore(){
        super();
    }

    // Constructor that takes a filename, calls super constructor with filename
    public BookStore(String filename)   {
        super(filename);
    }

    // Constructor that takes a filename and length, calls super constructor with filename and length
    public BookStore(String filename, int length)  {
        super(filename, length);
    }

    // Overrides the getRandomItem method in the parent class
    @Override
    public String getRandomItem(String key) {
        String item = super.getRandomItem(key);

        // If item is not null, extract year from the title and add year category
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

            // If item is null, return null
        } else {
            return null;
        }
    }
}
