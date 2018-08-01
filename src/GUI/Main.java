package GUI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;
	
	private Thread thread;
	
	JPanel pa = new JPanel();
	
	JButton ON = new JButton("냉방");
	JButton OFF = new JButton("끄기");
	JButton Connect = new JButton("연결");
	JButton Disconnect = new JButton("끊기");
	
	JLabel title = new JLabel("에어컨 제어 시스템");
	JLabel temp = new JLabel("온도 : ");
	JLabel humidity = new JLabel("습도 : ");
	JLabel logTitle = new JLabel("Log");
	
	JLabel tempvalue = new JLabel("0");
	JLabel humidityvalue = new JLabel("0");
	
	JTextArea log = new JTextArea();
	JScrollPane logscroll = new JScrollPane(log);
	
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
	
	public Main() {
		
		pa.setLayout(null);
		title.setBounds(85,10,260,30);
		ON.setBounds(20,90,120,30);
		OFF.setBounds(150,90,120,30);
		Connect.setBounds(110,50,70,30);
		Disconnect.setBounds(200, 50, 70, 30);
		logTitle.setBounds(20,120,340,30);
		log.setBounds(20,150,340,140);
		logscroll.setBounds(20,150,340,140);
		temp.setBounds(290,45,50,30);
		humidity.setBounds(290,65,50,30);
		tempvalue.setBounds(330,45,70,30);
		humidityvalue.setBounds(330,65,70,30);
		
		func = new Func("COM1");
		
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}

		status();
		
		title.setFont(new Font("맑은 고딕",Font.PLAIN,25));
		
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
				func.test2 = "";
				func.test = "";
				status();
			}
		}; 
		ActionListener Selectlisten = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox temp = (JComboBox<?>)e.getSource();
				func.changePort(temp.getSelectedIndex());
				func.test2 = "";
				func.test = "";
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
		pa.add(logscroll);
		pa.add(temp);
		pa.add(humidity);
		pa.add(tempvalue);
		pa.add(humidityvalue);
		
		add(pa);
		
		setSize(400,350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("에어컨 제어 시스템");
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
	public void setvalue(String command) {

		int a = command.lastIndexOf("(");
		command = command.substring(a+1,command.length()-1);
		
		String[] list = command.split(",");
		func.Log = "Data updated";
		status();
		if(list.length==3) {
			tempvalue.setText(list[1]+" °C");
			humidityvalue.setText(list[2]);
		}
	}

	@Override
	public void run() {
		while(true) {
			if(func.test2 != null) {
				if(func.test2.contains("(TH")) {
					System.out.print(func.test2);
					setvalue(func.test2);
				}
			}
			
			try {
				Thread.sleep(5000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
}