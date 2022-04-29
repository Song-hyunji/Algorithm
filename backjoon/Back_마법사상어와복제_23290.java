import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/*
1. 모든 물고기 복제 마법 시전 
2. 물고기 이동(상어칸, 물고기 냄새칸으론 이동X), 이동 가능할 때까지 45도 반시계 회전. 이동 불가능하면 이동X
3. 상어가 연속 3칸 이동(상하좌우 가능)=> 물고기 수가 가장 많은 3칸으로. 3칸 중 격자 범위 벗어나면 이동X
      상어가 물고기 먹으면 물고기 냄새 남김(냄새는 2회전 지속됨)
4. 2회전 지속된 물고기 냄새 없어짐
5. 1에서 복제한 물고기 마법 완료. 
 */
public class Back_마법사상어와복제_23290 {
	
	static int M, S; //M 물고기 수, S 마법횟수
	static Cell[][] map, dupMap; //격자
	static int dy[] = {0,-1,-1,-1,0,1,1,1}; //물고기 방향 : 0~7
	static int dx[] = {-1,-1,0,1,1,1,0,-1};
	static int sharkY, sharkX; //상어 위치
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new Cell[5][5]; //(1,1)~(4,4)
		for(int i=1; i<=4; i++) {
			for(int j=1; j<=4; j++) {
				map[i][j] = new Cell(false, 0); 
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); //물고기 수
		S = Integer.parseInt(st.nextToken()); //마법 횟수
		
		//물고기 입력받기
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fy = Integer.parseInt(st.nextToken());
			int fx = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[fy][fx].fish.add(d-1);
		}
		
		//상어 입력받기
		st = new StringTokenizer(br.readLine());
		sharkY = Integer.parseInt(st.nextToken());
		sharkX = Integer.parseInt(st.nextToken());
		map[sharkY][sharkX].shark = true;
		
		while(S-- > 0) {
			//새로 만들 배열 초기화
			dupMap = new Cell[5][5];
			for(int i=1; i<=4; i++) {
				for(int j=1; j<=4; j++) {
					dupMap[i][j] = new Cell(false, 0);
					dupMap[i][j].smell = map[i][j].smell;
				}
			}
			
			fishMove(); //물고기 한 칸 이동
			sharkMove(); //상어 이동, 물고기 잡아먹음 (물고기 냄새 3으로 초기화)
			smellDecrease(); //물고기 냄새 -1
			duplicate(); //물고기 복제마법 완료
			
			map = dupMap;
		}
		
