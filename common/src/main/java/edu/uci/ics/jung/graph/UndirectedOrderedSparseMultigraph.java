/*
 * Created on Oct 18, 2005
 *
 * Copyright (c) 2005, the JUNG Project and the Regents of the University 
 * of California
 * All rights reserved.
 *
 * This software is open-source under the BSD license; see either
 * "license.txt" or
 * http://jung.sourceforge.net/license.txt for a description.
 */
package edu.uci.ics.jung.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.graph.util.Pair;

/**
 * An implementation of <code>UndirectedGraph</code> that is suitable for sparse
 * graphs, orders its vertex and edge collections according to insertion time,
 * and permits parallel edges.
 */
@SuppressWarnings("serial")
public class UndirectedOrderedSparseMultigraph<V, E> extends
		UndirectedSparseMultigraph<V, E> implements UndirectedGraph<V, E> {

	/**
	 * Returns a {@code Factory} that creates an instance of this graph type.
	 * 
	 * @param <V>
	 *            the vertex type for the graph factory
	 * @param <E>
	 *            the edge type for the graph factory
	 */
	public static <V, E> Factory<UndirectedGraph<V, E>> getFactory() {
		return UndirectedOrderedSparseMultigraph::new;
	}

	/**
	 * Creates a new instance.
	 */
	public UndirectedOrderedSparseMultigraph() {
		vertices = new LinkedHashMap<>();
		edges = new LinkedHashMap<>();
	}

	@Override
	public boolean addVertex(V vertex) {
		if (vertex == null) {
			throw new IllegalArgumentException("vertex may not be null");
		}
		if (!containsVertex(vertex)) {
			vertices.put(vertex, new LinkedHashSet<>());
			return true;
		}
		return false;
	}

	@Override
	public Collection<V> getNeighbors(V vertex) {
		if (!containsVertex(vertex)) {
			return null;
		}

		Set<V> neighbors = new LinkedHashSet<>();
		for (E edge : getIncident_internal(vertex)) {
			Pair<V> endpoints = this.getEndpoints(edge);
			V e_a = endpoints.getFirst();
			V e_b = endpoints.getSecond();
			if (vertex.equals(e_a)) {
				neighbors.add(e_b);
			} else {
				neighbors.add(e_a);
			}
		}

		return Collections.unmodifiableCollection(neighbors);
	}
}
