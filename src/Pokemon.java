public class Pokemon {

    String nome;
    String tipo;
    int vida;
    int ataque;
    int pocoes = 2;

    String[] ataques = new String[4];
    int[] danosAtaques = new int[4];
    int numeroAtaques = 2;

    public Pokemon(String nome, String tipo, int vida, int ataque) {
        this.nome = nome;
        this.tipo = tipo;
        this.vida = vida;
        this.ataque = ataque;

        ataques[0] = "Ataque Básico";
        danosAtaques[0] = ataque;

        if (tipo.equals("Fogo")) {
            ataques[1] = "Brasa";
            danosAtaques[1] = ataque + 5;
        } else if (tipo.equals("Água")) {
            ataques[1] = "Jato d'Água";
            danosAtaques[1] = ataque + 5;
        } else if (tipo.equals("Planta")) {
            ataques[1] = "Folha Navalha";
            danosAtaques[1] = ataque + 5;
        }
    }

    public void atacar(Pokemon inimigo, int escolhaAtaque) {
        if (escolhaAtaque < 0 || escolhaAtaque >= numeroAtaques) {
            System.out.println("Ataque inválido!");
            return;
        }

        int danoFinal = danosAtaques[escolhaAtaque];

        if ((this.tipo.equals("Fogo") && inimigo.tipo.equals("Planta")) ||
                (this.tipo.equals("Planta") && inimigo.tipo.equals("Água")) ||
                (this.tipo.equals("Água") && inimigo.tipo.equals("Fogo"))) {
            danoFinal += 5;
            System.out.println("🔥 Ataque super efetivo!");
        }

        inimigo.vida -= danoFinal;

        if (inimigo.vida < 0) {
            inimigo.vida = 0;
        }

        System.out.println(this.nome + " usou " + ataques[escolhaAtaque] +
                " e causou " + danoFinal + " de dano em " + inimigo.nome + "!");
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

    public void evoluir() {
        if (numeroAtaques < 4) {
            if (tipo.equals("Fogo")) {
                if (numeroAtaques == 2) {
                    ataques[2] = "Lança-Chamas";
                    danosAtaques[2] = ataque + 10;
                } else if (numeroAtaques == 3) {
                    ataques[3] = "Explosão de Fogo";
                    danosAtaques[3] = ataque + 15;
                }
            } else if (tipo.equals("Água")) {
                if (numeroAtaques == 2) {
                    ataques[2] = "Hidrobomba";
                    danosAtaques[2] = ataque + 10;
                } else if (numeroAtaques == 3) {
                    ataques[3] = "Onda d'Água";
                    danosAtaques[3] = ataque + 15;
                }
            } else if (tipo.equals("Planta")) {
                if (numeroAtaques == 2) {
                    ataques[2] = "Chicote de Vinha";
                    danosAtaques[2] = ataque + 10;
                } else if (numeroAtaques == 3) {
                    ataques[3] = "Raio Solar";
                    danosAtaques[3] = ataque + 15;
                }
            }

            numeroAtaques++;
            vida += 20;
            ataque += 5;

            System.out.println("✨ " + nome + " evoluiu e aprendeu um novo ataque!");
        } else {
            System.out.println(nome + " já possui o máximo de ataques!");
        }
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void mostrarStatus() {
        System.out.println(nome + " (" + tipo + ") - Vida: " + vida + " | Poções: " + pocoes);

        System.out.println("Ataques disponíveis:");
        for (int i = 0; i < numeroAtaques; i++) {
            System.out.println((i + 1) + " - " + ataques[i] + " | Dano: " + danosAtaques[i]);
        }
    }
}