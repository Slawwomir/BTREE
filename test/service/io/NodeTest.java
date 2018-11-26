package service.io;

import org.junit.Test;
import tree.Node;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class NodeTest {

    @Test
    public void testWriteReadNode() throws IOException {
        Node node = new Node(0);
        node.setKeys(Arrays.asList(5, 10, 15, 20));
        node.setDescendants(Arrays.asList(1, 2, 3, 4, 5));

        NodeWriter writer = new NodeWriter("testfile", 11 * Integer.BYTES);
        writer.write(node);

        NodeReader reader = new NodeReader("testfile", 11 * Integer.BYTES);

        Node read = reader.read(0);

        assertArrayEquals(read.getKeys().toArray(new Integer[0]), new Integer[]{5, 10, 15, 20});
        assertArrayEquals(read.getDescendants().toArray(new Integer[0]), new Integer[]{1, 2, 3, 4, 5});
    }
}