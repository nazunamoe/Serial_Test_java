package GUI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Test extends JFrame{

	private static final long serialVersionUID = 1L;
	JPanel pa = new JPanel();
	
	JButton ON = new JButton("�ù�");
	JButton OFF = new JButton("����");
	JButton Connect = new JButton("����");
	JButton Disconnect = new JButton("����");
	
	JLabel title = new JLabel("������ ���� �ý���");
	JLabel logTitle = new JLabel("Log");
	
	JTextArea log = new JTextArea();
	String logmessage ="";
	
	Func func;
	
	private void status() {
		if(func.MySerialPort[func.number].isOpened() == false) {
			ON.setEnabled(false);
			OFF.setEnabled(false);
			Disconnect.setEnabled(false);
			Connect.setEnabled(true);
		}else if(func.MySerialPort[func.number].isOpened() == true) {
			ON.setEnabled(true);
			OFF.setEnabled(true);
			Connect.setEnabled(false);
			Disconnect.setEnabled(true);
		}
		logmessage = logmessage+"\n"+func.Log;
		log.setText(logmessage);
	}
	
	public Test() {
		
		pa.setLayout(null);
		title.setBounds(85,10,260,30);
		ON.setBounds(20,90,120,30);
		OFF.setBounds(150,90,120,30);
		Connect.setBounds(110,50,70,30);
		Disconnect.setBounds(200, 50, 70, 30);
		logTitle.setBounds(20,120,340,30);
		log.setBounds(20,150,340,140);

		func = new Func("COM1");

		status();
		
		title.setFont(new Font("���� ���",Font.PLAIN,25));
		
		JComboBox<Object> select = new JComboBox<Object>(func.portNames);
		select.setBounds(20,50,70,30);
		ActionListener Onlisten = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				func.Cold();
			}
		};
		ActionListener Offlisten = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				func.Off();
			}
		}; 
		ActionListener Connectlisten = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				func.Connect();
				status();
			}
		}; 
		ActionListener Disconnectlisten = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				func.Disconnect();
				status();
			}
		}; 
		ActionListener Selectlisten = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox temp = (JComboBox<?>)e.getSource();
				func.number = temp.getSelectedIndex();
				func.setPort();
				status();
			}
		};
		
		select.addActionListener(Selectlisten);
		ON.addActionListener(Onlisten);
		OFF.addActionListener(Offlisten);
		Connect.addActionListener(Connectlisten);
		Disconnect.addActionListener(Disconnectlisten);
				
		log.setBorder(new LineBorder(Color.LIGHT_GRAY,1));
		log.setEditable(false);
		
		pa.add(select);
		pa.add(ON);
		pa.add(Connect);
		pa.add(Disconnect);
		pa.add(OFF);
		pa.add(title);
		pa.add(logTitle);
		pa.add(log);
		
		add(pa);
		
		setSize(400,350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("������ ���� �ý���");
		setVisible(true);
	}
}

public class Main{
	public static void main(String[] args) {
		Test t = new Test();
	}
}