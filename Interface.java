package atmnew;

import java.util.*;
import javax.swing.*;

import javax.swing.JLabel;

import java.awt.*;
import java.awt.event.*;

public class Interface {
	static Scanner input = new Scanner(System.in);
	private static String id;
	private static String remit_id; // �۱� ���� id
	private static int password, channel;
	private static long cash;

	private static JFrame frame = new JFrame("ATM");

	// flag�� ���� ����ڿ��� ���� �����ݴϴ�.
	static long Show(int flag, String id, DataBase db, ATM atm) {

		int one_cnt, five_cnt;
		if (flag == 1) { // �ܾ���ȸ
			String message = String.format("�ܾ� ��ȸ �����Դϴ�.\n�ش� ������ �ܾ��� : %d�� �Դϴ�!\n", db.getBalance(id));
			JOptionPane.showInternalMessageDialog(null, message);
			return -1;
		} else if (flag == 2) { // �Ա�
			while (true) {
				String temp_cash = JOptionPane.showInputDialog("�Ա��� �ݾ��� �Է��ϼ���");
				cash = Long.parseLong(temp_cash);
				String t_one_cnt = JOptionPane.showInputDialog("�Ա��� ����� �� �������� �� ������ �Է��ϼ���");
				one_cnt = Integer.parseInt(t_one_cnt);
				String t_five_cnt = JOptionPane.showInputDialog("�Ա��� ����� �� �� �������� �� ������ �Է��ϼ���");
				five_cnt = Integer.parseInt(t_five_cnt);
				if (cash != ((long) one_cnt * 10000) + ((long) five_cnt * 50000)) {
					JOptionPane.showInternalMessageDialog(null, "�߸��Է��ϼ̽��ϴ�! �ٽ� �Է����ּ���!");
				} else {
					atm.put(one_cnt, five_cnt);
					String message = String.format("�Ա��� �Ϸ�Ǿ����ϴ�.\n�ش� ������ �ܾ��� : %d�� �Դϴ�!\n", db.getBalance(id) + cash);
					JOptionPane.showInternalMessageDialog(null, message);
					break;
				}
			}
			return cash;
		} else if (flag == 3) { // ���
			while (true) {
				String temp_cash = JOptionPane.showInputDialog("����� �ݾ��� �Է��ϼ���");
				cash = Long.parseLong(temp_cash);
				if (cash > db.getBalance(id)) {
					JOptionPane.showInternalMessageDialog(null, "�ܾ��� �����մϴ�! �ٽ� �Է����ּ���!");
					continue;
				}
				String t_one_cnt = JOptionPane.showInputDialog("����� ����� �� �������� �� ������ �Է��ϼ���");
				one_cnt = Integer.parseInt(t_one_cnt);
				String t_five_cnt = JOptionPane.showInputDialog("����� ����� �� �� �������� �� ������ �Է��ϼ���");
				five_cnt = Integer.parseInt(t_five_cnt);
				if (cash != ((long) one_cnt * 10000) + ((long) five_cnt * 50000)) {
					JOptionPane.showInternalMessageDialog(null, "�߸��Է��ϼ̽��ϴ�! �ٽ� �Է����ּ���!");
				} else {
					int cmp_one = atm.getOne();
					int cmp_five = atm.getFive();

					// atm ���� ���� �ǰ� 5���� ���� �󸶳� ���Ҵ����� ���� ��� �������� �Ǵ�
					if (one_cnt <= cmp_one && five_cnt <= cmp_five) {
						atm.put(-one_cnt, -five_cnt);
						String message = String.format("����� �Ϸ�Ǿ����ϴ�.\n�ش� ������ �ܾ��� : %d�� �Դϴ�!\n",
								db.getBalance(id) - cash);
						JOptionPane.showInternalMessageDialog(null, message);
						break;
					} else {
						JOptionPane.showInternalMessageDialog(null, "ATM ����� ���� �����մϴ�! �ٽ� �Է����ּ���!");
					}
				}
			}
			return cash;

		} else if (flag == 4) {
			while (true) {
				long adminpass;
				long addcheck;

				String temp_admin = JOptionPane.showInputDialog("������ ��й�ȣ�� �Է��ϼ���");
				adminpass = Long.parseLong(temp_admin);
				if (adminpass == 1234) {

					JOptionPane.showInternalMessageDialog(null, "������ �޴��Դϴ�.");
					ATM.LogPrint();

					JOptionPane.showInternalMessageDialog(null, "���ʸ� �߰� �Ͻðڽ��ϱ�?");
					String temp_addcheck = JOptionPane.showInputDialog("���� �߰� �Ͻðڽ��ϱ�? yes: 1/ no: 0");
					addcheck = Long.parseLong(temp_addcheck);
					if (addcheck == 1) {
						atm.put(1000, 200);
						JOptionPane.showInternalMessageDialog(null, "���� �߰� �Ǿ����ϴ�.");
					}
					break;
				}
				break;
			}
			return cash;

		}

		else { // �۱�
				// �Է� �ݾ� �� ����� Ȯ��
			while (true) {
				String temp_cash = JOptionPane.showInputDialog("�۱��� �ݾ��� �Է��ϼ���");
				cash = Long.parseLong(temp_cash);
				if (cash > db.getBalance(id)) {
					JOptionPane.showInternalMessageDialog(null, "���� �ܾ��� �����մϴ�! �ٽ� �Է����ּ���!");
				} else
					break;
			}

			return cash;
		}
	}

