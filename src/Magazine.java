public class Magazine extends LibraryItem implements Borrowable {
    private String author;
    Magazine(String id,String title,String author){
        super(id,title);
        this.author=author;
    }
}
