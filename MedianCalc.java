package TimeComplexity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MedianCalc {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the First Array Length ");
		int m=Integer.parseInt(br.readLine());
		
		System.out.println("Enter the Second Array Length ");
		int n=Integer.parseInt(br.readLine());
		
		int []arr1=new int[m];
		System.out.println("Enter "+m+"Elements for Sequences 1 ");
		for(int i=0;i<m;i++)
			arr1[i]=Integer.parseInt(br.readLine());
			
		
		int []arr2=new int[n];
		System.out.println("Enter "+n+"Elements for Sequences 1 ");
		for(int i=0;i<n;i++)
			arr2[i]=Integer.parseInt(br.readLine());
		
		// Sort the Arrays so that we can work on the indexes 
		
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		
	    //	int m=arr1.length;
     	//	int n=arr2.length;
		
		swap(arr1,arr2,m,n);
		
		// Check whether the size are different or same
		// Accordingly find the algorithms 
		if(m==n)
		{
			int res=findSameMedian(arr1,arr2,m);
			if(res==-1)
				System.out.println("Invalid array inputs ");
			System.out.println("Median of two arrays with same size "+res);
		}
		else
		{
			int res=findOddMedian(arr1,arr2,m,n);
			if(res==-1)
				System.out.println("Invalid array inputs ");
			System.out.println("Median of two arrays with different size "+res);
		}
		

	}

	private static void swap(int[] arr1, int[] arr2,int m,int n) {
		// TODO Auto-generated method stub
		// if larger length is already at arr1 then just return 
		if(m>n)
			return;
		// Function to swap the array with larger length to arr1 and smaller length to arr2
		if(n>m)
		{
			int temp[]=arr1;
			arr1=arr2;
			arr2=temp;
		}
		
	}

	private static int findOddMedian(int[] arr1, int[] arr2, int m, int n) {
		// TODO Auto-generated method stub
		/*
		 *  step 1 find the ending point as high= length of arrays 
		 *  traverse till the end 
		 *  find the mid index as cut1 and cut2  of arr1 and arr2  respectively
		 *  
		 *  find the left1 right1 left2 right2 
		 *  they are obtained by find the cut1 and cut2 indexs 
		 *  variable left be the element left directed to the cut
		 *  variable right be the element at the cut
		 *  
		 *  then just check the condition wrt r1
		 *  --------->  if the left1 > right1 just updated the high as cut1-1
		 *  ---------> if the left2 > right2 just updated the low as cut1+1
		 *  
		 *  if length is even the find the maximum of left1 and left2 and add to the minimum of right1 and right2 and return
		 *  otherwise return minimum of right1 and right2 
		 *  
		 *
		 */
		int low=0;
		
		//int high=(m>n)?m-1:n-1;
		int high=m-1;
		//int l1,l2,r1,r2;
		
		while(low<=high)
		{
			int cut1=low+(high-low)/2;
			int cut2=(m+n)/2-cut1;
			
			int l1=cut1==0?Integer.MIN_VALUE:arr1[cut1-1];
		    int	l2=cut2==0?Integer.MIN_VALUE:arr2[cut2-1];
			int r1=cut1==0?Integer.MIN_VALUE:arr1[cut1];
			int r2=cut2==0?Integer.MIN_VALUE:arr2[cut2];
			
			if(l1>r1)
			{
				high=cut1-1;
			}
			else if(l2>r1)
				low=cut1+1;
			
			else
			{
				return (m+n)%2==0?Math.max(l1, l2)+Math.min(r1, r2):Math.min(r1, r2);
			}
		}
		return -1;
	}

	private static int findSameMedian(int[] arr1, int[] arr2, int m) {
		// TODO Auto-generated method stu
		
		/*
		 * traverse the arrays upto length of array1 
		 * i and j are the indexs of arrays 1 and arrays 2
		 * check whether the array 1 elements at index i is smaller than at j of array2 
		 * ---------->     swap the variable of two updated variables eg (m1,m2) as m2=index i elements and m1=m2 and continue 
		 *                 till the j greater than i 
		 * ---------->     swap the variable of two updated variables eg (m1,m2) as m2=index j elements and m1=m2 and continue 
		 *                 till the i greater than j
		 *  
		 * if the index i is already increasing and their elements smaller than j till the end of loop just 
		 * updated m1 and m2 and at the i reaches length of arr1
		 * just update m1 m2 we did earlier and m2 stores the first element of arr2 and break the condition
		 * and it sames for j reaches the end of loop ...just update m1 and m2 and m2 stores the first element of arr1 and break
		 * 
		 *                                  we reached the end of function by returning median 
		 *                                  as sum of m1 and m2 we have after the end of loop and divide by 2 gives the median
		 *                 
		 * 
		 */
		int m1=-1,m2=-1;
		int i=0,j=0;
		for(int counter=0;counter<=m;counter++)
		{
			if(i==m)
			{
				m1=m2;
				m2=arr2[j];
				break;
			}
			else if(j==m)
			{
				m1=m2;
				m2=arr1[i];
				break;
			}
			if(arr1[i]<=arr2[j])
			{
				m1=m2;
				m2=arr1[i];
				i++;
			}
			else
			{
				m1=m2;
				m2=arr2[j];
				j++;
			}
		}
		return (m1+m2)/2;
	}

}
