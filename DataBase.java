package atmnew;

import java.util.HashMap;
import java.util.Map;

public class DataBase {
	// (id, password) map
	private static Map<String, Integer> info = new HashMap<String, Integer>();

	// (id, cash) map
	private static Map<String, Long> account = new HashMap<String, Long>();

	public DataBase() {
		// �ʱ� ATM�� DataBase�� ���� �ִٴ� �����Ͽ� �����ϱ� ������
		// constructor�� �ʱ� �������� ������� �־��ݴϴ�. ->info (Hashmap) ���ٰ�
		info.put("00000-00000", 0000);
		account.put("00000-00000", (long) 10000000);
		info.put("11111-11111", 0000);
		account.put("11111-11111", (long) 20000);
		info.put("22222-22222", 0000);
		account.put("22222-22222", (long) 30000);
	}

	// �ش� ������ ���� �ܾ��� �����մϴ�.(getter)
	public static long getBalance(String id) {
		return account.get(id);
	}

	// �ؽøʿ� �ܾ� ������ ������Ʈ�մϴ�.(setter)
	public static void setBalance(String id, long cash) {
		account.put(id, cash);
	}

	// ���ڷ� ���� id�� password�� �ʿ� ����� password�� ��ġ�ϴ��� �˷��ݴϴ�.
	public static boolean matchingPassword(String id, int password) {
		if (password == info.get(id))
			return true;
		return false;
	}

	// ���ڷ� ���� id�� db�� �����ϴ��� �˷��ݴϴ�
	public static boolean checkingId(String id) {
		// //�Է��� id�� ���Ŀ� �´��� Ȯ��
		if (id.length() != 11)
			return false;
		for (int i = 0; i < id.length(); i++) {
			char tmp = id.charAt(i);
			if (i == 5) {
				if (tmp != '-')
					return false;
			} else {
				if ('0' > tmp || tmp > '9')
					return false;
			}
		}
		if (info.containsKey(id)) {
			return true;
		}
		return false;
	}
}