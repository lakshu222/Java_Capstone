abstract public class Member extends Library {
    private String id;
    private String name;
    Member(String id,String name){
        this.id=id;
        this.name=name;
    }
    public String getid(){
        return id;
    }
    abstract public int getperiod();
    abstract public int getmax();
}
