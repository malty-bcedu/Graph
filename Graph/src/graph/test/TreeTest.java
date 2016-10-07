package graph.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import graph.Tree;
import graph.Vertex;
import graph.exception.InvalidConstructionException;

public class TreeTest {

	public static void main(String[] args) {

		String fileName = null;
		Tree<Integer> tree = null;
		BufferedReader br = null;
		Scanner kb = new Scanner(System.in);

		while(true) {

			while (tree == null) {
				/* Get an file name from the user */
				System.out.print("Enter file name: ");
				fileName = kb.nextLine();
				
				try {
					/* Open file */
					br = new BufferedReader(new FileReader(fileName));

					String line = null;
					String[] tokens = null;
					int first;
					int second;

					/* Read in the first line so we can parse the root node */
					line = br.readLine();
					if (line == null) {
						System.out.println("Input file empty");
						br.close();
						tree = null;
						continue;
					}

					tokens = line.split(" ");
					if (tokens.length == 0) {
						System.out.println("Invalid file format");
						br.close();
						tree = null;
						continue;
					}

					/* The first token is the root */
					first = Integer.parseInt(tokens[0]);
					tree = new Tree<Integer>(first);

					/* Get the second token, if any, and construct an edge */
					if (tokens.length == 2) {
						second = Integer.parseInt(tokens[1]);
						tree.addEdge(first, second);
					}

					/* Process the rest of the file */
					while ((line = br.readLine()) != null) {
						tokens = line.split(" ");
						if (tokens.length != 2) {
							System.out.println("Invalid file format");
							br.close();
							tree = null;
							break;
							
						}
						first = Integer.parseInt(tokens[0]);
						second = Integer.parseInt(tokens[1]);

						tree.addEdge(first, second);
					} 
					br.close();
				}	
				catch (InvalidConstructionException e) {
					System.out.println(e.toString());
					tree = null;
					try {
						br.close();
					} catch (IOException e1) {
						System.out.println("Error closing file");
					}
				} catch (NoSuchElementException e) {
					System.out.println("Invalid file format");
					tree = null;
					continue;
				} catch (NumberFormatException e) {
					System.out.println("Invalid File format");
					tree = null;
					continue;
				} catch (FileNotFoundException e) {
					System.out.println("File not found");
					tree = null;
					continue;
				} catch (IOException e) {
					System.out.println("Error reading file");
					tree = null;
					continue;
				}
			}

			tree.print();
			System.out.println();

			/* Get an integer from the user */
			int searchValue;

			while (true) {
				System.out.print("Enter integer to search for: ");
				if (!kb.hasNextInt()) {
					kb.nextLine();
					continue;
				}
				searchValue = kb.nextInt();
				kb.nextLine();
				break;
			}

			/* Find the vertex containing the integer */
			Vertex<Integer> v = null;
			v = tree.DFSearch(tree.getRoot(), searchValue);
			

			if (v == null) {
				System.out.println("Element not found");
			}
			else {
				System.out.println("Element found");
			}

			System.out.println("Press 'e' to exit or enter to continue: ");
			String input = kb.nextLine();
			if (input.equals("e"))
				break;
		}
		kb.close();
	}
} 
// End of file
