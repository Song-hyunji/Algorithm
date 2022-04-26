package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back_주사위윷놀이_17825 {

	static int[][] map;
	static int dice[], select[];
	static int ans; //ans:정답 점수, dir:방향, loc:위치
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		map = new int[5][];
		map[0] = new int[] {0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40};
		map[1] = new int[] {13,16,19}; //10에 멈추면 가야할 곳
		map[2] = new int[] {22,24}; //20에 멈추면 가야할 곳
		map[3] = new int[] {28,27,26}; //30에 멈추면 가야할 곳
		map[4] = new int[] {25,30,35,40}; //마지막 라인
		
		dice = new int[10]; //원본 주사위
		select = new int[10]; //순열로 선택된 주사위
		for(int i=0; i<10; i++) dice[i] = Integer.parseInt(st.nextToken());
		
		//dir 0 : 테두리로 이동, dir 1 : 10에서 방향전환, dir 2 : 20에서 방향전환, dir 3 : 30에서 전환
		int where[][] =  new int[4][2]; //0~3:말 번호 [0]dir, [1]위치 
		
		cal(0, where, 0);
		
		System.out.println(ans);
	}
	
	static void cal(int cnt, int[][] where, int score) { //점수 계산
		if(cnt == 10) {
			ans = Math.max(ans, score);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(where[i][0] == 10) continue; //도착한 말이면 넘김
			int[][] newWhere = copyArr(where);
			int newScore = score;
			
			//10, 20, 30에 도착해있으면 방향 바꿔주기
			if(newWhere[i][0] == 0 && newWhere[i][1] != 0 && newWhere[i][1] % 5 == 0 && newWhere[i][1] / 5 <= 3) {
				newWhere[i][0] = newWhere[i][1] / 5;
				newWhere[i][1] = -1;
			}
			
			newWhere[i][1] += dice[cnt]; //현재 방향에서 칸 이동
			if(newWhere[i][0] == 4 && newWhere[i][1] >= map[newWhere[i][0]].length) { //끝에 도달했으면
				newWhere[i][0] = 10;
			}
			
			//1,2,3 방향에서 4 방향으로 가야할 때
			if(newWhere[i][0] >=1 && newWhere[i][0] <= 3 && newWhere[i][1] >= map[newWhere[i][0]].length) {
				newWhere[i][1] -= map[newWhere[i][0]].length;
				newWhere[i][0] = 4;
			}
			
			if(newWhere[i][0] == 0 && newWhere[i][1] == map[newWhere[i][0]].length - 1) { //테두리로 마지막 40에 도달했을 때 방향 바꿔주기
				newWhere[i][0] = 4;
				newWhere[i][1] = map[newWhere[i][0]].length - 1;
			}
			
			if(newWhere[i][0] != 10 && newWhere[i][1] >= map[newWhere[i][0]].length) newWhere[i][0] = 10; //끝에 도달했으면
			if(dup(i, newWhere[i][0], newWhere[i][1], newWhere)) continue; //이동한 곳에 다른 말이 있는지 확인
			
			if(newWhere[i][0] != 10) newScore += map[newWhere[i][0]][newWhere[i][1]]; //도착한 칸의 점수 더해주기			
			
			
			cal(cnt+1, newWhere, newScore);
			
		}
	}
	
	static boolean dup(int w, int dir, int loc, int[][] where) { //말이 이동을 마치는 칸에 다른 말이 있으면 그 말은 고를 수 없다
		for(int i=0; i<4; i++) {
			if(i == w || where[i][0] == 10) continue;
			if(where[i][0] == dir && where[i][1] == loc) return true;
			if(dir>=1 && dir <=3 && loc == map[dir].length && where[i][0] == 4 && where[i][1] == 0 ) return true; //중간인 25에서 겹쳤을 때
		}
		return false;
	}
	
	static int[][] copyArr(int [][] where){
		int[][] newArr = new int[4][2];
		for(int i=0; i<4; i++) {
			for(int j=0; j<2; j++) {
				newArr[i][j] = where[i][j];
			}
		}
		return newArr;
	}

}