	// �۱��� ���¸� �������� �����ϴ� �Լ�
	static String to_id(int x) {
		if (x == 0)
			remit_id = JOptionPane.showInputDialog("�۱��� ���¸� �Է����ּ��� : ");
		else
			remit_id = JOptionPane.showInputDialog("�߸��� �۱� �����Դϴ�! �ٽ� �Է����ּ���!");
		return remit_id;
	}

	static void remit_success(DataBase db, String idid) {
		String message = String.format("�۱��� �Ϸ��߽��ϴ�.\n�ش� ������ �ܾ��� : %d�� �Դϴ�!\n", db.getBalance(idid));
		JOptionPane.showInternalMessageDialog(null, message);
	}

	// ���� ȭ�� �����ֱ�
	static String Input_id(int x) {
		if (x == 0)
			id = JOptionPane.showInputDialog("�ݰ����ϴ� ����! ���� ��ȣ�� �Է����ּ���!"); // x == 0 �̶�� ó�� �Է�
		else
			id = JOptionPane.showInputDialog("���¹�ȣ�� �߸� �Է��߽��ϴ�!! �ٽ� �Է����ּ���!"); // x != 0 �̶�� ���Է�
		return id;
	}

	static int Input_pass(int x) {
		String temp_pw;
		if (x == 0)
			temp_pw = JOptionPane.showInputDialog("��й�ȣ�� �Է����ּ���"); // x == 0 �̶�� ó�� �Է�
		else
			temp_pw = JOptionPane.showInputDialog("��й�ȣ�� �߸� �Է��߽��ϴ�!! �ٽ� �Է����ּ���!"); // x != 0 �̶�� ���Է�
		password = Integer.parseInt(temp_pw);
		return password;
	}

	static void createmenu(DataBase db, ATM atm) {
		// JFrame frame = new JFrame("ATM");
		// ���� ATM ȭ���� �߸鼭 � �۾��� �� �� ��ư�� ���� �Է� �޴´�(�Ա�, ���, �ܾ���ȸ, �۱�)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel("                             ATM : ���Ͻô� �۾��� �����ϼ���.                             ");
		label.setFont(new Font("Serif", Font.PLAIN, 14));
		Dimension dim = new Dimension(500, 410);

		frame.add(label);
		frame.setLocation(400, 200);
		frame.setPreferredSize(dim);

		// ��ư ����
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 200, 20);
		frame.setLayout(layout);

		JButton print_button = new JButton("�ܾ���ȸ");
		print_button.setPreferredSize(new Dimension(100, 40));
		print_button.setBounds(0, 0, 50, 25);
		frame.add(print_button);
		JButton deposit_button = new JButton("�Ա�");
		deposit_button.setBounds(0, 0, 50, 25);
		deposit_button.setPreferredSize(new Dimension(100, 40));
		frame.add(deposit_button);
		JButton withdraw_button = new JButton("���");
		withdraw_button.setPreferredSize(new Dimension(100, 40));
		withdraw_button.setBounds(0, 0, 50, 25);
		frame.add(withdraw_button);
		JButton manager = new JButton("������ �޴�");
		manager.setPreferredSize(new Dimension(100, 40));
		manager.setBounds(0, 0, 50, 25);
		frame.add(manager);
		JButton exit_button = new JButton("����");
		exit_button.setPreferredSize(new Dimension(100, 40));
		exit_button.setBounds(0, 0, 50, 25);
		frame.add(exit_button);

		// ��ư�� ���� EVENT Handling
		ActionListener eventHandler = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				long return_Cash;
				if (e.getSource() == print_button) {
					channel = 1;
					return_Cash = Show(channel, id, db, atm);
				} else if (e.getSource() == deposit_button) {
					channel = 2;
					return_Cash = Show(channel, id, db, atm);
					atm.Deposit(db, id, return_Cash);
				} else if (e.getSource() == withdraw_button) {
					channel = 3;
					return_Cash = Show(channel, id, db, atm);
					atm.WithDraw(db, id, return_Cash);
				} else if (e.getSource() == manager) {
					channel = 4;
					return_Cash = Show(channel, id, db, atm);
					/*
					 * String to_id; to_id = to_id(0); if(db.checkingId(to_id)==false) {
					 * while(db.checkingId(to_id)==false) { to_id = to_id(1); } } //atm.Remit(db,
					 * id, to_id, return_Cash); //remit_success(db, id);
					 */
				} else if (e.getSource() == exit_button) {
					ShowDown();
					System.exit(0);
				}

			}
		};

		print_button.addActionListener(eventHandler);
		deposit_button.addActionListener(eventHandler);
		withdraw_button.addActionListener(eventHandler);
		manager.addActionListener(eventHandler);
		exit_button.addActionListener(eventHandler);

		frame.pack();
		frame.setVisible(true);

	}

	// ���� ����
	static void ShowDown() {
		JOptionPane.showInternalMessageDialog(null, "�ȳ��������� ����!");
		frame.setVisible(false);
		main.serviceOn();
	}
}