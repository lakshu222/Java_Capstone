public class ReferenceBook extends LibraryItem implements Borrowable {
    private String issue;
    ReferenceBook(String id,String title,String issue){
        super(id,title);
        this.issue=issue;
    }
}
