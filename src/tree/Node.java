package tree;

import java.util.List;

public class Node {
    private Integer offset;
    private Integer parentOffset;

    private List<Integer> keys;
    private List<Integer> descendants;

    public Node(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getParentOffset() {
        return parentOffset;
    }

    public void setParentOffset(Integer parentOffset) {
        this.parentOffset = parentOffset;
    }

    public List<Integer> getKeys() {
        return keys;
    }

    public void setKeys(List<Integer> keys) {
        this.keys = keys;
    }

    public List<Integer> getDescendants() {
        return descendants;
    }

    public void setDescendants(List<Integer> descendants) {
        this.descendants = descendants;
    }
}
