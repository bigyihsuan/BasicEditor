package basicEditor;

import java.util.*;

public class BasicEditorMain {

	static LineNode root = null;
	static LineNode pointer = root;

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		boolean wantToExit = false;
		boolean hasPrevious = false;
		LineNode temp = null;
		LineNode prev = null;
		//main loop
		System.out.println("READY. INPUT A COMMAND:");
		do {
			pointer = root;
			System.out.print("] ");
			String in = input.nextLine();
			
			//handle attaching code to end of the program
			if (Character.isDigit(in.charAt(0))) {
				
				temp = new LineNode(in);
				while (temp.getLineNumber() <= 0) {
					System.err.println("ERROR: INVALID LINE NUMBER.\n"
										+ "INPUT CODE WITH POSITIVE LINE NUMBER:");
					in = input.nextLine();
					
					
				}
				
				if (!hasPrevious && temp != null) {
					root = temp;
					prev = root;
					hasPrevious = true;
				}
				else if (hasPrevious && temp != null){
					prev.setNext(temp);
					prev = temp;
					hasPrevious = true;
				}
			}
			else if (in.toUpperCase().equals("LIST")) {
				list();
			}
		} while (!wantToExit);

	}
	
	public static void list() {
		if (pointer == null) {
			System.out.println();
		}
		else {
			while (pointer != null) {
				System.out.println(pointer);
				pointer = pointer.getNext();
			}
		}
	}

}
