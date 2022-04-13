package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_특이한자석_4013 {
	//붙어 있는 자석은 서로 붙어 있는 날의 자성이 다를 경우에만 반대 방향으로 1 칸 회전
	//N이 0, S가 1
	
	static int[][] wheel; //톱니바퀴
	static int[] topWheel; //자석 꼭대기에 몇번째 위치가 있는지 저장
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int K = Integer.parseInt(br.readLine());
			
			wheel = new int[4][8];
			topWheel = new int[4];
			for(int i=0; i<4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<8; j++) {
					wheel[i][j] = Integer.parseInt(st.nextToken());
				}
				topWheel[i] = 0; //자석 꼭대기에 몇번째 위치가 있는지 저장해주기
			}
			
			for(int k=1; k<=K; k++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken()); //자석번호
				int dir = Integer.parseInt(st.nextToken()); //회전방향. 1은 시계, -1은 반시계
				change(num-1, dir);
			}
			
			int ans = 0;
			for(int i=0; i<4; i++) {
				if(wheel[i][topWheel[i]] == 1) ans += Math.pow(2, i);
			}
			System.out.println("#"+t+" "+ans);
		}

	}
	static void change(int num, int dir) {
		int[] rotateArr = new int[4];
		rotateArr[num] = dir; //회전할 방향 저장해두기
		
		int sideDir = dir;
		for(int i=num; i>0; i--) { //현재꺼랑 왼쪽꺼 비교
			sideDir *= -1;
			if(wheel[i][(topWheel[i]+6) % 8] != wheel[i-1][(topWheel[i-1]+2)%8]) {
				rotateArr[i-1] = sideDir;
			}else break;
		}
		
		sideDir = dir;
		for(int i=num; i<3; i++) { //현재꺼랑 오른쪽꺼 비교
			sideDir *= -1;
			if(wheel[i][(topWheel[i]+2) % 8] != wheel[i+1][(topWheel[i+1]+6)%8]) {
				rotateArr[i+1] = sideDir;
			}else break;
		}
		
		//저장한 방향대로 회전하기
		for(int i=0; i<4; i++) {
			rotate(i, rotateArr[i]);
		}
	}
	static void rotate(int num, int dir) {
		if(dir == 1) {
			topWheel[num] = (topWheel[num]+7) % 8;
		}else if(dir == -1){
			topWheel[num] = (topWheel[num]+1) % 8;
		}
	}

}
