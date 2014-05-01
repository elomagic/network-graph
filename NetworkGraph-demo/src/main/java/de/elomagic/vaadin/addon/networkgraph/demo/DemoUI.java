package de.elomagic.vaadin.addon.networkgraph.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
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

    private NetworkGraph networkGraph;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(final VaadinRequest request) {
        try {
            final TextField tfNodeId = new TextField("Node Id", UUID.randomUUID().toString().substring(0, 4));
            final TextField tfNodeName = new TextField("Node Name");
            final TextField tfNodeParentId = new TextField("Node Parent Id", "root");
            final ComboBox cbNodeImage = new ComboBox("Node Image URL", Arrays.asList("/VAADIN/Company.png", "/VAADIN/Delivery.png", "/VAADIN/Home.png", "/VAADIN/Telephone.png"));
            cbNodeImage.setValue("/VAADIN/Company.png");

            Button btnAddNodes = new Button("Add Node & Edge", new Button.ClickListener() {

                @Override
                public void buttonClick(final Button.ClickEvent event) {
                    String id = tfNodeId.getValue();
                    GraphNode newNode = new GraphNode(id, "ID=" + id + "; " + tfNodeName.getValue());
                    newNode.setImage(cbNodeImage.getValue() == null ? "" : cbNodeImage.getValue().toString());
                    newNode.setShape("image");

                    String parentId = tfNodeParentId.getValue();
                    Edge newEdge = new Edge(parentId, id);
                    newEdge.setLength(200);

                    networkGraph.addData(new GraphNode[] {newNode}, new Edge[] {newEdge});

                    tfNodeId.setValue(UUID.randomUUID().toString().substring(0, 4));
                }
            });

            FormLayout formLayout = new FormLayout(tfNodeName, tfNodeId, tfNodeParentId, cbNodeImage, btnAddNodes);
            formLayout.setSizeUndefined();

            GraphNode node1 = new GraphNode();
            node1.setId("root");
            node1.setLabel("ID=root");
            node1.setTitle("I'm a happy root node");
            node1.setShape("square");
            node1.setColor("red");

            Data data = new Data(
                    Collections.singletonList(node1),
                    Collections.EMPTY_LIST);

            // Initialize our new UI component
            networkGraph = new NetworkGraph();
            networkGraph.setSizeFull();
            networkGraph.setData(data);
            networkGraph.addSelectListener(new NetworkGraph.SelectListener() {

                @Override
                public void nodeSelect(final NetworkGraph.SelectEvent event) {
                    Notification.show("Nodes selected: " + Arrays.toString(event.getNodesId().toArray()));
                }
            });

            // Show it
            final VerticalLayout layout = new VerticalLayout(formLayout, networkGraph);
            layout.setStyleName("demoContentLayout");
            layout.setSizeFull();
            layout.setExpandRatio(networkGraph, 1);
            setContent(layout);
            setSizeFull();
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

}
