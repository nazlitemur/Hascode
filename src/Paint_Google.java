import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class Paint_Google {

	// Variables

	// TODO Auto-generated constructor stub

	int n_warehouse;
	int n_order;
	int n_product;

	List<Drone> drones;
	List<Product> products;
	List<Warehouse> warehouse;
	List<Order> orders;
	static int coloumn = 0;
	static int row = 0;

	/*
	 * input parameters for my method will be: 1. list of warehouses represented
	 * by their coordinates and list of product. Product is represented by its
	 * ID and with the number of available count 2. list of orders where an
	 * order is represented by coordinates and list of ordered items
	 */

	public static class Order {
		int order_coordinate;
		int[] order_list;

		public Order(int order_coordinate) {

		}

	}

	public static class Drone {
		// n_ for total number
		int n_drone, n_turn;
		int max_payload;

		public Drone() {

		}

	}

	public static class Warehouse {
		// n_ for total number
		int n_warehouse_item;
		int[] warehouse_coordinate;
		int warehouse_prod_type;

		public Warehouse() {

		}
	}

	public static class Product {
		// n_ for total number
		int prod_id;
		int product_weight;
		int product_type;
		int n_product_type;
		int[] product_type_list;

	}

	public static Integer[][] create2DIntMatrixFromFile(String filename)
			throws Exception {
		// String[][] matrix = null;
		String line;
		int order_order_coordinate = 0;
		Drone drone = new Drone();
		Warehouse warehouse = new Warehouse();
		String[] split = null;
		Order order = new Order(order_order_coordinate);
		Product product = new Product();

		// If included in an Eclipse project.
		InputStream stream = ClassLoader.getSystemResourceAsStream(filename);
		// BufferedReader buffer = new BufferedReader(
		// new InputStreamReader(stream));

		// If in the same directory - Probably in your case...
		// Just comment out the 2 lines above this and uncomment the line
		// that follows.
		// BufferedReader buffer = new BufferedReader(new FileReader(filename));
		Scanner buffer = new Scanner(new FileReader(filename));
		String first_line;
		first_line = buffer.nextLine();

		// Lazy instantiation.
		for (int i = 0; i < first_line.length(); i++) {
			split = first_line.trim().split("\\s+");
			row = Integer.parseInt(split[0]);
			coloumn = Integer.parseInt(split[1]);
			drone.n_drone = Integer.parseInt(split[2]);
			drone.n_turn = Integer.parseInt(split[3]);
			drone.max_payload = Integer.parseInt(split[4]);

			// matrix = new Integer[row][col];

		}
		first_line = buffer.nextLine();
		for (int i = 0; i < first_line.length(); i++) {
			split = first_line.trim().split("\\s+");
			product.n_product_type = Integer.parseInt(split[0]);
		}

		Integer[][] matrix = new Integer[row][coloumn];
		int r = 0;
		int c = 0;

		// while ((line = buffer.nextLine()) != null) {
		while (buffer.hasNextLine()) {
			for (r = 0; r < row; r++) {
				for (c = 0; c < coloumn; c++) {
					matrix[r][c] = Integer.parseInt(split[r]);
					System.out.println(matrix[r][c]);
				}

			}

		}
		return matrix;
	}

	public static void main(String[] args) {
		Integer[][] matrix = null;

		try {
			matrix = create2DIntMatrixFromFile("/Users/nazlitemur/Documents/workspace/Hascode/busy_day.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
