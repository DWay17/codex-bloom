package io.github.dway17.codex_bloom;

import static org.junit.jupiter.api.Assertions.*;

import java.util.BitSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BitSetTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    BitSetFixedSize bs;

    @BeforeEach
    void setUp() throws Exception {
	bs = new BitSetFixedSize(5);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testBitSetFixedSize() {

    }

    @Test
    void testBitSetFixedSizeInt() {

    }

    @Test
    void testClearInt() {

    }

    @Test
    void testClearIntInt() {

    }

    @Test
    void testFixedSize() {
	assertEquals(5, bs.fixedSize());
    }

    @Test
    void testFlipInt() {
	assertThrows(IndexOutOfBoundsException.class, () -> bs.flip(5));
    }

    @Test
    void testFlipIntInt() {

    }

    @Test
    void testGetInt() {
	bs.get(4);
    }

    @Test
    // (expected = IndexOutOfBoundsException.class)
    void testGetIntEx() {
	assertThrows(IndexOutOfBoundsException.class, () -> bs.get(5));
    }

    @Test
    void testGetIntInt() {

    }

    @Test
    void testSetInt() {
	assertThrows(IndexOutOfBoundsException.class, () -> bs.set(5));
    }

    @Test
    void testSetIntBoolean() {

    }

    @Test
    void testSetIntInt() {

    }

    @Test
    void testSetIntIntBoolean() {

    }


}
