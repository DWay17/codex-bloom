
package io.github.dway17.codex_bloom;

import java.io.*;
import java.util.*;

public class BloomHashFactory {

    static long seedFirstName;
    static long seedLastName;
    static long seedBirthdate;
    static long seedGender;
    static long seedBalanced;

    public static IBloomHash getDefault() {
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
    }


}
