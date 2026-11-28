package MEMS;
public class Faculty extends Member{
    public Faculty(String id,String name){
        super(id,name);
    }
    public int getperiod(){
        return 30;
    }
    public int getmax(){
        return 5;
    }
}
