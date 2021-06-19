package practice;

import com.google.gson.Gson;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PracticeJavaInterview {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 5, 4, 7, 9));
        Map<String, Object> map = new HashMap<>();
        map.put("name", "ajay");
        map.put("age", "30");
        map.put("company", "accenture");
        String string = "ajay kumar singh";
        getExcelDataAsMap("src/test/resources/config/environment/TestData.xlsx", "Login Validation");
    }

    private static void printString(String s, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(s);
        }
    }

    public static void printPattern() {
        StringBuilder string = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            string.append("*");
            System.out.println(string);
        }
    }

    private static void printPattern1(int rows) {
        // for loop for the rows
        for (int i = 1; i <= rows; i++) {
            // white spaces in the front of the numbers
            int numberOfWhiteSpaces = rows - i;

            //print leading white spaces
            printString(" ", numberOfWhiteSpaces);

            //print numbers
            printString(i + " ", i);

            //move to next line
            System.out.println("");
        }
    }

    private static void printPattern2(int rows) {
        // for loop for the rows
        for (int i = 1; i <= rows; i++) {
            // white spaces in the front of the numbers
            int numberOfWhiteSpaces = rows - i;

            //print leading white spaces
            printString(" ", numberOfWhiteSpaces);

            //print numbers
            for (int x = 1; x <= i; x++) {
                System.out.print(x + " ");
            }

            //move to next line
            System.out.println("");
        }
    }

    private static void printPattern3(int rows) {
        // for loop for the rows
        for (int i = 1; i <= rows; i++) {
            // white spaces in the front of the numbers
            int numberOfWhiteSpaces = rows - i;

            //print leading white spaces
            printString(" ", numberOfWhiteSpaces);

            //print character
            printString("* ", i);

            //move to next line
            System.out.println("");
        }
    }

    private static void printPattern4(int rows) {
        // for loop for the rows
        for (int i = 1; i <= rows; i++) {
            // white spaces in the front of the numbers
            int numberOfWhiteSpaces = (rows - i) * 2;

            //print leading white spaces
            printString(" ", numberOfWhiteSpaces);

            //print numbers
            for (int x = 1; x <= i; x++) {
                System.out.print(x + " ");
            }

            //move to next line
            System.out.println("");
        }
    }

    private static void printPattern5(int rows) {
        // for loop for the rows
        for (int i = rows; i >= 1; i--) {
            // white spaces in the front of the numbers
            int numberOfWhiteSpaces = i * 2;

            //print leading white spaces
            printString(" ", numberOfWhiteSpaces);

            //print numbers
            for (int x = i; x <= i; x--) {
                System.out.print(x + " ");
            }
            //move to next line
            System.out.println("");
        }
    }

    private static void printPattern6(int rows) {
        // for loop for the rows
        for (int i = rows; i >= 1; i--) {
            // white spaces in the front of the numbers
            int numberOfWhiteSpaces = rows - i;

            //print leading white spaces
            printString(" ", numberOfWhiteSpaces);

            //print character
            printString("* ", i);

            //move to next line
            System.out.println("");
        }
    }

    private static void printPattern7(int rows) {
        // for loop for the rows
        for (int i = rows; i >= 1; i--) {
            // white spaces in the front of the numbers
            int numberOfWhiteSpaces = rows - i;

            //print leading white spaces
            printString(" ", numberOfWhiteSpaces);

            //print character
            printString(i + " ", i);

            //move to next line
            System.out.println("");
        }
    }

    //Find factorial of an integer?
    public static long factorial(long n) {
        if (n == 1)
            return 1;
        else
            return (n * factorial(n - 1));
    }

    //How to remove leading and trailing whitespaces from a string?
    public static void removeLeadingTrailingWhiteSpace() {
        String str = " ajay kumar ";
        System.out.println(str.trim());
    }

    //Check if a List of integers contains only odd numbers?
    public static boolean onlyOddNumbers(List<Integer> list) {
        for (int i : list) {
            if (i % 2 == 0)
                return false;
        }
        return true;
    }

    //Java Program to check if a vowel is present in the string?
    public static boolean checkIfStringContainsVowel(String str) {
        return str.toLowerCase().matches(".*[aeiou].*");
    }

    public static void mapIteration(Map<String, Object> map) {

        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> me = iterator.next();
            System.out.println(me.getKey() + ":" + me.getValue());
        }

        for (Map.Entry<String, Object> me : map.entrySet()) {
            System.out.println(me.getKey() + ":" + me.getValue());
        }
    }

    //Write a Java Program to find the count of words in a string using StringTokenizer
    public static void wordCountUsingStringTokenizer(String string) {
        StringTokenizer stringTokenizer = new StringTokenizer(string);
        System.out.println(stringTokenizer.countTokens());
    }

    //Write a Java Program to find the count of words in a string using String Split
    public static void wordCountUsingSplit(String string) {
        String[] strings = string.split(" ");
        System.out.println(strings.length);
    }

    public static void getExcelDataAsMap(String filepath, String worksheet) {
        Map<String, LinkedHashMap<String, Object>> dataMap = new LinkedHashMap<>();
        LinkedHashMap<String, Object> mapTemp = null;
        String dataset = null;
        Row headerRow = null;
        Object key = null;
        Object value = null;
        try (FileInputStream file = new FileInputStream(filepath);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            XSSFSheet sheet = workbook.getSheet(worksheet);
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            int lastRow = sheet.getLastRowNum();
            int currentRowNum = 0;
            while (currentRowNum < lastRow) {
                headerRow = sheet.getRow(currentRowNum);

                dataset = getCellData(sheet.getRow(headerRow.getRowNum()).getCell(0), formulaEvaluator).toString();
                mapTemp = new LinkedHashMap<>();
                for (int cellCounter = 1; cellCounter < sheet.getRow(currentRowNum).getLastCellNum(); cellCounter++) {
                    key = getCellData(sheet.getRow(headerRow.getRowNum()).getCell(cellCounter), formulaEvaluator);
                    value = getCellData(sheet.getRow(currentRowNum + 1).getCell(cellCounter), formulaEvaluator);
                    if (value != null && key != null) {
                        mapTemp.put(key.toString(), value);
                    }
                }
                currentRowNum += 2;
                dataMap.put(dataset, mapTemp);
            }
        } catch (Exception exception) {
            System.out.print(exception);
        }
        System.out.println(dataMap);
    }

    private static Object getCellData(Cell cell, FormulaEvaluator formulaEvaluator) {
        Object obj = null;
        if (cell == null) return null;
        switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    obj = (new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue()));
                } else {
                    obj = (long) cell.getNumericCellValue();
                }
                break;
            case BLANK:
                obj = null;
                break;
            case STRING:
            default:
                obj = (cell.getStringCellValue());
        }
        return obj;
    }

    public static void loadPropsDataAsMap(String propsFilePath) throws ConfigurationException {
        File propsFile = new File(propsFilePath);
        PropertiesConfiguration props = new PropertiesConfiguration();
        props.load(propsFile);
        Iterator<String> iterator = props.getKeys();
        Map<String, String> map = new HashMap<>();
        while (iterator.hasNext()) {
            String key = iterator.next();
            map.put(key, props.getString(key));
        }
        System.out.println(map);
    }

    public static void loadJSONDataAsMap(String filepath) {
        Gson gson = new Gson();
        Map map = new HashMap<>();
        try {
            FileReader reader = new FileReader(filepath);
            map = gson.fromJson(reader, Map.class);
        } catch (FileNotFoundException ignored) {

        }
        System.out.println(map);

    }

    public static void loadJSONDataAsListOfMap(String filepath) {
        Gson gson = new Gson();
        List mapList = new ArrayList<>();
        try {
            FileReader reader = new FileReader(filepath);
            mapList = gson.fromJson(reader, List.class);
        } catch (FileNotFoundException ignored) {

        }
        System.out.println(mapList);

    }

    //Write a Java Program to remove all white spaces from a string with using replace().
    public static void removeWhiteSpaceUsingReplaceAll(String string) {
        String stringWithoutWhiteSpace = string.replaceAll("\\s", "");
        System.out.println(stringWithoutWhiteSpace);
    }

    public static void removeWhiteSpaceUsingSplit() {
        String inputString = "ajay kumar";
        String[] strings = inputString.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            if (!string.isEmpty()) {
                stringBuilder.append(string);
            }
        }
        System.out.println(stringBuilder);
    }

    public static void removeWhiteSpaceUsingChars() {
        String string = "ajay kumar";
        char[] chars = string.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (Character character : chars) {
            if (!Character.isWhitespace(character)) {
                stringBuilder.append(character);
            }
        }
        System.out.println(stringBuilder);
    }

    //Write a Java Program to find whether a string or number is palindrome or not.
    public static void checkIfPalindrome(String string) {
        String[] strings = string.split("");
        StringBuilder reversed = new StringBuilder();
//        reversed.append(string).reverse();
        for (int i = strings.length - 1; i >= 0; i--) {
            reversed.append(strings[i]);
        }
        if (string.equals(reversed.toString()))
            System.out.println("palindrome: " + string);
        else
            System.out.println("not palindrome: " + string);
    }

    public static void checkIfPalindrome(int num) {
        int number = num;
        int reverse = 0;

        while (num != 0) {
            int remainder = num % 10;
            reverse = reverse * 10 + remainder;
            num = num / 10;
        }
        if (number == reverse) {
            System.out.println("palindrome: " + number);
        } else {
            System.out.println("not palindrome: " + number);
        }
    }

    //Write a Java Program to find whether a number is prime or not.
    public static boolean isPrime(int num) {
        if (num == 0 || num == 1) {
            return false;
        } else if (num == 2) {
            return true;
        }
        for (int i = 2; i < num; i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    public static void reverseStringUsingStream(String string) {
        String result = Stream.of(string)
                .map(word -> new StringBuilder(word).reverse())
                .collect(Collectors.joining(""));
        System.out.print(result);
    }

    public static void reverseStringUsingStringBytes(String string) {
        byte[] input = string.getBytes();
        byte[] output = new byte[string.length()];

        for (int i = string.length() - 1; i >= 0; i--) {
            output[i] = input[string.length() - i - 1];
        }
        System.out.println(new String(output));
    }

    public static void reverseStringUsingSwapping(String string) {

        char[] input = string.toCharArray();
        char[] output = new char[string.length()];

        for (int i = 0; i < string.length(); i++) {
            char temp = input[i];
            output[i] = input[string.length() - i - 1];
            input[string.length() - 1 - i] = temp;
        }
        System.out.println(new String(output));
    }

    public static void reverseStringUsingCharArray(String string) {
        char[] input = string.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = input.length - 1; i >= 0; i--) {
            stringBuilder.append(input[i]);
        }
        System.out.println(stringBuilder);

    }

    public static void reverseStringUsingStringBuilder(String string) {
        StringBuilder sb = new StringBuilder();
        sb.append(string);
        sb.reverse();
        System.out.println(sb);
    }

    public static void reverseStringUsingStringBuffer(String string) {
        StringBuffer sb = new StringBuffer();
        sb.append(string);
        sb.reverse();
        System.out.println(sb);
    }

    public static void reverseStringUsingArrayList(String string) {
        char[] input = string.toCharArray();
        List<Character> characterList = new ArrayList<>();
        for (Character character : input) {
            characterList.add(character);
        }
        Collections.reverse(characterList);
        for (Character character : characterList) {
            System.out.print(character);
        }

    }

    public static void reverseStringUsingStringSplit(String string) {
        String[] strings = string.split("");
        for (int i = strings.length - 1; i >= 0; i--) {
            System.out.print(strings[i] + "");
        }
    }

    //Write a Java Program to swap two numbers using the third variable.
    public static void swapValueUsingThirdVariable(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        System.out.printf("%s %s%n", a, b);
    }

    //Write a Java Program to swap two numbers without using the third variable.
    public static void swapValueWithoutUsingThirdVariable(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.printf("%s %s%n", a, b);
    }

    //Write a Java Program to find the count of repeated words in a string using HashMap
    public static void repeatedAndMaximumWordCount() {
        String string = "ajay kumar ajay name ajay kumar jai ho";
        String[] strings = string.split(" ");
        HashMap<String, Integer> map = new HashMap<>();
        for (String str : strings) {
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);

            } else {
                map.put(str, 1);
            }
        }
        System.out.println(map);
        Set<String> set = map.keySet();
        String key = "";
        int val = 0;
        for (String str : set) {
            if (val < map.get(str)) {
                key = str;
                val = map.get(str);
            }
        }
        System.out.println(key + ":" + val);
    }

    public static void duplicateAndMaximumCharacterCount() {
        String word = "aaabccccddeeee";
        String[] strings = word.split("");
        Map<String, Integer> map = new HashMap<>();
        for (String strChar : strings) {
            if (map.containsKey(strChar)) {
                map.put(strChar, map.get(strChar) + 1);
            } else {
                map.put(strChar, 1);
            }
        }
        System.out.println(map);
        Set<String> set = map.keySet();
        int val = 0;
        String key = "";
        for (String str : set) {
            if (val < map.get(str)) {
                key = str;
                val = map.get(str);
            }
        }
        System.out.println(key + ":" + val);
    }

    public static void repeatedWordCountUsingFrequency(String string) {
        List<String> list = Arrays.asList(string.split(" "));

        Set<String> uniqueWords = new HashSet<String>(list);
        for (String word : uniqueWords) {
            System.out.println(word + ": " + Collections.frequency(list, word));
        }
    }

    public static boolean checkStringIfAnagram(String str1, String str2) {

        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        if (chars1.length != chars2.length) {
            return false;
        } else {
            for (int i = 0; i < chars1.length; i++) {
                if (chars1[i] != chars2[i])
                    return false;
            }
        }
        return true;
    }

    public static void printFibonacciSeries(int count) {
        int a = 0;
        int b = 1;
        int c = 1;
        for (int i = 1; i <= count; i++) {
            System.out.print(a + ", ");
            a = b;
            b = c;
            c = a + b;
        }
    }

    public static int fibonacciSeries(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacciSeries(n - 1) + fibonacciSeries(n - 2);
        }
    }
}
