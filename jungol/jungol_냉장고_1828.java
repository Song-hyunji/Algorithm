package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class jungol_냉장고_1828 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<tem> ref = new ArrayList<tem>();
		
		//입력받기
		for(int n=0; n<N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			ref.add(new tem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		//최저온도 오름차순으로 정렬
		Collections.sort(ref, (tem1, tem2)->
				tem1.low == tem2.low ? tem1.high-tem2.high : tem1.low-tem2.low);
		
		int count = 1; //냉장고 개수
		tem temp = ref.get(0); 
		
		for(int n=1; n<N; n++) {
			if( temp.high < ref.get(n).low) {
				count++;
				temp = ref.get(n);
			}else if( ref.get(n).high < temp.high ) {
					temp.high = ref.get(n).high;
			}
		}
		System.out.println(count);
		
	}
}
class tem { //최저온도, 최고온도 저장하는 클래스
	int low;
	int high;
	public tem(int low, int high) {
		super();
		this.low = low;
		this.high = high;
	}
	
}
