
package io.github.dway17.codex_bloom;

import static org.junit.jupiter.api.Assertions.*;

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


class BloomHashNormalizeTest {

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
	void testNormalize() {
		BloomHash bh = new BloomHash(0, 0, 0, 0);
		String s = "PrOf. aéz";
		String voc = "AZ";
		Set<String> remSet = new HashSet();
		remSet.add("Prof.");
		LinkedHashMap<String, String> trans = new LinkedHashMap<>();
		trans.put("é", "e");
		String actual = bh.normalize(true, s, voc, remSet, trans);
		String expected = " AEZ";
		assertEquals(expected, actual);
	}

	@Test
	void testNormalizeReplaceNullTransNull() {
		BloomHash bh = new BloomHash(0, 0, 0, 0);
		String s = "az";
		String voc = "AZ";
		String actual = bh.normalize(true, s, voc, null, null);
		String expected = "AZ";
		assertEquals(expected, actual);
	}

	@Test
	void testNormalizeTransNull() {
		BloomHash bh = new BloomHash(0, 0, 0, 0);
		String s = "PrOf. az";
		String voc = "AZ";
		Set<String> remSet = new HashSet();
		remSet.add("Prof.");
 		String actual = bh.normalize(true, s, voc, remSet, null);
		String expected = " AZ";
		assertEquals(expected, actual);
	}

}
