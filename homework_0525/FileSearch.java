package homework_0525;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class FileSearch {

	Scanner sc = new Scanner(System.in);
	
	private String inputFolderName; // 폴더명 입력받기 (새폴더, 이름변경, 삭제, 하위폴더 이동)
	private String folderPath = "iodata"; //객체 호출했을 때, 최초 경로는 iodata
	
	private File fileObj = new File(folderPath); // iodata 경로 (최상위 경로)에서 시작

	public void printFile() { // fileObj가 가르키는 객체의 정보를 출력함 

		File[] fileList = fileObj.listFiles(); 

		System.out.println("---------------------------------------");
		System.out.println(folderPath + " 폴더 정보");
		System.out.println("---------------------------------------");
		System.out.println("이름  \t종류  \t크기  \t마지막 수정 날짜");
		System.out.println("---------------------------------------");

		for (File files : fileList) {

			System.out.print(files.getName() + "\t");
			System.out.print(files.isDirectory() ? "폴더" + "\t" : "파일" + "\t");
			System.out.print(files.length() + "bytes" + "\t");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 mm월 dd일 hh시 mm분 ss초");
			System.out.println(sdf.format(files.lastModified())); // 마지막 수정 날짜
		}
		System.out.println("---------------------------------------");
		selectMenu();

	}

	public void selectMenu() {
		
	while (true) {	
		System.out.print("[M]새폴더  [R]이름변경  [D]삭제 [.]상위폴더이동 [move]하위폴더 이동 : ");
		String input = sc.nextLine();

		switch (input) {
		case "M":
		case "m":
			makeFile();
			break;
		case "R":
		case "r":
			renameFile();
			break;
		case "D":
		case "d":
			deleteFile();
			break;
		case ".":
			moveup();
		case "move":
			movedown();
		default:
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			;
		}
	}
	}


	public void makeFile() {
		while (true) {
			System.out.print("새롭게 생성할 폴더명을 입력하세요. :");
			inputFolderName = sc.nextLine();
			inputFolderName = "/" + inputFolderName;
			folderPath += inputFolderName;
			fileObj = new File(folderPath); //해당 경로를 가르키는 디렉토리 객체
			if (fileObj.exists()) {
				System.out.println("이미 존재하는 폴더입니다. 다시 입력해주세요.");
			} else {
				fileObj.mkdir();
				System.out.println("폴더 생성 완료");
				folderPath = fileObj.getParent();
				fileObj =new File(folderPath);
				printFile(); // 새로운 폴더 생성 후 화면에 출력
				break;
			}
		}
	}

	public void renameFile() {

		while (true) {
			System.out.print("수정하고 싶은 파일 입력 : ");
			inputFolderName = sc.nextLine();
			inputFolderName = "/" + inputFolderName;
			
			fileObj = new File(folderPath); // inputFolderName의 상위 경로에 inputFolderName이 존재하는지 확인

			if (fileObj.exists()) { // folderPath 현재 경로에 수정하고싶은 파일이 존재하는지 확인

				System.out.print("어떤 이름으로 변경하시겠습니까? ");
				String renameFolderName = sc.nextLine(); // 파일의 새로운 이름
				renameFolderName = "/" + renameFolderName;
				
				fileObj = new File(folderPath + inputFolderName); // move 후 folderPath는 iodata/aaa임, fileObj가 가르켜야 하는 파일은 iodata/aaa/bbb임
				fileObj.renameTo(new File(folderPath + renameFolderName)); // iodata/aaa/bbb를 iodata/aaa/ccc로 변경
				System.out.println("이름 변경 완료");
				fileObj = new File(folderPath);
				printFile();
				break;
			} else {
				System.out.println("입력하신 파일이 존재하지 않습니다.");
			}

		}
	}

	public void deleteFile() {
		while (true) {

			System.out.print("삭제하고 싶은 파일 입력 : ");
			inputFolderName = sc.nextLine();
			inputFolderName = "/" + inputFolderName;
			folderPath += inputFolderName;

			File delObj = new File(folderPath);

			if (delObj.exists()) {
				folderPath =delObj.getParent(); // 삭제한 디렉토리의 상위 디렉토리를 printFile() 하기 위해
				
				delObj.delete(); //하위 디렉토리를 가지는 파일은 삭제되지 않음
				System.out.println("삭제 완료되었습니다.");
				fileObj = new File(folderPath); // 삭제한 뒤 삭제한 폴더의 상위 경로 출력
				printFile();

				break;
			} else {
				System.out.println("입력하신 파일이 존재하지 않습니다.");
			}

		}

	}

	public void movedown() {
		System.out.print("이동하고 싶은 하위 폴더 입력 : ");
		inputFolderName = sc.nextLine();
		folderPath += "/" + inputFolderName;
		fileObj = new File(folderPath);
		printFile();
	}
	
	public void moveup() {

		if (fileObj.getParent() == null) {
			System.out.println("최상위 디렉토리 입니다.");
			printFile();
		} else {
			folderPath = fileObj.getParent(); // 경로를 상위 디렉토리로 변경
			fileObj = new File(folderPath);
			System.out.println("상위 폴더 이동 성공");
			printFile();
		}
	}

}