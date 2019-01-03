import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class proj2f {
		static Node root;
	    private static int height (Node N) {
	        if (N == null)
	            return 0;
	        return N.height;
	    }
	    public void insertt(String value) {
			root = insert(root, value);
		}
	    
		 Node insert(Node node, String value) {
	        /* 1.  Perform the normal BST rotation */
	    	int p;
	    	if (node == null) {
	            return(new Node(value));
	        }	     p=value.compareTo(node.value);
	        if(p==0) {
	        	(root.count)++;
	        	return node;
	        	
	        }
	        else if (p<0)
	            node.left  = insert(node.left, value);
	        else
	            node.right = insert(node.right, value);

	        /* 2. Update height of this ancestor node */
	        node.height = Math.max(height(node.left), height(node.right)) + 1;

	        /* 3. Get the balance factor of this ancestor node to check whether
	           this node became unbalanced */
	        int balance = getBalance(node);

	        // If this node becomes unbalanced, then there are 4 cases

	        // Left Left Case
	        if (balance > 1 && p<0)
	            return Rotate_right(node);

	        // Right Right Case
	        if (balance < -1 && p>0)
	            return Rotate_left(node);

	        // Left Right Case
	        if (balance > 1 && p>0)
	        {
	            node.left =  Rotate_left(node.left);
	            return Rotate_right(node);
	        }

	        // Right Left Case
	        if (balance < -1 && p<0)
	        {
	            node.right = Rotate_right(node.right);
	            return Rotate_left(node);
	        }

	        /* return the (unchanged) node pointer */
	        return node;
	    }

		 public static boolean isInteger(Object object) {
				if(object instanceof Integer) {
					return true;
				} else {
					String string = object.toString();
					
					try {
						Integer.parseInt(string);
					} catch(Exception e) {
						return false;
					}	
				}
			  
			    return true;
			}
		 
		 
	    private static Node Rotate_right(Node y) {
	        Node x = y.left;
	        Node T2 = x.right;

	        // Perform rotation
	        x.right = y;
	        y.left = T2;

	        // Update heights
	        y.height = Math.max(height(y.left), height(y.right))+1;
	        x.height = Math.max(height(x.left), height(x.right))+1;

	        // Return new root
	        return x;
	    }

	    private static Node Rotate_left(Node x) {
	        Node y = x.right;
	        Node T2 = y.left;

	        // Perform rotation
	        y.left = x;
	        x.right = T2;

	        //  Update heights
	        x.height = Math.max(height(x.left), height(x.right))+1;
	        y.height = Math.max(height(y.left), height(y.right))+1;

	        // Return new root
	        return y;
	    }

	    // Get Balance factor of node N
	    private static int getBalance(Node N) {
	        if (N == null)
	            return 0;
	        return height(N.left) - height(N.right);
	    }

	    public void PreOrder(Node root) {
	        if (root != null) {

	            PreOrder(root.left);
	            System.out.println(" " + root.value);

	            
	            PreOrder(root.right);

	        }
	    }
	    
	  

	    private Node minValueNode(Node node) {
	        Node current = node;
	        /* loop down to find the leftmost leaf */
	        while (current.left != null)
	            current = current.left;
	        return current;
	    }

	    @SuppressWarnings("unused")
		private Node deleteNode(Node root, String value) {
	        // STEP 1: PERFORM STANDARD BST DELETE
            int c;
            c=value.compareTo(root.value);
	        if (root == null)
	            return root;

	        // If the value to be deleted is smaller than the root's value,
	        // then it lies in left subtree
	        if ( c<0 )
	            root.left = deleteNode(root.left, value);

	        // If the value to be deleted is greater than the root's value,
	        // then it lies in right subtree
	        else if( c>0 )
	            root.right = deleteNode(root.right, value);

	        // if value is same as root's value, then This is the node
	        // to be deleted
	        else {
	        	if(root.count>1) {
	        		(root.count)--;
	        		return null;
	        		
	        	}
	            // node with only one child or no child
	        	
	            if( (root.left == null) || (root.right == null) ) {

	                Node temp;
	                if (root.left != null)
	                        temp = root.left;
	                else
	                    temp = root.right;

	                // No child case
	                if(temp == null) {
	                    temp = root;
	                    root = null;
	                }
	                else // One child case
	                    root = temp; // Copy the contents of the non-empty child

	                temp = null;
	            }
	            else {
	                // node with two children: Get the PreOrder successor (smallest
	                // in the right subtree)
	                Node temp = minValueNode(root.right);

	                // Copy the PreOrder successor's data to this node
	                root.value = temp.value;

	                // Delete the PreOrder successor
	                root.right = deleteNode(root.right, temp.value);
	            }
	        }

	        // If the tree had only one node then return
	        if (root == null)
	            return root;

	        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
	        root.height = Math.max(height(root.left), height(root.right)) + 1;

	        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
	        //  this node became unbalanced)
	        int balance = getBalance(root);

	        // If this node becomes unbalanced, then there are 4 cases

	        // Left Left Case
	        if (balance > 1 && getBalance(root.left) >= 0)
	            return Rotate_right(root);

	        // Left Right Case
	        if (balance > 1 && getBalance(root.left) < 0) {
	            root.left =  Rotate_left(root.left);
	            return Rotate_right(root);
	        }

	        // Right Right Case
	        if (balance < -1 && getBalance(root.right) <= 0)
	            return Rotate_left(root);

	        // Right Left Case
	        if (balance < -1 && getBalance(root.right) > 0) {
	            root.right = Rotate_right(root.right);
	            return Rotate_left(root);
	        }

	        return root;
	    }

	   
	    
	    private static Integer getNodeHeight(Node node) {
	   	 
	        if(node == null) {
	            return -1;
	        }
	 
	        return Math.max(getNodeHeight(node.getLeft()), getNodeHeight(node.getRight()))+1;
	    }

	    public static boolean CheckMaxHeap(Node root) {
	    	if(root.right==null) {
	    		return true;
	    	}
			return false;
	    	
	    }

	    private static ArrayList<String> extractValues(Node n) {
	        ArrayList<String> result = new ArrayList<String>();
	        if (n.getLeft() != null) {
	            result.addAll(extractValues(n.left));
	        }

	        if (n.getRight() != null) {
	            result.addAll(extractValues(n.right));
	        }

	        result.add(n.value);

	        return result;
	    }

		  public static String sortString(String inputString) 
		    { 
		        // convert input string to char array 
		        char tempArray[] = inputString.toCharArray(); 
		          
		        // sort tempArray 
		        Arrays.sort(tempArray); 
		          
		        // return new sorted string 
		        return new String(tempArray); 
		    } 
		  
		  private static void printOwnAnagrams(String[] arr) {
				// TODO Auto-generated method stub
			  

				Map<String, Integer> countMap = new HashMap<>();
				int appearances = 0;

				for (int i = 0; i < arr.length; i++) {

					String word = arr[i];
					char[] wordsArray = word.toCharArray();
					Arrays.sort(wordsArray);
					String presentWord = new String(wordsArray);

					if (!countMap.containsKey(presentWord)) {
						countMap.put(presentWord, appearances);
					} else {
						countMap.put(presentWord, countMap.get(presentWord) + 1);
					}
				}

				for (int j = 0; j < arr.length; j++) {

					char[] sortedWords = arr[j].toCharArray();
					Arrays.sort(sortedWords);
					String sampleWord = new String(sortedWords);

					if (countMap.containsKey(sampleWord)) {
						System.out.println(arr[j] + " " + countMap.get(sampleWord));
					}
				}

			}
		
		  static int fill_array(Node root, String[] array,int pos) {
				if (root.left != null) {
			        pos = fill_array(root.left, array,pos);
			    }
			    array[pos++] = root.value;
			    if (root.right != null) {
			        pos = fill_array(root.right, array,pos);
			    }
				return pos;
			    
			}

	    public class Node {
	        Node left;
			Node right;
	        int height = 1;
	        String value;
	        int count;
        
	        Node (String val) {
	            value = val;
	            height=1;
	            count=1;
	        }

	        public Node getLeft() {
	            return left;
	        }
	    
              public Node getRight() {
            	  return right;
              }
	    }
	
	    
	    public static void main(String args[]) {
	        proj2f tree = new proj2f();
	        tree.PreOrder(tree.root); 
	        int input=1,max=0,size1=0;
			String k;
	        boolean t;
	        
	        while (input> 0 && input <9) {
		        
               
	            System.out.println("Enter your choice \n");

	        	System.out.println("(1) Create a Balanced Binary Search Tree\r\n");
	        	System.out.println("(2) Find the length of the Balanced Binary Search Tree.\r\n");
	        	System.out.println("(3) Add an element to BST\r\n");
	        	System.out.println("(4) Delete an element from BST.\r\n");
	        	System.out.println("(5) Print Elements of the BST.\r\n");
	        	System.out.println("(6) Check if BST is Max Binary Heap or not.\r\n");
	            System.out.println("(7) Find the number of Anagrams for each input string in the BST.\r\n");
	            System.out.println("(8) Exit\r\n");
	            input = new Scanner(System.in).nextInt();
	            
	           if(isInteger(input)== false)
	        	   System.out.println("Enter Integer");
	           
	            
	            switch (input) {
	                case 1:
	 	                	//tree.root=null;
	 	            		System.out.println("Please enter size of array");
	 	            		int size = new Scanner(System.in).nextInt();
	 	            		System.out.println("The size you entered is " + size);
	 	            		
	 	            		String[] array = new String[size];
	 	            		System.out.println("Enter the array: ");
	 	            		 
	 	            		int j = 0;
	 	            		while (j < size) {
	 	            		    System.out.print("Enter "+ (j+1) + ": ");
	 	            	        array[j] = new Scanner(System. in).next();     // getting input array
	 	            	        //System.out.println(array[j]);
	 	            		   tree.root=tree.insert(tree.root,array[j]);
	 	            		    //System.out.print(tree.root);
	 	            		    ++j;
	 	            		}	 	            		
	                    break;
	                case 2:
	                	//max = (height(tree.root.left) > height(tree.root.right)) ? height(tree.root.left) : height(tree.root.right);
	                	max=getNodeHeight(tree.root);
	                	System.out.println("Height of tree is" + " " + max);
	                   break;
	            
	                case 3:
	                	System.out.println("Please enter size of array");
 	            		 size1 = new Scanner(System.in).nextInt();
	                	System.out.println("The size you entered is " + size1);
 	            		String[] array1 = new String[size1];
 	            		System.out.println("Enter the array: ");
 	            		 
 	            		int j1 = 0;
 	            		while (j1 < size1) {
 	            		    System.out.print("Enter "+ (j1+1) + ": ");
 	            		    array1[j1] = new Scanner(System.in).nextLine();     // getting input array
 	            		    tree.root = tree.insert(tree.root,array1[j1]);
 	            		    //System.out.print(tree.root);
 	            		    ++j1;
 	            		    
 	            		}
 	            		tree.PreOrder(tree.root);

 	            		break;
 	            		
	                case 4:
	                	System.out.println("Enter the element to be deleted:");
	                	 k = new Scanner(System.in).nextLine();
		                tree.root = tree.deleteNode(tree.root, k);
 	            		tree.PreOrder(tree.root);

 	            		break;
 	            		
	                case 5:
                        System.out.println("Elements are : ");
 	            		tree.PreOrder(tree.root);
 	            		break;
 	                
	                case 6:
	                	t=CheckMaxHeap(tree.root);
	                	if(t==true) 
	                		System.out.println("Yes, It is a max heap");
	                	else
	                		System.out.println("No, its not a max heap");
	                	
	                	break;
	                	
	                case 7:
	                	
	               String[] values;
	               ArrayList<String> list= new ArrayList<String>();
	                list=extractValues(tree.root);
	                

	      		String[] arrayz = new String[list.size()];
	      		System.arraycopy(list.toArray(), 0, arrayz, 0, list.size());
	      		printOwnAnagrams(arrayz);                      
                          break;
	                
	                case 8 :
	                	System.out.println("Exiting...");
	                	input=15;
	                	break;

	               
	             
	            }
	             
	        }
	        
	       
	    }
	    }



