/* 
 * Copyright (C) 2017, Rockwell Collins
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the 3-clause BSD license.  See the LICENSE file for details.
 * 
 */
package fuzzm.engines.messages;

import fuzzm.engines.EngineName;

/**
 * The Unsat message indicates that the constraint is not solvable.
 * 
 * Generated by the Solver Engine.
 * Consumed by the Test Heuristic Engines.
 */
public class UnsatMessage extends FeatureMessage {

    final double time;
	public UnsatMessage(EngineName source, FeatureID id, String name, long sequence, double time) {
		super(source,QueueName.UnsatMessage,id,name,sequence);
		this.time = time;
	}

	public UnsatMessage(EngineName source, ConstraintMessage m, double time) {
		this(source,m.id,m.name,m.sequence,time);
	}
	
	@Override
	public void handleAccept(MessageHandler handler) {
		handler.handleMessage(this);
	}

	@Override
	public String toString() {
		return "Message: [UNSAT] " + sequence + ":" + id + " Time = " + time/1000.0 + " s"; 
	}

}
