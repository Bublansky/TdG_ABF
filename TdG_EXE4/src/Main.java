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
        ordem--;
        g = new Grafo(ordem);
        // leitura da matriz de adjacencias
        for(i = 0 ; i < ordem ; i++)
        {
            for(j = 0 ; j < ordem ; j++)
            {
                g.AddAresta(i, j, entrada.nextInt());
            }
        }
        origem = entrada.nextInt();
        //g.ImprimirAdjacencias();
        g.BellmanFord(origem);
        
        //System.out.println("ue");
        //System.out.println(g.GetComponentesFortementeConectados());
        
        //g.ImprimirAdjacencias();
    }
    
    private static class Grafo
    {
        private int adj[][];
        private int vertices = 0;
        private int distancia[];

        public Grafo(int quantidade)
        {
            vertices = quantidade;
            adj = new int [quantidade][quantidade];
            distancia = new int[quantidade];
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
            for(v = 0 ; v < vertices ; v++)
            {
                distancia[v] = Integer.MAX_VALUE;
            }
            distancia[origem] = 0;
            //passo 2:
            for(i = 0 ; i < vertices - 1; i++)
            {
                for(u = 0 ; u < vertices ; u++)
                {
                    for(v = 0 ; v < vertices ; v++)
                    {
                        peso = getAresta(u, v);
                        if(peso != 0)
                        {    
                            if(distancia[u] + peso < distancia[v] && distancia[u] != Integer.MAX_VALUE)
                            {
                                distancia[v] = distancia[u] + peso;
                            }
                        }
                    }
                }
            }
            //passo 3:
            for(u = 0 ; u < vertices ; u++)
            {
                for(v = 0 ; v < vertices ; v++)
                {
                    peso = getAresta(u, v);
                    //se ha uma aresta uv
                    if(peso != 0)
                    {
                        if(distancia[u] + peso < distancia[v] && distancia[u] != Integer.MAX_VALUE)
                        {
                            System.out.println("ciclo negativo");
                            return;
                        }
                    }
                }
            }
            for(i = 0 ; i < vertices ; i++)
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
    }
}
 