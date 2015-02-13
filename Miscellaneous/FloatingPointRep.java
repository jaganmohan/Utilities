package miscellaneous;

import java.lang.reflect.Array;
import java.util.Scanner;

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
	private double decimal;
	private char[] binaryRep;
	
	public FloatingPointRep(){
		
		Scanner sin = new Scanner(System.in);
		System.out.print("Enter the length of floating point representaion : ");
		noOfBits = sin.nextInt();
		System.out.print("Enter the lenth of Exponent : ");
		expLength = sin.nextInt();
		System.out.print("Enter the decimal number to be converted");
		decimal= sin.nextDouble();
		binaryRep = (char[])Array.newInstance(char.class, noOfBits);
		sin.close();
	}
	
	public void floatingPointDisplay(){
		
		if(decimal < 0){
			binaryRep[0] = '1';
		}
                int dec = (int)decimal;
                String predec = Integer.toBinaryString(dec);
		char preDecimal[] = predec.toCharArray();
                int exponent  = predec.length()+(2^(expLength-1)-1)-1;
                char exp[] = Integer.toBinaryString(exponent).toCharArray();
                System.arraycopy(exp, 0, binaryRep, 1, exp.length);
		
	}

}
