package homework_0525;

public class Thread_QUIZ {

	static int all_sum;

	public static void main(String[] args) {

		First first = new First();
		Second second = new Second();
		Third third = new Third();
		Fourth fourth = new Fourth();

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
		System.out.println("국민들이 보내주신 성금 총 액 : " + all_sum + "원");
	}
}


class First extends New_Thread {

	@Override
	public void run() {
		money_sum = CallCenter(1); // 1번 콜센터의 성금 총액
		System.out.println("1번 콜센터 성금 총 액: " + money_sum + "원");
		Thread_QUIZ.all_sum += money_sum;
	}
}

class Second extends New_Thread {

	@Override
	public void run() {
		money_sum = CallCenter(2);
		System.out.println("2번 콜센터 성금 총 액: " + money_sum + "원");
		Thread_QUIZ.all_sum += money_sum;
	}
}

class Third extends New_Thread {

	@Override
	public void run() {
		money_sum = CallCenter(3);
		System.out.println("3번 콜센터 성금 총 액: " + money_sum + "원");
		Thread_QUIZ.all_sum += money_sum;

	}
}

class Fourth extends New_Thread {

	@Override
	public void run() {
		money_sum = CallCenter(4);
		System.out.println("4번 콜센터 성금 총 액: " + money_sum + "원");
		Thread_QUIZ.all_sum += money_sum;

	}
}
