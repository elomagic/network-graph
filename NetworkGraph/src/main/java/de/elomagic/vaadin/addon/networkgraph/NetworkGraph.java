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

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.JavaScriptFunction;

@JavaScript({"js/vis.min.js", "js/networkgraph-connector.js"})
public class NetworkGraph extends AbstractJavaScriptComponent {
    private final List<SelectListener> selectListener = new ArrayList<>();

    public NetworkGraph() {
        super();

        addFunction("onSelectNode", new JavaScriptFunction() {

            @Override
            public void call(final JSONArray arguments) throws JSONException {

                fireSelectNodeEvent(arguments.getString(0));
            }
        });
    }

    public void setData(final Data data) {
        getState().data = data;
    }

    @Override
    protected NetworkGraphState getState() {
        return (NetworkGraphState)super.getState();
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

    private void fireSelectNodeEvent(final String nodeId) {
        SelectEvent event = new SelectEvent(this, nodeId);

        for(SelectListener listener : selectListener) {
            listener.nodeSelect(event);
        }
    }

    public class SelectEvent extends Component.Event {
        private final String nodeId;

        public SelectEvent(final Component source, final String nodeId) {
            super(source);

            this.nodeId = nodeId;
        }

        public String getNodeId() {
            return nodeId;
        }

    }

    public interface SelectListener {
        void nodeSelect(final SelectEvent event);
    }

}
