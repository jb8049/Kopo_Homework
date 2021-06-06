package homework_0525;

import java.util.Random;

public class Modify_Thread_QUIZ {

//	static int all_sum;

	public static void main(String[] args) {

		CallCenter first = new CallCenter(1);
		CallCenter second = new CallCenter(2);
		CallCenter third = new CallCenter(3);
		CallCenter fourth = new CallCenter(4);

		System.out.println("성금 모금 시작");

		first.start();
		second.start();
		third.start();
		fourth.start();

		try {
			first.join(); // Thread가 종료되기까지 기다렸다가 다음 main 실행
			second.join();
			third.join();
			fourth.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("성금 모금 끝");
		System.out.println("국민들이 보내주신 성금 총 액 : " + (first.getAllSum()+second.getAllSum()+third.getAllSum()+fourth.getAllSum())  + "원");
	}
}


//     New_CallCenter first = new New_CallCenter(1) 1번 콜센터에 대한 스레드

class CallCenter extends Thread{
	
	Random random = new Random();
	private int randomValue;
	private int type;
	private int money_sum;
	private int all_sum;
	
	
	public CallCenter(int type) {
		this.type = type; // 1번 콜센터에 대한 정보, type변수를 활용할 수 있음
	}
	
	@Override
	public void run() {
		for(int i=0; i < 10 ; i++) {
			randomValue=(int)((Math.random()*10)+1)*1000;
			System.out.println(type +"번 콜센터 : " + randomValue + "원을 받았습니다." );
			money_sum += randomValue;
			
		} System.out.println(type +"번 콜센터 성금 총 액: " + money_sum + "원");
		  all_sum +=money_sum;
		
		
	}
	public int getAllSum() {
		return all_sum;
	}
	
	

}
