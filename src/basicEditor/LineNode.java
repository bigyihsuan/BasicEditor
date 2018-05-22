package basicEditor;

public class LineNode {
	
	private int lineNumber;
	private String code;
	private LineNode next;
	
	public LineNode(String input) {
		parse(input);
	}
	
	public LineNode(int l, String c, LineNode n) {
		lineNumber = l;
		code = c;
		next = n;
	}
	
	private void parse(String input) {
		//go through characters until find a number
		//once find num, go until find a space
		//lineNumber found
		//rest of string is code
		//next is null for now
		char[] arr = input.toCharArray();
		int lnStart = 0;
		int lnEnd = 0;
		
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == ' ') {
				lnEnd = i;
				break;
			}
		}
		
		lineNumber = Integer.parseInt(input.substring(lnStart, lnEnd + 1));		
		code = input.substring(lnEnd);
		next = null;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public String getCode() {
		return code;
	}

	public LineNode getNext() {
		return next;
	}

	public void setLineNumber(int ln) {
		lineNumber = ln;
	}

	public void setCode(String c) {
		code = c;
	}

	public void setNext(LineNode n) {
		next = n;
	}
	
}
