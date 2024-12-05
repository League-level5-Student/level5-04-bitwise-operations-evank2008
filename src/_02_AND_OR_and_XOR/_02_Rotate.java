package _02_AND_OR_and_XOR;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import _00_Binary_Conversion._02_BinaryToDecimal;

/*
 * Goal: Implement left and right rotate methods.
 * 
 * Inherently Java doesn't have an instruction to perform left or right
 * rotates (though there are rotate functions in the Integer class).
 * 
 * A rotate is when a bit is shifted outside the width of the variable and is
 * placed on the other side. Here is an example of a right rotate on int 7
 * by 1:
 *      00000000 00000000 00000000 00000111   // original value of 7
 *      10000000 00000000 00000000 00000011   // rotate right by 1
 * 
 * Normally when the number 7 is right shifted by 1, the rightmost '1' is
 * discarded and the result looks like this:
 *      00000000 00000000 00000000 00000011   // 7 >> 1 (last '1' is discarded)
 * For a right rotate the number is not discarded and it's placed on the left,
 * the most significant bit (bit 31).
 * 
 * The same goes for a left rotate:
 *      11111111 11111111 11111111 11111000   // original value of -8
 *      11111111 11111111 11111111 11110001   // rotate left by 1
 */
public class _02_Rotate {
    //dont make a char array, use string. char array makes the size too small.
    int rotateLeft(int value, int rotateAmount) {
    	String bum = Integer.toBinaryString(value);
    	for(int i = rotateAmount;i>0;i--) {
    	char c = bum.charAt(0);
    	bum=bum.substring(1)+c;
    	}
    	//so the rotation is correct but it wont convert to decimal fsr
    	_02_BinaryToDecimal d = new _02_BinaryToDecimal();
    	int n = d.convertBinaryStringToDecimalInt(bum);
    	return n;
    }
    
    int rotateRight(int value, int rotateAmount) {
    	String bum = Integer.toBinaryString(value);
    	for(int i = rotateAmount;i>0;i--) {
    	System.out.println("Unchanged binary: "+bum);
    	
    	char last = bum.charAt(bum.length()-1);
    	bum=bum.substring(0,bum.length()-1);
    	if(last=='1') {
    		//total string length must be 32
    		int spotsLeft=32-(bum.length()+1);
    		String zeroes="";
    		for(int j=spotsLeft;j>0;j--) {
    			zeroes+="0";
    		}
    		bum=last+zeroes+bum;
    	}
    	System.out.println("Rotated binary: "+bum);
    	}
    	_02_BinaryToDecimal d = new _02_BinaryToDecimal();
    	int n = d.convertBinaryStringToDecimalInt(bum);
    	return n;
    	}
    
    @Test
    void testRotateLeft() {
        int i = -8;

        int result = rotateLeft(i, 1);
        System.out.println("Left rotate tests");
        System.out.println("Expected: " + Integer.toBinaryString(-15));
        System.out.println("Actual  : " + Integer.toBinaryString(result));
        assertEquals(-15, result);
        
        result = rotateLeft(i, 3);
        System.out.println();
        System.out.println("Expected: " + Integer.toBinaryString(-57));
        System.out.println("Actual  : " + Integer.toBinaryString(result));
        assertEquals(-57, result);
    }
    
    @Test
    void testRotateRight() {
        int i = 7;
        
        int result = rotateRight(i, 1);
        System.out.println("\nRight rotate tests");
        System.out.println("Expected: " + Integer.toBinaryString(-2147483645));
        System.out.println("Actual  : " + Integer.toBinaryString(result));
        assertEquals(-2147483645, result);
        
        result = rotateRight(i, 16);
        System.out.println();
        System.out.println("Expected: " + Integer.toBinaryString(458752));
        System.out.println("Actual  : " + Integer.toBinaryString(result));
        assertEquals(458752, result);
    }
}
