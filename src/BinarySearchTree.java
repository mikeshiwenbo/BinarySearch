import java.util.LinkedList;
import java.util.Queue;

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
/************************************************************************/
	public int size(){
		return count;
	}
/************************************************************************/
	public boolean isEmpty(){
		return count==0;
	}
/************************************************************************/
	public void insert(Comparable key,Value value){
		root=insert(root,key,value);//����ڵ�
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
/************************************************************************/
	public boolean contain(Comparable key){
		return contain(root,key);
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
/************************************************************************/
	public BinarySearchTree<Value>.Node search(Comparable key){
		return search(root,key);
	}
	private BinarySearchTree<Value>.Node search(BinarySearchTree<Value>.Node root, Comparable key) {
		// TODO Auto-generated method stub
		if(root==null) return null;
		if(root.key.compareTo(key)==0) return root;
		else if(key.compareTo(root.key)<0) return search(root.left,key);
		else return search(root.right,key);
	}
/************************************************************************/
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
/************************************************************************/
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
/************************************************************************/
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
/************************************************************************/
	//������ȱ�����в������  ��ʵ�ֹ�����ȱ�����Ҫ�������У����ȰѸ��ڵ�����У����ų����У����˽ڵ������Һ��ӣ����˽ڵ�����Һ�������У����ν��У�ֱ������Ϊ��
	public void levelOrder(){
		levelOrder(root);
	}
	private void levelOrder(BinarySearchTree<Value>.Node root2) {
		// TODO Auto-generated method stub
		Queue<BinarySearchTree<Value>.Node> q=new LinkedList<BinarySearchTree<Value>.Node>();;
		q.offer(root2);
		while(!q.isEmpty()){
			Node n=q.poll();
			System.out.println(n.key+"   "+n.value);
			if(n.left!=null)
				q.add(n.left);
			if(n.right!=null)
				q.add(n.right);
		}
	}
/************************************************************************/
	//Ѱ�Ҷ��������������ֵ����С��ֵ
	Node minimum(){
		if(count==0) return null;
		Node node=minimum(root);
		return node;
	}
	private BinarySearchTree<Value>.Node minimum(BinarySearchTree<Value>.Node root2) {
		// TODO Auto-generated method stub
		if(root2.left==null) return root2;
		return minimum(root2.left);
	}
/************************************************************************/
	//����
	Node maximum(){
		if(count==0) return null;
		Node node=maximum(root);
		return node;
	}	
	private BinarySearchTree<Value>.Node maximum(BinarySearchTree<Value>.Node root2) {
		// TODO Auto-generated method stub
		if(root2.right==null) return root2;
		return maximum(root2.right);
	}
/************************************************************************/
	//ɾȥ��Сֵ
	public void removeMin(){
		root=removeMin(root);
	}
	private BinarySearchTree<Value>.Node removeMin(BinarySearchTree<Value>.Node root2) {
		// TODO Auto-generated method stub
		if(root2.left==null){
			Node rightNode=root2.right;
			count--;
			return rightNode;
		}
		root2.left=removeMin(root2.left);
		return root2; //�������󣬷��ص��Ǹ��ڵ�
	}
/************************************************************************/
	//ɾ�����ֵ
	public void removeMax(){
		root=removeMax(root);
	}
	private BinarySearchTree<Value>.Node removeMax(BinarySearchTree<Value>.Node root2) {
		// TODO Auto-generated method stub
		if(root2.right==null){
			Node leftNode=root2.left;
			return leftNode;
		}
		root2.right=removeMax(root2.right);
		return root2;
	}
/************************************************************************/	
/************************************************************************/
/************************************************************************/
/************************************************************************/
/************************************************************************/



}
