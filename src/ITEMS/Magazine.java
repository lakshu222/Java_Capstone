package ITEMS;
public class Magazine extends LibraryItem implements Borrowable {
    private String author;
    public boolean borrowed=false;
    public Magazine(String id,String title,String author){
        super(id,title);
        this.author=author;
    }
    public int getfine(int n){
        return 1*n;
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
        return "MAGAZINE";
    }
}
