package com.sist.board;
import java.awt.Font;
import java.util.*;
import javax.swing.*;
public class BoardDelete extends JPanel{
	JLabel titleLa,la;
	JPasswordField pf;
	JButton b1,b2;
	public BoardDelete()
	{
		 titleLa=new JLabel("삭제하기",JLabel.CENTER);// <table>
    	 titleLa.setFont(new Font("맑은 고딕",Font.BOLD,30)); //<h3></h3>
    	 setLayout(null);
   	     titleLa.setBounds(10, 15, 620, 50);
   	     add(titleLa);
   	     
   	     la=new JLabel("비밀번호");
   	     pf=new JPasswordField();
   	     b1=new JButton("삭제");
   	     b2=new JButton("취소");
   	     
   	     // 배치 
   	     la.setBounds(230, 75, 80, 30);
   	     pf.setBounds(315, 75, 150, 30);
   	     
   	     JPanel p=new JPanel();
   	     p.add(b1);p.add(b2);
   	     
   	     p.setBounds(230, 115, 235, 35);
   	     add(p);
   	     
   	     add(la);
   	     add(pf);
	}
}
