package com.nisum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ZipMain {

	private static Scanner scan;
	private static ZipCode zipCode = null;
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		String zipcodeRanges = scan.nextLine();
		String[] zipcodeIntervals = zipcodeRanges.split(" ");

		List<ZipCode> zipcodesList = new ArrayList<ZipCode>();
		for (int i = 0; i < zipcodeIntervals.length; i++) {
			zipcodesList.add(splitIntoZip(zipcodeIntervals[i].replaceAll("\\[|\\]", "").split(",")));
		}

		Collections.sort(zipcodesList, new ZipCodeComparator());
		List<ZipCode> mergedZipcodeList = new ArrayList<ZipCode>();
		for (ZipCode newZipCode : zipcodesList) {
			if (zipCode == null)
				zipCode = newZipCode;
			else {
				if (zipCode.getUpperBound() >= newZipCode.getLowerBound()) {
					zipCode.setUpperBound(Math.max(zipCode.getUpperBound(), newZipCode.getUpperBound()));
				} else {
					mergedZipcodeList.add(zipCode);
					zipCode = newZipCode;
				}
				mergedZipcodeList.add(zipCode);
			}
		}

		for (ZipCode zipcode : mergedZipcodeList) {
			System.out.println("[" + zipcode.getLowerBound() + "," + zipcode.getUpperBound() + "]");
		}
	}

	private static ZipCode splitIntoZip(String[] splitZip) {

		int lowerBound = Integer.parseInt(splitZip[0]);
		int upperBound = Integer.parseInt(splitZip[1]);

		return new ZipCode(lowerBound, upperBound);
	}

}
