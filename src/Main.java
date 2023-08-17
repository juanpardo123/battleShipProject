

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GameHelper game = new GameHelper(3,3);
        int turns = 0;



        boolean runningGame = true;
        System.out.println("Welcome to the Battleship game");
        System.out.println("");
        System.out.println("Legend: | 0:Unused space | 1:Missed | 2:Hit!!");
        printIntArray(game.getGameGrid());

        while(runningGame) {



            System.out.println(game.getTargets());
            System.out.println(game.getTargetsInt());

            //printIntArray(game.getGrid());
            String input = game.getUserInput("Select a column");
            String action = game.gameMove(input);

            System.out.println("");
            System.out.println("Legend: | 0:Unused space | 1:Missed | 2:Hit!!");

            printIntArray(game.getGameGrid());


            System.out.println(action);
            turns++;
        }

    }
    public static void printIntArray(int[] array){

       int sum = 0;
       int row = 1;
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
                if(row < 8) {
                    System.out.print(row + " ");

                }

            }

        }
        System.out.println("  ");
    }
}