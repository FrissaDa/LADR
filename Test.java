import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Test {

	/**
	 * Verifie que le resultat donne d'une expression booleenne est vraie et
	 * affiche dans la console le bilan de cette verification.
	 * @param result le resultat d'une expression booleenne a verifier
	 * @param description la description du test
	 */
	static void assertTrue(boolean result, String description) {
		try {
			throw new AssertionError(description);
		}
		catch (AssertionError error) {
			printResult(result, description);
		}
	}

	/** e
	 * Verifie que le resultat donne d'une expression booleenne est faux et
	 * affiche dans la console le bilan de cette verification.
	 * @param result le resultat d'une expression booleenne a verifier
	 * @param description la description du test
	 */
	static void assertFalse(boolean result, String description) {
		try {
			throw new AssertionError(description);
		}
		catch (AssertionError error) {
			printResult(!result, description);
		}
	}

	/**
	 * Verifie que le resultat obtenu est egal au resultat attendu et affiche
	 * dans la console le bilan de cette verification.
	 * 
	 * Le test d'egalite utilise la methode "egale" si elle est definie dans la
	 * classe dont les deux resultats sont instances, ou la methode "equals"
	 * sinon.
	 * 
	 * L'affichage utilise la methode "versChaine" si elle est definie dans la
	 * classe dont les deux resultats sont instances, ou la methode "toString"
	 * sinon.
	 * 
	 * @param result le resultat obtenu
	 * @param expectedResult le resultat attendu
	 * @param description la description du test
	 */
	static void assertEquals(Object result, Object expectedResult, String description) {
		try {
			throw new AssertionError(description);
		} catch (AssertionError error) {
			// case when both result and expected result are null
			boolean passed = (result == null && expectedResult == null) || ((result != null) && (expectedResult != null) && equals(result, expectedResult));
			if (!passed) {
				description = description + " (result is " + toString(result) + " but " + toString(expectedResult) + " is expected)";
			}
			printResult(passed, description);
		}
	}

	/**
	 * Verifie que le resultat obtenu n'est pas egal au resultat attendu et affiche
	 * dans la console le bilan de cette verification.
	 * 
	 * Le test d'egalite utilise la methode "egale" si elle est definie dans la
	 * classe dont les deux resultats sont instances, ou la methode "equals"
	 * sinon.
	 * 
	 * L'affichage utilise la methode "versChaine" si elle est definie dans la
	 * classe dont les deux resultats sont instances, ou la methode "toString"
	 * sinon.
	 * 
	 * @param result le resultat obtenu
	 * @param expectedResult le resultat attendu
	 * @param description la description du test
	 */
	static void assertNotEquals(Object result, Object expectedResult, String description) {
		try {
			throw new AssertionError(description);
		}
		catch (AssertionError error) {
			// case when both result and expected result are null
			boolean passed = (result == null && expectedResult==null) || ((result != null) && (expectedResult != null) && !equals(result, expectedResult));
			if (!passed) {
				description = description + " (result is " + toString(result) + " but " + toString(expectedResult) + " is expected)";
			}
			printResult(passed, description);
		}
	}

	/**
	 * Verifie que l'execution d'une bloc d'instructions conduit a une erreur et
	 * affiche dans la console le bilan de cette verification.
	 * @param runnable le bloc d'instructions a executer
	 * @param description la description du test
	 */
	static void assertError(Runnable runnable, String description) {
		try {
			runnable.run();
			printResult(false, description);
		} catch (Error error) {
			printResult(true, description);
		}
	}
	
	/**
	 * Verifie que l'execution d'une bloc d'instructions ne conduit pas a une erreur et
	 * affiche dans la console le bilan de cette verification. Cette methode verifie que le
	 * code est "protege" des levees d'exceptions.
	 * @param runnable le bloc d'instructions a executer
	 * @param description la description du test
	 */
	static void assertNoError(Runnable runnable, String description) {
		try {
			runnable.run();
			printResult(true, description);
		}
		catch (Exception exception) {
			printResult(false, description);
			exception.printStackTrace();
		}
	}

	// ====================================================
	// | Methodes internes volontairement non documentees |
	// ====================================================

	static boolean equals(Object object1, Object object2) {
		try {
			Class<?>[] parameterTypes = { object2.getClass() };
			Method egale = object1.getClass().getDeclaredMethod("egale", parameterTypes);
			return (Boolean) egale.invoke(object1, object2);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassCastException e) {
			return object1.equals(object2);
		}
	}

	static String toString(Object object) {
		try {
			Method versChaine = object.getClass().getDeclaredMethod("versChaine", new Class<?>[0]);
			return (String) versChaine.invoke(object);
		} catch (NullPointerException npe) {
			return "null";
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassCastException e) {
			return object.toString();
		}
	}

	static int testCount = 0;

	static void printResult(boolean passed, String message) {
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		int index = stack.length - 1;
		int i = 0;
		boolean found = false;
		while (i < stack.length - 1 && !found) {
			if (stack[i].getMethodName().startsWith("assert")) {
				index = i + 1;
				found = true;
			} else {
				i++;
			}
		}
		// DO NOT CLOSE this stream of it will close System.out and System.err...
		PrintStream stream = (passed?System.out:System.err);
		StackTraceElement trace = stack[index];
		stream.printf("%s : %d : %s : %s\n",trace.getFileName(),
				trace.getLineNumber(),
				(passed?"passed":"error"),
				message);
		stream.flush();
		try {
			Thread.sleep(10); // Avoid out and err streams messages to mix on the console
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		testCount++;
	}
}
