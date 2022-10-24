package org.geogebra.test;

import org.geogebra.common.main.error.ErrorHandler;
import org.geogebra.common.main.error.ErrorLogger;
import org.geogebra.common.util.AsyncOperation;
import org.junit.Assert;

/**
 * Error handler that fails a JUnit test when error is found
 */
public final class TestErrorHandler implements ErrorLogger {

	/**
	 * Singleton instance
	 */
	public static final ErrorHandler INSTANCE = new TestErrorHandler();

	private TestErrorHandler() {
		// singleton
	}

	@Override
	public void showError(String msg) {
		Assert.assertNull(msg);
	}

	@Override
	public boolean onUndefinedVariables(String string,
			AsyncOperation<String[]> callback) {
		return false;
	}

	@Override
	public void showCommandError(String command, String message) {
		Assert.assertNull(command);
	}

	@Override
	public String getCurrentCommand() {
		return null;
	}

	@Override
	public void resetError() {
		// nothing to do
	}

	@Override
	public void log(Throwable e) {
		e.printStackTrace();
		Throwable cause = e.getCause() == null ? e : e.getCause();
		StackTraceElement[] ste = cause.getStackTrace();
		StringBuilder out =
				new StringBuilder(cause.getClass().getName() + ":" + cause.getMessage());
		for (StackTraceElement stackTraceElement : ste) {
			out.append("\n").append(stackTraceElement.getClassName()).append(".")
					.append(stackTraceElement.getMethodName()).append(":")
					.append(stackTraceElement.getLineNumber());
		}
		Assert.fail(e.getMessage() + "\n" + out);
	}

}
