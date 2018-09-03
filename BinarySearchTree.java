package binarytree.thread;

/**
 * This class relate to the functional of the program.
 */
public class BinarySearchTree 
{
	// variable.
	private Node root;
	private int size;
	private Node median; // floor median.

	
	/**
	 * Constructs a BinarySearchTree object.
	 */
	public BinarySearchTree()
	{
		this.root = null;
		size = 0;
		median = null;
	}


	/**
	 * Get the root of the tree.
	 * @return	Node of the root.
	 */
	public Node getRoot() 
	{
		return this.root;
	}


	/**
	 * Get the size of the tree.
	 * @return	size of the tree.
	 */
	public int getSize()
	{ 
		return this.size; 
	}


	/**
	 * check if the tree is empty.
	 * @return	true if the tree is empty. Else return false.
	 */
	public boolean isEmpty()
	{
		return isEmpty(root);
	}


	/**
	 * private method of isEmpty().
	 */
	private boolean isEmpty	(Node Root)
	{ 
		if (Root==null) 
			return true;
		return false;
	} 


	/**
	 * Get the Median by the value of the tree (the median id). 
	 * @return	Node of the median value
	 */
	public Node getMedianValue ()
	{
		return this.median;
	}


	/**
	 * Private method, Checks if node father is real parent of node son.
	 * @param father	node that we want check if is parent.
	 * @param son	node that we want check if is son.
	 * @return	true if Node father is the parent of node son, else false.
	 */
	private boolean isFather (Node father, Node son) 
	{
		if (father == null && son == null) // if they both null - it's not possible, so it have to return false.
			return false;
		if (father == null && son.getParent() == father)
			return true; //is the root.
		if (son== null) // means the father is max or minimum.
			return true;
		if (son.getParent() == father)
			return true;
		else
			return false;
	}


	/**
	 * update the median value of the tree after deleting node (private method).
	 * @param current	value that was deleted.
	 */
	private void updateMedianAfterDelete (Node current) 
	{
		if (this.size == 0) // if now it's empty, there is no median. 
			median = null;
		else if (this.size % 2 == 1)  // if we have now odor nodes in the tree.
			if (median != null &&current.getKey() > median.getKey()) 
				//median actually should never be null, because if it's null we made deletion in empty tree, and it's impossible.
				; // do nothing.
			else 
				median = getSuccessor(median);
		else if (this.size%2 == 0) // if we have even number of the nodes in the tree.
			if (median != null &&current.getKey() > median.getKey())
				median = getPredecessor(median);
			else
				; // do nothing.
	}


	/**
	 * Get the max value of the tree.
	 * @return	Node of the max value.
	 */
	public Node getMax() 
	{
		return getMax(root);
	}


	/**
	 * Get the max value of subTree (private method). 
	 * Uses us for get Max, and predecessor.
	 * @param subRoot	node that we want to check the maximum.
	 * @return	Node of the subTree max value
	 */
	private Node getMax (Node subRoot) 
	{
		Node curr = subRoot;
		if (curr == null)
			return null;
		while (curr.getRight() != null && isFather(curr, curr.getRight())) 
			// is father checks if we actually reached null.
			curr = curr.getRight();
		return curr;
	}


	/**
	 * Get the minimum value of the tree.
	 * @return Node of the minimum value.
	 */
	public Node getMin()
	{
		return getMin(root);
	}


	/**
	 * Get the minimum value of subTree (private method). 
	 * Uses us for get Min, and successor.
	 * @param subRoot	node that we want to check the minimum.
	 * @return Node of the subTree minimum value
	 */
	private Node getMin(Node subRoot) 
	{
		Node curr = subRoot;
		if (curr == null)
			return null;
		while (curr.getLeft() != null && isFather(curr, curr.getLeft()))
			curr = curr.getLeft();
		return curr;
	}


	/**
	 * Update the size of the tree (private method). 
	 * Uses us while adding or deleting node.
	 * @param num	size to add to current size (always grow by 1 or decrease by 1).
	 */
	private void changeSize (int num) 
	{
		this.size = size + num;
	}


	/**
	 * Get Node from the tree by its ID.
	 * @param id	(int value that represent ID).
	 * @return	Node (if not found return error).
	 */
	public Node find(int id) 
	{
		return findPrivate(id);
	}


