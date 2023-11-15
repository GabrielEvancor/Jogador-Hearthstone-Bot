/*EXPLICACAO SOBRE O CODIGO

COMPORTAMENTO AGRESSIVO: Durante o comportamento agressivo adotado procurei potencializar da melhor forma possivel o ataque direcionado ao heroi
inimigo. Dessa forma, foquei em realizar ataques diretamente ao heroi inimigo sem realizar trocas de lacaios com o inimigo.
Com isso, a primeiro acao(1) tomada por mim foi direcionar os ataques de todos os lacaios ja presentes na mesa ao heroi inimigo, fazendo com que ele
levasse o maior dano possivel. 
Segundamente (2), minha estrategia foi descer todas as cartas magias alvo da minha mao direcionadas a esse heroi (ate a quantidade de mana no turno permitir), 
uma vez que essas cartas causam um dano imediato no alvo desejado. 
Posteriormente a isso (3), decidi descer meus lacaios de maior ataque na mesa(ate a quantidade de mana que restou do turno permitir), sendo esses os que eu adotei
como tendo 4 ou mais de pontos de ataque, pois com isso e possivel potencializar muito o dano para o proximo turno. 
Alem disso(4), se ainda restar mana disponivel, adotei a mesma estrategia do topico 3, porem com os lacaios na minha mao que classifiquei como de ataque medio
(pontos de ataque maior ou igual a 2 e menor do que 4).
Por ultima se restar mana para utilizar o poder heroico(>2 de mana), farei acontecer esse ataque.

COMPORTAMENTO CONTROLE: Com o comportamento controle pocurei manter um maior controle do campo acumulando lacaios vivos e conseguir realizar trocas favoraveis.
Dividi as trocas favoraveis de lacaios em tres situacoes. Basicamete percorremos o ArrayList dos meus lacaios aliados juntamente a ArrayList de lacaios do inimigo
(verificando sempre se e um lacaio valido de ser atacado, ou seja, vida maior que zero).
A primeira situacao se da atraves do ataque de um lacaio meu em um lacaio inimigo, onde meu lacaio continua vivo  e o lacaio do inimigo morre. 
No segundo caso  ambos os lacaios morrem, porem o custo de mana do lacaio adversario e maior do que do meu lacaio, levando dessa forma uma troca favoravel devido
a vantagem ganha em mana. 
Na ultima, ambos os lacaios tambem morrem, porem o meu lacaio ja esta danificado e possui menos vida do que o do adversario.
Apos checar todas as possibiliades de trocas favoraveis, se nao houver nenhuma, fiz com que meu lacaios atacassem diretamente o heroi adversario.
Apos avaliarmos as situacoes dos lacaios, percorremos nossa mao para observarmos quais magias compensam serem colocadas na mesa. 
Primeira analise: Se tivermos uma magia do tipo area, devemos avaliar se existem dois ou mais lacaios inimigos vivos na mesa e se temos mana suficiente para tal jogada.
Se sim devemos executa-la.
Segunda analise: Continuando percorrendo nossa mao, se tivermos uma carta magia do tipo alvo devemos utiliza-la somente se a diferença entre o dano da magia e a vida atual do lacaio
(que deve ser diferente de 0, ou seja, esteja vivo) é pequena (no máximo 1),ou seja, não “desperdiça-se” dano de magias de alvo, e verificamos se temos mana suficiente 
para usa-la.
Terceira analise: Tambem, se tivermos mana suficiente e uma carta de buff em nossas maos, tomei a estrategia de aplica-la ao meu lacaio com maior vida, pois ele ira perdurar mais ainda durante
os turnos, e gerando mais dano tambem aos lacaios inimigos ou ao heroi.
Quarta analise: Por ultimo se restar mana, devemos investi-la em poder heroico para gerarmos dano direto ao heroi inimigo.

COMPORTAMENTO CURVA DE MANA: O objetivo principal desse comportamento e ser moderado entre o de controle e agressivo. Com isso ele procura utilizar toda a mana do turno, porem
balanceando essas escolhas.
Primeiro de tudo procuramos atacar com os nossos lacaios presentes na mesa. Com isso checamos se nossos lacaios conseguem matar o que chamei de lacaios uteis (vida atual > 3)
sem que meus lacaios morram. Se isso nao acontecer, direcionei o ataque diretamente ao heroi inimigo.
Posteriormente procurei baixar na mesa um lacaio que consuma a quantidade que temos de mana no round (sendo provavelmente o mais forte que podemos descer na mesa no momento),
porem se isso nao for possivel procurei balancear uma quantidade de mana para utilizar com um lacaio e o restante com magias. Cheguei atraves de testes que a melhor distribuicao
seria de 80% para lacaios e o restante para magias.
Nas magias, procuro primeiro as de area para dar equilibrio entre dano no heroi e nos lacaios inimigos. POsteriormente, para as de alvo devemos analisar se existe algum lacaio
inimigo que pode ser morto, e se tiver sera executado. Caso contrario procurei atacar o lacaio inimigo de menor vida.
Por ultimo, se restar mana, procurei utilizar o poder heroico no inimigo.

QUANDO USAR CADA COMPORTAMENTO?
Eu particularmente gosto da ideia de um jogo mais agressivo e menor recuado levando em conta as regras do jogo LaMana. Com isso foquei na utilizacao de principalmente
o comportamento agressivo e o comportamento curva de mana durante as partidas. Procurei avaliar em qual momento fazer a troca entre uma postura balanceada e a agressiva
e cheguei a conclusao atraves de testes de que o melhor resultado seria obtido levando em conta a analise da vida do heroi inimigo. Nesses testes cheguei a conclusao de 
que o comportamento curva de mana funcionaria melhor com o heroi inimigo tendo 20 ou mais de vida, ou seja, a etapa mais inicial da partida. Apos isso, o comportamento agressivo
ja seria excelente para lidar com o adversario ate o final da partida. O comportamento controlado, que na minha opiniao nao traz muitos beneficios para o codigo, eu procurei 
utiliza-lo somente em situacoes da partida em que o adversario conseguisse abrir uma vantagem de dois lacaios em relacao a mim, e com esse comportamento procurei retomar a 
igualdade ou superioridade de lacaios na mesa. 

*/

