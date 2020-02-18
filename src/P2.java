import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.io.*;
import java.util.PriorityQueue;
import java.util.Arrays;


class MyScanner {
    BufferedReader br;
    StringTokenizer st;
        
    public MyScanner(FileInputStream f) {
        br = new BufferedReader(new InputStreamReader(f));
    }

    String next() throws IOException {
        while (st == null || !st.hasMoreElements())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }
        
    int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    void close() throws IOException {
        br.close();
    }
}



class Coordinates implements Comparable<Coordinates>{
    int x;
    int y;

    Coordinates(int _x, int _y){
        this.x = _x;
        this.y = _y;
    }

    @Override
        public int compareTo(Coordinates pozition){
            return Integer.compare(this.x, pozition.x);
        }
}



public class P2 {
    
    public static void main(String[] args) throws Exception {

        MyScanner sc = new MyScanner(new FileInputStream("p2.in"));
        PrintWriter pw = new PrintWriter(new File("p2.out"));
            
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        int[][] matrix = new int[n + 2][m + 2];

        //initializez matricea de valori
        //pun pe marginea matricei -8 
        //imi este util acest lucru pentru a stii 
        //daca am vecin stanga/dreapta/sus/jos
        for(int i = 0; i < n + 2; i++){
            for(int j = 0; j < m + 2; j++){

                if( i == 0 || j == 0 || i == n+1 || j == m+1 ){
                    matrix[i][j] = -8;
                    continue;
                }

                matrix[i][j] = sc.nextInt();

            }
        }

        sc.close();


        
        //in maxArea retin aria maxima pe care o voi afisa
        int maxArea = 0;

        //currentmaxArea este variabila in care retin valoarea maxima 
        //a ariei pentru zona curent gasita
        int currentmaxArea = 0;

        //pentru fiecare element din matrice pornesc in cautarea unei zone valide
        for( int i = 1; i < n+1; i++){
            for(int j = 1; j < m+1; j++){

                //in matricea de vizitate retin nodurile prin care am trecut 
                //in cautarea zonei CURENTE
                boolean[][] checked = new boolean[n+2][m+2];

                for( int p = 0; p < n + 2; p++)
                        Arrays.fill(checked[p], Boolean.FALSE);
                
               
                //in container adaug pozitiile elementelor din matrice 
                //care fac parte din zona valida curenta
                //pentru a putea apoi sa le extrag pe rand si 
                //sa le verific vecinii pentru a extinde zona 
                PriorityQueue<Coordinates> container = new PriorityQueue<>();

                //min este elementul minim din zona pe care o construiesc
                //initial, zona are un singur element
                int min = matrix[i][j];

                //adaug elementul in container si aria maxima curenta va fi 1
                container.add(new Coordinates(i, j));
                currentmaxArea = 1;

                //odata ce il adaug in coada, marchez elementul ca vizitat
                //adica, face parte din noua zona
                checked[i][j] = true;


                //extrag cate un element din coada si 
                //verific daca are vecini ce pot extinde zona
                while( !container.isEmpty() ){

                    Coordinates temp = container.poll();


                    //vecin sus
                    //daca nu face parte inca din zona curenta si 
                    //adaugarea lui nu incalca conditia ca (max - min) <= k
                    //atunci il adaug in coada(zona), marchez ca vizitat 
                    //si incrementez aria maxima a zonei curente
                    if( matrix[temp.x-1][temp.y] != -8 && 
                                checked[temp.x-1][temp.y] == false){

                        if( matrix[temp.x-1][temp.y] >= min && 
                                matrix[temp.x-1][temp.y] <= min + k) {   
                                    
                                    currentmaxArea++;
                                    container.add(new Coordinates(temp.x-1, temp.y));
                                    checked[temp.x-1][temp.y] = true;

                        }    
                       
                    }



                    //vecin stanga
                    //asemenea vecin sus
                    if( matrix[temp.x][temp.y-1] != -8 && 
                        checked[temp.x][temp.y-1] == false ){

                        if( matrix[temp.x][temp.y-1] >= min && 
                            matrix[temp.x][temp.y-1] <= min + k )  { 

                                currentmaxArea++;
                                container.add(new Coordinates(temp.x, temp.y-1));
                                checked[temp.x][temp.y-1] = true;

                        }    
                       
                    }


                    //vecin jos
                    //asemenea vecin sus
                    if( matrix[temp.x + 1][temp.y] != -8 && 
                        checked[temp.x + 1][temp.y] == false ){
                        
                            if( matrix[temp.x + 1][temp.y] >= min && 
                                matrix[temp.x + 1][temp.y] <= min + k )  {

                                currentmaxArea++;
                                container.add( new Coordinates(temp.x + 1, temp.y) );
                                checked[temp.x + 1][temp.y] = true;

                        }  
                        
                    }


                    //vecin dreapta
                    //asemenea vecin sus
                    if( matrix[temp.x][temp.y + 1] != -8 && 
                        checked[temp.x][temp.y + 1] == false ){

                        if( matrix[temp.x][temp.y + 1] >= min && 
                            matrix[temp.x][temp.y + 1] <= min + k ) {

                                currentmaxArea++;
                                container.add( new Coordinates(temp.x, temp.y + 1) );
                                checked[temp.x][temp.y + 1] = true;

                        }  
                       
                    }
                }

                //actualizez aria maxima cu maximul dintre zonele formate
                if(currentmaxArea > maxArea)    maxArea = currentmaxArea;

            }    

        
        }


        pw.print(maxArea);


        pw.close();

        return;


    }
}