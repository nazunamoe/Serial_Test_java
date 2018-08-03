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
	
	JButton ON = new JButton("ÀüÃ¼ ³Ã¹æ");
	JButton OFF = new JButton("ÀüÃ¼ ²ô±â");
	JButton Connect = new JButton("¿¬°á");
	JButton Disconnect = new JButton("²÷±â");

	JLabel sk1_title = new JLabel("SK_1");
	JLabel sk2_title = new JLabel("SK_2");
	JLabel roof_title = new JLabel("ROOF");

	JButton sk1_on = new JButton("³Ã¹æ");
	JButton sk1_resvoff = new JButton("¿¹¾à²ô±â");
	JButton sk1_off = new JButton("Àü¿ø");
	JButton sk2_on = new JButton("³Ã¹æ");
	JButton sk2_resvoff = new JButton("¿¹¾à²ô±â"); 
	JButton sk2_off = new JButton("Àü¿ø");
	JButton roof_on = new JButton("³Ã¹æ"); 
	JButton roof_off = new JButton("²ô±â"); 
	JButton roof_heat = new JButton("³­¹æ"); 
	
	JLabel title = new JLabel("¿¡¾îÄÁ Á¦¾î ½Ã½ºÅÛ");
	JLabel logTitle = new JLabel("Log");	
	
	JLabel temp1 = new JLabel("¿Âµµ1 : ");
	JLabel humidity1 = new JLabel("½Àµµ1 : ");
	JLabel temp2 = new JLabel("¿Âµµ2 : ");
	JLabel humidity2 = new JLabel("½Àµµ2 : ");
	
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
			
			sk1_on.setEnabled(false);
			sk2_on.setEnabled(false);
			roof_on.setEnabled(false);
			
			sk1_off.setEnabled(false);
			sk2_off.setEnabled(false);
			roof_off.setEnabled(false);
			
			sk1_resvoff.setEnabled(false);
			sk2_resvoff.setEnabled(false);
			roof_heat.setEnabled(false);
			
			Disconnect.setEnabled(false);
			Connect.setEnabled(true);
		}else if(func.MySerialPort[func.number].isOpened() == true) {
			ON.setEnabled(true);
			OFF.setEnabled(true);
			
			sk1_on.setEnabled(true);
			sk2_on.setEnabled(true);
			roof_on.setEnabled(true);
			
			sk1_off.setEnabled(true);
			sk2_off.setEnabled(true);
			roof_off.setEnabled(true);
			
			sk1_resvoff.setEnabled(true);
			sk2_resvoff.setEnabled(true);
			roof_heat.setEnabled(true);
			
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
		
		ON.setUI(new StyledButtonUI(theme));
		OFF.setUI(new StyledButtonUI(theme));
		Connect.setUI(new StyledButtonUI(theme));
		Disconnect.setUI(new StyledButtonUI(theme));
		
		sk1_on.setUI(new StyledButtonUI(theme));
		sk2_on.setUI(new StyledButtonUI(theme));
		roof_on.setUI(new StyledButtonUI(theme));
		
		sk1_off.setUI(new StyledButtonUI(theme));
		sk2_off.setUI(new StyledButtonUI(theme));
		roof_off.setUI(new StyledButtonUI(theme));
		
		sk1_resvoff.setUI(new StyledButtonUI(theme));
		sk2_resvoff.setUI(new StyledButtonUI(theme));
		roof_heat.setUI(new StyledButtonUI(theme));
		
		Font defaultfont = new Font("¸¼Àº °íµñ",Font.PLAIN,13);
		
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
		
		sk1_title.setFont(defaultfont);
		sk2_title.setFont(defaultfont);
		roof_title.setFont(defaultfont);
		
		sk1_on.setFont(defaultfont);
		sk2_on.setFont(defaultfont);
		roof_on.setFont(defaultfont);
		
		sk1_off.setFont(defaultfont);
		sk2_off.setFont(defaultfont);
		roof_off.setFont(defaultfont);
		
		sk1_resvoff.setFont(defaultfont);
		sk2_resvoff.setFont(defaultfont);
		roof_heat.setFont(defaultfont);
		
		title.setBounds(85,10,260,30);
		ON.setBounds(20,90,115,30);
		OFF.setBounds(155,90,115,30);
		Connect.setBounds(110,50,70,30);
		Disconnect.setBounds(200, 50, 70, 30);
		logTitle.setBounds(20,270,340,30);
		log.setBounds(20,300,355,140);
		logscroll.setBounds(20,300,355,140);
		temp1.setBounds(285,45,55,30);
		humidity1.setBounds(285,65,55,30);
		temp2.setBounds(285,85,55,30);
		humidity2.setBounds(285,105,55,30);
		tempvalue1.setBounds(335,45,70,30);
		humidityvalue1.setBounds(335,65,70,30);
		tempvalue2.setBounds(335,85,70,30);
		humidityvalue2.setBounds(335,105,70,30);
		
		sk1_title.setBounds(35,150,70,30);
		sk2_title.setBounds(35,190,70,30);
		roof_title.setBounds(35,230,70,30);
		
		sk1_on.setBounds(100,145,70,30);
		sk2_on.setBounds(100,185,70,30);
		roof_on.setBounds(100,225,70,30);
		
		sk1_off.setBounds(190,145,70,30);
		sk2_off.setBounds(190,185,70,30);
		roof_off.setBounds(190,225,70,30);
		
		sk1_resvoff.setBounds(280,145,90,30);
		sk2_resvoff.setBounds(280,185,90,30);
		roof_heat.setBounds(280,225,90,30);

		func = new Func("COM1");
		
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}

		status();
		
		title.setFont(new Font("¸¼Àº °íµñ",Font.PLAIN,25));
		
		JComboBox<Object> select = new JComboBox<Object>(func.portNames);
		select.setRenderer(new ComboBoxGUI());
		select.setEditor(new ComboBoxEditor());
		select.setBounds(20,58,70,23);
		select.setEditable(true);
		
		ActionListener Onlisten = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				func.Cold(0);
				status();			
			}
		};
		ActionListener Offlisten = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				func.Off(0);
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
		ActionListener sk1_onlisten = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				func.Cold(1);
				status();	
			}
		};
		ActionListener sk1_offlisten = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				func.Off(1);
				status();	
			}
		};
		ActionListener sk1_resvofflisten = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				func.resvOff(1);
				status();	
			}
		};
		ActionListener sk2_onlisten = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				func.Cold(2);
				status();	
			}
		};
		ActionListener sk2_offlisten = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				func.Off(2);
				status();	
			}
		};
		ActionListener sk2_resvofflisten = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				func.resvOff(2);
				status();	
			}
		};
		ActionListener roof_onlisten = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				func.Cold(3);
				status();	
			}
		};
		ActionListener roof_offlisten = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				func.Off(3);
				status();	
			}
		};
		ActionListener roof_heatlisten = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				func.resvOff(3);
				status();	
			}
		};
		
		select.addActionListener(Selectlisten);
		ON.addActionListener(Onlisten);
		OFF.addActionListener(Offlisten);
		Connect.addActionListener(Connectlisten);
		Disconnect.addActionListener(Disconnectlisten);
		
		sk1_on.addActionListener(sk1_onlisten);
		sk1_off.addActionListener(sk1_offlisten);
		sk1_resvoff.addActionListener(sk1_resvofflisten);
		sk2_on.addActionListener(sk2_onlisten);
		sk2_off.addActionListener(sk2_offlisten);
		sk2_resvoff.addActionListener(sk2_resvofflisten);
		roof_on.addActionListener(roof_onlisten);
		roof_off.addActionListener(roof_offlisten);
		roof_heat.addActionListener(roof_heatlisten);
				
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
		
		pa.add(sk1_title);
		pa.add(sk2_title);
		pa.add(roof_title);
		
		pa.add(sk1_on);
		pa.add(sk2_on);
		pa.add(roof_on);
		
		pa.add(sk1_off);
		pa.add(sk2_off);
		pa.add(roof_off);
		
		pa.add(sk1_resvoff);
		pa.add(sk2_resvoff);
		pa.add(roof_heat);
		
		add(pa);
		
		setSize(415,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("¿¡¾îÄÁ Á¦¾î ½Ã½ºÅÛ");
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
				tempvalue1.setText(list[1]+" ¨¬C");
				humidityvalue1.setText(list[2]+" %");
				func.Log = "Data updated from TH1 : Temp : "+list[1]+" Humidity : "+list[2];
			}else if(number ==2) {
				tempvalue2.setText(list[1]+" ¨¬C");
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