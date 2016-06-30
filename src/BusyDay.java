import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/* REQUIREMENTS
 100 100 3 50 500    --    100 rows, 100 columns, 3 drones, 50 turns, max payload is 500u
 3                   --    There are 3 different product types.
 100 5 450           --    The product types weigh: 100u, 5u, 450u
 2                   --    There are 2 warehouses.
 0 0                 --    First warehouse is located at [0, 0].
 5 1 0               --    It stores 5 items of product 0 and 1 of product 1.
 5 5                 --    Second warehouse is located at [5, 5].
 0 10 2              --    It stores 10 items of product 1 and 2 items of product 2.
 3                   --    There are 3 orders.
 1 1                 --    First order to be delivered to [1, 1].
 2                   --    First order contains 2 items.
 2 0                 --    Items of product types: 2, 0.  
 3 3                 --    Second order to be delivered to [3, 3].
 3                   --    Second order contains 3 items.
 0 0 0               --    Items of product types: 0, 0, 0.
 5 6                 --    Third order to be delivered to [5, 6] 
 1                   --    Third order contains 1 item.
 2                   --    Items of product types: 2. */

public class BusyDay {

	// Variables

	public BusyDay() {
		super();
		// TODO Auto-generated constructor stub
	}

	int n_warehouse;
	int n_order;
	int n_product;

	static List<Drone> drones;
	static List<Product> products;
	// static List<Product> warehouse_products;

	static List<Warehouse> warehouses;
	static List<Order> orders;
	static int coloumn = 0;
	static int row = 0;
	static Map<Warehouse, Product> map = new HashMap<Warehouse, Product>();
	static Map<Integer, Integer> map_w_product = new HashMap<Integer, Integer>();
	static Map<Integer, String> map_order_product = new HashMap<Integer, String>();

	/*
	 * input parameters for my method will be: 1. list of warehouses represented
	 * by their coordinates and list of product. Product is represented by its
	 * ID and with the number of available count 2. list of orders where an
	 * order is represented by coordinates and list of ordered items
	 */

	public static class Order {
		int[] delivery_coordinate;
		int[] order_product_type_list;
		int product_type;
		int n_order;
		int n_total_order;
		int n_nessesary_product;
		int n_unnessesary_product;
		int n_total_item;
		String order_name;

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

		int[] warehouse_coordinate;
		int n_warehouse;
		String warehouse_name;

		int missing_n_product_types = 0;
		int available_pT_items = 0;
		int available_n_product_types = 0;
		int n_total_warehouse_item;
		int[] stock_distribution;

		public Warehouse() {

		}
	}

	public static class Product {
		// n_ for total number
		int prod_id;
		int product_weight;
		String product_type;
		int n_total_product_type;

	}

