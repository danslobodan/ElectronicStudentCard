package utilities;

public class Logger {
	
	private Class<? extends Object> cls;

	private Logger(Object obj) {
		cls = obj.getClass();
	}

	public static Logger GetLogger(Object obj) {
		return new Logger(obj);
	}
	
	public void debug(String message) {
		System.out.println(String.format("%s >> %s", cls.getSimpleName(), message));
	}
	
	public void debug(String format, Object... args) {
		var message = String.format(format, args);
		debug(message);
	}
}
