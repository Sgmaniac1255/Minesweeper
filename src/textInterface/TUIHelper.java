/**
 * 
 */
package textInterface;

import java.io.*;
import java.util.Scanner;

/**
 * Wrapper Class for formated Input and Output to a console
 * @author Bobby Dillingham
 *
 */
public class TUIHelper {
	

	private static final int DEFAULT_CONSOLESIZE = 80;	
	private final int CONSOLESIZE;
	
	private InputStream in;
	private PrintStream out;
	private Scanner sc;
	
	
	/**
	 * basic constructor that will default to System.in and System.out with a default CONSOLESIZE of 80
	 */
	public TUIHelper() {
		this(System.in,System.out,DEFAULT_CONSOLESIZE);
	}	
	
	public TUIHelper(InputStream in, PrintStream out) {
		this(in,out,DEFAULT_CONSOLESIZE);
	}
	
	public TUIHelper(InputStream in, PrintStream out, int size) {
		this.in=in;
		this.out=out;
		this.CONSOLESIZE=size;
		sc=new Scanner(this.in);
	}

	public void printFullLine(char c) {
		for(int i = 0;i<CONSOLESIZE;i++){
			out.print(c);
		}
		out.println();
	}

	public void wrappedPrint(char c, String text) {

		String print = "";
		int whitespace = CONSOLESIZE/2-text.length()/2-1;
		
		print += c;
		print += addWhitespace(whitespace);
		print += text;
		print += addWhitespace(whitespace);
		print += c;
		
		out.println(print);
	}
	
	private String addWhitespace(int whitespace){
		String rtn="";
		for(int i=0;i<whitespace;i++){
			rtn += " ";
		}
		return rtn;
	}

	public void centerPrint(String string) {

		String print = "";
		int whitespace = CONSOLESIZE/2-string.length()/2;
		
		print += addWhitespace(whitespace);
		print +=string;
		
		out.println(print);
		
	}

	public String inputPrompt(String string) {
		out.print(string);
		return sc.next();
	}

	public int getConsoleSize() {
		return CONSOLESIZE;
	}

	public int nextBountedInt(String prompt, int lowBound, int highBound) {
		int rtn=0;
		boolean illegalArg = true;
		do {
			try {
				rtn = Integer.parseInt(this.inputPrompt(prompt +" (" + lowBound +"-"+highBound+"): "));
				if (rtn<lowBound||rtn>highBound) throw new IllegalArgumentException("Please enter a number within the valid range");
				illegalArg = false;
			} catch (IllegalArgumentException e) {
				this.centerPrint("I'm sorry i didn't understand, "+e.getMessage());
			}
		} while (illegalArg);
		return rtn;
	}

	public void print(StringBuilder intput) {

		out.print(intput);
		
	}

}
