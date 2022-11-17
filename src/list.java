import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
//Diego herrera diaz
public class list {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No ingreso el nombre del archivo de texto");
            System.exit(0);
        }
        ArrayList<String> list = new ArrayList<String>();
        list = words(new String[]{args[0]}, list);
    }

    public static void palnum(ArrayList<String> list, String palabra){
        HashSet<String> z = new HashSet<String>(list);
        System.out.println("La palabra " + palabra + " se repite: " + Collections.frequency(list, palabra));
    }

    public static ArrayList<String> words(String[] args, ArrayList<String> list){
        FileReader read = null;
        try {
            read = new FileReader(args[0]);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(-1);
        }

        BufferedReader inputFile = new BufferedReader(read);

        String textLine = null;

        int lineCount = 0;
        int counterwords = 0;
        int numberCount = 0;

        //limitantes para las palabras del archivo

        String delimiters = "\\s+|,\\s*|\\.\\s*|\\;\\s*|\\:\\s*|\\!\\s*|\\¡\\s*|\\¿\\s*|\\?\\s*|\\-\\s*"
                + "|\\[\\s*|\\]\\s*|\\(\\s*|\\)\\s*|\\\"\\s*|\\_\\s*|\\%\\s*|\\+\\s*|\\/\\s*|\\#\\s*|\\$\\s*";


        long initialtime = System.currentTimeMillis();
        try {
            while ((textLine = inputFile.readLine()) != null) {
                lineCount++;

                if (textLine.trim().length() == 0) {
                    continue;
                }

                // split words from selected file
                String words[] = textLine.split( delimiters );

                counterwords += words.length;

                for (String palabra : words) {

                    palabra = palabra.toLowerCase().trim();

                    boolean numericquestion = true;

                    try {
                        Double num = Double.parseDouble(palabra);
                    } catch (NumberFormatException e) {
                        numericquestion = false;
                    }


                    if (numericquestion) {
                        numberCount++;
                        continue;
                    }

                    list.add(palabra);
                }
            }
            //execution (time)
            long exetime = System.currentTimeMillis() - initialtime;
            inputFile.close();
            read.close();

            System.out.printf("%2.3f  segundos, %,d lineas y %,d palabras\n",
                    exetime / 1000.00, lineCount, counterwords - numberCount);


            System.out.printf("%,total de palabras diferentes\n", list.size());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }


}