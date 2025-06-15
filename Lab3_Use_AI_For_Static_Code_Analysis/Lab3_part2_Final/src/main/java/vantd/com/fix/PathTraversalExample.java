package vantd.com.fix;
import java.util.logging.Logger;
import java.io.*;

public class PathTraversalExample {
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger(PathTraversalExample.class.getName());
        String userInput = "../secret.txt";
        File file = new File(userInput);
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            logger.info("Reading file: " + file.getPath());
            reader.close();
        }
    }
}
