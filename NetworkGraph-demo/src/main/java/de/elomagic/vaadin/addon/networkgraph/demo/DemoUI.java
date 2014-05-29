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

import de.elomagic.vaadin.addon.networkgraph.*;
import de.elomagic.vaadin.addon.networkgraph.Node;

import static de.elomagic.vaadin.addon.networkgraph.Edge.Style.arrow;
import static de.elomagic.vaadin.addon.networkgraph.Options.HierarchicalLayout.Direction.LR;

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
                    String label = "ID=" + id + "; " + tfNodeName.getValue();
                    String url = cbNodeImage.getValue() == null ? "" : cbNodeImage.getValue().toString();
                    String parentId = tfNodeParentId.getValue();

                    // image shape was broken :)
//                    networkGraph.addNodes(Node.builder().id(id).label(label).image(url).node());
                    networkGraph.addNodes(Node.builder().id(id).label(label).title("Title " + id).shape("circle").color("red").node());
                    Edge edge = new Edge(parentId, id);
                    edge.setLength(100);
                    edge.setStyle(arrow);
                    networkGraph.addEdges(edge);

                    tfNodeId.setValue(UUID.randomUUID().toString().substring(0, 4));
                }
            });

            FormLayout formLayout = new FormLayout(tfNodeName, tfNodeId, tfNodeParentId, cbNodeImage, btnAddNodes);
            formLayout.setSizeUndefined();

            // Initialize our new UI component
            networkGraph = new NetworkGraph();
            networkGraph.setOptions(new Options() {{
                setHierarchicalLayout(new HierarchicalLayout(){{
                    setDirection(LR);
                }});
                setEdges(new Edges() {{
                    setArrowScaleFactor(1.1);
                    setWidth(2);
                    setStyle(arrow);
                }});
                setNodes(new Nodes() {{
                    setFontSize(16);
                    setShape("circle");
                    setColor(new NodeColor() {{
                        setColor("red");
                    }});
                }});
            }});
            networkGraph.setSizeFull();
            networkGraph.addNodes(Node.builder().id("root").label("root").title("I'm a happy root node").shape("circle").color("red").node());
            networkGraph.addSelectListener(new NetworkGraph.SelectListener() {

                @Override
                public void nodeSelect(final NetworkGraph.SelectEvent event) {
                    Notification.show("Nodes selected: " + Arrays.toString(event.getNodesId().toArray()), Notification.Type.TRAY_NOTIFICATION);
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
