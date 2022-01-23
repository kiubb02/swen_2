package components.battles;

public class Logger {
    private static Logger OBJ =null;
    private StringBuilder Log;

    public static Logger getInstance() {
        if (OBJ == null)
            OBJ = new Logger();

        return OBJ;
    }
    private Logger(){
        Log=new StringBuilder("Log: \n");
    }
    public static void log(String message){
        Log.append(message);
    }
    public String getLog(){
        return Log.toString();
    }
}
