package p1;

import java.io.*;
import java.util.*;

public class MagicSquare {

	public static int isValidNumber(String fileName) throws IOException {
	/*
	 *Judge the numbers in the file is positive integer or not
	 *return the number of the rows in the file
	 */
		BufferedReader buf = new BufferedReader(new FileReader(fileName));
		String bufLine;
		int row = 0;
		while((bufLine = buf.readLine())!=null) {
			bufLine = bufLine.replaceAll("[0-9]+\\s*", "");
			if(bufLine.length() != 0) {
				System.out.println("Invalid numbers !");
				row = -1;
				break;
			}
			row++;
		}
		buf.close();
		return row;
	}
	
	
	public static boolean isLegalMagicSquare(String fileName) throws IOException {
		int row = isValidNumber(fileName);
		int i = 0,j;
		if(row == -1 ) 
			return false;
		long  matrix[][] = new long [row][row];
		long check[] = new long [row*2+2];
		
		BufferedReader buf = new BufferedReader(new FileReader(fileName));
		String[] tempLine;
		String bufLine;
		for(i = 0; i < row ; i++) {
			bufLine = buf.readLine();
			tempLine = bufLine.split("\t");
			if(row != tempLine.length) {
				System.out.println("Not the martrix required!");
				buf.close();
				return false;
			}
			for(j = 0 ;j < row; j++) 
				matrix[i][j] = Integer.valueOf(tempLine[j]);
		}
		buf.close();
		
		for(i = 0; i < row ; i++) 
			for(j = 0 ;j < row; j++){
				check[i] += matrix[i][j];
				check[row+j] += matrix[i][j];
				if(j == i)
					check[row*2] += matrix[i][j];
				if(i + j == row - 1)
					check[row*2+1] += matrix[i][j];
			}
		for(i = 1; i < row*2 + 2 ; i++) {
			if(check[0]!=check[i])
				return false;
		}
		return true;

		
	}
	public static boolean generateMagicSquare(int n) throws IOException {
		if(n % 2 == 0 || n < 0)
			return false;
		int magic[][] = new int[n][n];
		int row = 0, col = n / 2, i, j, square = n * n;
		for (i = 1; i <= square; i++) {
			magic[row][col] = i;
			if (i % n == 0)
				row++;
			else {
				if (row == 0)
					row = n - 1;
				else
					row--;
				if (col == (n - 1))
					col = 0;
				else
					col++;
			}
		}
		FileWriter fw = new FileWriter("src/6.txt",false);  
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
		        fw.write(magic[i][j] + "\t");  
			fw.write("\n");
		}
		fw.close(); 
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		String head = "src/";
		String tail = ".txt";
		boolean ans;
		int i;
		for(i = 1;i < 6;i ++) {
			System.out.println("case " + String.valueOf(i) + ": " + head + String.valueOf(i) + tail); 
			ans = isLegalMagicSquare(head + String.valueOf(i) + tail);
			System.out.println(ans);
		}
		if(generateMagicSquare(7) == true) {
			System.out.println("case 6 : src/6.txt"); 
			ans = isLegalMagicSquare("src/6.txt");
			System.out.println(ans);
		}
		return;
	}

}
