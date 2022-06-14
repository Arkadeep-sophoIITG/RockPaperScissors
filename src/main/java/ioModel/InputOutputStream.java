package ioModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class InputOutputStream {

    private final BufferedReader bufferedReader;
    private final PrintWriter printWriter;

    public InputOutputStream(){
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        printWriter = new PrintWriter(System.out);
    }

    public synchronized String getInputString() throws IOException {
        return bufferedReader.readLine();
    }

    public synchronized Integer getInputInteger() throws IOException{
        return Integer.parseInt(bufferedReader.readLine());
    }

    public synchronized void printNewLine(String outputString) {
        printWriter.println(outputString);
        printWriter.flush();
    }

    public synchronized void print(String outputString) {
        printWriter.print(outputString);
        printWriter.flush();
    }

    public synchronized void closeInputStream() throws IOException {
        bufferedReader.close();
    }

    public synchronized void closeOutputStream() {
        printWriter.close();
    }

}
