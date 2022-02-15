package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class bj_백설공주난쟁이_3040 {

	static int[] a;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		a = new int[9];
		int sum = 0; //아홉 난쟁이의 합
		for(int i=0; i<9; i++) {
			a[i] = Integer.parseInt(br.readLine());
			sum+=a[i]; 
		}
		
		total : for(int i=0; i<9; i++) {
			for(int j=i; j<9; j++) {
				if((sum-a[i]-a[j]) == 100) {
					for(int k=0; k<9; k++) {
						if(k==i || k==j) continue;
						System.out.println(a[k]);
					}
					break total;
				}
			}
		}
		
	}

}
