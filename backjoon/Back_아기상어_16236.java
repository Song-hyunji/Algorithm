import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/*
N*N크기. 물고기 M마리. 아기상어 1마리(초기크기:2)
아기상어보다 큰 물고기 -> 지나갈 수 없음
아기상어보다 작은 물고기 -> 지나감 & 먹을 수 있음
아기상어와 같은 물고기 -> 지나감

[더이상 물고기 못먹을 때까지의 시간 구하기]
거리가 가장 가까운 물고기 먹으러 감
- 거리가 가까운 물고기 여러개 => 가장 위에 있는 물고기 > 가장 왼쪽에 있는 물고기
 */
public class Back_아기상어_16236 {

	static int N, sharkY, sharkX, size, time, eatNum;
	static int[][] map;
	
	static int[] dy = {-1,0,1,0}; //상 좌 하 우
	static int[] dx = {0,-1,0,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //크기
		
		map = new int[N][N]; //공간
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 9) {
					sharkY = i; sharkX = j;
				}
			}
		}
		size = 2; //상어 초기 크기
		time = 0; //엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간
		eatNum = 0; //상어가 먹은 물고기 개수
		
		while(true) {
			if(!eat()) break; //먹은게 없으면 멈춤
		}
		
		System.out.println(time);
	}
	
	static boolean eat() {
		boolean visit[][] = new boolean[N][N];
		PriorityQueue<Cell> queue = new PriorityQueue<>(
				(o1,o2) -> o1.count == o2.count ? (o1.y == o2.y ? o1.x - o2.x : o1.y - o2.y) : o1.count - o2.count);
		queue.add(new Cell(sharkY, sharkX, 0));
		visit[sharkY][sharkX] = true;
		
		while(!queue.isEmpty()) {
			Cell cell = queue.poll();
			
			//아기상어보다 더 작은 물고기 있으면 잡아먹고 거기로 이동하기
			if(map[cell.y][cell.x] > 0 && map[cell.y][cell.x] < size) {
				map[sharkY][sharkX] = 0;
				map[cell.y][cell.x] = -1; //아기상어가 본인을 안잡아 먹기 위해 -1로 변경
				sharkY = cell.y; sharkX = cell.x; //상어 이동
				time += cell.count; //시간 추가
				eatNum++; //먹은 물고기 수 +1
				if(eatNum == size) { //상어 증가
					size++;
					eatNum = 0;
				}
				return true;
			}
			
			//이동 가능한 칸 탐색
			for(int d=0; d<4; d++) {
				int ny = cell.y + dy[d];
				int nx = cell.x + dx[d];
				if(ny<0 || nx<0 || ny>=N || nx>=N || visit[ny][nx]) continue; //인덱스, 방문체크
				
				visit[ny][nx] = true; //방문처리
				
				if(map[ny][nx] > size) continue; //아기상어보다 더 큰 물고기 있으면 못지나감
				
				queue.add(new Cell(ny, nx, cell.count+1)); //이동할 수 있으면 큐에 넣기
				
			}
		}
		return false;
	}
	
	static class Cell{ //칸의 정보
		int y;
		int x;
		int count; //상어 위치에서 해당 칸까지의 거리
		public Cell(int y, int x, int count) {
			super();
			this.y = y;
			this.x = x;
			this.count = count;
		}
	}
}
