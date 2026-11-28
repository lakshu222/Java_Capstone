package ITEMS;
public interface Borrowable { 
    int borrowed=0;
    int getfine(int n);
    boolean getborrowed();
    void borrow();
    void retun();

}