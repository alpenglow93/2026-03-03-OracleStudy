package com.sist.user;

import java.util.List;

import com.sist.dao.*;
import com.sist.vo.FoodVO;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FoodDAO dao = FoodDAO.newInstance();
		List<FoodVO> list = dao.foodFindData("양식");
		for(FoodVO vo:list)
		{
			System.out.println("맛집 번호: "+vo.getNo());
			System.out.println("업체명: "+vo.getName());
			System.out.println("음식 종류: "+vo.getType());
			System.out.println("주소: "+vo.getAddress());
			System.out.println("전화번호: "+vo.getPhone());
			System.out.println();
		}

	}

}
