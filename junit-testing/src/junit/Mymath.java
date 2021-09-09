package junit;

public class Mymath {
	
	public int sum(int[] arr) {
			int sum=0;
			for(int i : arr) {
				sum += i;
			}
			return sum;
	}
	
}
