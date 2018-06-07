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
				else if (hasPrevious && temp != null) {
					//handle changing a line
					if (lineNums.contains(temp.getLineNumber())) {
						//changing the code
						LineNode tempPointer = root;
						if (tempPointer.getLineNumber() == temp.getLineNumber()) {
							//change a code line
							if (!temp.getCode().equals("")) {
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
								//	if has next
								//		set root to next
								//		remove old root
								if (tempPointer == root) {
									if (tempPointer.hasNext()) {
										root = tempPointer.getNext();
										tempPointer.getNext().setPrevious(null);
										tempPointer.setNext(null);
									}
									else {
										root = null;
									}
								}
								//if last line
								//	remove last line from rest
								else if (!tempPointer.hasNext()) {
									tempPointer.getPrevious().setNext(null);
									tempPointer.setPrevious(null);
								}
								//if middle line
								//	link prev.next to next
								//	link next.prev to prev
								else {
									tempPointer.getPrevious().setNext(tempPointer.getNext());
									tempPointer.getNext().setPrevious(tempPointer.getPrevious());
								}
							}
						}
						else {
							tempPointer = tempPointer.getNext();
						}
					}
					//add new line
					else {
						//add in the middle
						if (temp.getLineNumber() < lineNums.last()
								&& prev.hasNext() && prev.getNext().hasPrevious()) {
							lineNums.add(temp.getLineNumber());
							prev.getNext().setPrevious(temp);
							temp.setNext(prev.getNext());
							temp.setPrevious(prev);
							prev.setNext(temp);
							prev = temp;
							hasPrevious = true;
						}
						//add in the end
						else {
							lineNums.add(temp.getLineNumber());
							prev.setNext(temp);
							temp.setPrevious(prev);
							prev = temp;
							hasPrevious = true;
						}
						
					}
				}
			}
			else if (in.toUpperCase().contains("LIST")) {
				if (in.length() == 4) {
					list();
				}
				else {
					list(Integer.parseInt(in.substring(5)), lineNums);
				}
			}
			else if (in.toUpperCase().contains("EXIT")) {
				wantToExit = true;
				LineNode checker = root;
				while (checker.hasNext()) {
					checker = checker.getNext();
				}
				
				if (!checker.getCode().toUpperCase().contains("END")) {
					System.out.println("ERROR: LAST LINE NEEDS END");
					wantToExit = false;
				}
			}
			else if (in.toUpperCase().equals("REN")) {
				if (in.length() == 3) {
					renumber(root, 10);
				}
				else {
					renumber(root, Integer.parseInt(in.substring(3)));
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
		System.out.println();
	}

	public static void list(int in, TreeSet<Integer> lineNums) {
		if (lineNums.isEmpty() || !lineNums.contains(in)) {
			System.out.println();
		}
		else {
			int counter = 0;
			LineNode linPoint = root;

			for (int x : lineNums) {
				if (x != in) {
					counter++;
				}
				else {
					break;
				}
			}
			for (/* counter */; counter > 0; counter--) {
				if (linPoint.getNext() != null) {
					linPoint = linPoint.getNext();
				}
			}
			System.out.println(linPoint);
		}
		System.out.println();
	}
	
	//Renumber lines in muliples of 10 starting at 10.
	//if "REN XYZ", start there. else start 10
	public static void renumber(LineNode root, int startIndex) {		
		LineNode pointer = root;
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>(); //original, new
		
		//find inputed line number
		while (pointer.hasNext()) {
			if (pointer.getLineNumber() != startIndex) {
				pointer = pointer.getNext();
			}
			else { //start renumbering from wherever pointer is
				//get number of lines after
				LineNode temp = pointer;
				int next = 0;
				while (temp.hasNext()) {
					temp = temp.getNext();
					next++;
				}
				
				next *= 10;
				temp = pointer;
				
				for (int i = 0; i < next; i+=10) {
					map.put(temp.getLineNumber(), pointer.getLineNumber() + i);
					if (temp.hasNext()) {
						temp = temp.getNext();
					}
				}
				//has old values mapped to new values, apply new ones
				Set<Integer> keys = map.keySet();
				Iterator<Integer> iter = keys.iterator();
				temp = pointer;
				
				while (iter.hasNext()) {
					int key = iter.next();
					int newLine = map.get(key);
					temp.setLineNumber(newLine);
					
					if (temp.hasNext()) {
						temp = temp.getNext();
					}
				}
				break;
			}
		}
		
	}

}
