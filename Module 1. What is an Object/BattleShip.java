import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class BattleShip {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, the ocean is empty.");
        String[][] map = new String[10][10];
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                map[r][c] = " ";
            }
        }
        showOceanMap(map);
        deployPlayerShips(map, input);
        deployComputerShips(map);
        battle(map, input);
    }

    public static void showOceanMap(String[][] map) {
        for (int r = 0; r < map.length; r++) {
            if (r == 0) {
                System.out.println("  0123456789");
            }
            System.out.print(r + "|");
            for (int c = 0; c < map[0].length; c++) {
                if (map[r][c].equals("1")) {
                    System.out.print("@");
                } else if (map[r][c].equals("2") | map[r][c].equals("0")) {
                    System.out.print(" ");
                } else {
                    System.out.print(map[r][c]);
                }
            }
            System.out.println("|" + r);
            if (r == map.length-1) {
                System.out.println("  0123456789");
            }
        }
    }

    public static void deployPlayerShips(String[][] map, Scanner input) {
        int shipsDeployed = 1;
        while (shipsDeployed <= 5) {
            System.out.print("Enter X coordinate for your ship #" + shipsDeployed + ": ");
            int x = input.nextInt();
            while (x < 0 | x > 9) {
                System.out.println("Please enter X coordinate in range 0 to 9");
                x = input.nextInt();
            }
            System.out.print("Enter Y coordinate for your ship #" + shipsDeployed + ": ");
            int y = input.nextInt();
            while (y < 0 | y > 9) {
                System.out.println("Please enter Y coordinate in range 0 to 9");
                y = input.nextInt();
            }
            if (map[x][y].equals(" ")) {
                map[x][y] = "1";
                shipsDeployed++;
            } else if (map[x][y].equals("1")) {
                System.out.println("There is a ship already there, try again");
            }
        }
        showOceanMap(map);
    }

    public static void deployComputerShips(String[][] map) {
        System.out.println("Computer is deploying ships...");
        int shipsDeployed = 1;
        while (shipsDeployed <= 5) {
            int x = ThreadLocalRandom.current().nextInt(0, 10);
            int y = ThreadLocalRandom.current().nextInt(0, 10);
            if (map[x][y].equals(" ")) {
                map[x][y] = "2";
                System.out.println("Ship #" + shipsDeployed + " deployed");
                shipsDeployed++;
            }
        }
    }

    public static void battle(String[][] map, Scanner input) {
        System.out.println("Let the battle BEGIN...");
        int playerShips = 5;
        int computerShips = 5;
        boolean gameOver = false;
        while (!gameOver) {
            System.out.println("Player! It's your turn");
            System.out.println("Enter X coordinate");
            int x = input.nextInt();
            while (x < 0 | x > 9) {
                System.out.println("Please enter X coordinate in range 0 to 9");
                x = input.nextInt();
            }
            System.out.print("Enter Y coordinate");
            int y = input.nextInt();
            while (y < 0 | y > 9) {
                System.out.println("Please enter Y coordinate in range 0 to 9");
                y = input.nextInt();
            }
            if (map[x][y].equals("2")) {
                System.out.println("BOOOOOOOM!!! You sunk enemy's ship!!!");
                map[x][y] = "!";
                computerShips--;
            } else if (map[x][y].equals("1")) {
                System.out.println("Uffff... The cannon backfired :( You sunk your own ship :(");
                map[x][y] = "X";
                playerShips--;
            } else {
                System.out.println("Sorry, you missed");
                map[x][y] = "-";
            }
            showOceanMap(map);
            System.out.println("Your ships: " + playerShips + " | Computer ships: " + computerShips);
            System.out.println("....");
            System.out.println("Computer's turn");
            boolean validShot = false;
            int[][] shots = new int[10][10];
            while (!validShot) {
                x = ThreadLocalRandom.current().nextInt(0, 10);
                y = ThreadLocalRandom.current().nextInt(0, 10);
                if (shots[x][y] == 1) {
                    validShot = false;
                } else if (map[x][y].equals("1")) {
                    System.out.println("BOOOOOOOM!!! Computer sunks your ship!!!");
                    map[x][y] = "X";
                    shots[x][y] = 1;
                    playerShips--;
                    validShot = true;
                } else if (map[x][y].equals("2")) {
                    System.out.println("Computer's cannon backfired, she sunks her own ship");
                    map[x][y] = "!";
                    shots[x][y] = 1;
                    computerShips--;
                    validShot = true;
                } else {
                    System.out.println("Computer missed");
                    shots[x][y] = 1;
                    validShot = true;
                }
            }
            showOceanMap(map);
            System.out.println("Your ships: " + playerShips + " | Computer ships: " + computerShips);
            if (playerShips == 0 | computerShips == 0) {
                gameOver = true;
            }
        }
        System.out.println("Your ships: " + playerShips + " | Computer ships: " + computerShips);
        if (computerShips == 0) {
            System.out.println("Congrats! You defeated computer!");
        } else {
            System.out.println("Computer destoyed you like a baby!");
        }
    }
}
