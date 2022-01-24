package components.battles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Logger {
    private static Logger instance = null;


    public String name = "logfile";
    //get the home directory
    protected String dir = System.getProperty("user.dir");
    private static File log_file; //file where the entire log is stored

    ///////////////////////////////////////////////       CONSTRUCTOR       ////////////////////////////////////////////////////////////

    private Logger(){
        if (instance != null){
            //Prevent Reflection
            throw new IllegalStateException("Cannot instantiate a new singleton instance of log"); //we can only create ONE instance and not more
        }
        this.createLog();
    }

    ///////////////////////////////////////////////       INSTANCE       ////////////////////////////////////////////////////////////

    public static Logger getInstance(){
        if (instance == null){
            //Prevent Reflection
            instance = new Logger();
        }
        return instance;
    }


    ///////////////////////////////////////////////       METHODS       ////////////////////////////////////////////////////////////

    public void createLog(){
        File directory_log = new File(dir+'/'+"logs");
        boolean dir_success;
        //check if the folder already exists or not
        if(!directory_log.exists()){
            //Create the directory
            dir_success = directory_log.mkdir();
            if(!dir_success){
                System.err.println("Folder couldn't have been created");
            }
        }

        //if folder creation was successful or if folder already exists keep creating the file:
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        //create the name
        name = name + '-' + date.format(cal.getTime()) + ".log";
        Logger.log_file = new File(directory_log.getName(), name);

        //check if the logfile can be created successfully
        try{
            if(log_file.createNewFile()){ //new file could have been created
                System.err.println("INFO: Creating new log file");
            }
        } catch(IOException ex){
            System.err.println("ERROR: Cannot create log file");
            System.exit(1);
        }

    }


    public static void log(String msg){
        //put messages into the File
        try{
            FileWriter out = new FileWriter(log_file, true);
            //get date and time
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            out.write(dtf.format(now).toCharArray());
            out.write(" - ");
            out.write(msg.toCharArray());
            out.close();
        }catch(IOException e){
            System.err.println("ERROR: Could not write to log file");
        }
    }
}
