package jp.dev.atl.marubatsu;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 * Created by Sashida2 on 2014/12/10.
 */
public class Array2D<Type> extends ArrayList<Type>
{
	private int mWidth;
	private int mHeight;

	Array2D(int width, int height)
	{
		mWidth = width;
		mHeight = height;
		ensureCapacity(width * height);
	}


}
