/*
 * Copyright 2014 Carsten Rambow.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.elomagic.vaadin.addon.networkgraph;

import java.util.List;

/**
 * The data class of the Graph is an object which can contain different types of data.
 */
public final class Data {
    private GraphNode[] nodes;
    private Edge[] edges;

    public Data() {
    }

    public Data(final List<GraphNode> nodes, final List<Edge> edges) {
        this.nodes = nodes.toArray(new GraphNode[0]);
        this.edges = edges.toArray(new Edge[0]);
    }

    public void setNodes(final GraphNode[] nodes) {
        this.nodes = nodes;
    }

    public GraphNode[] getNodes() {
        return nodes;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public void setEdges(final Edge[] edges) {
        this.edges = edges;
    }
}
