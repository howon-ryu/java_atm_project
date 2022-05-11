package atmnew;

import java.awt.Dimension;
import java.awt.EventQueue;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class main {

	private static Scanner input = new Scanner(System.in);
	private static int command; // ������� �Է��� �޴� Ŀ�ǵ�
	private static ATM atm = new ATM(); // ATM ��üew
	private static DataBase DB = new DataBase(); // DataBase ��ü
	private static Interface screen; // Interface ��ü
	private static String id;
	private static int password;

	public static void main(String[] args) {
		serviceOn();
	}

	// GUI�� �ٲ�
	public static void serviceOn() {
		String tmp_id = screen.Input_id(0);
		while (!DB.checkingId(tmp_id)) {
			tmp_id = screen.Input_id(1);
		}
		int tmp_pass = screen.Input_pass(0);
		while (!DB.matchingPassword(tmp_id, tmp_pass)) {
			tmp_pass = screen.Input_pass(1);
		}
		id = tmp_id;
		password = tmp_pass;
		screen.createmenu(DB, atm);
	}

	private static void serviceOff() {
		screen.ShowDown();
	}

}