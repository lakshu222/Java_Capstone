import ITEMS.*;
import MEMS.*;
public class Loan {
    private LibraryItem item;
    private Member mem;
    private int borrowedday;
    private int duedate;
    Loan(LibraryItem item,Member mem,int borrowedday,int duedate){
        this.item=item;
        this.mem=mem;
        this.borrowedday=borrowedday;
        this.duedate=duedate;
    }
    public String getmemId(){
        return mem.getid();
    }
    public int getduedate(){
        return this.duedate;
    }
    public String getitemid(){
        return item.getid();
    }
}