	/**
	 * Get Node from the tree by its ID (private node).
	 * @param id	(int value that represent ID).
	 * @return	Node (if not found return error).
	 */
	private Node findPrivate(int id)
	{
		Node father = null;
		Node current = this.root;
		while(current!=null && isFather(father, current) )
		{
			// is father: if the current is actually a null in a normal binary Search tree.
			if(current.getKey()==id)
			{
				return current; // if found: return current.
			}
			else if(current.getKey()>id)
			{
				father = current;
				current = current.getLeft();

			}
			else
			{
				father = current;
				current = current.getRight();

			}
		}
		return null; // not found: return null.
	}


	/**
	 * Delete Node from the tree by its ID.
	 * @param id	(int value that represent ID). (int value that represent ID).
	 * @return	true if found, else false. Delete the node from the tree if found. 
	 */
	public boolean delete(int id) 
	{
		return deletePrivate(id);
	}


	/**
	 * Delete Node from the tree by id (private method)
	 * @param id	(int value that represent ID). - (int value that represent ID).
	 * @return	true if found, else false.
	 */
	private boolean deletePrivate(int id)
	{
		// first part, checkes if the node exist in the tree. 
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
		while(current.getKey()!=id)
		{
			parent = current;
			if(current.getKey()>id)
			{
				isLeftChild = true;
				current = current.getLeft();
			}
			else
			{
				isLeftChild = false;
				current = current.getRight();
			}
			if(current ==null)
			{
				return false; // the node is not exisit in the tree.
			}
		}
		//if we reached to this point that means we have found the node. current is the node to be deleted. parent its parent.
		changeSize (-1);
		// there are 3 cases to deletion. case 1 - node to be deleted is a leave. case 2 - node to be deleted is a node with one child. case 3 - node to be deleted is a node with 2 childs. 
		if (deletionCaseOneOrTwo(current, parent, isLeftChild)== true) 
			return true; // means it handled the deletion by cases 1 or 2. no more job needed.

		// case 3 : happy father with 2 children
		else if((isFather(current, current.getLeft()) && isFather(current, current.getRight())))
		{ 
			//now we have found the minimum element in the right sub tree. we not delete the real node, but changes its values with its successor who must be one of case 1 or 2.
			Node successor = getSuccessor(current); // find its successor.
			// change the values.
			current.setKey(successor.getKey());
			current.setStudentName(successor.getStudentName());

			// We copied the information of the successor to the relevant node, with the 2 childs. now, because successor can have or 1 child or 0 child - we will continue with the code as it is in case 1 or 2.
			// we update all the relevant data for the new node to be deleted by case 1 or 2.
			current = successor;
			parent = successor.getParent();
			if (parent.getLeft() == current) 
				isLeftChild = true;
			else
				isLeftChild = false;
		}
		// now we do it with case 2 and case 1
		if (deletionCaseOneOrTwo(current, parent, isLeftChild)== true)
			return true;
		return true; // never can reach this point. written to make the code compile. 
	}

	
	/**
	 * in order to avoid double coding, this method created, to helps us delete node by cases 1 or 2.
	 * @param current	the node to be deleted. 
	 * @param parent	its parent.
	 * @param isLeftChild	true if the node to be deleted is a left child. else false. 
	 * @return	 true if handled by case 1 or 2. else - return false. (will never return false, as the condition to enter this function is only after we checked it should be deleted by case 1 or 2).
	 */
	private boolean deletionCaseOneOrTwo (Node current, Node parent, boolean isLeftChild) { // case 1 - node to delete is leave. case 2 -node to delete has 1 child.

		//Case 1: if node to be deleted has no children - a leave.
		if((current.getLeft()==null && current.getRight()==null) ||!isFather(current,current.getLeft()) && !isFather(current,current.getRight()))
		{ // if they both null -its' a leave.
			if(current==root)
			{ // if it was the root and it doesn't have children.
				root = null;
				median = null;
			}
			if(isLeftChild ==true)
			{ // if the leave in a left child
				parent.setLeft(getPredecessor(parent.getLeft())); // update the father.
				updateMedianAfterDelete (current);
				return true;
			}
			else
			{
				parent.setRight(getSuccessor(parent.getRight()));  // update the father.
				updateMedianAfterDelete (current);
				return true;
			}
		}
		//Case 2 : if node to be deleted has only one child.
		else if(!isFather(current, current.getRight()) || current.getRight()==null)
		{  // if it has only left subtree
			if(current==root)
			{ // if it gots right child, and it's root:
				getPredecessor(current).setRight(getSuccessor(current));
				root = current.getLeft(); 
				updateMedianAfterDelete (current);
				root.setParent(null); // update its root parent value. 
				return true;
			}
			else if(isLeftChild)
			{
				getPredecessor(current).setRight(getSuccessor(current));
				parent.setLeft(current.getLeft());
				parent.getLeft().setParent(parent); // update its parent value.
				updateMedianAfterDelete (current);
				return true;
			}
			else //if it's right child.
			{
				getPredecessor(current).setRight(getSuccessor(current));
				parent.setRight(current.getLeft());
				parent.getRight().setParent(parent); // update its parent value.
				updateMedianAfterDelete (current);
				return true;
			}
		}
		else if(!isFather(current, current.getLeft()) || current.getLeft()==null)
		{  // has right subtree. not left.
			if(current==root)
			{
				getSuccessor(current).setLeft(getPredecessor(current));
				root = current.getRight();
				root.setParent(null); // update its root parent value.
				updateMedianAfterDelete (current);
				return true;
			}
			else if(isLeftChild)
			{
				getSuccessor(current).setLeft(getPredecessor(current));
				parent.setLeft(current.getRight());
				parent.getLeft().setParent(parent);  // update its parent value.
				updateMedianAfterDelete (current);
				return true;

			}
			else
			{	// deal with maximum and minimum to be deleted. 
				if (getSuccessor(current) == null) 
				{
					parent.setRight(current.getLeft());
					parent.getRight().setParent(parent);
					updateMedianAfterDelete(current);
					return true;
				}
				else 
				{
					getSuccessor(current).setLeft(getPredecessor(current));
					parent.setRight(current.getRight());
					parent.getRight().setParent(parent);
					updateMedianAfterDelete (current);
					return true;
				}
			}
		}
		return false;
	}



