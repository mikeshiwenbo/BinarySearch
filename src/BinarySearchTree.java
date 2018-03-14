
public class BinarySearchTree<Value> {
	//�����������ǲ���ȫ�Ķ�������ÿһ���ڵ��м�ֵ�ԣ����ӵļ���ԶС�ڽڵ�ļ����Һ��ӵļ���ԶС�ڴ˽ڵ�ļ�
	//���㷨�ĺ����ǣ������²���Ľڵ㣬���Ⱥ͸��ڵ�ļ��Ƚϣ���С�ڣ����������ӱȽϣ��������Һ��ӱȽϣ���˵ݹ飬ֱ���ҵ�һ���յĽڵ�洢���µĽڵ�
	//�㷨�ŵ㣺���ҡ�ɾ�������붼��log(n)
	class Node{
		Comparable key;
		Value value;
		Node left;
		Node right;	
		//����ڵ�
		public Node(Comparable key,Value value){
			this.key=key;
			this.value=value;
			this.left=this.right=null;
		}
	}
	private Node root;
	int count;
	public int size(){
		return count;
	}
	public boolean isEmpty(){
		return count==0;
	}
	public void insert(Comparable key,Value value){
		root=insert(root,key,value);//����ڵ�
	}
	public boolean contain(Comparable key){
		return contain(root,key);
	}
	public BinarySearchTree<Value>.Node search(Comparable key){
		return search(root,key);
	}
	//ǰ�����   �ȷ����Լ���Ȼ�������ӣ������Һ���
	public void preOrder(){
		perOrder(root);
	}
	private void perOrder(BinarySearchTree<Value>.Node root2) {
		// TODO Auto-generated method stub
		if (root2!=null){
			System.out.println(root2.key+"   "+root2.value);
			perOrder(root2.left);
			perOrder(root2.right);
			}
	}
	//�������   �ȷ������ӣ��ٷ����Լ�������Һ���     ��ӡ����Ǹ���key��С����������е�
	public void inOrder(){
		inOrder(root);
	}
	private void inOrder(BinarySearchTree<Value>.Node root2) {
		// TODO Auto-generated method stub
		if (root2!=null){
			inOrder(root2.left);
			System.out.println(root2.key+"   "+root2.value);
			inOrder(root2.right);
			}
	}
	//�������    �������ӣ������Һ��ӣ�����Լ�    ���ã������ͷ����Һ���
	public void postOrde(){
		postOrder(root);
	}
	private void postOrder(BinarySearchTree<Value>.Node root2) {
		// TODO Auto-generated method stub
		if(root2!=null){
			postOrder(root2.left);
			postOrder(root2.right);
			System.out.println(root2.key+"   "+root2.value);
		}
	}
	 
	private BinarySearchTree<Value>.Node search(BinarySearchTree<Value>.Node root, Comparable key) {
		// TODO Auto-generated method stub
		if(root==null) return null;
		if(root.key.compareTo(key)==0) return root;
		else if(key.compareTo(root.key)<0) return search(root.left,key);
		else return search(root.right,key);
	}
	private boolean contain(Node root,Comparable key){
		if(root==null) return false;
		if(root.key.compareTo(key)==0) return true;
		else if(root.key.compareTo(key)>0){
			return contain(root.left,key);
		}else{
			return contain(root.right,key);
		}
		
	}
	private BinarySearchTree<Value>.Node insert(BinarySearchTree<Value>.Node root2, Comparable key, Value value) {
		// TODO Auto-generated method stub
		if(root2==null){ 
			count++;
			return new Node(key, value);
		}
		if(key.compareTo(root2.key)==0) root2.value=value; //������ͬ�������ֵ
		else if (key.compareTo(root2.key)<0) root2.left=insert(root2.left,key,value);//��С�ڽڵ�ļ�����������
		else root2.right=insert(root2.right,key,value);//�����ڽڵ�ļ��������Һ���
		return root2;//���ظ��ڵ�
	}

}
