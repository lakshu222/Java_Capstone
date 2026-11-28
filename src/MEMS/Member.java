package MEMS;
abstract public class Member{
    private String id;
    private String name;
    public int itemsborrowed=0;
    public Member(String id,String name){
        this.id=id;
        this.name=name;
    }
    public String getid(){
        return id;
    }
    abstract public int getperiod();
    abstract public int getmax();
    public int getitemsborrowed(){
        return itemsborrowed;
    }
}
