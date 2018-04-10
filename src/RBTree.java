
public class RBTree<T extends Comparable<T>> {
	//��������ԣ�
	//1��ÿ���ڵ��Ǻ�ɫ���ߺ�ɫ
	//2�����ڵ��Ǻ�ɫ
	//3��Ҷ�ӽ���Ǻ�ɫ��ע��Ҷ�ӽ��ָ���ǿսڵ㣩
	//4�����һ��Ҷ�ӵĽڵ��Ǻ�ɫ���������ӽڵ�����Ǻ�ɫ
	//5����һ���ڵ㵽�ýڵ�������������·���ϰ�����ͬ��Ŀ�ĺ�ɫ���
	//���������ǣ���ӡ�ɾ����������������������������Ŀ����Ϊ�˵������ĸ߶�
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
	 * �Ժ�����Ľڵ�(x)��������ת
	 *
	 * ����ʾ��ͼ(�Խڵ�x��������)��
	 *      px                              px
	 *     /                               /
	 *    x                               y                
	 *   /  \      --(����)-.             / \                #
	 *  lx   y                          x  ry     
	 *     /   \                       / \
	 *    ly   ry                     lx  ly  
	 */
	private void leftRotate(Node<T> x){
		 // ����x���Һ���Ϊy
		Node y=x.right;
		// �� ��y�����ӡ� ��Ϊ ��x���Һ��ӡ���
	    // ���y�����ӷǿգ��� ��x�� ��Ϊ ��y�����ӵĸ��ס�
		x.right=y.left;
		if(y.left!=null)
			y.left.parent=x;
		// �� ��x�ĸ��ס� ��Ϊ ��y�ĸ��ס�
		y.parent=x.parent;
		if(x.parent==null)
			this.root=y;  // ��� ��x�ĸ��ס� �ǿսڵ㣬��y��Ϊ���ڵ�
		else if(x.parent.left==x)
			x.parent.left=y;  // ��� x�������ڵ�����ӣ���y��Ϊ��x�ĸ��ڵ�����ӡ�
		else
			x.parent.right=y;  // ��� x�������ڵ���Һ��ӣ���y��Ϊ��x�ĸ��ڵ���Һ��ӡ�
		// �� ��x�� ��Ϊ ��y�����ӡ�
		y.left=x;
		// �� ��x�ĸ��ڵ㡱 ��Ϊ ��y��
		x.parent=y;
	}
	/* 
	 * �Ժ�����Ľڵ�(y)��������ת
	 *
	 * ����ʾ��ͼ(�Խڵ�y��������)��
	 *            py                               py
	 *           /                                /
	 *          y                                x                  
	 *         /  \      --(����)-.              /  \                     #
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
	 * �����������������
	 *
	 * ���������в���ڵ�֮��(ʧȥƽ��)���ٵ��øú�����
	 * Ŀ���ǽ������������һ�ź������
	 *
	 * ����˵����
	 *     node ����Ľ��        // ��Ӧ���㷨���ۡ��е�z
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
