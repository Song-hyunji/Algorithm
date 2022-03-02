package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Back_오목_2615 {

	static int[] dx = {1,1,1,0};
	static int[] dy = {-1,0,1,1};
	
	public static void main(String[] args) throws IOException {
		char[][] baduk = new char[19][19];

		//입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 19; i++) {
			baduk[i] = br.readLine().replace(" ", "").toCharArray();
		}
		
		int win = 0;
		
		ite : for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if(baduk[i][j] == '0') continue;
				
				for(int k = 0; k < 4; k++) { //4방 탐색
					int count = 0;
					
					for(int b=0;b<5;b++) { //오목 체크
						int X = i + dy[k]*b;
						int Y = j + dx[k]*b;
						if(X < 0 || Y < 0 || X >=19 || Y >=19) break; //인덱스 넘어가면 break
						if(baduk[i][j] != baduk[X][Y]) break; //색깔 다르면 break
						count++;
						
						if(count == 5) { //오목이면
							X = i - dy[k]; //육목인지 체크
							Y = j - dx[k];
							if(X >= 0 && Y >= 0 && X < 19 && Y < 19) {
								if(baduk[i][j] == baduk[X][Y]) break;								
							}
							
							X = i + dy[k]*5; //육목인지 체크
							Y = j + dx[k]*5;
							if(X >= 0 && Y >= 0 && X < 19 && Y < 19) {
								if(baduk[i][j] == baduk[X][Y]) break;
							}
							win = baduk[i][j] - '0'; //육목 아니면 print 후 종료
							System.out.println(win);
							System.out.println((i+1) + " " + (j+1));
							break ite;
						}
						
					}
				}
			}
		}
		if(win == 0) System.out.println(win);
		br.close();
		
	}
}
