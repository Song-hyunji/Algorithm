package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Back_두용액_2470 {
	
	static Integer[] acid;
	static Integer[] alkal;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		//산성용액, 알칼리용액 배열을 따로 만들어줌
		acid = new Integer[N]; //양수
		alkal = new Integer[N]; //음수
		
		//배열 초기화
		Arrays.fill(acid, 0);
		Arrays.fill(alkal, 0);
		
		//배열에 숫자 입력받기
		int acidNum = 0;
		int alkalNum = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num>0) {
				acid[acidNum++] = num;
			}else {
				alkal[alkalNum++] = num;
			}
		}
		
		//정렬
		Arrays.sort(acid, Collections.reverseOrder()); //내림차순으로 +의 절댓값이 큰것부터 정렬
		Arrays.sort(alkal); //오름차순으로 -의 절댓값이 큰것부터 정렬
		
		//sum 초기화
		int sum = Integer.MAX_VALUE;
		int answer1 = 0;
		int answer2 = 0;
		
		//산성 또는 알칼리로만의 합이 가장 작을 수 있는 경우
		
		//산성 개수가 2개 이상일 때 산성 중 가장 절대값 작은거 2개 더함
		if(acidNum >=2) {
			answer1 = acid[acidNum-1];
			answer2 = acid[acidNum-2];
			sum = answer1 + answer2; //제일 절댓값 작은걸로 sum 초기화 
		}
		
		//알칼리 개수가 2개 이상일 때 알칼리 중 가장 절대값 작은거 2개 더함
		if(alkalNum >=2) {
			int tempAnswer1 = alkal[alkalNum-2];
			int tempAnswer2 = alkal[alkalNum-1];
			if(sum > Math.abs(tempAnswer1+tempAnswer2)) {
				sum = tempAnswer1 + tempAnswer2;
				answer1 = tempAnswer1;
				answer2 = tempAnswer2;
			}
		}
		
		//산+알칼리 중 절대값 작은거 탐색
		int acidPointer = 0;
		int alkalPointer = 0;
		
		while(true) {
			if( acidPointer >=acidNum || alkalPointer >= alkalNum ) break; //산성포인터, 알칼리포인터가 더 이상 숫자를 가리키지 않으면 멈춤
			
			int tempSum = acid[acidPointer] + alkal[alkalPointer];
			
			if(Math.abs(tempSum) < Math.abs(sum)) { //절댓값 더 작으면 갱신
				sum = tempSum;
				answer1 = alkal[alkalPointer];
				answer2 = acid[acidPointer];
				if(sum == 0) break;
			}
			if(tempSum <0) alkalPointer++; //합이 음수면 알칼리포인터 +1 (음수를 덜 더해줘야 하니까) 
			else acidPointer++; //합이 양수면 산포인터 +1
		}
		
		System.out.println(answer1 + " " + answer2);
	}

}
