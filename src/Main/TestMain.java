package Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JList;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Choice;
import javax.swing.JSeparator;
import java.awt.ScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JComboBox;

public class TestMain extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestMain frame = new TestMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestMain() {
	
		Func func = new Func("COM1");
		getContentPane().setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 27));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 696);
		
		JLabel lblNewLabel = new JLabel("\uC5D0\uC5B4\uCEE8 \uC81C\uC5B4 \uC2DC\uC2A4\uD15C");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 27));
		
		JComboBox comboBox = new JComboBox(func.portNames);
		
		JButton btnNewButton_1 = new JButton("New button");
		
		JButton btnNewButton_2 = new JButton("New button");
		
		JButton btnNewButton_3 = new JButton("New button");
		
		JButton btnNewButton_5 = new JButton("New button");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		getContentPane().add(lblNewLabel);
		
		Panel panel_2 = new Panel();
		getContentPane().add(panel_2);
		
		Panel panel = new Panel();
		getContentPane().add(panel);
		
		Panel panel_1 = new Panel();
		getContentPane().add(panel_1);
		
		JButton btnNewButton = new JButton("\uC885\uB8CC");
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(1);
			}
		});
		
		JButton btnNewButton_4 = new JButton("\uC815\uBCF4");
		getContentPane().add(btnNewButton_4);
		btnNewButton_4.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		btnNewButton.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		getContentPane().add(btnNewButton);
		getContentPane().add(comboBox);
		getContentPane().add(btnNewButton_1);
		getContentPane().add(btnNewButton_2);
		getContentPane().add(btnNewButton_3);
		getContentPane().add(btnNewButton_5);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