import java.util.ArrayList;
import java.util.Random;

/**
* Esta classe representa um Jogador aleatório (realiza jogadas de maneira aleatória) para o jogo LaMa (Lacaios & Magias).
* @see java.lang.Object
* @author Rafael Arakaki - MC302
*/
public class JogadorRA250320 extends Jogador {
	private ArrayList<CartaLacaio> lacaios;
	private ArrayList<CartaLacaio> lacaiosOponente;
	
	/**
	  * O método construtor do JogadorAleatorio.
	  * 
	  * @param maoInicial Contém a mão inicial do jogador. Deve conter o número de cartas correto dependendo se esta classe Jogador que está sendo construída é o primeiro ou o segundo jogador da partida. 
	  * @param primeiro   Informa se esta classe Jogador que está sendo construída é o primeiro jogador a iniciar nesta jogada (true) ou se é o segundo jogador (false).
	  */
	public JogadorRA250320(ArrayList<Carta> maoInicial, boolean primeiro){
		primeiroJogador = primeiro;
		mao = maoInicial;
		lacaios = new ArrayList<CartaLacaio>();
		lacaiosOponente = new ArrayList<CartaLacaio>();
		
		// Mensagens de depuração:
		System.out.println("*Classe JogadorRA250320* Sou o " + (primeiro?"primeiro":"segundo") + " jogador (classe: JogadorAleatorio)");
		System.out.println("Mao inicial:");
		for(int i = 0; i < mao.size(); i++)
			System.out.println("ID " + mao.get(i).getID() + ": " + mao.get(i));
	}
	