	/**
	 * Get the successor of a given node.
	 * @param node	 the node that we wont to find the successor.
	 * @return	 Node of the successor.
	 */
	public Node getSuccessor(Node node) 
	{
		return getSuccessorPrivate(node);
	}


	/**
	 * Get the successor of a given node (private method). 
	 * @param node	the node that we wont to find the successor.
	 * @return Node of the successor.
	 */
	private Node getSuccessorPrivate(Node node)
	{
		if (node == null) // if the node we gets it's null, it's impossible to know its successor.
			return null;
		if (node.getRight() != null && isFather(node, node.getRight())) // if it got right son - it's the minimun of right son BST.
			return getMin(node.getRight());
		Node parentOfInput = node.getParent();
		while (parentOfInput != null && node == parentOfInput.getRight()) 
		{
			node = parentOfInput;
			parentOfInput = parentOfInput.getParent();
		}
		return parentOfInput; // if there is no Successor, it will return null (for example - if it's the max val);
	}


	/**
	 * Get the predecessor of a given node.  
	 * @param node	 the node that we wont to find the Predecessor.
	 * @return	 node of the predecessor
	 */
	public Node getPredecessor(Node node) 
	{
		return getPredecessorPrivate(node);
	}


	/**
	 * Get the predecessor of a given node(private method).  
	 * @param node 	the node that we wont to find the Predecessor.
	 * @return	 node of the predecessor.
	 */
	private Node getPredecessorPrivate(Node node)
	{
		if (node == null) // if the node we gets it's null, it's impossible to know its successor.
			return null;
		if (node.getLeft() != null && isFather(node, node.getLeft())) // if it got left son - the Predecessor is the max in its bst.
			return getMax(node.getLeft());
		Node parentOfInput = node.getParent();
		while (parentOfInput!= null && node == parentOfInput.getLeft()) 
		{
			node = parentOfInput;
			parentOfInput = parentOfInput.getParent();
		}
		return parentOfInput; // if there is no predecessor, it will return null (for example - if it's the min val);
	}

	
	/**
	 * Wiring the new node after insert.
	 * If left is null - update to predecessor of the node.
	 * If right is null - update to successor of the node.
	 * (private method)
	 * @param ID	(int value that represent ID).   (int value that represent ID).   id of new insert node 
	 */
	private void wireNodeAfterInsert (int id) 
	{
		Node current = root;
		while(current.getKey()!=id)
		{
			if(current.getKey()>id)
			{
				current = current.getLeft();
			}
			else
			{
				current = current.getRight();
			}
			if (current.getLeft() == null)
				current.setLeft(getPredecessor(current));
			if (current.getRight() == null)
				current.setRight(getSuccessor(current));
		}
	}


