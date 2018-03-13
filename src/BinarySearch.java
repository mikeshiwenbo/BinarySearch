
public class BinarySearch {
	public static int search(Comparable[] a,Comparable value,int lo,int hi){
		int i=lo,j=hi-1;
		while(i<=j){
			//��ʹ��int mid=(j+i)/2;��Ϊi+j����Խ�磬���ϵͳ����
			int mid=i+(j-i)/2;
			if(a[mid].compareTo(value)==0){
				return mid;
			}
			if(a[mid].compareTo(value)<0){
				i=mid+1;
			}else{
				j=mid-1;
			}
			
		}
		return -1;
	}
}
