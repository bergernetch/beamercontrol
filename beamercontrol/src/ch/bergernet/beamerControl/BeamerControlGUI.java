package ch.bergernet.beamerControl;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import ch.bergernet.plugins.infocus.InfocusControlPlugin;

public class BeamerControlGUI {

	private JFrame frame;
	private JButton btnConnect = new JButton();
	private JLabel lblURL = new JLabel();
	private JTextField fieldURL = new JTextField();

	private BeamerControlPlugin control;

	/**
	 * Initialize the contents of the frame
	 */
	private void createContents() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = frame.getContentPane();
		SpringLayout layout = new SpringLayout();
		container.setLayout(layout);

		btnConnect.setText("Connect");
		btnConnect.setSize(100, 20);

		lblURL.setText("URL");
		lblURL.setSize(50, 20);

		fieldURL.setText("tcp://localhost:8888");
		fieldURL.setSize(150, 20);

		ActionListener btnConnectActionListener = new AnActionListener();
		btnConnect.addActionListener(btnConnectActionListener);

		container.add(lblURL);
		container.add(fieldURL);
		container.add(btnConnect);

		// Adjust constraints for the label so it's at (5,5).
		layout.putConstraint(SpringLayout.WEST, lblURL, 5, SpringLayout.WEST,
				container);

		layout.putConstraint(SpringLayout.NORTH, lblURL, 5, SpringLayout.NORTH,
				container);

		// Adjust constraints for the text field so it's at
		// (<label's right edge> + 5, 5).
		layout.putConstraint(SpringLayout.WEST, fieldURL, 5, SpringLayout.EAST,
				lblURL);

		layout.putConstraint(SpringLayout.WEST, btnConnect, 5,
				SpringLayout.EAST, fieldURL);

		layout.putConstraint(SpringLayout.NORTH, fieldURL, 5,
				SpringLayout.NORTH, container);

		layout.putConstraint(SpringLayout.NORTH, btnConnect, 5,
				SpringLayout.NORTH, container);

		// frame.pack();
		frame.setVisible(true);
	}

	class AnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			String url = fieldURL.getText();
			System.out.println("connection url: " + url);
			control.connect(url);
			System.out.println("connection state: "
					+ control.getConnectionState().isConnected());
		}
	}

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BeamerControlGUI window = new BeamerControlGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application
	 */
	public BeamerControlGUI() {
		createContents();
		control = new InfocusControlPlugin();
	}
}
