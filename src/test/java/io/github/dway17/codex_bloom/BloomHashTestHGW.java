
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
	String expected = "h3le06jR5/HM2sm6YjAR8hevgR6p6zaZu9b+z2Ribq5OemT5mWgl9bjGcux6bcVaXY84VD4FXACWYbrmmJ6GizrpoVJ/CMHA3Z62aApDzfxj1nx/8Xl9evKo3EYg86usRmPBrb4DKB/jEG++y5lIVKz2DFGqyBd7xv1KEI2ikJ7XmNNmd3dyu2yAnY7UXCysEHv8OeGOtkDoZzYdvoEgTyZIJZXQDQ+1aptoDg6agFQzIR8lQdhZWXZkXDsARgP9vF87YRHAWSw2pEl+AtgtGZR79qMbKTCax0oqxKbsiXAYdKEhvi4UZ229apbT62eZrSnHbTIuL4ezng==";
	assertTrue(actual.length() == 336);
	assertEquals(expected, actual);
    }

    @Test
    void testMM() {
	IBloomHash bloomHash = BloomHashFactory.getDefault();
	String actual = bloomHash.createBase64Result("Max", "Mustermann", 'm', 1976, 2, 1);
	String expected = "3ftf0sARpzDlWums3xF39nOsITaAyDTdvg88bmTYbrQ+eOdRGViE9bBFYul7Kcl6DQ+0UDJHJBC3YZp8qbCHrlfRA1pcGMHW2P60dUjDzdgynXz/+Pncc7PpyUYAfbXuQOPhCppCIRdhkh73YLGIeoysDTOizkt55vXrNQmIwc7zALFi9UgGq2xAhA+vvFyjUmr9EeGnrlItYjcU5JXKn27ILeeRLgu3YotuiQC7NAQpoJ4WZIxRbXNc7S7gNod7FNr7ME1BOjzco9nf5qEEORgKYrJLrTiWx2lZhIb8uaCAfLIj3o/W90sU/rPj+2+KKWnPxDT0L5azrg==";
	assertTrue(actual.length() == 336);
	assertEquals(expected, actual);
    }

    @Test
    void testTTM() {
	IBloomHash bloomHash = BloomHashFactory.getDefault();
	String actual = bloomHash.createBase64Result("Test", "Tester", 'm', 1987, 9, 25);
	String expected = "fxqWVtZzGf6Ebn/d22Sy9AAliezAtAH5mV4LDizcCLZ/dIH9ATg14fReT2paC6tUXIZkJNp/PMlXKACXzoPuSmCbAxZIeBbyPFRs9VhDTr3w0r7+bSh56TbOO0JC7inEQrt1G+zIlxN7H4x/BDvE7A4sSBeB6E/x1uUTsBWqh5bwfuBH5hC0bk3nHFHe3kxbEsENDVltxgo7K1eQtPQFrlqJP+vfKwGHZsBPzse3AiBFs863hki7SfLMbd4gO5aLVnqx0aVJPg6w5klsfkYfDFEucDpvDymSuTTpWeLhlTgcPpa+pQmGp+P84KJmlba6z8CffTLGBxMfog==";
	assertTrue(actual.length() == 336);
	assertEquals(expected, actual);
    }

    @Test
    void testTTW() {
	IBloomHash bloomHash = BloomHashFactory.getDefault();
	String actual = bloomHash.createBase64Result("Testerin", "Tester", 'f', 1970, 1, 1);
	String expected = "bRtWB95bGfyGamt9WiHzVDAlJezhvACZlV4tDizSFKYN5oD0ASCmxxRwSy1SWqdqVZI9BN53bggSKIjnzpPqX2QpYh5J2FDSvdx++Rp7SPzAn/7eJOFZaDB/HEUi/mPkVmvFzGjqL5N7Pv88wjsUeawsSBMMyc3SzrWbkbGqFJZV+qLm5fhA6TzGXtWYnKT4GohNRc0t+ikwL+XUlOGGzh5NK8PXCgCH7MlDjsf6iyX5qfokp8iqSfruX65IYhe6eh9wZqdBf65Qxcn6cpovBFs88jtfD8CUqRSqmbnklbBNIgY/rZxk5Vf8aIRL90K4WKAn7yLfD4I/pw==";
	assertTrue(actual.length() == 336);
	assertEquals(expected, actual);
    }


}
