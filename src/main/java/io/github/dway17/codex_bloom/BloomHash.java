package io.github.dway17.codex_bloom;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;

public class BloomHash implements IBloomHash {

	public BloomHash(long seedFirstName, long seedLastName, long seedBirthdate, long seedGender) {
	}

	@Override
	public String createBase64Result(String firstName, String lastName, char gender, Date bithdate) {
		return createBase64Result(firstName, lastName, gender,
				LocalDate.ofInstant(bithdate.toInstant(), ZoneId.systemDefault()));
	}

	@Override
	public String createBase64Result(String firstName, String lastName, char gender, int birthYear, int birthMonth,
			int birthDay) {
		return createBase64Result(firstName, lastName, gender,
				LocalDateTime.of(birthYear, birthMonth, birthDay, 0, 0, 0).toLocalDate());
	}

	@Override
	public String createBase64Result(String firstName, String lastName, char gender, LocalDate bithdate) {
		// TODO Auto-generated method stub
		Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(new byte[2000 / 8]);
	}


}
