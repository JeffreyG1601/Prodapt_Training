package D27p1;
import java.util.*;
public class D27j3 {
	public static void main(String[] args) {
		List<Integer> num = Arrays.asList(10,34,34,53,24,24,10098,93,92,92,10098);
		num.stream().forEach(System.out::println);
		System.out.println("==========EVEN=============");
		num.stream().filter(n->n%2==0).forEach(System.out::println);
		System.out.println("==========Sorted===========");
		num.stream().sorted().forEach(System.out::println);
		System.out.println("==========Distinct=========");
		num.stream().distinct().forEach(System.out::println);
		System.out.println("===========Everything=========");
		num.stream().filter(n->n%2==0)
		   .distinct()
		   .sorted(Comparator.reverseOrder())
		   .limit(4)
		   .skip(1)
		   .forEach(System.out::println);
	}
}
