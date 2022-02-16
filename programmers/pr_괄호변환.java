package pr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
//프로그래머스 괄호변환 문제(level2)
public class pr_괄호변환 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String p = br.readLine();
		
		System.out.println(solution(p));
		
	}
	static String solution(String p) { //입력값 받고 정답 리턴하는 메소드
		String answer = "";
		if(correct(p)) return p; //올바른 괄호 문자열이면 바로 답으로 리턴
		
		//올바른 괄호 문자열 아닐때
		
		//문자열 분리
		int divideNum = divide(p); //분리할 index 받아옴
		String u = p.substring(0, divideNum);
		String v = p.substring(divideNum, p.length());
		
		if(correct(u)) { //u가 올바른 괄호 문자열이면
			answer = answer + u;
			if(!correct(v)) {
				answer += solution(v); //v의 올바른 괄호 문자열을 찾기 위해 solution 재귀 돌려줌
			}
		}
		else { //u가 균형잡힌 괄호 문자열이면
			answer += "(";
			answer += solution(v); 
			answer += ")";
			answer += remakeU(u); //u 문자열을 변형해줌
		}
		return answer;
	}
	
	static String remakeU(String u) { //u가 올바른 괄호 문자열이 아닐 때 만드는 새로운 문자열
		String reU = "";
		for(int i=1; i<u.length()-1; i++) {  //앞뒤 글자 하나씩은 순회하지 않고 뒤집어줌
			if(u.charAt(i) == '(') reU += ")";
			else if(u.charAt(i) == ')') reU += "(";
		}
		return reU;
	}
	
	static int divide(String p) { //p 문자열을 u, v로 나눠줌
		int left=0, right=0; //(개수와 )개수 확인하기 위함
		for(int i=0; i<p.length(); i++) {
			if(p.charAt(i)=='(') left++;
			else if(p.charAt(i)==')') right++;
			
			if(left == right) { //(개수와 )개수가 같으면 균형잡힌 문자열이므로 리턴
				return i+1;
			}
		}
		return p.length();
	}
	
	static boolean correct(String s) { //올바른 괄호 문자열인지 확인
		if(s.equals("")) return true; //빈 문자열이면 올바름
		
		boolean ans = true;
		Stack<Character> stack = new Stack<>();
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)=='(') {
				stack.add('(');
			}else {
				if(stack.isEmpty() || stack.peek() != '(') {
					ans = false; break;
				}else {
					stack.pop();
				}
			}
		}
		
		return ans;
	}
}
