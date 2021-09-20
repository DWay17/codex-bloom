
package io.github.dway17.codex_bloom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** @author t.richter
 * @see https://confluence.adesso.de/display/NUM/Patientenregistrierung#Patientenregistrierung-Testdaten(vorNormalisierung): */
public class BloomHashTestHGW {

    @Test
    void testEM() {
	IBloomHash bloomHash = BloomHashFactory.getDefault();
	String actual = bloomHash.createBase64Result("Erika", "Mustermann", 'f', 1964, 8, 12);
	String expected;
	expected = "h3le06jR5/HM2sm6YjAR8hevgR6p6zaZu9b+z2Ribq5OemT5mWgl9bjGcux6bcVaXY84VD4FXACWYbrmmJ6GizrpoVJ/CMHA3Z62aApDzfxj1nx/8Xl9evKo3EYg86usRmPBrb4DKB/jEG++y5lIVKz2DFGqyBd7xv1KEI2ikJ7XmNNmd3dyu2yAnY7UXCysEHv8OeGOtkDoZzYdvoEgTyZIJZXQDQ+1aptoDg6agFQzIR8lQdhZWXZkXDsARgP9vF87YRHAWSw2pEl+AtgtGZR79qMbKTCax0oqxKbsiXAYdKEhvi4UZ229apbT62eZrSnHbTIuL4ezng==";
	expected = "A+EhxVXyQgcIAs+GseI0z5+DvhyGYFLOTe8MDVM3Hh4mYyZVO35TgSQ59bXE66BzZVBYdCgx6/hJ5eBDNOpSIWeX5I8oVsNis3D2nazmogjbIV6Ukq5rNpZNXz/124dEaYQBvxLNntJGCNmGLZ3+py1cW20M8lKu47nT1owVlxQE5zdE+IrHn/gbTjzG+GZtf9arPeuqD8cyURvXW2WWuA8rRt93B/v042m9FWCZuwf2Ygh3R5J08ykpA1mUL4/tk6ZCwcZk3do0wCFgShi8XAF2Wj58EuNRiT3NS7/JFmI4oEtf1Xq4SAm61COAsK73Qd+JO5laU/gsxg==";
	assertTrue(actual.length() == 336);
	assertEquals(expected, actual);
    }

    @Test
    void testMM() {
	IBloomHash bloomHash = BloomHashFactory.getDefault();
	String actual = bloomHash.createBase64Result("Max", "Mustermann", 'm', 1976, 2, 1);
	String expected;
	expected = "3ftf0sARpzDlWums3xF39nOsITaAyDTdvg88bmTYbrQ+eOdRGViE9bBFYul7Kcl6DQ+0UDJHJBC3YZp8qbCHrlfRA1pcGMHW2P60dUjDzdgynXz/+Pncc7PpyUYAfbXuQOPhCppCIRdhkh73YLGIeoysDTOizkt55vXrNQmIwc7zALFi9UgGq2xAhA+vvFyjUmr9EeGnrlItYjcU5JXKn27ILeeRLgu3YotuiQC7NAQpoJ4WZIxRbXNc7S7gNod7FNr7ME1BOjzco9nf5qEEORgKYrJLrTiWx2lZhIb8uaCAfLIj3o/W90sU/rPj+2+KKWnPxDT0L5azrg==";
	expected = "M+M0hRf75m+/Q98FIXFGLryBNFtGSBJOTasJzVc3dhwkYp5ciVbjkRB78SFVw4C3BXK+I1wz6fdR4ZEH+GIWK2sTpxPoK+VOoWh93SeEIBBdBloxtKxTtg4dD19d2gKErQiH/9P9xeJGBNyGqx/VZykr+/xg/sOth4FH1rCejMRBdZ9G6ILnz8hbXmxK+MVplcjLPGquHeYQVUdW2ua35Kciht9vNnn8ppml1maj6yG2O0xXZpVmdXnoM3jUCQ1916VByUdgjcCV0NHpyBSsBEJV+jZ406LBSybMCDnPTm68JM511WOzVskOPmbIsL7ldUWLK5EXUtIsxg==";
	assertTrue(actual.length() == 336);
	assertEquals(expected, actual);
    }

