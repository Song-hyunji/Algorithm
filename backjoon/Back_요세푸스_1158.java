package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Back_요세푸스_1158 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> yose = new ArrayList<>();
		for(int n=1; n<=N; n++) {
			yose.add(n);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		int count = 0;
		int idx = 0;
		while(!yose.isEmpty()) {
			if(++count % K == 0) {
				sb.append(yose.get(idx) + ", ");
				yose.remove(idx--);
			}
			if(++idx>=yose.size()) idx = 0;
		}
		System.out.println(sb.substring(0, sb.length()-2)+">");
	}

}
