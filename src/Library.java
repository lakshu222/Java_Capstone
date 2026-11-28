import java.util.*;
import Exceptions.*;
import ITEMS.*;
import MEMS.*;
public class Library{
    private int day;
    private Map<String,LibraryItem> items=new HashMap<>();
    private Map<String,Member> mems=new HashMap<>();
    private Map<String,Loan> loans=new HashMap<>();
    private Map<String,LinkedList<Member>> reservation=new HashMap<>();
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
        System.out.println("OK today is day "+day);
    }
    void Borrow(String memId,String itemid,boolean res) throws LibraryException{
        Member mem=mems.get(memId);
        if(mem==null){
            throw new MemberNotFoundException(memId);
        }
        int period=mem.getperiod();
        int maxi=mems.get(memId).getmax();
        int count=0;
        LibraryItem it=items.get(itemid);
        if(it==null){
            throw new ItemNotFoundException(itemid);
        }
        // for(Loan k:loans.values()){
        //     if(k.getmemId().equals(memId)){
        //         count++;
        //     }
        // }
        if(!(it instanceof Borrowable)){
        throw new ItemNotAvailableException(itemid+" cannot be borrowed");
        }
        if(it instanceof Borrowable b){
            if(b.getborrowed()){
                throw new ItemNotAvailableException(itemid+" is on loan");
            }
        }
        if(mem.getitemsborrowed()>=maxi){throw new BorrowLimitExceededException(memId,maxi);}
        Loan n=new Loan(items.get(itemid),mems.get(memId),day,day+period);
        loans.put(itemid,n);
        if(it instanceof Borrowable b){
            b.borrow();
        }
        if(!res){
        System.out.println("OK due on day "+(period+day));}
        mems.get(memId).itemsborrowed++;
    }
    void Return(String itemid) throws LibraryException{
        LibraryItem it=items.get(itemid);
        if(it==null){
            throw new ItemNotFoundException(itemid);
        }
        Loan l=loans.get(itemid);
        if(l==null){
            throw new ItemNotOnLoanException(itemid);
        }
        int dd=l.getduedate();
        int fine=0;
        if(day>dd){
            if(it instanceof Borrowable b){
            fine=b.getfine(day-dd);
            }
        }
        mems.get(loans.get(itemid).getmemId()).itemsborrowed--;
        loans.remove(itemid);
        if(it instanceof Borrowable b){
            b.retun();
        }
        if(!reservation.containsKey(itemid)){
        System.out.println("OK fine "+fine);}
        else{
            Member togive=null;
            LinkedList<Member> q=reservation.get(itemid);
            for(Member mid:q){
                if(mid.getitemsborrowed()<mid.getmax()){
                    togive=mid;
                    break;
                }
            }
            if(togive!=null){
                q.remove(togive);
                reservation.put(itemid,q);
                Borrow(togive.getid(), itemid,true);
                System.out.println("OK fine "+fine+", issued to "+togive.getid()+" due on day "+loans.get(itemid).getduedate());
            }
            else{
                System.out.println("OK fine "+fine);
            }
        }

    }

    void search(String text){
        List<LibraryItem> arr=new ArrayList<>();

        for(LibraryItem it:items.values()){
            if(it.gettitle().toLowerCase().contains(text.toLowerCase())){
                arr.add(it);
            }
        }

        arr.sort(Comparator.comparing(LibraryItem::gettitle));

        if(arr.isEmpty()){
            System.out.println("NONE");
            return;
        }

        for(LibraryItem it:arr){
            System.out.println(it.getid()+"|"+it.gettype()+"|"+it.gettitle());
        }
    }

  
    void overdue(){
        List<Loan> arr=new ArrayList<>();

        for(Loan l:loans.values()){
            if(day>l.getduedate()){
                arr.add(l);
            }
        }

        arr.sort(Comparator.comparingInt((Loan l)->day-l.getduedate()).reversed()
            .thenComparing(Loan::getitemid));

        if(arr.isEmpty()){
            System.out.println("NONE");
            return;
        }

        for(Loan l:arr){
            LibraryItem it=items.get(l.getitemid());
            int late=day-l.getduedate();

            if(it instanceof Borrowable b){
                System.out.println(l.getitemid()+"|"+l.getmemId()+"|"+late+"|"+b.getfine(late));
            }
        }
    }

    void inventory(){
        List<LibraryItem> arr=new ArrayList<>(items.values());
        arr.sort(Comparator.comparing(LibraryItem::getid));

        if(arr.isEmpty()){
            System.out.println("NONE");
            return;
        }

        for(LibraryItem it:arr){
            String k="NOT_BORROWABLE";

            if(it instanceof Borrowable b){
                if(b.getborrowed()){
                    String k2=loans.get(it.getid()).getmemId();
                    k="ON_LOAN|"+k2;
                }
                else{
                    k="AVAILABLE";
                }
            }

            System.out.println(it.getid()+"|"+it.gettype()+"|"+k);
        }
    }

    void loansOf(String memId) throws LibraryException{
        if(!mems.containsKey(memId)){
            throw new MemberNotFoundException(memId);
        }
        List<Loan> arr=new ArrayList<>();
        for(Loan l:loans.values()){
            if(l.getmemId().equals(memId)){
                arr.add(l);
            }
        }
        arr.sort(Comparator.comparingInt(Loan::getduedate)
            .thenComparing(Loan::getitemid));
        if(arr.isEmpty()){
            System.out.println("NONE");
            return;
        }
        for(Loan l:arr){
            System.out.println(l.getitemid()+"|"+items.get(l.getitemid()).gettitle()+"|"+l.getduedate());
        }
    }

    void reserve(String memId,String itemid) throws LibraryException{
        if(!mems.containsKey(memId)){
            throw new MemberNotFoundException(memId);
        }
        if(!items.containsKey(itemid)){
            throw new ItemNotFoundException(itemid);
        }
        LibraryItem dt=items.get(itemid);
        if(!(dt instanceof Borrowable)){
            throw new ItemNotAvailableException(itemid+" cannot be borrowed");
        }
        boolean k=false;
        String newmemId="";
        for(String it:loans.keySet()){
            if(it.equals(itemid)){
                k=true;
                newmemId=loans.get(it).getmemId();
            }
        }
        if(!k){
            throw new ReservationNotallowedException(itemid+" is available");
        }
        if(memId.equals(newmemId)){
            throw new ReservationNotallowedException(newmemId+" already has "+itemid);
        }
        LinkedList<Member> b=reservation.get(itemid);
        if(b!=null){
            for(Member m:b){
                if(m.getid().equals(memId)){
                    throw new ReservationNotallowedException(memId+" already reserved "+itemid);
                }
            }
            b.add(mems.get(memId));
            reservation.put(itemid,b);
            System.out.println("OK reserved");
            return;
        }
        LinkedList<Member> v=new LinkedList<>();
        v.add(mems.get(memId));
        reservation.put(itemid,v);
        System.out.println("OK reserved");
    }
}