package encryptdecrypt;

class Cipher {
    
    private AlgorithmStrategy algorithmType;

    public void setAlgorithm(String algorithm) {
        if (algorithm.equals("unicode")){
            this.algorithmType = new UnicodeAlgorithm();
        } else {
            this.algorithmType = new ShiftAlgorithm();
        }
    }

    public String encode(String data, int key) {
        return this.algorithmType.encrypt(data, key);
    }

    public String decode(String data, int key) {
        return this.algorithmType.decrypt(data, key);
    }
}