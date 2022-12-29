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

    //Constantes
    private static final int INFANCIA = 0;
    private static final int ADULTA = 1;
    private static final int VEJEZ = 2;


    public RatoncitoFiuFiu(String nombre, int peso, byte hambre, byte suciedad, byte salud, byte energia) {
        // Un objeto mascota.RatoncitoFiuFiu debería informar cuando nace...
        this.nombre = nombre;
        this.peso = peso;
        this.hambre = hambre;
        this.suciedad = suciedad;
        this.salud = salud;
        this.energia = energia;
    }


    public String estadisticas() {
        return "Hambre " + hambre + ", " + "Salud " + salud + ", " + "Suciedad " + suciedad + ", " + "Peso " + peso + ", " + "Energia " + energia;
    }

    public void limpiar(float esfuerzoHigienico) {
        if (suciedad > esfuerzoHigienico) {
            suciedad -= esfuerzoHigienico;
        }
        estadisticas();
    }

    public int queTramoEdad() {
        int valor = 0;
        if (valor == INFANCIA) {
            return 0;
        } else if (valor == ADULTA) {
            return 1;
        } else {
            return 2;
        }

    }

    public boolean estasDormido() {
        boolean trueOFalse = true;
        if (energia <= 50) {
            trueOFalse = true;
        } else if (energia >= 75) {
            trueOFalse = false;
        }
        return trueOFalse;

    }

    public boolean estasEnfermo() {
        if (salud < 50 && salud > 10) {
            return true;
        } else {
            return false;
        }
    }

    public boolean estasSucio() {
        if (suciedad < 5) {
            return true;
        } else {
            return false;
        }
    }

    public boolean estasMuerto() {
        if (salud <= 10) {
            return true;
        }
        return false;
    }

    public void envejecer(int segundos) {
        this.edad += segundos;
    }

    public boolean tienesQuejas() {
        if (hambre < 20 || salud < 25 || suciedad < 15) {
            return true;
        } else {
            return false;
        }
    }

    public void alimentar(float cantidadAlimento) {
        if (hambre > cantidadAlimento) {
            hambre -= cantidadAlimento;
            ganarPeso(cantidadAlimento);
        } else {
            hambre = 0;
            estasEnfermo();
        }
        estadisticas();

    }

    public void curar(float cantidadMedicina) {
        if (salud + cantidadMedicina <= 100) {
            aumentarSalud(cantidadMedicina);
        }
        estadisticas();
    }

    private void ganarPeso(float cantidad) {
        if (estasEnfermo() == false) {
            if (peso + cantidad <= 100) {
                peso += cantidad;
            } else {
                peso = 100;
            }

        }

    }

    private void aumentarEnergia(float cantidad) {
        if (energia + cantidad <= 100) {
            energia += cantidad;
        }
    }

    private void aumentarSalud(float cantidad) {
        if (hambre > 1) {
            salud += cantidad;
        } else {

        }
    }


}