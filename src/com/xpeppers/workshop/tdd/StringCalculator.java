package com.xpeppers.workshop.tdd;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

	private static final String DEFAULT_DELIMITERS_REGEX = "[\\n|,]";

	public int add(String delimiterAndNumbers) {
		if (delimiterAndNumbers.isEmpty())
			return 0;

		HeaderParser headerParser = new HeaderParser(delimiterAndNumbers, DEFAULT_DELIMITERS_REGEX);
		return sumAllSeparatedBy(headerParser.delimiter(), headerParser.allButHeader());
	}

	private int sumAllSeparatedBy(String delimiter, String numbersSeparatedByDelimiter) {
		String[] addendums = numbersSeparatedByDelimiter.split(delimiter);
		return sumAll(addendums);
	}

	private int sumAll(String[] addendums) {
		int sum = 0;
		List<String> negatives = new ArrayList<>();
		for (String addendum : addendums) {
			int signedNumber = Integer.parseInt(addendum);
			if (signedNumber < 0)
				negatives.add(addendum);
			sum += signedNumber;
		}
		if (!negatives.isEmpty())
			throw new IllegalArgumentException("negatives not allowed: " + String.join(", ", negatives));
		return sum;
	}

}
