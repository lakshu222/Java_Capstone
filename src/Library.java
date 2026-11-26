import java.util.*;
public class Library{
    private int day;
    private Map<String,LibraryItem> items=new HashMap<>();
    private Map<String,Member> mems=new HashMap<>();
    private Map<String,Loan> loans=new HashMap<>();
    Library(){
        day=0;
    }
    public void addItem(LibraryItem item){
        items.put(item.getid(),item);
        System.out.println("OK");
    }
    public void addMember(Member mem){
        mems.put(mem.getid(),mem);
        System.out.println("OK");
    }
    void advance(int d){
        day+=d;
    }
    void Borrow(String memId,String itemid){
        int period=mems.get(memId).getperiod();
        int maxi=mems.get(memId).getmax();
        int count=0;
        for(Loan l:loans.values()){
            if(l.getmemId().equals(memId)){
                count++;
            }
        }
        

    }

}