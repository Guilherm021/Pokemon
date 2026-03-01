import java.util.Scanner;
import java.util.Random;

public class JogoPokemon {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("🔥 BEM-VINDO AO MUNDO POKÉMON 🔥");
        System.out.println("Escolha seu Pokémon:");
        System.out.println("1 - Charmander (Fogo)");
        System.out.println("2 - Squirtle (Água)");
        System.out.println("3 - Bulbasaur (Planta)");

        int escolha = scanner.nextInt();
        Pokemon jogador;

        switch (escolha) {
            case 1:
                jogador = new Pokemon("Charmander", "Fogo", 100, 20);
                break;
            case 2:
                jogador = new Pokemon("Squirtle", "Água", 110, 18);
                break;
            default:
                jogador = new Pokemon("Bulbasaur", "Planta", 105, 19);
        }

        Pokemon[] inimigos = {
                new Pokemon("Charmander", "Fogo", 100, 20),
                new Pokemon("Squirtle", "Água", 110, 18),
                new Pokemon("Bulbasaur", "Planta", 105, 19)
        };

        int vitorias = 0;

        while (jogador.estaVivo()) {
            Pokemon inimigo = inimigos[random.nextInt(inimigos.length)];
            System.out.println("\n⚔️ Novo oponente: " + inimigo.nome + "!\n");

            while (jogador.estaVivo() && inimigo.estaVivo()) {
                jogador.mostrarStatus();
                inimigo.mostrarStatus();

                System.out.println("\nEscolha:");
                System.out.println("1 - Atacar");
                System.out.println("2 - Usar Poção");

                int acao = scanner.nextInt();

                if (acao == 1) {
                    jogador.atacar(inimigo);
                } else if (acao == 2) {
                    jogador.usarPocao();
                }

                if (inimigo.estaVivo()) {
                    inimigo.atacar(jogador);
                }
            }

            if (jogador.estaVivo()) {
                vitorias++;
                System.out.println("🏆 Vitórias consecutivas: " + vitorias);
            }
        }

        System.out.println("💀 Game Over!");
        scanner.close();
    }
}