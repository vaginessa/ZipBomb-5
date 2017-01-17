import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ZipBomb implements Runnable{
    private final File file;
    private final int number;

    public ZipBomb(int number) {
        this.number = number;
        file = new File(System.getProperty("user.dir") + "/bomb" + number + ".bin");
    }

    private void newFile() {
        FileOutputStream fileOutputStream = null;
        Random generator = new Random();
        byte[] data = new byte[1024 * 1024];
        generator.nextBytes(data);
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data, 0, data.length);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ZipBomb.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
        }
        finally{
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        newFile();
        System.out.println("Thread " + number + " has finished.");
    }
}
