package encryptdecrypt;

interface AlgorithmStrategy {

    String encrypt(String data, int key);
    String decrypt(String data, int key);
}

class UnicodeAlgorithm implements AlgorithmStrategy {
    
    @Override
    public String encrypt(String data, int key) {
        StringBuilder output = new StringBuilder();
        if (!data.isEmpty()) {
            for (char ch : data.toCharArray()) {
                output.append((char) (ch + key));
            }
        }
        return output.toString();
    }

    @Override
    public String decrypt(String data, int key) {
        return encrypt(data, - key);
    }
}

class ShiftAlgorithm implements AlgorithmStrategy {

    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final char[] lowerChars = LOWER_CASE.toCharArray();
    private final char[] upperChars = UPPER_CASE.toCharArray();

    @Override
    public String encrypt(String data, int key) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            if (Character.isLowerCase(data.charAt(i))) {
                for (int j = 0; j < lowerChars.length; j++) {
                    if (data.charAt(i) == lowerChars[j]) {
                        output.append(lowerChars[(j + key) % 26]);
                    }
                }
            } else if (Character.isUpperCase(data.charAt(i))) {
                for (int k = 0; k < upperChars.length; k++) {
                    if (data.charAt(i) == upperChars[k]) {
                        output.append(upperChars[(k + key) % 26]);
                    }
                }
            } else {
                output.append(data.charAt(i));
            }
        }
        return output.toString();
    }

    @Override
    public String decrypt(String data, int key) {
        return encrypt(data, 26 - (key % 26));
    }
}