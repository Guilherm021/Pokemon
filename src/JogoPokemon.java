import java.util.Random;
import java.util.Scanner;

public class JogoPokemon {

    public static void aplicarBonusFinal(Pokemon jogador, Scanner scanner) {
        System.out.println("\n====================================");
        System.out.println("🎁 BÔNUS FINAL ANTES DO CHEFÃO");
        System.out.println("====================================");
        System.out.println("Escolha um bônus:");
        System.out.println("1 - Cura Total");
        System.out.println("2 - +10 de Ataque");
        System.out.println("3 - +2 Poções");

        int bonus = scanner.nextInt();

        switch (bonus) {
            case 1:
                jogador.vida = jogador.vidaMaxima;
                System.out.println("💖 Seu Pokémon foi curado totalmente!");
                break;
            case 2:
                jogador.ataque += 10;
                for (int i = 0; i < jogador.numeroAtaques; i++) {
                    jogador.danosAtaques[i] += 10;
                }
                System.out.println("⚔️ Seu ataque aumentou em +10!");
                break;
            case 3:
                jogador.pocoes += 2;
                System.out.println("🧪 Você ganhou +2 poções!");
                break;
            default:
                System.out.println("❌ Opção inválida! Nenhum bônus recebido.");
        }
    }

    public static Pokemon criarInimigoPorFase(int fase, String nomeBase) {
        switch (nomeBase) {
            case "Charmander":
                if (fase == 1) {
                    return new Pokemon("Charmander", "Fogo", 100, 20);
                } else if (fase == 2) {
                    return new Pokemon("Charmeleon", "Fogo", 120, 25);
                } else {
                    return new Pokemon("Charizard", "Fogo", 140, 30);
                }

            case "Squirtle":
                if (fase == 1) {
                    return new Pokemon("Squirtle", "Água", 110, 18);
                } else if (fase == 2) {
                    return new Pokemon("Wartortle", "Água", 130, 23);
                } else {
                    return new Pokemon("Blastoise", "Água", 150, 28);
                }

            default:
                if (fase == 1) {
                    return new Pokemon("Bulbasaur", "Planta", 105, 19);
                } else if (fase == 2) {
                    return new Pokemon("Ivysaur", "Planta", 125, 24);
                } else {
                    return new Pokemon("Venusaur", "Planta", 145, 29);
                }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int recordeVitorias = 0;
        int recordeNivel = 0;

        boolean jogarNovamente = true;

        while (jogarNovamente) {

            System.out.println("====================================");
            System.out.println("🔥 BEM-VINDO AO MUNDO POKÉMON 🔥");
            System.out.println("====================================");
            System.out.println("Escolha seu Pokémon:");
            System.out.println("1 - Charmander (Fogo)");
            System.out.println("2 - Squirtle (Água)");
            System.out.println("3 - Bulbasaur (Planta)");

            int escolha = scanner.nextInt();
            Pokemon jogador;
            String pokemonInicialJogador;

            switch (escolha) {
                case 1:
                    jogador = new Pokemon("Charmander", "Fogo", 100, 20);
                    pokemonInicialJogador = "Charmander";
                    break;
                case 2:
                    jogador = new Pokemon("Squirtle", "Água", 110, 18);
                    pokemonInicialJogador = "Squirtle";
                    break;
                default:
                    jogador = new Pokemon("Bulbasaur", "Planta", 105, 19);
                    pokemonInicialJogador = "Bulbasaur";
                    break;
            }

            String[] bases = {"Charmander", "Squirtle", "Bulbasaur"};
            String[] inimigosBase = new String[2];
            int indice = 0;

            for (String base : bases) {
                if (!base.equals(pokemonInicialJogador)) {
                    inimigosBase[indice] = base;
                    indice++;
                }
            }

            // Embaralha os dois inimigos possíveis
            if (random.nextBoolean()) {
                String temp = inimigosBase[0];
                inimigosBase[0] = inimigosBase[1];
                inimigosBase[1] = temp;
            }

            int vitorias = 0;
            boolean chefeDerrotado = false;

            // 3 batalhas antes do boss
            for (int fase = 1; fase <= 3 && jogador.estaVivo(); fase++) {

                String nomeBaseInimigo;

                if (fase == 1) {
                    nomeBaseInimigo = inimigosBase[0];
                } else if (fase == 2) {
                    nomeBaseInimigo = inimigosBase[1];
                } else {
                    // terceira batalha usa um dos dois de novo, mas agora na evolução final
                    nomeBaseInimigo = inimigosBase[random.nextInt(2)];
                }

                Pokemon inimigo = criarInimigoPorFase(fase, nomeBaseInimigo);

                System.out.println("\n====================================");
                System.out.println("⚔️ BATALHA " + fase);
                System.out.println("====================================");
                System.out.println("Oponente: " + inimigo.nome + " (" + inimigo.tipo + ")");

                while (jogador.estaVivo() && inimigo.estaVivo()) {

                    System.out.println("\n=== SEU POKÉMON ===");
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
                        System.out.println("❌ Opção inválida!");
                    }

                    if (inimigo.estaVivo()) {
                        int ataqueInimigo = random.nextInt(inimigo.numeroAtaques);
                        inimigo.atacar(jogador, ataqueInimigo);
                    }
                }

                if (jogador.estaVivo()) {
                    vitorias++;
                    System.out.println("\n🏆 Você derrotou " + inimigo.nome + "!");
                    System.out.println("🏆 Vitórias consecutivas: " + vitorias);

                    jogador.ganharXp(50);
                    jogador.ganharPokeCoins(50);

                    String nomeAntes = jogador.nome;
                    jogador.evoluir();

                    if (!nomeAntes.equals(jogador.nome)) {
                        System.out.println("🌟 " + nomeAntes + " evoluiu para " + jogador.nome + "!");
                    } else {
                        System.out.println("🌟 Seu Pokémon ficou mais forte!");
                    }
                }
            }

            if (jogador.estaVivo()) {
                aplicarBonusFinal(jogador, scanner);

                Pokemon chefeFinal = new Pokemon("Mewtwo", "Psíquico", 180, 30);

                System.out.println("\n====================================");
                System.out.println("👑 CHEFÃO FINAL APARECEU: " + chefeFinal.nome + "!");
                System.out.println("====================================");

                while (jogador.estaVivo() && chefeFinal.estaVivo()) {

                    System.out.println("\n=== SEU POKÉMON ===");
                    jogador.mostrarStatus();

                    System.out.println("\n=== CHEFÃO FINAL ===");
                    chefeFinal.mostrarStatusInimigo();

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
                        jogador.atacar(chefeFinal, ataqueEscolhido);

                    } else if (acao == 2) {
                        jogador.usarPocao();
                    } else {
                        System.out.println("❌ Opção inválida!");
                    }

                    if (chefeFinal.estaVivo()) {
                        int ataqueInimigo = random.nextInt(chefeFinal.numeroAtaques);
                        chefeFinal.atacar(jogador, ataqueInimigo);
                    }
                }

                if (jogador.estaVivo()) {
                    chefeDerrotado = true;
                    vitorias++;
                    System.out.println("\n🏆 PARABÉNS! VOCÊ DERROTOU O CHEFÃO FINAL E VENCEU O JOGO!");
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