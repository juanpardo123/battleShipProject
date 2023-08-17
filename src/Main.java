

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GameHelper game = new GameHelper(3,3);
        int turns = 0;
        int maxturns = 25;



        boolean runningGame = true;
        System.out.println("Welcome to the Battleship game");
        System.out.println("");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Legend: | 0:Unused space | 1:Missed | 2:Hit!! | Ships left: " + game.getActiveShips().size());
        printIntArray(game.getGameGrid());

        while(runningGame) {



           // System.out.println(game.getTargets());
           // System.out.println(game.targets);
           System.out.println(game.getTargetsInt());

            //printIntArray(game.getGrid());
            String input = game.getUserInput("Select a column");
            System.out.println("-------------------------------------------------------------------------------");
            String action = game.gameMove(input);

            System.out.println("");

            System.out.println("Legend: | 0:Unused space | 1:Missed | 2:Hit!! | Ships left: " + game.getActiveShips().size());

            printIntArray(game.getGameGrid());


            System.out.println(action);
            turns++;
            if(game.getActiveShips().size()<1){
                runningGame = false;
                System.out.println("you won!!! it took you " + turns + " turns.");
            }
            if(turns > maxturns){
                runningGame = false;
                System.out.println("You lost!!! You ran out of Turns :( ");
            }
        }

    }
    public static void printIntArray(int[] array){

       int sum = 0;
       int row = 0;
       System.out.println("--|A||B||C||D||E||F||G|");
       System.out.println("");
        System.out.print(row + " ");
        for(int e: array){

            if(sum<6){
                System.out.print("|"+ e + "|");
                sum++;
            }else{

                    System.out.println("|" + e + "|");
                    row++;
                sum = 0;
                if(row < 7) {
                    System.out.print(row + " ");

                }

            }

        }
        System.out.println("  ");
    }
}