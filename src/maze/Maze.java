package maze;

import java.util.Scanner;
import maze.Position.Direction;


public class Maze {
	char[][] board=new char[10][10];
	Dragon dragon;
	Hero hero;
	Sword sword;
	Position exitPos;

	public Maze(){
		this.board = createBoard();
		dragon = new Dragon(new Position(1, 3));
		hero = new Hero(new Position(1, 1));
		sword = new Sword(new Position(8, 1));
		exitPos = new Position(5, 9);

		Scanner s = new Scanner(System.in);
		for(int i = 0;i < 100;i++){
			printBoard();
			int move;
			System.out.println("Movimento do herói:");
			System.out.println("0 -> esquerda");
			System.out.println("1 -> direita");
			System.out.println("2 -> cima");
			System.out.println("3 -> baixo");
			System.out.print(">> ");

			move = s.nextInt();
			Direction dir = null;

			switch(move){
			case 0:
				dir = Direction.LEFT;
				break;
			case 1:
				dir = Direction.RIGHT;
				break;
			case 2:
				dir = Direction.UP;
				break;
			case 3:
				dir = Direction.DOWN;
				break;
			default:
				break;
			}
			Position oldPosHero = hero.getPosition();
			hero.atualizePosition(dir);

			if(isInvalidHeroPosition()){
				hero.setPosition(oldPosHero);
				System.out.println("Posição inválida. Tente de novo...");
			}
			else{
				if(heroFoundSword()){
					sword.take();
					hero.arm();
				}
				if(heroIsNearToTheDragon()){
					if(hero.isArmed())
						dragon.killDragon();
					else
						hero.killHero();
				}
				changeBoard(oldPosHero);
			}

		}
		s.close();
	}



	public boolean heroFoundSword(){
		int x = hero.getPosition().getX();
		int y = hero.getPosition().getY();
		if(board[y][x] == 'E')
			return true;
		else
			return false;

	}

	public boolean isInvalidHeroPosition(){
		Position heroPos = hero.getPosition();
		char heroPosInBoard = board[heroPos.getY()][heroPos.getX()];
		if(heroPosInBoard == 'X')
			return true;
		else
			return false;
	}

	public boolean heroIsNearToTheDragon(){
		int x = hero.getPosition().getX();
		int y = hero.getPosition().getY();
		if(x != 9 && board[y][x+1] == 'D')
			return true;
		if(board[y][x-1] == 'D' || board[y-1][x] == 'D' || board[y+1][x] == 'D')
			return true;
		else
			return false;

	}

	public void changeBoard(Position oldPosHero){
		if(sword.wasTaken()){
			board[sword.getPosition().getY()][sword.getPosition().getX()] = ' ';
		}
		
		board[oldPosHero.getY()][oldPosHero.getX()] = ' ';
		if(hero.isAlive()){
			if(hero.isArmed())
				board[hero.getPosition().getY()][hero.getPosition().getX()] = 'A';
			else
				board[hero.getPosition().getY()][hero.getPosition().getX()] = 'H';
		}
		
		if(!dragon.isAlive()){
			board[dragon.getPosition().getY()][dragon.getPosition().getX()] = ' ';
		}
	}

	public void printBoard(){
		for(int i=0;i<10;i++)
			for(int j=0;j<10;j++)
			{
				if(j==9){
					System.out.println(board[i][j]);

				}
				else {
					System.out.print(board[i][j]);
					System.out.print(" ");

				}
			}
	}

	public static char[][] createBoard(){
		char[][] board=new char[10][10];

		char parede='X';
		char heroi=	'H';
		char saida= 'S';
		char dragao='D';
		char espada='E';
		char branco=' ';

		//tudo a X
		for(int j=0;j<10;j++){
			board[0][j]=parede;
			board[9][j]=parede;
			board[j][0]=parede;
			board[j][9]=parede;
		}
		//meio a branco e Ã¡ volta a X
		for(int i=1;i<9;i++)
			for(int k=1;k<9;k++)
				board[i][k]=branco;


		//casos especiais de X
		for(int p=2;p<9;p++)
			for(int l=2;l<4;l++){
				if(p==5)
					continue;
				else
					board[p][l]=parede;
			}

		//casos especiais de X
		for(int m=2;m<8;m++){
			if(m==5)
				continue;
			else
				board[m][5]=parede;
		}

		for(int n=2;n<8;n++){
			board[n][7]=parede;
		}

		board[1][1]=heroi;
		board[5][9]=saida;
		board[3][1]=dragao;
		board[8][1]=espada;



		return board;

	}


}
