import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter; 

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.08
 */
public class Parser 
{
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * Goes through each command that is put in by the user, and
     * writes that command to the zuul.log file. Now takes input for
     * commands that are more than 2 words. If an error occurs regarding 
     * this file, and exception will be thrown and dealt with.
     * 
     * @return The next command from the user.
     */
    public Command getCommand() throws IOException 
    {
        String inputLine;   // will hold the full input line
        String word1 = null;
        String word2 = null;

        System.out.print("> ");     // print prompt

        inputLine = reader.nextLine();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        
        // Trys the potentially dangerous code.
        try {
            FileWriter fw = new FileWriter("zuul.log", true);
                        
            if(tokenizer.hasNext()) {
                word1 = tokenizer.next();   // get first word
                fw.write(word1 + " ");
                if(tokenizer.hasNext()) {
                    word2 = tokenizer.next();
                    fw.write(word2 + " ");// get second word

                    // note: we just ignore the rest of the input line.
                }
                while(tokenizer.hasNext()){
                   String word = tokenizer.next();
                   fw.write(word1 + " ");           
                } 
            }
            fw.write(System.getProperty("line.separator"));
            fw.close();
        }
        catch(Exception e) {
            System.out.println("Something went wrong when working with this file.");
        }
        
        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if(commands.isCommand(word1)) {
            return new Command(word1, word2);
        }
        else {
            return new Command(null, word2); 
        }
    }

    /**
     * Print out a list of valid command words.
     */
    public void showCommands()
    {
        commands.showAll();
    }
}