	/**
	  * Um método que processa o turno de cada jogador. Este método deve retornar as jogadas do Jogador decididas para o turno atual (ArrayList de Jogada).
	  * 
	  * @param mesa   O "estado do jogo" imediatamente antes do início do turno corrente. Este objeto de mesa contém todas as informações 'públicas' do jogo (lacaios vivos e suas vidas, vida dos heróis, etc).
	  * @param cartaComprada   A carta que o Jogador recebeu neste turno (comprada do Baralho). Obs: pode ser null se o Baralho estiver vazio ou o Jogador possuir mais de 10 cartas na mão.
	  * @param jogadasOponente   Um ArrayList de Jogada que foram os movimentos utilizados pelo oponente no último turno, em ordem.
	  * @return            um ArrayList com as Jogadas decididas
	  */
	public ArrayList<Jogada> processarTurno (Mesa mesa, Carta cartaComprada, ArrayList<Jogada> jogadasOponente){
		int minhaMana, minhaVida;
		if(cartaComprada != null)
			mao.add(cartaComprada);
		if(primeiroJogador){
			minhaMana = mesa.getManaJog1();
			minhaVida = mesa.getVidaHeroi1();
			lacaios = mesa.getLacaiosJog1();
			lacaiosOponente = mesa.getLacaiosJog2();
			//System.out.println("--------------------------------- Começo de turno pro jogador1");
		}
		else{
			minhaMana = mesa.getManaJog2();
			minhaVida = mesa.getVidaHeroi2();
			lacaios = mesa.getLacaiosJog2();
			lacaiosOponente = mesa.getLacaiosJog1();
			//System.out.println("--------------------------------- Começo de turno pro jogador2");
		}
		
		ArrayList<Jogada> minhasJogadas = new ArrayList<Jogada>();
		int vidaHeroiInimigo = primeiroJogador ? mesa.getVidaHeroi2(): mesa.getVidaHeroi1();
		ArrayList<CartaLacaio> lacaiosOponente2 = primeiroJogador ? mesa.getLacaiosJog2(): mesa.getLacaiosJog1();
		ArrayList<CartaLacaio> lacaios2 = primeiroJogador ? mesa.getLacaiosJog2(): mesa.getLacaiosJog1();
		/*
		for(int i = 0; i < mao.size(); i++){
			Carta card = mao.get(i);
			if(card instanceof CartaLacaio && card.getMana() <= minhaMana){
				Jogada lac = new Jogada(TipoJogada.LACAIO, card, null);
				minhasJogadas.add(lac);
				minhaMana -= card.getMana();
				System.out.println("Jogada: Decidi uma jogada de baixar o lacaio: "+ card);
				mao.remove(i);
				i--;
			}
		}
		*/
		if(vidaHeroiInimigo >= 20)
			return comportamentoCurvaDeMana(mesa, cartaComprada, jogadasOponente, minhasJogadas);
		if(lacaiosOponente2.size() - lacaios2.size() >= 2)
			return comportamentoControle(mesa, cartaComprada, jogadasOponente, minhasJogadas);
		if(vidaHeroiInimigo < 20)
			return comportamentoAgressivo(mesa, cartaComprada, jogadasOponente, minhasJogadas);
		
		return comportamentoCurvaDeMana(mesa, cartaComprada, jogadasOponente, minhasJogadas);
	}

