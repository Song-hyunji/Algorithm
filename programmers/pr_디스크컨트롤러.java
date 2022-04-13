package pr;
import java.util.*;

public class pr_디스크컨트롤러 {

	static PriorityQueue<Job> queue;
    static int nextJob; //다음 작업 idx
	public static void main(String[] args) {
		System.out.println(solution(new int[][]{{0, 10}, {2, 12}, {9, 19}, {15, 17}}));
		System.out.println(solution(new int[][]{{0,3},{1,9},{2,6}}));

	}
	static public int solution(int[][] jobs) {
		Arrays.sort(jobs, (o1,o2)->o1[0]==o2[0]?o1[1]-o2[1]:o1[0]-o2[0]);
        int answer = 0;
        
        queue = new PriorityQueue<>((o1,o2)->o1.work-o2.work);
        
        queue.add(new Job(jobs[0][0], jobs[0][1])); 
        nextJob = 1; //다음 작업 idx
        int time = jobs[0][0]; //현재 시간
        
        while(!queue.isEmpty() || nextJob < jobs.length){
            Job job = queue.poll(); //수행할 작업 하나 빼기

            answer += (time - job.arrive) + job.work; //기다린 시간 정답에 더해주기
            time += job.work;
            
            //현재 시간까지 기다리고 있는 작업들 큐에 넣어주기
            for(int i=nextJob; i<jobs.length; i++){
                if(jobs[i][0] <= time) {
                	queue.add(new Job(jobs[i][0], jobs[i][1]));
                	nextJob++;
                }
                else break;
            }
            
            //기다리고 있는 작업은 없지만, 아직 작업이 남았을 때
            if(queue.isEmpty() && nextJob < jobs.length){
                queue.add(new Job(jobs[nextJob][0], jobs[nextJob][1]));
                time = jobs[nextJob][0];
                nextJob++;
            }
        }
        
        return answer / jobs.length;
    }
    
    static class Job{
        int arrive;
        int work;
        Job(int arrive, int work){
            this.arrive = arrive;
            this.work = work;
        }
    }
    

}
