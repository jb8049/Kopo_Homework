package homework_0525;

import java.util.Random;

public abstract class  New_Thread extends Thread {
	
	Random random = new Random();
	int randomValue;
	int money_sum = 0;
	
	public abstract void run();
	
	public int CallCenter(int num) {
		for(int i=0; i < 10 ; i++) {
			randomValue=(int)((Math.random()*10)+1)*1000;
			System.out.println(num +"번 콜센터 : " + randomValue + "원을 받았습니다." );
			money_sum += randomValue;
		}
		return money_sum;
	};
	
	
	
}
