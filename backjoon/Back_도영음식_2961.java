package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//부분집합 바이너리 카운팅으로 해결
public class Back_도영음식_2961 {
	
	static int[][] flavor; //신맛, 쓴맛
	static int N;
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		flavor = new int[N][2];
		
		//입력받기
		for(int n=0; n<N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			flavor[n][0] = Integer.parseInt(st.nextToken());
			flavor[n][1] = Integer.parseInt(st.nextToken());
		}
		
		generateSubset();
		System.out.println(ans);	
	}
	
	static void generateSubset() {
		int caseCount = 1<<N; 
		for(int flag = 1; flag<caseCount; flag++) {
			//flag : 원소들의 선택상태 비트열
			int sour=1, bitter=0; //신맛은 곱, 쓴맛은 합
			for(int i=0; i<N; i++) {
				if((flag & 1<<i) != 0) {
					sour *= flavor[i][0];
					bitter += flavor[i][1];
				}
			}
			ans = Math.min(ans, Math.abs(sour-bitter));
		}
	}
}
