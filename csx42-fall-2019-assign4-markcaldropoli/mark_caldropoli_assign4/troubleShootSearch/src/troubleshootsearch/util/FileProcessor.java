package troubleshootsearch.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.System;
import troubleshootsearch.search.ExactMatch;
import troubleshootsearch.search.MyArrayList;
import troubleshootsearch.search.MyTree;
import troubleshootsearch.search.NaiveStemmingMatch;
import troubleshootsearch.search.SemanticMatch;
import troubleshootsearch.search.Visitor;

/**
 * Process input files
 * @author Mark Caldropoli
 */
public class FileProcessor {
    private Results results;
    private MyLogger logger;

    /**
     * Constructor to populate results field.
     */
    public FileProcessor(Results resultsIn, MyLogger loggerIn) {
        results = resultsIn;
        logger = loggerIn;
    }

    /**
     * Process technical info input file.
     */
    public void processTechnicalInfo(String filename, MyArrayList list, MyTree tree) {
        File file = new File(filename);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            if(file.length() != 0) {
                int count = 1;
                String line;
                while((line = reader.readLine()) != null) {
                    line = line.trim();
                    logger.writeMessage("Technical Info: " + line, MyLogger.DebugLevel.FILE_PROCESSOR);
                    list.addInfo(line);
                    for(String s : line.split(" ")) {
                        s = s.replace(".", "");
                        tree.addWord(s, count);
                    }
                    count++;
                }
            } else {
                logger.writeMessage("File " + filename + " is empty: Technical Info", MyLogger.DebugLevel.ERROR);
                System.exit(1);
            }
        } catch(FileNotFoundException e) {
            logger.writeMessage("File not found: Technical Info", MyLogger.DebugLevel.ERROR);
            System.exit(1);
        } catch(IOException e) {
            logger.writeMessage("IO Exception: Technical Info", MyLogger.DebugLevel.ERROR);
            System.exit(1);
        } finally {
            try {
                reader.close();
            } catch(IOException e) {
                logger.writeMessage("BufferedReader not found: Technical Info", MyLogger.DebugLevel.ERROR);
                System.exit(1);
            }
        }
    }

    /**
     * Process synonyms input file.
     */
    public void processSynonyms(String filename, MyArrayList list) {
        File file = new File(filename);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            if(file.length() != 0) {
                String line;
                while((line = reader.readLine()) != null) {
                    line = line.trim();
                    logger.writeMessage("Synonyms: " + line, MyLogger.DebugLevel.FILE_PROCESSOR);
                    list.addSynonym(line);
                }
            } else {
                logger.writeMessage("File " + filename + " is empty: Synonyms", MyLogger.DebugLevel.ERROR);
                System.exit(1);
            }
        } catch(FileNotFoundException e) {
            logger.writeMessage("File not found: Synonyms", MyLogger.DebugLevel.ERROR);
            System.exit(1);
        } catch(IOException e) {
            logger.writeMessage("IO Exception: Synonyms", MyLogger.DebugLevel.ERROR);
            System.exit(1);
        } finally {
            try {
                reader.close();
            } catch(IOException e) {
                logger.writeMessage("BufferedReader not found: Synonyms", MyLogger.DebugLevel.ERROR);
                System.exit(1);
            }
        }
    }

    /**
     * Process user input file and call the visitors' accept method.
     */
    public void processUserInput(String filename, MyArrayList list, MyTree tree) {
        File file = new File(filename);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            if(file.length() != 0) {
                String line;
                boolean first = true;
                while((line = reader.readLine()) != null) {
                    line = line.trim();
                    logger.writeMessage("User Input: " + line, MyLogger.DebugLevel.FILE_PROCESSOR);

                    // Record user input line
                    results.addLine("user input - " + line);

                    // Create visitors
                    Visitor exact = new ExactMatch(results, logger);
                    Visitor naiveStemming = new NaiveStemmingMatch(results, logger);
                    Visitor semantic = new SemanticMatch(results, exact, logger);

                    // Call visitors' accept method
                    if(first) {
                        results.addLine("Exact Match");
                        first = false;
                    }
                    else results.addLine("\nExact Match");
                    results.addLine("-----------");
                    list.accept(exact, line);
                    results.addLine("\nNaive Stemming Match");
                    results.addLine("--------------------");
                    tree.accept(naiveStemming, line);
                    results.addLine("\nSemantic Match");
                    results.addLine("--------------");
                    list.accept(semantic, line);
                }
            } else {
                logger.writeMessage("File " + filename + " is empty: User Input", MyLogger.DebugLevel.ERROR);
                System.exit(1);
            }
        } catch(FileNotFoundException e) {
            logger.writeMessage("File not found: User Input", MyLogger.DebugLevel.ERROR);
            System.exit(1);
        } catch(IOException e) {
            logger.writeMessage("IO Exception: User Input", MyLogger.DebugLevel.ERROR);
            System.exit(1);
        } finally {
            try {
                reader.close();
            } catch(IOException e) {
                logger.writeMessage("BufferedReader not found: User Input", MyLogger.DebugLevel.ERROR);
                System.exit(1);
            }
        }
    }

    /**
     * Default toString, not needed for debugging here.
     * @return String with value null
     */
    public String toString() {
        return null;
    }
}
