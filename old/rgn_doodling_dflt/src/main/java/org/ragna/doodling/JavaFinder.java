package org.ragna.doodling;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Localiza JAVA_HOME.
 * 
 * @author nilseu.padilha
 * 
 */
public class JavaFinder {

	private static final String JAVA_1_7 = "1.7";
	private static final Logger LOG = LoggerFactory.getLogger(JavaFinder.class);

	public static void main(String[] args) {

		JavaFinder finder = new JavaFinder();

		// finder.printEnv();
		System.out
				.println("\n========================================================\n");
		finder.findJavaInOSEnv();
		System.out
				.println("\n========================================================\n");
		finder.findWindowsArch();
		System.out
				.println("\n========================================================\n");
		finder.findJavaArch();
		System.out
				.println("\n========================================================\n");
		finder.findJavaInPath();
		System.out
				.println("\n========================================================\n");
		finder.javaCurrent();
		System.out
				.println("\n========================================================\n");
		String[] javas = finder.findJava7();
		System.out.println(Arrays.toString(javas));
		System.out
				.println("\n========================================================\n");

		finder.printJavaKey64InRegistry();

		System.out
				.println("\n========================================================\n");

		finder.printJavaHome64InRegistry();
	}

	public JavaFinder() {
	}

	public void javaCurrent() {
		System.out.printf("Java current Path: '%s'\n", SystemUtils.JAVA_HOME);
	}

	public void printJavaKey64InRegistry() {
		String res = findJavaKey64InRegistry();

		System.out.printf("\tValue '%s'\n", res);
	}

	private void printJavaHome64InRegistry() {
		String res = findJavaHome64InRegistry();

		System.out.printf("\tPATH Value '%s'\n", res);
	}

	private String findJavaHome64InRegistry() {
		String javaVersion = findJavaKey64InRegistry();
		String query = String
				.format("\"HKEY_LOCAL_MACHINE\\SOFTWARE\\JavaSoft\\Java Development Kit\\%s\"",
						javaVersion);
		String[] cmd = new String[] { "REG", "query", query, "/v", "JavaHome" };
		StringWriter sw = processCommand(cmd);
		String reg = sw.toString().trim();
		System.out.printf("ReqQuery '%s'\n", sw.toString().trim());

		String javaHomeRegex = ".+\\s+(([a-zA-Z]:)?(\\\\[a-zA-Z0-9._-]+)+\\\\?)$";
		Pattern javaHomePtn = Pattern.compile(javaHomeRegex, Pattern.MULTILINE);
		Matcher takeIt = javaHomePtn.matcher(reg);

		String res = "NOT_FOUND";
		if (takeIt.find()) {
			res = takeIt.group(1);
		}
		return res;
	}

	/**
	 * Pesquisa o Windows Registry para procurar uma instalação do Java usando o
	 * comando <a
	 * href="http://technet.microsoft.com/en-us/library/cc742028.aspx">REG
	 * QUERY</a>
	 * 
	 * @return
	 */
	private String findJavaKey64InRegistry() {
		String query = "\"HKEY_LOCAL_MACHINE\\SOFTWARE\\JavaSoft\\Java Development Kit\"";
		String[] cmd = new String[] { "REG", "query", query, "/v",
				"CurrentVersion" };
		StringWriter sw = processCommand(cmd);
		String reg = sw.toString().trim();
		System.out.printf("ReqQuery '%s'\n", sw.toString().trim());

		String javaVersionRegex = ".+\\s+(\\d*\\.\\d*)$";
		Pattern javaVersionPtn = Pattern.compile(javaVersionRegex,
				Pattern.MULTILINE);
		Matcher takeIt = javaVersionPtn.matcher(reg);

		String res = "NOT_FOUND";
		if (takeIt.find()) {
			res = takeIt.group(1);
		}

		return res;
	}

	/**
	 * Atualiza as variáveis de ambiente (de sistema) usando o comando <a
	 * href="http://technet.microsoft.com/en-us/library/cc755104.aspx">SETX</a>.
	 * 
	 * @param javaHome
	 *            caminho para setar o java home
	 */
	public void setJavaHome(File javaHome) {
		if (javaHome == null || !javaHome.exists() || !javaHome.isDirectory()) {
			throw new RuntimeException(String.format(
					"JAVA_HOME invalido! '%s'", javaHome.getAbsolutePath()));
		}
		String javaHomePath = String.format("\"%s\"",
				javaHome.getAbsolutePath());
		String[] cmd = new String[] { "CMD.exe", "/C", "setx", "JAVA_HOME",
				javaHomePath, "/m" };
		processCommand(cmd);
	}

