import java.util.Scanner;

public class ConnectFour {
	static int i;
	static int j;
	public static void main(String[] args) {
	
		char[][] board = 
				{
					{' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				};
		
	Scanner scan = new Scanner(System.in);
	
	printBoard(board);
	boolean p1 = true;
	boolean p2 = true;
	boolean go = true;
	while(go)
	{
		
		System.out.println("Player one's turn");
		
		while (p1)
		{
			
			boolean work;
			boolean won;
			boolean isTie;
			
			System.out.println("Enter your column");
			int column = scan.nextInt();
			
			column--;
			work = add(board, column, true);
			
			if (!work)
			{
				System.out.println("You have entered an invalid column enter another column");
				continue;
			}
			
			won = hasWon(board, column);
			if (won)
			{
				System.out.println("PLAYER ONE WON!!!");
				go = false;
				break;
			}
			
			isTie = isTie(board);
			if (isTie)
			{
				System.out.println("Game ends in a tie.");
				go = false;
				break;
			}
			
			printBoard(board);
			
			p1  = false;
		}
		
		if (!go)
		{
			break;
		}
		
		System.out.println("Player two's Turn");
		
		while (p2)
		{
			
			boolean work;
			boolean won;
			boolean isTie;
			
			System.out.println("Enter your column");
			int column = scan.nextInt();
			
			column--;
			work = add(board, column, false);
			
			if (!work)
			{
				System.out.println("You have entered an invalid column enter another column");
				continue;
			}
			
			won = hasWon(board, column);
			if (won)
			{
				System.out.println("PLAYER TWO WON!!!");
				go = false;
				break;
			}
			
			isTie = isTie(board);
			if (isTie)
			{
				System.out.println("Game ends in a tie.");
				go = false;
				break;
			}
			
			printBoard(board);
			
			p2  = false;
		}
		
		if (!go)
		{
			break;
		}
		
		
		p1 = true;
		p2 = true;
	}
		
		System.out.println("Final Board...");
		printBoard(board);
		
	}
	public static boolean isTie(char[][] array)
	{
		for (int i = 0; i < 6; i++)
		{
			for (j = 0; j < 7; j++)
			{
				if (array[i][j] == ' ') {return false;}
			}
		}
		return true;
	}
	
	public static boolean hasWon(char[][] arr, int column)
	{
		
		int row = findRow(arr, column) + 1;

		char win = arr[row][column];
		if (win == ' ') {return false;}

		if (row <= 2 && win == arr[row + 1][column] && win == arr[row + 2][column] && win == arr[row + 3][column]) {return true;} //down
		if (column <= 3 && row <= 2 && win == arr[row + 1][column + 1] && win == arr[row + 2][column + 2] && win == arr[row + 3][column + 3]) {return true;}//Diagonal right
		if (column <= 3 && win == arr[row][column + 1] && win == arr[row][column + 2] && win == arr[row][column + 3]) {return true;}// flat right
		if (column >= 3 && row <= 2 && win == arr[row + 1][column - 1] && win == arr[row + 2][column - 2] && win == arr[row + 3][column - 3]) {return true;}//Diagonal left
		if (column >= 3 && win == arr[row][column - 1] && win == arr[row][column - 2] && win == arr[row][column - 3]) {return true;}// flat left
		if (column >= 3 && row >= 3 && win == arr[row - 1][column - 1] && win == arr[row - 2][column - 2] && win == arr[row - 3][column - 3]) {return true;} //diagonal up left
		if (column <= 3 && row >= 3 && win == arr[row - 1][column + 1] && win == arr[row - 2][column + 2] && win == arr[row - 3][column + 3]) {return true;} //diagonal up right
		
		if (row >= 2 && row <= 4 && column <= 5 && column >= 2 && win == arr[row + 1][column + 1] && win == arr[row - 1][column - 1] && win == arr[row - 2][column - 2]) {return true;}// diagonal \ second lowest
		if (row >= 1 && row <= 3 && column <= 5 && column >= 1 && win == arr[row - 1][column - 1] && win == arr[row + 1][column + 1] && win == arr[row + 2][column + 2]) {return true;}// diagonal \ second highest
		if (row >= 2 && row <= 4 && column >= 1 && column <= 4 && win == arr[row + 1][column - 1] && win == arr[row - 1][column + 1] && win == arr[row - 2][column + 2]) {return true;}// diagonal / second lowest
		if (row >= 1 && row <= 4 && column >= 2 && column <= 5 && win == arr[row - 1][column + 1] && win == arr[row + 1][column - 1] && win == arr[row + 2][column - 2]) {return true;}// diagonal / second highest
		
		if (column >= 1 && column <= 4 && win == arr[row][column - 1] && win == arr[row][column + 1] && win == arr[row][column + 2]) {return true;} // flat left mid
		if (column >= 2 && column <= 5 && win == arr[row][column - 1] && win == arr[row][column - 2] && win == arr[row][column + 1]) {return true;} // flat right mid
		
		return false;
	}
	
	public static int findRow(char[][] arr, int column)
	{
		
		for(i = 5; i > -1; i--)
		{
			char hi = arr[i][column];
			if (hi == ' ')
			{
				return i;
			}
		}
		return -1;
		
	}
	
	public static boolean add(char[][] arr, int column, boolean turn)
	{
		
		if (column < 0 || column > 6) {return false;}
		
		int row = findRow(arr, column);
		if (row != -1)
		{
			if(turn)
			{
				arr[row][column] = 'X';
			}
			else
			{
				arr[row][column] = 'O';
			}
		return true;
		}
		return false;	
	}
	
	public static void printBoard(char[][] array)
	{
		
		System.out.println("\n+---+---+---+---+---+---+---+");
		
		for(i = 0; i < 6; i++)
		{
			for(j = 0; j < 7; j++)
			{
				System.out.print("| " + array[i][j] + " ");
			}
			System.out.print("|");
			System.out.println("\n+---+---+---+---+---+---+---+");
		}
			
		System.out.println("  1   2   3   4   5   6   7");
		
	}

}