	/**
	 * insert new node to the tree
	 * @param id	The id of node that we want to insert to the tree (int value that represent ID).
	 * @param name	The name of node that we want to insert to the tree.
	 */
	public void insert(int id, String name) 
	{ 
		insertPrivate(id, name);
	}


	/**
	 * insert new node to the tree (private method).
	 * @param id	The id of node that we want to insert to the tree (int value that represent ID).
	 * @param name	The name of node that we want to insert to the tree.
	 */
	private void insertPrivate(int id, String name)
	{
		changeSize (1);
		Node newNode = new Node(id, name);
		if(root==null)
		{ // Also we can use "if size is 1"
			root = newNode;
			median = root;
			return; /// if the tree is empty. put it in the root. we dont need to fix as wired if it's empty. so we don't send it to the other method.
		}
		Node current = root;
		Node parent = null;
		while(true) // find the place for the new node and insert it. 
		{
			parent = current;
			if(id<current.getKey()) 
			{
				current = current.getLeft(); 
				if(current == null || !isFather(parent, current))
				{ // if parent is not the father of current, so current actually should be null in non-wired tree. 
					parent.setLeft(newNode);
					newNode.setParent(parent);
					wireNodeAfterInsert(id); // should take care the new node to be wired.
					setMedianAfterInsert(newNode);
					return;
				}
			}
			else
			{
				current = current.getRight();
				if(current == null || !isFather(parent, current))
				{ // if parent is not the father of current, so current actually should be null in non-wired tree. 
					parent.setRight(newNode);
					newNode.setParent(parent);
					wireNodeAfterInsert(id); // should take care the new node to be wired.
					setMedianAfterInsert(newNode);
					return;
				}
			}
		}
	}
	
	
	/**
	 * update median after insertion (private method).
	 * @param node	(node that just inserted).
	 */
	private void setMedianAfterInsert(Node node)
	{
		if(size % 2 == 1) 
			if (node.getKey() > median.getKey())
				median = getSuccessor(median); // we use median, so if we have something above. it means that before it was median. and we need to update it.
			else
				return;
		else if (size % 2 == 0)
			if (node.getKey() > median.getKey())
				return;
			else
				median = getPredecessor(median); // we use median, so if we have something above. it means that before it was median. and we need to update it.
		return;
	}


	/**
	 * print tree as inorder traversal.
	 */
	public void inorder () 
	{
		inorder(null, this.root);
		System.out.print('\n');
	}


	/**
	 * print tree as inorder traversal(private method).
	 * @param father	parent of the root (null).
	 * @param root	the root of the tree. 
	 */
	private void inorder(Node father, Node root)
	{
		if(root!=null && isFather(father, root))
		{
			inorder(root, root.getLeft());
			System.out.print(" " + root.getKey());
			inorder(root, root.getRight());
		}
	}


	/**
	 * print tree as preorder traversal. 
	 */
	public void preorder() 
	{
		preorder(null, this.root);
		System.out.print('\n');
	}


	/**
	 * print tree as preorder traversal(private method).
	 * @param	parent of the root (null).
	 * @param root	the root of the tree. .
	 */
	private void preorder(Node father, Node root)
	{
		if(root!=null && isFather(father, root))
		{
			System.out.print(" " + root.getKey());
			preorder(root,root.getLeft());
			preorder(root,root.getRight());
		}
	}


	/**
	 * print tree as postorder traversal.
	 */
	public void postorder () 
	{
		postorder(null, root);
		System.out.print('\n');
	}


	/**
	 * print tree as postorder traversal(private method).
	 * @param father	parent of the root (null).
	 * @param root	the root of the tree. .
	 */
	private void postorder(Node father, Node root)
	{
		if(root!=null && isFather(father, root))
		{
			postorder(root, root.getLeft());
			postorder(root, root.getRight());
			System.out.print(" " + root.getKey());
		}
	}

}
