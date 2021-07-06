package io.github.dway17.codex_bloom;

import java.util.BitSet;

public class BitSetFixedSize extends java.util.BitSet {

    private static final long serialVersionUID = 818046097952037945L;

    private int fixedSize;

    public BitSetFixedSize() {
	throw new IndexOutOfBoundsException();
    }

    public BitSetFixedSize(int i) {
	super(i);
	fixedSize = i;
    }

    @Override
    public void clear(int bitIndex) {
	if (bitIndex > fixedSize) {
	    throw new IndexOutOfBoundsException();
	}
	super.clear(bitIndex);
    }

    @Override
    public void clear(int fromIndex, int toIndex) {
	if (fromIndex > fixedSize || toIndex > fixedSize) {
	    throw new IndexOutOfBoundsException();
	}
	super.clear(fromIndex, toIndex);
    }

    public int fixedSize() {
	return fixedSize;
    }

    @Override
    public void flip(int bitIndex) {
	if (bitIndex > fixedSize) {
	    throw new IndexOutOfBoundsException();
	}
	super.flip(bitIndex);
    }

    @Override
    public void flip(int fromIndex, int toIndex) {
	if (fromIndex > fixedSize || toIndex > fixedSize) {
	    throw new IndexOutOfBoundsException();
	}
	super.flip(fromIndex, toIndex);
    }

    @Override
    public boolean get(int bitIndex) {
	if (bitIndex > fixedSize) {
	    throw new IndexOutOfBoundsException();
	}
	return super.get(bitIndex);
    }

    @Override
    public BitSet get(int fromIndex, int toIndex) {
	if (fromIndex > fixedSize || toIndex > fixedSize) {
	    throw new IndexOutOfBoundsException();
	}
	return super.get(fromIndex, toIndex);
    }

    @Override
    public void set(int bitIndex) {
	if (bitIndex > fixedSize) {
	    throw new IndexOutOfBoundsException();
	}
	super.set(bitIndex);
    }

    @Override
    public void set(int bitIndex, boolean value) {
	if (bitIndex > fixedSize) {
	    throw new IndexOutOfBoundsException();
	}
	super.set(bitIndex, value);
    }

    @Override
    public void set(int fromIndex, int toIndex) {
	if (fromIndex > fixedSize || toIndex > fixedSize) {
	    throw new IndexOutOfBoundsException();
	}
	super.set(fromIndex, toIndex);
    }

    @Override
    public void set(int fromIndex, int toIndex, boolean value) {
	if (fromIndex > fixedSize || toIndex > fixedSize) {
	    throw new IndexOutOfBoundsException();
	}
	super.set(fromIndex, toIndex, value);
    }


}
