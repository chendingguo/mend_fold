package com.airsupply.adapter.hbase;

import java.io.File;
import java.util.Map;

import org.apache.calcite.schema.Table;
import org.apache.calcite.schema.impl.AbstractSchema;

import com.google.common.collect.ImmutableMap;

/**
 * Schema mapped onto a directory of Hbase files.
 */
public class HBaseSchema extends AbstractSchema {
	final File directoryFile;
	private final HBaseTable.Flavor flavor;

	/**
	 * Creates a Hbase schema.
	 *
	 * @param directoryFile
	 *            Directory that holds table describe files
	 * @param flavor
	 *            Whether to instantiate flavor tables that undergo query
	 *            optimization
	 */
	public HBaseSchema(File directoryFile, HBaseTable.Flavor flavor) {
		super();
		this.directoryFile = directoryFile;
		this.flavor = flavor;
	}

	/**
	 * Looks for a suffix on a string and returns either the string with the
	 * suffix removed or the original string.
	 */
	private static String trim(String s, String suffix) {
		String trimmed = trimOrNull(s, suffix);
		return trimmed != null ? trimmed : s;
	}

	/**
	 * Looks for a suffix on a string and returns either the string with the
	 * suffix removed or null.
	 */
	private static String trimOrNull(String s, String suffix) {
		return s.endsWith(suffix) ? s
				.substring(0, s.length() - suffix.length()) : null;
	}

	@Override
	protected Map<String, Table> getTableMap() {

		File[] files = directoryFile.listFiles();

		if (files == null) {
			System.out.println("directory " + directoryFile + " not found");
			files = new File[0];
		}
		// Build a map from table name to table; each file becomes a table.
		final ImmutableMap.Builder<String, Table> builder = ImmutableMap
				.builder();
		for (File file : files) {

			String tableName = trim(file.getName(), ".json");
			final Table table = createTable(file);
			builder.put(tableName, table);
		}
		return builder.build();
	}

	/** Creates different sub-type of table based on the "flavor" attribute. */
	private Table createTable(File file) {
		switch (flavor) {

		case SCANNABLE:
			return new HBaseScannableTable(file, null);

		default:
			throw new AssertionError("Unknown flavor " + flavor);
		}
	}
}
