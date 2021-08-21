package Classes;

public class Utilizador {
    private float altura;
    private float peso;

    private float lipPerc=0.25f;
    private float carbPerc=0.50f;
    private float protPerc=0.25f;

    public Utilizador() {
        altura=0;
        peso=0;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float IMC() {
        return peso / (altura * altura);
    }

    public void setLipPerc(float lipPerc) {
        this.lipPerc = lipPerc;
    }

    public void setCarbPerc(float carbPerc) {
        this.carbPerc = carbPerc;
    }

    public void setProtPerc(float protPerc) {
        this.protPerc = protPerc;
    }

    public float[] diet() {
        float[] macros = new float[4];

        macros[0] = peso * 44; //Calorias

        macros[1] = macros[0] * (lipPerc / 9); //Lipidos
        macros[2] = macros[0] * (carbPerc / 4); //Carbohidratos
        macros[3] = macros[0] * (protPerc / 4); //Proteinas

        return macros;
    }
}