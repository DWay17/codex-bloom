
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
	assertEquals(false, in.get(4));
	BitSetFixedSize actual = bf.balanceBloomFilter(in);
	assertEquals(false, actual.get(0));
	assertEquals(false, actual.get(1));
	assertEquals(true, actual.get(2));
	assertEquals(false, actual.get(3));
//	assertEquals(false, actual.get(4));

	assertEquals(true, actual.get(5));
	assertEquals(true, actual.get(6));
	assertEquals(false, actual.get(7));
	assertEquals(true, actual.get(8));
	assertEquals(true, actual.get(9));
//	assertEquals(8, actual.length());
    }

    @Test
    void testBalanceBloomFilter1() {
	BitSetFixedSize in = new BitSetFixedSize(1);
	in.flip(1);
	assertEquals(false, in.get(0));
	assertEquals(true, in.get(1));
	BitSetFixedSize actual = bf.balanceBloomFilter(in);
	assertEquals(false, actual.get(0));
	assertEquals(true, actual.get(1));
	assertEquals(true, actual.get(2));
	assertEquals(false, actual.get(3));

//	assertEquals(8, actual.length());
    }

    @Test
    void testBalanceBloomFilterSize() {
	BitSetFixedSize in = new BitSetFixedSize(4);
	in.flip(0, 4);
	BitSetFixedSize actual = bf.balanceBloomFilter(in);
	assertEquals(9, actual.fixedSize());
    }

    @Test
    void testBalanceBloomFilterSize2() {
	BitSetFixedSize in = new BitSetFixedSize(999);
//	in.flip(0, 4);
	BitSetFixedSize actual = bf.balanceBloomFilter(in);
	assertEquals(1999, actual.fixedSize());
    }

    @Test
    void testCalcDateSep() {
	String actual = bf.calcDateSep("12,12,1212");
	String expected = ",";
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
	long[] longs = new long[] { 1L };
	BitSetFixedSize bitSetFixedSize = new BitSetFixedSize(1);
	bitSetFixedSize.set(1);
	table.put("  ", bitSetFixedSize);
	BitSetFixedSize actual = bf.randomHash("  ", table);
	assertTrue(actual.get(1));
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
	int cardinality = bs.cardinality();
	assertEquals(25, cardinality);
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
