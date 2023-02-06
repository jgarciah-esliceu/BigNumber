class BigNumber {
    String valor;

    // Constructor 1
    public BigNumber(String s) {
        s = s.replaceFirst("^0*", ""); // Reemplaça tots els zero de l'esquerra
        if (s.isEmpty()) s = "0"; // Si s està buit, li direm que és igual a 0,
        this.valor = s;
    }

    // Constructor 2
    public BigNumber(BigNumber b) {

    }

    // Suma
    BigNumber add(BigNumber other) {
        // Afegeix zeros al numero que sigui de menor longitud
        zerosIzq(other);
        int n1;
        int n2;
        int suma = 0;
        String[] s1 = this.valor.split("");
        String[] s2 = other.valor.split("");
        BigNumber resultat = null;
        String t = "";

        for (int i = s1.length - 1; i >= 0; i--) {
            n1 = Integer.parseInt(s1[i]);
            n2 = Integer.parseInt(s2[i]);
            int meLlevo = suma;
            suma = n1 + n2;

            if (meLlevo >= 10){
                suma++;
                if (i == 0) {
                    t = suma + t;
                } else {
                    if (suma % 10 == 0) {
                        t = 0 + t;
                    } else {
                        t = (suma%10) + t;
                    }
                }
            } else {
                if (i == 0) {
                    t = suma + t;
                } else {
                    t = (suma%10) + t;
                }
            }
            resultat = new BigNumber(t);
        }
        return resultat;
    }

    // Resta
    BigNumber sub(BigNumber other) {
        zerosIzq(other);
        int n1;
        int n2;
        int resta = 0;
        String[] s1 = this.valor.split("");
        String[] s2 = other.valor.split("");
        BigNumber resultat = null;
        String t = "";

        for (int i = s1.length - 1; i >= 0; i--) {
            n1 = Integer.parseInt(s1[i]);
            n2 = Integer.parseInt(s2[i]);
            int meLlevo = resta;
            resta = n1 - n2;

            if (meLlevo < 0) {
                resta += 10;
                resta--;
                if (i == 0) {
                    t = (resta%10) + t;
                } else {
                    if (resta % 10 == 0) {
                        t = 0 + t;
                    } else {
                        t = (resta % 10) + t;
                    }
                }
                resta -= 10;
            } else if (i == 0) {
                t = resta + t;
            } else {
                resta += 10;
                t = (resta % 10) + t;
                resta -= 10;
            }
            resultat = new BigNumber(t);
        }
        return resultat;
    }



    // Multiplica
    BigNumber mult(BigNumber other) {
        zerosIzq(other);
        int n1;
        int n2;
        int multiplicacio = 0;
        String[] s1 = this.valor.split("");
        String[] s2 = other.valor.split("");
        BigNumber resultat = null;
        String t = "";

        for (int i = 0; i < s2.length; i++) {
            n1 = Integer.parseInt(s1[i]);
            n2 = Integer.parseInt(s2[i]);

            multiplicacio = n1 + n1;
            t = multiplicacio + t;

            resultat = new BigNumber(t);
        }

        return resultat;
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
        if (this.valor.length() == other.valor.length()) {
            for (int i = 0; i < this.valor.length(); i++) {
                if (this.valor.charAt(i) > other.valor.charAt(i)) {
                    return 1;
                } else if (this.valor.charAt(i) < other.valor.charAt(i))
                    return -1;
            } return 0;
        } else if (this.valor.length() > other.valor.length()) {
            return 1;
        }
        return -1;
    }

    // Torna un String representant el número
    public String toString() {
        return this.valor;
    }

    // Mira si dos objectes BigNumber són iguals
    public boolean equals(Object other) { // Object es una classe especial de java, que esta per damunt de la resta de classes
        BigNumber b = (BigNumber) other;
        if (b.valor.equals(this.valor)) return true;
        return false;
    }

    // Funciones propias
    private void zerosIzq(BigNumber other) {
        // Afegeix zeros al numero que sigui de menor longitud
        if (this.valor.length() > other.valor.length()) {
            for (int i = other.valor.length(); i <= this.valor.length()-1; i++) {
                other.valor = "0".concat(other.valor);
            }
        } else if (this.valor.length() < other.valor.length()) {
            for (int i = this.valor.length(); i <= other.valor.length()-1; i++) {
                this.valor = "0".concat(this.valor);
            }
        }
    }
}