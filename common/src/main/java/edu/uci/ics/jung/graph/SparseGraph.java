/*
 * Created on Apr 15, 2007
 *
 * Copyright (c) 2007, the JUNG Project and the Regents of the University 
 * of California
 * All rights reserved.
 *
 * This software is open-source under the BSD license; see either
 * "license.txt" or
 * http://jung.sourceforge.net/license.txt for a description.
 */
package edu.uci.ics.jung.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;

/**
 * An implementation of <code>Graph</code> that is suitable for sparse graphs
 * and permits both directed and undirected edges.
 */
@SuppressWarnings("serial")
public class SparseGraph<V, E> extends AbstractGraph<V, E>
		implements Graph<V, E> {
	/**
	 * Returns a {@code Factory} that creates an instance of this graph type.
	 * 
	 * @param <V>
	 *            the vertex type for the graph factory
	 * @param <E>
	 *            the edge type for the graph factory
	 */
	public static <V, E> Factory<Graph<V, E>> getFactory() {
		return SparseGraph::new;
	}

	protected static final int INCOMING = 0;
	protected static final int OUTGOING = 1;
	protected static final int INCIDENT = 2;

	protected Map<V, Map<V, E>[]> vertex_maps; // Map of vertices to adjacency
												// maps of vertices to
												// {incoming, outgoing,
												// incident} edges
	protected Map<E, Pair<V>> directed_edges; // Map of directed edges to
												// incident vertex sets
	protected Map<E, Pair<V>> undirected_edges; // Map of undirected edges to
												// incident vertex sets

	/**
	 * Creates an instance.
	 */
	public SparseGraph() {
		vertex_maps = new HashMap<>();
		directed_edges = new HashMap<>();
		undirected_edges = new HashMap<>();
	}

	@Override
	public E findEdge(V v1, V v2) {
		if (!containsVertex(v1) || !containsVertex(v2)) {
			return null;
		}
		E edge = vertex_maps.get(v1)[OUTGOING].get(v2);
		if (edge == null) {
			edge = vertex_maps.get(v1)[INCIDENT].get(v2);
		}
		return edge;
	}

	@Override
	public Collection<E> findEdgeSet(V v1, V v2) {
		if (!containsVertex(v1) || !containsVertex(v2)) {
			return null;
		}
		Collection<E> edges = new ArrayList<>(2);
		E e1 = vertex_maps.get(v1)[OUTGOING].get(v2);
		if (e1 != null) {
			edges.add(e1);
		}
		E e2 = vertex_maps.get(v1)[INCIDENT].get(v2);
		if (e1 != null) {
			edges.add(e2);
		}
		return edges;
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

		// undirected edges and directed edges are not considered to be parallel
		// to each other,
		// so as long as anything that's returned by findEdge is not of the same
		// type as
		// edge, we're fine
		E connection = findEdge(v1, v2);
		if (connection != null && getEdgeType(connection) == edgeType) {
			return false;
		}

		if (!containsVertex(v1)) {
			this.addVertex(v1);
		}

		if (!containsVertex(v2)) {
			this.addVertex(v2);
		}

		// map v1 to <v2, edge> and vice versa
		if (edgeType == EdgeType.DIRECTED) {
			vertex_maps.get(v1)[OUTGOING].put(v2, edge);
			vertex_maps.get(v2)[INCOMING].put(v1, edge);
			directed_edges.put(edge, new_endpoints);
		} else {
			vertex_maps.get(v1)[INCIDENT].put(v2, edge);
			vertex_maps.get(v2)[INCIDENT].put(v1, edge);
			undirected_edges.put(edge, new_endpoints);
		}

		return true;
	}

	@Override
	public Collection<E> getInEdges(V vertex) {
		if (!containsVertex(vertex)) {
			return null;
		}

		// combine directed inedges and undirected
		Collection<E> in = new HashSet<>(
				vertex_maps.get(vertex)[INCOMING].values());
		in.addAll(vertex_maps.get(vertex)[INCIDENT].values());
		return Collections.unmodifiableCollection(in);
	}

	@Override
	public Collection<E> getOutEdges(V vertex) {
		if (!containsVertex(vertex)) {
			return null;
		}

		// combine directed outedges and undirected
		Collection<E> out = new HashSet<>(
				vertex_maps.get(vertex)[OUTGOING].values());
		out.addAll(vertex_maps.get(vertex)[INCIDENT].values());
		return Collections.unmodifiableCollection(out);
	}

	@Override
	public Collection<V> getPredecessors(V vertex) {
		if (!containsVertex(vertex)) {
			return null;
		}

		// consider directed inedges and undirected
		Collection<V> preds = new HashSet<>(
				vertex_maps.get(vertex)[INCOMING].keySet());
		preds.addAll(vertex_maps.get(vertex)[INCIDENT].keySet());
		return Collections.unmodifiableCollection(preds);
	}

	@Override
	public Collection<V> getSuccessors(V vertex) {
		if (!containsVertex(vertex)) {
			return null;
		}

		// consider directed outedges and undirected
		Collection<V> succs = new HashSet<>(
				vertex_maps.get(vertex)[OUTGOING].keySet());
		succs.addAll(vertex_maps.get(vertex)[INCIDENT].keySet());
		return Collections.unmodifiableCollection(succs);
	}

	@Override
	public Collection<E> getEdges(EdgeType edgeType) {
		if (edgeType == EdgeType.DIRECTED) {
			return Collections.unmodifiableCollection(directed_edges.keySet());
		} else if (edgeType == EdgeType.UNDIRECTED) {
			return Collections
					.unmodifiableCollection(undirected_edges.keySet());
		} else {
			return null;
		}
	}

	@Override
	public Pair<V> getEndpoints(E edge) {
		Pair<V> endpoints;
		endpoints = directed_edges.get(edge);
		if (endpoints == null) {
			return undirected_edges.get(edge);
		}
		return endpoints;
	}

	@Override
	public EdgeType getEdgeType(E edge) {
		if (directed_edges.containsKey(edge)) {
			return EdgeType.DIRECTED;
		} else if (undirected_edges.containsKey(edge)) {
			return EdgeType.UNDIRECTED;
		} else {
			return null;
		}
	}

	@Override
	public V getSource(E directed_edge) {
		if (getEdgeType(directed_edge) == EdgeType.DIRECTED) {
			return directed_edges.get(directed_edge).getFirst();
		}
		return null;
	}

	@Override
	public V getDest(E directed_edge) {
		if (getEdgeType(directed_edge) == EdgeType.DIRECTED) {
			return directed_edges.get(directed_edge).getSecond();
		}
		return null;
	}

	@Override
	public boolean isSource(V vertex, E edge) {
		if (!containsVertex(vertex) || !containsEdge(edge)) {
			return false;
		}

		V source = getSource(edge);
		if (source != null) {
			return source.equals(vertex);
		}
		return false;
	}

	@Override
	public boolean isDest(V vertex, E edge) {
		if (!containsVertex(vertex) || !containsEdge(edge)) {
			return false;
		}

		V dest = getDest(edge);
		if (dest != null) {
			return dest.equals(vertex);
		}
		return false;
	}

	@Override
	public Collection<E> getEdges() {
		Collection<E> edges = new ArrayList<>(directed_edges.keySet());
		edges.addAll(undirected_edges.keySet());
		return Collections.unmodifiableCollection(edges);
	}

	@Override
	public Collection<V> getVertices() {
		return Collections.unmodifiableCollection(vertex_maps.keySet());
	}

	@Override
	public boolean containsVertex(V vertex) {
		return vertex_maps.containsKey(vertex);
	}

	@Override
	public boolean containsEdge(E edge) {
		return directed_edges.containsKey(edge)
				|| undirected_edges.containsKey(edge);
	}

	@Override
	public int getEdgeCount() {
		return directed_edges.size() + undirected_edges.size();
	}

	@Override
	public int getVertexCount() {
		return vertex_maps.size();
	}

	@Override
	public Collection<V> getNeighbors(V vertex) {
		if (!containsVertex(vertex)) {
			return null;
		}
		// consider directed edges and undirected edges
		Collection<V> neighbors = new HashSet<>(
				vertex_maps.get(vertex)[INCOMING].keySet());
		neighbors.addAll(vertex_maps.get(vertex)[OUTGOING].keySet());
		neighbors.addAll(vertex_maps.get(vertex)[INCIDENT].keySet());
		return Collections.unmodifiableCollection(neighbors);
	}

	@Override
	public Collection<E> getIncidentEdges(V vertex) {
		if (!containsVertex(vertex)) {
			return null;
		}
		Collection<E> incident = new HashSet<>(
				vertex_maps.get(vertex)[INCOMING].values());
		incident.addAll(vertex_maps.get(vertex)[OUTGOING].values());
		incident.addAll(vertex_maps.get(vertex)[INCIDENT].values());
		return Collections.unmodifiableCollection(incident);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean addVertex(V vertex) {
		if (vertex == null) {
			throw new IllegalArgumentException("vertex may not be null");
		}
		if (!containsVertex(vertex)) {
			vertex_maps.put(vertex, new HashMap[] { new HashMap<V, E>(),
					new HashMap<V, E>(), new HashMap<V, E>() });
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
		Collection<E> incident = new ArrayList<>(getIncidentEdges(vertex));

		for (E edge : incident) {
			removeEdge(edge);
		}

		vertex_maps.remove(vertex);

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

		// remove edge from incident vertices' adjacency maps
		if (getEdgeType(edge) == EdgeType.DIRECTED) {
			vertex_maps.get(v1)[OUTGOING].remove(v2);
			vertex_maps.get(v2)[INCOMING].remove(v1);
			directed_edges.remove(edge);
		} else {
			vertex_maps.get(v1)[INCIDENT].remove(v2);
			vertex_maps.get(v2)[INCIDENT].remove(v1);
			undirected_edges.remove(edge);
		}

		return true;
	}

	@Override
	public int getEdgeCount(EdgeType edge_type) {
		if (edge_type == EdgeType.DIRECTED) {
			return directed_edges.size();
		}
		if (edge_type == EdgeType.UNDIRECTED) {
			return undirected_edges.size();
		}
		return 0;
	}

	@Override
	public EdgeType getDefaultEdgeType() {
		return EdgeType.UNDIRECTED;
	}

	@Override
	public SparseGraph<V, E> newInstance() {
		return new SparseGraph<>();
	}

}
