
package io.github.dway17.codex_bloom;

import java.io.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BloomHashFactory {

	private final static Logger LOGGER = LoggerFactory.getLogger(BloomHashFactory.class);

	static long seedFirstName;
	static long seedLastName;
	static long seedBirthdate;
	static long seedGender;
	static long seedBalanced;

	private static boolean propsLoaded;

	public static IBloomHash getDefault() {
		if (!propsLoaded) {
			try {
				loadProps();
			} catch (IOException e) {
				LOGGER.error("Cannot load Properties.", e);
				throw new RuntimeException(e);
			}
		}
		return new BloomHash(seedFirstName, seedLastName, seedBirthdate, seedGender, seedBalanced);
	}

	static void loadProps() throws IOException {
		Properties properties = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream("BloomHashFactory.properties");
		properties.load(stream);
		seedFirstName = Long.valueOf((String) properties.get("seedFirstName"));
		seedLastName = Long.valueOf((String) properties.get("seedLastName"));
		seedBirthdate = Long.valueOf((String) properties.get("seedBirthdate"));
		seedGender = Long.valueOf((String) properties.get("seedGender"));
		seedBalanced = Long.valueOf((String) properties.get("seedBalanced"));
		stream.close();
		propsLoaded = true;
	}

	{
		try {
			loadProps();
		} catch (IOException e) {
			LOGGER.error("Cannot load Properties.", e);
			throw new RuntimeException(e);
		}
	}


}
