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
window.de_elomagic_vaadin_addon_networkgraph_NetworkGraph = function() {

    var nodes, edges, graph;

    var e = this.getElement();
    var self = this;

    // Handle changes from the server-side
    this.onStateChange = function() {
        var state = this.getState();
        var command = state.data.command;

        if (command == "SetData") {
            e.innerHTML = "<div id=\"netgraph\" style=\"width: " + state.width + "; height:" + state.height + "\"></div><div id=\"log\"></div>";

            nodes = new vis.DataSet();
            nodes.add(new Array().concat(state.data.nodes));

            edges = new vis.DataSet();
            edges.add(new Array().concat(state.data.edges));

            var model = {
                nodes: nodes,
                edges: edges
            };

            var options = {
                width: state.width,
                height: state.height
            };

            var container = document.getElementById('netgraph');
            graph = new vis.Graph(container, model, options);
            graph.on('select', function(properties) {
                self.onSelectNodes(properties.nodes);
            });
        } else if (command == "AddData") {
            nodes.add(new Array().concat(state.data.nodes));
            edges.add(new Array().concat(state.data.edges));
        } else if (command == "AddNodes") {
            nodes.add(new Array().concat(state.data.nodes));
        } else if (command == "UpdateNodes") {
            nodes.update(new Array().concat(state.data.nodes));
        } else if (command == "AddEdges") {
            edges.add(new Array().concat(state.data.edges));
        }
    };
};
