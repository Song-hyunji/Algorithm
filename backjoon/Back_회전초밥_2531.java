package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Back_회전초밥_2531 {

	static int N, d, k, c; //접시수, 초밥 가짓수, 연속 접시 수, 쿠폰번호
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //전체 접시 수
		d = Integer.parseInt(st.nextToken()); //초밥 가짓수
		k = Integer.parseInt(st.nextToken()); //연속 접시 수
		c = Integer.parseInt(st.nextToken()); //쿠폰 번호
		
		//초밥 번호 입력 받기
		int[] dish = new int[N];
		int[] dp = new int[N]; //dp[i] : i~i+n-1까지의 초밥 가짓수
		int[] cupon = new int[N]; 
		for(int n=0; n<N; n++) {
			dish[n] = Integer.parseInt(br.readLine());
		}
		
		ArrayList<Integer> arr = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();
		
		for(int i=0; i<k; i++) {
			arr.add(dish[i]);
			set.add(dish[i]);
		}
		
		dp[0] = set.size();
		if(!arr.contains(c)) cupon[0] = 1;
		
		//앞에꺼 빼고, 뒤에꺼 넣고
		for(int i=1; i<N-k+1; i++) {
			dp[i] = dp[i-1];
			//앞에꺼 하나 빼고, 그게 중복됐던 것인지 확인. 중복됐던거면 가짓수 그대로 두고, 중복 아니었으면 가짓수-1
			int pollNum = arr.remove(0);
			if(!arr.contains(pollNum)) dp[i]--;
			//배열에서 하나 넣기
			int addNum = dish[i+k-1];
			if(!arr.contains(addNum)) dp[i]++;
			arr.add(addNum);
			//쿠폰 초밥이 포함됐는지 확인
			if(!arr.contains(c)) cupon[i] = 1;
		}
		
		//앞에꺼 빼고, 뒤에꺼 넣는데 한바퀴 돈거임. 
		for(int i=N-k+1, j=0; i<N; i++, j++) {
			dp[i] = dp[i-1];
			//앞에꺼 하나 빼고, 그게 중복됐던 것인지 확인. 중복됐던거면 가짓수 그대로 두고, 중복 아니었으면 가짓수-1
			int pollNum = arr.remove(0);
			if(!arr.contains(pollNum)) dp[i]--;
			//배열에서 하나 넣기
			int addNum = dish[j];
			if(!arr.contains(addNum)) dp[i]++;
			arr.add(addNum);
			//쿠폰 초밥이 포함됐는지 확인
			if(!arr.contains(c)) cupon[i] = 1;
		}
		
		int ans = 0;
		for(int i=0; i<N; i++) {
			ans = Math.max(ans, dp[i]+cupon[i]);
		}
		System.out.println(ans);
	}

}
