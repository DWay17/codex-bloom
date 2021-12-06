package io.github.dway17.codex_bloom;

import java.util.BitSet;

public class BitSetFixedSize extends java.util.BitSet {

	private static final long serialVersionUID = 818046097952037945L;

	private int fixedSize;

	public BitSetFixedSize() {
		throw new IndexOutOfBoundsException();
	}

	/** Creates a bit set whose initial size is large enough to explicitlyrepresent bits with indices in the range 0
	 * through nbits-1. All bits are initially false.
	 * 
	 * @throws NegativeArraySizeException - if the specified initial sizeis negative
	 * @param nbits the initial size of the bit set */
	public BitSetFixedSize(int nbits) {
		super(nbits);
		fixedSize = nbits;
	}

	@Override
	public void clear(int bitIndex) {
		if (bitIndex >= fixedSize) {
			throw new IndexOutOfBoundsException();
		}
		super.clear(bitIndex);
	}

	@Override
	public void clear(int fromIndex, int toIndex) {
		if (fromIndex >= fixedSize || toIndex >= fixedSize) {
			throw new IndexOutOfBoundsException();
		}
		super.clear(fromIndex, toIndex);
	}

	public int fixedSize() {
		return fixedSize;
	}

	@Override
	public void flip(int bitIndex) {
		if (bitIndex >= fixedSize) {
			throw new IndexOutOfBoundsException();
		}
		super.flip(bitIndex);
	}

	@Override
	public void flip(int fromIndex, int toIndex) {
		if (fromIndex >= fixedSize || toIndex > fixedSize) {
			throw new IndexOutOfBoundsException();
		}
		super.flip(fromIndex, toIndex);
	}

	@Override
	public boolean get(int bitIndex) {
		if (bitIndex >= fixedSize) {
			throw new IndexOutOfBoundsException();
		}
		return super.get(bitIndex);
	}

	@Override
	public BitSet get(int fromIndex, int toIndex) {
		if (fromIndex >= fixedSize || toIndex > fixedSize) {
			throw new IndexOutOfBoundsException();
		}
		return super.get(fromIndex, toIndex);
	}

	@Override
	public void set(int bitIndex) {
		if (bitIndex >= fixedSize) {
			throw new IndexOutOfBoundsException();
		}
		super.set(bitIndex);
	}

	@Override
	public void set(int bitIndex, boolean value) {
		if (bitIndex >= fixedSize) {
			throw new IndexOutOfBoundsException();
		}
		super.set(bitIndex, value);
	}

	@Override
	public void set(int fromIndex, int toIndex) {
		if (fromIndex >= fixedSize || toIndex > fixedSize) {
			throw new IndexOutOfBoundsException();
		}
		super.set(fromIndex, toIndex);
	}

	@Override
	public void set(int fromIndex, int toIndex, boolean value) {
		if (fromIndex >= fixedSize || toIndex > fixedSize) {
			throw new IndexOutOfBoundsException();
		}
		super.set(fromIndex, toIndex, value);
	}


}
