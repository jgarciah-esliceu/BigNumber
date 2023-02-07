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
        BigNumber resultatMultiplicacio = new BigNumber("");
        BigNumber res = new BigNumber("0");

        String n1 = this.valor;
        String n2 = other.valor;

        //Variable "operación" para guardar la operación resultado, variable "meLlevo" para saber cuándo debemos sumar a la posterior operación y el BigNumber "nada"
        // para guardar todas las sumas de los resultados de las multiplicaciones
        int operacion = 0;
        int meLlevo = 0;

        for (int i = 1; i < n2.length() + 1; i++) {
            //Cada vez que cambiamos de número en la fila de abajo, ponemos el resultado vacío y conseguimos el número el que lo multiplicaremos con
            // la fila de arriba
            resultatMultiplicacio.valor = "";
            String multiplicarN2 = Character.toString(n2.charAt(n2.length() - i));

            //Recorremos la fila de arriba, yendo multiplicando por cada uno de los números de arriba por número que hemos conseguido antes
            for (int j = 1; j < n1.length() + 1; j++) {
                //Vamos consiguiendo el número por el que se multiplicara, hasta que termine la fila de arriba (s1)
                String multiplicarN1 = Character.toString(n1.charAt(n1.length() - j));

                //Y multiplicamos los dos números
                operacion = Integer.parseInt(multiplicarN1) * Integer.parseInt(multiplicarN2) + meLlevo;

                //Una vez se ha multiplicado se ha de poner la variable nos llevamos a 0
                meLlevo = 0;

                //Si la operación resultante es menor a 10, querrá decir que no debemos quitarnos resultatMultiplicacio. Simplemente asignamos la operación resultante a la variable "resultatMultiplicacio"
                if (operacion < 10) {
                    resultatMultiplicacio.valor += operacion;

                    //mientras j sea menor al tamaño de s1 (fila de arriba), deberemos conseguir la decena y la unidad, e ir quitándonos la decena y guardar la unidad a resultatMultiplicacio
                } else if (j < n1.length()) {
                    String seperaDigits = Integer.toString(operacion);
                    int desena = Character.getNumericValue(seperaDigits.charAt(0));
                    int unitat = Character.getNumericValue(seperaDigits.charAt(1));

                    resultatMultiplicacio.valor += unitat;
                    meLlevo = desena;
                } else { // Si se da el caso de que la operacion es mayor a 10 pero es el ultimo numero, pondremos el resultado le la operacion tal y como esta (ya que es el final
                    // y no debemos seguir operando)
                    resultatMultiplicacio.valor += giraResultat(Integer.toString(operacion));
                }
            }

            //Per cada multiplicació que feim, hem d'anar afegint zeros. Per cada operacio que feim afegirem un 0. A més, com hem estat afegint directament el resultat a un string
            // toca invertir el resultat i ho farem amb giraResultat()
            resultatMultiplicacio.valor = afegeixZeros(giraResultat(resultatMultiplicacio.valor), i - 1);

            //I anam guardant els resultat de les sumes en res
            res = resultatMultiplicacio.add(res);
        }

        return res;
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
        BigNumber res = new BigNumber(this.valor);
        BigNumber base = new BigNumber(this.valor);

        //hacemos un bucle que multiplique el numero n veces.
        // Ej: 7^4 = 7*7*7*7 = 2401
        for (int i = 1; i < n; i++) {
            res = res.mult(base);
        }

        return res;
    }

    // Factorial
    BigNumber factorial() {
        BigNumber res = new BigNumber("1");
        BigNumber base = new BigNumber(this.valor);

        //Hacemos otro bucle que vaya multiplicando "res" por la variable i.
        // La variable "i" irá del número hasta uno, bajando uno cada vez que pase el bucle.
        // Ej: 5 = 5*4*3*2*1 = 120
        for (int i = 1; i < Integer.parseInt(base.valor) + 1; i++) {
            res = res.mult(new BigNumber(Integer.toString(i)));
        }

        return res;
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

    //Torna un string amb el string que l'hi passam per parametre totalment girat
    public String giraResultat(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < s.length() + 1; i++) {
            String actualXifra = Character.toString(s.charAt(s.length() - i));
            res.append(actualXifra);
        }
        return res.toString();
    }

    //Torna un string amb la quantitat de zeros que l'hi indiquem. Aquest zeros s'afegeixen al final del string passat, es a dir, a la dreta.
    public String afegeixZeros(String s, int quantitatZeros) {
        return s + "0".repeat(quantitatZeros);
    }
}