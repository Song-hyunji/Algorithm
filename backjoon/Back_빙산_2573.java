package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
//Back_빙산_2573
public class Back_빙산_2573 {
	
	static int N, M;
	static int map[][];
	static boolean visit[][];
	
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//입력받기
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int age = 0; //시간
		while(true) { //1년에 반복 1번
			age++;
			int slice = 0; //빙산 조각 개수
			
			visit = new boolean[N][M];
			ite : for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					
					if(map[i][j] !=0 && !visit[i][j]) {
						if(++slice == 2) break ite;
						bfs(i,j);
					}
				}
			}
			
			//빙산 조각이 0이거나 2이상이면 멈춤
			if(slice == 0) {
				System.out.println(0);
				break;
			}else if(slice >=2) {
				System.out.println(age-1);
				break;
			}
		}
	}
	
	static void bfs(int a, int b) { //한 덩어리의 빙산 모두를 녹임
		int[][] copyMap = copy(map); //미리 녹은 빙산이 다른 빙산에 영향 주지 않기 위해 배열복사
		
		Queue<Loc> queue = new ArrayDeque<>();
		queue.offer(new Loc(a,b));
		
		visit[a][b] = true;
		
		while(!queue.isEmpty()) {
			Loc loc = queue.poll();
			
			for(int d=0; d<4; d++) {
				int Y = loc.y + dy[d];
				int X = loc.x + dx[d];
				if(Y<0 || X<0 || Y>=N || X>=M || visit[Y][X]) continue; //인덱스체크, 방문체크
				
				if(map[Y][X] == 0) { //주변에 0 이 있으면 빙산 -1
					if(copyMap[loc.y][loc.x] <=0 ) copyMap[loc.y][loc.x] = 0; 
					else copyMap[loc.y][loc.x]--;
				}else { //같은 덩어리의 빙산이 있으면 방문체크 & 큐 삽입
					visit[Y][X] = true;
					queue.offer(new Loc(Y,X));
				}
			}
		}
		
		map = copyMap; //빙산 녹인거 map 배열에 반영
	}
	
	static int[][] copy(int[][] map){ //배열복사
		int[][] copyMap = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		return copyMap;
	}
	
	static class Loc{ //위치 클래스
		int y;
		int x;
		Loc(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
