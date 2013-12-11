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
window.de_elomagic_vaadin_addon_networkgraph_NetworkGraph = function() {

    // end

    var e = this.getElement();
    var self = this;

    // Handle changes from the server-side
    this.onStateChange = function() {
        e.innerHTML = "<div id=\"netgraph\" style=\"width: " + this.getState().width + "; height:" + this.getState().height + "\"></div><div id=\"log\"></div>";

        var data = this.getState().data;

        var model = {
            nodes: new Array().concat(data.nodes),
            edges: new Array().concat(data.edges)
        };

        var options = {
            width: this.getState().width,
            height: this.getState().height
        };

        var container = document.getElementById('netgraph');
        var graph = new vis.Graph(container, model, options);
        vis.events.addListener(graph, 'select', function onSelect() {
            self.onSelectNodes(graph.getSelection());
        });

    };



    // Pass user interaction to the server-side
    //var self = this;
    //mycomponent.click = function() {
    //    self.onClick(mycomponent.getValue());
    //};
};
