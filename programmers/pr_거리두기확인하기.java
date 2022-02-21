package pr;

public class pr_거리두기확인하기 {
	
	//상우하좌 상우,우하,하좌,좌상
	static int[] dy = {-1,0,1,0, -1,1,1,1};
	static int[] dx = {0,1,0,-1, 1,1,-1,-1}; 

	static String[][] places;
	public static void main(String[] args) {
		//응시자P, 빈테이블O, 파티션X
		//거리두기 지키면 1, 아니면 0
		String[][] place = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, 
				{"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, 
				{"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, 
				{"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, 
				{"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
		
		places = place;
		
		int[] answer = solution(places);
		for (int i = 0; i < 5; i++) { //정답 출력
			System.out.print(answer[i]+" ");
		}

	}
	
	static public int[] solution(String[][] places) {

		int[] answer = new int[5];
		
		for(int i=0; i<5; i++) {
			int tempAns = 1; //각 행의 정답
			
			testcase : for(int j=0; j<5; j++) {
				for(int k=0; k<5; k++) {
					if(places[i][j].charAt(k) == 'P') { //P를 만나면 체크
						if(check(i,j,k) == false) { //거리두기 안지키면 정답 0 으로 넣고 멈춤
							tempAns = 0; 
							break testcase;
						}
					}
				}
			}
			
			answer[i] = tempAns;
		}
        return answer;
    }
	
	static boolean check(int a, int b, int c) { //거리두기 잘 지켜졌는지 확인
		
		//상우하좌 확인(1칸 옆)
		for(int d=0; d<4; d++) {
			int Y = b + dy[d];
			int X = c + dx[d];
			if(Y<0 || X<0 || Y>=5 || X>=5) continue; //인덱스 처리
			if(places[a][Y].charAt(X) == 'P') return false; //바로 옆에 P면 false
		}
		
		//상우하좌 확인(2칸 옆)
		for(int d=0; d<4; d++) {
			int Y = b + dy[d]*2;
			int X = c + dx[d]*2;
			if(Y<0 || X<0 || Y>=5 || X>=5) continue; //인덱스 처리
			if(places[a][Y-dy[d]].charAt(X-dx[d]) == 'X') continue; //벽 있으면 넘김
			if(places[a][Y].charAt(X) == 'P') return false; //벽 없이 2칸 옆에 P면 false
		}
		
		//대각선 확인
		for(int d=4; d<7; d++) {
			int Y = b + dy[d];
			int X = c + dx[d];
			if(Y<0 || X<0 || Y>=5 || X>=5) continue; //인덱스 처리
			if(places[a][Y].charAt(c) == 'X' && places[a][b].charAt(X) == 'X') continue; // 양 옆 다 벽 있으면 넘김
			if(places[a][Y].charAt(X) == 'P') return false; //벽 없이 대각선에 P면 false
		}
		
		return true;
		
	}
}
