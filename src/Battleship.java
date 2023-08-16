import java.util.ArrayList;

public class Battleship {
    ArrayList<String> location;


    String Name;
    boolean sunk = false;
    public Battleship(ArrayList<String> coordinates, String name){
        location = coordinates;
         Name = name;

    }
}
