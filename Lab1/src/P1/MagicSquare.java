package p1;

import java.io.*;
import java.util.*;

public class MagicSquare {

	public static int isValidNumber(String fileName) throws IOException {
	/*
	 *判断文件中的数字是否是正整数
	 *返回：
	 *	若矩阵符合要求，则返回矩阵的行数
	 *	若矩阵不符合要求，则返回-1
	 */
		BufferedReader buf = new BufferedReader(new FileReader(fileName));
		String bufLine;
		int row = 0;
		while((bufLine = buf.readLine())!=null) {
			//检测矩阵是否符合要求
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
		/*
		 * 判断文件中的矩阵是否为幻方方阵
		 * */
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
			//将每一行按\t分割，拆分得到的字符串放入字符串数组中
			tempLine = bufLine.split("\t");
			if(row != tempLine.length) {
				//若每一行的元素数不等于行数说明不是方阵
				System.out.println("Not the martrix required!");
				buf.close();
				return false;
			}
			for(j = 0 ;j < row; j++) 
				matrix[i][j] = Integer.valueOf(tempLine[j]);
		}
		buf.close();
		//检测是否为幻方
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
		//偶数和负数直接退出
		if(n % 2 == 0 || n < 0)
			return false;
		int magic[][] = new int[n][n];
		//初始化为第一排中间
		int row = 0, col = n / 2, i, j, square = n * n;
		for (i = 1; i <= square; i++) {
			magic[row][col] = i;
			if (i % n == 0)//填完n个数字就下移一行
				row++;
			else {
				if (row == 0)//若在行尾则转向第一行
					row = n - 1;
				else//不在行尾则去下一行
					row--;
				if (col == (n - 1))//若在列尾则转向第一列
					col = 0;
				else//否则去向下一列
					col++;
			}
		}
		FileWriter fw = new FileWriter("src/6.txt",false);  
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
		        fw.write(magic[i][j] + "\t");  //逐个读入数字
			fw.write("\n");//读完一行就换行
		}
		fw.close(); 
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		String head = "src/txt/";
		String tail = ".txt";
		boolean ans;
		int i;
		for(i = 1;i < 6;i ++) {
			//构造文件名
			System.out.println("case " + String.valueOf(i) + ": " + head + String.valueOf(i) + tail); 
			ans = isLegalMagicSquare(head + String.valueOf(i) + tail);
			System.out.println(ans);
		}
		if(generateMagicSquare(7) == true) {
			System.out.println("case 6 : src/6.txt"); 
			ans = isLegalMagicSquare("src/txt/6.txt");
			System.out.println(ans);
		}
		return;
	}
}
