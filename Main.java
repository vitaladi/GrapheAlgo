import graphe.Graphe;

public class Main {
	
	//probleme sur les resultats attendus
	public static void testTarjan() {
		int[] fs = { 22, 2, 3, 4, 0, 5, 0, 6, 0, 7, 0, 1, 6, 0, 3, 4, 7, 8, 0, 4, 0, 7, 0 };
		int[] aps = { 8, 1, 5, 7, 9, 11, 14, 19, 21 };

		int taille = aps.length+1;
		int[] pilch = new int[taille];
		int[] cfc = new int[taille];
		boolean[] entarj = new boolean[taille];
		int[] ro = new int[taille];
		int[] tarj = new int[taille];
		int[] prem = new int[taille];
		int[] num = new int[taille];
		int p = 0;
		int nb=0;

		Graphe g = new Graphe();

		for (int s = 1; s <= aps[0]; s++) {

			if (num[s] == 0) {
				g.traverse(s, p, nb, fs, aps, tarj, num, ro, cfc, pilch, prem, entarj);

			}
		}
		System.out.print("Pilch :");
		for (int i = 1; i < aps.length; i++) {
			System.out.print(pilch[i] + " ");
		}
		System.out.println();

		System.out.print("Prem :");
		for (int i = 1; i < aps.length; i++) {
			System.out.print(prem[i] + " ");
		}
		System.out.println();

		System.out.print("CFC :");
		for (int i = 1; i < aps.length; i++) {
			System.out.print(cfc[i] + " ");
		}
		System.out.println();
		
		System.out.print("Num :");
		for (int i = 1; i < aps.length; i++) {
			System.out.print(num[i] + " ");
		}
		System.out.println();
		
		System.out.print("Ro :");
		for (int i = 1; i < aps.length; i++) {
			System.out.print(ro[i] + " ");
		}
		System.out.println();
		
		System.out.print("Tarj :");
		for (int i = 1; i < aps.length; i++) {
			System.out.print(tarj[i] + " ");
		}
	}
	
	public static void testCodagePrufer() {
		//dans P[0] on aura n-2 où n est le nombre de sommets
		int[]P=null;
		int[][]adj=new int[7][7];
		for (int i=1;i<7;i++) {
			for (int j=1;j<7;j++) {
				adj[i][j]=0;
			}
		}
		adj[0][0]=6;
		
		adj[1][0]=1;
		adj[2][0]=4;
		adj[3][0]=1;
		adj[4][0]=1;
		adj[5][0]=2;
		adj[6][0]=1;

		
		adj[1][2]=1;
		adj[2][1]=1;
		adj[2][3]=1;
		adj[2][4]=1;
		adj[2][5]=1;
		adj[3][2]=1;
		adj[4][2]=1;
		adj[5][2]=1;
		adj[5][6]=1;
		adj[6][5]=1;
		
		Graphe g=new Graphe();
		g.codagePrufer(adj, P);
	}
	
	public static void testDecodagePrufer() {
		//dans P[0] on aura n-2 où n est le nombre de sommets
		
		//int[]P= {7,2,2,2,5,6,5,7};
		//int[]P= {4,2,2,2,5};
		int[]P= {8,4,10,3,8,4,4,5,10};
		int[]I= null;
		int[]b = null;
		Graphe g=new Graphe();
		g.decodagePrufer(P, I, b);
	}

	public static void main(String[] args) {
		//testTarjan();
		//testDecodagePrufer();
		testCodagePrufer();
	}
}
