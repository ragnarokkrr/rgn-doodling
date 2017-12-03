package org.ragna.doodling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang.SystemUtils;

public class Tests {

	public static void main(String[] args) {

		// http://www.oracle.com/technetwork/java/hotspotfaq-138619.html#64bit_detection
		// "32", "64", or "unknown".
		String osArch = SystemUtils.OS_ARCH;

		boolean is64 = SystemUtils.OS_ARCH.contains("64");
		boolean is32 = SystemUtils.OS_ARCH.contains("32");

		System.out.printf("osArch: '%s'\n", osArch);
		System.out.printf("is64: '%s'\n", is64);
		System.out.printf("is32: '%s'\n", is32);

		System.out.printf("Java 170 pelo menos. '%s'\n",
				SystemUtils.isJavaVersionAtLeast(170));

		String javaHome = SystemUtils.JAVA_HOME;

		System.out.printf("java home: '%s'\n", javaHome);

		System.out.println("==========================================================================");
		System.out.println("env:");
		System.out.println(Arrays
				.toString(System.getenv().entrySet().toArray()));
		System.out.println("--------------------------------------------------------------------------");
		System.out.println(System.getenv("JAVA_HOME"));
		System.out.println("==========================================================================");
		
		
		
		System.out.println("==========================================================================");
		System.out.println("=====================   SET RAGNA_HOME='aaaaaa' ==========================");
		System.out.println("==========================================================================");
		ProcessBuilder pb1 = new ProcessBuilder("CMD.exe", "/C", "SET RAGNA_HOME='aaaaaa'");
		
		try {
			pb1.start();
			System.out.println("----------------------------------env--------------------------------");
			System.out.println(Arrays
					.toString(pb1.environment().entrySet().toArray()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		

		System.out.println("==========================================================================");
		System.out.println("===========================   SET    =====================================");
		System.out.println("==========================================================================");
		
		ProcessBuilder pb = new ProcessBuilder("CMD.exe", "/C", "SET");

		try {
			pb.redirectErrorStream(true);
			Map<String, String> env = pb.environment();
			String path = env.get("Path") + ";C:\\naved\\bin";
			env.put("Path", path);
			Process process;
			process = pb.start();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("==========================================================================");

		
		
		
	}

}
