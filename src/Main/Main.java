package Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import GUI.ComboBoxEditor;
import GUI.ComboBoxGUI;
import GUI.StyledButtonUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;
	
	private Thread thread;
	
	Random random = new Random();
	
	Color theme = new Color(random.nextInt(170),random.nextInt(170),random.nextInt(170));
	
	JPanel pa = new JPanel();
	
	JButton ON = new JButton("냉방");
	JButton OFF = new JButton("끄기");
	JButton Connect = new JButton("연결");
	JButton Disconnect = new JButton("끊기");
	JButton Exit = new JButton("종료");
	
	JLabel title = new JLabel("에어컨 제어 시스템");
	JLabel logTitle = new JLabel("Log");	
	
	JLabel temp1 = new JLabel("온도 1 : ");
	JLabel humidity1 = new JLabel("습도 1 : ");
	JLabel temp2 = new JLabel("온도 2 : ");
	JLabel humidity2 = new JLabel("습도 2 : ");
	
	JLabel tempvalue1 = new JLabel("0");
	JLabel humidityvalue1 = new JLabel("0");
	JLabel tempvalue2 = new JLabel("0");
	JLabel humidityvalue2 = new JLabel("0");
	
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
		pa.setLayout(null);
		
		Exit.setUI(new StyledButtonUI(theme));
		ON.setUI(new StyledButtonUI(theme));
		OFF.setUI(new StyledButtonUI(theme));
		Connect.setUI(new StyledButtonUI(theme));
		Disconnect.setUI(new StyledButtonUI(theme));
		
		Font defaultfont = new Font("맑은 고딕",Font.PLAIN,13);
		
		Exit.setFont(defaultfont);
		ON.setFont(defaultfont);
		OFF.setFont(defaultfont);
		Connect.setFont(defaultfont);
		Disconnect.setFont(defaultfont);
		temp1.setFont(defaultfont);
		humidity1.setFont(defaultfont);
		temp2.setFont(defaultfont);
		humidity2.setFont(defaultfont);
		tempvalue1.setFont(defaultfont);
		humidityvalue1.setFont(defaultfont);
		tempvalue2.setFont(defaultfont);
		humidityvalue2.setFont(defaultfont);
		logscroll.setFont(defaultfont);
		log.setFont(defaultfont);
		logTitle.setFont(defaultfont);
		
		title.setBounds(85,10,260,30);
		ON.setBounds(20,90,70,30);
		OFF.setBounds(110,90,70,30);
		Connect.setBounds(110,50,70,30);
		Disconnect.setBounds(200, 50, 70, 30);
		logTitle.setBounds(20,120,340,30);
		log.setBounds(20,150,355,140);
		logscroll.setBounds(20,150,355,140);
		temp1.setBounds(285,45,55,30);
		humidity1.setBounds(285,65,55,30);
		temp2.setBounds(285,85,55,30);
		humidity2.setBounds(285,105,55,30);
		tempvalue1.setBounds(335,45,70,30);
		humidityvalue1.setBounds(335,65,70,30);
		tempvalue2.setBounds(335,85,70,30);
		humidityvalue2.setBounds(335,105,70,30);
		Exit.setBounds(200, 90, 70, 30);

		func = new Func("COM1");
		
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}

		status();
		
		title.setFont(new Font("맑은 고딕",Font.PLAIN,25));
		
		JComboBox<Object> select = new JComboBox<Object>(func.portNames);
		select.setRenderer(new ComboBoxGUI());
		select.setEditor(new ComboBoxEditor());
		select.setBounds(20,58,70,23);
		select.setEditable(true);
		
		ActionListener Onlisten = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				func.Cold();
				status();			
			}
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
				func.Command1 = new StringBuffer();
				func.Command2 = new StringBuffer();
				func.CommandByte = new StringBuffer();
				status();
			}
		}; 
		ActionListener Selectlisten = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox temp = (JComboBox) e.getSource();
				System.out.println(func.MySerialPort.length);
				func.changePort(temp.getSelectedIndex());
				System.out.println(temp.getSelectedIndex());
				func.Command1 = new StringBuffer();
				func.Command2 = new StringBuffer();
				func.CommandByte = new StringBuffer();
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
				
		logscroll.setBorder(new LineBorder(theme,1));
		log.setEditable(false);
		
		pa.add(select);
		pa.add(ON);
		pa.add(Connect);
		pa.add(Disconnect);
		pa.add(OFF);
		pa.add(title);
		pa.add(logTitle);
		pa.add(logscroll);
		pa.add(temp1);
		pa.add(humidity1);
		pa.add(tempvalue1);
		pa.add(humidityvalue1);
		pa.add(temp2);
		pa.add(humidity2);
		pa.add(tempvalue2);
		pa.add(humidityvalue2);
		pa.add(Exit);
		
		add(pa);
		
		setSize(415,350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("에어컨 제어 시스템");
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
	public void setvalue(String command, int number) {

		int a = command.lastIndexOf("(");
		int b = command.lastIndexOf(")");
		command = command.substring(a+1,b);
		String[] list = command.split(",");

		if(list.length==3) {
			if(number ==1) {
				tempvalue1.setText(list[1]+" °C");
				humidityvalue1.setText(list[2]+" %");
				func.Log = "Data updated from TH1 : Temp : "+list[1]+" Humidity : "+list[2];
			}else if(number ==2) {
				tempvalue2.setText(list[1]+" °C");
				humidityvalue2.setText(list[2]+" %");
				func.Log = "Data updated from TH2 : Temp : "+list[1]+" Humidity : "+list[2];
			}
		}else {
			func.Log = "Data Wrong!";
		}
		status();
		func.gotcha = false;
	}

	@Override
	public void run() {
		while(true) {
			
			if(func.gotcha) {
				if(func.Command1 != null) {
					if(func.Command1.indexOf("TH1")!=-1) {
						System.out.print(func.Command1.toString());
						setvalue(func.Command1.toString(),1);
					}
				}else {
					return;
				}
				
				if(func.Command2 != null) {
					if(func.Command2.indexOf("TH2")!=-1) {			
						System.out.print(func.Command2.toString());
						setvalue(func.Command2.toString(),2);
					}
				}else  {
					return;
				}
				func.Command1 = new StringBuffer();
				func.Command2 = new StringBuffer();
				func.CommandByte = new StringBuffer();
			}
			
			try {
				Thread.sleep(1);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
}