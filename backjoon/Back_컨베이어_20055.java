import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back_컨베이어_20055 {

	static int N, K;
	static int[] con; //컨베이어 벨트 내구도 
	static boolean[] robot; //로봇 유무
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //컨베이어 벨트 길이
		K = Integer.parseInt(st.nextToken()); //내구도가 0인 칸의 개수
		
		con = new int[2*N]; // 0 ~ N-1
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<2*N; i++) {
			con[i] = Integer.parseInt(st.nextToken());
		}
		
		robot = new boolean[N]; // 0~N-1 로봇이 있으면 true
		
		int stage = 0; //수행 횟수
		while(K > 0) {
			stage++;
			course1(); //1번째 단계 : 벨트 회전
			course2(); //2번째 단계 : 로봇 이동
			course3(); //3번째 단계 : 0번째 칸에 로봇 올리기
		}
		
		System.out.println(stage);
	}
	
	//1번째 단계 : 벨트 회전, 로봇 회전
	static void course1() {
		//벨트 회전
		int temp = con[2*N-1];
		for(int i=2*N-1; i>0; i--) {
			con[i] = con[i-1];
		}
		con[0] = temp;
		
		//로봇도 함께 회전
		for(int i=N-1; i>0; i--) {
			robot[i] = robot[i-1];
			if(i == N-1 && robot[i]) robot[i] = false; //내려야하는 위치(N-1)에서 로봇 내리기
		}
		robot[0] = false; //0번째 위치의 로봇은 1번째 위치로 가니까, 0번째 위치는 false로 바꿔줌
	}
	
	//2번째 단계 : 로봇 이동 
	static void course2() {
		for(int i=N-1; i>0; i--) {
			if(robot[i-1] && !robot[i] && con[i] != 0) { //i-1칸에 로봇 있고, i칸에 로봇 없고, i칸의 내구도가 0이 아닐 때 로봇 이동
				robot[i] = true;
				robot[i-1] = false;
				con[i]--; //내구도 감소
				if(con[i] == 0) K--; //로봇 옮긴 후 내구도가 0 되면 K-- 
				if(i == N-1 && robot[i]) robot[i] = false; //로봇 옮긴 후 로봇 내려야하면 내려주기
			}
		}
	}
	
	//3번째 단계 : 0번째 칸의 내구도가 0이 아니면 로봇 올리기
	static void course3() {
		if(con[0] != 0) {
			robot[0] = true;
			con[0]--;
			if(con[0] == 0) K--;
		}
	}
}
