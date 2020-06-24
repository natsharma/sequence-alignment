import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class LongestSubsequence {
	//longest common subsequence
    static int commonChild(String s1, String s2) {
        int[][] matrix = new int[s1.length()+1][s2.length()+1];
        for(int i=0; i< matrix.length; i++){matrix[i][0] = 0;}
        for(int i=0; i<matrix[0].length; i++){matrix[0][i] = 0;}
        int max = Integer.MIN_VALUE;
        for(int i=1; i< matrix.length; i++){
            for(int j=1; j< matrix[i].length; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    matrix[i][j] = matrix[i-1][j-1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
                }
                if(matrix[i][j] > max){
                    max = matrix[i][j];
                }
            }
        }
        return max;
        //returns the length of the longest common subsequence between two strings
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}