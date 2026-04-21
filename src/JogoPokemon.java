import java.util.Random;
import java.util.Scanner;

public class JogoPokemon {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int recordeVitorias = 0;
        int recordeNivel = 0;

        boolean jogarNovamente = true;

        while (jogarNovamente) {

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
                    break;
            }

            Pokemon[] inimigos = {
                    new Pokemon("Charmander", "Fogo", 100, 20),
                    new Pokemon("Squirtle", "Água", 110, 18),
                    new Pokemon("Bulbasaur", "Planta", 105, 19)
            };

            int vitorias = 0;
            boolean chefeDerrotado = false;

            while (jogador.estaVivo() && !chefeDerrotado) {

                Pokemon inimigo;

                if (vitorias >= 5) {
                    inimigo = new Pokemon("Mewtwo", "Psíquico", 180, 30);
                    System.out.println("\n👑 CHEFÃO FINAL APARECEU: " + inimigo.nome + "!\n");
                } else {
                    inimigo = inimigos[random.nextInt(inimigos.length)];
                    System.out.println("\n⚔️ Novo oponente: " + inimigo.nome + "!\n");
                }

                while (jogador.estaVivo() && inimigo.estaVivo()) {

                    System.out.println("=== SEU POKÉMON ===");
                    jogador.mostrarStatus();

                    System.out.println("\n=== OPONENTE ===");
                    inimigo.mostrarStatusInimigo();

                    System.out.println("\nEscolha:");
                    System.out.println("1 - Atacar");
                    System.out.println("2 - Usar Poção");

                    int acao = scanner.nextInt();

                    if (acao == 1) {
                        System.out.println("\nEscolha seu ataque:");
                        for (int i = 0; i < jogador.numeroAtaques; i++) {
                            System.out.println((i + 1) + " - " + jogador.ataques[i]);
                        }

                        int ataqueEscolhido = scanner.nextInt() - 1;
                        jogador.atacar(inimigo, ataqueEscolhido);

                    } else if (acao == 2) {
                        jogador.usarPocao();
                    } else {
                        System.out.println("Opção inválida!");
                    }

                    if (inimigo.estaVivo()) {
                        int ataqueInimigo = random.nextInt(inimigo.numeroAtaques);
                        inimigo.atacar(jogador, ataqueInimigo);
                    }
                }

                if (jogador.estaVivo()) {
                    if (inimigo.nome.equals("Mewtwo")) {
                        chefeDerrotado = true;
                        System.out.println("\n🏆 PARABÉNS! VOCÊ DERROTOU O CHEFÃO FINAL E VENCEU O JOGO!");
                    } else {
                        vitorias++;
                        System.out.println("🏆 Vitórias consecutivas: " + vitorias);

                        jogador.ganharXp(50);

                        if (vitorias % 2 == 0) {
                            jogador.evoluir();
                        }
                    }
                }
            }

            if (!jogador.estaVivo()) {
                System.out.println("💀 Game Over!");
            }

            if (vitorias > recordeVitorias) {
                recordeVitorias = vitorias;
            }

            if (jogador.nivel > recordeNivel) {
                recordeNivel = jogador.nivel;
            }

            System.out.println("\n=== RANKING DA PARTIDA ===");
            System.out.println("🏆 Vitórias nesta partida: " + vitorias);
            System.out.println("⭐ Nível alcançado nesta partida: " + jogador.nivel);

            System.out.println("\n=== RECORDES ===");
            System.out.println("🏅 Recorde de vitórias: " + recordeVitorias);
            System.out.println("🌟 Maior nível alcançado: " + recordeNivel);

            System.out.println("\nDeseja jogar novamente?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");

            int resposta = scanner.nextInt();

            if (resposta != 1) {
                jogarNovamente = false;
                System.out.println("👋 Obrigado por jogar!");
            }
        }

        scanner.close();
    }
}