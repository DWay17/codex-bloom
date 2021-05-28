
package io.github.dway17.codex_bloom;

import java.util.Date;

public interface IBloomFilter {

	String createBase64Result(String firstName, String lastName, char gender, Date bithdate);

	String createBase64Result(String firstName, String lastName, char gender, int birthYear, int birthMonth,
			int birthDay);

}
