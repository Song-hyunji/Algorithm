package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_PokerGame_9760 {

	static String[] card;
	static int[] number; // A~K (1~13)
	static int[] pattern; // S D H C (1~4)
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t=1; t<=T; t++) {
			card = new String[5];
			number = new int[14];
			pattern = new int[5];
			
			//card 배열에 카드 입력받기
			//입력받고 해당하는 슈트(패턴)과 랭크(값)에 누적
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<5; i++) {
				card[i] = st.nextToken();
				
				switch(card[i].charAt(0)) {
					case 'S' : pattern[1]++; break;
					case 'D' : pattern[2]++; break;
					case 'H' : pattern[3]++; break;
					case 'C' : pattern[4]++; break;
				}
				switch(card[i].charAt(1)) {
				case 'A' : number[1]++; break;
				case 'T' : number[10]++; break;
				case 'J' : number[11]++; break;
				case 'Q' : number[12]++; break;
				case 'K' : number[13]++; break;
				default : number[card[i].charAt(1)-'0']++; break; //2~9
				}
			}
			
			
			sb.append("#"+t+" ");
			
			int[] arr = numberArr(); //arr의 1~4번째에 동일한 랭크(값) 1장, 2장, 3장, 4장인 개수 저장
			
			if(Straight() && Flush()) {
				sb.append("Straight Flush");
			}else if(arr[4] == 1) {
				sb.append("Four of a Kind");
			}else if(arr[3]==1 && arr[2]==1) {
				sb.append("Full House");
			}else if(Flush()) {
				sb.append("Flush");
			}else if(Straight()) {
				sb.append("Straight");
			}else if(arr[3] == 1) {
				sb.append("Three of a kind");
			}else if(arr[2] == 2) {
				sb.append("Two pair");
			}else if(arr[2] == 1) {
				sb.append("One pair");
			}else {
				sb.append("High card");
			}
			sb.append("\n");
			
		}
		System.out.println(sb);

	}
	static boolean Flush() { //5장이 모두 동일한 슈트(4번)
		for(int i=1; i<=4; i++) {
			if(pattern[i] == 5) return true;
		}
		return false;
	}
	static boolean Straight() { //값이 모두 연속적(5번)
		
		int num = 0;
		for(int i=1; i<=13; i++) {
			if(number[i] >= 1) num++;
			else if(number[i] == 0) num=0;
			
			if(num == 5) return true;
		}
		for(int i=1; i<=4; i++) {
			if(number[i] >= 1) num++;
			else if(number[i] == 0) num=0;
			
			if(num == 5) return true;
		}
		return false;
	}
	static int[] numberArr() { //동일한 랭크(값)2장인 개수, 3장인 개수, 4장인 개수
		int[] arr = new int[5];
		for(int i=1; i<=13; i++) {
			arr[number[i]]++;
		}
		return arr;
	}

}
