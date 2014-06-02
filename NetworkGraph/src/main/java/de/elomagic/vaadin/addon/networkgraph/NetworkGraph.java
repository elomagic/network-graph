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

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.server.EncodeResult;
import com.vaadin.server.JsonCodec;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.JavaScriptFunction;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * NetworkGraph is a visualization to display graphs and networks consisting of nodes and edges.
 * <p/>
 * The visualization is easy to use and supports custom shapes, styles, colors, sizes, images, and more.
 * <p/>
 * The graph visualization works smooth on any modern browser for up to a few thousand nodes and edges. To handle a larger amount of nodes, Graph has clustering support.
 *
 * @author Carsten Rambow
 */
@JavaScript({"js/vis.min.js", "js/networkgraph-connector.js"})
@StyleSheet({"css/vis.css", "css/networkgraph.css"})
public class NetworkGraph extends AbstractJavaScriptComponent {
    private final List<SelectListener> selectListener = new ArrayList<>();
    private final List<String> selectedItems = new ArrayList<>();

    public NetworkGraph() {
        super();
        setStyleName("networkgraph");
        addFunction("onSelectNodes", new JavaScriptFunction() {

            @Override
            public void call(final JSONArray arguments) throws JSONException {
                JSONArray array = arguments.getJSONArray(0);
                List<String> list = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    list.add(array.getString(i));
                }

                selectedItems.clear();
                selectedItems.addAll(list);

                fireSelectNodeEvent(list);
            }
        });
    }

    private static <T> List<T> collectArgs(T item, T[] items) {
        List<T> n = new ArrayList<>(asList(items));
        n.add(item);
        return n;
    }

    public Options getOptions() {
        return getState().options;
    }

    public void setOptions(Options options) {
        getState().options = options;
        markAsDirty();
    }

    /**
     * Redraw the graph.
     * <p/>
     * Useful when the layout of the webpage changed..
     */
    public void redraw() {
        callFunction("redraw");
    }

    /**
     * Clear the complete DataSet.
     */
    public void clear() {
        callFunction("clear");
    }

    public void addNodes(Node node, Node... nodes) {
        List<Node> n = collectArgs(node, nodes);
        getState().nodes.addAll(n);
        callFunction("addNodes", new Object[]{encodeArray(n)});
    }

    /**
     * Update a node or an array with nodes.
     *
     * @param nodes
     */
    public void updateNodes(final Node node, Node... nodes) {
        List<Node> n = collectArgs(node, nodes);
        getState().nodes.addAll(n);
        callFunction("updateNodes", new Object[]{encodeArray(n)});
    }

    public void removeNodes(final String[] nodeUIDs) {
        callFunction("removeNodes", (Object[]) nodeUIDs);
    }

    public void addEdges(final Edge edge, Edge... edges) {
        List<Edge> e = collectArgs(edge, edges);
        getState().edges.addAll(e);
        callFunction("addEdges", new Object[]{encodeArray(e)});
    }

    @Override
    protected NetworkGraphState getState() {
        return (NetworkGraphState) super.getState();
    }

    /**
     * Returns an array with the ids of the selected nodes.
     * <p/>
     * Returns an empty array if no nodes are selected. The selections are not ordered.
     *
     * @return List of id's
     */
    public List<String> getSelectedItems() {
        return selectedItems;
    }

    /**
     * Select nodes.
     * <p/>
     * <code>nodes</code> is an array with ids of nodes to be selected. The array selection can contain zero or multiple ids.
     * <p/>
     * Example usage: graph.setSelection([3, 5]); will select nodes with id 3 and 5.
     *
     * @param nodes
     */
    public void setSelection(final Node node, Node... nodes) {
        callFunction("setSelection", new Object[]{encodeArray(collectArgs(node, nodes))});
    }

    public void addSelectListener(final SelectListener listener) {
        if (selectListener.contains(listener)) {
            return;
        }

        selectListener.add(listener);
    }

    public void removeSelectListener(final SelectListener listener) {
        selectListener.remove(listener);
    }

    private void fireSelectNodeEvent(final List<String> nodesId) {
        SelectEvent event = new SelectEvent(this, nodesId);

        for (SelectListener listener : selectListener) {
            listener.nodeSelect(event);
        }
    }

    private Object[] encodeArray(final Collection<?> objects) {
        List<Object> result = new ArrayList<>();

        for (Object o : objects) {
            result.add(encodeObject(o));
        }

        return result.toArray(new Object[result.size()]);
    }

    private Object encodeObject(Object o) {
        try {
            EncodeResult result = JsonCodec.encode(o, null, o.getClass(), null);
            return result.getEncodedValue();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public interface SelectListener {
        void nodeSelect(final SelectEvent event);
    }

    public class SelectEvent extends Component.Event {
        private final List<String> nodesId;

        public SelectEvent(final Component source, final List<String> nodesId) {
            super(source);

            this.nodesId = nodesId;
        }

        public List<String> getNodesId() {
            return nodesId;
        }

    }

}
