package com.sist.user;

import java.util.*;

import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

import java.io.*;

public class FoodManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<FoodVO> fList = new ArrayList<FoodVO>();
		
		try
		   {
			   FileReader fr=new FileReader("c:\\javaDev\\food.txt");
			   int i=0;
			   StringBuffer sb=new StringBuffer();
			   while((i=fr.read())!=-1)
			   {
				   sb.append((char)i); // 파일에 있는 문자열 전체 저장 
			   }
			   fr.close();
			   String[] datas=sb.toString().split("\n");
			   
			   for(String food:datas)
			   {
				   FoodVO f=new FoodVO();
				   StringTokenizer st=new StringTokenizer(food,"|");
				   f.setNo(Integer.parseInt(st.nextToken()));
				   f.setName(st.nextToken());
				   f.setType(st.nextToken());
				   f.setPhone(st.nextToken());
				   f.setAddress(st.nextToken());
				   f.setScore(Double.parseDouble(st.nextToken()));
				   f.setParking(st.nextToken());
				   f.setPoster("http://menupan.com"+st.nextToken());
				   f.setTime(st.nextToken());
				   f.setContent(st.nextToken());
				   f.setTheme(st.nextToken());
				   f.setPrice(st.nextToken());
				   
				   fList.add(f);
			   }
		   }catch(Exception ex) {}
		
		FoodDAO dao = FoodDAO.newInstance();
		int k = 1;
		
		for(FoodVO vo:fList)
		{
			dao.foodInsert(vo);
			System.out.println(k+" ROW 저장");
			k++;
		}

	}

}
