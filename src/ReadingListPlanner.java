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

            return getStrings(input, result, numOfPrize, numOfBook);
        } else {

            return getString(input, result, numOfPrize, numOfBook, Integer.parseInt(prefixLength));
        }
    }

    private List<String> getStrings(String input, List<String> result, int numOfPrize, int numOfBook) {
        for (char c : input.toCharArray()) {
            String character = Character.toString(c);

            if (result.size() < 1) {
                String author = authorStore.getRandomItem(character);
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
    }
    private List<String> getString(String input, List<String> result, int numOfPrize, int numOfBook, int prefixLength ) {
        for (char c : input.toCharArray()) {
            int charCount = 1;
            String character = Character.toString(c);

            if (result.size() < 1) {
                String author = authorStore.getRandomItem(character);
                int x = 1;


                while ((charCount < input.length()-1)&&(author.substring(0,x).equalsIgnoreCase(input.substring(0, charCount)))){


                    charCount+=1;
                    x = x+1;
                    }
                author = author.substring(0, x).toUpperCase() + author.substring(x);
                result.add(author);


                }







            // Get items from prizeWinnerStore
            else if (result.size() < 2) {

                String prizeWinner = prizeWinnerStore.getRandomItem(character);
                int x = 1;

                while ((charCount < input.length()-1)&&(prizeWinner.substring(0,x).equalsIgnoreCase(input.substring(0, charCount)))){


                    charCount+=1;
                    x = x+1;
                }
                prizeWinner = prizeWinner.substring(0, x).toUpperCase() + prizeWinner.substring(x);

                result.add(prizeWinner);

            } else if (numOfPrize < numOfBook) {
                String prizeWinner = prizeWinnerStore.getRandomItem(character);
                int x = 1;

                while ((charCount < input.length()-1)&&(prizeWinner.substring(0,x).equalsIgnoreCase(input.substring(0, charCount)))){

                    charCount+=1;
                    x = x+1;
                }
                prizeWinner = prizeWinner.substring(0, x).toUpperCase() + prizeWinner.substring(x);

                result.add(prizeWinner);



            } else {

                String Book = bookStore.getRandomItem(character);
                int x = 1;

                while ((charCount < input.length()-1)&&(Book.substring(0,x).equalsIgnoreCase(input.substring(0, charCount)))){


                    charCount+=1;
                    x = x+1;
                }
                Book = Book.substring(0, x).toUpperCase() + Book.substring(x);
                result.add(Book);

            }

        }
        return result;
    }


    public static void main(String[] args) throws IOException {
        //args = new String[]{"thththththththththth", "2"};



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
