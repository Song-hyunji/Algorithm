package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Back_좌표압축_18870 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Number[] number = new Number[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			number[i] = new Number(Integer.parseInt(st.nextToken()), i, 0);
		}
		
		//크기 순서대로 정렬
		Arrays.sort(number, (o1,o2)->o1.num-o2.num);
		
		//크기 순서대로 순번 매기기(좌표압축)
		for(int i=1; i<N; i++) {
			if(number[i].num == number[i-1].num) number[i].sizeIdx = number[i-1].sizeIdx;
			else number[i].sizeIdx = number[i-1].sizeIdx+1;
		}
		
		//원래 입력받은 순서대로 다시 정렬하기
		Arrays.sort(number, (o1,o2)->o1.idx-o2.idx);
		
		//출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(number[i].sizeIdx+" ");
		}
		System.out.println(sb);

	}
	static class Number{
		int num; //입력받은 숫자
		int idx; //원래의 idx
		int sizeIdx; //사이즈 순으로 했을 때의 idx
		public Number(int num, int idx, int sizeIdx) {
			super();
			this.num = num;
			this.idx = idx;
			this.sizeIdx = sizeIdx;
		}
	}

}
