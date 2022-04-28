import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
N개 시험장
감독관 : 총, 부
총감독관 : B명 감시 가능 (시험장 당 1명)
부감독관 : C명 감시 가능 (시험장 당 여러명)
 */
public class Back_시험감독_13458 {

	static int N, B, C;
	static int[] room; //시험장에 있는 응시자 수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		room = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			room[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken()); //총 감독관
		C = Integer.parseInt(st.nextToken()); //부 감독관
		
		long ans = 0; //long으로 해야함... 주의 !!
		for(int i=0; i<N; i++) {
			room[i] -= B; ans++;
			if(room[i] <= 0) continue;
			
			ans += room[i] / C;
			room[i] = room[i] % C;
			if(room[i] > 0) ans++;
		}
		System.out.println(ans);
		
	}
}
