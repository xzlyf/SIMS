package com.xz.sims.ui;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.xz.sims.data.Controller;
import com.xz.sims.entity.Student;
import com.xz.sims.entity.Teacher;

public class AddTeachFrame extends JFrame {
	private Student student;
	private JPanel contentPane;
	private JTextField teachName;
	private JTextField classCount;
	private JButton submit;
	private JList list;
	private List<Teacher> listData;

	public AddTeachFrame(Student stu) {
		this.student = stu;
		setVisible(true);
		setTitle("绑定班级");
		setResizable(false);
		setBounds(100, 100, 356, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("所有班级");
		lblNewLabel.setBounds(45, 27, 54, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("班主任");
		lblNewLabel_1.setBounds(164, 53, 54, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("班级人数");
		lblNewLabel_2.setBounds(164, 100, 54, 15);
		contentPane.add(lblNewLabel_2);

		list = new JList();
		JScrollPane sp1 = new JScrollPane(list);
		sp1.setBounds(10, 51, 128, 200);
		contentPane.add(sp1);

		teachName = new JTextField();
		teachName.setEditable(false);
		teachName.setBounds(223, 50, 88, 21);
		contentPane.add(teachName);
		teachName.setColumns(10);

		classCount = new JTextField();
		classCount.setEditable(false);
		classCount.setBounds(223, 96, 88, 21);
		contentPane.add(classCount);
		classCount.setColumns(10);

		submit = new JButton("绑定班级");
		submit.setBounds(168, 230, 143, 21);
		contentPane.add(submit);

		setListData();
		listener();
		setLocationRelativeTo(null);

	}

	private void setListData() {
		listData = Controller.findAllTeach();
		if (listData == null) {
			return;
		}
		list.setModel(new AbstractListModel() {
			@Override
			public int getSize() {
				return listData.size();
			}

			@Override
			public Object getElementAt(int index) {
				return listData.get(index).getClassName();
			}
		});

	}

	private void listener() {
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				teachName.setText(listData.get(list.getSelectedIndex()).getName());
				classCount.setText(Controller.getClassCount(listData.get(list.getSelectedIndex()).getUserNo()) + "");
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		submit.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = list.getSelectedIndex();
				if (i == -1) {
					JOptionPane.showMessageDialog(contentPane, "请先选择班级");
					return;
				}
				Controller.bindTeacher(student, listData.get(i).getUserNo());
				JOptionPane.showMessageDialog(null, "绑定成功,重新登录将生效");
				dispose();
			}
		});
	}

}