    @Test
    void testTTM() {
	IBloomHash bloomHash = BloomHashFactory.getDefault();
	String actual = bloomHash.createBase64Result("Test", "Tester", 'm', 1987, 9, 25);
	String expected;
	expected = "fxqWVtZzGf6Ebn/d22Sy9AAliezAtAH5mV4LDizcCLZ/dIH9ATg14fReT2paC6tUXIZkJNp/PMlXKACXzoPuSmCbAxZIeBbyPFRs9VhDTr3w0r7+bSh56TbOO0JC7inEQrt1G+zIlxN7H4x/BDvE7A4sSBeB6E/x1uUTsBWqh5bwfuBH5hC0bk3nHFHe3kxbEsENDVltxgo7K1eQtPQFrlqJP+vfKwGHZsBPzse3AiBFs863hki7SfLMbd4gO5aLVnqx0aVJPg6w5klsfkYfDFEucDpvDymSuTTpWeLhlTgcPpa+pQmGp+P84KJmlba6z8CffTLGBxMfog==";
	expected = "IyeATQyvbRq9hP52I58nPd1AQDZuYBor+fV57F1jJj0IRE5cFlTbhRC5PwWH0/JOU3ruAsQx21fQFdVhVXVSqykTgzk4ZuQKgdx+3O/k5ET8GdcRVCJzYK4d513lkRukPTSiT31r5sJGFdoea4+BN2ylD3xA1xE0k4vO5t3LFP1dt0tE7ELW5eoPD2gOWI3pEceqpKGLHFcaAIj2i5lUPDlv781GJFnJkxs1tGr1tyW2Y8ZHRB+mNlGsRfDYjQW91xdBKMZurVKVENjB2jCt1mRW1PZu+GOoq7zInPucJty/i9I2RWe5SmGkdmSZaHLlR9tIDdXOVdIM5w==";
	assertTrue(actual.length() == 336);
	assertEquals(expected, actual);
    }

    @Test
    void testTTW() {
	IBloomHash bloomHash = BloomHashFactory.getDefault();
	String actual = bloomHash.createBase64Result("Testerin", "Tester", 'f', 1970, 1, 1);
	String expected;
	expected = "bRtWF95bGfyEamt9WiDz1DAlIezhvACZlV49DizSDKYNdoD0ASCkx5RwTy1aWqdoRYY8BM53bggSKIjnzpPuX2QpYh5J2FDQvdx++RprSvzgl/7+ZOFZaDJ/HEYi/mPkRmvFzGzqL5N7Pv88wjsUaawsSBMMzE/TzrWbkZGqFZZU+qLm5fxA6TzGXtGYnGz4GohNBUkt/gkwL+eUtOGGzh5NL8PXCgGH5slDjsf6gyX5qfokp8iqSfpMXa5I4he6eh9wZKdBf65Qxcn6cp4/BFs88jt/D8CWqRSqmbvglbgcIgY/rZxk50f8aIZL92K4SKAn7yLfD4I/pg==";
	expected = "M+ekzRCvbA5chP70oddkrf9ZtjN6VBIvOcQ8Ld8nRj6kwZ8chBDLgSDwqzUH06IzX1g+A/gx+dVdMdUhkO1yoicTgImAFuQOkdB+3a/kQD/MAFcRVKh7doYYb29V0TKEbXYiT5fZ1OZmPJsXqw6BNywFx3pS0pEwx4MW1t3aNzBZd+tmbIPn9/hfbshO9aVpyc6L+omuHAcalIeeSomWvK8ixs9mIlvYh3snlW6G7QenYobbcnXmejmkU9iQCgU9U5VJWEZq7MolEPDEwBCkxgZ03nZk+eOAi7ft2v+ZZsY7idJERXe7zKmkHuKZqMbld84rPYHH1Risxg==";
	assertTrue(actual.length() == 336);
	assertEquals(expected, actual);
    }


}
