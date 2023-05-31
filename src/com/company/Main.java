package com.company;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        System.out.println("Rules:");
        System.out.println("Black Jack is played with 52 cards (2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A in four suits)");
        System.out.println("The player's goal is to beat the dealer. The goal is to have a higher card total than the dealer and at the same time not exceed the total of 21.");
        System.out.println("If the total exceeds 21, the player loses his bet.");
        System.out.println("Cards 2 through 10 have the value shown on the card. The J (Jack), Q (Queen) and K (King) cards are worth 10 points.");
        System.out.println("The value of A (Ace) can be chosen by the player - 1 point or 11 points.");
        System.out.println("Game Progress");
        System.out.println("Each player is dealt 2 cards");
        System.out.println("Then you have two options:");
        System.out.println("Hit - Take another card");
        System.out.println("Stand - End your game");
        System.out.println();
        System.out.println("You start with $1000");
        System.out.println();

        boolean endGame;
        int totalMoney = 1000;
        String [] cardSuits = {"\u2663", "\u2660", "\u2666", "\u2665"};
        String[] cardsChar = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        int[] cardsValue = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

        do {
            endGame = false;
            System.out.println("You have total: $" + totalMoney);
            int bet;
            do {
                System.out.print("Enter amount of money you want to bet: ");
                bet = sc.nextInt();

            }while (bet > totalMoney);
            totalMoney -= bet;

            String[][] myCardsString = new String[20][2];
            int myCardsCount = 0;
            int myCardsTotal = 0;

            for (int i = 0; i < 2; i++) {
                int num = r.nextInt(cardsChar.length);
                int suit = r.nextInt(cardSuits.length);
                myCardsString[i][0] = cardsChar[num];
                myCardsString[i][1] = cardSuits[suit];
                if (num == 12) {
                    int y;
                    do {
                        System.out.println("You get Ace");
                        System.out.print("Pick 1 or 11 points: ");
                        y = sc.nextInt();
                        myCardsString[i][0] = "A(" + y + ")";
                    } while (y != 1 && y != 11);
                    myCardsTotal += y;
                } else {
                    myCardsTotal += cardsValue[num];
                }

                myCardsCount++;
            }
            System.out.println();
            System.out.println("Your Cards: " + myCardsString[0][0] + myCardsString[0][1] + " " + myCardsString[1][0] + myCardsString[1][1]);


            boolean index;
            do {
                index = false;
                System.out.println();
                System.out.println("Hit - Press 1");
                System.out.println("Stand - Press 2");
                System.out.print("Enter - ");
                int choice = sc.nextInt();
                if (choice == 1) {
                    boolean randomCycle;
                    do {
                        randomCycle = false;

                        int num = r.nextInt(cardsChar.length);
                        int suit = r.nextInt(cardSuits.length);

                        myCardsString[myCardsCount][0] = cardsChar[num];
                        myCardsString[myCardsCount][1] = cardSuits[suit];

                        if (num == 12) {
                            int y;
                            do {
                                System.out.println("You get Ace");
                                System.out.print("Pick 1 or 11 points: ");
                                y = sc.nextInt();
                                myCardsString[myCardsCount][0] = "A(" + y + ")";
                            } while (y != 1 && y != 11);
                            myCardsTotal += y;
                        } else {
                            myCardsTotal += cardsValue[num];
                        }

                        for (int i = 0; i < myCardsCount-1; i++) {
                            if (!Objects.equals(myCardsString[myCardsCount][0], myCardsString[i][0]) || !Objects.equals(myCardsString[myCardsCount][1], myCardsString[i][1])) {
                                randomCycle = true;
                                break;
                            }
                        }

                    }while (!randomCycle);


                    myCardsCount++;
                    System.out.println();
                    System.out.print("Your Cards: ");
                    for (int i = 0; i < myCardsCount; i++) {
                        System.out.print(myCardsString[i][0] + myCardsString[i][1] + " ");
                    }
                    System.out.println();

                    if (myCardsTotal > 21) index = true;

                } else if (choice == 2) index = true;


            } while (!index);

            if (myCardsTotal > 21) {
                System.out.println();
                System.out.println("You have more than 21");
                System.out.println("You lose your bet!");
            }
            else {
                int dealerTotal = 0;
                String [][] dealerCardsString = new String[20][2];
                int dealerCardsCount = 0;

                do {
                    int y;
                    boolean randomCycle = false;

                    do {

                        int num = r.nextInt(cardsChar.length);
                        int suit = r.nextInt(cardSuits.length);

                        dealerCardsString[dealerCardsCount][0] = cardsChar[num];
                        dealerCardsString[dealerCardsCount][1] = cardSuits[suit];

                        if (num == 12) {
                            if (dealerTotal + 11 > 21) {
                                //pick 1
                                y = 1;
                                dealerCardsString[dealerCardsCount][0] = "A(1)";
                            }
                            else {
                                //pick 11
                                y = 11;
                                dealerCardsString[dealerCardsCount][0] = "A(11)";
                            }
                        }
                        else y = cardsValue[num];

                        if (dealerCardsCount > 1) {
                            for (int i = 0; i < dealerCardsCount-1; i++) {
                                if (Objects.equals(dealerCardsString[dealerCardsCount][0], dealerCardsString[i][0]) && Objects.equals(dealerCardsString[dealerCardsCount][1], dealerCardsString[i][1])){
                                    randomCycle = false;
                                }
                                else randomCycle = true;
                            }
                        }
                        else randomCycle = true;


                    }while (!randomCycle);

                    dealerTotal += y;
                    dealerCardsCount++;
                }while (dealerTotal < 17);


                System.out.println();
                System.out.print("Dealers Cards: ");
                for (int i = 0; i < dealerCardsCount; i++) {
                    System.out.print(dealerCardsString[i][0] + dealerCardsString[i][1] + " ");
                }

                System.out.println();
                if (dealerTotal > 21) {
                    System.out.println("Dealer have more than 21!");
                    System.out.println("You won!");
                    totalMoney += bet*2;
                }
                else if (myCardsTotal > dealerTotal) {
                    System.out.println("You have more than dealer!");
                    System.out.println("You won!");
                    totalMoney += bet*2;
                }
                else if (myCardsTotal == dealerTotal) {
                    System.out.println("You have same points as dealer");
                    System.out.println("It is draw!");
                    System.out.println("You get your bet back.");
                    totalMoney += bet;
                }
                else {
                    System.out.println("Dealer have more than You!");
                    System.out.println("You lose your bet!");
                }
            }
            System.out.println();

            if (totalMoney > 0) {
                System.out.println("You have total: $" + totalMoney);
            }
            else {
                System.out.println("You have $0 money!");
                System.out.println("You are kicked out of casino!");
                break;
            }


            System.out.println();
            System.out.println("Do you want play again?");
            int playAgain;
            do {
                System.out.println("Yes - Press 1");
                System.out.println("No - Press 2");
                System.out.print("Enter: ");
                playAgain = sc.nextInt();
            }while (playAgain != 1 && playAgain != 2);

            if (playAgain == 2) endGame = true;
            System.out.println();

        } while (!endGame);

    }
}