		//격자에 남아있는 물고기 수 구하기
		int ans = 0;
		for(int i=1; i<=4; i++) {
			for(int j=1; j<=4; j++) {
				ans += map[i][j].fish.size();
			}
		}
		System.out.println(ans);
		
	}
	
	//물고기 한 칸 이동
	static void fishMove() {
		for(int i=1; i<=4; i++) {
			for(int j=1; j<=4; j++) {
				
				//map배열 칸에 있는 물고기 하나씩 보며 dupMap에 이동해주기
				for(int f : map[i][j].fish) {
					
					boolean canGo = false; //이동 가능 한지 유무

					//반시계 방향 돌며 이동가능방향 탐색
					for(int k=f+8; k>f; k--) {
						int ny = i + dy[k%8];
						int nx = j + dx[k%8];
						
						//인덱스 체크, 상어유무체크, 물고기 냄새 체크
						if(ny<1 || nx<1 || ny>4 || nx>4 || map[ny][nx].shark || map[ny][nx].smell > 0) continue; 
						dupMap[ny][nx].fish.add(k%8);
						canGo = true;
						break;
					}
					
					if(!canGo) dupMap[i][j].fish.add(f); //움직일 수 없으면 가만히 있기
				}
			}
		}
	}
	
	//상어 이동, 물고기 잡아먹음 (물고기 냄새 3으로 초기화)
	static void sharkMove() {
		int sdy[] = {0,-1,0,1,0}; //상좌하우
		int sdx[] = {0,0,-1,0,1};
		//(방향숫자, 물고기 수) 저장. 물고기 수 내림차순, 숫자 오름차순 정렬
		PriorityQueue<int[]> queue = new PriorityQueue<>((o1,o2)->o1[1]==o2[1] ? o1[0]-o2[0] : o2[1]-o1[1]);
		
		//상어가 이동할 방향 찾기
		for(int d1=1; d1<=4; d1++) {
			
			int ny = sharkY + sdy[d1];
			int nx = sharkX + sdx[d1];
			if(ny<1 || nx<1 || ny>4 || nx>4) continue; //인덱스 체크
			
			int visit = (1 << 17) | (1 << ((ny-1)*4 + nx)); //방문 처리
			int count = dupMap[ny][nx].fish.size(); //물고기 수 저장
			
			for(int d2=1; d2<=4; d2++) {
				int ny2 = ny + sdy[d2];
				int nx2 = nx + sdx[d2];
				if(ny2<1 || nx2<1 || ny2>4 || nx2>4) continue; //인덱스 체크
				
				int count2 = count;
				if((visit & (1 << ((ny2-1)*4 + nx2)) ) == 0) count2 += dupMap[ny2][nx2].fish.size(); //방문 안했던 곳이면 물고기 먹기
				int visit2 = visit | 1 << ((ny2-1)*4 + nx2); //방문 처리
				
				for(int d3=1; d3<=4; d3++) {
					int ny3 = ny2 + sdy[d3];
					int nx3 = nx2 + sdx[d3];
					if(ny3<1 || nx3<1 || ny3>4 || nx3>4) continue; //인덱스 체크
					int count3 = count2;
					
					if((visit2 & (1 << ((ny3-1)*4 + nx3)) ) == 0) count3 += dupMap[ny3][nx3].fish.size(); //방문 안했던 곳이면 물고기 먹기
					int dir = d1*100 + d2*10 + d3; //방향 숫자로 변환
					queue.add(new int[] {dir, count3});
				}
			}
		}
		
		//상어 이동하기
		int[] move = queue.poll();
		int ny = sharkY; int nx = sharkX;
		//상어 현재 위치에 있는 물고기는 안잡아먹음
		for(int i=2; i>=0; i--) {
			ny += sdy[(int) (move[0] / (Math.pow(10, i)))];
			nx += sdx[(int) (move[0] / (Math.pow(10, i)))];
			if(dupMap[ny][nx].fish.size() > 0) {
				dupMap[ny][nx].fish.clear(); //물고기 다 잡아먹기
				dupMap[ny][nx].smell = 3; //물고기 냄새 남기기
			}
			move[0] %= (Math.pow(10, i));
		}
		
		//상어 위치 변경
		dupMap[sharkY][sharkX].shark = false;
		dupMap[ny][nx].shark = true;
		sharkY = ny; sharkX = nx;
		
	}
	
	//물고기 냄새 -1
	static void smellDecrease() {
		for(int i=1; i<=4; i++) {
			for(int j=1; j<=4; j++) {
				if(dupMap[i][j].smell > 0) dupMap[i][j].smell--;
			}
		}
	}
	
	//물고기 복제마법 완료
	static void duplicate() {
		for(int i=1; i<=4; i++) {
			for(int j=1; j<=4; j++) {
				
				//map배열 칸에 있는 물고기 dupMap에 복제해주기
				for(int f : map[i][j].fish) {
					dupMap[i][j].fish.add(f);
				}
			}
		}
	}
	
	//칸 정보
	static class Cell {
		boolean shark; //상어 유무
		int smell; //물고기 냄새(2,1,0)
		Queue<Integer> fish = new ArrayDeque<>(); //현재 있는 물고기들의 방향 저장하기
		Cell(boolean shark, int smell){
			this.shark = shark;
			this.smell = smell;
		}
	}
}
