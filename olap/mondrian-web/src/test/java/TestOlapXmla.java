import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.olap4j.Cell;
import org.olap4j.CellSet;
import org.olap4j.OlapConnection;
import org.olap4j.OlapStatement;
import org.olap4j.Position;
import org.olap4j.metadata.Member;

public class TestOlapXmla {
	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		// callschema();
		callxmla();

	}

	public static void callxmla() throws ClassNotFoundException, SQLException {
		Class.forName("org.olap4j.driver.xmla.XmlaOlap4jDriver");
		Connection connection = DriverManager
				.getConnection("jdbc:xmla:Server=http://localhost:8080/mondrian/xmla");
		OlapConnection olapConnection = connection.unwrap(OlapConnection.class);
		OlapStatement statement = olapConnection.createStatement();
		CellSet cellSet = statement
				.executeOlapQuery("SELECT {[Measures].[Sales Count]} ON COLUMNS, Hierarchize({[Product].[All Products]}) ON ROWS FROM [Sales 2]");

		for (Position row : cellSet.getAxes().get(1)) {
			for (Position column : cellSet.getAxes().get(0)) {
				for (Member member : row.getMembers()) {
					System.out.println("row members:" + member.getUniqueName());
				}
				for (Member member : column.getMembers()) {
					System.out.println("column members:"
							+ member.getUniqueName());
				}
				final Cell cell = cellSet.getCell(column, row);
				System.out.println("cell value:" + cell.getValue());
				System.out.println();
			}
		}
	}
}
