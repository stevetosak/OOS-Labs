import java.io.*;
import java.util.Arrays;

public class SoundLevelMonitor {
    static String getSoundLevelReading(double value){
        if(value >= 40 && value < 60) return "Low";
        else if (value >= 60 && value < 80) return "Medium";
        else return "High";
    }
    static String outPath = System.getenv("AVG_POLLUTION_PATH");
    static String inPath = System.getenv("SOUND_DATA_PATH");
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inPath)));
        try (PrintWriter pw = new PrintWriter(outPath)) {
            String line = br.readLine();
            String[] values = line.split(" +");
            double avg = (double) Arrays.stream(values).sequential().map((Integer::parseInt)).reduce(0, Integer::sum) / values.length;
            pw.println(String.format("(%.2fdB) %s",avg,getSoundLevelReading(avg)));
        }
    }
}
