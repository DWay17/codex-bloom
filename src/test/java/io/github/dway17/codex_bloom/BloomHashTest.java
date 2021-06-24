
package io.github.dway17.codex_bloom;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.BitSet;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

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
	BloomHash bf = new BloomHash(0, 0, 0, 0);
	BitSet in = new BitSet(4);
	in.flip(0, 4);
	BitSet actual = bf.balanceBloomFilter(in);
	assertEquals(8, actual.length());
    }

    @Test
    void testBalanceBloomFilterSize2() {
	BloomHash bf = new BloomHash(0, 0, 0, 0);
	BitSet in = new BitSet(1000);
//	in.flip(0, 4);
	BitSet actual = bf.balanceBloomFilter(in);
	assertEquals(2000, actual.length());
    }

    @Test
    void testCalcDateSep() {
	BloomHash bf = new BloomHash(0, 0, 0, 0);
	String actual = bf.calcDateSep("12,12,1212");
	String expected = ",";
	assertEquals(expected, actual);
    }

    @Test
    void testHashBigram() {
	fail("Not yet implemented");
    }


}
