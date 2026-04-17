package com.sist.board;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BoardMain extends JFrame
{
	private CardLayout card = new CardLayout();
	BoardList bList = new BoardList();
	public BoardMain()
	{
		setLayout(card);
		add("bList", bList);
		setSize(640, 550);
		bList.print();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BoardMain();
	}
}
