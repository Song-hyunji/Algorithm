package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Back10799_쇠막대기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String bar = br.readLine();
		Stack<Character> stack = new Stack<>();
		int ans = 0; //정답
		int lazer = 1; //현재 레이저에 걸쳐있는 쇠막대기 갯수
		
		stack.push(bar.charAt(0)); //처음 문자 먼저 넣기
		for(int i=1; i<bar.length(); i++) {
			if(bar.charAt(i) == '(') {
				stack.push('(');
				lazer++;
			}
			else if(bar.charAt(i) == ')') {
				//레이저 쏘기
				if(bar.charAt(i-1)=='(') {
					stack.pop();
					lazer--;
					ans+=lazer;
				}else {
					ans++;
					lazer--;
					stack.pop();
				}
			}
		}
		System.out.println(ans);
	}
}


