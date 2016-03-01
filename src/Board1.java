
public class Board1 {

public static void printaTab(char[][] tabu){
	for(int i=0;i<10;i++)
		for(int j=0;j<10;j++)
		{
			if(j==9){
				System.out.println(tabu[i][j]);
		
			}
			else {
				System.out.print(tabu[i][j]);
				System.out.print(" ");
				
			}
		}
}
	
public static char[][] tab(){
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
		//meio a branco e á volta a X
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



public static void main(String[] args) {
	char[][] teste=new char[10][10];
	teste=tab();
	printaTab(teste);


}
}
