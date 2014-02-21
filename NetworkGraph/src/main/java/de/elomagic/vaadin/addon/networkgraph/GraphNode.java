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

/**
 * GraphNodes typically have an id and label.
 * <p/>
 * A node must contain at least a property id. GraphNodes can have extra properties, used to define the shape and style of the nodes.
 */
public class GraphNode {
    private String id;
    private String label;
    private String color = "";
    private String shape = "ellipse";
    private String image;
    private String title;

    public GraphNode() {
    }

    public GraphNode(final String id, final String label) {
        this.id = id;
        this.label = label;
    }

    /**
     * Returns the unique id for this node.
     *
     * @return The Id
     */
    public String getId() {
        return id;
    }

    /**
     * Set a unique id for this node (Required).
     * <p/>
     * GraphNodes may not have duplicate id's. Id's do not need to be consecutive. An id is normally a number, but may be any type.
     *
     * @param id A unique id for this node.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Returns text label to be displayed in the node or under the image of the node.
     * <p/>
     * Multiple lines can be separated by a newline character
     *
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set text label to be displayed in the node or under the image of the node.
     * <p/>
     * Multiple lines can be separated by a newline character
     *
     * @param label Text
     */
    public void setLabel(final String label) {
        this.label = label;
    }

    /**
     * Returns color for the node.
     *
     * @return HTML encoded color
     */
    public String getColor() {
        return color;
    }

    /**
     * Set color for the node.
     *
     * @param color HTML encoded color
     */
    public void setColor(final String color) {
        this.color = color;
    }

    /**
     * Returns the shape for the node.
     * <p/>
     * Choose from ellipse (default), circle, box, database, image, label, dot, star, triangle, triangleDown, and square.
     * <p/>
     * In case of image, a property with name image must be provided, containing image urls.
     * <p/>
     * The shapes dot, star, triangle, triangleDown, and square, are scalable. The size is determined by the properties radius or value.
     * <p/>
     * When a property label is provided, this label will be displayed inside the shape in case of shapes box, circle, ellipse, and database. For all other shapes, the label will be displayed right
     * below the shape.
     *
     * @return Shape
     */
    public String getShape() {
        return shape;
    }

    /**
     * Define the shape for the node.
     * <p/>
     * Choose from ellipse (default), circle, box, database, image, label, dot, star, triangle, triangleDown, and square.
     * <p/>
     * In case of image, a property with name image must be provided, containing image urls.
     * <p/>
     * The shapes dot, star, triangle, triangleDown, and square, are scalable. The size is determined by the properties radius or value.
     * <p/>
     * When a property label is provided, this label will be displayed inside the shape in case of shapes box, circle, ellipse, and database. For all other shapes, the label will be displayed right
     * below the shape.
     *
     * @param shape Shape
     */
    public void setShape(final String shape) {
        this.shape = shape;
    }

    /**
     * Returns url of an image.
     * <p/>
     * Only applicable when the shape of the node is image.
     *
     * @return URL
     */
    public String getImage() {
        return image;
    }

    /**
     * Set url of an image.
     * <p/>
     * Only applicable when the shape of the node is image.
     *
     * @param image URL
     */
    public void setImage(final String image) {
        this.image = image;
    }

    /**
     * Returns title to be displayed when the user hovers over the node.
     * <p/>
     * The title can contain HTML code.
     *
     * @return Title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title to be displayed when the user hovers over the node.
     * <p/>
     * The title can contain HTML code.
     *
     * @param title Title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

}
