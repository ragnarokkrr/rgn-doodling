package org.ragna.doodling.key;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class KeyStoreTest {

	private static final String PASSWORD = "some password";

	public static void main(String[] args) throws Exception {
		firstTest();
		secondTest();
	}

	private static void secondTest() {
		File resKS = getKeystore();
		try {
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			FileInputStream fin = new FileInputStream(resKS);
			ks.load(fin, "aaaa".toCharArray()); // PASSWORD.toCharArray());
			System.out.printf("Arquivo e senha carregado", resKS);
			fin.close();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("=================================");
			if (e.getCause() instanceof UnrecoverableKeyException) {
				System.out.printf("OHoh '%s'\n", e.getCause()
						.getLocalizedMessage());
			}
		}

	}

	private static void firstTest() throws KeyStoreException, IOException,
			NoSuchAlgorithmException, CertificateException,
			FileNotFoundException {
		File resKS = getKeystore();
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		char[] password = PASSWORD.toCharArray();
		ks.load(null, password);

		// Store away the keystore.
		FileOutputStream fos = new FileOutputStream(resKS);
		ks.store(fos, password);

		fos.close();

		System.out.printf("Keystore '%s' criada\n", resKS.getAbsolutePath());
	}

	private static File getKeystore() {
		String userDir = System.getProperty("user.dir");
		System.out.println(userDir);

		// File resKS = new File(userDir,
		// "src/main/resources/testkeystore.jks");
		File resKS = new File("testkeystore.jks");
		return resKS;
	}
}
