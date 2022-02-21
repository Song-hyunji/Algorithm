package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Back_암호만들기_1759 {
	//최소 1개의 모음, 2개의 자음
	
	static int L, C; 
	static char[] alpa;
	static char[] select;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		sb = new StringBuilder();
		select = new char[L];
		alpa = new char[C];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			alpa[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(alpa); //오름차순 정렬
		
		combination(0, 0);
		System.out.println(sb);
		
	}
	static void combination(int count, int start) {
		if(count == L) {
			int ja=0;
			int mo=0;
			String temp = "";
			for(int i=0; i<L; i++){
				if(select[i] == 'a' || select[i] == 'e' || select[i] == 'i' || select[i] == 'o' || select[i] == 'u') {
					mo++;
				}else ja++;
				temp+=select[i];
			}
			if(ja>=2 && mo>=1) {
				sb.append(temp+"\n");
			}
			return;
		}
		
		for(int i=start; i<C; i++) {
			select[count] = alpa[i]; 
			combination(count+1, i+1);
		}
	}
}
