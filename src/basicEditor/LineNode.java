package basicEditor;

public class LineNode {
	
	private int lineNumber;
	private String code;
	private LineNode next;
	private LineNode prev;
	
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
			lnEnd = input.length();
		}
		
		lineNumber = Integer.parseInt(input.substring(lnStart, lnEnd));		
		if (input.length() > new Integer(input.substring(lnStart, lnEnd)).toString().length()) {
			code = input.substring(lnEnd).toUpperCase();
		}
		next = null;
		prev = null;
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
	
	public LineNode getPrevious() {
		return prev;
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
	
	public void setPrevious(LineNode p) {
		prev = p;
	}
	
	public boolean hasNext() {
		return next != null;
	}
	
	
	@Override
	public String toString() {
		return lineNumber + " " + code;
	}
}
