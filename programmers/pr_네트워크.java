package pr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
3
1 1 0
1 1 1
0 1 1
 */

//프로그래머스 네트워크(level3)
public class pr_네트워크 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] computers = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				computers[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(solution(N, computers));

	}
	
	static int solution(int n, int[][] computers) { //n:컴퓨터개수, computers:연결정보가 담긴 2차원배열
        int answer = 0; //네트워크 개수
        int[] select = new int[n]; //선택됐는지 확인하는 배열
        Arrays.fill(select, -1); //배열 -1로 초기화
        
        for(int i=0; i<n; i++) {
			if(select[i] != -1) continue; //이미 선택되었으면 넘김
			
			select[i] = i; //선택됨
			answer++; 
			
			for(int j=0; j<n; j++) { //i와 연결된 모든 것들 탐색
				if(i == j) continue; //본인 네트워크를 가리키면 넘김
				if(computers[i][j] == 1) { //연결되어 있으면
					select[j] = i; //i와 연결되어있다고 알려줌(개수뿐이라서 그냥 true,false로 해도 됨)
					dfs(n, j, computers, select); //j와 연결되어있는 것들 찾으러감
				}
			}
		}
        return answer;
    }
	
	static void dfs(int n, int k, int[][] computers, int[] select) { //n과 연결되어있는 것들 확인
		for(int i=0; i<n; i++) {
			if(i!=k && select[i] == -1 && computers[k][i] == 1) { //본인을 가르키지않고, 선택된적 없고, 연결되어 있으면
				select[i] = select[k]; 
				dfs(n, i, computers, select); 
			}
		}
	}

}
