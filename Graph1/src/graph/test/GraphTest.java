package graph.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import graph.Graph;
import graph.Vertex;
import graph.exception.InvalidConstructionException;

public class GraphTest {

	public static void main(String[] args) {

		String fileName = null;
		Graph<Integer> graph = null;
		BufferedReader br = null;
		Scanner kb = new Scanner(System.in);

		while(true) {

			while (graph == null) {
				try {
					/* Get an file name from the user */
					System.out.print("Enter file name: ");
					fileName = kb.nextLine();

					/* Open file */
					br = new BufferedReader(new FileReader(fileName));

					String line = null;
					String[] tokens = null;
					int first;
					int second;

					/* Create graph */
					graph = new Graph<Integer>();

					/* Process the file */
					while ((line = br.readLine()) != null) {
						tokens = line.split(" ");
						if (tokens.length != 2) {
							System.out.println("Invalid file format");
							br.close();
							graph = null;
							break;
						}
						
						first = Integer.parseInt(tokens[0]);
						second = Integer.parseInt(tokens[1]);

						System.out.println("Adding: (" + first + "," + second + ")");
						graph.addEdge(first, second);
					} 
					br.close();
				}	
				catch (InvalidConstructionException e) {
					System.out.println(e.toString());
					graph = null;
					try {
						br.close();
					} catch (IOException e1) {
						System.out.println("Error closing file");
					}
				} catch (NoSuchElementException e) {
					System.out.println("Invalid file format");
					graph = null;
					continue;
				} catch (NumberFormatException e) {
					System.out.println("Invalid file format");
					graph = null;
					continue;
				} catch (FileNotFoundException e) {
					System.out.println("File not found");
					graph = null;
					continue;
				} catch (IOException e) {
					System.out.println("Error reading file");
					graph = null;
					continue;
				}
			}

			graph.print();
			System.out.println();

			/* Get value for search's start node */
			int startValue;
			Vertex<Integer> startVertex = null;
			while (true) {
				System.out.println("Enter integer indicating where to start the search: ");
				if (!kb.hasNextInt()) {
					kb.nextLine();
					continue;
				}
				startValue = kb.nextInt();
				kb.nextLine();

				startVertex = graph.findVertex(startValue);
				if (startVertex == null) {
					System.out.println("Vertex does not exist");
					continue;
				}
				break;
			}

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
			v = graph.DFSearch(startVertex, searchValue);
			//v1 = graph.BFSearch(startVertex, searchValue);
			
			if (v == null) {
				System.out.println("Element not found");
			}
			else {
				System.out.println("Element exists");
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

