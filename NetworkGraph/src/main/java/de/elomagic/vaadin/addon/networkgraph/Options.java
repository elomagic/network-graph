package de.elomagic.vaadin.addon.networkgraph;

/**
 * @author sovsyankin
 *         Date: 29.05.2014
 */
public class Options {
    private HierarchicalLayout hierarchicalLayout;

    public HierarchicalLayout getHierarchicalLayout() {
        return hierarchicalLayout;
    }

    public void setHierarchicalLayout(HierarchicalLayout hierarchicalLayout) {
        this.hierarchicalLayout = hierarchicalLayout;
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
}
