package filesEx1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ArrayListProgram2 {
	public static void main(String[] args) {

		ArrayList<Integer> intList = new ArrayList<Integer>();

		while (true) {
			Scanner scan = new Scanner(System.in) ;
			try {
				instructDisp();
				int input = scan.nextInt();
				int sum = 0;
				switch (input) {
				case 1:
					System.out.println("Enter a number you wish to add to the list");
					intadd(scan.nextInt(), intList);
					break;
				case 2:
					System.out.println("The list is: \n" + intList);
					break;
				case 3:
					saveList(intList);
					break;
				case 4:
					intList = loadList(intList);
					break;
				case 5:
					for (Integer num : intList) {
						sum += num;
					}
					System.out.println("The sum of the memebers: " + sum);
					break;
				case 6:
					if (intList.size() != 0) {
						for (Integer num : intList) {
							sum += num;
						}
						System.out.println("The average value of the members: " + (double) sum / intList.size());
					} else {
						System.out.println("You never entered numbers into the list, dividing by zero is not allowed!");
					}
					break;
				case 7:
					int max = intList.get(0);
					for (Integer num : intList) {
						if (num > max) {
							max = num;
						}
					}
					System.out.println("The biggest number is: " + max);
					break;
				case 8:
					int min = intList.get(0);
					for (Integer num : intList) {
						if (num < min) {
							min = num;
						}
					}
					System.out.println("The smallest number is: " + min);
					break;

				case 9:
					scan.close();
					System.out.println("The program operation has ended.");
					return;
				}
			} catch (InputMismatchException e) {
				System.out.println("Input invalid: " + e.getMessage());
			} catch (IndexOutOfBoundsException e) {
				System.out.println("List is empty: " + e.getMessage());
			} 
		}
	}

	public static void instructDisp() {
		System.out.println("Enter 1 to add a number to the list.");
		System.out.println("Enter 2 to display the list.");
		System.out.println("Enter 3 to save the list.");
		System.out.println("Enter 4 to load saved list.");
		System.out.println("Enter 5 to display the sum of the memebers of the list.");
		System.out.println("Enter 6 to display the average value of the members of the list.");
		System.out.println("Enter 7 to display the largest number in the list.");
		System.out.println("Enter 8 to display the smallest number in the list.");
		System.out.println("Enter 9 to end the program.");
	}

	public static void intadd(int num, ArrayList<Integer> arr) {
		arr.add(num);
	}

	public static void saveList(ArrayList<Integer> arr) {
		try (FileOutputStream fos = new FileOutputStream("ArrayListProgram.txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(arr);
			System.out.println("List was saved");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static ArrayList<Integer> loadList(ArrayList<Integer> arr) {
		try (FileInputStream fis = new FileInputStream("ArrayListProgram.txt");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			arr = (ArrayList<Integer>) ois.readObject();
			System.out.println("You loaded this list: \n" + arr);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return arr;
	}
}