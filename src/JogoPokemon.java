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

        Pokemon inimigo = inimigos[random.nextInt(3)];

        System.out.println("\n⚔️ Seu oponente é " + inimigo.nome + "!\n");

        while (jogador.estaVivo() && inimigo.estaVivo()) {

            jogador.mostrarStatus();
            inimigo.mostrarStatus();

            System.out.println("\nEscolha uma ação:");
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
            System.out.println("\n🏆 VOCÊ VENCEU!");
        } else {
            System.out.println("\n💀 VOCÊ PERDEU!");
        }

        scanner.close();
    }
}