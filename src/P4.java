import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;
import java.io.*;

import java.util.Comparator;

public class P4 {
   
   public static void main(String[] args) throws Exception {

        PrintWriter pw = new PrintWriter(new File("p4.out"));
            pw.print(-1);
     

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
  