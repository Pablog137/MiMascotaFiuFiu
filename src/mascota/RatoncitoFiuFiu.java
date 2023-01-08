package mascota;

public class RatoncitoFiuFiu {

    //Atributos
    private String nombre;
    private int edad;
    private int peso;
    private byte hambre;
    private byte suciedad;
    private byte salud;
    private byte energia;
    private byte felicidad;

    private byte enfermedad;
    private int contador; //Último método

    //Constantes
    private static final int INFANCIA = 0;
    private static final int ADULTA = 1;
    private static final int VEJEZ = 2;


    public RatoncitoFiuFiu(String nombre, int peso, byte hambre, byte suciedad, byte salud, byte energia, byte felicidad, byte enfermedad) {
        // Un objeto mascota.RatoncitoFiuFiu debería informar cuando nace...
        this.nombre = nombre;
        this.peso = peso;
        this.hambre = hambre;
        this.suciedad = suciedad;
        this.salud = salud;
        this.energia = energia;
        this.felicidad = felicidad;
        this.enfermedad = enfermedad;
    }


    public String estadisticas() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre).append('\n');
        sb.append("Edad: ").append(edad).append('\n');
        sb.append("Peso: ").append(peso).append('\n');
        sb.append("Hambre: ").append(hambre).append('\n');
        sb.append("Suciedad: ").append(suciedad).append('\n');
        sb.append("Salud: ").append(salud).append('\n');
        sb.append("Energia: ").append(energia).append('\n');
        return sb.toString();
    }

    public void limpiar(float esfuerzoHigienico) {
        if (!estasMuerto()) {
            if (suciedad - esfuerzoHigienico > 0) {
                suciedad -= esfuerzoHigienico;
            } else {
                suciedad = 0;
            }
            cuantoEnfermo(esfuerzoHigienico);
        }

    }

    private void cuantoEnfermo(float numero) {
        if (enfermedad - numero > 0) {
            enfermedad -= numero;
        } else {
            enfermedad = 0;
        }
        if (enfermedad == 100) {
            estasMuerto();
        }
    }

    public int queTramoEdad() {

        if (edad <= 500) {
            return INFANCIA;
        } else if (edad >= 500 && edad < 1000) {
            return ADULTA;
        } else {
            return VEJEZ;
        }

    }


    public boolean estasDormido() {
        boolean estaDormido;
        if (queTramoEdad() == INFANCIA) {
            if (energia < 60) {
                estaDormido = true;
            } else {
                estaDormido = false;
            }
        } else if (queTramoEdad() == ADULTA) {
            if (energia < 50) {
                estaDormido = true;
            } else {
                estaDormido = false;
            }
        } else {
            if (energia < 40) {
                estaDormido = true;
            } else {
                estaDormido = false;
            }

        }
        return estaDormido;
    }

    public boolean estasEnfermo() {
        if (salud < 40 && salud >= 1 || hambre == 0 || enfermedad == 70) {
            return true;
        } else {
            return false;
        }
    }

    public boolean estasSucio() {
        if (suciedad > 70) {
            return true;
        } else {
            return false;
        }
    }

    public boolean estasMuerto() {
        if (salud == 0 || energia == 0 || enfermedad == 100 || peso == 0) {
            return true;
        }
        return false;
    }

    public void envejecer(int segundos) {
        if (!estasMuerto()) {
            this.edad += segundos;
            if (edad % 100 == 0) {
                ataque();
            }

            if (hambre < 100) {
                hambre++;
            }
            if (suciedad < 100) {
                suciedad++;
            }
            if (salud > 0) {
                salud--;
            }

            if (salud == 0) {
                estasMuerto();
            }
            if (energia > 0) {
                energia--;
            }
            if (energia == 0) {
                estasMuerto();
            }
            if (peso > 0) {
                pierdePeso(edad);
            }
            if (queTramoEdad() == INFANCIA || queTramoEdad() == ADULTA) {
                if (edad % 2 == 0) {
                    enfermedad++;
                }
            } else {
                if (enfermedad < 100) {
                    if (estasEnfermo()) {
                        enfermedad += 2;
                    } else {
                        enfermedad++;
                    }
                }
                if (enfermedad == 100) {
                    estasMuerto();
                }

            }
        }

    }


    public boolean tienesQuejas() {
        if (hambre > 75 && !estasEnfermo() && !estasDormido()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean tienesHambre() {
        if (hambre > 40) {
            return true;
        }
        return false;
    }

    public boolean estasFeliz() {
        if (tienesHambre() && estasEnfermo() && estasSucio()) {
            return false;
        }
        return true;
    }

    public void alimentar(float cantidadAlimento) {
        if (!estasMuerto()) {
            if (hambre - cantidadAlimento > 0) {
                ganarPeso(cantidadAlimento);
                aumentarEnergia(cantidadAlimento);
                aumentarSalud(cantidadAlimento);
                if (queTramoEdad() == INFANCIA) {
                    hambre -= cantidadAlimento;
                } else if (queTramoEdad() == ADULTA) {
                    hambre -= cantidadAlimento + 5;
                } else {
                    hambre -= cantidadAlimento - 3;
                }
            } else {
                hambre = 0;
                salud -= (cantidadAlimento - hambre);
                estasEnfermo();

            }
        }


    }

    public void curar(float cantidadMedicina) {
        if (!estasMuerto()) {
            recibeMedicinas();
            if (salud + cantidadMedicina <= 100) {
                aumentarSalud(cantidadMedicina);
            } else {
                salud = 100;
            }
        }
    }

    private void ganarPeso(float cantidad) {
        if (!estasEnfermo()) {
            peso += cantidad;
        }

    }

    private void aumentarEnergia(float cantidad) {
        if (energia + cantidad <= 100) {
            energia += cantidad;
        } else {
            energia = 100;
        }
    }

    private void aumentarSalud(float cantidad) {
        if (!estasEnfermo()) {
            sumarSaludad(cantidad);
        }
    }

    public boolean jugar(float cantidadDiversion) {
        if (estasDormido() || estasEnfermo() || !estasFeliz()) {
            return false;
        } else {
            restarEnergia(cantidadDiversion);
            sumarHambre(cantidadDiversion);
            sumarFelicidad(cantidadDiversion);
            return true;
        }
    }

    private void restarEnergia(float cantidad) {
        if (energia - cantidad > 0) {
            energia -= cantidad;
        } else {
            energia = 0;
        }
    }

    private void sumarHambre(float cantidad) {
        if (hambre + cantidad <= 100) {
            hambre += cantidad;
        } else {
            hambre = 100;
        }
    }

    private void sumarFelicidad(float cantidad) {
        if (felicidad + cantidad <= 100) {
            felicidad += cantidad;
        } else {
            felicidad = 100;
        }
    }

    private void sumarSaludad(float cantidad) {
        if (salud + cantidad <= 100) {
            salud += cantidad;
        } else {
            salud = 100;
        }
    }

    private boolean pierdePeso(int edad) {
        boolean retorno;
        if (estasEnfermo()) {
            peso--;
            retorno = true;
        } else {
            retorno = false;
            if (edad % 2 == 0) {
                peso--;
            }
        }
        return retorno;
    }

    private void recibeMedicinas() {
        //Si una mascota sana recibe medicinas enferma
        if (!estasEnfermo()) {
            if (enfermedad < 70) { //Para que le baje la enfermedad cuando tiene +70
                enfermedad = 70;
            }
            contador++;
            estasEnfermo();
            if (contador > 1) {
                if (enfermedad + 5 * contador < 100) {
                    enfermedad += 5 * contador;
                } else {
                    enfermedad = 100;
                }
            }


        }
    }

    private void ataque() {  //Implementación
        int random = (int) (Math.random() * (60 - 20) + 20);
        if (salud - random > 0) {
            salud -= random;
        } else {
            salud = 0;
            estasMuerto();
        }

    }

    public void probarSuerte(float vida) {  //Implementación
        if (!estasMuerto()) {
            int suerte = (int) (Math.random() * (10 - 1) + 1);
            if (suerte % 2 == 0) {
                if (salud + vida < 100) {
                    salud += vida;
                } else {
                    salud = 100;
                }
            } else if (suerte % 2 != 0 && suerte != 3) {
                if (salud - vida > 0) {
                    salud -= vida;
                } else {
                    salud = 0;
                    estasMuerto();
                }
            } else {
                salud = 100;
            }
        }
    }
}
