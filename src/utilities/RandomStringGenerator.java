package utilities;

public class RandomStringGenerator {
	
	private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private RandomStringGenerator() {
		// TODO Auto-generated constructor stub
	}

	public static String Generate(int count) {
		
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < count; i++) {
			int charIndex = (int)(Math.random() * CHARACTERS.length());
			builder.append(CHARACTERS.charAt(charIndex));
		}
		return builder.toString();
	}
}
