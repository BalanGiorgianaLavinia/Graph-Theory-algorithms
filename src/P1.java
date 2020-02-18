import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;
import java.io.*;

import java.util.Comparator;

public class P1 {
   
    static class Dist implements Comparable<Dist>{
        int node;
        int dist;

        Dist(int _node, int _dist){
            this.node = _node;
            this.dist = _dist;
        }

        @Override
        public int compareTo(Dist d){
            return Integer.compare(this.dist, d.dist);
        }
    }


    public static void main(String[] args) throws Exception {

        MyScanner sc = new MyScanner(new FileInputStream("p1.in"));
        PrintWriter pw = new PrintWriter(new File("p1.out"));
            
        int n = sc.nextInt();
        ArrayList<Dist> v = new ArrayList<>(n);
           
        int dist;
        for(int i = 0; i < n; i++){
            dist = sc.nextInt();
            v.add(new Dist(i + 1, dist));
        }

	    sc.close();

        //sortez crescator vectorul de distante
        //pentru a putea construi muchiile pe fiecare nivel
        Collections.sort(v);

        //daca am mai multe noduri marcate ca radacina
        if( v.get(1).dist == 0 ){
            pw.print(-1); 
            pw.close(); 
            return;
        }

        //verific daca se poate forma arborele
        //adica daca nu pot ajunge la un anumit nod 
        int index = 0; 
        for( int i = 0; i < n; i ++ ){
            if( v.get(i).dist == index ) continue;
            index += 1;
            if( v.get(i).dist != index ){
                pw.print(-1); 
                pw.close(); 
                return;
            }
        }


        //numarul de muchii ce trebuiesc formate 
        //este egal cu numarul de noduri - 1
        pw.print(n-1);
        pw.println();


        //current_dist este nivelul curent la care sunt in graf

        //current_nod este nodul la care leg urmatorul nod din graf
        //adica nodul parinte pentru nodurile de pe nivelul curent

        //aux ia pe rand valorile nodurilor de pe nivelul curent
        //ex: daca am nodurile 2,3,4 de distanta 1, 
        //inseamna ca vor fi legate direct la nodul 1 
        //nodul 1 va fi current_node, iar aux va lua pe rand valorile 2, 3, 4
        //la trecerea pe urmatorul nivel, 4(ultimul aux) va fi current_node
        int current_dist = 1;
        int current_nod = 1;
        int aux = 1;

        for( int i = 1; i < n; i++){

            if( v.get(i).dist == current_dist ){
                //actualizez aux
                aux = v.get(i).node;

                pw.printf("%d %d\n", current_nod, v.get(i).node );

            }else{
                //daca trec pe urmatorul nivel in graf
                //actualizez nivelul curent
                //current_nod devine egal cu ultimul aux(ca in ex. dat mai sus)
                current_dist++;
                current_nod = aux;
                aux = v.get(i).node;

                pw.printf("%d %d\n", current_nod, v.get(i).node );

            }

        }




        pw.close();

		return;
	}

}



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
  