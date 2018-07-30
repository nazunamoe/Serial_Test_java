package GUI;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Function.Func;

import javax.swing.JButton;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Test extends JFrame{
	Func func = new Func();
	JPanel pa = new JPanel();
	JButton ON = new JButton("냉방");
	JButton OFF = new JButton("끄기");
	public Test() {
		
		pa.setLayout(null);
		ON.setBounds(20,60,70,50);
		OFF.setBounds(110,60,70,50);
		ActionListener Onlisten = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Func.Cold();
			}
		};
		ActionListener Offlisten = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Func.Off();
			}
		};
		
		ON.addActionListener(Onlisten);
		OFF.addActionListener(Offlisten);
		pa.add(ON);
		
		pa.add(OFF);
		add(pa);

		
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("에어컨 제어 시스템");
		setVisible(true);
	}
}



public class Main{
	public static void main(String[] args) {
		Test t = new Test();
	}
	
	public void Cold() {
		System.out.println("Cold");
	}
}