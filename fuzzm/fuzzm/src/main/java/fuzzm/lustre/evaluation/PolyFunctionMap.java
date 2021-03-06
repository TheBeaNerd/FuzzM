/* 
 * Copyright (C) 2017, Rockwell Collins
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the 3-clause BSD license.  See the LICENSE file for details.
 * 
 */
package fuzzm.lustre.evaluation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fuzzm.util.Rat;
import fuzzm.util.RatVect;
import fuzzm.util.TypedName;

public class PolyFunctionMap {

	private class FunctionMap {
		List<TypedName> args;
		TypedName value;
		public FunctionMap(List<TypedName> args, TypedName value) {
			this.args = args;
			this.value = value;
		}
	}
	
	Map<String,List<FunctionMap>> fmaplist;
	
	public PolyFunctionMap() {
		fmaplist = new HashMap<>();
	}
	
	public FunctionLookupEV updateFunctions(RatVect env, FunctionLookupEV oldfns) {
		FunctionLookupEV res = new FunctionLookupEV(oldfns.fsig);
		for (String fn: fmaplist.keySet()) {
			for (FunctionMap fmap: fmaplist.get(fn)) {
				EvaluatableArgList args = new EvaluatableArgList();
				for (TypedName arg: fmap.args) {
					args.add(Rat.ValueFromTypedFraction(arg.type,env.get(arg)));
				}
				res.set(fn, args, Rat.ValueFromTypedFraction(fmap.value.type,env.get(fmap.value)));
			}
		}
		return res;
	}

	public void updateFnMap(String fn, List<TypedName> args, TypedName value) {
		List<FunctionMap> Lfmap = (fmaplist.containsKey(fn)) ? fmaplist.get(fn) : new ArrayList<>();
		Lfmap.add(new FunctionMap(args,value));
		fmaplist.put(fn, Lfmap);
	}
}
