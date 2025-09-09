package project_3;

import java.util.ArrayList;
import java.util.List;

/**
 * this immutable class defines the binary tree.
 *  
 * two different constructors
 * constructor 1: creates binary tree with preorder expression
 * constructor 2: creates a binary search tree with integer list
 * 
 * includes methods to:
 * 		output indented tree
 * 		return whether tree is a binary search tree
 * 		return whether tree is balanced
 * 		return the height of the tree
 * 		return array list of values in the tree 
 * 
 * author: Elizabeth Bloss
 * UMGC CMSC315
 * June 2024
 * Project 3
 */

public class BinaryTree{

	private Node root;
		
	/** CONSTRUCTOR: accepts string of preorder values 
	 * and constructs a binary tree	
	 * @param String s = preorder expression including asterisk
	 * @throws InvalidSyntaxException */
	public BinaryTree(String s) throws InvalidSyntaxException {
		
		// split expression by space to obtain values as String array
		String[] nodeValues = s.split("\s+");
		int[] arrayIndex = {0};  // to traverse String array
		root = makeTree(nodeValues, arrayIndex);
	}
	
	
	/** CONSTRUCTOR: accepts array list of integers and 
	 * constructs a balanced binary search tree with those values
	 * @param it[] values = array of integer values that make up tree */
	public BinaryTree(List<Integer> nodeValues) {
		// sort array of values in ascending order
		nodeValues.sort(null);
		int size = nodeValues.size();
		root = makeBST(nodeValues, 0, size - 1);
	}
	
	
	/** METHOD: recursive method to build a tree
	 * 
	 *  @param String[] nodeValues: values to be placed in binary tree
	 *  @param int[] arrayIndex: the index position as array is traversed
	 *  @return Node for inserting into binary tree 
	 *  @throws InvalidSyntaxException for values outside of integers
	 */
	private Node makeTree(String[] nodeValues, int[] arrayIndex) 
			throws InvalidSyntaxException {
		if(arrayIndex[0] >= nodeValues.length) {
			// incomplete tree syntax
			throw new InvalidSyntaxException("ERROR: Incomplete tree.");
		}
		String value = nodeValues[arrayIndex[0]++];
		
		// base case
		if(value.equals("*")){
			return null;
		}
	
		// check for integer
		if(!value.matches("\\d+")) {
			throw new InvalidSyntaxException("ERROR: "
					+ "Value must be an integer");
		}
		// create node from integer value
		Node currentNode = newNode(Integer.parseInt(value));
		
		// left subtree
		currentNode.left = makeTree(nodeValues, arrayIndex);
		// right subtree
		currentNode.right = makeTree(nodeValues, arrayIndex);
		
		return currentNode;
	}

	
	// testing output
	// (53 (28 (11 * *) (41 * *)) (83 (67 * *) *))
	
	/** METHOD: display indented tree 
	 * uses recursion to output desired tree */
	public void indentedTree() {
		indentedTreeHelper(root, 0);
	}
	/** helper method
	 * @param Node node = the node to be displayed
	 * @param int numIndent = nmber of times to repeat the indentation
	 * */
	private void indentedTreeHelper(Node node, int numIndent) {
		//base case
		if(node == null) {
			return;
		}
		numIndent++;
		System.out.println("   ".repeat(numIndent) + node.value);
		indentedTreeHelper(node.left, numIndent++);
		numIndent--;
		indentedTreeHelper(node.right, numIndent--);
		
	}
	
	
	/** METHOD: converts values of tree into an integer array list 
	 * @param String expression = tree as string expression 
	 * 								with parentheses removed
	 * @return List<Integer> = array list of integer values in tree
	 * */
	public List<Integer> toIntArray(String expression) {

		List<Integer> intListValues = new ArrayList<>();
		String[] values = expression.split("\\s+");
		for(String s: values) {
			if (!s.equals("*")) {
				intListValues.add(Integer.parseInt(s));
			}
		}
		return intListValues;
	}	
	
	
	/** METHOD: returns whether tree is a binary search tree 
	 * @return boolean = true if tree is a binary search tree */
	public boolean checkIsBST() {
						// minimum value of tree and maximum
		return checkIsBSTHelper(root, Integer.MIN_VALUE,
				Integer.MAX_VALUE);
	}
	/** helper method
	 * @param Node node = node who's value is to be checked
	 * @param int low = the minimum value of the tree values
	 * @param int high = the maximum value of the tree values
	 * @return boolean: false if any node is less than current low
	 * 			or greater than current high*/
	private boolean checkIsBSTHelper(Node node, int low, int high) {
		// base case
		if(node == null) {
			return true;
		}
		if(node.value < low || node.value > high) {
			return false;
		}
		boolean result = checkIsBSTHelper(node.left, low, node.value -1)
			&& checkIsBSTHelper(node.right, node.value +1, high);
		
		return result;
	}
	
	
	/** METHOD: recursive method to create a binary search tree
	 * @param List<Integer> values = integer array list of values to be used
	 * @param int start = starting index position
	 * @param int end = ending index position
	 * @return Node currentNode = node to be inserted into balanced BST */
	private Node makeBST(List<Integer> nodeValues, int start, int end) {
		//base case 
		if (start > end || start < 0 || end > nodeValues.size()-1) {
			return null;
		}
		// find element in middle and make root
		int middle = (start + end) / 2;
		Node currentNode = newNode(nodeValues.get(middle));
		
		// left subtree
		currentNode.left = makeBST(nodeValues, start, middle - 1);
		// right subtree
		currentNode.right = makeBST(nodeValues, middle + 1, end);
		
		return currentNode;
	}
	
	
	/** METHOD: returns whether the tree is height balanced
	 * @return boolean: true if tree is balanced */
	public boolean isBalanced() {
		boolean result = isBalancedHelper(root);
		return result;
	}
	/** helper method
	 * @param Node root = node of binary search tree
	 * @return boolean: true if  balanced
	 *                  false if not balanced     */
	private boolean isBalancedHelper (Node root) {
		
		if (root == null) {
			return true;
		}
		int left = getHeightHelper(root.left);
		int right = getHeightHelper(root.right);
		
		if(Math.abs(left - right) <= 1 && isBalancedHelper(root.left)
				&& isBalancedHelper(root.right)) {
			return true;
		}
		return false;
	}
	
	
	/** METHOD: returns the height of the tree 
	 * @return int: the height of the tree*/
	public int getHeight() {
		return getHeightHelper(root);
	}
	/** helper method
	 * @param Node root = root of binary tree 
	 * @return int: height of the tree */
	/** METHOD: returns an array list of the values in tree */

	

// should i be inserting nodes individually and then displaying BST? 
	// insert node
	private int getHeightHelper(Node root) {
		// base case
		if(root == null) {
			return 0;  // empty tree
		}
		// left subtree depth
		int left = getHeightHelper(root.left);
		
		// right subtree depth
		int right = getHeightHelper(root.right);
		
		// return larger depth
		return Math.max(left, right) + 1;
		
	}
				 
	/** INNER CLASS:: to define a treeNode 
	 * static, because it does not access any 
	 * instance members defined in its outer class */
	public static class Node {
		int value;   
		Node left;
		Node right;

		public Node(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}
	
	/** METHOD: to create a new node 
	 * @param int value = the data to be stored in node
	 * @return Node node =  a new node */
	
	static Node newNode(int value) {
		Node node = new Node(value);
		node.left = node.right = null;
		return node;
	}


	

}
