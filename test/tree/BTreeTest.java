package tree;

import model.Record;
import org.junit.Test;
import service.io.NodeReader;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

public class BTreeTest {

    @Test
    public void insert() throws IOException {
        BTree tree = new BTree(3, 3, "test");
        tree.insert(Record.getRandomRecord(5));
        tree.insert(Record.getRandomRecord(3));
        tree.insert(Record.getRandomRecord(1));

        NodeReader reader = new NodeReader("test", tree.getBlockSize());

        Node node = reader.read(0);
        assertArrayEquals(node.getKeys().toArray(new Integer[0]), new Integer[]{1, 3, 5});
    }

    @Test
    public void remove() {
    }

    @Test
    public void update() {
    }

    @Test
    public void search() {
    }
}