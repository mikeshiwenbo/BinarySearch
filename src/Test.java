
public class Test {
	public static void main(String[] args){
		Integer[] a=new Integer[3000];
		//���Զ��ֲ��ҷ�
//		for(int i=0;i<300;i++){
//			a[i]=i;
//		}
//		int q=BinarySearch.search(a,20,0,300);
//		System.out.println(q);
		//���Զ���������
		for(int i=0;i<a.length;i++){
			a[i]=(int)(Math.random()*60);
//			System.out.println(a[i]);
		}
		BinarySearchTree<Integer> test=new BinarySearchTree<>();
		for(int i=0;i<a.length;i++){
			if(test.contain(a[i])){
				test.search(a[i]).value++;
			}else{
				test.insert(a[i], i);
			}
		}
//		for(int i=0;i<a.length;i++){
//			System.out.println(test.search(a[i]).key+"   "+test.search(a[i]).value);
//		}
		System.out.println("---------------------------");
		test.preOrder();
		System.out.println("---------------------------");
		test.inOrder();
		System.out.println("---------------------------");
		test.postOrde();
	}
}
