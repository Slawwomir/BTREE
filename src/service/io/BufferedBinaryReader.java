package service.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class BufferedBinaryReader {
    public static final int DOUBLE_SIZE = Double.SIZE / Byte.SIZE;
    public static int diskOperationCounter;

    private int blockSize;                      // in bytes
    private byte[] block;
    private int index;
    private int read;
    private boolean EOF;
    private boolean empty;
    private RandomAccessFile in;

    public BufferedBinaryReader(String filename, int blockSize) throws FileNotFoundException {
        this.blockSize = blockSize;
        block = new byte[blockSize];
        in = new RandomAccessFile(filename, "r");
    }

    public void close() throws IOException {
        EOF = false;
        index = 0;
        read = 0;
        in.close();
    }

    public Double readDouble() throws IOException {
        byte[] bytes = readBytes(Double.BYTES);

        // TODO : REFACTOR THIS -> ByteBuffer is slow
        return bytes == null ? null : ByteBuffer.wrap(bytes).getDouble();
    }

    public Long readLong() throws IOException {
        byte[] bytes = readBytes(Long.BYTES);

        // TODO : REFACTOR THIS -> ByteBuffer is slow
        return bytes == null ? null : ByteBuffer.wrap(bytes).getLong();
    }

    public Integer readInteger() throws IOException {
        byte[] bytes = readBytes(Integer.BYTES);

        // TODO : REFACTOR THIS -> ByteBuffer is slow
        return bytes == null ? null : ByteBuffer.wrap(bytes).getInt();
    }

    private byte[] readBytes(final int size) throws IOException {
        if (empty = (EOF && index >= read)) {
            return null;
        }

        byte[] db = new byte[size];

        if (index + size <= read) {
            System.arraycopy(block, index, db, 0, size);
            index += size;
        } else {
            int offset = read - index;
            System.arraycopy(block, index, db, 0, offset);

            nextBlock();

            System.arraycopy(block, 0, db, offset, size - offset);
            index = size - offset;
        }
        return db;
    }

    private void nextBlock() throws IOException {
        read = in.read(block, 0, blockSize);

        if (read < blockSize) {
            EOF = true;
        }

        index = 0;
        diskOperationCounter++;
    }

    public void seek(Integer offset) throws IOException {
        index = 0;
        in.seek(offset * blockSize);
    }
}
