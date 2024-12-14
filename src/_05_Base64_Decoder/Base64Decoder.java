package _05_Base64_Decoder;

import java.util.ArrayList;

/*
 * Base 64 is a way of encoding binary data using text.
 * Each number 0-63 is mapped to a character.
 * NOTE: THIS IS NOT THE SAME AS ASCII OR UNICODE ENCODING!
 * 
 * The base64Chars array below maps the index to a character
 * in the array. For example,
 *      0 -> 'A'    // 'A' is at index 0 in the array, so 0 maps to 'A'
 *      1 -> 'B'    // 'B' is at index 1 in the array, so 1 maps to 'B'
 *      2 -> 'C'    // 'C' is at index 1 in the array, so 2 maps to 'C'
 *      ...
 *      63 -> '/'   // '/' is at index 63 in the array, so 63 maps to '/'
 * 
 * Since the numbers 0 through 63 can be represented using 6 bits,
 * every four (4) characters will represent twenty four (24) bits of data.
 *      0b111111    // 63 decimal, all 6 bits set to 1
 *      4 * 6 = 24  // 4 characters * (6 bits / character)
 * 
 * Even though only 6 bits are needed to represent each character in the
 * base64Chars array, the minimum number of bits a computer can transfer
 * is 8 bits, i.e. a byte.
 * 
 * This means that for every Base64 character there will be 2 bits that aren't
 * used. Those 2 bits are the two leftmost bits, bits 6 and 7.
 * bit # 7 6 5 4 3 2 1 0
 *       0 0 1 1 1 1 1 1    // binary 0b00111111, decimal 63, character '/'
 *       \_\_unused bits
 * 
 * If there are 4 bytes (32 bits) transferred, each byte will have 2 unused
 * bits for a total of 8 unused bits. We can shift the bits in each byte to
 * to fit into 3 bytes with no unused bits.
 *       Byte 3      Byte 2      Byte 1       Byte 0    // 4 total bytes (32 bits)
 *      00111111    00111111    00111111     00111111   // "////"
 *               ____// |__|  ___/___/ \\__  __|____|
 *        111111 11     1111 1111        11  111111     // 3 total bytes (24 bits)
 *         Byte 2         Byte 1           Byte 0
 * 
 * For this exercise, we won't worry about what happens if the total bits
 * being converted do not divide evenly by 24.
 * 
 * View this link for a full description of Base64 encoding
 * https://en.wikipedia.org/wiki/Base64
 */
public class Base64Decoder {

    final static char[] base64Chars = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };
//10000010
    //1. Complete this method so that it returns the index in
    //   the base64Chars array that corresponds to the passed in char.
    public static byte convertBase64Char(char c){
    	for(byte b = 0;b<64;b++) {
    		if(base64Chars[b]==c) {
    			return b;
    		}
    	}
    	System.out.println("wee woo");
    	return -1;
    }

    //2. Complete this method so that it will take in a string that is 4
    //   characters long and return an array of 3 bytes (24 bits). The byte
    //   array should be the binary value of the encoded characters.
    public static byte[] convert4CharsTo24Bits(String s){
    	byte[] cb = new byte[4];
    	byte[] bs = new byte[3];
    	
    	for(int i = 0; i<4;i++) {
    		cb[i]=convertBase64Char(s.charAt(i));
    	}
    	
    	bs[0] = (byte) ((cb[0]<<2)|(cb[1]>>4)); //last 6 of cb[0] and first 2 of cb[1]
    	bs[1] = (byte) ((cb[1]<<4)|(cb[2]>>2)); //last 4 of cb[1] and first 4 of cb[2]
    	bs[2] = (byte) ((cb[2]<<4)|(cb[3])); //last 2 of cb[2] and first 6 of cb[3]
    	return bs;
    }

    //3. Complete this method so that it takes in a string of any length
    //   and returns the full byte array of the decoded base64 characters.
    public static byte[] base64StringToByteArray(String file) {
    	ArrayList<Byte> list = new ArrayList<Byte>();
    	byte[] ray;
    	while(file.length()>=4) {
    		for(byte b: convert4CharsTo24Bits(file.substring(0, 4))) {
    			list.add(b);
    		}
    		file=file.substring(4);
    	}/*
    	if(file.length()>0) {
    		//convert the last 1-3 chars to bytes
    		//problem: chars store 6 bits and bytes store 8
    		//1 char: 1 byte, 2 unused
    		//2 char: 2 byte, 4 unused
    		//3 char: 3 byte 6 unused
    		//add bytes to list
    		byte b;
    		byte[] bs = new byte[file.length()];
    		char[] cs = file.toCharArray();
    		for(int i = 0;i<file.length();i++) {
    			switch (i) {
    			case 0:
    				bs[0]=(byte) (convertBase64Char(cs[0])<<2);
    				break;
    			case 1:
    				b = convertBase64Char(cs[1]);
    				bs[0]|=(b>>4);
    				bs[1]=(byte) (b<<4);
    				break;
    			case 2:
    				b = convertBase64Char(cs[2]);
    				bs[1]|=(b>>2);
    				bs[2]=(byte) (b<<6);
    				break;
    			}
    		}
    		for(byte be: bs) {
    			list.add(be);
    		}
    	} 
    	*/
    	//now list has all filled bytes
    	ray=new byte[list.size()];
    	for(int i = 0; i<ray.length;i++) {
    		System.out.println(list.get(i));
    		ray[i]=list.get(i).byteValue();
    	}
        return ray;
    }
}
