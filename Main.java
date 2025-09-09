package project_3;

import java.util.List;
import java.util.Scanner;
/**
 * this is the main class for the main method
 * to execute the binary tree class.
 * It prompts the user to enter values for a binary tree
 * and returns an indented form of the tree, and
 * determines if it is a balanced BST, an unbalanced
 * BST, or not a BST.
 * 
 * author: Elizabeth Bloss
 * UMGC CMSC315
 * June 2024
 * Project 3
 */
public class Main {

	public static void main(String[] args) throws InvalidSyntaxException {
		
		Scanner scan = new Scanner(System.in);
		boolean moreTrees = true;  // conditional to continue
		while(moreTrees) {
			try {
				// get user input
				System.out.print("Enter a binary tree: ");
				String preorderExp = scan.nextLine();
			
				// Remove extra characters at end of input
				// and remove all parentheses from expression
				preorderExp = preorderExp.trim(); 
				String modifiedExp = preorderExp.replaceAll("\\(","")
						.replaceAll("\\)", "");
				
				// binary tree
				BinaryTree tree = new BinaryTree(modifiedExp);
			
				// display
				tree.indentedTree();
	
				// get integer array list of values
				List<Integer> treeValues = tree.toIntArray(modifiedExp);
				// balanced BST
				BinaryTree tree2 = new BinaryTree(treeValues);	
				
			
				// ######################################################
				// messaging for balanced BST, unbalanced BST, or not BST
			
				// balanced BST
				if(tree.checkIsBST() && tree.isBalanced()) {
					System.out.println("It is a balanced binary search tree");
				}
			
				// BST but not balanced
				else if(tree.checkIsBST() && !tree.isBalanced()) {
					System.out.println("It is a binary tree but it is not balanced");
					tree2.indentedTree();
					displayHeightInfo(tree, tree2);
				}
			
				// not a BST
				else {
					System.out.println("It is not a binary search tree");
					tree2.indentedTree();
					displayHeightInfo(tree, tree2);
				}
		
				//ask to continue
				moreTrees = wantContinue();
				
			} catch (InvalidSyntaxException e){
				System.out.println("Invalid expression.\n" 
						+ e.getMessage());
			}
		} 
	}
	
	
	/**METHOD: to determine if user want to continue
	 * prompts for choice of Y or N
	 * @return true if yes, false if no
	 */
	private static boolean wantContinue() {
		Scanner scan = new Scanner(System.in);
		System.out.print("\nMore trees? Y or N:\t");
		String choice = scan.nextLine().trim();
		
		if (choice.toUpperCase().equals("N")) {
			System.out.println("Thank you for using my program.");
			return false;
		}
		if (choice.toUpperCase().equals("Y")) {
			return true;
		}
		else {
			System.out.print("\n*** Please enter Y or N ***");
			wantContinue();
		}
		return true;
	}
	
	/**METHOD: to display the height of both
	 * the original tree and the balanced tree
	 * @param BinaryTree tree = initial binary tree
	 * @param BinaryTree tree2 = balanced binary search tree
	 */
	private static void displayHeightInfo(BinaryTree tree, BinaryTree tree2) {
		
		int height = tree.getHeight() - 1;
		int balancedHeight = tree2.getHeight() - 1;  
				
		System.out.println("Original tree has height " + height);
		System.out.println("Balanced tree has height " + balancedHeight);
	}
	
}
