
package hw6;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class IntLinkedBinaryTree extends LinkedBinaryTree<Integer>{

	// define necessary instance variables and methods, including a constructor(s)
        private int size;
        public int size(){ return size;}
        
        public IntLinkedBinaryTree() {
            root = null;
            size = 0;
        }
    
	/**
	 * Add a new node with e to the tree rooted at position p
	 * @param p The root of the tree to which new node is added
	 * @param e The element of the new node
	 * @return If a node with e does not exist, a new node with e is added and 
	 *   reference to the node is returned. If a node with e exists, null is returned.
	 */
	public Position<Integer> add(Position<Integer> p, Integer e){
            
            if (p == null){
                 Position<Integer> temp = addRoot(e);
                 size = 1;
                 return temp;
            }
            Node<Integer> x = validate(p);
            Node<Integer> y = x;
            
            while (x != null){
                
                if (x.getElement() == e){return null;}
                
                else if (x.getElement() > e){
                    y = x;
                    x = x.getLeft();
                }
                
                else {
                    y = x;
                    x = x.getRight();
                }
            }
            
            Node<Integer> newNode = new Node<>(e, null, null, null);
            
            if (y.getElement() > e){
                y.setLeft(newNode);
            }
            else{
                y.setRight(newNode);
            }
            
            size++;
            return newNode;	
	}
        
        
        /**
         * Input tree, and integer. Check if integer is unique inside of Binary tree. Return false if not unique
         * @param tree (Int Linked Binary Tree) 
         * @param num (int)
         */
        public static boolean isUnique(IntLinkedBinaryTree tree, int num){
            Node<Integer> x = tree.root;
            if (x == null){
                return true;
            }
            Node<Integer> y = x;
            
            while (x != null){
                
                if (x.getElement() == num){
                    return false;
                }
                
                else if (x.getElement() > num){
                    y = x;
                    x = x.getLeft();
                }
                
                else {
                    y = x;
                    x = x.getRight();
                }
            }
            return true;
        }
        
        /**
         * Get the height of the binary tree.  
         * @param Node of the root of the tree. 
         * @return height of the treesd
         */
        public static int findHeight(Node<Integer> dopeNode){
            if (dopeNode == null){
                return -1;
            }
            
            int left_height = findHeight(dopeNode.getLeft());
            int right_height = findHeight(dopeNode.getRight());
            
            if(left_height > right_height){
                return left_height + 1;
            }
            else{
                return right_height + 1;
            }
        }
	
	public static void main(String[] args) {
		
		// create a new binary tree instance
		IntLinkedBinaryTree t =   new IntLinkedBinaryTree();
		
		// add some integers
		 t.add(t.root, 100);
		 t.add(t.root, 50);
		 t.add(t.root, 150);
		 t.add(t.root, 70);
         
		Iterator<Position<Integer>> it = t.inorder().iterator();
                while (it.hasNext()){
                    System.out.print(it.next().getElement() + " ");
                }
                System.out.println();
				
		// experimentally determine the average height of a binary search tree
		// clear the tree before beginning this experiment
		// repeat 100 times
		
		int maxIteration = 100;
		List<Integer> integerArray = new ArrayList<>();
                
		for (int i= 0; i<maxIteration; i++){
			// begin with an empty tree in each iteration
                        IntLinkedBinaryTree tree =   new IntLinkedBinaryTree();
                        
                        int numbers = 1000;
                        for (int j = 0; j < numbers; j++){
                            int randomNum = ThreadLocalRandom.current().nextInt(0, 1000000);
                            boolean unique = isUnique(tree, randomNum);
                            while (unique == false){
                                randomNum = ThreadLocalRandom.current().nextInt(0, 1000000);
                                unique = isUnique(tree, randomNum);
                            }
                            tree.add(tree.root, randomNum);
                        }
                        
                        int height = findHeight(tree.root);
                        integerArray.add(height);
                        System.out.println("Nodes: " + tree.size);
                        System.out.println("Height: " + height);
                        System.out.println("");
			
		}
		Integer sum = 0;
                double ave = 0;
                if (!integerArray.isEmpty()){
                    for (Integer num : integerArray){
                        sum += num;
                    }
                    ave = sum.doubleValue() / integerArray.size();
                }
                
                System.out.println("Average Height: " + ave);
		
	}

}
