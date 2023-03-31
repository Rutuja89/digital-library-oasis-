


 
	import java.awt.EventQueue;
	 
	import javax.swing.JFrame;
	import java.awt.Font;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.border.TitledBorder;
	import javax.swing.table.TableModel;
	import javax.swing.border.LineBorder;
	import java.awt.Color;
	import javax.swing.border.EmptyBorder;
	import javax.swing.border.EtchedBorder;
	import javax.swing.JTextField;
	import javax.swing.JButton;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import java.sql.*;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import javax.swing.JRadioButton;
	public class newest {
	 
		private JFrame frmCurdOperationSwing;
		private JTextField txtID;
		private JTextField txtstudentName;
		private JTextField txtbookname;
		
		private JTable table;
	 
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						newest window = new newest();
						window.frmCurdOperationSwing.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	 
		/**
		 * Create the application.
		 */
		public newest() {
			initialize();
			 Connect();
			 loadData();
		}
	 
	 
		//Database Connection
		Connection con = null;
		PreparedStatement pst;
		ResultSet rs;
	 
		public void Connect() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/com";
				String username = "root";
				String password = "1234";
				con = DriverManager.getConnection(url, username, password);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	 
		// Clear All
			public void clear() {
				txtID.setText("");
				txtstudentName.setText("");
				txtbookname.setText("");
				
				txtstudentName.requestFocus();
			}
	 
			// Load Table
			public void loadData() {
				try {
					pst = con.prepareStatement("Select * from one");
					rs = pst.executeQuery();
					table.setModel(DbUtil.resultSetToTableModel(rs));
	 
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
	 
	 
		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize() {
			frmCurdOperationSwing = new JFrame();
			frmCurdOperationSwing.getContentPane().setBackground(Color.CYAN);
			frmCurdOperationSwing.setBackground(Color.CYAN);
			frmCurdOperationSwing.setTitle("              "+"CURD Operation Swing MySQL");
			frmCurdOperationSwing.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
			frmCurdOperationSwing.getContentPane().setLayout(null);
	 
			JLabel lblNewLabel = new JLabel("LIBRARY MANAGEMENT SYSTEM");
			lblNewLabel.setForeground(Color.RED);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewLabel.setBounds(10,11,300,60);//10, 11, 259, 60
			frmCurdOperationSwing.getContentPane().add(lblNewLabel);
	 
			JPanel panel = new JPanel();
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel.setBounds(20, 71, 387, 284);
			frmCurdOperationSwing.getContentPane().add(panel);
			panel.setLayout(null);
	 
			JLabel lblNewLabel_1 = new JLabel("Id");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_1.setBounds(21, 46, 46, 24);
			panel.add(lblNewLabel_1);
	 
			JLabel lblNewLabel_1_1 = new JLabel("studentName");
			lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_1_1.setBounds(21, 81, 46, 24);
			panel.add(lblNewLabel_1_1);
	 
			JLabel lblNewLabel_1_2 = new JLabel("bookname");
			lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_1_2.setBounds(21, 116, 46, 24);
			panel.add(lblNewLabel_1_2);
	 
			
	 
			txtID = new JTextField();
			//txtID.setEditable(false);
			txtID.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtID.setBounds(78, 46, 287, 24);
			panel.add(txtID);
			txtID.setColumns(10);
	 
			txtstudentName = new JTextField();
			txtstudentName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtstudentName.setColumns(10);
			txtstudentName.setBounds(78, 81, 287, 24);
			panel.add(txtstudentName);
	 
			txtbookname = new JTextField();
			txtbookname.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtbookname.setColumns(10);
			txtbookname.setBounds(78, 120, 287, 24);
			panel.add(txtbookname);
	 
			
	 
			JButton btnSave = new JButton("ISSUE BOOK");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String ID = txtID.getText();
					String studentname = txtstudentName.getText();
					String bookname= txtbookname.getText();
				
					if (ID == null || ID.isEmpty() || ID.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please Enter id");
						txtID.requestFocus();
						return;
					}
					if (studentname == null || studentname.isEmpty() || studentname.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please Enter studentName");
						txtstudentName.requestFocus();
						return;
					}
					if (bookname == null ||bookname.isEmpty() || bookname.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please Enter bookname");
						txtbookname.requestFocus();
						return;
					}
				
	 
					
						try {
							String sql = "insert into one (ID,studentNAME,bookname) values (?,?,?)";
							pst = con.prepareStatement(sql);
							pst.setString(1,  ID);
							pst.setString(2, studentname);
							pst.setString(3, bookname);
						
							pst.executeUpdate();
							JOptionPane.showMessageDialog(null, "Data insert Success");
							clear();
							loadData();
	 
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
	 
	 
				}
			);
			btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnSave.setBounds(20, 195, 125, 23);
			panel.add(btnSave);
	 
			JButton btnUpdate = new JButton("Update");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Update Details
					String ID = txtID.getText();
					String studentname = txtstudentName.getText();
					String bookname = txtbookname.getText();
					
	 
					if (studentname == null || studentname.isEmpty() || studentname.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please Enter studentname");
						txtstudentName.requestFocus();
						return;
					}
					if (bookname == null || bookname.isEmpty() || bookname.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please Enter bookname");
						txtbookname.requestFocus();
						return;
					}
					
	 
					if (!txtID.getText().isEmpty()) {
						try {
							String sql = "update one set studentNAME=?,bookname=? where ID=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, studentname);
							pst.setString(2, bookname);
							
							pst.setString(3, ID);
							pst.executeUpdate();
							JOptionPane.showMessageDialog(null, "Data Update Success");
							clear();
							loadData();
	 
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
			btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnUpdate.setBounds(150, 195, 88, 23);
			panel.add(btnUpdate);
	 
			JButton btnDelete = new JButton("RETURN BOOK");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
	 
					// Delete Details
					String ID = txtID.getText();
					if (!txtID.getText().isEmpty()) {
	 
						int result = JOptionPane.showConfirmDialog(null, "Sure? You want to Delete?", "Delete",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	 
						if (result == JOptionPane.YES_OPTION) {
							try {
								String sql = "delete from one where ID=?";
								pst = con.prepareStatement(sql);
								pst.setString(1, ID);
								pst.executeUpdate();
								JOptionPane.showMessageDialog(null, "Data Deleted Success");
								clear();
								loadData();
	 
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}
	 
				}
			}
			);
			
			btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnDelete.setBounds(250, 195, 125, 23);
			panel.add(btnDelete);
	 
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(417, 71, 467, 284);
			frmCurdOperationSwing.getContentPane().add(scrollPane);
	 
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
	 
					int index = table.getSelectedRow();
					TableModel model = table.getModel();
					// ID NAME AGE CITY
					txtID.setText(model.getValueAt(index, 0).toString());
					txtstudentName.setText(model.getValueAt(index, 1).toString());
					txtbookname.setText(model.getValueAt(index, 2).toString());
					
				}
			});
			table.setFont(new Font("Tahoma", Font.PLAIN, 14));
			table.setRowHeight(30);
			scrollPane.setViewportView(table);
			frmCurdOperationSwing.setBounds(100, 100, 910, 522);
			frmCurdOperationSwing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}}
