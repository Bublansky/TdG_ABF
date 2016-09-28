import  java.util.Scanner;
class Main
{
    private static Scanner entrada = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        int ordem, i, j, origem;
        Grafo g;
        // leitura da ordem-1 da matriz
        ordem = entrada.nextInt();
        g = new Grafo(ordem-1);
        // leitura da matriz de adjacencias
        for(i = 1 ; i < ordem ; i++)
        {
            for(j = 1 ; j < ordem ; j++)
            {
                g.AddAresta(i, j, entrada.nextInt());
            }
        }
        origem = entrada.nextInt();
        //g.ImprimirAdjacencias();
        g.BellmanFord(origem+1);
        
        //System.out.println("ue");
        //System.out.println(g.GetComponentesFortementeConectados());
        
        //g.ImprimirAdjacencias();
    }
    
    private static class Grafo
    {
        private int adj[][];
        private int vertices = 0;
        private int anterior[];
        private int distancia[];

        public Grafo(int quantidade)
        {
            vertices = quantidade;
            adj = new int [quantidade + 1][quantidade + 1];
            anterior = new int[quantidade + 1];
            distancia = new int[quantidade + 1];
        }
        public int getAresta(int origem, int destino)
        {
            return adj[origem][destino];
        }
        public void AddAresta(int linha, int coluna, int value)
        {
            adj[linha][coluna] = value;
        }
        public void BellmanFord(int origem)
        {
            int v, i, u, peso;
            //passo 1:
            for(v = 1 ; v <= vertices ; v++)
            {
                distancia[v] = Integer.MAX_VALUE;
                anterior[v] = -1;
            }
            distancia[origem] = 0;
            //passo 2:
            for(i = 1 ; i < vertices ; i++)
            {
                for(u = 1 ; u <= vertices ; u++)
                {
                    for(v = 1 ; v <= vertices ; v++)
                    {
                        peso = getAresta(u, v);
                        if(peso != 0)
                        {    
                            if(distancia[u] + peso < distancia[v])
                            {
                                distancia[v] = distancia[u] + peso;
                                anterior[v] = u;
                            }
                        }
                    }
                }
            }
            //passo 3:
            for(u = 1 ; u <= vertices ; u++)
            {
                for(v = 1 ; v <= vertices ; v++)
                {
                    peso = getAresta(u, v);
                    if(peso != 0)
                    {
                        if(distancia[u] + peso < distancia[v])
                        {
                            System.out.println("ciclo negativo");
                            System.exit(0);
                        }
                    }
                }
            }
            for(i = 1 ; i <= vertices ; i++)
            {
                if(distancia[i] == Integer.MAX_VALUE)
                {
                    System.out.print("i ");
                }
                else
                {
                    System.out.print(distancia[i] + " ");
                }
            }
        }
        
        public void ImprimirVetor(int A[])
        {
            System.out.print("\n<!------------------------------------------------------->\n");
            for(int i = 1 ; i < A.length ; i++)
            {
                System.out.print(A[i] + "  ");
            }
        }
        public void ImprimirAdjacencias()
        {
            System.out.print("\n<!------------------------------------------------------->\n");
            int i, j;
            for(i = 1 ; i <= vertices ; i++)
            {
                for(j = 1 ; j <= vertices ; j++)
                {
                    System.out.print(adj[i][j] + " ");
                }
                System.out.println("");
            }
        }
    }
}
 