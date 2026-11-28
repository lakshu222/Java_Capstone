package MEMS;
public class Student extends Member{
    public Student(String id,String name){
        super(id,name);
    }
    public int getperiod(){
        return 14;
    }
    public int getmax(){
        return 3;
    }
}
