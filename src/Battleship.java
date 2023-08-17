import java.util.ArrayList;


public class Battleship {
    ArrayList<Integer> location;
    ArrayList<Boolean> hits = new ArrayList<Boolean>();

    String Name;
    boolean sunk = false;
    public Battleship(ArrayList<Integer> coordinates, String name){
        location = coordinates;
         Name = name;
         for(Integer e: location){
             hits.add(false);
         }

    }
    public String checkHit(int index) {
        if (!sunk) {

            if (hits.indexOf(false) != -1) {

                int hitsIndex = location.indexOf(index);
                if (hitsIndex < 0) {

                } else {
                    hits.set(hitsIndex, true);

                }
            }
            if(hits.indexOf(false) == -1) {

                sunk = true;
                return ("You sank The " + Name);
            }
            return "";
        }
        return "";
    }
}
