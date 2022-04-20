import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back_연산자끼워넣기_14888 {
	
	static int N;
	static int[] num;
	static int[] ope; //연산자 개수(+,-,*,/)
	static int min,max;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		num = new int[N];
		ope = new int[4];
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			ope[i] = Integer.parseInt(st.nextToken());
		}
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		cal(0, num[0]);
		System.out.println(max);
		System.out.println(min);
	}
	
	static void cal(int idx, int result) {
		if(idx == N-1) {
			min = Math.min(min, result);
			max = Math.max(max, result);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(ope[i] == 0) continue;
			int newresult = result;
			switch(i) {
			case 0: newresult += num[idx+1]; break;
			case 1: newresult -= num[idx+1]; break;
			case 2: newresult *= num[idx+1]; break;
			case 3: newresult /= num[idx+1]; break;
			}
			ope[i]--;
			cal(idx+1, newresult);
			ope[i]++;
		}
		
		
	}

}
