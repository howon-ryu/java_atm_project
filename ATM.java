package atmnew;

import java.util.*;
import javax.swing.JOptionPane;

public class ATM {

	private static int one, five; // ATM기 안에 들어있는 만 원권, 오 만원권 지폐 개수
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

	// 입금
	static void Deposit(DataBase db, String id, long cash) {
		db.setBalance(id, db.getBalance(id) + cash);
		tr.add(id + " deposit: " + String.valueOf(cash) + "\n");
	}

	// 출금
	static void WithDraw(DataBase db, String id, long cash) {
		db.setBalance(id, db.getBalance(id) - cash);
		tr.add(id + " withdraw: " + String.valueOf(cash) + "\n");
	}

	// 잔액 확인
	static long DisplayCash(DataBase db, String id) {
		return db.getBalance(id);
	}

	// transaction history 확인
	static void LogPrint() {
		ArrayList<String> tmp = new ArrayList<>();
		for (int i = 0; i < ATM.tr.size(); i++) {
			tmp.add(tr.get(i));
		}
		JOptionPane.showMessageDialog(null, tmp);
	}

}