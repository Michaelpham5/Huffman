import java.util.*;
import java.io.*;

public class Huffman {
	@SuppressWarnings("serial")
	public static class SyntaxErrorException extends Exception {
        SyntaxErrorException(String message) {
            super(message);
        } 
	}
	public static HashMap<Character, Integer> getFrequencyFile(String filePath)  {
		HashMap<Character, Integer> frequencies = new HashMap<Character, Integer>();
		try {
			String line;
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			while ((line = reader.readLine()) != null) {
				String[] chars = line.split("-", 2);
				if (chars.length >= 2) {
					Character key = chars[0].charAt(0);
					Integer value = Integer.valueOf(chars[1]);
					frequencies.put(key, value);
				}
			}
			reader.close();	
		} catch (FileNotFoundException e) {
			System.out.println("Error file not found");
		} catch (IOException e) {
			System.out.println("Error");
		}
		return frequencies;
	}

	public static HashMap<Character, Integer> getFrequency(String string) {
		HashMap<Character, Integer> frequencies = new HashMap<Character, Integer>();
		for (int i = 0; i<string.length(); i++) {
			if (frequencies.containsKey(string.charAt(i))) {
				frequencies.put(string.charAt(i), frequencies.get(string.charAt(i)) + 1);
			} else {
				frequencies.put(string.charAt(i), 1);
			}
			
		}
		return frequencies;
	}
	public static TreePriorityQueue buildPriorityQueue(HashMap<Character, Integer> map) {
		TreePriorityQueue queue = new TreePriorityQueue((map.size()*2) + 2);
		for (Map.Entry<Character, Integer> item : map.entrySet()) {
			String data =String.valueOf(item.getKey());
			int weight = item.getValue();
			BinaryTree<String> temp = new BinaryTree<String>(data, weight);
			queue.insert(temp);
		}
		return queue;
	}
	public static BinaryTree<String> buildHuffmanTree(TreePriorityQueue queue) {
		while (queue.size > 1) {
			BinaryTree<String> left = queue.poll();
			BinaryTree<String> right = queue.poll();
			String data = left.getData() + right.getData();
			int weight = left.getWeight() + right.getWeight();
			BinaryTree<String> temp = new BinaryTree<String>(data, weight, left, right);
			queue.insert(temp);
		}
		return queue.poll();
	}
	
	public static void printCode(BinaryTree<String> tree) {
		String s ="";
		printCode(tree, s);
	}
	private static void printCode(BinaryTree<String> tree, String s) {
		if (tree.isLeaf()) {
			if (tree.getData()==" ") {
				System.out.println("space: " + s);
				return;
			} else {
				System.out.println(tree.getData() + ": " + s);
				return;
			} 
		} else {
			printCode(tree.getLeftTree(), s + "0");
			printCode(tree.getRightTree(), s + "1");

		}
	} 
	public static HashMap<String, String> buildCodeMap(BinaryTree<String> tree) {
		HashMap<String, String> codeMap = new HashMap<String, String>();
		String code = "";
		addCodes(codeMap, tree, code);
		return codeMap;
		
	}
	
	private static void addCodes(HashMap<String, String> map, BinaryTree<String> tree, String code) {
		if (tree.isLeaf()) {
			map.put(tree.getData(), code);
		} else {
			addCodes(map, tree.getLeftTree(), code + "0");
			addCodes(map, tree.getRightTree(), code + "1");
		}
	}
	public static String encode(HashMap<String, String> map, String string) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<string.length(); i++) {
			sb.append(map.get(String.valueOf(string.charAt(i))));
		}
		return sb.toString();
		
	}
	
	public static String decode(BinaryTree<String> tree, String code) throws SyntaxErrorException {
		StringBuilder sb = new StringBuilder();
		BinaryTree<String> current = tree;
		for (int i = 0; i<code.length(); i++) {
			if (code.charAt(i) == '1') {
				current = current.getRightTree();
			} else if (code.charAt(i) == '0'){
				current = current.getLeftTree();
			} else {
				throw new SyntaxErrorException("Error");
			}
			if (current.getData().length() == 1) {
				String s = current.getData();
				sb.append(s);
				current = tree;
			}
		}
		return sb.toString();
	}
	
	public static void huffmanString(String input) {
		HashMap<Character, Integer> frequencies = getFrequency(input);
		TreePriorityQueue queue = buildPriorityQueue(frequencies);
		BinaryTree<String> huffTree = buildHuffmanTree(queue);
		HashMap<String, String> codeMap = buildCodeMap(huffTree);
		String code = encode(codeMap, input);
		System.out.println("Character  Frequency  Huffman Code");
		for (Map.Entry<String, String> item : codeMap.entrySet()) {
			if (Character.isSpaceChar(item.getKey().charAt(0))) {
				System.out.println("Space      "+frequencies.get(item.getKey().charAt(0)) + 
						"          " + item.getValue());
			} else { 
				System.out.println(item.getKey()+"          "+frequencies.get(item.getKey().charAt(0)) + 
						"          " + item.getValue());
			}
			
		}
		System.out.println("\n\nHuffmanTree");
		System.out.println(huffTree.toString());
				
		System.out.println("\n\nEncoded String");
		System.out.println(code);
	}
	public static void main(String[] args) {
		String fileInput = "Input1.txt";
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileInput));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			br.close();
			String input = sb.toString();
			huffmanString(input);
		} catch (FileNotFoundException e) {
			System.out.println("Error File Not Found");
		} catch (IOException e) {
			System.out.println("Error");
		}
	}
}
