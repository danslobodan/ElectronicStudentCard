package utilities;

public class StringExtensions {

	private StringExtensions() {
		// TODO Auto-generated constructor stub
	}

	public static boolean IsNullOrWhitespace(String str) {
		return str == null || str.isBlank();
	}
}
