public class Book extends LibraryItem implements Borrowable {
    private String author;
    Book(String id,String title,String author){
        super(id,title);
        this.author=author;
    }
}
