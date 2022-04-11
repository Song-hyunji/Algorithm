package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Back_주사위_1041 {

	static int N;
	static int[] dice;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		//주사위 숫자 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		dice = new int[7];
		for(int i=1; i<=6; i++) 
			dice[i] = Integer.parseInt(st.nextToken());
		
		//N이 1이면 오름차순 정렬 후, 제일 큰거 뺀 5면 합 더하기
		if(N == 1) {
			Arrays.sort(dice);
			System.out.println(dice[1]+dice[2]+dice[3]+dice[4]+dice[5]);
		}else {
			long ans=0;
			ans += side3()*4; //세 면 : 맨 위에서 모서리 4개 
			ans += side2()*((N-2)*8 + 4); //두 면 : (한 변에서 모서리 2개 뺀거)*8(맨위, 사이드) + 맨 밑의 모서리 4개
			ans += side1()*((long)(N-2)*(N-2)*5 + (N-2)*4); //한 면 : (한 면에서 테두리 뺀 거)*5 + 맨 밑의 테두리에서 모서리 제외
			System.out.println(ans);
		}
		

	}
	
	//면이 3개일 때
	static long side3() {
		long minside = Math.min(dice[1], dice[6]);
		long min = minside+dice[2]+dice[3];
		min = Math.min(min, minside+dice[2]+dice[4]);
		min = Math.min(min, minside+dice[3]+dice[5]);
		min = Math.min(min, minside+dice[4]+dice[5]);
		return min;
	}
	
	//면이 2개일 때
	static long side2() {
		long min = dice[1]+dice[2];
		for(int i=2; i<=5; i++) {
			min = Math.min(min, dice[1]+dice[i]);
			min = Math.min(min, dice[6]+dice[i]);
		}
		min = Math.min(min, dice[2]+dice[3]);
		min = Math.min(min, dice[2]+dice[4]);
		min = Math.min(min, dice[3]+dice[5]);
		min = Math.min(min, dice[4]+dice[5]);
		return min;
	}
	
	//면이 하나일 때 -> 가장 작은 수 1개 리턴
	static long side1() {
		long min=dice[1];
		for(int i=2; i<=6; i++)
			min = Math.min(min, dice[i]);
		return min;
	}

}
