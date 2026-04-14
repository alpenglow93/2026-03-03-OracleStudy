package com.sist.dao;
// 사용자 요청 => 오라클 연결 후 처리
/*
 * 	데이터베이스는 읽어오는 단위 ROW
 * 	
 */
import java.sql.*;
import java.util.*;

import com.sist.vo.FoodVO;

// DAO => Service => 브라우저
public class FoodDAO 
{
	// 오라클 => 필요한 객체
	// 연결 객체
	private Connection conn;
	// 송수신 : SQL전송 => 데이터 받기
	private PreparedStatement ps;	
	// 한 사람당 1개의 Connection 사용
	private static FoodDAO dao;	// 싱글턴
	// 오라클 주소
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	// thin => 드라이버 이름: 연결만 해주는 드라이버 (무료)
	
	// 드라이버 설정 => 한 번만 설정
	public FoodDAO()
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	// 싱글턴 => 100%
	public static FoodDAO newInstance()
	{
		if(dao==null)
			dao = new FoodDAO();

		return dao;
	}
	
	// 오라클 연결
	public void getConnection()
	{
		try 
		{
			conn = DriverManager.getConnection(URL,"hr","happy");
			// conn hr/happy => 오라클로 명령 전송
			
		} catch (Exception ex) {}
	}
	
	// 오라클 닫기
	public void disConnection()
	{
		try 
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			// 오라클 => exit
			
		} catch (Exception ex) {}
	}
	
	//-------------------------------- 공통
	/*
	 *		foodInsert(...)	
	 *		public void foodInsert(
	 *		int no,
	 *		String name, type, phone,address,parking,time,content,theme,
	 *		double score,
	 *) 
	 * 
	 */
	
		
	public void foodInsert(FoodVO vo)
	{
		try 
		{
			// 1. 연결
			getConnection();
			// 2. SQL 문장
			String sql = "INSERT INTO food VALUES("
					+ "?,?,?,?,?,?,?,?,?,?,?,?)";	
			ps=conn.prepareStatement(sql);	// sql문장을 오라클 전송
			// 실행 전에 ?에 값을 채운다
			ps.setInt(1, vo.getNo());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getType());
			ps.setString(4, vo.getPhone());
			ps.setString(5, vo.getAddress());
			ps.setDouble(6, vo.getScore());
			ps.setString(7, vo.getParking());
			ps.setString(8, vo.getPoster());
			ps.setString(9, vo.getTime());
			ps.setString(10, vo.getContent());
			ps.setString(11, vo.getTheme());
			ps.setString(12, vo.getPrice());
			
			// 실행
			ps.executeUpdate();	// commit
			
		} catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		
	}
	
	// 검색 => 한식 / 중식 / 분식 / 양식 / 일식
	public List<FoodVO> foodFindData(String type)
	{
		List<FoodVO> list = new ArrayList<FoodVO>();
		
		try 
		{
			// 연결
			getConnection();
			// SQL문장
			String sql = "SELECT no,name,type,address,phone "
					+ "FROM food "
					//+ "WHERE type LIKE '%'||?||'%' "	// 자바에서는 LIKE 문장 작성시 '%?%' 가 아닌 '%'||?||'%' 로 입력해야한다
					+ "WHERE type LIKE '%"+type+"%'"	// 혹은 이렇게
					+ "ORDER BY no ASC";
			ps = conn.prepareStatement(sql);
			// 실행 전에 ?에 값을 채운다
			//ps.setString(1, type);	// 위 문장에서 ? 안 쓸땐 지우고 ? 쓸땐 쓰고 
			// ? 를 사용할 경우 해당 ?에 어떤 값을 넣을지 알려주는 문장
			// 실행후에 결과값을 가지고 온다
			ResultSet rs = ps.executeQuery();
			while(rs.next())	// rs.previous()
			{
//				System.out.println(
//						rs.getInt(1) + " "+
//						rs.getString(2) + " "+
//						rs.getString(3) + " "+
//						rs.getString(4) + " "+
//						rs.getString(5)
//						);
				FoodVO vo = new FoodVO();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setType(rs.getString(3));
				vo.setAddress(rs.getString(4));
				vo.setPhone(rs.getString(5));
				list.add(vo);
			}
			rs.close();
			
			
		} catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		
		return list;
	}
	
	

}
