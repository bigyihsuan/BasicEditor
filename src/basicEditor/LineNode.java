package basicEditor;

public class LineNode {

	private int lineNumber;
	private String code;
	private LineNode prev;
	private LineNode next;
	private CodeType type;
	private enum CodeType {
		COMMENT, ASSIGNMENT, ARRAY_DECLARATION, STRING, DEFAULT, NOTHING
	}

	public LineNode(String input) {
		parse(input);
	}

	public LineNode(int l, String c, LineNode p, LineNode n) {
		lineNumber = l;
		code = c;
		prev = p;
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
			if (code.length() >= 4) {
				if (code.contains("LET")) {
					type = CodeType.ASSIGNMENT;
				}
				if (code.contains("REM")) {
					type = CodeType.COMMENT;
				}
			}
			else {
				type = CodeType.DEFAULT;
			}
		}
		else {
			code = "";
			type = CodeType.NOTHING;
		}
		next = null;
		prev = null;
	}

	/* 
	Valid names:
	A, AB, ABC, etc...
	A1, A2, A3, etc...

	if a string, must have '$'
	 */
	private boolean checkType(String in) {
		switch (type) {
			case COMMENT:
				break;
			case ASSIGNMENT:
				break;
			case ARRAY_DECLARATION:
				break;
			case STRING:
				break;
			default:
				break;
		}

		return true;
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

	public CodeType getCodeType() {
		return type;
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

	public boolean hasPrevious() {
		return prev != null;
	}

	@Override
	public String toString() {
		return lineNumber + " " + code;
	}
}
