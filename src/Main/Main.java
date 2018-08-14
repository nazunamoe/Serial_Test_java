package Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.component.FlatScrollPane;
import com.mommoo.flat.frame.FlatFrame;
import com.mommoo.flat.frame.listener.OnExitListener;
import com.mommoo.flat.layout.linear.LinearLayout;
import com.mommoo.flat.layout.linear.Orientation;
import com.mommoo.flat.text.label.FlatLabel;
import com.mommoo.flat.text.textarea.FlatTextArea;

import GUI.ComboBoxEditor;
import GUI.ComboBoxGUI;
import GUI.StyledButtonUI;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Random;

public class Main extends JFrame implements Runnable, ComponentListener{

	private static final long serialVersionUID = 1L;
	
	private Thread thread;
	
	Random random = new Random();
	
	Color theme = new Color(151,151,255);
	
	JPanel pa = new JPanel();
	
	double ss = 2.5;
	
	double height = 490*ss;
	double width = 415*ss;

	JButton ON = new JButton("전체 냉방");
	JButton OFF = new JButton("전체 끄기");
	JButton Connect = new JButton("연결");
	JButton Disconnect = new JButton("끊기");

	JLabel sk1_title = new JLabel("SK_1");
	JLabel sk2_title = new JLabel("SK_2");
	JLabel roof_title = new JLabel("ROOF");

	JButton sk1_on = new JButton("냉방");
	JButton sk1_resvoff = new JButton("예약끄기");
	JButton sk1_off = new JButton("전원");
	JButton sk2_on = new JButton("냉방");
	JButton sk2_resvoff = new JButton("예약끄기");
	JButton sk2_off = new JButton("전원");
	JButton roof_on = new JButton("냉방"); 
	JButton roof_off = new JButton("끄기"); 
	JButton roof_heat = new JButton("난방"); 
	
	JLabel title = new JLabel("에어컨 제어 시스템");
	JLabel logTitle = new JLabel("Log");	
	
	JLabel temp1 = new JLabel("온도1 : ");
	JLabel humidity1 = new JLabel("습도1 : ");
	JLabel temp2 = new JLabel("온도2 : ");
	JLabel humidity2 = new JLabel("습도2 : ");
	
	JLabel tempvalue1 = new JLabel("0");
	JLabel humidityvalue1 = new JLabel("0");
	JLabel tempvalue2 = new JLabel("0");
	JLabel humidityvalue2 = new JLabel("0");
	
	FlatTextArea log = new FlatTextArea();
	FlatScrollPane logscroll = new FlatScrollPane(log, theme);
	JScrollBar logscrollbar = logscroll.getVerticalScrollBar();
	
	private String logmessage ="";
	
	private int pos = 0;
	
	Func func;
	
	private int resizefont(int FontSize, int Width, int Height) {
		
		int size = FontSize;
		
		int framesize = Width * Height;
		
		
		
		if(Width < Height) {
			size = (int)(getHeight()*0.05);
		}else {
			size = (int)(getWidth()*0.06);
		}
		
		return size;
	}
	
