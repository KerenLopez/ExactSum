import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	
	public static int[] arrayOfPrices;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int availableBooks = Integer.parseInt(br.readLine());
		String[] prices = br.readLine().split(" ");
		int peterMoney = Integer.parseInt(br.readLine());
		br.close();
		arrayOfPrices = new int[availableBooks];
		for(int k = 0;k<prices.length;k++) {
			arrayOfPrices[k] = Integer.parseInt(prices[k]);
		}
		String message1 = findBooks(peterMoney);
		System.out.println(message1);
	}
	
	public static String findBooks(int peterMoney) {
		Arrays.sort(arrayOfPrices);
		String message = "";
		boolean find = false;
		int beginning = 0;
		int end = arrayOfPrices.length-1;
		while(beginning <= end && !find) {
			int middle = (beginning + end)/2;
			for (int i = 0; i < arrayOfPrices.length; i++) {
				if((arrayOfPrices[middle]+ arrayOfPrices[i])==peterMoney) {
					message = "Peter should buy books whose prices are "+arrayOfPrices[middle]+" and "+arrayOfPrices[i];
				}else if((arrayOfPrices[middle] + arrayOfPrices[i])>peterMoney){
					end = middle - 1;
				}else {
					beginning = middle + 1;
				}
			}
		}
		return message;
	}

}
