package com.airsupply.adapter.solr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;

import org.apache.calcite.adapter.java.JavaTypeFactory;
import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.util.Pair;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Enumerator that read the solr data.
 * 
 * @author arisupply
 *
 * @param <E>
 *            rowtype
 */
class SolrEnumerator<E> implements Enumerator<E> {
	private static String JSON_SUFFIX = ".json";
	private static String ip;
	private static int port;
	private static String coreName;
	SolrDocumentList solrDocumentList;
	private int index = 0;

	private final RowConverter<E> rowConverter;
	private E current;

	private static final FastDateFormat TIME_FORMAT_DATE;
	private static final FastDateFormat TIME_FORMAT_TIME;
	private static final FastDateFormat TIME_FORMAT_TIMESTAMP;

	static {
		TimeZone gmt = TimeZone.getTimeZone("GMT");
		TIME_FORMAT_DATE = FastDateFormat.getInstance("yyyy-MM-dd", gmt);
		TIME_FORMAT_TIME = FastDateFormat.getInstance("hh:mm:ss", gmt);
		TIME_FORMAT_TIMESTAMP = FastDateFormat.getInstance(
				"yyyy-MM-dd hh:mm:ss", gmt);
	}

	public SolrEnumerator(File file, List<SolrFieldType> fieldTypes) {
		this(file, fieldTypes, identityList(fieldTypes.size()));
	}

	@SuppressWarnings("unchecked")
	public SolrEnumerator(File file, List<SolrFieldType> fieldTypes,
			int[] fields) {
		this(file, null, (RowConverter<E>) converter(fieldTypes, fields));
	}

