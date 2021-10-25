package assignment2.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BibCreator {
    public static void main(String[] args) {
        System.out.println("Welcome to BibCreator!\n");
        int N = 10;
        String dir = "./Files/";
        String saveDir = "./Files/out/";
        File[] bibFiles = new File[N];
        Scanner[] bibScanners = new Scanner[N];
        for (int i = 1; i <= N; i++) {
            String filePath = dir + "Latex" + i + ".bib";
            bibFiles[i - 1] = new File(filePath);
            bibScanners[i - 1] = getInputScanner(bibFiles[i - 1]);
            if (bibScanners[i - 1] == null) {
                closeAll(bibScanners);
                return;
            }
        }
        // create the output dictionary if not exists
        createDictionaryIfNotExists(new File(saveDir));

        File[][] jsonFiles = new File[N][3];
        PrintWriter[][] jsonPWs = new PrintWriter[N][3];
        for (int i = 1; i <= N; i++) {
            jsonFiles[i - 1][0] = new File(saveDir + "IEEE" + i + ".json");
            jsonFiles[i - 1][1] = new File(saveDir + "ACM" + i + ".json");
            jsonFiles[i - 1][2] = new File(saveDir + "NJ" + i + ".json");
            jsonPWs[i - 1][0] = getOutputWriter(jsonFiles[i - 1][0]);
            jsonPWs[i - 1][1] = getOutputWriter(jsonFiles[i - 1][1]);
            jsonPWs[i - 1][2] = getOutputWriter(jsonFiles[i - 1][2]);
            if (jsonPWs[i - 1][0] == null || jsonPWs[i - 1][1] == null
                    || jsonPWs[i - 1][2] == null) {
                closeAll(jsonPWs);
                for (File[] arr : jsonFiles)
                    for (File f : arr)
                        deleteIfExist(f);
                return;
            }
        }
        int failedCout = 0;
        for (int i = 0; i < N; i++) {
            boolean ret = processFilesForValidation(bibFiles[i].getName(), bibScanners[i],
                    jsonPWs[i][0], jsonPWs[i][1], jsonPWs[i][2]);
            if (!ret) {
                failedCout++;
                jsonPWs[i][0].close();
                jsonPWs[i][1].close();
                jsonPWs[i][2].close();
                deleteIfExist(jsonFiles[i][0]);
                deleteIfExist(jsonFiles[i][1]);
                deleteIfExist(jsonFiles[i][2]);
            }
        }
        if (failedCout != 0) {
            System.out.println("A total of " + failedCout
                    + " files were invalid,and could not be processed.All other " + (N - failedCout)
                    + " valid files have been created.");
        } else {
            System.out.println("All files have been created.");
        }
        System.out.println("Please enter the name of one of the files that you need to review:");


        closeAll(bibScanners);
        closeAll(jsonPWs);
    }

    private static void createDictionaryIfNotExists(File file) {
        if (!file.isDirectory()) {
            file.mkdir();
        }
    }

    public static PrintWriter getOutputWriter(File file) {
        try {
            return new PrintWriter(file);
        } catch (FileNotFoundException e) {
            System.out.println("File:" + file.getPath() + " could not be opened/created");
            return null;
        }
    }

    public static Scanner getInputScanner(File file) {
        try {
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Could not open input file " + file.getPath() + " for reading.\n\n"
                    + "Please check if file exists! Program will terminate after closing any opened files.");
            return null;
        }
    }

    public static void closeAll(Scanner[] arr) {
        for (Scanner sc : arr) {
            if (sc != null)
                sc.close();
        }
    }

    public static void closeAll(PrintWriter[][] pws) {
        for (PrintWriter[] arr : pws)
            for (PrintWriter pw : arr)
                if (pw != null)
                    pw.close();
    }

    public static void deleteIfExist(File file) {
        if (file != null && file.exists()) {

            try {
                Files.delete(Paths.get(file.getPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String ieeeFormat(Map<String, String> article) {
        return article.get("author").replace(" and ", ", ") + ". \"" + article.get("title")
                + "\", " + article.get("journal") + ", Vol. " + article.get("volume") + ", no. "
                + article.get("number") + ", p. " + article.get("pages") + ", "
                + article.get("month") + " " + article.get("year") + ".";
    }

    public static String acmFormat(Map<String, String> article) {
        String author = article.get("author");
        String[] names = author.split(" and ");
        return names[0] + " et al. " + article.get("year") + ". " + article.get("title") + ". "
                + article.get("journal") + ". " + article.get("volume") + ", "
                + article.get("number") + " (" + article.get("year") + "), " + article.get("pages")
                + ". DOI:https://doi.org/" + article.get("doi") + ".";
    }

    public static String njFormat(Map<String, String> article) {
        return article.get("author").replace(" and ", " & ") + ". " + article.get("title")
                + ". " + article.get("journal") + ". " + article.get("volume") + ", "
                + article.get("pages") + "(" + article.get("year") + ").";
    }

    public static boolean processFilesForValidation(String currentFile, Scanner fi,
            PrintWriter ieee, PrintWriter acm, PrintWriter nj) {
        String line;
        Map<String, String> article = new HashMap<>();
        int idxOfEq;
        int counter = 0;
        try {
            while (fi.hasNext()) {
                line = fi.nextLine();
                line = line.trim();
                if (line.startsWith("@ARTICLE") && !article.isEmpty()) {
                    counter++;
                    writeArticle(ieee, acm, nj, counter, article);
                } else if ((idxOfEq = line.indexOf("=")) >= 0) {
                    String field = line.substring(0, idxOfEq);
                    String content = line.substring(idxOfEq + 2, line.length() - 2);
                    if (content.length() == 0)
                        throw new FileInvalidException("File is Invalid: Field " + field
                                + " is Empty. Processing stoped at this point. Other empty fields may be present as well!");
                    article.put(field, content);
                }
            }
            if (article.size() != 0) {
                writeArticle(ieee, acm, nj, counter + 1, article);
                article.clear();
            }
        } catch (FileInvalidException e) {
            System.out.println("Error: Detected Empty Field!\n");
            System.out.println("============================");
            System.out.println("Problem detected with input file: " + currentFile);
            System.out.println(e.getMessage() + "\n");
            return false;
        }
        return true;
    }

    private static void writeArticle(PrintWriter ieee, PrintWriter acm, PrintWriter nj, int counter, Map<String,String> article) {
        String ieeeFormat = ieeeFormat(article);
        String acmFormat = acmFormat(article);
        String njFormat = njFormat(article);
        article.clear();
        ieee.println(ieeeFormat + "\n");
        acm.println("[" + counter + "] " + acmFormat + "\n");
        nj.println(njFormat + "\n");
    }
}
