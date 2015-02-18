package miscellaneous;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Boolean algebra {(0,1), +,.,~}<br/>
 * Terms containing '+' are called MAX terms and terms containing '.' are called MIN terms
 * represented as M<sub>i</sub> and m<sub>i</sub> respectively.<br/>
 * For inverse of a boolean function take its dual and inverse of operands.<br/><br/>
 * 
 * Takes input an array of boolean values for a function whose simplified boolean
 * expression need to be found using karnaugh maps.
 * 
 * @author Artist
 *
 */

public class KarnaughMaps {
	
	private byte functionValues[][];
	private int noOfVariables;
	private int noOfRowVar;
	private int noOfColVar;
	private int noOfRows;
	private int noOfCol;
	private int noOfTerms;

	public KarnaughMaps(){
		
		Scanner sin = new Scanner(System.in);
		System.out.print("Enter the number of variables for the function : ");
		noOfVariables = sin.nextInt();
		
		noOfRowVar = noOfVariables/2;
		noOfColVar = noOfVariables - noOfRowVar;
		noOfRows = (int)Math.pow(2,noOfRowVar);
		noOfCol = (int)Math.pow(2, noOfColVar);
		int rowCol[] = {noOfRows, noOfCol};
		functionValues = (byte[][])Array.newInstance(byte.class, rowCol);
		for(int i = 0; i < noOfRows; i++)
			Arrays.fill(functionValues[i], (byte)0);
		
		System.out.print("Enter the number of minterms : ");
		int minTerms = sin.nextInt();
		byte terms[] = (byte[])Array.newInstance(byte.class, noOfTerms);
		System.out.print("\n Enter the minterms seperated by space : ");
		for(int i = 0; i < minTerms; i++)
			sin.nextByte();
		
		String binaryRep;
		char binaryRepNorm[] = new char[noOfVariables];
		int temp, termRow, termCol;
		for(int i = 0; i < minTerms; i++){
			
			binaryRep = Integer.toBinaryString(terms[i]);
			binaryRepNorm = binaryRep.toCharArray();
			
			temp = termRow = 0;
			while(temp < noOfRowVar){
				termRow += binaryRepNorm[temp++];
			}
			
			termCol = 0;
			while(temp < noOfVariables){
				termCol += binaryRepNorm[temp++];
			}
			
			functionValues[termRow][termCol] = 1;
			
		}
			
		for(int i=0,j; i < noOfRows; i++){
			for(j=0; j < noOfCol; j++){
				
				if((j+1)%4 == 0){
					swapCol(i,j);
				}
				
				if((i+1)%4 == 0){
					swapRow(i,j);
				}
				
			}
		}
		
	}
	
	public void swapCol(int row, int col){
		
		functionValues[row][col] += functionValues[row][col-1];
		functionValues[row][col-1] = (byte)(functionValues[row][col] - functionValues[row][col-1]);
		functionValues[row][col] = (byte)(functionValues[row][col] - functionValues[row][col-1]);
		
	}
	
	public void swapRow(int row, int col){
		
		functionValues[row][col] += functionValues[row-1][col];
		functionValues[row-1][col] = (byte)(functionValues[row][col] - functionValues[row-1][col]);
		functionValues[row][col] = (byte)(functionValues[row][col] - functionValues[row-1][col]);
		
	}
	
	public void getPrimeImplicants(){
		
		
		
	}
	
}