	public ArrayList<Jogada> comportamentoAgressivo(Mesa mesa, Carta cartacomprada, ArrayList<Jogada> jogadasOponente, ArrayList<Jogada> minhasJogadas){
		ArrayList<CartaLacaio> meusLacaios = primeiroJogador ? mesa.getLacaiosJog1() : mesa.getLacaiosJog2();
		int numeroMeusLacaios = primeiroJogador ? mesa.getLacaiosJog1().size() : mesa.getLacaiosJog2().size();
		int minhaMana = primeiroJogador ? mesa.getManaJog1() : mesa.getManaJog2();
		
		//(1) Atacar o heroi adversario com todos os lacaios ja presentes na mesa
		for(int i = 0; i < meusLacaios.size(); i++) {
			Jogada ataqueLacaiosMesa = new Jogada(TipoJogada.ATAQUE, meusLacaios.get(i), null);
			minhasJogadas.add(ataqueLacaiosMesa);
		}

		//(2) Descer na mesa todas as magias de alvo
		for (int x = 0 ; x < mao.size(); x++) {
			Carta carta = mao.get(x);
			if (carta instanceof CartaMagia && carta.getMana() <= minhaMana) {
				CartaMagia magia = (CartaMagia) carta;
				if (magia.getMagiaTipo() == TipoMagia.ALVO) {
						Jogada magiaAlvo = new Jogada(TipoJogada.MAGIA, carta, null);
						minhasJogadas.add(magiaAlvo);
						minhaMana = minhaMana - carta.getMana();
						mao.remove(carta);
				}
			}
		}
		
		//(3) Descer lacaios mais fortes na mesa
		for (int x = 0; x < mao.size(); x++) {
			Carta carta = mao.get(x);
			if (carta instanceof CartaLacaio && carta.getMana() <= minhaMana) {
				CartaLacaio lacaio = (CartaLacaio) carta;
				if (lacaio.getAtaque()>= 4 && numeroMeusLacaios < 7 ) {
						Jogada ataqueLacaioForte = new Jogada(TipoJogada.LACAIO, carta, null);
						minhaMana = minhaMana - carta.getMana();
						minhasJogadas.add(ataqueLacaioForte);
						mao.remove(carta);
						numeroMeusLacaios ++;
						x--;
				}
			}
		}
		
		//(4) Descer lacaios de forca media na mesa
		for (int x = 0; x < mao.size(); x++) {
			Carta carta = mao.get(x);
			if (carta instanceof CartaLacaio && carta.getMana() <= minhaMana) {
				CartaLacaio lacaio = (CartaLacaio) carta;
				if (lacaio.getAtaque() < 4 && lacaio.getAtaque() >= 2 && numeroMeusLacaios < 7) {
						Jogada ataqueLacaioMedio = new Jogada(TipoJogada.LACAIO, carta, null);
						minhaMana = minhaMana - carta.getMana();
						mao.remove(carta);
						minhasJogadas.add(ataqueLacaioMedio);
						numeroMeusLacaios ++;
						x--;
				}
			}
		}
		if(minhaMana >= 2) {
			Jogada poderHeroicoAgressivo = new Jogada(TipoJogada.PODER, null, null);
			minhasJogadas.add(poderHeroicoAgressivo);
		}
		return minhasJogadas;
	}
	
