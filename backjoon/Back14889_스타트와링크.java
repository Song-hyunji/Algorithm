package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back14889_스타트와링크 {

	static int N; //사람 수
	static int[][] S; //능력치 배열
	static int[] start, link; //스타트팀, 링크팀 배열
	static int ans=100*20; //정답 초기화
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		start = new int[N/2];
		link = new int[N/2];
		
		//입력받기 s[i][j] = s[i][j]+s[j][i]
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				S[i][j]+=Integer.parseInt(st.nextToken());
				S[j][i]=S[i][j];
			}
		}
		
		//0~N-1 숫자중 N/2개 start 배열에 담기
		Combination(0,0);
		System.out.println(ans);
	}
	
	static void Combination(int cnt, int s) { //start 위주로 조합 생성
		if(cnt == N/2) {
			Link(); //start에 없는 인자 link에 담아주기
			ans = Math.min(ans, Math.abs(ability(start)-ability(link))); //능력치 차이 구해서 최솟값 구하기
			return;
		}
		
		for(int i=s; i<N; i++) {
			start[cnt] = i; 
			Combination(cnt+1, i+1);
		}
		
	}
	static void Link() { //start 배열에 없는 인자 link 배열에 넣기 
		int startIdx=0;
		int linkIdx=0;
		int num=0;
		while(num<N) {
			if(startIdx<N/2 && start[startIdx] == num) {
				startIdx++; 
			}else if(linkIdx<N/2){
				link[linkIdx] = num;
				linkIdx++;
			}
			num++;
		}
	}
	
	static int ability(int[] array) { //능력치 구하기
		int sum=0;
		for(int i=0; i<N/2; i++) {
			for(int j=i+1; j<N/2; j++) {
				sum+=S[array[i]][array[j]];
			}
		}
		return sum;
	}

}
