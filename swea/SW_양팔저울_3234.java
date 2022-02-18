package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_양팔저울_3234 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			//입력받기
			int N = Integer.parseInt(br.readLine());
			Integer[] weight = new Integer[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
			
			//무조건 왼쪽>=오른쪽
			
			int ans = permutation(weight,0,0,0,0,0);
			System.out.println("#"+t+" "+ans);
			
		}
	}
	
	//순열으로 순서대로 왼쪽이나 오른쪽에 인자 놓기
	//weight:입력배열, cnt:선택개수, flag:select됐는지 확인, left:왼쪽 추 합, right:오른쪽 추 합, ans:경우의 수(정답)
	static int permutation(Integer[] weight, int cnt, int flag, int left, int right, int ans) {
		if(cnt == weight.length) {
			return ans+1;
		}
		
		for(int i=0; i<weight.length; i++) {
			if((flag & 1<<i) !=0) continue; //사용중인지 확인
			
			ans = permutation(weight, cnt+1, flag | 1<<i, left+weight[i], right, ans);
		
			if(left >= right+weight[i]) {
				ans = Math.max(ans, permutation(weight, cnt+1, flag | 1<<i, left, right+weight[i],ans));
			}
		}
		return ans;
	}
}
