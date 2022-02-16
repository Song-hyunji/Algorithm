package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back_Z_1074 {

	static int N, r, c; //r행 c열을 몇번째 방문했는지
	static int ans=0; //정답
	static int done=0; //정답 찾았는지 여부
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		//분할정복
		divideConquer(0, 0, (int)Math.pow(2, N)); 
		System.out.println(ans);
	}
	
	static void divideConquer(int startX, int startY, int num) { //시작행, 시작열, 길이
		if(done == 1) return; //답을 찾았으면 리턴
		if(r<startX || r>=startX+num || c<startY || c>=startY+num) { //찾으려는 행열이 해당 분할범위에 없으면 ans에 갯수 더해주고 리턴
			ans+=num*num;
			return;
		}
		if(r==startX && c==startY) { //정답을 찾았을 때
			//0부터 시작하기 때문에 찾았을 때 +1 안해줌
			done=1;
			return;
		}
		
		//범위 안에 찾으려는 행열이 있으면 분할
		divideConquer(startX, startY, num/2);
		divideConquer(startX, startY + num/2, num/2);
		divideConquer(startX + num/2, startY, num/2);
		divideConquer(startX + num/2, startY + num/2, num/2);
		
	}
}
