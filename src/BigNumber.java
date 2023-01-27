class BigNumber {
    String valor;

    public static String eliminaZeros(String str) {
        int i = 0; // Aquesta variable contarà tots els zeros que hi ha a l'esquerra
        while (i < str.length() && str.charAt(i) == '0') i++;

        StringBuffer num = new StringBuffer(str); // StringBuffer és com un String, a diferència que aquest pot cambiar el seu tamany
        num.replace(0, i, "");
        return num.toString();
    }

    // Constructor 1
    public BigNumber(String s) {
        this.valor = eliminaZeros(s);

    }

    // Constructor 2
    public BigNumber(BigNumber b) {

    }

    // Suma
    BigNumber add(BigNumber other) {
        return null;
    }

    // Resta
    BigNumber sub(BigNumber other) {
        return null;
    }

    // Multiplica
    BigNumber mult(BigNumber other) {
        return null;
    }

    // Divideix
    BigNumber div(BigNumber other) {
        return null;
    }

    // Arrel quadrada
    BigNumber sqrt() {
        return null;
    }

    // Potència
    BigNumber power(int n) {
        return null;
    }

    // Factorial
    BigNumber factorial() {
        return null;
    }

    // MCD. Torna el Màxim comú divisor
    BigNumber mcd(BigNumber other) {
        return null;
    }

    // Compara dos BigNumber. Torna 0 si són iguals, -1
// si el primer és menor i torna 1 si el segon és menor
    public int compareTo(BigNumber other) {
        BigNumber b = other;
        if (b.valor.equals(this.valor)) {
            return 0;
        }

        return -1;
    }

    // Torna un String representant el número
    public String toString() {
        return "";
    }

    // Mira si dos objectes BigNumber són iguals
    public boolean equals(Object other) { // Object es una classe especial de java, que esta per damunt de la resta de classes
        BigNumber b = (BigNumber) other;
        if (b.valor.equals(this.valor)) return true;
        return false;
    }
}