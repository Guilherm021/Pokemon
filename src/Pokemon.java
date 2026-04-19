import java.util.Random;

public class Pokemon {

    String nome;
    String tipo;
    int vida;
    int vidaMaxima;
    int ataque;
    int pocoes = 2;

    String[] ataques = new String[4];
    int[] danosAtaques = new int[4];
    int numeroAtaques = 2;

    int nivel = 1;
    int xp = 0;
    int xpParaProximoNivel = 100;

    Random random = new Random();

    public Pokemon(String nome, String tipo, int vida, int ataque) {
        this.nome = nome;
        this.tipo = tipo;
        this.vida = vida;
        this.vidaMaxima = vida;
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

        // Ataque crítico (20% de chance)
        if (random.nextInt(100) < 20) {
            danoFinal *= 2;
            System.out.println("💥 Ataque crítico!");
        }

        // Vantagem de tipo
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

            if (vida > vidaMaxima) {
                vida = vidaMaxima;
            }

            pocoes--;
            System.out.println(nome + " usou poção! Vida +20 | Poções restantes: " + pocoes);
        } else {
            System.out.println(nome + " não tem poções restantes!");
        }
    }

    public void evoluir() {
        if (numeroAtaques < 4) {

            // Troca de nome
            if (nome.equals("Charmander")) {
                nome = "Charmeleon";
            } else if (nome.equals("Charmeleon")) {
                nome = "Charizard";
            } else if (nome.equals("Squirtle")) {
                nome = "Wartortle";
            } else if (nome.equals("Wartortle")) {
                nome = "Blastoise";
            } else if (nome.equals("Bulbasaur")) {
                nome = "Ivysaur";
            } else if (nome.equals("Ivysaur")) {
                nome = "Venusaur";
            }

            // Novos ataques
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
            ataque += 5;
            vidaMaxima += 20;
            vida = vidaMaxima;

            System.out.println("✨ Seu Pokémon evoluiu para " + nome + "!");
            System.out.println("❤️ Vida restaurada completamente!");
        } else {
            System.out.println(nome + " já possui o máximo de ataques!");
        }
    }

    public void ganharXp(int quantidade) {
        xp += quantidade;
        System.out.println(nome + " ganhou " + quantidade + " XP!");

        while (xp >= xpParaProximoNivel) {
            xp -= xpParaProximoNivel;
            subirNivel();
        }
    }

    public void subirNivel() {
        nivel++;
        vidaMaxima += 15;
        vida = vidaMaxima;
        ataque += 3;

        for (int i = 0; i < numeroAtaques; i++) {
            danosAtaques[i] += 3;
        }

        xpParaProximoNivel += 50;

        System.out.println("⬆️ " + nome + " subiu para o nível " + nivel + "!");
        System.out.println("Vida e ataque aumentaram!");
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public String gerarBarraVida() {
        int tamanhoBarra = 10;
        int blocosCheios = (vida * tamanhoBarra) / vidaMaxima;

        String barra = "[";

        for (int i = 0; i < tamanhoBarra; i++) {
            if (i < blocosCheios) {
                barra += "█";
            } else {
                barra += "░";
            }
        }

        barra += "]";
        return barra;
    }

    public void mostrarStatus() {
        System.out.println(nome + " (" + tipo + ") - Vida: " + vida + "/" + vidaMaxima + " " + gerarBarraVida() +
                " | Poções: " + pocoes +
                " | Nível: " + nivel +
                " | XP: " + xp + "/" + xpParaProximoNivel);

        System.out.println("Ataques disponíveis:");
        for (int i = 0; i < numeroAtaques; i++) {
            System.out.println((i + 1) + " - " + ataques[i] + " | Dano: " + danosAtaques[i]);
        }
    }

    public void mostrarStatusInimigo() {
        System.out.println(nome + " (" + tipo + ") - Vida: " + vida + "/" + vidaMaxima + " " + gerarBarraVida());
    }
}