package Exceptions;
public class BorrowLimitExceededException extends LibraryException{
    public BorrowLimitExceededException(String id,int max){
        super(id+" has reached the limit of "+max);
    }
}