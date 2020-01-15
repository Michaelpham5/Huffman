/* Michael Pham
 * Lab 3
 * This is the main driver. The program will ask you if you want to build a huffman tree from a string by 
 * counting the frequency or it can build a tree from a list of frequency in the format of "Character"
 * -"Frequency." In a separate output file, the characters, frequencies, and code will print in a table. The
 * huffman tree and the encoded string will also be printed. 
 */


import java.util.*;
import java.io.*;

public class Driver {
	public static void main(String[] args) {
		while(true) {
			try { 
				Scanner userInput = new Scanner(System.in);
				System.out.println("Enter 1 to build Huffman Tree from string, 2 to build from file, or 0 to exit: ");
				Integer input = userInput.nextInt();
				userInput.close();
				if (input == 0) {
					break;
				} else if (input == 1) {
					Scanner userFile = new Scanner(System.in);
					System.out.println("Enter file directory: ");
					String fileInput = userFile.nextLine();
					userInput.close();
					BufferedReader br = new BufferedReader(new FileReader(fileInput));
					StringBuilder sb = new StringBuilder();
					String line = br.readLine();
					while (line != null) {
						sb.append(line);
						line = br.readLine();
					}
					br.close();
					String string = sb.toString();
					huffmanString(string);
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input\n");
				continue;
			}
			
		}
	}
		
}


