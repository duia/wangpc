package com.wpc.test;

public class MyTest {

	static int MAX = 8;

	static int board[][] = new int[MAX][MAX];

	public static void main(String[] args) {

		if(queue(0))
			printBoard();

	}

	static boolean queue(int y) {
		if (y == MAX) {
			return true;
		}

		for (int i = 0; i < MAX; i++) {
			for (int x = 0; x < MAX; x++) {
				board[x][y] = 0;
			}
			if(checkQueue(i, y)) {
				board[i][y] = 1;
				if (queue(y + 1)) {
					return true;
				}
			}
		}
		return false;
	}

	static boolean checkQueue(int x, int y) {

		for (int i = 0; i < MAX; i++) {
			//检查横向
//			if (board[i][y] == 1) {
//				return false;
//			}
			//检查纵向
			if (board[x][i] == 1) {
				return false;
			}
			//检查左上方向
			if (x-1-i >= 0 && y-1-i >= 0 && board[x-1-i][y-1-i] == 1) {
				return false;
			}
			//检查右上方向
			if (x+1+i < MAX && y-1-i >= 0 && board[x+1+i][y-1-i] == 1) {
				return false;
			}
		}
		return true;
	}

	static void printBoard() {
		for (int i = 0; i < MAX; i++) {
			for (int j = 0; j < MAX; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println("");
		}
	}

}
