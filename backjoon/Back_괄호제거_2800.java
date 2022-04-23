package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
/*
1. 괄호 쌍을 찾아서 쌍의 인덱스를 저장한다
2. 괄호 1개 ~ n개를 부분집합으로 따져서 원래 문자에서 공백으로 바꿔준다 (부분집합 바이너리 카운팅)
3. 2에서 나온 문자에서 공백을 제거하고 ans 리스트에 더해준다
4. ans 리스트의 중복을 제거하고 출력해준다
 */
public class Back_괄호제거_2800 {

	static String text; //원본 문자열
	static ArrayList<String> ans; //정답 리스트
	static int[][] pair; //괄호쌍 위치
	static int pairNum; //괄호 개수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		text = br.readLine();
		ans = new ArrayList<>(); //정답 담기
		pair = new int[10][2]; //괄호쌍의 위치 담기 (괄호는 최대 10개이므로 10개로 선언)
		pairNum = 0;
		
		//괄호 쌍 찾기
		Stack<Integer> stack = new Stack<>(); //괄호 쌍 찾기 위함
		for(int i=0; i<text.length(); i++) {
			if(text.charAt(i) == '(') stack.push(i);
			else if(text.charAt(i) == ')') {
				int pairStart = stack.pop();
				pair[pairNum][0] = pairStart; // ( 위치 저장
				pair[pairNum][1] = i; // ) 위치 저장
				pairNum++; //괄호 갯수 +1
			}
		}
		
		//부분집합 만들기
		generateSubset();
		
		Collections.sort(ans);
		for(int i=0; i<ans.size(); i++) {
			if(i != 0 && ans.get(i-1).equals(ans.get(i))) continue;
			System.out.println(ans.get(i));
		}
	}
	
	//부분집합 바이너리 카운팅
	static void generateSubset() { 
		int caseCount = 1 << pairNum; // 괄호개수만큼 자릿수 만들어주기 111 001 010 011
		
		for(int flag = 1; flag<caseCount; flag++) { //괄호 쌍을 적어도 하나는 없애줘야하니 flag는 1부터 시작
			StringBuilder ansText = new StringBuilder(text); //원본 문자열 복사
			
			for(int i=0; i<pairNum; i++) {
				if((flag & 1 << i) != 0) { // 부분집합 => 해당 인덱스의 괄호를 없애준다 (공백으로 바꿔줌)
					ansText.setCharAt(pair[i][0], ' '); 
					ansText.setCharAt(pair[i][1], ' ');
				}
			}
			ans.add(ansText.toString().replaceAll(" ", "")); //공백 없애고 정답 리스트에 담기
		}
	}

}
