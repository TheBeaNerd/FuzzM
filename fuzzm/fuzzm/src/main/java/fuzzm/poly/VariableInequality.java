/* 
 * Copyright (C) 2017, Rockwell Collins
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the 3-clause BSD license.  See the LICENSE file for details.
 * 
 */
package fuzzm.poly;

abstract public class VariableInequality extends VariableRelation {

	protected VariableInequality(VariableID name, boolean cex, RelationType relation, AbstractPoly poly, FeatureType feature, TargetType target) {
		super(name,cex,relation,poly,feature,target);
	}
	
	abstract VariableInequality chooseBestCompliment(VariableInterval arg);
	
	@Override
	public boolean requiresRestriction() {
		return false;
	}

	@Override
	public RestrictionResult restriction() {
		throw new IllegalArgumentException();
	}

	
}
