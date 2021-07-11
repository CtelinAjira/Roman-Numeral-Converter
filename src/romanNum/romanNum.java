package romanNum;

import java.util.HashMap;

public class romanNum {
	private static String[] numeralCache = {"I", "V", "X", "L", "C", "D", "M"};
	private static int[] numberCache = {1, 5, 10, 50, 100, 500, 1000};
	private static HashMap<String, Integer> digitCache = new HashMap<String, Integer>();
	
	private static int initDigitCache() {
		int i = 0;
		int length = Math.min(numeralCache.length, numberCache.length);
		while (i < length) {
			digitCache.put(numeralCache[i], numberCache[i]);
			i++;
		}
		return numeralCache.length - numberCache.length;
	}
	
	@SuppressWarnings("static-access")
	private static boolean isRomanNumeralValid(String numeral) {
		String workingNumeral = numeral.toUpperCase().trim();
		int numLength = workingNumeral.length();
		char[] numeralArray = new char[numLength];
		workingNumeral.getChars(0, numLength, numeralArray, 0);
		String currentChar = "";
		int validChars = 0;
		for (int i = 0; i < numLength; i++) {
			currentChar = currentChar.copyValueOf(numeralArray, i, 1);
			for (String j : digitCache.keySet()) {
				if (currentChar.equals(j)) {
					validChars++;
				}
			}
		}
		return (validChars == numeral.length());
	}
	
	@SuppressWarnings("static-access")
	private static int evalNumeral(String numeral) {
		String workingNumeral = numeral.toUpperCase().trim();
		int sum = 0;
		int numLength = workingNumeral.length();
		char[] numeralArray = new char[numLength];
		workingNumeral.getChars(0, numLength, numeralArray, 0);
		String leftNumeral = "";
		String rightNumeral = "";
		
		for (int i = 0; i < numLength-1; i++) {
			leftNumeral = leftNumeral.copyValueOf(numeralArray, i, 1);
			rightNumeral = rightNumeral.copyValueOf(numeralArray, i+1, 1);
			
			if (digitCache.get(leftNumeral) < digitCache.get(rightNumeral)) {
				sum -= digitCache.get(leftNumeral);
			} else {
				sum += digitCache.get(leftNumeral);
			}
			
			leftNumeral = "";
			rightNumeral = "";
		}
		
		rightNumeral = String.copyValueOf(numeralArray, numeralArray.length-1, 1);
		sum += digitCache.get(rightNumeral);
		
		return sum;
	}
	
	public static void main(String[] args) {
		int cacheDiff = initDigitCache();
		if (cacheDiff > 0) {
			System.out.println("Invalid hash map - character array longer than number array");
		} else if (cacheDiff < 0) {
			System.out.println("Invalid hash map - number array longer than character array");
		} else {
			String numeral = "MCDXLIV";
			boolean isValid = isRomanNumeralValid(numeral);
			if (isValid) {
				int numeralEval = evalNumeral(numeral);
				System.out.println(numeralEval);
			} else {
				System.out.println("Invalid input string - string cannot be evaluated as a roman numeral");
			}
		}
	}
}
