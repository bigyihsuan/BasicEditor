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
		TreeSet<Integer> lineNums = new TreeSet<Integer>();

		//main loop
		System.out.println("READY. INPUT A COMMAND:");
		do {
			pointer = root;
			System.out.print("] ");
			String in = input.nextLine();
			
			//handle typing in code
			if (Character.isDigit(in.charAt(0))) {
				temp = new LineNode(in);

				//invalid line number (negatives and 0)
				while (temp.getLineNumber() <= 0) {
					System.err.println("ERROR: INVALID LINE NUMBER.\n"
							+ "INPUT CODE WITH POSITIVE LINE NUMBER:\n" + "] ");
					in = input.nextLine();
				}

				//adding to end of program
				//empty program
				if (!hasPrevious && temp != null) {
					lineNums.add(temp.getLineNumber());
					root = temp;
					prev = root;
					hasPrevious = true;
				}
				//has something there
				else if (hasPrevious && temp != null){
					//handle changing a line
					if (lineNums.contains(temp.getLineNumber())) {
						//changing the code
						LineNode tempPointer = root;
						while (tempPointer != null) {
							if (tempPointer.getLineNumber() == temp.getLineNumber()) {
								
								//change a code line
								if (tempPointer != null && !temp.getCode().equals("")) {
									tempPointer.setCode(temp.getCode());
									break;
								}
								
								//delete a line
								else {
									//if nothing in the program
									//	do nothing
									if (root == null) {
										break;
									}
									//if first line
									//	if only line
									//		delete line
									//	else (has next)
									//		unlink next prev
									//		set root to next
									else if (tempPointer == root) {
										
									}
									//if not first line
									//	if has next line
									//		
									//		
									else {
										
									}
								}
							}
							else {
								tempPointer = tempPointer.getNext();
							}
						}
					}
					//add new line
					else {
						lineNums.add(temp.getLineNumber());
						prev.setNext(temp);
						temp.setPrevious(prev);
						prev = temp;
						hasPrevious = true;
					}
				}
			}
			else if (in.toUpperCase().compareTo("LIST") >= 0) {
				if (in.length() == 4) {
					list();
				}
				else {
					list(Integer.parseInt(in.substring(6)), lineNums);
				}
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
		pointer = root;
	}
	
	//TODO: line-number list()
	public static void list(int in, TreeSet<Integer> lineNums) {
		if (lineNums.isEmpty()) {
			System.out.println();
		}
		else {
			int counter = 0;
			Iterator<Integer> iter = lineNums.iterator();
			
			LineNode linPoint = root;
			while (iter.hasNext()) {
				int temp = iter.next();
				if (in != temp) {
					counter++;
				}
				else {
					break;
				}
			}
			System.out.println(counter);
			for (/* counter */; counter > 0; counter--) {
				if (linPoint.getNext() != null) {
					linPoint = linPoint.getNext();
				}
			}
			System.out.println(linPoint);
			
		}
	}

}
