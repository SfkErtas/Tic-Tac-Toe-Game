package com.javaclass;
import java.util.*;
import java.util.Scanner;
import com.javaclass.services.UserServiceImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Configuration
@ComponentScan
public class JavaClassApplication{
	static String[] board;
	static String turn;
	static String checkWinner() {
		for (int a = 0; a < 8; a++) {
			String line = switch (a) {
				case 0 -> board[0] + board[1] + board[2];
				case 1 -> board[3] + board[4] + board[5];
				case 2 -> board[6] + board[7] + board[8];
				case 3 -> board[0] + board[3] + board[6];
				case 4 -> board[1] + board[4] + board[7];
				case 5 -> board[2] + board[5] + board[8];
				case 6 -> board[0] + board[4] + board[8];
				case 7 -> board[2] + board[4] + board[6];
				default -> null;
			};

			if (line.equals("XXX")) {
				return "X";
			}

			else if (line.equals("OOO")) {
				return "O";
			}
		}
		for (int a = 0; a < 9; a++) {
			if (Arrays.asList(board).contains(String.valueOf(a + 1)))
			{
				break;
			} else if (a == 8)
			{
				return "draw";
			}
		}
		System.out.println(turn + "'s turn; enter a slot number to place " + turn + " in:");
		return null;
	}
	static void printBoard() {
		System.out.println("|---|---|---|");
		System.out.println("| " + board[0] + " | "
				+ board[1] + " | " + board[2]
				+ " |");
		System.out.println("|-----------|");
		System.out.println("| " + board[3] + " | "
				+ board[4] + " | " + board[5]
				+ " |");
		System.out.println("|-----------|");
		System.out.println("| " + board[6] + " | "
				+ board[7] + " | " + board[8]
				+ " |");
		System.out.println("|---|---|---|");
	}

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(JavaClassApplication.class, args);
		UserServiceImp userServiceImp = context.getBean(UserServiceImp.class);
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter user name: ");
		String name = scanner.nextLine();
		userServiceImp.saveUser(name);
		String name2;
		do{
			System.out.print("Enter second user name: ");
			name2 = scanner.nextLine();
			if (name.equals(name2))
			{
				System.out.println("The two names cannot be the same.");
			}
		} while(name.equals(name2));
		userServiceImp.saveUser(name2);
		System.out.println("User saved successfully!");
		Scanner in = new Scanner(System.in);
		board = new String[9];
		turn = name;
		String winner = null;
		for (int a = 0; a < 9; a++) {
			board[a] = String.valueOf(a + 1);
		}
		System.out.println("Welcome to 3x3 Tic Tac Toe.");
		printBoard();
		System.out.println(turn + " will play first. Enter a slot number to place X in:");
		while (winner == null) {
			int numInput;
			try {
				numInput = in.nextInt();
				if (!(numInput > 0 && numInput <= 9)) {
					System.out.println("Invalid input; re-enter slot number:");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input; re-enter slot number:");
				continue;
			}
			if (board[numInput - 1].equals(String.valueOf(numInput))) {

				if (turn.equals(name)) {
					turn = name2;
					board[numInput - 1] = "X";
				} else {
					turn = name;
					board[numInput - 1] = "O";
				}
				printBoard();
				winner = checkWinner();
			} else {
				System.out.println("Slot already taken; re-enter slot number:");
			}
		}
		if (winner.equals("X")){
			userServiceImp.winner(name);
			userServiceImp.loser(name2);
		}
		else if (winner.equals("O")) {
			userServiceImp.winner(name2);
			userServiceImp.loser(name);
		}
		if (winner.equalsIgnoreCase("draw")) {
			userServiceImp.draw(name);
			userServiceImp.draw(name2);
			System.out.println("It's a draw! Thanks for playing.");
		}
		else {
			System.out.println("Congratulations! " + winner + "'s have won! Thanks for playing.");
		}
		in.close();
	}
}
