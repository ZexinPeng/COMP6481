package assignment2.part2;

/**
 * This class the exception for a invalid file with wrong format
 * @author 40152234 Sijie Min    40166520   Zexin Peng
 */
class FileInvalidException extends Exception {
    public FileInvalidException() {
        super("Error: Input file cannot be parsed due to missing information\r\n"
                + "(i.e. month={}, title={}, etc.) ");
    }

    public FileInvalidException(String message) {
        super(message);
    }
}
