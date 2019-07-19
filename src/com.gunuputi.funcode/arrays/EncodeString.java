public class EncodeString {

    public static void encode(StringBuffer input) {

        // probably this is not needed
        if (input.length() <= 1) {
            return;
        }

        int charCount = 1;
        int startPos = 0;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) != input.charAt(i - 1)) {
                startPos = updateInputStringBuffer(charCount, input.charAt(i - 1), startPos, input);
                charCount = 1;
                continue;
            }
            charCount++;
        }
        startPos = updateInputStringBuffer(charCount, input.charAt(input.length() - 1), startPos, input);
        input.setLength(startPos);
    }

    private static int updateInputStringBuffer(int charCount, char repeatingChar, int startPos, StringBuffer input) {

        input.setCharAt(startPos++, repeatingChar);
        if (charCount == 1) {
            return startPos;
        }
        String charCountString = String.valueOf(charCount);
        int i = 0;
        while (i < charCountString.length()) {
            input.setCharAt(startPos++, charCountString.charAt(i));
            i++;
        }

        return startPos;

    }

    public static void main(String[] args) {
        StringBuffer test = new StringBuffer("aaabbbcccccccccccccccccccccccccccccddddefghaaaaa");
        encode(test); //a3b2c30d4efgha5
        System.out.println("Encode " + test);

        StringBuffer test1 = new StringBuffer("test");
        encode(test1); //a3b2c30d4efgha5
        System.out.println("Encode " + test1);
        // System.out.println("Encode " + test);
    }

}
