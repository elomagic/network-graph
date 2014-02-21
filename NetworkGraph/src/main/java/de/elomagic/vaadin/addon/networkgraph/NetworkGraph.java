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

    public void setData(final Data data) {
        data.setCommand(DataCommand.SetData);
        getState().data = data;
    }

    public void addData(final GraphNode[] nodes, final Edge[] edges) {
        Data data = new Data();
        data.setCommand(DataCommand.AddData);
        data.setNodes(nodes);
        data.setEdges(edges);

        getState().data = data;
    }

    public void addNodes(final GraphNode[] nodes) {
        Data data = new Data();
        data.setCommand(DataCommand.AddData);
        data.setNodes(nodes);
        getState().data = data;
    }

    public void setNodes(final GraphNode[] nodes) {
        Data data = new Data(Arrays.asList(nodes), Collections.EMPTY_LIST);
        data.setCommand(DataCommand.SetNodes);
        getState().data = data;
    }

    public void updateNodes(final GraphNode[] nodes) {
        Data data = new Data();
        data.setCommand(DataCommand.UpdateNodes);
        data.setNodes(nodes);
        getState().data = data;
    }

    public void addEdges(final Edge[] edges) {
        Data data = new Data();
        data.setCommand(DataCommand.AddEdges);
        data.setEdges(edges);
        getState().data = data;
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
     * Returns list of selected node Id's.
     *
     * @return List of id's
     */
    public List<String> getSelectedItems() {
        return selectedItems;
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
