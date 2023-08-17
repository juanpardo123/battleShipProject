import java.util.*;


public class GameHelper {
    ArrayList<String> shipNames = new ArrayList<String>();

    private static final String ALPHABET = "abcdefg";
    private static final int GRID_LENGTH = 7;
    private static final int GRID_SIZE = 49;
    private static final int MAX_ATTEMPTS = 200;

    static final int HORIZONTAL_INCREMENT = 1;          // A better way to represent these two
    static final int VERTICAL_INCREMENT = GRID_LENGTH;  // things is an enum (see Appendix B)

    private final int[] grid = new int[GRID_SIZE];

    private final int[] gameGrid = new int[GRID_SIZE];

    private ArrayList<String> targetsAlpha = new ArrayList<>();

    private ArrayList<ArrayList> targetsGrouped = new ArrayList<>();

    private ArrayList<Battleship> activeShips = new ArrayList<>();
    private final Random random = new Random();

    public ArrayList<ArrayList> targets = new ArrayList<>();

    private int startupCount = 0;

    public GameHelper(int shipSize, int numShips){
        initGamegrid();
        initNames();

        for(int i = 0; i < numShips; i++){
            ArrayList<String> ship = placeStartup(shipSize);
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



    public ArrayList<String> getTargets() {
        return targetsAlpha;
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
    private String  randomName(){

        Random rand = new Random();
        int randomIndex = rand.nextInt(shipNames.size());
        String name = shipNames.get(randomIndex);
        shipNames.remove(randomIndex);
        return name;
    }
    public ArrayList<ArrayList> getTargetsInt() {
        return targetsGrouped;
    }

    public String getUserInput(String prompt) {
        System.out.print(prompt + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase();
    } //end getUserInput

    public ArrayList<String> placeStartup(int startupSize) {
        // holds index to grid (0 - 48)
        int[] startupCoords = new int[startupSize];         // current candidate co-ordinates
        int attempts = 0;                                   // current attempts counter
        boolean success = false;                            // flag = found a good location?

        startupCount++;                                     // nth Startup to place
        int increment = getIncrement();                     // alternate vert & horiz alignment

        while (!success & attempts++ < MAX_ATTEMPTS) {      // main search loop
            int location = random.nextInt(GRID_SIZE);         // get random starting point

            for (int i = 0; i < startupCoords.length; i++) {  // create array of proposed coords
                startupCoords[i] = location;                    // put current location in array
                location += increment;                          // calculate the next location
            }
            //System.out.println("Trying: " + Arrays.toString(startupCoords));

            if (startupFits(startupCoords, increment)) {      // startup fits on the grid?
                success = coordsAvailable(startupCoords);       // ...and locations aren't taken?
            }                                                 // end loop
        }                                                   // end while

        savePositionToGrid(startupCoords);                  // coords passed checks, save
        ArrayList<String> alphaCells = convertCoordsToAlphaFormat(startupCoords);
        //System.out.println("Placed at: "+ alphaCells);
        return alphaCells;
    } //end placeStartup

    boolean startupFits(int[] startupCoords, int increment) {
        int finalLocation = startupCoords[startupCoords.length - 1];
        if (increment == HORIZONTAL_INCREMENT) {
            // check end is on same row as start
            return calcRowFromIndex(startupCoords[0]) == calcRowFromIndex(finalLocation);
        } else {
            return finalLocation < GRID_SIZE;                 // check end isn't off the bottom
        }
    } //end startupFits

    boolean coordsAvailable(int[] startupCoords) {
        for (int coord : startupCoords) {                   // check all potential positions
            if (grid[coord] != 0) {                           // this position already taken
                //System.out.println("position: " + coord + " already taken.");
                return false;                                   // NO success
            }
        }
        return true;                                        // there were no clashes, yay!
    } //end coordsAvailable

    void savePositionToGrid(int[] startupCoords) {
        for (int index : startupCoords) {
            grid[index] = 1;                                  // mark grid position as 'used'
        }
    } //end savePositionToGrid

    private ArrayList<String> convertCoordsToAlphaFormat(int[] startupCoords) {
        ArrayList<String> alphaCells = new ArrayList<String>();
        for (int index : startupCoords) {                   // for each grid coordinate
            String alphaCoords = getAlphaCoordsFromIndex(index); // turn it into an "a0" style
            alphaCells.add(alphaCoords);                      // add to a list
        }
        return alphaCells;                                  // return the "a0"-style coords
    } // end convertCoordsToAlphaFormat

    String getAlphaCoordsFromIndex(int index) {
        int row = calcRowFromIndex(index);                  // get row value
        int column = index % GRID_LENGTH;                   // get numeric column value

        String letter = ALPHABET.substring(column, column + 1); // convert to letter
        return letter + row;
    } // end getAlphaCoordsFromIndex

    private int calcRowFromIndex(int index) {
        return index / GRID_LENGTH;
    } // end calcRowFromIndex

    private int getIncrement() {
        if (startupCount % 2 == 0) {                        // if EVEN Startup
            return HORIZONTAL_INCREMENT;                      // place horizontally
        } else {                                            // else ODD
            return VERTICAL_INCREMENT;                        // place vertically
        }
    }

    public int[] getGrid(){
        return grid;
    }
    public String indexToAlpha(int index){
        return getAlphaCoordsFromIndex(index);
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

    private void initGamegrid(){
        for (int e: gameGrid){
            e = 0;
        }
    }

    public int[] getGameGrid (){
        return gameGrid;
    }

    public String gameMove(String move){
        int index = alphaToIndex(move);


        if(index < 0){
            return "Invalid input please try again";
        }
        if(gameGrid[index] == 0){
            for(Battleship e: activeShips){
                e.checkHit(index);
            }
            if(grid[index] == 1){
                gameGrid[index] = 2;

                return "its a hit!!!!";
            }
            else{
                gameGrid[index] = 1;
                return "its a miss!!!!";
            }

        }else{
            return "That position has already been used, Try again";
        }

    }

    public ArrayList<ArrayList> getTargetsGrouped() {
        return targetsGrouped;
    }

    private ArrayList<Integer> shipToLocation(ArrayList<String> ship){
            ArrayList<Integer> location = new ArrayList<>();
            for(String e: ship){

                location.add(alphaToIndex(e));
            }
        return location;
    }

    public ArrayList<Battleship> getActiveShips() {
        return activeShips;
    }

    //end getIncrement
} //end class
