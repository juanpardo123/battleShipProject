import ch6.GameHelper;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ch6.GameHelper game = new GameHelper(3,3);

        boolean runningGame = true;
        while(runningGame) {



            System.out.println(game.getTargets());
            System.out.println(game.getTargetsInt());

            printIntArray(game.getGrid());

            printIntArray(game.getGameGrid());

            String input = game.getUserInput("Select a column");
            game.gameMove(input);

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