package de.elomagic.vaadin.addon.networkgraph.demo;

import java.util.Arrays;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.elomagic.vaadin.addon.networkgraph.Data;
import de.elomagic.vaadin.addon.networkgraph.Edge;
import de.elomagic.vaadin.addon.networkgraph.NetworkGraph;
import de.elomagic.vaadin.addon.networkgraph.Node;

@Theme("demo")
@Title("NetworkGraph Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(final VaadinRequest request) {
        try {
            Edge edge = new Edge("node1", "node2");
            //edge.setStyle("dash-line");

            Node node1 = new Node("node1", "Node 1");
            node1.setTitle("blablabla<br/>blubb");

            List<Node> nodes = Arrays.asList(node1, new Node("node2", "Node 2"));
            List<Edge> edges = Arrays.asList(edge);

            Data data = new Data(nodes, edges);

            // Initialize our new UI component
            final NetworkGraph component = new NetworkGraph();
            component.setSizeFull();
            component.setData(data);
            component.addSelectListener(new NetworkGraph.SelectListener() {

                @Override
                public void nodeSelect(final NetworkGraph.SelectEvent event) {
                    Notification.show("Nodes selected: " + Arrays.toString(event.getNodesId().toArray()));
                }
            });

            // Show it in the middle of the screen
            final VerticalLayout layout = new VerticalLayout();
            layout.setStyleName("demoContentLayout");
            layout.setSizeFull();
            layout.addComponent(component);
            layout.setComponentAlignment(component, Alignment.MIDDLE_CENTER);
            setContent(layout);
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

}
