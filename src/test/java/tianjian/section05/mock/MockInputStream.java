package tianjian.section05.mock;

import com.sun.tools.doclets.internal.toolkit.util.DocFinder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tianjian on 2017/12/24.
 */
public class MockInputStream extends InputStream {
    private String buffer;
    private int position = 0;
    private int closeCount = 0;
    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }
    @Override
    public int read() throws IOException {
        if(position == this.buffer.length()) {
            return -1;
        }
        return this.buffer.charAt(this.position++);
    }

    public void close() throws IOException {
        closeCount++;
        super.close();
    }

    public void verify() {
        if(closeCount != 1) {
            throw new AssertionError("close() should have been "
                    + "callsed once and once only");
        }
    }


}
