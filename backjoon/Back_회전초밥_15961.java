package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Back_회전초밥_15961 {

	static int N, d, k, c; //접시수, 초밥 가짓수, 연속 접시 수, 쿠폰번호
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //전체 접시 수
		d = Integer.parseInt(st.nextToken()); //초밥 가짓수
		k = Integer.parseInt(st.nextToken()); //연속 접시 수
		c = Integer.parseInt(st.nextToken()); //쿠폰 번호
		
		int[] dish = new int[N];
		int[] sushi = new int[d+1]; //초밥 종류별로 몇개 있는지 저장

		//초밥 번호 입력 받기
		for(int n=0; n<N; n++) {
			dish[n] = Integer.parseInt(br.readLine());
		}
		
		ArrayList<Integer> arr = new ArrayList<>(); //연속된 초밥들 담기(투포인터 개념 적용해서)
		HashSet<Integer> set = new HashSet<>(); //처음에 담긴 초밥 종류가 몇개인지 확인하기 위함
		
		for(int i=0; i<k; i++) {
			arr.add(dish[i]);
			set.add(dish[i]);
			sushi[dish[i]]++;
		}
		
		int ans = set.size(); //정답 찾기
		int count = ans; //초밥 종류 수 계산
		
		//앞에꺼 빼고, 뒤에꺼 넣고
		for(int i=1; i<N-k+1; i++) {
			if(count >= ans) {
				ans = count;
				if(sushi[c] == 0) ans++; //쿠폰 초밥이 포함안됐으면 정답+1
			}
			
			//앞에꺼 하나 빼고, 그게 중복됐던 것인지 확인. 중복됐던거면 가짓수 그대로 두고, 중복 아니었으면 가짓수-1
			int pollNum = arr.remove(0);
			if(sushi[pollNum]-- == 1) count--;

			//배열에서 하나 넣기. 넣으려는게 현재 없는 초밥이면 count+1, sushi개수+1
			int addNum = dish[i+k-1];
			if(sushi[addNum]++ == 0) count++;
			arr.add(addNum);
		}
		
		//앞에꺼 빼고, 뒤에꺼 넣는데 한바퀴 돈거임. 
		for(int i=N-k+1, j=0; i<N; i++, j++) {
			if(count >= ans) {
				ans = count;
				if(sushi[c] == 0) ans++; //쿠폰 초밥이 포함안됐으면 정답+1
			}
			
			//앞에꺼 하나 빼고, 그게 중복됐던 것인지 확인. 중복됐던거면 가짓수 그대로 두고, 중복 아니었으면 가짓수-1
			int pollNum = arr.remove(0);
			if(sushi[pollNum]-- == 1) count--;
			//배열에서 하나 넣기
			int addNum = dish[j];
			if(sushi[addNum]++ == 0) count++;
			arr.add(addNum);
		}
		
		if(count >= ans) {
			ans = count;
			if(sushi[c] == 0) ans++; //쿠폰 초밥이 포함안됐으면 정답+1
		}
		
		System.out.println(ans);
	}

}
