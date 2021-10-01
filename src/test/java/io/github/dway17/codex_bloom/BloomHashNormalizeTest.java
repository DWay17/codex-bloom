
package io.github.dway17.codex_bloom;

import static org.junit.jupiter.api.Assertions.*;

import java.util.BitSet;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
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
    void test() {
	BloomHash bh = new BloomHash(0, 0, 0, 0, 0);
	String s = "Prof. aéz/xx";
	String voc = bh.getVocFirstName();
	Set<String> remSet = bh.getRemoveFirstName();
	remSet.add("Prof.");
	LinkedHashMap<String, String> trans = new LinkedHashMap<>();
	trans.put("é", "e");
	String actual = bh.normalize(true, s, voc, remSet, trans);
	String expected = " AEZXX";
	assertEquals(expected, actual);
    }

    @Test
    void testNormalize() {
	BloomHash bh = new BloomHash(0, 0, 0, 0, 0);
	String s = "Prof. aéz";
	String voc = "AZPROFE";
	Set<String> remSet = new HashSet();
	remSet.add("Prof.");
	LinkedHashMap<String, String> trans = new LinkedHashMap<>();
	trans.put("é", "e");
	String actual = bh.normalize(true, s, voc, remSet, trans);
	String expected = "AEZ";
	assertEquals(expected, actual);
    }

    @Test
    void testNormalizeReplaceNullTransNull() {
	BloomHash bh = new BloomHash(0, 0, 0, 0, 0);
	String s = "az";
	String voc = "AZ";
	String actual = bh.normalize(true, s, voc, null, null);
	String expected = "AZ";
	assertEquals(expected, actual);
    }

    @Test
    void testNormalizeTransNull() {
	BloomHash bh = new BloomHash(0, 0, 0, 0, 0);
	String s = "Prof. az";
	String voc = "AZPROF.";
	Set<String> remSet = new HashSet();
	remSet.add("Prof.");
	String actual = bh.normalize(true, s, voc, remSet, null);
	String expected = "AZ";
	assertEquals(expected, actual);
    }

    @Test
    void testRemoveFirstName() {
	BloomHash bh = new BloomHash(0, 0, 0, 0, 0);
	String s = "Anna Marie";
	String voc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ -";
	Set<String> remSet = bh.getRemoveFirstName();
	LinkedHashMap<String, String> trans = new LinkedHashMap<>();
	String actual = bh.normalize(true, s, voc, remSet, trans);
	String expected = "ANNA MARIE";
	assertEquals(expected, actual);
    }

    @Test
    void testRemoveFirstNameBS() {
	BloomHash bh = new BloomHash(0, 0, 0, 0, 0);
	String s = "Anna-Marie";
	String voc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ -";
	Set<String> remSet = bh.getRemoveFirstName();
	LinkedHashMap<String, String> trans = new LinkedHashMap<>();
	String actual = bh.normalize(true, s, voc, remSet, trans);
	String expected = "ANNAMARIE";
	assertEquals(expected, actual);
    }

    @Test
    void testRemoveLastName() {
	BloomHash bh = new BloomHash(0, 0, 0, 0, 0);
	String s = "Schmitt Meier";
	String voc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ -";
	Set<String> remSet = bh.getRemoveLastName();
	LinkedHashMap<String, String> trans = new LinkedHashMap<>();
	String actual = bh.normalize(true, s, voc, remSet, trans);
	String expected = "SCHMITTMEIER";
	assertEquals(expected, actual);
    }

    @Test
    void testRemoveLastNameBS() {
	BloomHash bh = new BloomHash(0, 0, 0, 0, 0);
	String s = "Schmitt-Meier";
	String voc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ -";
	Set<String> remSet = bh.getRemoveLastName();
	LinkedHashMap<String, String> trans = new LinkedHashMap<>();
	String actual = bh.normalize(true, s, voc, remSet, trans);
	String expected = "SCHMITTMEIER";
	assertEquals(expected, actual);
    }

    @Test
    void testRemoveLastNameCBvM() {
	BloomHash bh = new BloomHash(0, 0, 0, 0, 0);
	String s = "Baron von Münchhausen";
	String voc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ -";
	Set<String> remSet = bh.getRemoveLastName();
	Map<String, String> trans = bh.getTransLastName();
	String actual = bh.normalize(true, s, voc, remSet, trans);
	String expected = "BARONVONMUENCHHAUSEN";
	assertEquals(expected, actual);
    }

    @Test
    void testRemoveLastNameDeVil() {
	BloomHash bh = new BloomHash(0, 0, 0, 0, 0);
	String s = "de Vil";
	String voc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ -";
	Set<String> remSet = bh.getRemoveLastName();
	Map<String, String> trans = bh.getTransLastName();
	String actual = bh.normalize(true, s, voc, remSet, trans);
	String expected = "DEVIL";
	assertEquals(expected, actual);
    }

    @Test
    void testRemoveLastNameMIRAC() {
	BloomHash bh = new BloomHash(0, 0, 0, 0, 0);
	String s = "Miraç";
	String voc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ -";
	Set<String> remSet = bh.getRemoveLastName();
	Map<String, String> trans = bh.getTransLastName();
	String actual = bh.normalize(true, s, voc, remSet, trans);
	String expected = "MIRA";
	assertEquals(expected, actual);
    }


}