	public ArrayList<Jogada> comportamentoControle(Mesa mesa, Carta cartaComprada, ArrayList<Jogada> jogadasOponente, ArrayList<Jogada> minhasJogadas){
		ArrayList<CartaLacaio> meusLacaios = primeiroJogador ? mesa.getLacaiosJog1() : mesa.getLacaiosJog2();
		ArrayList<CartaLacaio> inimigosLacaios = primeiroJogador ? mesa.getLacaiosJog2() : mesa.getLacaiosJog1();
		int minhaMana = primeiroJogador ? mesa.getManaJog1() : mesa.getManaJog2(); 
		
		//Realizar trocas favoraveis
		for(int x = 0; x < meusLacaios.size(); x++) {
			boolean ataqueFuncionou = false;
			for(int y = 0; y < inimigosLacaios.size(); y++) {
				//meu lacaio destroi o do oponente e continua vivo
				if (meusLacaios.get(x).getAtaque() > inimigosLacaios.get(y).getVidaAtual() && meusLacaios.get(x).getVidaAtual() > inimigosLacaios.get(y).getAtaque() && inimigosLacaios.get(y).getVidaAtual() > 0 && meusLacaios.get(x).getVidaAtual() > 0) {
					Jogada ataqueDestrutor = new Jogada(TipoJogada.ATAQUE, meusLacaios.get(x), inimigosLacaios.get(y));
					minhasJogadas.add(ataqueDestrutor);
					meusLacaios.get(x).setVidaAtual(meusLacaios.get(x).getVidaAtual() - inimigosLacaios.get(y).getAtaque());
					inimigosLacaios.get(y).setVidaAtual(0);
					ataqueFuncionou = true;
					break;
				}
				//ambos os lacaios morrerão, porém o custo de mana do lacaio y é maior do que o custo de mana do lacaio x;
				if (meusLacaios.get(x).getAtaque() > inimigosLacaios.get(y).getVidaAtual() && inimigosLacaios.get(y).getAtaque() > meusLacaios.get(x).getVidaAtual() && inimigosLacaios.get(y).getMana() > meusLacaios.get(x).getMana() && inimigosLacaios.get(y).getVidaAtual() > 0 && meusLacaios.get(x).getVidaAtual() > 0) {
					Jogada ataqueVantagemMana = new Jogada(TipoJogada.ATAQUE, meusLacaios.get(x), inimigosLacaios.get(y));
					minhasJogadas.add(ataqueVantagemMana);
					meusLacaios.get(x).setVidaAtual(0);
					inimigosLacaios.get(y).setVidaAtual(0);
					ataqueFuncionou = true;
					break;
				}
				//ambos os lacaios morrerão, porém o lacaio x já estava danificado e possui menos vida do que o lacaio y.
				if (meusLacaios.get(x).getAtaque() > inimigosLacaios.get(y).getVidaAtual() && inimigosLacaios.get(y).getAtaque() > meusLacaios.get(x).getVidaAtual() && meusLacaios.get(x).getVidaAtual() < meusLacaios.get(x).getVidaMaxima() && meusLacaios.get(x).getVidaAtual() < inimigosLacaios.get(y).getVidaAtual() && inimigosLacaios.get(y).getVidaAtual() > 0 && meusLacaios.get(x).getVidaAtual() > 0) {
					Jogada ataqueVantagemVida = new Jogada(TipoJogada.ATAQUE, meusLacaios.get(x), inimigosLacaios.get(y));
					minhasJogadas.add(ataqueVantagemVida);
					meusLacaios.get(x).setVidaAtual(0);
					inimigosLacaios.get(y).setVidaAtual(0);
					ataqueFuncionou = true;
					break;	
				}
				//nao ha trocas favoraveis
				if (ataqueFuncionou == false) {
					Jogada ataqueDireto = new Jogada(TipoJogada.ATAQUE, meusLacaios.get(x), null);
					minhasJogadas.add(ataqueDireto);
					break;
				}
			}
		}
		
		//Cartas magias comportamento controle
		for(int x = 0; x < mao.size(); x++) {
			Carta carta = mao.get(x);
			if(carta instanceof CartaMagia && carta.getMana() <= minhaMana) {
				CartaMagia magia = (CartaMagia) carta;
				//dois ou mais lacaios inimigos no campo de batalha
				if (magia.getMagiaTipo() == TipoMagia.AREA) {
					//verifica quantos lacaios vivos existem
					for(int y = 0; y < inimigosLacaios.size(); y++) {
						int lacaiosVivos = 0;
						if(inimigosLacaios.get(y).getVidaAtual() > 0) {
							lacaiosVivos = lacaiosVivos +1;
						}
						
						if (lacaiosVivos >= 2) {
							Jogada magiaArea = new Jogada(TipoJogada.MAGIA, carta, null);
							minhasJogadas.add(magiaArea);
							minhaMana = minhaMana - magia.getMana();
							mao.remove(magia);
							x--;
						}
					}
				}	
				//magia de alvo usadas para matar de forma favoravel
				if(magia.getMagiaTipo() == TipoMagia.ALVO && carta.getMana() <= minhaMana) {
					for(int y = 0; y < inimigosLacaios.size(); y++) {
						if(inimigosLacaios.get(y).getVidaAtual() > 0 && magia.getMagiaDano() > inimigosLacaios.get(y).getVidaAtual() && (magia.getMagiaDano() - inimigosLacaios.get(y).getVidaAtual()) <= 1){
							Jogada magiaAlvo = new Jogada(TipoJogada.MAGIA, magia, inimigosLacaios.get(y));
							minhasJogadas.add(magiaAlvo);
							mao.remove(magia);
							inimigosLacaios.get(y).setVidaAtual(0);
							minhaMana -= magia.getMana();
							y--;
							//break;
						}
					}
				}
				//Utilizando a magia de buff
				if(magia.getMagiaTipo() == TipoMagia.BUFF && carta.getMana() <= minhaMana) {
					int maiorVida = 0;
					//encontrando o de maior vida
					for(int y = 0; y < meusLacaios.size(); y++) {						
						//se so um lacaio meu na mesa
						if(y == 0 && meusLacaios.get(y).getVidaAtual() > 0) {
							maiorVida = meusLacaios.get(y).getAtaque();
						}
						//se tiver mais de um lacaio meu na mesa 
						if(y > 0 && meusLacaios.get(y).getVidaAtual() > meusLacaios.get(y-1).getVidaAtual() && meusLacaios.get(y).getVidaAtual() > 0 && meusLacaios.get(y-1).getVidaAtual() > 0) {
							maiorVida = meusLacaios.get(y).getVidaAtual();
						}
					}
					for(int i = 0; i < meusLacaios.size(); i++) {
						if(meusLacaios.get(i).getVidaAtual() == maiorVida && meusLacaios.get(i).getAtaque() > 4) {
							Jogada buffLacaioMaiorVida = new Jogada(TipoJogada.MAGIA, magia, meusLacaios.get(i));
							minhasJogadas.add(buffLacaioMaiorVida);
							minhaMana = minhaMana - magia.getMana();
							mao.remove(magia);
							i--;
							break;
						}
					}	
				}		
			}
		}
		
		//usando poder heroico
		if (minhaMana >= 3) {
			Jogada poderHeroico = new Jogada(TipoJogada.PODER, null, null);
			minhasJogadas.add(poderHeroico);
			minhaMana = minhaMana - 2;
		}
		return minhasJogadas;
	}
	
