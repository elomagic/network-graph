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

/**
 * Edges are connections between nodes.
 * <p/>
 * An edge must at least contain properties from and to, both referring to the id of a node. Edges can have extra properties, used to define the type and style.
 */
public final class Edge {
    private String from;
    private String to;
    private String title;
    private String label;
    private Style style = Style.line;
    private String color = "";
    private int length;

    public Edge() {
    }

    public Edge(final String from, final String to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the id of a node where the edge starts (Required).
     * <p/>
     * The type must correspond with the type of the node id's. This is normally a number, but can be any type.
     *
     * @return The id
     */
    public String getFrom() {
        return from;
    }

    /**
     * Set the id of a node where the edge starts (Required).
     * <p/>
     * The type must correspond with the type of the node id's. This is normally a number, but can be any type.
     *
     * @param from The Id
     */
    public void setFrom(final String from) {
        this.from = from;
    }

    /**
     * Returns the id of a node where the edge ends.
     * <p/>
     * The type must correspond with the type of the node id's. This is normally a number, but can be any type.
     *
     * @return The Id
     */
    public String getTo() {
        return to;
    }

    /**
     * Set the id of a node where the edge ends.
     * <p/>
     * The type must correspond with the type of the node id's. This is normally a number, but can be any type.
     *
     * @param to The Id
     */
    public void setTo(final String to) {
        this.to = to;
    }

    /**
     * Returns the line style for the edge.
     * <p/>
     * Choose from line (default), arrow, arrow-center, or dash-line.
     *
     * @return Style
     */
    public Style getStyle() {
        return style;
    }

    /**
     * Set the line style for the edge.
     * <p/>
     * Choose from line (default), arrow, arrow-center, or dash-line.
     *
     * @param style Style
     */
    public void setStyle(final Style style) {
        this.style = style;
    }

    /**
     * Returns HTML color for the edge.
     *
     * @return HTML encoded color
     */
    public String getColor() {
        return color;
    }

    /**
     * Set HTML color for the edge.
     *
     * @param color HTML encoded color
     */
    public void setColor(final String color) {
        this.color = color;
    }

    /**
     * Returns the length of the edge in pixels.
     *
     * @return
     */
    public int getLength() {
        return length;
    }

    /**
     * Set the length of the edge in pixels.
     *
     * @param length
     */
    public void setLength(final int length) {
        this.length = length;
    }

    /**
     * Returns the title of the edge. (Tooltip Text seen on mouseover)
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the edge. (Tooltip Text seen on mouseover)
     *
     * @param title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Returns the label of the edge.
     *
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set the label of the edge.
     *
     * @param label
     */
    public void setLabel(final String label) {
        this.label = label;
    }

    public static enum Style {
        line("line"), arrow("arrow"), arrowCenter("arrow-center"), dashLine("dash-line");

        private final String style;

        Style(String style) {
            this.style = style;
        }

        @Override
        public String toString() {
            return this.style;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (from != null ? !from.equals(edge.from) : edge.from != null) return false;
        if (to != null ? !to.equals(edge.to) : edge.to != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }
}
