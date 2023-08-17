import java.util.ArrayList;
import java.util.Random;

public class BattleshipGame {
    private static final int GRID_LENGTH = 7;
    private static final int GRID_SIZE = 49;

    private ArrayList<ArrayList> targetsGrouped = new ArrayList<>();
    ArrayList<String> shipNames = new ArrayList<String>();

    private ArrayList<String> targetsAlpha = new ArrayList<>();

    private ArrayList<Battleship> activeShips = new ArrayList<>();

    public ArrayList<ArrayList> targets = new ArrayList<>();
    private final int[] gameGrid = new int[GRID_SIZE];

    public GameHelper helper;

   public BattleshipGame(int shipSize, int numShips){
       initGamegrid();
       initNames();
       helper = new GameHelper();

       for(int i = 0; i < numShips; i++){
           ArrayList<String> ship = helper.placeStartup(shipSize);
           ArrayList<Integer> locationShip = shipToLocation(ship);
           targetsGrouped.add(ship);
           targets.add(locationShip);
           Battleship ship1 = new Battleship(locationShip,randomName());
           activeShips.add(ship1);
           for(String e: ship) {
               targetsAlpha.add(e);

           }
       }
   }

    public int[] getGameGrid (){
        return gameGrid;
    }
    private void initGamegrid(){
        for (int e: gameGrid){
            e = 0;
        }
    }

    private String  randomName(){

        Random rand = new Random();
        int randomIndex = rand.nextInt(shipNames.size());
        String name = shipNames.get(randomIndex);
        shipNames.remove(randomIndex);
        return name;
    }
    public void initNames(){
        shipNames.add("Neptune's Fury");
        shipNames.add("Starlight Voyager");
        shipNames.add("Crimson Tempest");
        shipNames.add("Serenity Dawn");
        shipNames.add("Thundering Gale");
        shipNames.add("Solaris Empress");
        shipNames.add("Celestial Odyssey");
        shipNames.add("Midnight Serpent");
        shipNames.add("Aurora Borealis");
        shipNames.add("Ebon Mariner");
        shipNames.add("Ivory Horizon");
        shipNames.add("Phoenix Requiem");
        shipNames.add("Luna Mirage");
        shipNames.add("Aquamarine Dream");
        shipNames.add("Scarlet Enigma");
        shipNames.add("Silver Wing");
        shipNames.add("Golden Sovereign");
        shipNames.add("Obsidian Mistral");
        shipNames.add("Emerald Seafoam");
        shipNames.add("Radiant Leviathan");
    }

    private ArrayList<Integer> shipToLocation(ArrayList<String> ship){
        ArrayList<Integer> location = new ArrayList<>();
        for(String e: ship){

            location.add(alphaToIndex(e));
        }
        return location;
    }

    private int singleStringToInt(String letter){
        switch (letter){
            case "a":
                return 0;
            case "b":
                return 1;
            case "c":
                return 2;
            case "d":
                return 3;
            case "e":
                return 4;
            case "f":
                return 5;
            case "g":
                return 6;
            default:
                return -1;
        }
    }

    public int alphaToIndex(String alpha){



        if (alpha.length() != 2 ){
            return -1;
        }
        String l1 = String.valueOf(alpha.charAt(0));
        int l2 = Integer.parseInt(String.valueOf(alpha.charAt(1)));

        if (l2 < 0 || l2 > 6 ){
            return -1;
        }

        int valL2 = (l2) * 7;
        int valL1 = singleStringToInt(l1);

        if( valL1 < 0 || valL1 > 7 ){
            return -1;
        }

        else{
            return valL1 + valL2;
        }
    }

    public ArrayList<Battleship> getActiveShips() {
        return activeShips;
    }

    public String gameMove(String move){
        int index = alphaToIndex(move);


        if(index < 0){
            return "Invalid input please try again";
        }
        if(gameGrid[index] == 0){
            String message = "";

            if(helper.getGrid()[index] == 1){
                int removeIndex = -1;
                for(Battleship e: activeShips){
                    String result =  e.checkHit(index);
                    System.out.println("Length " + result.length());
                    if(result.length() > 0) {
                        message = result;
                        removeIndex = activeShips.indexOf(e);
                    }
                }
                if (removeIndex>-1){
                    activeShips.remove(removeIndex);
                }


                gameGrid[index] = 2;

                return "its a hit!!!! " + message ;
            }
            else{
                gameGrid[index] = 1;
                return "its a miss!!!!" ;
            }

        }else{
            return "That position has already been used, Try again";
        }

    }


}
