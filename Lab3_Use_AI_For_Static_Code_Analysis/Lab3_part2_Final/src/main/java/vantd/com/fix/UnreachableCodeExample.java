package vantd.com.fix;

import java.util.logging.Logger;

public class UnreachableCodeExample {
    private static final Logger logger = Logger.getLogger(UnreachableCodeExample.class.getName());

    private static final int NUMBER = 42; // Replaces getNumber()

    public static void main(String[] args) {
        logger.info(String.valueOf(NUMBER));
    }
}
