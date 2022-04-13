package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;
//백준 9205 맥주 마시면서 걸어가기
public class Back_맥주_9205 {

	static int N; //편의점 개수
	static ArrayList<int[]> store; //편의점 좌표
	static Queue<int[]> queue; //갈 수 있는 장소
	static int[] house; //집
	static int[] festival; //페스티벌
	static int nowX, nowY; //현재 위치
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			//입력 받기
			N = Integer.parseInt(br.readLine());
			store = new ArrayList<>();
			queue = new ArrayDeque<>();
			house = new int[2];
			festival = new int[2];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			house[0] = Integer.parseInt(st.nextToken());
			house[1] = Integer.parseInt(st.nextToken());
			
			for(int n=0; n<N; n++) {
				st = new StringTokenizer(br.readLine());
				store.add( new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			}
			
			st = new StringTokenizer(br.readLine());
			festival[0] = Integer.parseInt(st.nextToken());
			festival[1] = Integer.parseInt(st.nextToken());
			
			//현재 위치
			nowX = house[0];
			nowY = house[1];
			String ans = "sad";
			
			queue.add(new int[] {nowX, nowY});
			
			while(!queue.isEmpty()) {
				int[] next = queue.poll(); //이동할 다음 장소 꺼내기
				nowX = next[0]; nowY = next[1]; //현재 위치 갱신
				
				if((Math.abs(festival[0]-next[0])+Math.abs(festival[1]-next[1])) <= 1000) { //페스티벌로 갈 수 있는지 확인
					ans = "happy"; break;
				}
				
				//현재 위치에서 갈 수 있는 편의점들 오름차순 계산
				Collections.sort(store, (o1,o2)->{ 
					return Integer.compare(Math.abs(nowX-o1[0])+Math.abs(nowY-o1[1]), Math.abs(nowX-o2[0])+Math.abs(nowY-o2[1]));
				});
				
				//현재 위치에서 갈 수 있는 편의점들을 queue에 넣어주기
				while(!store.isEmpty()) {
					if(Math.abs(nowX-store.get(0)[0])+Math.abs(nowY-store.get(0)[1]) > 1000) break;
					queue.add(store.remove(0));
				}
				
			}
			System.out.println(ans);
			
		}
	}
}
