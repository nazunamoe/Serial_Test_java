package GUI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Test extends JFrame{
	Func func = new Func();
	JPanel pa = new JPanel();
	JButton ON = new JButton("�ù�");
	JButton OFF = new JButton("����");
	public Test() {
		
		pa.setLayout(null);
		ON.setBounds(20,60,70,50);
		OFF.setBounds(110,60,70,50);
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
		
		ON.addActionListener(Onlisten);
		OFF.addActionListener(Offlisten);
		pa.add(ON);
		
		pa.add(OFF);
		add(pa);
		
		setSize(220,170);
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