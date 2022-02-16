package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Back_쿼드트리_1992 {

	static char[][] quad; //영상 배열
	static StringBuilder sb; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); //영상 크기
		quad = new char[N][];
		
		//영상 배열 입력 받기
		for(int n=0; n<N; n++) {
			quad[n] = br.readLine().toCharArray();
		}
		
		sb = new StringBuilder();
		
		//본 영상 압축
		char total = condense(0, 0, N);
		
		if(total != '2') sb.append(total); //본 영상이 0 또는 1로만 이루어져있을 때 
		else divideConquer(0,0,N); 
		
		System.out.println(sb.toString());
		
	}
	static void divideConquer(int startX, int startY, int num) { //0과 1 혼합해서 이루어져있을 때 분할정복
		sb.append("(");
		
		//좌상 압축
		char leftTop = condense(startX, startY, num/2);
		if(leftTop != '2') sb.append(leftTop);
		else divideConquer(startX, startY, num/2);
		
		//우상 압축
		char rightTop = condense(startX, startY + num/2, num/2);
		if(rightTop != '2') sb.append(rightTop);
		else divideConquer(startX, startY + num/2, num/2);
		
		//좌하 압축
		char leftBottom = condense(startX + num/2, startY, num/2);
		if(leftBottom != '2') sb.append(leftBottom);
		else divideConquer(startX + num/2, startY, num/2);
		
		//좌하 압축
		char rightBottom = condense(startX + num/2, startY + num/2, num/2);
		if(rightBottom != '2') sb.append(rightBottom);
		else divideConquer(startX + num/2, startY + num/2, num/2);
		
		sb.append(")");
		
	}
	
	static char condense(int startX, int startY, int num) { //0만 있는지, 1만 있는지, 혼합되어 있는지 확인
		char temp = quad[startX][startY];
		if(num == 1) return temp;
		
		for(int i=startX; i<startX+num; i++ ) {
			for(int j=startY; j<startY+num; j++) {
				if(quad[i][j] != temp) {
					return '2';
				}
			}
		}
		return temp;
	}
}
