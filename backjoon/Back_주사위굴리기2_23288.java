import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
처음 (1,1)에 놓여짐, 첫 이동 방향은 동쪽
1. 이동 방향에 맞게 1칸 이동(이동 못하면 반대 방향으로 이동)
2. 점수 더하기(그 지도칸의 수와 같은 수가 근처에 얼마나 있는지 bfs로 확인)
3. 주사위 아랫면 수, 지도 수 비교해서 방향 바꾸기(주사위>지도:90도 시계 회전 / 주사위<지도:90도 반시계 회전 / 주사위=지도:그대로) 
 */
public class Back_주사위굴리기2_23288 {

	static int N,M,K, dir, y,x, ans;
	static int map[][], dice[];
	
	static int dy[] = {0,1,0,-1}; //동남서북 (+:시계, -:반시계) 
	static int dx[] = {1,0,-1,0};
			
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //세로
		M = Integer.parseInt(st.nextToken()); //가로
		K = Integer.parseInt(st.nextToken()); //이동 횟수
		
		map = new int[N][M]; //(0,0)~(N-1,M-1)
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dice = new int[] {6,3,4,5,2,1}; //밑, 동, 서, 남, 북, 위
		dir = 0; //첫 이동 방향은 동쪽
		y=0; x=0; //첫 위치는 (0,0)
		ans = 0; //점수
		
		while(K-- > 0) {
			int ny = y + dy[dir]; //이동할 위치
			int nx = x + dx[dir];
			if(ny<0 || nx<0 || ny>=N || nx>=M) dir = (dir+2)%4; //이동 불가능하면 반대방향으로 바꿔주기
			move(); //방향에 맞게 이동해주기
			getScore();
			changeDir();
		}
		System.out.println(ans);
	}
	
	//방향에 맞게 이동해주기
	static void move() {
		int ny = y + dy[dir]; //이동할 위치
		int nx = x + dx[dir];
		
		int[] moveDice = new int[6];
		
		switch(dir) {
		case 0: //동쪽
			moveDice[0]=dice[1]; moveDice[1]=dice[5]; moveDice[2]=dice[0]; 
			moveDice[3]=dice[3]; moveDice[4]=dice[4]; moveDice[5]=dice[2];
			break;
		case 1: //남쪽
			moveDice[0]=dice[3]; moveDice[1]=dice[1]; moveDice[2]=dice[2]; 
			moveDice[3]=dice[5]; moveDice[4]=dice[0]; moveDice[5]=dice[4];
			break;
		case 2: //서쪽
			moveDice[0]=dice[2]; moveDice[1]=dice[0]; moveDice[2]=dice[5]; 
			moveDice[3]=dice[3]; moveDice[4]=dice[4]; moveDice[5]=dice[1];
			break;
		case 3: //북쪽
			moveDice[0]=dice[4]; moveDice[1]=dice[1]; moveDice[2]=dice[2]; 
			moveDice[3]=dice[0]; moveDice[4]=dice[5]; moveDice[5]=dice[3];
			break;
		}
		
		dice = moveDice;
		y = ny; x = nx; //이동한 위치 저장
	}
	
	//점수 더해주기(현재 주사위가 위치한 숫자와 같은 점수가 근처에 몇개 있는지 찾기. + 개수*숫자)
	static void getScore() {
		int num = map[y][x];
		int score = 1;
		
		boolean[][] visit = new boolean[N][M];
		visit[y][x] = true;
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {y,x});
		
		while(!queue.isEmpty()) {
			int[] cell = queue.poll();

			for(int d=0; d<4; d++) {
				int ny = cell[0] + dy[d]; 
				int nx = cell[1] + dx[d];
				if(ny<0 || nx<0 || ny>=N || nx>=M || visit[ny][nx] || map[ny][nx] != num) continue;
				visit[ny][nx] = true; 
				score++;
				queue.add(new int[] {ny, nx});
			}
		}
		
		ans += num * score;
	}
	
	//방향 바꿔주기
	static void changeDir() {
		if(dice[0] > map[y][x]) dir = (dir+1)%4;
		else if(dice[0] < map[y][x]) dir = (dir+3)%4;
	}

}
