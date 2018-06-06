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
	//String is a subtype of assignment

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
					if (code.contains("\"")) {
						type = CodeType.STRING;
					}
				}
				if (code.contains("REM")) {
					type = CodeType.COMMENT;
				}
				if (code.contains("DIM")) {
					type = CodeType.ARRAY_DECLARATION;
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
	private boolean isValidName(String in) {
		switch (type) {
			case ASSIGNMENT: {
				for (char c : getName(in).toCharArray()) {
					if (!Character.isLetterOrDigit(c)) {
						return false;
					}
				}
				break;
			}
			case ARRAY_DECLARATION: {
				if (getName(in).contains("(") && getName(in).contains(")")
						&& getName(in).indexOf("(") < getName(in).indexOf(")")) {
					for (char c : getName(in).toCharArray()) {
						if (!Character.isLetterOrDigit(c) && c != '(') {
							return false;
						}
					}
				}
				break;
			}
			case STRING: {
				if (!getName(in).contains("$")) {
					type = CodeType.NOTHING;
					return false;
				}
				break;
			}
			case COMMENT:
			default:
				break;
		}
		return true;
	}
	
	//returns the name. Meant for assignments, arrays, and strings.
	private String getName(String inCode) {
		String out = "";
		int start = 0;
		switch (type) {
			case ASSIGNMENT: {
				start = 0;
				for (int i = start; i < inCode.length(); i++) {
					if (Character.isWhitespace(inCode.charAt(i))
							|| !Character.isLetterOrDigit(inCode.charAt(i))) {
						out = inCode.substring(0, i);
					}
				}
				break;
			}
			case ARRAY_DECLARATION: {
				start = 4;
				for (int i = start; i < inCode.length(); i++) {
					if (Character.isWhitespace(inCode.charAt(i))
							|| inCode.charAt(i) != ')') {
						//will include the parentheses
						//e.g. AB(9)
						out = inCode.substring(0, i);
					}
				}
				break;
			}
			case STRING: {
				start = 0;
				for (int i = start; i < inCode.length(); i++) {
					if (inCode.charAt(inCode.length() - 1) == '$') {
						out = inCode.substring(0, i);
					}
				}
				break;
			}
		}

		return out;
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