	private void resize() {
		
		double setWidth;
		double setHeight;
		
		setWidth = getWidth()/width;
		setHeight = getHeight()/height;
		
	}
	
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
		if(getLineNum(logmessage)==50) {
			logmessage = "";
		}
		log.setText(logmessage);
		pos = log.getText().length();
		log.setCaretPosition(pos);
		log.requestFocus();
	}
	
	public int getLineNum(String content){
        int linenum = 0;
        int point = 0;
        int endcheck = content.lastIndexOf ('\n');
        while(true){
            point = content.indexOf('\n', point);
            linenum++;
            if(endcheck == point++) break;
        }
        return linenum;
    }
	
	public Main() {
		
        setSize((int)(415*ss),(int)(500*ss));
		
        getContentPane().addComponentListener(this);
		
		pa.setLayout(null);

		Font defaultfont = new Font("맑은 고딕",Font.PLAIN,(int)(13*ss));
		
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
		
		title.setBounds((int)(85*ss),(int)(10*ss),(int)(260*ss),(int)(30*ss));
		ON.setBounds((int)(20*ss),(int)(90*ss),(int)(115*ss),(int)(30*ss));
		OFF.setBounds((int)(155*ss),(int)(90*ss),(int)(115*ss),(int)(30*ss));
		Connect.setBounds((int)(110*ss),(int)(50*ss),(int)(70*ss),(int)(30*ss));
		Disconnect.setBounds((int)(200*ss),(int) (50*ss),(int) (70*ss),(int) (30*ss));
		logTitle.setBounds((int)(20*ss),(int)(270*ss),(int)(340*ss),(int)(30*ss));
		log.setBounds((int)(20*ss),(int)(300*ss),(int)(355*ss),(int)(120*ss));
		logscroll.setBounds((int)(20*ss),(int)(300*ss),(int)(355*ss),(int)(110*ss));
		temp1.setBounds((int)(285*ss),(int)(45*ss),(int)(55*ss),(int)(30*ss));
		humidity1.setBounds((int)(285*ss),(int)(65*ss),(int)(55*ss),(int)(30*ss));
		temp2.setBounds((int)(285*ss),(int)(85*ss),(int)(55*ss),(int)(30*ss));
		humidity2.setBounds((int)(285*ss),(int)(105*ss),(int)(55*ss),(int)(30*ss));
		tempvalue1.setBounds((int)(335*ss),(int)(45*ss),(int)(70*ss),(int)(30*ss));
		humidityvalue1.setBounds((int)(335*ss),(int)(65*ss),(int)(70*ss),(int)(30*ss));
		tempvalue2.setBounds((int)(335*ss),(int)(85*ss),(int)(70*ss),(int)(30*ss));
		humidityvalue2.setBounds((int)(335*ss),(int)(105*ss),(int)(70*ss),(int)(30*ss));
		
		sk1_title.setBounds((int)(35*ss),(int)(150*ss),(int)(70*ss),(int)(30*ss));
		sk2_title.setBounds((int)(35*ss),(int)(190*ss),(int)(70*ss),(int)(30*ss));
		roof_title.setBounds((int)(35*ss),(int)(230*ss),(int)(70*ss),(int)(30*ss));
		
		sk1_on.setBounds((int)(100*ss),(int)(145*ss),(int)(70*ss),(int)(30*ss));
		sk2_on.setBounds((int)(100*ss),(int)(185*ss),(int)(70*ss),(int)(30*ss));
		roof_on.setBounds((int)(100*ss),(int)(225*ss),(int)(70*ss),(int)(30*ss));
		
		sk1_off.setBounds((int)(190*ss),(int)(145*ss),(int)(70*ss),(int)(30*ss));
		sk2_off.setBounds((int)(190*ss),(int)(185*ss),(int)(70*ss),(int)(30*ss));
		roof_off.setBounds((int)(190*ss),(int)(225*ss),(int)(70*ss),(int)(30*ss));
		
		sk1_resvoff.setBounds((int)(280*ss),(int)(145*ss),(int)(90*ss),(int)(30*ss));
		sk2_resvoff.setBounds((int)(280*ss),(int)(185*ss),(int)(90*ss),(int)(30*ss));
		roof_heat.setBounds((int)(280*ss),(int)(225*ss),(int)(90*ss),(int)(30*ss));
		
		func = new Func("COM1");
		
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}

		status();
		
		title.setFont(new Font("맑은 고딕",Font.PLAIN,(int)(25*ss)));
		
		JComboBox<Object> select = new JComboBox<Object>(func.portNames);
		select.setRenderer(new ComboBoxGUI());
		select.setEditor(new ComboBoxEditor());
		select.setBounds((int)(20*ss),(int)(58*ss),(int)(70*ss),(int)(23*ss));
		select.setRenderer(new FontCellRenderer());
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
		
		ON.setUI(new StyledButtonUI(theme));
		OFF.setUI(new StyledButtonUI(theme));
		Connect.setUI(new StyledButtonUI(theme));
		Disconnect.setUI(new StyledButtonUI(theme));
		sk1_on.setUI(new StyledButtonUI(theme));
		sk1_off.setUI(new StyledButtonUI(theme));
		sk1_resvoff.setUI(new StyledButtonUI(theme));
		sk2_on.setUI(new StyledButtonUI(theme));
		sk2_off.setUI(new StyledButtonUI(theme));
		sk2_resvoff.setUI(new StyledButtonUI(theme));
		roof_on.setUI(new StyledButtonUI(theme));
		roof_off.setUI(new StyledButtonUI(theme));
		roof_heat.setUI(new StyledButtonUI(theme));
				
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
		
		setSize((int)(415*ss),(int)(470*ss));
		setTitle("에어컨 제어 시스템");
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class FontCellRenderer extends DefaultListCellRenderer {
		  public Component getListCellRendererComponent(JList list, Object value,
		      int index, boolean isSelected, boolean cellHasFocus) {
		    JLabel label = (JLabel) super.getListCellRendererComponent(list, value,
		        index, isSelected, cellHasFocus);
		    Font font = new Font((String) value, Font.PLAIN, (int)(20*ss));
		    label.setFont(font);
		    return label;
		  }
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
				tempvalue1.setText(list[1]+" ºC");
				humidityvalue1.setText(list[2]+" %");
				func.Log = "Data updated from TH1 : Temp : "+list[1]+" Humidity : "+list[2];
			}else if(number ==2) {
				tempvalue2.setText(list[1]+" ºC");
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

	    
		//resize();
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
	}
}