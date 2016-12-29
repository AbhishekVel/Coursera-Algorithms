import java.util.Scanner;

/**
 * 
 * @author Abhishek Velayudham
 *
 */
public class KaratsubaMultiplication {

	public static void main(String[] args) {
		/*&= sc = new Scanner(System.in);
		Integer num1 = sc.nextInt();
		Integer num2 = sc.nextInt();*/
		//karatsubaMultiplication(num1, num2);
		System.out.println(karatsubaMultiplication(1301, 12));
	}
	
	public static int karatsubaMultiplication(Integer num1, Integer num2) { // not working for digits with odd number of digits
		if (num1 < 10 || num2 < 10) return num1*num2;
		
		String num1Str = String.valueOf(num1);
		String num2Str = String.valueOf(num2);
		int padding = (int) Math.pow(10, Integer.max(num1Str.length(), num2Str.length()));
		int padding2 = (int) Math.pow(10, Integer.max(num1Str.length()/2, num2Str.length()/2));
		
		int half1 = num1Str.length()/2;
		if (num1Str.length() % 2 != 0) {
			half1 += 1;
			padding /= 10;
		}
		int half2 = num2Str.length()/2;
		if (num2Str.length() %2 != 0) {
			half2 += 1;
			padding2 /= 10;
		}
		
		int a = Integer.parseInt(num1Str.substring(0, half1));
		int b = Integer.parseInt(num1Str.substring(half1));
		int c = Integer.parseInt(num2Str.substring(0, half2));
		int d = Integer.parseInt(num2Str.substring(half2));
		
		int ac = karatsubaMultiplication(a, c);
		int bd = karatsubaMultiplication(b, d);
		int abcd = karatsubaMultiplication(a+b, c+d);
		int all = abcd-bd-ac;
		int answer = (ac*padding) + bd + (all*padding2);
		return answer;
		}	
}
