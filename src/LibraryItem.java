abstract public class LibraryItem extends Library{
    private String id,title;
    LibraryItem(String id,String title){
        this.id=id;
        this.title=title;
    }
    public String getid(){
        return id;
    }
}
