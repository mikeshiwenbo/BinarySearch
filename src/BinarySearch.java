
public class BinarySearch {
	public static int search(Comparable[] a,Comparable value,int lo,int hi){
		int i=lo,j=hi-1;
		while(i<=j){
			//不使用int mid=(j+i)/2;因为i+j可能越界，造成系统报错
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
