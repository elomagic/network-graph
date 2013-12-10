/* 
 * Copyright 2013 carstenrambow.
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

import org.json.JSONException;

/**
 *
 */
public final class Data {
    private Node[] nodes;
    private Edge[] edges;

    public Data() {
    }

    public Data(final List<Node> nodes, final List<Edge> edges) throws JSONException {
        this.nodes = nodes.toArray(new Node[0]);
        this.edges = edges.toArray(new Edge[0]);
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }

    public Node[] getNodes() {
        return nodes;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public void setEdges(Edge[] edges) {
        this.edges = edges;
    }

}