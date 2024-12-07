package _03_Printing_Binary;

import java.util.Random;

public class _01_BinaryPrinter {
    /*
     * Complete the methods below so they print the passed in parameter in binary.
     * Do not use the Integer.toBinaryString method!
     * Use the main method to test your methods.
     */


    public void printByteBinary(byte b) {
        // We first want to print the bit in the one's place

        // Shift b seven bits to the right
    	for(int i =7;i>=0;i--) {
    		byte checker=1;
    		byte c = (byte) (b>>i);
    		//make the binary string
    		byte checked=(byte) (c&checker);
    		if(checked!=0) {
    			System.out.print(1);
    		} else {
    			System.out.print(0);
    		}
    	}
    	
    	
        // Use the & operator to "mask" the bit in the one's place
        // This can be done by "anding" (&) it with the value of 1

        // Print the result using System.out.print (NOT System.out.println)

        //Use this method to print the remaining 7 bits of b
    }

    public void printShortBinary(short s) {
        // Create 2 byte variables
    	byte first=(byte)(s>>8);
    	byte second=(byte)((s<<8)>>8);
        // Use bit shifting and masking (&) to save the first
        // 8 bits of s in one byte, and the second 8 bits of
        // s in the other byte
    	printByteBinary(first);
    	printByteBinary(second);
        // Call printByteBinary twice using the two bytes
        // Make sure they are in the correct order
    }

    public void printIntBinary(int i) {
        // Create 2 short variables
    	short first=(short)(i>>16);
    	short second=(short)((i<<16)>>16);
    	printShortBinary(first);
    	printShortBinary(second);
        // Use bit shifting and masking (&) to save the first
        // 16 bits of i in one short, and the second 16 bits of
        // i in the other short

        // Call printShortBinary twice using the two short variables
        // Make sure they are in the correct order
    }

    public void printLongBinary(long l) {
        // Use the same method as before to complete this method
    	int f = (int) (l>>32);
    	int s = (int) ((l<<32)>>32);
    	printIntBinary(f);
    	printIntBinary(s);
    }

    
    public static void main(String[] args) {
        // Test your methods here
    	new _01_BinaryPrinter();
    }
    
    public _01_BinaryPrinter() {
    	int s = new Random().nextInt();
    	System.out.println("s="+s);
    	printIntBinary(s);
    }
}
