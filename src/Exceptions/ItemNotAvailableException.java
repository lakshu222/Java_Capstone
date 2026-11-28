package Exceptions;
public class ItemNotAvailableException extends LibraryException{
    public ItemNotAvailableException(String msg){
        super(msg);
    }
}