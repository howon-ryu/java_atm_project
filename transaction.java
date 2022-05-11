package atmnew;

import java.util.ArrayList;
import java.util.HashMap;

public class transaction {

	private static ArrayList<String> history = new ArrayList<>();
	private String id = "";
	private String cash = "";

	public transaction() {

	}

	public void add(String log) {
		history.add(log);
	}

	public String get(int idx) {
		return history.get(idx);
	}

	public int size() {
		return history.size();
	}

}