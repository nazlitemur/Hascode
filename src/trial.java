import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class trial {
	public static String[][] create2DIntMatrixFromFile(String filename)
			throws Exception {
		// String[][] matrix = null;

		// If included in an Eclipse project.
		InputStream stream = ClassLoader.getSystemResourceAsStream(filename);
		// BufferedReader buffer = new BufferedReader(
		// new InputStreamReader(stream));

		// If in the same directory - Probably in your case...
		// Just comment out the 2 lines above this and uncomment the line
		// that follows.
		BufferedReader buffer = new BufferedReader(new FileReader(filename));

		String line;
		int row = 0;
		int col = 0;
		int size = 0;
		String first_line;
		first_line = buffer.readLine();
		String[] split = first_line.trim().split("\\s+");

		// Lazy instantiation.

		for (int i = 0; i < split.length; i++) {

			row = Integer.parseInt(split[0]);
			col = Integer.parseInt(split[1]);
			// matrix = new Integer[row][col];

		}
		String[][] matrix = new String[row][col];
		int C = 0;
		while ((line = buffer.readLine()) != null) {

			for (int R = 0; R < row; R++) {
				matrix[R][C] = line;
				// System.out.println(matrix[R][C]);
			}
			C++;
		}

		if (matrix != null) {
		String str="";	
			for (int r = 0; r < row; r++) {
				for (int c = 0; c < col; c++) {
					str += matrix[r][c];
					
					
					if (c==col) {
						str += "\n";
					}
				}

				if (r == row) {
					str += "\n";

				}
			}

			System.out.println(str);
		}

		return matrix;
	}



	public static void main(String[] args) {
		String[][] matrix = null;

		try {
			matrix = create2DIntMatrixFromFile("/Users/nazlitemur/Documents/workspace/Hascode/learn_and_teach.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
