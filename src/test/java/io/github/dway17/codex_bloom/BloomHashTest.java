
package io.github.dway17.codex_bloom;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
//import java.util.BitSet;
import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;

class BloomHashTest {

	private static BloomHash bf;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		bf = new BloomHash(0, 0, 0, 0, 0);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testBalanceBloomFilter() {
		BitSetFixedSize in = new BitSetFixedSize(4);
		in.flip(2);
		assertEquals(false, in.get(0));
		assertEquals(false, in.get(1));
		assertEquals(true, in.get(2));
		assertEquals(false, in.get(3));
		BitSetFixedSize actual = bf.balanceBloomFilter(in);
		assertEquals(8, actual.length());
		assertEquals(false, actual.get(0));
		assertEquals(false, actual.get(1));
		assertEquals(true, actual.get(2));
		assertEquals(false, actual.get(3));

		assertEquals(true, actual.get(4));
		assertEquals(true, actual.get(5));
		assertEquals(false, actual.get(6));
		assertEquals(true, actual.get(7));
	}

	@Test
	void testBalanceBloomFilter0() {
		BitSetFixedSize in = new BitSetFixedSize(1);
		in.flip(0);
		assertEquals(true, in.get(0));
		BitSetFixedSize actual = bf.balanceBloomFilter(in);
		assertEquals(2, actual.fixedSize());
		assertEquals(true, actual.get(0));
		assertEquals(false, actual.get(1));
	}

	@Test
	void testBalanceBloomFilter1() {
		BitSetFixedSize in = new BitSetFixedSize(2);
		in.flip(1);
		assertEquals(false, in.get(0));
		assertEquals(true, in.get(1));
		BitSetFixedSize actual = bf.balanceBloomFilter(in);
		assertEquals(4, actual.fixedSize());
		assertEquals(false, actual.get(0));
		assertEquals(true, actual.get(1));
		assertEquals(true, actual.get(2));
		assertEquals(false, actual.get(3));
	}

	@Test
	void testBalanceBloomFilterSize() {
		BitSetFixedSize in = new BitSetFixedSize(4);
		in.flip(0, 4);
		BitSetFixedSize actual = bf.balanceBloomFilter(in);
		assertEquals(8, actual.fixedSize());
	}

	@Test
	void testBalanceBloomFilterSize2() {
		BitSetFixedSize in = new BitSetFixedSize(1000);
		BitSetFixedSize actual = bf.balanceBloomFilter(in);
		assertEquals(2000, actual.fixedSize());
	}

	@Test
	void testBalanceBloomFilterSize22() {
		BitSetFixedSize in = new BitSetFixedSize(1000);
		BitSetFixedSize actual = bf.balanceBloomFilter(in);
		assertEquals(2000, actual.fixedSize());
	}

	@Test
	void testCalcDateSep() {
		String actual = bf.calcDateSep("12,12,1212");
		String expected = ",";
		assertEquals(expected, actual);
	}

	@Test
	void testConvertString2localDate() {
		LocalDate actual = bf.convertString2localDate("13.12.2000");
		LocalDate expected = LocalDate.of(2000, 12, 13);
		assertEquals(expected, actual);
	}

	@Test
	void testConvertString2localDateDash() {
		LocalDate actual = bf.convertString2localDate("2000-12-13");
		LocalDate expected = LocalDate.of(2000, 12, 13);
		assertEquals(expected, actual);
	}

	@Test
	void testFormatBirthdate() {
		LocalDate localDate = LocalDate.now();
		String actual = bf.formatBirthdate(localDate);
		assertEquals(4 + 2 + 2, actual.length());
		localDate = LocalDate.of(2022, 1, 1);
		actual = bf.formatBirthdate(localDate);
		String expected = "20220101";
		assertEquals(expected, actual);
	}

	@Test
	void testHashBigram() {
		LinkedHashMap<String, BitSetFixedSize> table = new LinkedHashMap<>();
		BitSetFixedSize bitSetFixedSize = new BitSetFixedSize(1);
		bitSetFixedSize.set(0);
		table.put("  ", bitSetFixedSize);
		BitSetFixedSize actual = bf.randomHash("  ", table);
		assertTrue(actual.get(0));
	}

	@Test
	void testInsertInTable() {
		LinkedHashMap<String, BitSetFixedSize> table = new LinkedHashMap<>();
		Random r = new Random(1234);
		String s1 = "X";
		String s2 = "Y";
		bf.insertInTable(table, r, s1, s2);
		assertEquals(1, table.size());
		BitSetFixedSize bs = table.get("XY");
		assertEquals(25, bs.cardinality());
	}

	@Test
	void testPermuteBloomfilter0() {
		BitSetFixedSize in = new BitSetFixedSize(4);
		long r = 0;
		BitSetFixedSize actual = bf.permuteBloomfilter(in, r);
		assertEquals(4, actual.fixedSize());
		for (int i = 0; i < 4; i++) {
			assertEquals(false, actual.get(i));
		}
	}

	@Test
	void testPermuteBloomfilter010() {
		BitSetFixedSize in = new BitSetFixedSize(4);
		Random r = new Random();
		in.flip(r.nextInt(4));
		long seed = 0;
		BitSetFixedSize actual = bf.permuteBloomfilter(in, seed);
		assertEquals(4, actual.fixedSize());
		assertEquals(1, actual.cardinality());
	}

	@Test
	void testPermuteBloomfilter1() {
		BitSetFixedSize in = new BitSetFixedSize(4);
		in.flip(0, 4);
		long r = 0;
		BitSetFixedSize actual = bf.permuteBloomfilter(in, r);
		assertEquals(4, actual.fixedSize());
		for (int i = 0; i < 4; i++) {
			assertEquals(true, actual.get(i));
		}
	}

	@Test
	void testPermuteBloomfilter2() {
		BitSetFixedSize in = new BitSetFixedSize(7);
		Random r = new Random();
		in.flip(2);
		in.flip(4);
		long seed = 0;
		BitSetFixedSize actual = bf.permuteBloomfilter(in, seed);
		assertEquals(7, actual.fixedSize());
		assertEquals(2, actual.cardinality());
	}


}
