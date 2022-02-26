package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back_스위치켜고끄기_1244 {

	static int N; //스위치 개수
	static int swch[]; //스위치 상태 저장
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		swch = new int[N+1];
		
		//스위치 초기상태 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			swch[i] = Integer.parseInt(st.nextToken());
		}
		
		int num = Integer.parseInt(br.readLine());
		
		//스위치 변화
		for(int n=1; n<=num; n++) {
			st = new StringTokenizer(br.readLine());
			
			int gender = Integer.parseInt(st.nextToken()); //성별
			int where = Integer.parseInt(st.nextToken()); //스위치 위치
			
			switch(gender) {
			case 1:
				//남학생 : 자기가 받은 수의 배수이면, 그 스위치의 상태를 바꾼다
				for(int i=where; i<=N; i+=where) {
					if(swch[i] == 0) swch[i] = 1;
					else swch[i] = 0;
				}
				
				break;
			case 2:
				//여학생 : 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭인 스위치 찾아서 상태 바꾼다
				if(swch[where] == 0) swch[where] = 1;
				else swch[where] = 0;
				
				for(int i=1; where-i>0 && where+i<=N; i++) {
					if(swch[where+i] == swch[where-i]) {
						if(swch[where-i] == 0) swch[where-i] = 1;
						else swch[where-i] = 0;
						
						if(swch[where+i] == 0) swch[where+i] = 1;
						else swch[where+i] = 0;
					}
					else break;
				}
				
				break;
			}
		}
		
		//스위치의 상태를 1번 스위치에서 시작하여 마지막 스위치까지 한 줄에 20개씩 출력
		for(int i=1; i<=N; i++) {
			if(i%20 == 0) System.out.println(swch[i]+" ");
			else System.out.print(swch[i]+" ");
		}
	}
}
