public class GOL {
	public static void main(String [] args) {
		int [][] board = new int[20][20];

		//Randomly populate the board with true or false values
		for(int i =0; i < board.length; i++) {
			for(int j =0; j < board[i].length; j++) {
				if(Math.random() < .5) //Math.random() returns a value between 0 and 1, 5050 chance maybe? no true random in programming...
					board[i][j] = 1;
				else
					board[i][j] = 0;
			}
		}

		print(board); //Prints initial board

		for(int i = 0; i < 25; i++) { //Number of generations
			board = generation(board); //Feed the current board into generation
									  //Generation will return the updated board
			System.out.println("Generation " + i + ":  ");
			print(board);  //prints current board to command line;						  
		}
	}

	/**
	 * generation: 
	 * Method is control structure for updating the board
	 * Args -  integer [] [] board:  Our current generation board
	 * Returns - Retunrs updated board updatedBoard
	 */
	public static int [][] generation(int [][] board) {

		int [][] updatedBoard = new int[20][20];  //We need to declare a new board otherwise we will be checking the updated
														  //cell statuses that we had just did rather than the original which is part of game

		//loop through each cell in board
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				int numNeighborsAlive = checkNeighbors(board, i, j);  //check neighbors of current location
				int newCellStatus = checkRules(numNeighborsAlive, board[i][j]); //Decide what to do based off of num neighbors
				updatedBoard[i][j] = newCellStatus;  //update cell status
			}
		}

		return updatedBoard; //Returns back to original
	}

	/**
	 * checkNeighbors:
	 * Method checks near by cells for number of alive cells
	 * Args - Boolean[][] current board; i - X location; j - y location;
	 * Returns - number of neighbors alive
	 * */
	public static int checkNeighbors(int [][] board, int j, int i) {
		
		int numNeighbors = 0; //Declare originally none alive
		//fringe cases
		//IF ON TOP ROW
		if(i == 0) {
			//IF ON LEFT SIDE
			if(j == 0)
				numNeighbors = board[i+1][j] + board[i+1][j+1] + board[i][j+1];
			//Top right corner
			else if(j ==19) 
				numNeighbors = board[i+1][j] + board[i+1][j-1] + board[i][j-1];
			//Otherwise its in the middle of it all :)
			else 
				numNeighbors = board[i+1][j] + board[i+1][j-1] + board[i][j-1] + board[i+1][j+1] + board[i][j+1];
		}
		//Check if on bottom row
		else if(i==19) {
			//if on left side
			if(j==0)
				numNeighbors = board[i-1][j] + board[i-1][j+1] + board[i][j+1];
			//bottom right corner
			else if(j==19)
				numNeighbors = board[i-1][j] + board[i-1][j-1] + board[i][j-1];
			else
				numNeighbors = board[i-1][j] + board[i-1][j-1] + board[i][j-1] + board[i][j+1] + board[i-1][j+1];
		}
		else if(j==0) { //Left side of the board, not the corners
			numNeighbors = board[i+1][j] + board[i+1][j+1] + board[i][j+1] + board[i-1][j+1] + board[i-1][j];
		}
		else if(j==19) { //Right side of the board, not the corners
			numNeighbors =  board[i+1][j] + board[i+1][j-1] + board[i][j-1] + board[i-1][j-1] + board[i-1][j];
		}
		else {  //catch all middle casses, all 8 sumed
			numNeighbors = board[i-1][j-1] + board[i-1][j] + board[i-1][j+1] + board[i][j+1] + board[i+1][j+1] + board[i+1][j] + board[i+1][j-1] + board[i][j-1];
		}

		return numNeighbors;
	}

	/**
	 * checkRules:
	 * returns what the status of the updated cell should be 
	 * Args - int cellsAlive number of cells alive as given by checkNeighbors 
	 *        int currentCellStatus - if cell is currently alive or dead
	 * Return - int cellStatus one or zero value on updated cell based on rules 
	 */
	public static int checkRules(int cellsAlive, int currentCellStatus) {

		if(cellsAlive < 2) //two kills cell under population
			return 0;
		else if ((cellsAlive == 2 || cellsAlive ==3) && currentCellStatus == 1) //2 or 3, cell survives
			return 1; 
		else if(cellsAlive == 3 && currentCellStatus == 0) //dead cell comes to life on 3
			return 1;
		else
			return 0;
	}
	
	/**
	 * print
	 * iterates through the board and prints status
	 * Args - board = the board of life
	 */
	public static void print(int [][] board) {
		System.out.println(" New Generation");
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println(" ");
		}
	}


}
