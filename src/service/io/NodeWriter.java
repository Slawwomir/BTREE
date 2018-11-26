package service.io;

import tree.Node;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class NodeWriter {
    private BufferedBinaryWriter binaryWriter;

    public NodeWriter(String filename, int blockSize) throws FileNotFoundException {
        binaryWriter = new BufferedBinaryWriter(filename, blockSize);
    }

    public void write(Node node) throws IOException {
        List<Integer> keys = node.getKeys();
        List<Integer> descendants = node.getDescendants();

        Integer offset = node.getOffset();

        binaryWriter.seek(offset);

        binaryWriter.saveInt(node.getParentOffset());
        binaryWriter.saveInt(keys.size());

        for (int i = 0; i < keys.size(); i++) {
            binaryWriter.saveInt(descendants.get(i));
            binaryWriter.saveInt(keys.get(i));
        }

        binaryWriter.saveInt(descendants.get(descendants.size() - 1));
        binaryWriter.writeBlock();
    }

    public void close() throws IOException {
        binaryWriter.close();
    }
}
