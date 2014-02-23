package de.elomagic.vaadin.addon.networkgraph.demo;

import java.util.Arrays;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.json.JSONException;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.elomagic.vaadin.addon.networkgraph.Data;
import de.elomagic.vaadin.addon.networkgraph.Edge;
import de.elomagic.vaadin.addon.networkgraph.GraphNode;
import de.elomagic.vaadin.addon.networkgraph.NetworkGraph;

@Theme("demo")
@Title("NetworkGraph Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    private int id = 1;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(final VaadinRequest request) {
        try {
            Edge edge = new Edge("node1", "node2");
            //edge.setStyle("dash-line");

            GraphNode node1 = new GraphNode("node1", "Node 1");
            node1.setTitle("blablabla<br/>blubb");

            GraphNode node3 = new GraphNode("node3", "Node 3");
            node3.setShape("square");

            List<GraphNode> nodes = Arrays.asList(node1, new GraphNode("node2", "Node 2"), node3);
            List<Edge> edges = Arrays.asList(edge, new Edge("node1", "node3"));

            Data data = new Data(nodes, edges);

            // Initialize our new UI component
            final NetworkGraph networkGraph = new NetworkGraph();
            networkGraph.setSizeFull();
            networkGraph.setData(data);
            networkGraph.addSelectListener(new NetworkGraph.SelectListener() {

                @Override
                public void nodeSelect(final NetworkGraph.SelectEvent event) {
                    Notification.show("Nodes selected: " + Arrays.toString(event.getNodesId().toArray()));
                }
            });

            Button btnRedraw = new Button("Redraw()", new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    networkGraph.redraw();
                }
            });

            Button btnAddNodes = new Button("add Nodes/Edges", new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    try {
                        GraphNode[] nodes = new GraphNode[] {
                            new GraphNode("n" + Integer.toString(id++), ""),
                            new GraphNode("n" + Integer.toString(id++), "")
                        };

                        networkGraph.addNodes(nodes);

                        Edge[] edges = new Edge[] {
                            new Edge("node1", "n" + Integer.toString(id - 2)),
                            new Edge("node1", "n" + Integer.toString(id - 1))
                        };

                        networkGraph.addEdges(edges);
                    } catch(JSONException ex) {
                        ex.printStackTrace(System.err);
                    }
                }
            });

            HorizontalLayout hLayout = new HorizontalLayout(btnRedraw, btnAddNodes);
            hLayout.setSizeUndefined();

            // Show it in the middle of the screen
            final VerticalLayout layout = new VerticalLayout();
            layout.setStyleName("demoContentLayout");
            layout.setSizeFull();
            layout.addComponents(networkGraph, hLayout);
            layout.setComponentAlignment(networkGraph, Alignment.MIDDLE_CENTER);
            setContent(layout);
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

}
