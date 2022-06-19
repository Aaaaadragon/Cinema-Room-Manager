package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    private static int row;
    private static int col;
    private static int numberOfSeats = 0;
    private static char[][] seatLayout;
    private static Scanner scanner = new Scanner(System.in);
    private static int currentIncome = 0;
    private static int totalIncome = 0;
    private static int soldTickets = 0;
    private static Boolean stopProgram = false;


    public static void main(String[] args) {

        configSeats();

        while (!stopProgram){
            showMenu();
        }
    }

    public static void configSeats(){
        System.out.println("Enter the number of rows:");
        row = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        col = scanner.nextInt();


        System.out.println();

        numberOfSeats = row * col;

        if(row%2 == 0){
            totalIncome = (numberOfSeats/2)*8 + (numberOfSeats/2)*10;
        }else{
            totalIncome = (row/2+1)*col*8 + (row/2)*col*10;
        }

        seatLayout = new char[row][col];

        for (char[] seat: seatLayout){
            Arrays.fill(seat,'S');
        }
    }

    public static void showMenu(){
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int actionCode = scanner.nextInt();
        actionMenu(actionCode);
    }
    
    public static void actionMenu(int actionCode){
        switch (actionCode){
            case 1:
                showSeats();
                break;
            case 2:
                buyTicket();
                break;
            case 3:
                statistics();
                break;
            case 0:
                stopProgram = true;
        }

    }

    public static void showSeats(){
        System.out.println();
        System.out.println("Cinema:");
        for (int i = 1; i <= seatLayout[0].length; i++){
            if(i==1) System.out.print(" ");
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < seatLayout.length; i++){
            System.out.print(i + 1);
            for (int j = 0; j < seatLayout[0].length; j++){
                System.out.print(" " + seatLayout[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void buyTicket(){
        boolean buyingTicket = true;
        while (buyingTicket) {
            System.out.println();
            System.out.println("Enter a row number:");
            int selectedRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int selectedCol = scanner.nextInt();
            System.out.println();
            if (selectedRow > row || selectedRow < 0 ||selectedCol > col || selectedCol <= 0) {
                System.out.println("Wrong input!");
                continue;
            }else if (seatLayout[selectedRow-1][selectedCol-1] == 'B') {
                System.out.println("That ticket has already been purchased!");
                continue;
            }
            seatLayout[selectedRow-1][selectedCol-1] = 'B';
            soldTickets += 1;
            buyingTicket = false;
            if (numberOfSeats <= 60) {
                System.out.println("Ticket price: $10");
//                totalIncome += 10;
                currentIncome += 10;
            } else if (selectedRow <= row/2) {
                    System.out.println("Ticket price: $10");
//                    totalIncome += 10;
                    currentIncome += 10;
            } else {
                    System.out.println("Ticket price: $8");
//                    totalIncome += 8;
                    currentIncome += 8;
            }
            System.out.println();
            showSeats();
        }

    }

    public static void statistics(){
        System.out.println();
        System.out.println("Number of purchased tickets: " + soldTickets);
        float soldTicketsPercentage = (float) soldTickets / (float) numberOfSeats * 100f;
        System.out.printf("Percentage: %.2f%%%n", soldTicketsPercentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }


}