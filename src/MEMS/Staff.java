package MEMS;
public class Staff extends Member{
    public Staff(String id,String name){
        super(id,name);
    }
    public int getperiod(){
        return 21;
    }
    public int getmax(){
        return 4;
    }
}
