package GUI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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
	JButton Exit = new JButton("종료");
	
	JLabel title = new JLabel("에어컨 제어 시스템");
	JLabel temp = new JLabel("온도 : ");
	JLabel humidity = new JLabel("습도 : ");
	JLabel logTitle = new JLabel("Log");
	
	JLabel tempvalue = new JLabel("0");
	JLabel humidityvalue = new JLabel("0");
	
	JTextArea log = new JTextArea();
	JScrollPane logscroll = new JScrollPane(log);
	JScrollBar logscrollbar = logscroll.getVerticalScrollBar();
	
	private String logmessage ="";
	
	private int pos = 0;
	
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
		pos = log.getText().length();
		log.setCaretPosition(pos);
		log.requestFocus();
	}
	
	public Main() {
		
		try {
	        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		pa.setLayout(null);
		
		Exit.setUI(new StyledButtonUI());
		ON.setUI(new StyledButtonUI());
		OFF.setUI(new StyledButtonUI());
		Connect.setUI(new StyledButtonUI());
		Disconnect.setUI(new StyledButtonUI());
		
		Font defaultfont = new Font("맑은 고딕",Font.PLAIN,13);
		
		Exit.setFont(defaultfont);
		ON.setFont(defaultfont);
		OFF.setFont(defaultfont);
		Connect.setFont(defaultfont);
		Disconnect.setFont(defaultfont);
		temp.setFont(defaultfont);
		humidity.setFont(defaultfont);
		tempvalue.setFont(defaultfont);
		humidityvalue.setFont(defaultfont);
		logscroll.setFont(defaultfont);
		log.setFont(defaultfont);
		logTitle.setFont(defaultfont);
		
		title.setBounds(85,10,260,30);
		ON.setBounds(20,90,120,30);
		OFF.setBounds(150,90,120,30);
		Connect.setBounds(110,50,70,30);
		Disconnect.setBounds(200, 50, 70, 30);
		logTitle.setBounds(20,120,340,30);
		log.setBounds(20,150,340,140);
		logscroll.setBounds(20,150,340,140);
		temp.setBounds(285,40,50,30);
		humidity.setBounds(285,60,50,30);
		tempvalue.setBounds(325,40,70,30);
		humidityvalue.setBounds(325,60,70,30);
		Exit.setBounds(280, 90, 80, 30);

		func = new Func("COM1");
		
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}

		status();
		
		title.setFont(new Font("맑은 고딕",Font.PLAIN,25));
		
		JComboBox<Object> select = new JComboBox<Object>(func.portNames);
		select.setBounds(20,50,70,30);
		select.setFont(defaultfont);
		
		ActionListener Onlisten = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				func.Cold();
				status();			}
		};
		ActionListener Offlisten = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				func.Off();
				status();		
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
		ActionListener Exitlisten = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}; 
		
		select.addActionListener(Selectlisten);
		ON.addActionListener(Onlisten);
		OFF.addActionListener(Offlisten);
		Connect.addActionListener(Connectlisten);
		Disconnect.addActionListener(Disconnectlisten);
		Exit.addActionListener(Exitlisten);
				
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
		pa.add(Exit);
		
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

		if(list.length==3) {
			tempvalue.setText(list[1]+" °C");
			humidityvalue.setText(list[2]+" %");
			func.Log = "Data updated : Temp : "+list[1]+" Humidity : "+list[2];
		}else {
			func.Log = "Data Wrong!";
		}
		status();
	}

	@Override
	public void run() {
		while(true) {
			if(func.test2 != null) {
				if(func.test2.contains("(TH")) {
					System.out.print(func.test2);
					setvalue(func.test2);
				}
				else{
					if(func.test2 != "") {
						func.Log = "Data Wrong! : "+func.test2;
						status();
					}
				}
			}else if(func.test2 == "") {
				return;
			}
			
			try {
				Thread.sleep(5000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
}