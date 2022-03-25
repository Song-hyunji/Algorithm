package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Back_바이토닉_11054 {

	static int N;
	static int[] arr, dpinc, dpdec;
	public static void main(String[] args) throws Exception{
		//입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		dpinc = new int[N]; //앞에서부터 순서대로 증가하는 순열 개수 저장
		dpdec = new int[N]; //뒤에서부터 순서대로 증가하는 순열 개수 저장
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.fill(dpinc, 1);
		Arrays.fill(dpdec, 1);
		
		//앞에서부터 순서대로 증가하는 순열 개수 저장
		for(int i=1; i<N; i++) {
			for(int j=i; j>=0; j--) {
				if(arr[i] > arr[j]) { //오름차순
					if(dpinc[i] < dpinc[j]+1) dpinc[i] = dpinc[j]+1; 
				}
			}
		}
		
		//뒤에서부터 순서대로 증가하는 순열 개수 저장
		for(int i=N-2; i>=0; i--) {
			for(int j=N-1; j>=i; j--) {
				if(arr[i] > arr[j]) { //거꾸로 오름차순
					if(dpdec[i] < dpdec[j]+1) dpdec[i] = dpdec[j]+1;
				}
			}
		}
		
		//dpinc의 맨 뒤와 dpdec의 맨 앞 중 큰 숫자로 answer 초기화
		int answer = dpinc[N-1] > dpdec[0] ? dpinc[N-1] : dpdec[0];
		
		
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				if(arr[i] > arr[j]) {
					int sum = dpinc[i] + dpdec[j];
					int max = dpinc[i] > dpdec[j] ? dpinc[i] : dpdec[j];
					max = max > sum ? max : sum;
					answer = answer > max ? answer : max;
				}
			}
		}
		System.out.println(answer);
		
	}

}
