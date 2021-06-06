package homework_0525;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.JDBCClose;

public class QUIZ1 {
	public static void main(String[] args) {
		
		
		
		Connection conn =null;
		PreparedStatement pstmt = null;
		
		Scanner sc = new Scanner(System.in);
		
		
		try {
			
			// 1. 드라이버 로딩, 2. DB 연결
			
			ConnectionFactory factory = new ConnectionFactory();
			conn = factory.getConnection();
			
			// 3. SQL을 실행할 객체 생성
			
			
			System.out.println("급여를 입력하세요 : ");
			int sal = sc.nextInt();
			sc.nextLine();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT JOB_TITLE, AVG(SALARY) as AVG_SAL ");
			sql.append(" FROM EMPLOYEES E, JOBS J "); 
			sql.append(" WHERE E.JOB_ID = J.JOB_ID AND SALARY >= ? ");
			sql.append(" GROUP BY JOB_TITLE ");
			sql.append(" ORDER BY AVG_SAL DESC ");
			
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1,sal);
			
			ResultSet rs = pstmt.executeQuery();
			
			int cnt=0;
			
			while(rs.next()) { // boolean 값을 반환하는 rs.next() 이게 true(값이 존재할 때 까지)
				String JOB_TITLE = rs.getString("JOB_TITLE");
				String AVG_SAL = rs.getString("AVG_SAL");
				System.out.println("["+ JOB_TITLE +"] " + AVG_SAL);
				cnt ++;
			}
			
			System.out.println("총 " + "["+cnt +"]" + "명이 검색되었습니다.");
			

			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
		
	}
}
