
public class RBTree<T extends Comparable<T>> {
	//红黑树特性：
	//1、每个节点是黑色或者红色
	//2、根节点是黑色
	//3、叶子结点是黑色（注：叶子结点指的是空节点）
	//4、如果一个叶子的节点是红色，则它的子节点必须是黑色
	//5、从一个节点到该节点的子孙结点的所有路径上包含相同数目的黑色结点
	//基本操作是：添加、删除、左旋、右旋，左旋和右旋的目的是为了调节树的高度
	private Node<T> root;
	private static final boolean RED=false;
	private static final boolean BLACK=true;
	private int count;
	
	private class Node<T extends Comparable<T>>{
		boolean color;
		T key;
		Node<T> left;
		Node<T> right;
		Node<T> parent;
		public Node(T key,boolean color,Node<T> left,Node<T> right,Node<T> parent){
			this.color=color;
			this.key=key;
			this.left=left;
			this.right=right;
			this.parent=parent;
		}
	}
	/* 
	 * 对红黑树的节点(x)进行左旋转
	 *
	 * 左旋示意图(对节点x进行左旋)：
	 *      px                              px
	 *     /                               /
	 *    x                               y                
	 *   /  \      --(左旋)-.             / \                #
	 *  lx   y                          x  ry     
	 *     /   \                       / \
	 *    ly   ry                     lx  ly  
	 */
	private void leftRotate(Node<T> x){
		 // 设置x的右孩子为y
		Node y=x.right;
		// 将 “y的左孩子” 设为 “x的右孩子”；
	    // 如果y的左孩子非空，将 “x” 设为 “y的左孩子的父亲”
		x.right=y.left;
		if(y.left!=null)
			y.left.parent=x;
		// 将 “x的父亲” 设为 “y的父亲”
		y.parent=x.parent;
		if(x.parent==null)
			this.root=y;  // 如果 “x的父亲” 是空节点，则将y设为根节点
		else if(x.parent.left==x)
			x.parent.left=y;  // 如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
		else
			x.parent.right=y;  // 如果 x是它父节点的右孩子，则将y设为“x的父节点的右孩子”
		// 将 “x” 设为 “y的左孩子”
		y.left=x;
		// 将 “x的父节点” 设为 “y”
		x.parent=y;
	}
	/* 
	 * 对红黑树的节点(y)进行右旋转
	 *
	 * 右旋示意图(对节点y进行左旋)：
	 *            py                               py
	 *           /                                /
	 *          y                                x                  
	 *         /  \      --(右旋)-.              /  \                     #
	 *        x   ry                           lx   y  
	 *       / \                                   / \                   #
	 *      lx  rx                                rx  ry
	 * 
	 */
	private void rightRotate(Node<T> y){
		Node x=y.left;
		y.left=x.right;
		if(x.right!=null){
			x.right.parent=y;
		}
		x.parent=y.parent;
		if(y.parent==null){
			this.root=x;
		}else if(y.parent.left==y){
			y.parent.left=x;
		}else{
			y.parent.right=x;
		}
		 
		x.right=y;
		 
		y.parent=x;
		
	}
	public int size(){
		return count;
	}
	public void insert(T key){
		Node<T> node=new Node<T>(key,BLACK,null,null,null);
		if(node!=null){
			insert(node);
		}
	}
	private void insert(Node<T> node){
		int cmp;
		Node<T> y=null;
		Node<T> x=this.root;
		
		while(x!=null){
			y=x;
			cmp=node.key.compareTo(x.key);
			if(cmp<0){
				x=x.left;
			}else{
				x=x.right;
			}
		}
		node.parent=y;
		if(y!=null){
			cmp=node.key.compareTo(y.key);
			if(cmp<0){
				y.left=node;
			}else{
				y.right=node;
			}
		}else{
			this.root=node;
		}
		node.color=RED;
		insertFixUp(node);
		
	}
	/*
	 * 红黑树插入修正函数
	 *
	 * 在向红黑树中插入节点之后(失去平衡)，再调用该函数；
	 * 目的是将它重新塑造成一颗红黑树。
	 *
	 * 参数说明：
	 *     node 插入的结点        // 对应《算法导论》中的z
	 */
	private void insertFixUp(RBTree<T>.Node<T> node) {
		// TODO Auto-generated method stub
		Node<T> parent,gparent; 
		while((parent=node.parent)!=null&&parent.color==RED){
			gparent=parent.parent;
			if(parent==gparent.left){
				Node<T> uncle=gparent.right;
				if(uncle!=null&&uncle.color==RED){
					uncle.color=BLACK;
					parent.color=BLACK;
					gparent.color=RED;
					node=gparent;
					continue;
				}
				if(parent.right==node){
					Node<T> tmp;
					leftRotate(parent);
					tmp=parent;
					parent=node;
					node=tmp;
				}
				parent.color=BLACK;
				gparent.color=RED;
				rightRotate(gparent);
			}else{
				Node<T> uncle=gparent.left;
				if(uncle!=null&&uncle.color==RED){
					uncle.color=BLACK;
					parent.color=BLACK;
					gparent.color=RED;
					node=gparent;
					continue;
				}
				if(parent.left==node){
					Node<T> tmp;
					rightRotate(parent);
					tmp=parent;
					parent=node;
					node=tmp;
				}
				parent.color=BLACK;
				gparent.color=RED;
				leftRotate(gparent);
				
			}
		}
		root.color=BLACK;
	}
	

}
