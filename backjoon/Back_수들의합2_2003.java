package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//투포인터 문제인데 투포인터 사용안했음
public class Back_수들의합2_2003 {

	static int N, M; //숫자 개수, 합  
	static int[] numbers;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//숫자 입력 받기
		numbers = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		int count = 0; //정답 카운트
		
		for(int i=0; i<N; i++) {
			int temp = 0; //부분 합
			
			for(int j=i; j<N; j++) { //i부터 차례대로 더하기
				temp+=numbers[j]; 
				
				if(temp == M) {
					count++; 
					break;
				}else if(temp > M) break;
			}
		}
		System.out.println(count);
		
	}

}
