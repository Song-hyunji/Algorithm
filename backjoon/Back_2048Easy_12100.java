package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back_2048Easy_12100 {
//https://www.acmicpc.net/problem/12100
//백준 12100 - 2048(Easy)
	
	static int N;
	static int ans;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		//입력받기
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = maxNumber(map); //현재 배열의 최대값 저장해둠
		
		//최대 5번 이동
		dfs(0, map);
		System.out.println(ans);
		
		
	}
	static void dfs(int cnt, int[][] map) { //dfs
		
		if(cnt == 5) {
			ans = Math.max(ans, maxNumber(map));
			return;
		}
		
		for(int d=0; d<4; d++) {
			int[][] newMap = copyMap(map); //배열 복사해두기
			move(d, newMap); //방향에 따라 블록 밀기
			dfs(cnt+1, newMap);
		}
	}
	
	static void move(int dir, int[][] map) { //상 우 하 좌
		switch(dir) {
			case 0: //위로 밀기
				for(int j=0; j<N; j++) { //한 열씩 확인
					int tgtIdx=0; //위쪽부터 숫자 채우기 위함
					int tempNum=0; //임시저장 숫자
					
					for(int i=0; i<N; i++) { //위쪽부터 숫자 확인
						if(map[i][j] == 0) continue; //0이면 넘김
						if(tempNum == 0) { //만난 곳에 숫자가 있는데, 임시저장이 안되어 있으면 일단 임시저장
							tempNum = map[i][j];
							continue;
						}
						
						if(tempNum == map[i][j]) { //임시저장된거랑 만난 숫자랑 같으면 합쳐줌
							map[tgtIdx][j] = tempNum*2;
							tgtIdx++;
							tempNum = 0; //임시저장된건 합쳐졌기 때문에 0으로 갱신
						}else { //임시저장된거랑 만난 숫자랑 다르면, 임시저장된거 저장해주고 만난숫자를 임시저장
							map[tgtIdx][j] = tempNum;
							tgtIdx++;
							tempNum = map[i][j];
						}
					}
					if(tempNum != 0) map[tgtIdx++][j] = tempNum; //남아있는 임시저장 숫자 처리해줌
					while(tgtIdx<N) map[tgtIdx++][j] = 0;
				}
				break;
				
			case 1: //우로 밀기
				for(int i=0; i<N; i++) { //한 행씩 확인
					int tgtIdx=N-1; //오른쪽부터 숫자 채우기 위함
					int tempNum=0; //임시저장 숫자
					
					for(int j=N-1; j>=0; j--) { //오른쪽부터 숫자 확인
						if(map[i][j] == 0) continue; //0이면 넘김
						if(tempNum == 0) { //만난 곳에 숫자가 있는데, 임시저장이 안되어 있으면 일단 임시저장
							tempNum = map[i][j];
							continue;
						}
						if(tempNum == map[i][j]) { //임시저장된거랑 만난 숫자랑 같으면 합쳐줌
							map[i][tgtIdx] = tempNum*2;
							tgtIdx--;
							tempNum = 0; //임시저장된건 합쳐졌기 때문에 0으로 갱신
						}else { //임시저장된거랑 만난 숫자랑 다르면, 임시저장된거 저장해주고 만난숫자를 임시저장
							map[i][tgtIdx] = tempNum;
							tgtIdx--;
							tempNum = map[i][j];
						}
					}
					if(tempNum != 0) map[i][tgtIdx--] = tempNum; //남아있는 임시저장 숫자 처리해줌
					while(tgtIdx>=0) map[i][tgtIdx--] = 0;
				}
				break;
				
			case 2: //하로 밀기
				for(int j=0; j<N; j++) { //한 열씩 확인
					int tgtIdx=N-1; //아래쪽부터 숫자 채우기 위함
					int tempNum=0; //임시저장 숫자
					for(int i=N-1; i>=0; i--) { //아래쪽부터 숫자 확인
						if(map[i][j] == 0) continue; //0이면 넘김
						if(tempNum == 0) { //만난 곳에 숫자가 있는데, 임시저장이 안되어 있으면 일단 임시저장
							tempNum = map[i][j];
							continue;
						}
						if(tempNum == map[i][j]) { //임시저장된거랑 만난 숫자랑 같으면 합쳐줌
							map[tgtIdx][j] = tempNum*2;
							tgtIdx--;
							tempNum = 0; //임시저장된건 합쳐졌기 때문에 0으로 갱신
						}else { //임시저장된거랑 만난 숫자랑 다르면, 임시저장된거 저장해주고 만난숫자를 임시저장
							map[tgtIdx][j] = tempNum;
							tgtIdx--;
							tempNum = map[i][j];
						}
					}
					if(tempNum != 0) map[tgtIdx--][j] = tempNum; //남아있는 임시저장 숫자 처리해줌
					while(tgtIdx>=0) map[tgtIdx--][j] = 0;
				}
				break;
				
			case 3: //좌로 밀기
				for(int i=0; i<N; i++) { //한 행씩 확인
					int tgtIdx=0; //왼쪽부터 숫자 채우기 위함
					int tempNum=0; //임시저장 숫자
					
					for(int j=0; j<N; j++) { //왼쪽부터 숫자 확인
						if(map[i][j] == 0) continue; //0이면 넘김
						if(tempNum == 0) { //만난 곳에 숫자가 있는데, 임시저장이 안되어 있으면 일단 임시저장
							tempNum = map[i][j];
							continue;
						}
						if(tempNum == map[i][j]) { //임시저장된거랑 만난 숫자랑 같으면 합쳐줌
							map[i][tgtIdx] = tempNum*2;
							tgtIdx++;
							tempNum = 0; //임시저장된건 합쳐졌기 때문에 0으로 갱신
						}else { //임시저장된거랑 만난 숫자랑 다르면, 임시저장된거 저장해주고 만난숫자를 임시저장
							map[i][tgtIdx] = tempNum;
							tgtIdx++;
							tempNum = map[i][j];
						}
					}
					if(tempNum != 0) map[i][tgtIdx++] = tempNum; //남아있는 임시저장 숫자 처리해줌
					while(tgtIdx<N) map[i][tgtIdx++] = 0;
				}
				break;
		}
	}
	
	static int maxNumber(int[][] map) { //배열 숫자 중 가장 큰 숫자 찾기
		int max=0;
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				max = Math.max(max, map[i][j]);
			}
		}
		return max;
	}
	
	static int[][] copyMap(int[][] origin){ //배열 복사
		int[][] copy = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				copy[i][j] = origin[i][j];
			}
		}
		return copy;
	}

}
