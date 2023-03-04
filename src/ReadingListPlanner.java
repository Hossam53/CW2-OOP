import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadingListPlanner {

    // Declare variables

    private final ReadingListItemStore authorStore;
    private final PrizeWinnerStore prizeWinnerStore;
    private final BookStore bookStore;

    private final String prefixLength;

    // Constructor to initialize  variables

    public ReadingListPlanner(ReadingListItemStore authorStore, PrizeWinnerStore prizeWinnerStore, BookStore bookStore, String prefixLength) {
        this.authorStore = authorStore;
        this.prizeWinnerStore = prizeWinnerStore;
        this.bookStore = bookStore;
        this.prefixLength = prefixLength;
    }
    //  method to generate reading list based on user input


    public List<String> generate(String input, String prefixLength) {
        List<String> result = new ArrayList<>();
        int numOfPrize = 0;
        int numOfBook = 0;

        if (prefixLength == null) {


            // Get items from authorStore

            return getStrings(input, result, numOfPrize, numOfBook);
        } else {

            return getString(input, result, numOfPrize, numOfBook, Integer.parseInt(prefixLength));
        }
    }

    private List<String> getStrings(String input, List<String> result, int numOfPrize, int numOfBook) {
        for (char x : input.toCharArray()) {
            String character = Character.toString(x);

            // Check if result list is empty
            if (result.isEmpty()) {
                // Get a random author and add it to the result list
                String author = authorStore.getRandomItem(character);
                result.add(author);
            }

// Check if result list contains only one item
            else if (result.size() == 1) {
                // Get a random prize winner and add it to the result list
                String prizeWinner = prizeWinnerStore.getRandomItem(character);
                result.add(prizeWinner);
            }

// Check if the number of prizes is less than the number of books
            else if (numOfPrize < numOfBook) {
                // Get a random prize winner and add it to the result list
                String prizeWinner = prizeWinnerStore.getRandomItem(character);
                result.add(prizeWinner);
            }

// Otherwise
            else {
                // Get a random book and add it to the result list
                String book = bookStore.getRandomItem(character);
                result.add(book);
                numOfBook += 1;
            }


        }
        return result;
    }
    private List<String> getString(String input, List<String> result, int numOfPrize, int numOfBook, int prefixLength ) {
        // Loop through each character in the input string

        for (char y : input.toCharArray()) {
            // Initialize character count to 1 and convert the character to a string
            int charCount = 1;


            String character = Character.toString(y);

            if (result.isEmpty()) {
                // if result is empty, get a random author from authorStore and add it to the result list
                prizeWinner(input, result, charCount, character, authorStore);
            } else if (result.size() == 1) {
                // if result size is 1, get a random prize winner from prizeWinnerStore and add it to the result list
                prizeWinner(input, result, charCount, character, prizeWinnerStore);
            } else if (numOfPrize < numOfBook) {
                // if number of prizes is less than number of books, get a random prize winner from prizeWinnerStore and add it to the result list
                prize(input, result, charCount, character, prizeWinnerStore);
            } else {
                // otherwise, get a random book from bookStore and add it to the result list
                String Book = bookStore.getRandomItem(character);
                int x = 1;

                // find the length of the prefix of Book that matches with the input
                while ((charCount < input.length() - 1) && (Book.substring(0, x).equalsIgnoreCase(input.substring(0, charCount)))) {
                    charCount++;
                    x++;
                }

                // capitalize the prefix and add it to the result list
                Book = Book.substring(0, x).toUpperCase() + Book.substring(x);
                result.add(Book);
            }



        }
        // return the result list
        return result;
    }

    private void prize(String input, List<String> result, int charCount, String character, ReadingListItemStore prizeWinnerStore) {
        // randomly select an item from the prizeWinnerStore
        String prizeWinner = prizeWinnerStore.getRandomItem(character);
        int x = 1;

        // check how many characters match the input string until a match is found
        while ((charCount < input.length()-1)&&(prizeWinner.substring(0,x).equalsIgnoreCase(input.substring(0, charCount)))){

            charCount++;
            x++;
        }

        // capitalize the first letter of the prize and add it to the result list
        prizeWinner = prizeWinner.substring(0, x).toUpperCase() + prizeWinner.substring(x);
        result.add(prizeWinner);
    }

    private void prizeWinner(String input, List<String> result, int charCount, String character, ReadingListItemStore prizeWinnerStore) {
        prize(input, result, charCount, character, prizeWinnerStore);
    }


    public static void main(String[] args) throws IOException {
        //args = new String[]{"thththththththththth", "2"};

        // Try to read the arguments and create ReadingListPlanner object with those arguments
        try {
            // Create the ReadingListItemStore and PrizeWinnerStore based on input argument size
            ReadingListItemStore authorStore = new ReadingListItemStore("authors.txt",(Integer.parseInt(args[1])));
            PrizeWinnerStore prizeWinnerStore = new PrizeWinnerStore("prize-winners.txt", (Integer.parseInt(args[1])));

            // Create the BookStore object
            BookStore bookStore = new BookStore("books.txt");

            // Create a ReadingListPlanner object with the given stores and input argument size
            ReadingListPlanner planner = new ReadingListPlanner(authorStore, prizeWinnerStore, bookStore, args[1]);

            // Generate a reading list with the input string and argument size
            List<String> result = planner.generate(args[0],args[1]);

            // Print out the result
            for (String item : result){
                System.out.println(item);
            }

            // If there is an ArrayIndexOutOfBoundsException error, catch it and create a ReadingListPlanner object without argument size
        }catch (ArrayIndexOutOfBoundsException e){
            ReadingListItemStore authorStore = new ReadingListItemStore("authors.txt");
            PrizeWinnerStore prizeWinnerStore = new PrizeWinnerStore("prize-winners.txt");
            BookStore bookStore = new BookStore("books.txt");
            ReadingListPlanner planner = new ReadingListPlanner(authorStore, prizeWinnerStore, bookStore, null);

            // Generate a reading list with the input string and null argument size
            List<String> result = planner.generate(args[0],null);

            // Print out the result
            for (String item : result){
                System.out.println(item);
            }
        }
    }


}
