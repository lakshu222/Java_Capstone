package ITEMS;
public class Book extends LibraryItem implements Borrowable {
    private String author;
    public boolean borrowed=false;
    public Book(String id,String title,String author){
        super(id,title);
        this.author=author;
    }
    public int getfine(int n){
        return 2*n;
    }
    public boolean getborrowed(){
        return borrowed;
    }
    public void borrow(){
        borrowed=true;
    }
    public void retun(){
        borrowed=false;
    }
    public String gettype(){
        return "BOOK";
    }
}
