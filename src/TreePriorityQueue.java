
public class TreePriorityQueue {
	
	private BinaryTree<String>[] elements;
	protected int size;
	
	@SuppressWarnings("unchecked")
	public TreePriorityQueue(int capacity) {
		elements = new BinaryTree[capacity];
		size = 0;
	}
	public int getLeftIndex(int parentIndex) {
		return 2 * parentIndex + 1;
	}
	public int getRightIndex(int parentIndex) {
		return 2 * parentIndex + 2;
	}
	public int getParentIndex(int childIndex) {
		return (childIndex-1)/2;
	}
	
	public boolean hasLeftChild(int index) {
		return elements[getLeftIndex(index)] != null;
	}
	public boolean hasRightChild(int index) {
		return elements[getRightIndex(index)] != null;
	}
	public boolean hasParent(int index) {
		return getParentIndex(index) >= 0;
	}
	public boolean isLeaf(int index) {
		return (!hasLeftChild(index) && !hasRightChild(index));
	}
	
	private void swap(int indexOne, int indexTwo) {
		
		BinaryTree<String> temp = elements[indexOne];
		elements[indexOne] = elements[indexTwo];
		elements[indexTwo] = temp;
	}
	
	public static int compareTree(BinaryTree<String> tree1, BinaryTree<String> tree2) {
		int answer = 0;
		if (tree1.getData() == tree2.getData() && tree1.getWeight() == tree2.getWeight()) {
			answer = 0;
		} else if (tree1.getWeight() < tree2.getWeight()) {
			answer = -1;
		} else if (tree1.getWeight() == tree2.getWeight()) {
			if (tree1.getData().length()<tree2.getData().length()) {
				answer = -1;
			} else if (tree1.getData().compareTo(tree2.getData())<0) {
				answer = -1;
			}
		} else {
			answer = 1;
		}
		return answer;
	}

	private void heapify() {
		int index = 0;
		while (hasLeftChild(index)) {
			int smallerIndex = getLeftIndex(index);
			if (hasRightChild(index) && compareTree(elements[getRightIndex(index)], elements[getLeftIndex(index)])==-1) {
				smallerIndex = getRightIndex(index);
			}
			if (compareTree(elements[index], elements[smallerIndex])==-1) {
				break;
			} else { 
				swap(index, smallerIndex);
			}
			index = smallerIndex;
		}
	}
	
	public BinaryTree<String> poll() {
		BinaryTree<String> temp = elements[0];
		elements[0] = elements[size-1];
		elements[size-1] = null;
		size--;
		heapify();
		return temp;
	}
	

	@SuppressWarnings("unchecked")
	private void resize() {
		BinaryTree<String>[] newElements = new BinaryTree[size * 2];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
    }
	
	public void insert(BinaryTree<String> tree) {
		if (size == elements.length) {
			resize();
		}
		elements[size] = tree;
		int current = size;
		while (hasParent(current)) {
			if (compareTree(elements[current], elements[getParentIndex(current)]) == -1) {
				swap(current, getParentIndex(current));
			} else {
				break;
			}
			current = getParentIndex(current);
		}
		size++;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(BinaryTree<String> tree: elements) {
			if (tree == null) {
				sb.append("null");
			} else {
				sb.append(tree.root.toString());
			}
			sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
	public static void main(String[] args) {
		TreePriorityQueue q = new TreePriorityQueue(20);
		BinaryTree<String> a = new BinaryTree<String>("a", 3);
		BinaryTree<String> b = new BinaryTree<String>("b", 3);
		BinaryTree<String> c = new BinaryTree<String>("c", 12);
		BinaryTree<String> d = new BinaryTree<String>("d", 1);
		BinaryTree<String> e = new BinaryTree<String>("e", 5);
		BinaryTree<String> f = new BinaryTree<String>("f", 6);
		BinaryTree<String> g = new BinaryTree<String>("g", 3);
		BinaryTree<String> h = new BinaryTree<String>("h", 2);
		q.insert(a);
		q.insert(b);
		q.insert(c);
		q.insert(d);
		q.insert(e);
		q.insert(f);
		q.insert(g);
		q.insert(h);
		q.poll();
 		System.out.println(q.toString());
		
		
	}
}
