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

    //Constantes
    private static final int INFANCIA = 0;
    private static final int ADULTA = 1;
    private static final int VEJEZ = 2;
    private static final int APATICO = 0;
    private static final int CANSADO = 30;
    private static final int NORMAL = 50;
    private static final int ACTIVO = 70;
    private static final int EXTREMADAMENTEACTIVO = 100;


    public RatoncitoFiuFiu(String nombre, int peso, byte hambre, byte suciedad, byte salud, byte energia, byte felicidad) {
        // Un objeto mascota.RatoncitoFiuFiu debería informar cuando nace...
        this.nombre = nombre;
        this.peso = peso;
        this.hambre = hambre;
        this.suciedad = suciedad;
        this.salud = salud;
        this.energia = energia;
        this.felicidad = felicidad;
    }


    public String estadisticas() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre).append('\n');
        sb.append("Edad: ").append(edad).append('\n');
        sb.append("Peso: ").append(peso).append('\n');
        sb.append("Hambre: ").append(hambre).append('\n');
        sb.append("Suciedad: ").append(suciedad).append('\n');
        sb.append("Salud: ").append(salud).append('\n');
        ;
        sb.append("Energia: ").append(energia).append('\n');
        return sb.toString();
    }

    public void limpiar(float esfuerzoHigienico) {
        if (suciedad > esfuerzoHigienico) {
            suciedad -= esfuerzoHigienico;
        } else {
            suciedad = 0;
        }

    }

    public int queTramoEdad() {

        if (edad <= 500) {
            return INFANCIA;
        } else if (edad >= 500 && energia < 1000) {
            return ADULTA;
        } else {
            return VEJEZ;
        }

    }

    public boolean estasDormido() {
        boolean dormido;
        if (energia >= APATICO && energia < CANSADO || salud == 0) {
            dormido = true;
        } else if (energia >= CANSADO && energia < NORMAL) {
            dormido = true;
        } else if (energia >= NORMAL && energia < ACTIVO) {
            dormido = false;
        } else {
            dormido = false;
        }
        return dormido;
    }

    public boolean estasEnfermo() {
        if (salud < 40 && salud >= 1 || hambre == 0) {
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
        if (salud == 0 || energia == 0) {
            return true;
        }
        return false;
    }

    public void envejecer(int segundos) {
        this.edad += segundos;

        if (hambre < 100) {
            hambre++;
        }
        if (suciedad < 100) {
            suciedad++;
        }
        if (salud > 0) {
            salud--;
        }
        if (energia > 0) {
            energia--;
        }
        if (peso > 0) {
            pierdePeso(edad);
        }


    }

    public boolean tienesQuejas() {
        if (hambre > 75) {
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
        if (hambre > cantidadAlimento) {
            hambre -= cantidadAlimento;
            ganarPeso(cantidadAlimento);
            aumentarEnergia(cantidadAlimento);
            aumentarSalud(cantidadAlimento);
        } else {
            hambre = 0;
            salud -= (cantidadAlimento - hambre);
            estasEnfermo();

        }


    }

    public void curar(float cantidadMedicina) {
        if (recibeMedicinas()) {
            estasEnfermo();
        }
        if (salud + cantidadMedicina <= 100) {
            aumentarSalud(cantidadMedicina);
        } else {
            salud = 100;
        }

    }

    private void ganarPeso(float cantidad) {
        if (!estasEnfermo()) {
            peso += cantidad;
        }

    }

    private void aumentarEnergia(float cantidad) {
        sumarEnergia(cantidad);
    }

    private void aumentarSalud(float cantidad) {
        if (!estasEnfermo()) {
            sumarSaludad(cantidad);
        }
    }

    public boolean jugar(float cantidad) {
        if (estasDormido() || estasEnfermo() || !estasFeliz()) {
            return false;
        } else {
            sumarEnergia(cantidad);
            sumarHambre(cantidad);
            sumarFelicidad(cantidad);
            return true;
        }
    }

    private void sumarEnergia(float cantidad) {
        if (energia + cantidad <= 100) {
            energia += cantidad;
        } else {
            energia = 100;
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

    private boolean recibeMedicinas() {

        int contador = 0;
        if (!estasEnfermo()) {
            contador++;
            if (contador > 1) {
                salud -= 5 * contador;
            }
            return true;
        } else {
            return false;
        }

    }
}
