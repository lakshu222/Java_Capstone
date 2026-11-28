package ITEMS;
public class ReferenceBook extends LibraryItem {
    private String issue;
    public ReferenceBook(String id,String title,String issue){
        super(id,title);
        this.issue=issue;
    }
    public String gettype(){
        return "REFERENCE";
    }
}
