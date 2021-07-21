package Classes;

public class Utilizador {
    private float altura;
    private float peso;

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

    public float[] diet() {
        float[] macros = new float[4];

        macros[0] = peso * 44; //Calorias

        macros[1] = macros[0] * (0.25f / 9); //Lipidos
        macros[2] = macros[0] * (0.5f / 4); //Carbohidratos
        macros[3] = macros[0] * (0.25f / 4); //Proteinas

        return macros;
    }
}