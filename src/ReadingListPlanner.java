import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadingListPlanner {

    private ReadingListItemStore authorStore;
    private PrizeWinnerStore prizeWinnerStore;
    private BookStore bookStore;

    private String prefixLength;


    public ReadingListPlanner(ReadingListItemStore authorStore, PrizeWinnerStore prizeWinnerStore, BookStore bookStore, String prefixLength) {
        this.authorStore = authorStore;
        this.prizeWinnerStore = prizeWinnerStore;
        this.bookStore = bookStore;
        this.prefixLength = prefixLength;
    }


    public List<String> generate(String input, String prefixLength) {
        List<String> result = new ArrayList<>();
        int numOfPrize = 0;
        int numOfBook = 0;

        if (prefixLength == null) {


            // Get items from authorStore

            for (char c : input.toCharArray()) {
                String character = Character.toString(c);

                if (result.size() < 1) {
                    String author = authorStore.getRandomItem(String.valueOf(c));
                    result.add(author);

                }


                // Get items from prizeWinnerStore
                else if (result.size() < 2) {
                    String prizeWinner = prizeWinnerStore.getRandomItem(character);
                    result.add(prizeWinner);

                } else if (numOfPrize < numOfBook) {
                    String prizeWinner = prizeWinnerStore.getRandomItem(character);
                    result.add(prizeWinner);


                } else {
                    String Book = bookStore.getRandomItem(character);
                    result.add(Book);
                    numOfBook++;
                }

            }
            return result;
        } else {
            // generate with prefix length
            int length = Integer.parseInt(prefixLength);
            for (int i = 0; i < input.length(); i+=length) {
                String author = authorStore.getRandomItem(input);
                String prizeWinner = prizeWinnerStore.getRandomItem(input);
                String Book = bookStore.getRandomItem(input);



            }

            return result;

        }
    }




    public static void main(String[] args) throws IOException {


try {
    ReadingListItemStore authorStore = new ReadingListItemStore("authors.txt",(Integer.parseInt(args[1])));
    PrizeWinnerStore prizeWinnerStore = new PrizeWinnerStore("prize-winners.txt", (Integer.parseInt(args[1])));
    BookStore bookStore = new BookStore("books.txt");
    ReadingListPlanner planner = new ReadingListPlanner(authorStore, prizeWinnerStore, bookStore, args[1]);
    List<String> result = planner.generate(args[0],args[1]);
    for (String item : result){
        System.out.println(item);
    }

}catch (ArrayIndexOutOfBoundsException e){
    ReadingListItemStore authorStore = new ReadingListItemStore("authors.txt");
    PrizeWinnerStore prizeWinnerStore = new PrizeWinnerStore("prize-winners.txt");
    BookStore bookStore = new BookStore("books.txt");
    ReadingListPlanner planner = new ReadingListPlanner(authorStore, prizeWinnerStore, bookStore, null);
    List<String> result = planner.generate(args[0],null);
    for (String item : result){
        System.out.println(item);
    }

}




    }

}
