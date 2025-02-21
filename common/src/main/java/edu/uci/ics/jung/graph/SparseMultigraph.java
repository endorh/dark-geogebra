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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;

/**
 * An implementation of <code>Graph</code> that is suitable for sparse graphs
 * and permits directed, undirected, and parallel edges.
 */
@SuppressWarnings("serial")
public class SparseMultigraph<V, E> extends AbstractGraph<V, E>
		implements MultiGraph<V, E> {

	/**
	 * Returns a {@code Factory} that creates an instance of this graph type.
	 * 
	 * @param <V>
	 *            the vertex type for the graph factory
	 * @param <E>
	 *            the edge type for the graph factory
	 */
	public static <V, E> Factory<Graph<V, E>> getFactory() {
		return SparseMultigraph::new;
	}

	// TODO: refactor internal representation: right now directed edges each
	// have two references (in vertices and directedEdges)
	// and undirected also have two (incoming and outgoing).
	protected Map<V, Pair<Set<E>>> vertices; // Map of vertices to Pair of
												// adjacency sets {incoming,
												// outgoing}
	protected Map<E, Pair<V>> edges; // Map of edges to incident vertex pairs
	protected Set<E> directedEdges;

	/**
	 * Creates a new instance.
	 */
	public SparseMultigraph() {
		vertices = new HashMap<>();
		edges = new HashMap<>();
		directedEdges = new HashSet<>();
	}

	@Override
	public Collection<E> getEdges() {
		return Collections.unmodifiableCollection(edges.keySet());
	}

	@Override
	public Collection<V> getVertices() {
		return Collections.unmodifiableCollection(vertices.keySet());
	}

	@Override
	public boolean containsVertex(V vertex) {
		return vertices.keySet().contains(vertex);
	}

	@Override
	public boolean containsEdge(E edge) {
		return edges.keySet().contains(edge);
	}

	protected Collection<E> getIncoming_internal(V vertex) {
		return vertices.get(vertex).getFirst();
	}

	protected Collection<E> getOutgoing_internal(V vertex) {
		return vertices.get(vertex).getSecond();
	}

	@Override
	public boolean addVertex(V vertex) {
		if (vertex == null) {
			throw new IllegalArgumentException("vertex may not be null");
		}
		if (!vertices.containsKey(vertex)) {
			vertices.put(vertex,
					new Pair<>(new HashSet<>(), new HashSet<>()));
			return true;
		}
		return false;
	}

	@Override
	public boolean removeVertex(V vertex) {
		if (!containsVertex(vertex)) {
			return false;
		}

		// copy to avoid concurrent modification in removeEdge
		Set<E> incident = new HashSet<>(getIncoming_internal(vertex));
		incident.addAll(getOutgoing_internal(vertex));

		for (E edge : incident) {
			removeEdge(edge);
		}

		vertices.remove(vertex);

		return true;
	}

	@Override
	public boolean addEdge(E edge, Pair<? extends V> endpoints,
			EdgeType edgeType) {

		Pair<V> new_endpoints = getValidatedEndpoints(edge, endpoints);
		if (new_endpoints == null) {
			return false;
		}

		V v1 = new_endpoints.getFirst();
		V v2 = new_endpoints.getSecond();

		if (!vertices.containsKey(v1)) {
			this.addVertex(v1);
		}

		if (!vertices.containsKey(v2)) {
			this.addVertex(v2);
		}

		vertices.get(v1).getSecond().add(edge);
		vertices.get(v2).getFirst().add(edge);
		edges.put(edge, new_endpoints);
		if (edgeType == EdgeType.DIRECTED) {
			directedEdges.add(edge);
		} else {
			vertices.get(v1).getFirst().add(edge);
			vertices.get(v2).getSecond().add(edge);
		}
		return true;
	}

	@Override
	public boolean removeEdge(E edge) {
		if (!containsEdge(edge)) {
			return false;
		}

		Pair<V> endpoints = getEndpoints(edge);
		V v1 = endpoints.getFirst();
		V v2 = endpoints.getSecond();

		// remove edge from incident vertices' adjacency sets
		vertices.get(v1).getSecond().remove(edge);
		vertices.get(v2).getFirst().remove(edge);

		if (directedEdges.remove(edge) == false) {

			// its an undirected edge, remove the other ends
			vertices.get(v2).getSecond().remove(edge);
			vertices.get(v1).getFirst().remove(edge);
		}
		edges.remove(edge);
		return true;
	}

	@Override
	public Collection<E> getInEdges(V vertex) {
		if (!containsVertex(vertex)) {
			return null;
		}
		return Collections
				.unmodifiableCollection(vertices.get(vertex).getFirst());
	}

	@Override
	public Collection<E> getOutEdges(V vertex) {
		if (!containsVertex(vertex)) {
			return null;
		}
		return Collections
				.unmodifiableCollection(vertices.get(vertex).getSecond());
	}

	// TODO: this will need to get changed if we modify the internal
	// representation
	@Override
	public Collection<V> getPredecessors(V vertex) {
		if (!containsVertex(vertex)) {
			return null;
		}

		Set<V> preds = new HashSet<>();
		for (E edge : getIncoming_internal(vertex)) {
			if (getEdgeType(edge) == EdgeType.DIRECTED) {
				preds.add(this.getSource(edge));
			} else {
				preds.add(getOpposite(vertex, edge));
			}
		}
		return Collections.unmodifiableCollection(preds);
	}

	// TODO: this will need to get changed if we modify the internal
	// representation
	@Override
	public Collection<V> getSuccessors(V vertex) {
		if (!containsVertex(vertex)) {
			return null;
		}
		Set<V> succs = new HashSet<>();
		for (E edge : getOutgoing_internal(vertex)) {
			if (getEdgeType(edge) == EdgeType.DIRECTED) {
				succs.add(this.getDest(edge));
			} else {
				succs.add(getOpposite(vertex, edge));
			}
		}
		return Collections.unmodifiableCollection(succs);
	}

	@Override
	public Collection<V> getNeighbors(V vertex) {
		if (!containsVertex(vertex)) {
			return null;
		}
		Collection<V> out = new HashSet<>();
		out.addAll(this.getPredecessors(vertex));
		out.addAll(this.getSuccessors(vertex));
		return out;
	}

	@Override
	public Collection<E> getIncidentEdges(V vertex) {
		if (!containsVertex(vertex)) {
			return null;
		}
		Collection<E> out = new HashSet<>();
		out.addAll(this.getInEdges(vertex));
		out.addAll(this.getOutEdges(vertex));
		return out;
	}

	@Override
	public E findEdge(V v1, V v2) {
		if (!containsVertex(v1) || !containsVertex(v2)) {
			return null;
		}
		for (E edge : getOutgoing_internal(v1)) {
			if (this.getOpposite(v1, edge).equals(v2)) {
				return edge;
			}
		}

		return null;
	}

	@Override
	public Pair<V> getEndpoints(E edge) {
		return edges.get(edge);
	}

	@Override
	public V getSource(E edge) {
		if (directedEdges.contains(edge)) {
			return this.getEndpoints(edge).getFirst();
		}
		return null;
	}

	@Override
	public V getDest(E edge) {
		if (directedEdges.contains(edge)) {
			return this.getEndpoints(edge).getSecond();
		}
		return null;
	}

	@Override
	public boolean isSource(V vertex, E edge) {
		if (!containsEdge(edge) || !containsVertex(vertex)) {
			return false;
		}
		return getSource(edge).equals(vertex);
	}

	@Override
	public boolean isDest(V vertex, E edge) {
		if (!containsEdge(edge) || !containsVertex(vertex)) {
			return false;
		}
		return getDest(edge).equals(vertex);
	}

	@Override
	public EdgeType getEdgeType(E edge) {
		return directedEdges.contains(edge) ? EdgeType.DIRECTED
				: EdgeType.UNDIRECTED;
	}

	@Override
	public Collection<E> getEdges(EdgeType edgeType) {
		if (edgeType == EdgeType.DIRECTED) {
			return Collections.unmodifiableSet(this.directedEdges);
		} else if (edgeType == EdgeType.UNDIRECTED) {
			Collection<E> edges1 = new HashSet<>(getEdges());
			edges1.removeAll(directedEdges);
			return edges1;
		} else {
			return Collections.emptySet();
		}

	}

	@Override
	public int getEdgeCount() {
		return edges.keySet().size();
	}

	@Override
	public int getVertexCount() {
		return vertices.keySet().size();
	}

	@Override
	public int getEdgeCount(EdgeType edge_type) {
		return getEdges(edge_type).size();
	}

	@Override
	public EdgeType getDefaultEdgeType() {
		return EdgeType.UNDIRECTED;
	}

	@Override
	public SparseMultigraph<V, E> newInstance() {
		return new SparseMultigraph<>();
	}

}
