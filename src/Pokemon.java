import java.util.Random;

public class Pokemon {

    String nome;
    String tipo;
    int vida;
    int ataque;
    int pocoes = 2;

    public Pokemon(String nome, String tipo, int vida, int ataque) {
        this.nome = nome;
        this.tipo = tipo;
        this.vida = vida;
        this.ataque = ataque;
    }

    public void atacar(Pokemon inimigo) {
        Random random = new Random();
        int dano = ataque;

        // 20% chance de crítico
        if (random.nextInt(100) < 20) {
            dano *= 2;
            System.out.println("💥 ATAQUE CRÍTICO!");
        }

        // Sistema de vantagem de tipo
        if (vantagemTipo(this.tipo, inimigo.tipo)) {
            dano += 10;
            System.out.println("🔥 Super efetivo!");
        }

        inimigo.vida -= dano;
        System.out.println(nome + " causou " + dano + " de dano!");
    }

    public void usarPocao() {
        if (pocoes > 0) {
            vida += 20;
            pocoes--;
            System.out.println(nome + " usou poção e recuperou 20 de vida!");
        } else {
            System.out.println("Sem poções!");
        }
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void mostrarStatus() {
        System.out.println(nome + " (" + tipo + ") - Vida: " + vida + " | Poções: " + pocoes);
    }

    private boolean vantagemTipo(String atacante, String defensor) {
        return (atacante.equals("Fogo") && defensor.equals("Planta")) ||
                (atacante.equals("Planta") && defensor.equals("Água")) ||
                (atacante.equals("Água") && defensor.equals("Fogo"));
    }
}