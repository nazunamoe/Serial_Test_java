package Main;

import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main2 extends JFrame implements Runnable, ComponentListener{
	JPanel Main = new JPanel();
	JPanel Main1 = new JPanel();
	JPanel Main2 = new JPanel();
	JPanel Main3 = new JPanel();
	JPanel Main4 = new JPanel();
	JPanel Main5 = new JPanel();
	JPanel Main6 = new JPanel();
	JPanel Main7 = new JPanel();
	
	JButton test1 = new JButton("test");
	JButton test2 = new JButton("test");
	JButton test3 = new JButton("test");
	JLabel test4 = new JLabel("test");
	JButton test5 = new JButton("test");
	JButton test6 = new JButton("test");
	
	public Main2() {
		
		Main.setLayout(new GridLayout(7,1));

		Main2.add(test1);
		Main2.add(test2);
		Main2.add(test3);
		Main2.add(test4);
		Main2.add(test5);
		Main2.add(test6);
		
		Main2.setLayout(new GridLayout(2,3));
		
		Main.add(Main1);
		Main.add(Main2);
		Main.add(Main3);
		Main.add(Main4);
		Main.add(Main5);
		Main.add(Main6);
		Main.add(Main7);
		
		add(Main);
		setSize(500,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Main2();
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
