import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SequenceAlignment {
	
	public static void main(String [] args) throws FileNotFoundException {
		
		File file = new File("src/sequence-alignment-sample-IO/sample-input/input3.txt");
		Scanner sc = new Scanner(file);
		//String one = sc.next();
		//String two = sc.next();
		String one = "ACGCCG";
		String two = "ACCACG";
		String[] aligned = mutateDNA(one, two);
		scoringMatrix(aligned[0], aligned[1]);
		System.out.println(aligned[0]);
		System.out.println(aligned[1]);	
		//printMutateDNA(one, two);
	}
	
	public static void scoringMatrix(String one, String two) {
		char[] arr1 = one.toCharArray();
        char[] arr2 = two.toCharArray();
        int score = 0;
        boolean areEqual = false;
        if (arr1.length == arr2.length){
            areEqual = true;
        }
        if (areEqual == true){
            for (int i = 0; i < arr1.length; i++){
                if (arr1[i] == arr2[i]){
                    score += 2;
                } else if (arr1[i] == '-' || arr2[i] == '-'){
                    score--;
                } else {
                    score -= 2;
                }
            }        
        }
        System.out.println(score);
	}
	public static String[] mutateDNA(String one, String two) {
		StringBuilder finalOne = new StringBuilder(), finalTwo = new StringBuilder();
        int[][] matrix = new int[one.length() + 1][two.length() + 1];
        for (int i = 0; i <= one.length(); i++){
            matrix[i][0] = i;
        }
        for (int i = 0; i <= two.length(); i++){
            matrix[0][i] = i;
        }
        for (int i = 1; i <= one.length(); i++) {
            for (int j = 1; j <= two.length(); j++) {
                if (one.charAt(i - 1) == two.charAt(j - 1)){
                    matrix[i][j] = matrix[i - 1][j - 1];
                }
                else{
                    matrix[i][j] = Math.min(matrix[i - 1][j], matrix[i][j - 1]) + 1;
                }
            }
        }
        for (int i = one.length(), j = two.length(); i > 0 || j > 0; ) {
            if (i > 0 && matrix[i][j] == matrix[i - 1][j] + 1) {
                finalOne.append(one.charAt(--i));
                finalTwo.append("-");
            } else if (j > 0 && matrix[i][j] == matrix[i][j - 1] + 1) {
                finalTwo.append(two.charAt(--j));
                finalOne.append("-");
            } else if (i > 0 && j > 0 && matrix[i][j] == matrix[i - 1][j - 1]) {
                finalOne.append(one.charAt(--i));
                finalTwo.append(two.charAt(--j));
            }
        }
        return new String[]{finalOne.reverse().toString(), finalTwo.reverse().toString()};
	}
	
	public static void printMutateDNA(String one, String two) {
		StringBuilder finalOne = new StringBuilder(), finalTwo = new StringBuilder();
        int[][] matrix = new int[one.length() + 1][two.length() + 1];
        for (int i = 0; i <= one.length(); i++){
            matrix[i][0] = i;
        }
        for (int i = 0; i <= two.length(); i++){
            matrix[0][i] = i;
        }
        
        for (int i = 1; i <= one.length(); i++) {
            for (int j = 1; j <= two.length(); j++) {
                if (one.charAt(i - 1) == two.charAt(j - 1)){
                    matrix[i][j] = matrix[i - 1][j - 1];
                }
                else{
                    matrix[i][j] = Math.min(matrix[i - 1][j], matrix[i][j - 1]) + 1;
                }
            }
        }
      //print matrix
        for (int i=0; i< matrix.length; i++) {
        	for(int j=0; j< matrix[i].length; j++) {
        		System.out.print(matrix[i][j] + " ");
        	}
        	System.out.println();
        }
        
        for (int i = one.length(), j = two.length(); i > 0 || j > 0; ) {
            if (i > 0 && matrix[i][j] == matrix[i - 1][j] + 1) {
                finalOne.append(one.charAt(--i));
                finalTwo.append("-");
            } else if (j > 0 && matrix[i][j] == matrix[i][j - 1] + 1) {
                finalTwo.append(two.charAt(--j));
                finalOne.append("-");
            } else if (i > 0 && j > 0 && matrix[i][j] == matrix[i - 1][j - 1]) {
                finalOne.append(one.charAt(--i));
                finalTwo.append(two.charAt(--j));
                //System.out.println("appended " + one.charAt(i-1) + " and " + two.charAt(j-1));
            }
        }
	}
}
