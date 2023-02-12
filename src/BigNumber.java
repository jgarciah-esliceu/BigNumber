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
        this.valor = b.valor;
    }

    // Suma
    BigNumber add(BigNumber other) {
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
        String[] s1 = this.valor.split("");
        String[] s2 = other.valor.split("");
        int n1, n2;
        int[] resultMult = new int[s1.length + s2.length];

        for (int i = s1.length - 1; i >= 0; i--) {
            for (int j = s2.length - 1; j >= 0; j--) {
                n1 = Integer.parseInt(s1[i]);
                n2 = Integer.parseInt(s2[j]);
                resultMult[i + j + 1] += n1 * n2;
            }
        }

        int meLlevo = 0;
        for (int i = resultMult.length - 1; i >= 0; i--) {
            int sum = resultMult[i] + meLlevo;
            resultMult[i] = sum % 10;
            meLlevo = sum / 10;
        }

        StringBuilder multiplicacio = new StringBuilder();
        int i = 0;
        while (i < resultMult.length - 1 && resultMult[i] == 0) {
            i++;
        }

        for (i = 0; i < resultMult.length; i++) {
            multiplicacio.append(resultMult[i]);
        }

        return new BigNumber(multiplicacio.toString());
    }

    // Divideix
    BigNumber div(BigNumber other) {
        if (new BigNumber(this.valor).compareTo(new BigNumber(other.toString())) < 0){
            return new BigNumber("0");
        } else if (new BigNumber(this.valor).compareTo(new BigNumber(other.toString())) == 0){
            return new BigNumber("1");
        }
        String res = "";
        String temp = "";
        String resMult = "";
        for (int i = 0; i < this.valor.length() ; i++) {
            int numero1 = Character.getNumericValue(this.valor.charAt(i));
            temp += numero1;

            if (new BigNumber(temp).compareTo(other) >= 0){
                for (int j = 1; j < 11; j++) {
                    resMult = String.valueOf(other.mult(new BigNumber(String.valueOf(j))));

                    if (new BigNumber(resMult).compareTo(new BigNumber(temp)) > 0){
                        res += j-1;
                        temp = String.valueOf(new BigNumber(temp).sub((other.mult(new BigNumber(String.valueOf(j-1))))));

                        break;
                    }
                }

            } else {
                if (res != ""){
                    res += "0";
                }
            }
        }
        return new BigNumber(res);
    }

    // Arrel quadrada
    BigNumber sqrt() {
        StringBuilder res = new StringBuilder();
        BigNumber resto;
        StringBuilder grup = new StringBuilder();
        int puntero = 0;
        boolean primeraVez = true;

        if (this.toString().length() % 2 == 0) {
            for (int i = 0; i < 2; i++) {
                grup.append(this.toString().charAt(i));
                puntero++;
            }
        } else {
            grup.append(this.toString().charAt(0));
            puntero++;
        }
        resto = new BigNumber(grup.toString());

        if (puntero == this.toString().length()) {
            int grupInt = Integer.parseInt(grup.toString());
            for (int i = 0; i < 9; i++) {
                if (i * i > grupInt) {
                    res.append(i - 1);
                    break;
                }
            }
        } else {
            while (puntero < this.toString().length()) {
                if (primeraVez) {
                    primeraVez = false;
                } else {
                    grup = new StringBuilder(resto.toString());
                    for (int i = 0; i < 2; i++) {
                        grup.append(this.toString().charAt(puntero));
                        puntero++;
                    }
                }
                for (int i = 0; i <= 10; i++) {
                    if (new BigNumber(new BigNumber(res.toString()).mult(new BigNumber("2")).toString() + i).mult(new BigNumber(Integer.toString(i))).compareTo(new BigNumber(grup.toString())) == 1) {
                        resto = new BigNumber(grup.toString()).sub(new BigNumber(new BigNumber(res.toString()).mult(new BigNumber("2")).toString() + (i - 1)).mult(new BigNumber(Integer.toString(i - 1))));
                        res.append(i - 1);
                        break;
                    }
                }
            }
        }
        return new BigNumber(res.toString());
    }

    // Potència
    BigNumber power(int n) {
        BigNumber res = new BigNumber(this.valor);
        BigNumber base = new BigNumber(this.valor);

        for (int i = 1; i < n; i++) {
            res = res.mult(base);
        }

        return res;
    }

    // Factorial
    BigNumber factorial() {
        BigNumber res = new BigNumber("1");
        BigNumber base = new BigNumber(this.valor);

        for (int i = 1; i < Integer.parseInt(base.valor) + 1; i++) {
            res = res.mult(new BigNumber(Integer.toString(i)));
        }

        return res;
    }

    // MCD. Torna el Màxim comú divisor
    BigNumber mcd(BigNumber other) {
        BigNumber num1 = new BigNumber(this.valor);
        BigNumber num2 = new BigNumber(other.valor);
        BigNumber resto = new BigNumber(num1.valor);

        // Algoritme d'Euclides
        while (true) {
            resto = num1.sub(num1.div(num2).mult(num2));

            String t = resto.valor;
            num1.valor = num2.valor;
            num2.valor = t;

            if (num1.sub(num1.div(num2).mult(num2)).compareTo(new BigNumber("0")) == 0) {
                break;
            }
        }
        return resto;
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

    // Funciones propias //
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