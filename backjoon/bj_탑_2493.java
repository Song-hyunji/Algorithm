package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class bj_탑_2493 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		Stack<Top> top = new Stack<Top>();
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++) {
			int h = Integer.parseInt(st.nextToken()); //새로운 탑의 높이
			Top t = new Top(i, h); 
			
			int sendIdx = 0; //새로운 탑에게 레이저신호 받는 탑의 idx
			
			if(top.empty()) { //스택에 아무것도 없을 때
				top.push(t);
				sb.append(sendIdx + " ");
				continue;
			}else if(top.peek().num > h) { //스택 젤 위의 탑이 바로 레이저 신호 받을 때
				sb.append(top.peek().idx+" ");
				top.push(t);
				continue;
			}
			
			//바로 레이저 신호를 받지 못해서 스택을 순회해야할 때
			for(int j=top.size()-1; j>=0; j--) {
				if(top.peek().num < h) { //새로 들어올 탑보다 높이 낮으면 이후에 레이저 신호 받을일 없으니까 pop
					top.pop();
				}else { //새로 들어올 탑보다 높으면 레이저 신호 받음
					sendIdx = top.peek().idx;
					break;
				}
			}
			sb.append(sendIdx+" ");		
			top.push(t);
			
		}

		System.out.println(sb);
	}
}
class Top{
	int idx; 
	int num;
	public Top() {}
	public Top(int idx, int num) {
		super();
		this.idx = idx;
		this.num = num;
	}
	
}
