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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.JavaScriptFunction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
@StyleSheet({"css/vis.css"})
public class NetworkGraph extends AbstractJavaScriptComponent {
    private final List<SelectListener> selectListener = new ArrayList<>();
    private final List<String> selectedItems = new ArrayList<>();

    public NetworkGraph() {
        super();

        addFunction("onSelectNodes", new JavaScriptFunction() {

            @Override
            public void call(final JSONArray arguments) throws JSONException {
                JSONArray array = arguments.getJSONArray(0);
                List<String> list = new ArrayList<>();
                for(int i = 0; i < array.length(); i++) {
                    list.add(array.getString(i));
                }

                selectedItems.clear();
                selectedItems.addAll(list);

                fireSelectNodeEvent(list);
            }
        });
    }

    /**
     * Redraw the graph.
     * <p/>
     * Useful when the layout of the webpage changed..
     */
    public void redraw() {
        getState().data.setCommand(DataCommand.None);
        callFunction("redraw");
    }

    public void setData(final Data data) {
        data.setCommand(DataCommand.Init);
        getState().data = data;
    }

    /**
     * Clear the complete DataSet.
     */
    public void clear() {
        getState().data.setCommand(DataCommand.None);
        callFunction("clear");
    }

    public void addData(final GraphNode[] nodes, final Edge[] edges) {
        getState().data.setCommand(DataCommand.None);
        callFunction("addData", toJSONArray(nodes), toJSONArray(edges));
    }

    public void addNodes(final GraphNode[] nodes) throws JSONException {
        getState().data.setCommand(DataCommand.None);
        callFunction("addNodes", new Object[] {toJSONArray(nodes)});
    }

    /**
     * Update a node or an array with nodes.
     *
     * @param nodes
     */
    public void updateNodes(final GraphNode[] nodes) {
        getState().data.setCommand(DataCommand.None);
        callFunction("updateNodes", new Object[] {toJSONArray(nodes)});
    }

    public void addEdges(final Edge[] edges) {
        getState().data.setCommand(DataCommand.None);
        callFunction("addEdges", new Object[] {toJSONArray(edges)});
    }

    public void setEdges(final Edge[] edges) {
        Data data = new Data(Collections.EMPTY_LIST, Arrays.asList(edges));
        getState().data = data;
    }

    @Override
    protected NetworkGraphState getState() {
        return (NetworkGraphState)super.getState();
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
    public void setSelection(final GraphNode[] nodes) {
        getState().data.setCommand(DataCommand.None);
        callFunction("setSelection", new Object[] {toJSONArray(nodes)});
    }

    public void addSelectListener(final SelectListener listener) {
        if(selectListener.contains(listener)) {
            return;
        }

        selectListener.add(listener);
    }

    public void removeSelectListener(final SelectListener listener) {
        selectListener.remove(listener);
    }

    private void fireSelectNodeEvent(final List<String> nodesId) {
        SelectEvent event = new SelectEvent(this, nodesId);

        for(SelectListener listener : selectListener) {
            listener.nodeSelect(event);
        }
    }

    private JSONObject[] toJSONArray(final Object[] objects) {
        List<JSONObject> result = new ArrayList();

        for(Object o : objects) {
            result.add(new JSONObject(o));
        }

        return result.toArray(new JSONObject[0]);
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

    public interface SelectListener {
        void nodeSelect(final SelectEvent event);
    }

}
