package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back_야구공_17281 {
//3아웃 시 이닝 변경
	
	static int N;
	static int[][] player;
	static int[] numbers; //순열 저장되는 배열(0~7)
	static int[] newPlayer; //player의 순서가 저장됨
	static int score;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		player = new int[N][9];
		numbers = new int[8];
		newPlayer = new int[9];
		
		//입력 받기
		for(int n=0; n<N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<9; i++) {
				player[n][i] = Integer.parseInt(st.nextToken());
			}
		}
		score = 0;
		permutation(0,0);
		System.out.println(score);

	}
	
	//순열
	public static void permutation(int cnt, int flag) { //cnt : 직전까지 뽑은 수 개수, flag : 뽑힌 수들에 대한 플래그비트열
		
		//기본파트
		if(cnt == 8) {
			newPlayer[3] = 0;
			for(int i=0; i<3; i++) {
				newPlayer[i] = numbers[i]+1;
			}
			for(int i=4; i<9; i++) {
				newPlayer[i] = numbers[i-1]+1;
			}
			//System.out.println(Arrays.toString(newPlayer));
			score();
			return;
		}
		
		//입력받은 모든 수를 현재 자리에 넣어보기		
		for (int i = 0; i < 8; i++) {
			//기존자리의 수들과 중복되는지 체크
			if((flag & 1<<i) != 0 ) continue; //i가 사용중인지 확인
			
			numbers[cnt] = i;
			
			//다음 수 뽑으러 가기
			permutation(cnt+1, flag | 1<<i);
		}
		
	}
	
	//점수계산
	public static void score() {
		int nowScore = 0;
		int playerNum = -1;
		
		for(int i=0; i<N; i++) { //N 이닝
			int out = 0;
			int[] ground = new int[3];
			
			while(out < 3) {
				playerNum += 1;
				playerNum = playerNum == 9 ? 0 : playerNum;
				
				int turn = newPlayer[playerNum]; //몇번 선수가 치는지
				int playerScore = player[i][turn]; //그 선수가 치는 점수
				
				if(playerScore == 0) { //아웃
					out++;
					continue;
				}
				
				if(playerScore == 4) {
					for(int g=0; g<3; g++) {
						if(ground[g] != 0) {
							nowScore++;
							ground[g] = 0;
						}
					}
					nowScore++;
					continue;
				}
				
				//점수 얻기
				for(int g=0; g<playerScore; g++) {
					if(ground[g] != 0) nowScore++;
				}
				
				//옮기기
				for(int g=0; g<3; g++) {
					if(g < 3-playerScore) ground[g] = ground[g+playerScore];
					else ground[g] = 0;
				}
				
				//친 선수 뛰기
				if(playerScore != 4) ground[3-playerScore] = playerScore;
				else nowScore++;
				
				
				
			}
		}
		score = score > nowScore ? score : nowScore;
		
	}
	

}
