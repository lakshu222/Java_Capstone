public class Faculty extends Member{
    Faculty(String id,String name){
        super(id,name);
    }
    public int getperiod(){
        return 30;
    }
    public int getmax(){
        return 5;
    }
}
