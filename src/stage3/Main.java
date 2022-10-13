package stage3;

import java.util.*;

public class Main {

    private static String[][] battleField;
    private static String[][] field;


    private static final Scanner scanner = new Scanner(System.in);

    public static void initiateBattleField() {
        battleField = new String[11][11];
        for (int i = 0; i < battleField.length; i++) {
            for (int j = 0; j < battleField[0].length; j++) {
                if (i == 0 && j == 0) {
                    battleField[i][j] = " ";
                } else if (i == 0) {
                    battleField[i][j] = Integer.toString(j);
                } else if (j == 0) {
                    battleField[i][j] = Character.toString((char) ('A' + i - 1));
                } else {
                    battleField[i][j] = "~";
                }
            }
        }
    }
    public static void initiateGameBattleField() {
        field = new String[11][11];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (i == 0 && j == 0) {
                    field[i][j] = " ";
                } else if (i == 0) {
                    field[i][j] = Integer.toString(j);
                } else if (j == 0) {
                    field[i][j] = Character.toString((char) ('A' + i - 1));
                } else {
                    field[i][j] = "~";
                }
                System.out.print(field[i][j]+ " ");
            }
            System.out.println();
        }

    }

    public static void createBattleField(String beginning, String end, int cellLength) {
        int startX = beginning.charAt(0) - 'A' + 1;
        int startY = Integer.parseInt(beginning.substring(1));
        int endX = end.charAt(0) - 'A' + 1;
        int endY = Integer.parseInt(end.substring(1));

        if (startX == endX) {
            int sign = startY < endY ? 1 : -1;
            for (int i = 0; i < cellLength; i++) {
                battleField[startX][startY + i * sign] = "O";
            }
        } else if (startY == endY) {
            int sign = startX < endX ? 1 : -1;
            for (int i = 0; i < cellLength; i++) {
                battleField[startX + i * sign][startY] = "O";
            }
        }

    }

    public static void printBattleField() {
        for (String[] strings : battleField) {
            for (int j = 0; j < battleField[0].length; j++) {
                System.out.print(strings[j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean validateCoordinates(String beginning, String end, int requiredLength, String shipType) {
        int coordinateStartX = beginning.charAt(0) - 'A' + 1;
        int coordinateStartY = Integer.parseInt(beginning.substring(1));
        int coordinateEndX = end.charAt(0) - 'A' + 1;
        int coordinateEndY = Integer.parseInt(end.substring(1));
        if (coordinateStartX > coordinateEndX){
            int t = coordinateStartX;
            coordinateStartX = coordinateEndX;
            coordinateEndX =t;
        }

        if (coordinateStartY > coordinateEndY){
            int t = coordinateStartY;
            coordinateStartY = coordinateEndY;
            coordinateEndY = t;
        }

        if (coordinateStartX != coordinateEndX && coordinateStartY != coordinateEndY) {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }
        if (coordinateStartX == coordinateEndX) {
            if (Math.abs(coordinateStartY - coordinateEndY) != requiredLength - 1) {
                System.out.println("Error! Wrong length of the " + shipType + "! Try again:");
                return false;
            }
        }
        if (coordinateStartY == coordinateEndY) {
            if (Math.abs(coordinateStartX - coordinateEndX) != requiredLength - 1) {
                System.out.println("Error! Wrong length of the " + shipType + "! Try again:");
                return false;
            }
        }
        if (coordinateStartX < 10 && coordinateStartX == coordinateEndX) {
            if (battleField[coordinateStartX + 1][coordinateStartY].equals("O") || battleField[coordinateEndX + 1][coordinateEndY].equals("O")) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                return false;
            }
        }
        if (coordinateStartX > 1 && coordinateStartX == coordinateEndX) {
            if (battleField[coordinateStartX - 1][coordinateStartY].equals("O") || battleField[coordinateEndX - 1][coordinateEndY].equals("O")) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                return false;
            }
        }
        if (coordinateStartY > 1 && coordinateStartX == coordinateEndX) {
            if (battleField[coordinateStartX][coordinateStartY - 1].equals("O")) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                return false;
            }
        }
        if (coordinateStartY < 10 && coordinateStartX == coordinateEndX) {
            if (battleField[coordinateStartX][coordinateStartY + 1].equals("O")) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                return false;
            }
        }

        if (coordinateStartX > 1 && coordinateStartY == coordinateEndY) {
            if (battleField[coordinateStartX - 1][coordinateStartY ].equals("O")) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                return false;
            }
        }

        if (coordinateEndX < 10 && coordinateStartY == coordinateEndY) {
            if (battleField[coordinateEndX + 1][coordinateStartY ].equals("O")) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                return false;
            }
        }
        if (coordinateStartY < 10 && coordinateStartY == coordinateEndY) {
            if (battleField[coordinateStartX][coordinateStartY + 1].equals("O") || battleField[coordinateEndX][coordinateStartY + 1].equals("O")) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                return false;
            }
        }
        if (coordinateStartY > 1 && coordinateStartY == coordinateEndY) {
            if (battleField[coordinateStartX][coordinateStartY - 1].equals("O") || battleField[coordinateEndX][coordinateStartY - 1].equals("O")) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                return false;
            }
        }
        return true;
    }

    private static void initiateUserInput(int requiredLength, String message) {
        System.out.println(message);
        String beginning, end;
        while (true) {
            beginning = scanner.next();
            end = scanner.next();
            String shipName = message.substring(message.lastIndexOf("the"), message.indexOf("(") - 1);
            if (validateCoordinates(beginning, end,requiredLength, shipName)){
                createBattleField(beginning, end, requiredLength);
                printBattleField();
                return;
            }
        }


    }

    public static void startGame() {

        //int coordinateEndX = end.charAt(0) - 'A' + 1;
        //int coordinateEndY = Integer.parseInt(end.substring(1));
        System.out.println("The game starts!");
        initiateGameBattleField();

        System.out.println("Take a shot!");
        while (true) {
            String beginning = scanner.next();
            int coordinateStartX = beginning.charAt(0) - 'A' + 1;
            int coordinateStartY = Integer.parseInt(beginning.substring(1));
            if (coordinateStartX < 11 && coordinateStartY < 11) {
                if (battleField[coordinateStartX][coordinateStartY].equals("O")) {
                    battleField[coordinateStartX][coordinateStartY] = "X";
                    field[coordinateStartX][coordinateStartY] = "X";
                    fogOfWar();
                    System.out.println("You hit a ship!");
                    printBattleField();



                    break;

                }
                else if (!battleField[coordinateStartX][coordinateStartY].equals("O")) {
                    battleField[coordinateStartX][coordinateStartY] = "M";
                    fogOfWar();
                    System.out.println("You missed!");
                    printBattleField();
                    //fogOfWar();



                }
            }
            else if (coordinateStartX > 10 || coordinateStartY > 10) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            }



        }
    }
    public static void fogOfWar(){
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (battleField[i][j].equals("X")) {
                    field[i][j] = "X";
                }
                else if(battleField[i][j].equals("M")){
                    field[i][j] = "M";
                }
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }


    }

    public static void main(String[] args) {
        // Write your code here
        initiateBattleField();
        printBattleField();


        List<AbstractMap.SimpleEntry<Integer, String>> set = new ArrayList<>();
        set.add(new AbstractMap.SimpleEntry<>(5,"Enter the coordinates of the Aircraft Carrier (5 cells):"));
        set.add(new AbstractMap.SimpleEntry<>(4,"Enter the coordinates of the Battleship (4 cells):"));
        set.add(new AbstractMap.SimpleEntry<>(3,"Enter the coordinates of the Submarine (3 cells):"));
        set.add(new AbstractMap.SimpleEntry<>(3,"Enter the coordinates of the Cruiser (3 cells):"));
        set.add(new AbstractMap.SimpleEntry<>(2,"Enter the coordinates of the Destroyer (2 cells):"));

        for(var entry: set){
            initiateUserInput(entry.getKey(), entry.getValue());
        }
        startGame();

    }
}

