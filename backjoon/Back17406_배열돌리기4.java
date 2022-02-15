package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//Back17406_배열돌리기4
public class Back17406_배열돌리기4 {

	static int N, M, K;
	static int r, c, s;
	static int[][] map; //기존 배열
	static int[][] tempMap; //기존 배열 복사할 temp배열
	static Cal[] cal; //연산자들 저장할 배열
	static Cal[] numbers; //연산자들 순열 경우의 수로 저장할 배열
	static boolean[] isSelected; //순열 따질 때 선택됐는지 확인할 배열
	static int ans; 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		cal = new Cal[K];
		numbers = new Cal[K];
		isSelected = new boolean[K];
		
		//입력받기
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//연산자 저장하기
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			cal[k] = new Cal(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		ans = 100*50;
		//순열 따져서 배열 돌리기
		Permutation(0);
	
		System.out.println(ans);
	}
	
	//순열 경우의 수 따지기
	static void Permutation(int cnt) { 
		if(cnt == K) {
			//tempMap에 map 값 복사
			tempMap = new int[N+1][M+1];
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					tempMap[i][j] = map[i][j];
				}
			}
			
			//회전시키기
			for(int k=0; k<K; k++) {
				rotate(numbers[k].r, numbers[k].c, numbers[k].s);
			}
			
			//답 구하기
			for(int i=1; i<=N; i++) {
				int sum = 0;
				for(int j=1; j<=M; j++) {
					sum += tempMap[i][j];
				}
				ans = Math.min(ans, sum);
			}
			return;
		}
		
		//순열 구하기
		for(int i=0; i<K; i++) {
			if(isSelected[i] == true) continue;
			numbers[cnt] = cal[i];
			isSelected[i] = true;
			Permutation(cnt+1);
			isSelected[i] = false;
		}
	}
	
	//회전
	static void rotate(int r, int c, int s) {
		int sy = r-s, sx = c-s; //시작 행, 시작 열
		int ey = r+s, ex = c+s; //끝 행, 끝 열
		
		while(true) {
			if(ey-sy<1 || ex-sx<1) break;
			int temp = tempMap[sy][ex]; //우상 인자 백업
			
			//top 
			for(int i=ex; i>sx; i--) {
				tempMap[sy][i] = tempMap[sy][i-1];
			}
			
			//Left 
			for(int i=sy; i<ey; i++) {
				tempMap[i][sx] = tempMap[i+1][sx];
			}
			
			//bottom 
			for(int i=sx; i<ex; i++) {
				tempMap[ey][i] = tempMap[ey][i+1];
			}
			
			//Right 
			for(int i=ey; i>sy; i--) {
				tempMap[i][ex] = tempMap[i-1][ex];
			}
	
			tempMap[sy+1][ex] = temp;
			
			sy++; sx++; //좌우상하 모두 한칸씩 밀리게 됨
			ey--; ex--;
		}
	}
}
class Cal{
	int r, c, s;
	Cal(int r, int c, int s){
		this.r = r;
		this.c = c;
		this.s = s;
	}
}
