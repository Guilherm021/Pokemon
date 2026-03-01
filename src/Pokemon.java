public class Pokemon {

    String nome;
    String tipo;
    int vida;
    int ataque;
    int pocoes = 2; // cada Pokémon começa com 2 poções

    public Pokemon(String nome, String tipo, int vida, int ataque) {
        this.nome = nome;
        this.tipo = tipo;
        this.vida = vida;
        this.ataque = ataque;
    }

    public void atacar(Pokemon inimigo) {
        int danoFinal = this.ataque;

        // vantagem de tipo
        if ((this.tipo.equals("Fogo") && inimigo.tipo.equals("Planta")) ||
                (this.tipo.equals("Planta") && inimigo.tipo.equals("Água")) ||
                (this.tipo.equals("Água") && inimigo.tipo.equals("Fogo"))) {
            danoFinal += 5;
            System.out.println("🔥 Ataque super efetivo!");
        }

        inimigo.vida -= danoFinal;
        if (inimigo.vida < 0) inimigo.vida = 0;

        System.out.println(this.nome + " causou " + danoFinal + " de dano em " + inimigo.nome + "!");
    }

    public void usarPocao() {
        if (pocoes > 0) {
            vida += 20;
            pocoes--;
            System.out.println(nome + " usou poção! Vida +20 | Poções restantes: " + pocoes);
        } else {
            System.out.println(nome + " não tem poções restantes!");
        }
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void mostrarStatus() {
        System.out.println(nome + " (" + tipo + ") - Vida: " + vida + " | Poções: " + pocoes);
    }
}