	public ArrayList<Jogada> comportamentoCurvaDeMana(Mesa mesa, Carta cartaComprada, ArrayList<Jogada> jogadasOponente, ArrayList<Jogada> minhasJogadas){
 		ArrayList<CartaLacaio> meusLacaios = primeiroJogador ? mesa.getLacaiosJog1() : mesa.getLacaiosJog2();
		ArrayList<CartaLacaio> inimigosLacaios = primeiroJogador ? mesa.getLacaiosJog2() : mesa.getLacaiosJog1();
		//int numeroMeusLacaios = primeiroJogador ? mesa.getLacaiosJog1().size() : mesa.getLacaiosJog2().size();
		//double minhaMana = primeiroJogador ? mesa.getManaJog1() : mesa.getManaJog2();
		//boolean mateiLacaio = false;
		
		int numeroMeusLacaios = primeiroJogador ? mesa.getLacaiosJog1().size() : mesa.getLacaiosJog2().size();
		int minhaMana = primeiroJogador ? mesa.getManaJog1() : mesa.getManaJog2();
		boolean lacaioMana = false;
		boolean mateiLacaio = false;
		boolean mateiLacaioX = false;
//#################################################################################################################		
		//Matando algum Lacaio adversario ou Atacando o heroi inimigo
		for(int i = 0; i < meusLacaios.size(); i++) {
			for(int j = 0; j < inimigosLacaios.size(); j++) {
				//Fazer com que os meus lacaios ataquem apenas o que eu chamei de lacaios uteis (mais de 3 de vida) e que possuam vida menor do que o ataque do meu lacaio (sem meu lacaio morrer).
				if(inimigosLacaios.get(j).getVidaAtual() > 3 && meusLacaios.get(i).getAtaque() > inimigosLacaios.get(j).getVidaAtual() && meusLacaios.get(i).getVidaAtual() > inimigosLacaios.get(j).getAtaque()) {
					Jogada mataLacaioUtil = new Jogada(TipoJogada.ATAQUE, meusLacaios.get(i), inimigosLacaios.get(j));
					minhasJogadas.add(mataLacaioUtil);	
					mateiLacaio = true;
					inimigosLacaios.get(j).setVidaAtual(0);
					meusLacaios.get(i).setVidaAtual(meusLacaios.get(i).getVidaAtual() - inimigosLacaios.get(j).getAtaque());
				}
			}
			// se nao for possivel matar o lacaio inimigo, atacar diretamente o heroi do oponente
			if (mateiLacaio == false) {
				Jogada ataqueDiretoHeroi = new Jogada(TipoJogada.ATAQUE, meusLacaios.get(i), null);
				minhasJogadas.add(ataqueDiretoHeroi);
			}
		}

//####################################################################################################################		
		//comportamento agressivo de baixar o lacaio mais forte da mao possivel (custo de mana = custo de mana do turno). 
		for(int x = 0; x < mao.size(); x++) {
			Carta carta = mao.get(x);
			//baixando lacaio de custo igual o meu numero de mana no turno
			if(carta instanceof CartaLacaio && numeroMeusLacaios < 7 && carta.getMana() == minhaMana ) {
				Jogada ataqueLacaioMana = new Jogada(TipoJogada.LACAIO, carta, null);
				minhasJogadas.add(ataqueLacaioMana);
				minhaMana = 0;
				mao.remove(carta);
				numeroMeusLacaios++;
				x--;
				lacaioMana = true;
				break;
			}
		}

//#####################################################################################################################		
		//Utilizando 80% da mana para baixar um lacaio e o restante para usar uma magia
		if(lacaioMana == false) {
			int manaLac = (int) (minhaMana*(0.8));
			//Baixando lacaio com custo de mana igual a 80% da minha mana atual ou menos
			for(int i = 0; i < mao.size(); i++) {
				if(mao.get(i) instanceof CartaLacaio && mao.get(i).getMana() <= manaLac && numeroMeusLacaios < 7) {
					Jogada lac = new Jogada(TipoJogada.LACAIO, mao.get(i), null);
					minhaMana = minhaMana - mao.get(i).getMana();
					mao.remove(mao.get(i));
					i--;
					numeroMeusLacaios++;
					minhasJogadas.add(lac);
					manaLac = minhaMana;
				}
			}
			//utilizando as magias em mao com o restante de mana
			for(int i = 0; i < mao.size(); i++) {
				if(mao.get(i) instanceof CartaMagia && mao.get(i).getMana() <= minhaMana) {
					CartaMagia cartaMagia = (CartaMagia) mao.get(i);
					//equilibro dando dano em todos os lacaios adversarios e Heroi adversario
					if(cartaMagia.getMagiaTipo() == TipoMagia.AREA && mao.get(i).getMana() <= minhaMana) {	
						Jogada desceAlvoArea = new Jogada(TipoJogada.MAGIA, cartaMagia, null);
						minhasJogadas.add(desceAlvoArea);
						minhaMana = minhaMana - cartaMagia.getMana();
						mao.remove(cartaMagia);
						i--;
					}	
					if(cartaMagia.getMagiaTipo() == TipoMagia.ALVO ) {
						for(int j = 0; j < inimigosLacaios.size(); j++) {
							//Destruindo um lacaio inimigo com magia de alvo
							if(cartaMagia.getMagiaDano() >= inimigosLacaios.get(j).getVidaAtual() && (inimigosLacaios.get(j).getVidaAtual() > 0)) {
								mateiLacaioX = true;
								Jogada desceMagiaAlvo = new Jogada(TipoJogada.MAGIA, cartaMagia, inimigosLacaios.get(j));
								minhaMana -= cartaMagia.getMana();
								minhasJogadas.add(desceMagiaAlvo);
								mao.remove(cartaMagia);
								i--;
								inimigosLacaios.get(j).setVidaAtual(0);
								break;
							}
						}
					}
				}
			}
			if(mateiLacaioX == false) {
				for(int i = 0; i < mao.size(); i++) {
					if(mao.get(i) instanceof CartaMagia && mao.get(i).getMana() <= minhaMana) {
						CartaMagia cartaMagia = (CartaMagia) mao.get(i);
						
						if(cartaMagia.getMagiaTipo() == TipoMagia.ALVO) {
							//Atacar o lacaio inimigo de menor vida
							int lacaioMenorVida = 0;
							//se so um lacaio meu na mesa
							if(inimigosLacaios.size() > 0)
								lacaioMenorVida = inimigosLacaios.get(0).getVidaAtual();
							for(int j = 0; j < inimigosLacaios.size(); j++) {
								//se tiver mais de um lacaio meu na mesa 
								if(j > 0 && inimigosLacaios.get(j).getVidaAtual() < inimigosLacaios.get(j-1).getVidaAtual() && inimigosLacaios.get(j).getVidaAtual() > 0 && inimigosLacaios.get(j-1).getVidaAtual() > 0) 
									lacaioMenorVida = inimigosLacaios.get(j).getVidaAtual();
							}
							for(int k = 0; k < inimigosLacaios.size(); k++) {
								if(inimigosLacaios.get(k).getVidaAtual() == lacaioMenorVida && lacaioMenorVida  > 0) {
									Jogada magiaAlvo = new Jogada(TipoJogada.MAGIA, cartaMagia, inimigosLacaios.get(k));
									minhaMana -= cartaMagia.getMana();
									minhasJogadas.add(magiaAlvo);
									mao.remove(cartaMagia);
									i--;
									break;
								}
							}
						}
						
					}
				}
			}
		}
//###############################################################################################################		
		//usando poder de mana
		if(minhaMana >= 2) {
			Jogada poderHeroico = new Jogada(TipoJogada.PODER, null, null);
			minhasJogadas.add(poderHeroico);
			minhaMana = minhaMana -2;
		}
		return minhasJogadas;
	}		
}