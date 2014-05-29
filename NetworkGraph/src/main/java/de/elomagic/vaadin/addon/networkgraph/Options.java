package de.elomagic.vaadin.addon.networkgraph;

/**
 * @author sovsyankin
 *         Date: 29.05.2014
 */
public class Options {
    private HierarchicalLayout hierarchicalLayout;

    private Edges edges;

    private Nodes nodes;

    public HierarchicalLayout getHierarchicalLayout() {
        return hierarchicalLayout;
    }

    public void setHierarchicalLayout(HierarchicalLayout hierarchicalLayout) {
        this.hierarchicalLayout = hierarchicalLayout;
    }

    public Edges getEdges() {
        return edges;
    }

    public void setEdges(Edges edges) {
        this.edges = edges;
    }

    public Nodes getNodes() {
        return nodes;
    }

    public void setNodes(Nodes nodes) {
        this.nodes = nodes;
    }

    public static class HierarchicalLayout {
        private boolean enabled = true;

        private Integer levelSeparation;

        private Integer nodeSpacing;

        private Direction direction;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public Integer getLevelSeparation() {
            return levelSeparation;
        }

        public void setLevelSeparation(Integer levelSeparation) {
            this.levelSeparation = levelSeparation;
        }

        public Integer getNodeSpacing() {
            return nodeSpacing;
        }

        public void setNodeSpacing(Integer nodeSpacing) {
            this.nodeSpacing = nodeSpacing;
        }

        public Direction getDirection() {
            return direction;
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
        }

        public static enum Direction {
            UD, DU, LR, RL
        }
    }

    public static class Edges {
        private EdgeColor color;

        private Edge.Style style;

        private Integer width;

        private Double arrowScaleFactor;

        public EdgeColor getColor() {
            return color;
        }

        public void setColor(EdgeColor color) {
            this.color = color;
        }

        public Edge.Style getStyle() {
            return style;
        }

        public void setStyle(Edge.Style style) {
            this.style = style;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Double getArrowScaleFactor() {
            return arrowScaleFactor;
        }

        public void setArrowScaleFactor(Double arrowScaleFactor) {
            this.arrowScaleFactor = arrowScaleFactor;
        }
    }

    public static class Nodes {
        private NodeColor color;

        private String fontColor;

        private String fontFace;

        private Integer fontSize;

        private String shape;

        public NodeColor getColor() {
            return color;
        }

        public void setColor(NodeColor color) {
            this.color = color;
        }

        public String getFontColor() {
            return fontColor;
        }

        public void setFontColor(String fontColor) {
            this.fontColor = fontColor;
        }

        public String getFontFace() {
            return fontFace;
        }

        public void setFontFace(String fontFace) {
            this.fontFace = fontFace;
        }

        public Integer getFontSize() {
            return fontSize;
        }

        public void setFontSize(Integer fontSize) {
            this.fontSize = fontSize;
        }

        public String getShape() {
            return shape;
        }

        public void setShape(String shape) {
            this.shape = shape;
        }
    }

    public static class EdgeColor {
        private String color;

        private String highlight;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getHighlight() {
            return highlight;
        }

        public void setHighlight(String highlight) {
            this.highlight = highlight;
        }
    }

    public static class NodeColor {
        private String color;

        private String border;

        private String background;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getBorder() {
            return border;
        }

        public void setBorder(String border) {
            this.border = border;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }
    }
}