	public SolrEnumerator(File file, String[] filterValues,
			RowConverter<E> rowConverter) {
		this.rowConverter = rowConverter;
		String configFileName = file.getName().split("[.]")[0] + JSON_SUFFIX;
		String configFilePath = file.getParentFile().getAbsolutePath() + "/"
				+ configFileName;
		String connUrl = SolrAdapterUtil.getConnectUrl(configFilePath);
		SolrClient solrClient = SolrClientFactory.getSolrClient(connUrl);
		QueryResponse resp = null;
		try {
			resp = solrClient.query(new SolrQuery("*:*"));
			this.solrDocumentList = resp.getResults();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static RowConverter<?> converter(List<SolrFieldType> fieldTypes,
			int[] fields) {
		if (fields.length == 1) {
			final int field = fields[0];
			return new SingleColumnRowConverter(fieldTypes.get(field), field);
		} else {
			return new ArrayRowConverter(fieldTypes, fields);
		}
	}

	/**
	 * Deduces the names and types of a table's columns by reading the first
	 * line of a config csv file.
	 */
	static RelDataType deduceRowType(JavaTypeFactory typeFactory, File file,
			List<SolrFieldType> fieldTypes) {
		final List<RelDataType> types = new ArrayList<RelDataType>();
		final List<String> names = new ArrayList<String>();
		CSVReader reader = null;
		try {
			reader = openCsv(file);
			final String[] strings = reader.readNext();
			for (String string : strings) {
				final String name;
				final SolrFieldType fieldType;
				final int colon = string.indexOf(':');
				if (colon >= 0) {
					name = string.substring(0, colon);
					String typeString = string.substring(colon + 1);
					fieldType = SolrFieldType.of(typeString);
					if (fieldType == null) {
						System.out.println("WARNING: Found unknown type: "
								+ typeString + " in file: "
								+ file.getAbsolutePath() + " for column: "
								+ name
								+ ". Will assume the type of column is string");
					}
				} else {
					name = string;
					fieldType = null;
				}
				final RelDataType type;
				if (fieldType == null) {
					type = typeFactory.createJavaType(String.class);
				} else {
					type = fieldType.toType(typeFactory);
				}
				names.add(name);
				types.add(type);
				if (fieldTypes != null) {
					fieldTypes.add(fieldType);
				}
			}
		} catch (IOException e) {
			// ignore
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}
		if (names.isEmpty()) {
			names.add("line");
			types.add(typeFactory.createJavaType(String.class));
		}
		return typeFactory.createStructType(Pair.zip(names, types));
	}

	private static CSVReader openCsv(File file) throws IOException {
		final Reader fileReader;
		if (file.getName().endsWith(".gz")) {
			@SuppressWarnings("resource")
			final GZIPInputStream inputStream = new GZIPInputStream(
					new FileInputStream(file));
			fileReader = new InputStreamReader(inputStream);
		} else {
			fileReader = new FileReader(file);
		}
		return new CSVReader(fileReader);
	}

	public E current() {
		return current;
	}

	public boolean moveNext() {
		if (index >= solrDocumentList.size()) {
			return false;
		}
		SolrDocument solrDocument = solrDocumentList.get(index);
		Map<String, Object> fieldValueMap = solrDocument.getFieldValueMap();
		List<String> valueList = mapTransitionList(fieldValueMap);

		int size = valueList.size();
		String[] strings = valueList.toArray(new String[size]);
		current = rowConverter.convertRow(strings);
		// move to next index of the solrDocumentList
		index++;
		return true;

	}

	public List<String> mapTransitionList(Map<String, Object> map) {
		List<String> list = new ArrayList<String>();
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = it.next();
			list.add(String.valueOf(map.get(key)));
		}
		return list;
	}

	public void reset() {
		throw new UnsupportedOperationException();
	}

	public void close() {

	}

	/** Returns an array of integers {0, ..., n - 1}. */
	static int[] identityList(int n) {
		int[] integers = new int[n];
		for (int i = 0; i < n; i++) {
			integers[i] = i;
		}
		return integers;
	}

	/** Row converter. */
	abstract static class RowConverter<E> {
		abstract E convertRow(String[] rows);

		protected Object convert(SolrFieldType fieldType, String string) {
			if (fieldType == null) {
				return string;
			}
			switch (fieldType) {
			case BOOLEAN:
				if (string.length() == 0) {
					return null;
				}
				return Boolean.parseBoolean(string);
			case BYTE:
				if (string.length() == 0) {
					return null;
				}
				return Byte.parseByte(string);
			case SHORT:
				if (string.length() == 0) {
					return null;
				}
				return Short.parseShort(string);
			case INT:
				if (string.length() == 0) {
					return null;
				}
				return Integer.parseInt(string);
			case LONG:
				if (string.length() == 0) {
					return null;
				}
				return Long.parseLong(string);
			case FLOAT:
				if (string.length() == 0) {
					return null;
				}
				return Float.parseFloat(string);
			case DOUBLE:
				if (string.length() == 0) {
					return null;
				}
				return Double.parseDouble(string);
			case DATE:
				if (string.length() == 0) {
					return null;
				}
				try {
					Date date = TIME_FORMAT_DATE.parse(string);
					return new java.sql.Date(date.getTime());
				} catch (ParseException e) {
					return null;
				}
			case TIME:
				if (string.length() == 0) {
					return null;
				}
				try {
					Date date = TIME_FORMAT_TIME.parse(string);
					return new java.sql.Time(date.getTime());
				} catch (ParseException e) {
					return null;
				}
			case TIMESTAMP:
				if (string.length() == 0) {
					return null;
				}
				try {
					Date date = TIME_FORMAT_TIMESTAMP.parse(string);
					return new java.sql.Timestamp(date.getTime());
				} catch (ParseException e) {
					return null;
				}
			case STRING:
			default:
				return string;
			}
		}
	}

	/** Array row converter. */
	static class ArrayRowConverter extends RowConverter<Object[]> {

		private final SolrFieldType[] fieldTypes;
		private final int[] fields;

		ArrayRowConverter(List<SolrFieldType> fieldTypes, int[] fields) {

			this.fieldTypes = fieldTypes.toArray(new SolrFieldType[fieldTypes
					.size()]);
			this.fields = fields;
		}

		public Object[] convertRow(String[] strings) {
			final Object[] objects = new Object[fields.length];
			for (int i = 0; i < fields.length; i++) {
				int field = fields[i];
				objects[i] = convert(fieldTypes[field], strings[field]);
			}
			return objects;
		}
	}

	/** Single column row converter. */
	private static class SingleColumnRowConverter extends RowConverter<Object> {
		private final SolrFieldType fieldType;
		private final int fieldIndex;

		private SingleColumnRowConverter(SolrFieldType fieldType, int fieldIndex) {
			this.fieldType = fieldType;
			this.fieldIndex = fieldIndex;
		}

		public Object convertRow(String[] strings) {
			return convert(fieldType, strings[fieldIndex]);
		}
	}
}
