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

    var nodes = new vis.DataSet(),
        edges = new vis.DataSet(),
        graph;

    var e = this.getElement();
    var self = this;

    this.redraw = function() {
        graph.redraw();
    };

    this.clear = function(n) {
        nodes.clear();
    };

    this.addData = function(no, ed) {
        nodes.add([].concat(no));
        edges.add([].concat(ed));
    };

    this.setData = function(no, ed) {
        nodes = new vis.DataSet([].concat(no))
        edges = new vis.DataSet([].concat(ed))
        graph.setData({
            nodes: nodes,
            edges: edges
        })
    };

    this.addNodes = function(n) {
        nodes.add([].concat(n));
    };

    this.updateNodes = function(n) {
        nodes.update([].concat(n));
    };

    this.removeNodes = function(n) {
        nodes.remove([].concat(n));
    }

    this.addEdges = function(ed) {
        edges.add([].concat(ed));
    };

    this.setSelection = function(n) {
        graph.setSelection(n);
    }

    var state = this.getState() || {};
    var options = state.options || {};
    options['width'] = '' + e.offsetWidth + 'px'
    options['height'] = '' + e.offsetHeight + 'px'
    nodes.add([].concat(state.nodes || []))
    edges.add([].concat(state.edges || []))
    graph = new vis.Graph(e, {
        nodes: nodes,
        edges: edges
    }, options);
    graph.on('select', function(properties) {
        self.onSelectNodes(properties.nodes);
    });

    window.onresize = function() {
        graph.redraw();
    };

};
