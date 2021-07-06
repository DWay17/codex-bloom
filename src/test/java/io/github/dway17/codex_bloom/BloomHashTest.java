
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

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
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
    void testBalanceBloomFilterSize() {
	BloomHash bf = new BloomHash(0, 0, 0, 0, 0);
	BitSetFixedSize in = new BitSetFixedSize(4);
	in.flip(0, 4);
	BitSetFixedSize actual = bf.balanceBloomFilter(in);
	assertEquals(8, actual.length());
    }

    @Test
    void testBalanceBloomFilterSize2() {
	BloomHash bf = new BloomHash(0, 0, 0, 0, 0);
	BitSetFixedSize in = new BitSetFixedSize(1000);
//	in.flip(0, 4);
	BitSetFixedSize actual = bf.balanceBloomFilter(in);
	assertEquals(2000, actual.fixedSize());
    }

    @Test
    void testCalcDateSep() {
	BloomHash bf = new BloomHash(0, 0, 0, 0, 0);
	String actual = bf.calcDateSep("12,12,1212");
	String expected = ",";
	assertEquals(expected, actual);
    }

    @Test
    void testFormatBirthdate() {
	BloomHash bf = new BloomHash(0, 0, 0, 0, 0);
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
	BloomHash bf = new BloomHash(0, 0, 0, 0, 0);
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
	BloomHash bf = new BloomHash(0, 0, 0, 0, 0);
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
	BloomHash bf = new BloomHash(0, 0, 0, 0, 0);
	BitSetFixedSize in = new BitSetFixedSize(4);
	long r = 0;
	BitSetFixedSize actual = bf.permuteBloomfilter(in, r);
	assertEquals(4, actual.fixedSize());
    }

    @Test
    void testPermuteBloomfilter01() {
	BloomHash bf = new BloomHash(0, 0, 0, 0, 0);
	BitSetFixedSize in = new BitSetFixedSize(4);
	in.flip(0);
	long r = 0;
	BitSetFixedSize actual = bf.permuteBloomfilter(in, r);
	assertEquals(4, actual.fixedSize());
    }

    @Test
    void testPermuteBloomfilter1() {
	BloomHash bf = new BloomHash(0, 0, 0, 0, 0);
	BitSetFixedSize in = new BitSetFixedSize(4);
	in.flip(0, 4);
	long r = 0;
	BitSetFixedSize actual = bf.permuteBloomfilter(in, r);
	assertEquals(4, actual.fixedSize());
    }


}
