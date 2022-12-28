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
        return "Hambre : " + hambre + " , " + " Salud " + salud + " ," + "Suciedad " + suciedad;
    }

    public void limpiar(float esfuerzoHigienico) {
        if (suciedad > esfuerzoHigienico) {
            suciedad -= esfuerzoHigienico;
        }
    }

    public int queTramoEdad() {
        return 0;
    }

    public boolean estasDormido() {
        return false;
    }

    public boolean estasEnfermo() {
        if (salud<50 && salud > 10){
            return true;
        }else {
            return false;
        }
    }

    public boolean estasSucio() {
        if (suciedad<5){
            return true;
        }else {
            return false;
        }
    }

    public boolean estasMuerto() {
        if (salud<=10){
            return true;
        }
        return false;
    }

    public void envejecer(int segundos) {
        this.edad += segundos;
    }

    public boolean tienesQuejas() {
        return false;
    }

    public void alimentar(float cantidadAlimento) {
        if (hambre > cantidadAlimento) {
            hambre -= cantidadAlimento;
        }

    }

    public void curar(float cantidadMedicina) {
        if (salud + cantidadMedicina < 100) {
            salud += cantidadMedicina;
        }
    }
}