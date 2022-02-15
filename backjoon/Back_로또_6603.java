package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Back_로또_6603 {

	static int K; //집합 S의 갯수
	static String[] N;
	static String[] select = new String[6];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			
			if(K==0) break;
			
			//K개 숫자 입력받기
			N = new String[K];
			for(int i=0; i<K; i++) {
				N[i] = st.nextToken();
			}
			
			//조합
			lotto(0,0);
			System.out.println();
			
		}

	}
	static void lotto(int srcIdx, int tgtIdx) {
		if(tgtIdx == 6) {
			for (String n : select) {
				System.out.print(n+" ");
			}
			System.out.println();
			return;
		}
		if(srcIdx == K) return;
		
		select[tgtIdx] = N[srcIdx];
		
		lotto(srcIdx+1, tgtIdx+1);
		lotto(srcIdx+1, tgtIdx);
		
	}

}
