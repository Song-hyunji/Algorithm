package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_요리사_4012 {

	static int N, ans;
	static int[][] S;
	static int[] select;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			
			S = new int[N][N];
			
			//입력받기
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) { //대각선 기준으로 대칭 되게 짝 끼리 더해주기
					S[i][j] += Integer.parseInt(st.nextToken());
					S[j][i] = S[i][j]; 
				}
			}
			
			ans = Integer.MAX_VALUE;
			
			//np
			select = new int[N];
			int cnt=0;
			while(++cnt<=N/2) select[N-cnt] = 1; //뒤에서 N/2개만큼 1로 채운다
			
			do {
				//조합 나누기
				int[] A = new int[N/2];
				int[] B = new int[N/2];
				
				int ax=0;

				int bx=0;
				for(int i=0; i<N; i++) { 
					if(select[i] == 1) A[ax++] = i;
					else B[bx++] = i;
				}
				
				//맛 합 더하기
				int sumA = 0;
				for(int i=0; i<N/2; i++) {
					for(int j=i+1; j<N/2; j++) {
						sumA += S[A[i]][A[j]];
					}
				}
				
				int sumB = 0;
				for(int i=0; i<N/2; i++) {
					for(int j=i+1; j<N/2; j++) {
						sumB += S[B[i]][B[j]];
					}
				}
				
				ans = Math.min(ans, Math.abs(sumA-sumB));
				
			}while(np()); //다음 조합 찾기
			
			
			System.out.println("#"+t+" "+ans);
		}
		

	}
	static boolean np() {
		//교환위치 찾기
		int i=N-1;
		while(i>0 && select[i-1]>=select[i]) --i;
		
		if(i == 0) return false; //더이상 다음 조합 X
		
		//교환위치에 교환할 값 찾기
		int j=N-1;
		while(select[i-1] >= select[j] ) --j;
		
		//교환위치와 교환할 값 교환
		swap(i-1, j);
		
		//꼭대기부터 맨 뒤까지 만들수있는 가장 작은 순열 생성
		int k = N-1;
		while(i<k) {
			swap(i++, k--);
		}
		
		return true;
	}
	static void swap(int i, int j) {
		int temp = select[i];
		select[i] = select[j];
		select[j] = temp;
	}

}
