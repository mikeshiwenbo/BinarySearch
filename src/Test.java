
public class Test {
	public static void main(String[] args){
		Integer[] a=new Integer[300];
		for(int i=0;i<300;i++){
			a[i]=i;
		}
		int q=BinarySearch.search(a,20,0,300);
		System.out.println(q);
	}
}
