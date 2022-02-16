package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back_주유소_13305 {

	static int N; //도시 개수
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		long[] length = new long[N-1]; //도시 간 거리
		long[] price = new long[N]; //주유소 리터당 가격
		
		//도시 간 거리 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N-1; i++) {
			length[i] = Integer.parseInt(st.nextToken());
		}
		
		//주유소 가격 입력받기
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}
		
		long ans = 0; //비용
		long lowprice = price[0]; 
		for(int i=0; i<N-1;i++) {
			if(price[i] < lowprice) {
				lowprice = price[i];
			}
			ans += lowprice * length[i];
		}
		System.out.println(ans);
	}

}