	public static Integer[][] create2DIntMatrixFromFile(String filename)
			throws Exception {
		// String[][] matrix = null;
		String line;
		int total_product = 0;
		int total_ordered_item = 0;
		int order_order_coordinate = 0;
		Drone drone = new Drone();
		Warehouse warehouse = new Warehouse();
		String[] split = null;
		Order order = new Order(order_order_coordinate);
		Product product = new Product();
		// Product product_warehouse = new Product();
		products = new LinkedList<>();
		warehouses = new LinkedList<>();
		orders = new LinkedList<>();
		drones = new LinkedList<>();

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
		split = first_line.trim().split("\\s+");
		for (int i = 0; i < split.length; i++) {

			row = Integer.parseInt(split[0]);
			coloumn = Integer.parseInt(split[1]);
			drone.n_drone = Integer.parseInt(split[2]);
			drone.n_turn = Integer.parseInt(split[3]);
			drone.max_payload = Integer.parseInt(split[4]);
			System.out.printf("Drone Info Part :%n");
			System.out.println(split.length);

			// Integer[][] matrix_Drone = new Integer[row][coloumn];
			// System.out.println(matrix_Drone[row][coloumn]);

		}
		// Receive Total Number of Product_Types

		line = buffer.nextLine();
		split = line.trim().split("\\s+");

		product.n_total_product_type = Integer.parseInt(split[0]);
		// System.out.printf("Number of Product Part %n :");
		// System.out.println(split.length);

		String line2 = buffer.nextLine();
		split = line2.trim().split("\\s+");

		// Receive Product Details

		for (int j = 0; j < split.length; j++) {
			product.product_type = "Type" + j;
			product.product_weight = Integer.parseInt(split[j]);
			products.add(product);
			System.out.printf("---------------------------%n");
			System.out.printf("Total Number of Product :");
			System.out.println(product.n_total_product_type);
			System.out.printf("Size of Products list  :");
			System.out.println(products.size());
			System.out.printf("Product Type  :");
			System.out.println(product.product_type);
			System.out.printf("Product Weight  :");
			System.out.println(product.product_weight);

		}

		// Receive Total Number of Warehouse

		line = buffer.nextLine();
		split = line.trim().split("\\s+");
		warehouse.n_warehouse = Integer.parseInt(split[0]);
		System.out.printf("---------------------------%n");
		System.out.printf("Split Item :");
		System.out.println(split.length);
		System.out.printf("Total Number of Warehouse:");
		System.out.println(warehouse.n_warehouse);

		// Receive Warehouse Details [get the depth and repeat parsing of n node
		// depth, n times.]

		for (int depth = 0; depth < warehouse.n_warehouse; depth++) {

			line = buffer.nextLine();
			split = line.trim().split("\\s+");
			// Gather warehouse coordinate j
			// for (int i = 0; i < split.length; i++) {
			int[] values = new int[] { Integer.parseInt(split[0]),
					Integer.parseInt(split[1]) };
			warehouse.warehouse_coordinate = values;

			warehouse.warehouse_name = "W[" + values[0] + " " + values[1] + "]";
			System.out.printf("------------------------%n");
			System.out.printf("Warehouse Coordinate :");
			System.out.println(warehouse.warehouse_name);
			System.out.printf("Warehouse Coordinate Part :");
			System.out.println(split.length);

			// }
			// gather the number of product in a sequential order, x items of
			// product 0 , y items of product 1 , ... so on

			line = buffer.nextLine();
			split = line.trim().split("\\s+");
			System.out.printf("Total product type :");
			System.out.println(split.length);

			// here , I should calculate number of all products which have more
			// than 0 number of element with respect to product type/
			// warehouse.n_warehouse_prod_type= split.length;

			for (int k = 0; k < split.length; k++) {

				map_w_product.put(k, Integer.parseInt(split[k]));

				total_product += Integer.parseInt(split[k]);

				if (Integer.parseInt(split[k]) == 0) {

					warehouse.missing_n_product_types += 1;

				} else {
					warehouse.available_pT_items += Integer.parseInt(split[k]);
					warehouse.available_n_product_types += 1;

				}

			}

			warehouse.n_total_warehouse_item = total_product;
			warehouses.add(warehouse);
			System.out.printf("Total number of item :");
			System.out.println(warehouse.n_total_warehouse_item);
			System.out.printf("Available Product Type :");
			System.out.println(warehouse.available_n_product_types);
			System.out.printf("Available Item :");
			System.out.println(warehouse.available_pT_items);
			System.out.printf("Unavailable Product type :");
			System.out.println(warehouse.missing_n_product_types);
			/*
			 * for (Integer key : map_w_product.keySet()) {
			 * 
			 * System.out.println("Key: " + key + ", Value: " +
			 * map_w_product.get(key)); }
			 */

		}
		// Gather number of total order information
		line = buffer.nextLine();
		split = line.trim().split("\\s+");
		order.n_total_order = Integer.parseInt(split[0]);

		for (int depth = 0; depth < order.n_total_order; depth++) {
			// Gather delivery location information
			line = buffer.nextLine();
			split = line.trim().split("\\s+");

			int[] coord_values = new int[] { Integer.parseInt(split[0]),
					Integer.parseInt(split[1]) };
			order.delivery_coordinate = coord_values;
			order.order_name = "Ord[" + coord_values[0] + " " + coord_values[1]
					+ "]";

			// Gather product list as a form of order

			line = buffer.nextLine();
			split = line.trim().split("//s+");
			order.n_total_item = Integer.parseInt(split[0]);
			System.out.printf("*********%n");
			System.out.println(split.length);
			System.out.printf("total item :");
			System.out.println(order.n_total_item);

			line = buffer.nextLine();
			split = line.trim().split("//s+");

			for (int i = 0; i < order.n_total_item; i++) {

				map_order_product.put(0, line);
				// order.order_product_type_list =

			}

			orders.add(order);

			System.out.println("---------------------");
			System.out.printf("Total Number of Order :");
			System.out.println(order.n_total_order);
			System.out.printf("Order Delivery Coordinate :");
			System.out.println(order.order_name);
			System.out.printf("Total Item :");
			System.out.println(order.n_total_item);
			System.out.printf("Product List :");
			System.out.println(map_order_product.values().toString());

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