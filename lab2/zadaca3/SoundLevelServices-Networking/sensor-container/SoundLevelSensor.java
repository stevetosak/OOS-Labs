import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

public class SoundLevelSensor {
    static String filePath = System.getenv("SOUND_DATA_PATH");
    public static void main(String[] args) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(filePath,true))) {
            for(int i = 0; i < 10; i++){
                int number = new Random().nextInt(61) + 40;
                pw.print(number + " ");
            }
        }

    }
}
