
package io.github.dway17.codex_bloom;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.Base64.Encoder;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BloomHash implements IBloomHash {

    private final static Logger LOGGER = LoggerFactory.getLogger(BloomHash.class);

    private LinkedHashMap<String, BitSetFixedSize> firstNameTable;
    private LinkedHashMap<String, BitSetFixedSize> lastNameTable;
    private LinkedHashMap<String, BitSetFixedSize> birthdateTable;
    private LinkedHashMap<String, BitSetFixedSize> genderTable;

    private long seedFirstName;
    private long seedLastName;
    private long seedBirthdate;
    private long seedGender;
    private long seedBalanced;

    private String vocFirstName;
    private String vocLastName;
    private String vocBirthdate;
    private String vocGender;
    private String vocAll;

    private Set<String> removeFirstName;
    private Set<String> removeLastName;
    private Set<String> removeBirthdate;
    private Set<String> removeGender;

    private Map<String, String> transFirstName;
    private Map<String, String> transLastName;
    private Map<String, String> transBirthdate;
    private Map<String, String> transGender;

    private DateTimeFormatter formatter;

    public BloomHash(long seedFirstName, long seedLastName, long seedBirthdate, long seedGender, long seedBalanced) {
	this.seedFirstName = seedFirstName;
	this.seedLastName = seedLastName;
	this.seedBirthdate = seedBirthdate;
	this.seedGender = seedGender;
	this.seedBalanced = seedBalanced;

	this.vocFirstName = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .-0123456789";
	this.vocLastName = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .-0123456789";
	this.vocBirthdate = "0123456789";
	this.vocGender = "mfoux";
	this.vocAll = vocFirstName + vocGender;

	removeFirstName = new HashSet<>();
	removeLastName = new HashSet<>();
	removeBirthdate = new HashSet<>();
	removeGender = new HashSet<>();

	transFirstName = new LinkedHashMap<>();
	transLastName = new LinkedHashMap<>();
	transBirthdate = new LinkedHashMap<>();
	transGender = new LinkedHashMap<>();

	initRemove();
	initTrans();
	initLookupTable(seedFirstName, seedLastName, seedBirthdate, seedGender, vocAll, vocAll, vocAll, vocAll);

	DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
	formatter = dateTimeFormatterBuilder.appendPattern("uuuuMMdd").toFormatter();

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
	BitSetFixedSize bs = new BitSetFixedSize(1000);

	firstName = normalize(true, firstName, vocFirstName, removeFirstName, transFirstName);
	firstName = padding(firstName);
	bs.or(randomHash(firstName, firstNameTable));

	lastName = normalize(true, lastName, vocLastName, removeLastName, transLastName);
	lastName = padding(lastName);
	bs.or(randomHash(lastName, lastNameTable));

	String genderS = normalize(false, Character.toString(gender), vocGender, removeGender, transGender);
	genderS = padding(genderS);
	bs.or(randomHash(genderS, genderTable));

	String birthdateS = formatBirthdate(bithdate);
	birthdateS = padding(birthdateS);
	bs.or(randomHash(birthdateS, birthdateTable));

	BitSetFixedSize balanceBloomFilter = balanceBloomFilter(bs);
	BitSetFixedSize permutedBloomFilter = permuteBloomfilter(balanceBloomFilter, seedBalanced);
	Encoder encoder = Base64.getEncoder();
	return encoder.encodeToString(permutedBloomFilter.toByteArray());
    }

    public String createBase64Result(String firstName, String lastName, char gender, String birthday) {
	int birthYear;
	int birthMonth;
	int birthDay;
	switch (birthday.length()) {
	case 10:
	    String sep = calcDateSep(birthday);
	    String[] split = birthday.split(Pattern.quote(sep));
	    birthYear = Integer.valueOf(split[2]);
	    birthMonth = Integer.valueOf(split[1]);
	    birthDay = Integer.valueOf(split[0]);
	    break;
	default:
	    throw new IllegalArgumentException("Birthday '" + birthday + "' not parseable.");
	}
	return createBase64Result(firstName, lastName, gender,
		LocalDateTime.of(birthYear, birthMonth, birthDay, 0, 0, 0).toLocalDate());
    }

    BitSetFixedSize balanceBloomFilter(BitSetFixedSize bloomFilter) {
	// TODO use boolean ops
	/* Balanced Bloom filters can be constructed by concatenating a Bloom filter
	 * with length l with a negated copy of the same Bloom filter. */

	int lengthOld = bloomFilter.fixedSize();
	int lengthNew = ((lengthOld + 1) * 2) - 1;
	BitSetFixedSize ret = new BitSetFixedSize(lengthNew);
	for (int i = 0; i <= lengthOld; i++) {
	    if (bloomFilter.get(i)) {
		ret.set(i);
	    } else {
		ret.set(i + lengthOld + 1);
	    }
	}
	return ret;
    }

    String calcDateSep(String birthday) {
	String r1 = birthday.replaceAll("[a-zA-Z0-9]", "");
	boolean matches = r1.matches("^(.)\\1$");
	if (matches) {
	    return r1.substring(0, 1);
	} else {
	    throw new IllegalArgumentException("Date '" + birthday + "' not parseable");
	}
    }

    LinkedHashMap<String, BitSetFixedSize> createTable(long seed, String voc) {
	Random r;
	r = new Random(seed);
	LinkedHashMap<String, BitSetFixedSize> table = new LinkedHashMap<>(0);
	for (String s1 : voc.split("")) {
	    for (String s2 : voc.split("")) {
		insertInTable(table, r, s1, s2);
	    }
	}
	return table;
    }

    String formatBirthdate(LocalDate localDate) {
	return localDate.format(formatter);
    }

    void initLookupTable(long seedFirstName, long seedLastName, long seedBirthdate, long seedGender,
	    String vocFirstName, String vocLastName, String vocBirthdate, String vocGender) {
	firstNameTable = createTable(seedFirstName, vocFirstName);

	lastNameTable = createTable(seedLastName, vocLastName);

	birthdateTable = createTable(seedBirthdate, vocBirthdate);
	LOGGER.debug("birthdateTable=" + birthdateTable.keySet());

	genderTable = createTable(seedGender, vocGender);
	LOGGER.debug("genderTable=" + genderTable.keySet());
    }

    void insertInTable(LinkedHashMap<String, BitSetFixedSize> table, Random r, String s1, String s2) {
	BitSetFixedSize bs = new BitSetFixedSize(1000);
	IntStream.rangeClosed(1, 25).forEach(i -> bs.set(r.nextInt(1000)));
//	for (int i = 0; i < 25; i++) {
//	    bs.set(r.nextInt(1000));
//	}
	table.putIfAbsent(s1 + s2, bs);
    }

    String normalize(boolean toUpper, String s, String voc, Set<String> removeSet, Map<String, String> transformation) {
	String ret = s;
	if (null != removeSet) {
	    for (String rem : removeSet) {
		ret = ret.replace(rem, "");
	    }
	}
	if (null != transformation) {
	    for (Entry<String, String> entry : transformation.entrySet()) {
		ret = ret.replace(entry.getKey(), entry.getValue());
	    }
	}
	if (toUpper) {
	    ret = ret.toUpperCase();
	} else {
	    ret = ret;
	}
	// FIXME: remove not voc
	LOGGER.debug("'" + s + "' -> '" + ret + "'");
	return ret;
    }

    String padding(String firstName) {
	return " " + firstName + " ";
    }

    BitSetFixedSize permuteBloomfilter(BitSetFixedSize bloomFilter, long seebBalanced) {
	Random r = new Random(seedBalanced);
	int length = bloomFilter.fixedSize();
	for (int i = 0; i < length; i++) {
	    int newPos = r.nextInt(length);
	    boolean bitI = bloomFilter.get(i);
	    boolean bitNewPos = bloomFilter.get(newPos);
	    bloomFilter.set(i, bitNewPos);
	    bloomFilter.set(newPos, bitI);
	}
	return bloomFilter;
    }

    BitSetFixedSize randomHash(String s, LinkedHashMap<String, BitSetFixedSize> table) {
	String bigram;
	BitSetFixedSize bs = new BitSetFixedSize(1000);
	for (int i = 0; i < s.length() - 1; i++) {
	    bigram = s.substring(i, i + 2);
	    LOGGER.trace("i = " + i + "" + "\t" + "bigram = '" + bigram + "'.");
	    BitSetFixedSize bitSetFixedSize = table.get(bigram);
	    if (null == bitSetFixedSize) {
		throw new RuntimeException("bigram '" + bigram + "' is not contained in '" + table.keySet() + "'.");
	    } else {
		bs.or(bitSetFixedSize);
	    }
	}
	return bs;
    }

    private void initRemove() {
	removeFirstName.add("?");
	removeFirstName.add("Dr.");
	removeFirstName.add("Prof.");
	removeFirstName.add("med.");
	removeFirstName.add("rer.");
	removeFirstName.add("nat.");
	removeFirstName.add("Ing.");
	removeFirstName.add("Dipl.");
	removeFirstName.add(",");
	removeFirstName.add("-");

	removeLastName.add("?");
	removeLastName.add("Dr.");
	removeLastName.add("Prof.");
	removeLastName.add("med.");
	removeLastName.add("rer.");
	removeLastName.add("nat.");
	removeLastName.add("Ing.");
	removeLastName.add("Dipl.");
	removeLastName.add(",");
	removeLastName.add(" ");
	removeLastName.add("-");

    }

    private void initTrans() {
	transFirstName.put("é", "e");
	transFirstName.put("ä", "ae");
	transFirstName.put("ö", "oe");
	transFirstName.put("ü", "ue");
	transFirstName.put("ß", "ss");

	transLastName.put("é", "e");
	transLastName.put("ä", "ae");
	transLastName.put("ö", "oe");
	transLastName.put("ü", "ue");
	transLastName.put("ß", "ss");
    }


}
