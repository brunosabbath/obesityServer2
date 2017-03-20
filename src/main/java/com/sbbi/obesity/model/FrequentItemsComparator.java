package com.sbbi.obesity.model;

import java.util.Comparator;

import org.apache.commons.lang3.builder.CompareToBuilder;


public class FrequentItemsComparator implements Comparator<FrequentItems>{

	@Override
	public int compare(FrequentItems o1, FrequentItems o2) {
		return new CompareToBuilder().append(o2.getCalories(), o1.getCalories())
				.append(o2.getFrequency(), o1.getFrequency()).toComparison();
	}

}
