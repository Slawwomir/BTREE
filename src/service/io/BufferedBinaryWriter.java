package service.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class BufferedBinaryWriter {
    public static int diskOperationCounter;

    private int blockSize;                      // in bytes
    private byte[] block;
    private int index;
    private String filename;
    private RandomAccessFile out;


    public BufferedBinaryWriter(String filename, int blockSize) throws FileNotFoundException {
        this.blockSize = blockSize;
        this.filename = filename;
        block = new byte[blockSize];
        out = new RandomAccessFile(filename, "rw");
    }

    public void close() throws IOException {
        if(index > 0) {
            writeBlock();
        }
        if(out != null) {
            out.close();
        }
    }

    public void saveInt(Integer dd) throws IOException {
        byte[] bytes = new byte[Integer.BYTES];

        if(dd == null) {
            dd = -1;
        }

        ByteBuffer.wrap(bytes).putInt(dd);

        saveBytes(bytes);
    }

    public void saveLong(Long dd) throws IOException {
        byte[] bytes = new byte[Long.BYTES];
        ByteBuffer.wrap(bytes).putLong(dd);

        saveBytes(bytes);
    }

    public void saveDouble(Double dd) throws IOException {
        byte[] bytes = new byte[Double.BYTES];
        ByteBuffer.wrap(bytes).putDouble(dd);

        saveBytes(bytes);
    }

    private void saveBytes(final byte[] dd) throws IOException {
        if (index + dd.length < blockSize) {
            System.arraycopy(dd, 0, block, index, dd.length);
            index += dd.length;
        } else {
            int offset = blockSize - index;
            System.arraycopy(dd, 0, block, index, offset);
            index = blockSize;

            writeBlock();

            index = dd.length - offset;
            System.arraycopy(dd, offset, block, 0, index);
        }
    }

    public void writeBlock() throws IOException {
        if(index == 0)
            return;

        if (out == null) {
            out = new RandomAccessFile(filename, "rw");
        }

        out.write(block, 0, index);
        index = 0;
        diskOperationCounter++;
    }

    public void seek(Integer offset) throws IOException {
        index = 0;
        out.seek(offset * blockSize);
    }
}
