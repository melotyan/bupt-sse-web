import java.io.File;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**
 * Created by melot on 2016/4/14.
 */
public class NIOTest  {
    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("Test.java", "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(490);
        System.out.println(channel.read(buffer));
    }


}
