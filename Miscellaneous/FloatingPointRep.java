package miscellaneous;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

import jdk.nashorn.internal.ir.LiteralNode.ArrayLiteralNode.ArrayUnit;

/**
 * Takes input number of bits and length of "Exponent" for floating point representation<br/>
 * as (-1)<sup>S</sup> * 1.M * 2<sup>E-2<sup>Exponent</sup>-1</sup> and displays in implicit normalization format<br/>
 * S E<sub>j-1</sub>E<sub>j-2</sub>.....E<sub>0</sub> M<sub>i-1</sub>M<sub>i-2</sub>.............M<sub>1</sub>M<sub>0</sub>
 * where i and j are lengths of Exponent and Mantissa
 * @author Artist
 *
 */
public class FloatingPointRep {
	
	private int noOfBits;
	private int expLength;
	private String decimal;
	private int befDecimal;
	private double afterDecimal;
	private char[] binaryRep;
	
	public FloatingPointRep(){
		
		Scanner sin = new Scanner(System.in);
		System.out.print("Enter the length of floating point representaion : ");
		noOfBits = sin.nextInt();
		System.out.print("\nEnter the lenth of Exponent : ");
		expLength = sin.nextInt();
		System.out.print("\nEnter the decimal number to be converted : ");
		decimal= sin.next();
		binaryRep = (char[])Array.newInstance(char.class, noOfBits);
		Arrays.fill(binaryRep, '0');
		sin.close();
	}
	
	public void floatingPointDisplay(){
		
		int count = 0;
		boolean flag = false;
		befDecimal = (int) Double.parseDouble(decimal);
		
		if(befDecimal < 0){
			binaryRep[0] = '1';
		}
		
		int signBit = Integer.signum(befDecimal);
		befDecimal = befDecimal*signBit;
		afterDecimal = signBit*Double.parseDouble(decimal) - befDecimal;
		String predec = Integer.toBinaryString(befDecimal);
		char preDecimal[] = predec.toCharArray();
		if(preDecimal[0] != '0'){
			count = preDecimal.length; //decrement count by -1 at the end
			flag = true;
		}
		
		int i = 0, temp, loop = noOfBits - preDecimal.length - expLength +1;
		char binaryString[] = (char[])Array.newInstance(char.class, loop);
		Arrays.fill(binaryString, '0');
		
		while(afterDecimal > 0 && i < loop){
			afterDecimal = afterDecimal*2;
			temp = (int) afterDecimal;
			if(temp == 0 && !flag){
				--count;
			}else{
				binaryString[i++] = Integer.toString(temp).charAt(0);
				flag = true;
			}
			afterDecimal = afterDecimal - temp;
		}
		--count;
		int exponent  = count + (int)Math.pow(2, (expLength-1)) - 1;//TODO -2
		char exp[] = Integer.toBinaryString(exponent).toCharArray();
		int expStart = expLength - exp.length + 1, startMantissa;
		System.arraycopy(exp, 0, binaryRep, expStart, exp.length);
		if(preDecimal[0] != '0'){
			//System.arraycopy(preDecimal, 1, binaryRep, expLength+1, preDecimal.length);
			for(int j =1; j < preDecimal.length; j++){
				binaryRep[j+expLength] = preDecimal[j];
			}
			startMantissa = preDecimal.length + expLength ;
			temp = 0;
		}else{
			startMantissa = expLength;
			temp = 1;
		}
		System.arraycopy(binaryString, temp, binaryRep, startMantissa, noOfBits-startMantissa);
		
		display();
		
	}
	
	public void display(){
		System.out.print(binaryRep[0]+"   ");
		for(int i = 1; i <= expLength; i++)
			System.out.print(binaryRep[i] + " ");
		System.out.print("  ");
		for(int i = expLength+1; i < binaryRep.length; i++)
			System.out.print(binaryRep[i] + " ");
	}
	
	public static void main(String []ar){
		
		FloatingPointRep rep = new FloatingPointRep();
		rep.floatingPointDisplay();
	}

}
