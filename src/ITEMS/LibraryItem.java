package ITEMS;
abstract public class LibraryItem{
    private String id,title;
    public LibraryItem(String id,String title){
        this.id=id;
        this.title=title;
    }
    public String getid(){
        return id;
    }
    public String gettitle(){
        return title;
    }
    abstract public String gettype();
}
