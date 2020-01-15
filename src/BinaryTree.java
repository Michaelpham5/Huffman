

//Michael Pham

//Generic class for a binary tree. Has inner node class with pointers. 
public class BinaryTree<E> implements Comparable<BinaryTree<E>>{
	 protected static class Node<T> { 
		
		protected T data; 
		protected Node<T> left;
		protected Node<T> right;
		protected int weight;
		
		public Node(T data) {
			this.data = data;
			left = null;
			right = null;
			weight = -1;
		}
		
		public Node(T data, int weight) {
			this.data = data;
			this.weight = weight;
			left = null;
			right = null;
			
		}
		public String toString() {
			if (weight==-1) {
				return data.toString();
			} else { 
				return (String.valueOf(weight) + ":" + data.toString());
			}
				
		}
		
	}
	protected Node<E> root;
	
	//Constructors
	//Multiple constructors to take different arguments
	public BinaryTree() { 
		this.root = null;
	}
	public BinaryTree(E data) {
		this.root = new Node<>(data);
	}
	public BinaryTree(E data, int weight) {
		this.root = new Node<>(data, weight);
	}
	
	public BinaryTree(Node<E> root) {
		this.root = root;
	}
	public BinaryTree(E data, int weight, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
		this.root = new Node<>(data);
		this.root.weight = weight;
		if (leftTree != null) { 
			root.left = leftTree.root;
		} else {
			root.left = null;
		}
		if (rightTree != null) {
			root.right = rightTree.root; 
		} else { 
			root.right = null;
		}
	}
	
	//Methods
	//returns the data of the root
	public E getData() { 
		return root.data;
	}
	//returns the weight of the root
	public int getWeight() {
		return root.weight;
	}
	//checks if a node is a leat
	public boolean isLeaf() {
		return (root.left == null && root.right == null);
	}
	
	public BinaryTree<E> getLeftTree() {
		if (root.left != null && root != null) {
			return new BinaryTree<E>(root.left);
		} else {
			return null;
		}
	}
	
	public BinaryTree<E> getRightTree() {
		if (root.right != null && root != null) {
			return new BinaryTree<E>(root.right);
		} else {
			return null;
		}
	}
	//Gets the depth at a certain node
	public int getDepth(Node<E> node) {
		if (node == null) {
			return 0;
		} else {
			int leftDepth = getDepth(node.left);
			int rightDepth = getDepth(node.right);
			
			if (leftDepth > rightDepth) {
				return (leftDepth + 1);
			} else {
				return (rightDepth + 1);
			}
		}
	}
	//To string method to represent a tree;
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(root, 1, sb);
		return sb.toString();
	}
	private void toString(Node<E> node, int depth, StringBuilder sb) { 
		for (int i=1; i < depth; i++) {
			sb.append(" ");
		}
		if (node == null) {
			sb.append("\n");
		} else {
			sb.append(node.toString());
			sb.append("\n");
			toString(node.left, depth + 1, sb);
			toString(node.right, depth + 1, sb);
		}
	}
	public int compareTo(BinaryTree<E> tree) {
		if (this.root.weight > tree.root.weight) {
			return 1;
		} else if (this.root.weight == tree.root.weight) {
			return 0;
		} else {
			return -1;
		}
	}	
}