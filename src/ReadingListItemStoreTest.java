public class ReadingListItemStoreTest {
    public static void main(String[] args) {
        ReadingListItemStore store = new ReadingListItemStore();
        store.put("a", "Alexandre Dumas");
        store.put("ph", "Philip Larkin");
        System.out.println(store.getRandomItem("a"));
        System.out.println(store.getRandomItem("ph"));

    }
}
