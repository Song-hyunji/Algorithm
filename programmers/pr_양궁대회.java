package pr;

import java.util.Arrays;

public class pr_양궁대회 {
	
	static int ryan[], apeach[];
	static int score;
	static int[] answer;
	
	public static void main(String[] args) {
		System.out.println( Arrays.toString(solution(5, new int[] {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0})));
		System.out.println( Arrays.toString(solution(1, new int[] {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0})));
		System.out.println( Arrays.toString(solution(9, new int[] {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1})));
		System.out.println( Arrays.toString(solution(10, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3})));
	}
	
	//n : 화살개수, info: 어피치가 맞힌 과녁 점수의 개수(10점~0점 개수)
	//리턴: 라이언이 가장 큰 점수차이로 우승하기 위해 맞춰야할 n발의 점수. 배열에 담아 리턴. 라이언이 우승 못하면 [-1]
    static public int[] solution(int n, int[] info) {
    	ryan = new int[11];
    	apeach = info;
    	
    	score = -1;
    	answer = new int[] {-1};
    	
    	dfs(10, n); //낮은 점수부터 차례대로 화살 쏘기
        
        return answer;
    }
    
    //인덱스, 남은 화살 개수
    static void dfs(int idx, int shoot) {
    	if(shoot == 0) { //화살 다 쐈으면 점수 계산해주고 리턴
    		int tempScore = calScore();
    		if(tempScore > score) {
    			score = tempScore;
    			answer = ryan.clone(); //깊은 복사
    		}
    		return;
    	}
    	if(idx == -1) return; //인덱스 넘어가면 리턴
    	
    	for(int i=shoot; i>=0; i--) { //현재 인덱스에서 화살 쏘기
    		ryan[idx] = i;
    		dfs(idx-1, shoot-i);
    		ryan[idx] = 0;
    	}
    }
    
    //점수 계산
    static int calScore() {
    	int ape = 0; //어피치 점수
    	int li = 0; //라이언 점수
    	
    	for(int i=0; i<10; i++) {
    		if(apeach[i] == 0 && ryan[i] == 0 ) continue; //둘다 과녁에 화살을 못쐈으면 넘김
    		if(apeach[i] >= ryan[i]) ape += 10-i; //어피치 점수 획득
    		else li += 10-i; //라이언 점수 획득
    	}
    	
    	if(li > ape) return li-ape;
    	else return -1;
    }

}
