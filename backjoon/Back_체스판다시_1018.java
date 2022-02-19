package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back_체스판다시_1018 {

	public static void main(String[] args) throws IOException{
		
		//입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		StringTokenizer st = new StringTokenizer(s);
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] square = new char[N][M];
		for(int i=0;i<N;i++) {
			square[i] = br.readLine().toCharArray();
		}
		
		//정답 구하기
		int ans = 64;
		
		for(int i = 0; i <= N - 8; i++) {
			for(int j = 0; j <= M-8; j++) {
				//해당i,j부터 8*8 탐색
				int paint=0; //칠해야할 칸 수 -> 짝수 W, 홀수 B로 고정하고 계산 
				for(int n=0;n<8;n++) {
					for(int m=0;m<8;m++) {
						if((n+m)%2==0) {
							if(square[i+n][j+m]!='W') paint++;
						}else {
							if(square[i+n][j+m]!='B') paint++;
						}
					}
				}
				paint = paint<32 ? paint : 64-paint; //칠해야할 칸 수는 paint 또는 64-paint
				ans = ans<paint ? ans : paint;
				
			}
		}
		System.out.println(ans);
		
		br.close();
	}

}
