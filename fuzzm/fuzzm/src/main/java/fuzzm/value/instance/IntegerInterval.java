/* 
 * Copyright (C) 2017, Rockwell Collins
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the 3-clause BSD license.  See the LICENSE file for details.
 * 
 */
package fuzzm.value.instance;

import java.math.BigInteger;

import fuzzm.value.hierarchy.BooleanType;
import fuzzm.value.hierarchy.EvaluatableType;
import fuzzm.value.hierarchy.InstanceType;
import fuzzm.value.hierarchy.IntegerIntervalType;
import fuzzm.value.hierarchy.IntegerType;
import fuzzm.value.hierarchy.RationalType;
import jkind.util.BigFraction;

public class IntegerInterval extends IntegerIntervalType {

	IntegerType min;
	IntegerType max;
	
	public static final IntegerInterval INFINITE_INTERVAL = new IntegerInterval(IntegerInfinity.NEGATIVE_INFINITY,IntegerInfinity.POSITIVE_INFINITY);

	public IntegerInterval(IntegerType min, IntegerType max) {
		assert(! min.equals(max));
		this.min = min;
		this.max = max;
	}

	@Override
	public IntegerType getLow() {
		return min;
	}

	@Override
	public IntegerType getHigh() {
		return max;
	}

	@Override
	public EvaluatableType<IntegerType> newInterval(InstanceType<IntegerType> min, InstanceType<IntegerType> max) {
		if (min.equals(max)) return min;
		return new IntegerInterval((IntegerType) min, (IntegerType) max);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (! (obj instanceof IntegerInterval))
			return false;
		IntegerInterval other = (IntegerInterval) obj;
		return max.equals(other.max) && min.equals(other.min);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((max == null) ? 0 : max.hashCode());
		result = prime * result + ((min == null) ? 0 : min.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "[" + min + "," + max +  "]";
	}

	@Override
	public IntegerType newIntegerType(BigInteger value) {
		return new IntegerValue(value);
	}

	@Override
	public RationalType newRationalType(BigFraction value) {
		return new RationalValue(value);
	}

	@Override
	public BooleanType newBooleanType(boolean value) {
		return value ? BooleanValue.TRUE : BooleanValue.FALSE ;
	}

	@Override
	public EvaluatableType<BooleanType> newBooleanInterval() {
		return BooleanInterval.ARBITRARY;
	}

}
