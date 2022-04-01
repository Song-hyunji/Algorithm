package pr;

public class pr_정수삼각형 {

	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}}));

	}
	static public int solution(int[][] triangle) {
        int answer = 0;
        
        //정수삼각형 아래로 내려가면서 누적시켜줌
        for(int i=1; i<triangle.length; i++) {
        	for(int j=0; j<triangle[i].length; j++) {
        		if(j == 0) { //가장 왼쪽 줄일 때
        			triangle[i][j] += triangle[i-1][j];
        		}else if(j == triangle[i].length -1) { //가장 오른쪽 줄일 때
        			triangle[i][j] += triangle[i-1][j-1];
        		}else { //가운데
        			triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]); 
        		}
        	}
        }
        
        //최대값 찾기
        int lastrow = triangle.length-1;
        for(int i=0; i<triangle[lastrow].length; i++) {
        	answer = Math.max(answer, triangle[lastrow][i]);
        }
        
        return answer;
    }
}
