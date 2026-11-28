
import java.util.*;
import Exceptions.*;
import ITEMS.*;
import MEMS.*;

public class CommandProcessor{
    private Library current;

    public CommandProcessor(){
        current=new Library();
    }

    public void run(Scanner in){
        while(in.hasNextLine()){
            String line=in.nextLine();
            if(line.trim().isEmpty()) break;
            execute(line);
        }
    }

    private void execute(String line){
        try{
            String[] p=line.split("\\|",-1);

            switch(p[0]){
                case "ADD_BOOK":
                    current.addItem(new Book(p[1],p[2],p[3]));
                    break;

                case "ADD_MAGAZINE":
                    current.addItem(new Magazine(p[1],p[2],p[3]));
                    break;

                case "ADD_REFERENCE":
                    current.addItem(new ReferenceBook(p[1],p[2],p[3]));
                    break;

                case "ADD_MEMBER":
                    if(p[3].equals("STUDENT")){
                        current.addMember(new Student(p[1],p[2]));
                    }
                    else if(p[3].equals("STAFF")){
                        current.addMember(new Staff(p[1],p[2]));
                    }
                    else if(p[3].equals("FACULTY")){
                        current.addMember(new Faculty(p[1],p[2]));
                    }
                    break;

                case "BORROW":
                    current.Borrow(p[1],p[2],false);
                    break;

                case "RETURN":
                    current.Return(p[1]);
                    break;

                case "ADVANCE":
                    current.advance(Integer.parseInt(p[1]));
                    break;

                case "SEARCH":
                    current.search(p[1]);
                    break;

                case "LOANS":
                    current.loansOf(p[1]);
                    break;

                case "OVERDUE":
                    current.overdue();
                    break;

                case "INVENTORY":
                    current.inventory();
                    break;
                case "RESERVE":
                    current.reserve(p[1],p[2]);
            }
        }
        catch(LibraryException e){
            System.out.println("ERROR "+e.getMessage());
        }
    }
}
