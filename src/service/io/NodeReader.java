package service.io;
import tree.Node;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NodeReader {
    private BufferedBinaryReader binaryReader;

    public NodeReader(String filename, int blockSize) throws FileNotFoundException {
        binaryReader = new BufferedBinaryReader(filename, blockSize);
    }

    public Node read(Integer offset) throws IOException {
        binaryReader.seek(offset);

        Integer parentOffset = binaryReader.readInteger();
        Integer size = binaryReader.readInteger();

        Node node = new Node(parentOffset);

        List<Integer> keys = new ArrayList<>(size);
        List<Integer> descendants = new ArrayList<>(size + 1);

        for (int i = 0; i < size; i++) {
            descendants.add(binaryReader.readInteger());
            keys.add(binaryReader.readInteger());
        }

        descendants.add(binaryReader.readInteger());

        node.setDescendants(descendants);
        node.setKeys(keys);

        return node;
    }
}
