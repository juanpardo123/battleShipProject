import java.util.ArrayList;


public class Battleship {
    ArrayList<String> location;
    ArrayList<Boolean> hits = new ArrayList<Boolean>();

    String Name;
    boolean sunk = false;
    public Battleship(ArrayList<String> coordinates, String name){
        location = coordinates;
         Name = name;
         for(String e: location){
             hits.add(false);
         }

    }
    public void checkHit(int index) {
        if (!sunk) {
            if (hits.indexOf(false) > 0) {

                int hitsIndex = location.indexOf(index);
                if (hitsIndex < 0) {

                } else {
                    hits.set(hitsIndex, true);
                }
            } else {
                System.out.println("You sank " + Name);
                sunk = true;
            }
        }
    }
}
