package com.nisum;

import java.util.Comparator;

public class ZipCodeComparator implements Comparator<ZipCode> {

	@Override
	public int compare(ZipCode  zc1, ZipCode  zc2) {

		if (zc1.getLowerBound() > zc2.getLowerBound())
			return 1;
		else
			return -1;
	}
}
