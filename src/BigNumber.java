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
        if (this.valor.length() > other.valor.length()){ // Afegeix zeros als numeros que siguin de menor longitud
            other.valor = String.format("%0" + this.valor.length() + "d", Integer.valueOf(other.valor));
        } else if (this.valor.length() < other.valor.length()){
            other.valor = String.format("%0" + other.valor.length() + "d", Integer.valueOf(this.valor));
        }
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
                if (i == 0) {
                    t = (suma + 1) + t;
                } else {
                    t = (suma%10 + 1) + t;
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
}