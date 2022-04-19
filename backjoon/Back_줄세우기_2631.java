package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//LCS 이진 검색 활용해서 문제 해결
public class Back_줄세우기_2631 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //아이들 수
		
		//아이들 번호 입력받기
		int[] child = new int[N];
		for(int i=0; i<N; i++) {
			child[i] = Integer.parseInt(br.readLine());
		}
		
		int[] length = new int[N+1]; //length[i] : 길이가 i인 최장증가수열의 마지막 숫자 중 가장 작은 값
		
		int len = 0;
		for(int i=0; i<N; i++) {
			//length 배열의 0~len 인덱스에서 child[i] 값 검색 
			//해당 키 찾으면 그 위치 리턴, 아니면 -(삽입 위치)-1 리턴
			int pos = Arrays.binarySearch(length, 0, len, child[i]);
			pos = Math.abs(pos) - 1;
			length[pos] = child[i];
			if(len == pos) len++;
		}
		System.out.println(N-len);
	}
}
