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
		String line = br.readLine();
		while(line!=null){
			int availableBooks = Integer.parseInt(line);
			String[] prices = br.readLine().split(" ");
			int peterMoney = Integer.parseInt(br.readLine());
			arrayOfPrices = new int[availableBooks];
			for(int k = 0;k<prices.length;k++) {
				arrayOfPrices[k] = Integer.parseInt(prices[k]);
			}
			Arrays.sort(arrayOfPrices);
			String message = findBooks(peterMoney);
			bw.write(message+"\n\n");
			line = br.readLine();
			line = br.readLine();
		}
		bw.flush();
		br.close();
		bw.close();
	}

	public static String findBooks(int peterMoney) {
		String message = "";
		int firstPrice = 0, secondPrice = 0, subtraction1 = 0, subtraction2 = 0;
		for (int i = 0; i < arrayOfPrices.length; i++) {
			boolean find = false;
			int beginning = 0;
			int end = arrayOfPrices.length-1;
			while(beginning <= end && !find) {
				int middle = (beginning + end)/2;
				if((arrayOfPrices[middle]+ arrayOfPrices[i])==peterMoney) {
					if(firstPrice == 0 && secondPrice == 0) {
						if(arrayOfPrices[middle]<arrayOfPrices[i] || (arrayOfPrices[middle]==arrayOfPrices[i] && middle-i!=0)) {
							firstPrice = arrayOfPrices[middle];
							secondPrice = arrayOfPrices[i];
							subtraction2 = secondPrice-firstPrice;
							message = "Peter should buy books whose prices are "+firstPrice+" and "+secondPrice+".";
						}else{
							subtraction2 = firstPrice-secondPrice;
							message = "Peter should buy books whose prices are "+secondPrice+" and "+firstPrice+".";
						}
					}else {
						if(arrayOfPrices[middle]<arrayOfPrices[i]) {
							subtraction1 = arrayOfPrices[i]-arrayOfPrices[middle];
						}else {
							subtraction1= arrayOfPrices[middle]-arrayOfPrices[i];
						}
						if(subtraction1<subtraction2) {
							if(arrayOfPrices[i]<arrayOfPrices[middle]) {
								message = "Peter should buy books whose prices are "+arrayOfPrices[i]+" and "+arrayOfPrices[middle]+".";
							}else {
								message = "Peter should buy books whose prices are "+arrayOfPrices[middle]+" and "+arrayOfPrices[i]+".";
							}
						}else {
							if(firstPrice<secondPrice) {
								message = "Peter should buy books whose prices are "+firstPrice+" and "+secondPrice+".";
							}else {
								message = "Peter should buy books whose prices are "+secondPrice+" and "+firstPrice+".";
							}
						}
					}
					find = true;
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