	public String[] findJavaInPath() {
		String[] cmd = new String[] { "CMD.exe", "/C", "where java" };
		StringWriter sw = processCommand(cmd);

		String[] javaPaths = sw.toString().trim().split("\n");

		System.out.printf("Java Path: \n");
		for (int i = 0; i < javaPaths.length; i++) {
			javaPaths[i] = javaPaths[i].trim();
		}
		return javaPaths;
	}

	public String[] findJava7() {
		String[] javas = findJavaInPath();
		List<String> validatedJava = new ArrayList<String>();
		for (String jPath : javas) {
			checkJava7(validatedJava, jPath);
		}

		return javas;
	}

	private void checkJava7(List<String> validatedJava, String jPath) {
		StringWriter sw = processCommand(new String[] { jPath, "-version" });
		if (sw.toString().contains(JAVA_1_7)) {
			validatedJava.add(jPath);
		}
	}

	public void findJavaInOSEnv() {
		String[] cmd = new String[] { "CMD.exe", "/C", "ECHO", "%JAVA_HOME%" };
		StringWriter sw = processCommand(cmd);
		System.out.printf("JAVA_HOME (os): '%s'\n", sw.toString().trim());
		System.out.printf("JAVA_HOME (curr): '%s'\n", SystemUtils.JAVA_HOME);

		System.out.printf("Windows: '%s'\n", SystemUtils.IS_OS_WINDOWS);
		System.out.printf("Windows 7: '%s'\n", SystemUtils.IS_OS_WINDOWS_7);
		System.out.printf("JAVA 7: '%s'\n", SystemUtils.IS_JAVA_1_7);

	}

	public void findJavaArch() {
		String osArch = SystemUtils.OS_ARCH;
		boolean is64 = SystemUtils.OS_ARCH.contains("64");
		boolean is32 = SystemUtils.OS_ARCH.contains("32");

		System.out.printf("JVM osArch: '%s'\n", osArch);
		System.out.printf("\tis64: '%s'\n", is64);
		System.out.printf("\tis32: '%s'\n", is32);
	}

	public void findWindowsArch() {
		String winArch = findWindowsArchitecture();
		boolean isWin64 = winArch.contains("64");
		boolean isWin32 = winArch.contains("32");
		System.out.printf("Windows ARCH: '%s'\n", winArch);
		System.out.printf("\tisWin64: '%s'\n", isWin64);
		System.out.printf("\tisWin32: '%s'\n", isWin32);

	}

	public String findWindowsArchitecture() {
		String[] cmd = new String[] { "CMD.exe", "/C",
				"SET PROCESSOR_ARCHITECTURE" };
		StringWriter sw = processCommand(cmd, new UpdatePath());
		String result = sw.toString();
		result = result == null ? null : result.trim();
		return result;
	}

	@SuppressWarnings("unused")
	private void printEnv() {
		String[] cmd = new String[] { "CMD.exe", "/C", "SET" };
		StringWriter sw = processCommand(cmd, new UpdatePath());
		String result = sw.toString();
		System.out.println(result);
	}

	public StringWriter processCommand(String[] cmdArray) {
		return processCommand(Arrays.asList(cmdArray), null);
	}

	public StringWriter processCommand(String[] cmdArray, EnvUpdater envUpdater) {
		return processCommand(Arrays.asList(cmdArray), envUpdater);
	}

	public StringWriter processCommand(List<String> cmdList) {
		return processCommand(cmdList, null);
	}

	/**
	 * Chama programa do SO em conjunto com a lista de parametros (string),
	 * incluindo o programa a ser chamado no primeiro parametro.
	 * 
	 * @param cmdList
	 * @param envUpdater
	 * @return
	 */
	private StringWriter processCommand(List<String> cmdList,
			EnvUpdater envUpdater) {

		ProcessBuilder pb = new ProcessBuilder();
		StringWriter sw = new StringWriter();
		pb.command(cmdList);
		try {
			pb.redirectErrorStream(true);

			Map<String, String> env = pb.environment();

			if (envUpdater != null) {
				envUpdater.updateEnv(env);
			}

			Process process;
			process = pb.start();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					process.getInputStream()));

			PrintWriter pw = new PrintWriter(sw);

			String line;
			while ((line = in.readLine()) != null) {
				pw.println(line);
			}

		} catch (IOException e) {
			throw new RuntimeException(
					"Erro ao identificar intalação do Javas", e);
		}
		return sw;
	}
}

class UpdatePath implements EnvUpdater {

	public void updateEnv(Map<String, String> env) {
		String path = env.get("Path") + ";C:\\naved\\bin";

		env.put("Path", path);
	}
}
