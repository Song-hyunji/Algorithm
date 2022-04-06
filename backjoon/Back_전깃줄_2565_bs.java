package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//바이너리 서치로 LCS 풀기
public class Back_전깃줄_2565_bs {

	static int N;
	static int[][] elec;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		elec = new int[N][2]; //[n][0]:A전봇대 위치 , [n][1]:B전봇대 위치
		int[] memoi = new int[N]; //memoi[i] : i길이의 LCS를 가진 숫자 중 최소값
		
		//입력받기
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			elec[i][0] = Integer.parseInt(st.nextToken());
			elec[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(elec, (o1, o2)-> o1[0]-o2[0]); //A 전봇대 위치 기준으로 오름차순 정렬
		
		//LCS - binary search로 풀기
		int len = 0; //현재의 최고 길이
		
		for(int i=0; i<N; i++) {
			//memoi배열의 0~len에서 elec[i][1] 숫자 위치 찾기 
			//-> 해당 숫자를 찾으면 그 위치 리턴, 아니면 -(삽입할 위치)-1 리턴
			//삽입할 위치는 해당 숫자보다 큰 최초의 위치
			int position = Arrays.binarySearch(memoi, 0, len, elec[i][1]); 
			position = Math.abs(position) - 1;
			memoi[position] = elec[i][1];
			if(position == len) len++;
		}
		
		System.out.println(N - len);
		
	}

}
