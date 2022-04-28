import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
블록 : 검은색(-1), 무지개(0), 일반(1~M가지 색)
블록 그룹(블록 2개 이상) : 연결된 블록의 집합 -> 일반블록 1개 이상, 일반블록 색 동일해야, 검은색 0개, 무지개 개수 상관X
기준 블록 : 행 번호 가장 작고, 열 번호 가장 작은거

오토 플레이 (블록 그룹이 있으면 계속 실행)
1. 크기가 큰 블록 그룹 => 무지개 블록 수, 기준 블록 행 큰거, 기준 블록 열 큰거
2. 1에서 찾은 블록 그룹 제거. 블록의 수^2점 획득
3. 중력 => 검은색 블록 빼고 모든 블록 : 행 번호가 큰 칸으로 이동(아래로 이동)
4. 90도 반시계 회전
5. 중력 
 */
public class Back_상어중_21609 {

	static int N, M;
	static int map[][]; //블록 정보
	static int score;
	
	static int dy[] = {0,0,1,-1};
	static int dx[] = {-1,1,0,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//블록 정보 저장
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		score = 0;
		//오토 플레이
		while(true) {
			if(!existBlock()) break;
			gravity();
			rotate(); //90도 반시계방향 회전
			gravity();
		}
		
		System.out.println(score);

	}
	
	//반시계 방향 90도 회전
	static void rotate() {
		int n = N;
		int Y=0;
		int X=0;
		while(n>0) {
			//윗줄 한줄 temp에 저장해두기
			int temp[] = new int[n];
			for(int i=X; i<X+n; i++) {
				temp[i-X] = map[Y][i];
			}
			
			//오른쪽 한줄 윗줄로 이동
			for(int i=X; i<X+n; i++) {
				map[Y][i] = map[i][N-1-X];
			}
			
			//밑줄 -> 오른쪽줄 이동
			for(int i=X; i<X+n; i++) {
				map[i][N-1-X] = map[N-1-X][N-1-i];
			}
			
			//왼쪽줄 -> 밑줄 이동
			for(int i=X; i<X+n; i++) {
				map[N-1-X][N-1-i] = map[N-1-i][X];
			}
			
			//저장해둔 temp를 왼쪽 줄에 저장
			for(int i=X; i<X+n; i++) {
				map[N-1-i][X] = temp[i-X]; 
			}
			Y++; X++; n-=2;
		}
	}
	
	//격자에 중력 작용
	static void gravity() {
		for(int j=0; j<N; j++) {
			for(int i=N-1; i>=0; i--) {
				if(map[i][j] < 0) continue; //옮길 필요 없으면 넘김
				
				//아래로 옮기기
				for(int k=i; k<N-1; k++) {
					if(map[k+1][j] == -10) {
						map[k+1][j] = map[k][j];
						map[k][j] = -10;
					}else break;
				}
			}
		}
	}
	
	//기준 블록 : 일반블록 중 행의 번호가 작은 블록, 열의 번호가 작은 블록
	//블록 그룹 선택 : 무지개 블록 수 많은거 > 기준블록 행 큰거 > 기준블록 열 큰거
	//1. 가장 큰 블록 그룹 찾기 -> 그룹이 있으면 true, 없으면 false 리턴
	//2. 1에서 찾은 블록 그룹의 모든 블록 제거(-10으로 저장)
	//3. 점수 추가
	static boolean existBlock() {
		Group standardGroup = new Group(0,0,-1,-1,0); 
		
		//(i,j)에서 시작해서 블록 그룹 찾기
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] >= 0) {
					//블록 그룹의 개수가 몇개인지 찾기
					boolean visit[][] = new boolean[N][N]; //bfs용 방문 확인
					Queue<int[]> queue = new ArrayDeque<>(); //bfs용 큐
					
					//새로운 블록 그룹 찾기
					Group group = new Group(1, map[i][j] == 0 ? 1 : 0, i, j, map[i][j]); 
					group.queue.add(new int[] {i,j}); //현재 블록 그룹에 넣어주기
					
					visit[i][j] = true; //방문처리
					queue.add(new int[]{i,j}); 
					
					while(!queue.isEmpty()) {
						int[] b = queue.poll();
						for(int d=0; d<4; d++) {
							int ny = b[0] + dy[d];
							int nx = b[1] + dx[d];
							if(ny<0 || nx<0 || ny>=N || nx>=N || visit[ny][nx] || map[ny][nx]<0) continue; //인덱스체크, 방문체크, 무지개&일반블록 체크
							
							//일반 블록 색은 모두 같아야 함
							if(map[ny][nx] != 0 && group.color == 0) group.color = map[ny][nx]; //일반 블록을 처음 만날 때
							else if(map[ny][nx] != 0 && map[ny][nx] != group.color) continue; //일반 블록 색이 다르면 넘김
							
							//무지개 블록이면 무지개 +1
							if(map[ny][nx] == 0) group.rainbow++;
							else { 
								//일반 블록이면 기준블록 선택하기
								//행의 번호가 가장 작은 블록, 그러한 블록이 여러개면 열의 번호가 가장 작은 블록
								if(ny < group.y || (ny == group.y && nx < group.x)) { 
									group.y = ny;
									group.x = nx;
								}
							}
							
							visit[ny][nx] = true;
							
							group.total++;
							queue.add(new int[] {ny, nx});
							group.queue.add(new int[] {ny, nx});
						}
					}
					
					if(group.color == 0) continue; //일반 블록이 없으면 넘김
					
					//블록 그룹 선택
					if(standardGroup.total < group.total || (standardGroup.total == group.total && standardGroup.rainbow < group.rainbow) 
							|| (standardGroup.total == group.total && standardGroup.rainbow == group.rainbow && standardGroup.y < group.y)
							|| (standardGroup.total == group.total && standardGroup.rainbow == group.rainbow && standardGroup.y == group.y && standardGroup.x < group.x)) {
						standardGroup = group;
					}
				}
			}
		}
		
		if(standardGroup.total <= 1) return false;
		else {
			//점수 더해주기
			score += standardGroup.total*standardGroup.total;
			//블록 그룹의 모든 블록 제거
			for(int[] cell : standardGroup.queue) {
				map[cell[0]][cell[1]] = -10;
			}
			return true;
		}
	}
	
	static class Group{
		int total; //전체 블록 수
		int rainbow; //무지개 블록 수
		int y; //기준블록의 Y
		int x; //기준블록의 X
		int color; //일반블록 색
		Queue<int[]> queue = new ArrayDeque<>(); //포함된 블록들의 위치
		
		public Group(int total, int rainbow, int y, int x, int color) {
			super();
			this.total = total;
			this.rainbow = rainbow;
			this.y = y;
			this.x = x;
			this.color = color;
		}
	}
}
