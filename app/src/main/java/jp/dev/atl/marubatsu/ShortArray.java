package jp.dev.atl.marubatsu;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Sashida on 2014/12/10.
 * short プリミティブ型を格納できる ArrayList と似たインターフェースのコンテナ.
 * 大きな配列を小メモリで確保したいときに使う.
 */
public class ShortArray implements java.util.RandomAccess, Iterable<Short>
{
	private int m_size = 0;
	private int m_capacity = 0;
	private short[] m_arr = null;

	public ShortArray() {}

	public short get(int i) { return m_arr[i]; }
	public  void set(int i, short val) { m_arr[i] = val; }

	public boolean isEmpty() { return m_size == 0; }

	public void clear()
	{
		m_size = 0;
		m_capacity = 0;
		m_arr = null;
	}

	public int size() { return m_size; }

	public void add(short val)
	{
		if((m_size + 1) > m_capacity) { ensureCapacity((m_size + 1) * 2); }
		m_arr[m_size] = val;
		m_size += 1;
	}

	public void resize(int size)
	{
		ensureCapacity(size);
		m_size = size;
	}

	public void ensureCapacity(int size)
	{
		if(size > m_capacity)
		{
			m_capacity = size;
			short[] temp = new short[m_size];
			for(int i = 0; i < m_size; ++i) { temp[i] = m_arr[i]; }

			m_arr = new short[m_capacity];
			for(int i = 0; i < m_size; ++i) { m_arr[i] = temp[i]; }
		}
	}

	public String toString()
	{
		String s = "";
		s += "size = " + m_size + "\ncapacity = " + m_capacity + "\n";
		for(int i = 0; i < m_size; ++i)
		{
			s += "[" + i + "] = " + m_arr[i] + "\n";
		}
		return s;
	}

	@Override
	public Iterator<Short> iterator() {
		return new Iterator<Short>() {
			int idx;
			@Override
			public boolean hasNext() {
				return idx < m_size;
			}

			@Override
			public Short next() {
				return m_arr[idx++];
			}

			@Override
			public void remove() {
				// do nothing
			}
		};
	}
}
