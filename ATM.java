package atmnew;

import java.util.*;
import javax.swing.JOptionPane;

public class ATM {

	private static int one, five; // ATM�� �ȿ� ����ִ� �� ����, �� ������ ���� ����
	private static transaction tr = new transaction();

	// constructor
	public ATM() {
		one = 10000;
		five = 10000;
	}

	static int getOne() {
		return one;
	}

	static int getFive() {
		return five;
	}

	static void put(int putOne, int putFive) {
		one += putOne;
		five += putFive;
	}

	// �Ա�
	static void Deposit(DataBase db, String id, long cash) {
		db.setBalance(id, db.getBalance(id) + cash);
		tr.add(id + " deposit: " + String.valueOf(cash) + "\n");
	}

	// ���
	static void WithDraw(DataBase db, String id, long cash) {
		db.setBalance(id, db.getBalance(id) - cash);
		tr.add(id + " withdraw: " + String.valueOf(cash) + "\n");
	}

	// �ܾ� Ȯ��
	static long DisplayCash(DataBase db, String id) {
		return db.getBalance(id);
	}

	// transaction history Ȯ��
	static void LogPrint() {
		ArrayList<String> tmp = new ArrayList<>();
		for (int i = 0; i < ATM.tr.size(); i++) {
			tmp.add(tr.get(i));
		}
		JOptionPane.showMessageDialog(null, tmp);
	}

}