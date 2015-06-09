/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.airsupply.adapter.solr;

import org.apache.calcite.model.ModelHandler;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaFactory;
import org.apache.calcite.schema.SchemaPlus;

import java.io.File;
import java.util.Map;

/**
 * Factory that creates a {@link SolrSchema}.
 *
 * <p>
 * Allows a custom schema to be included in a <code><i>model</i>.json</code>
 * file.
 * </p>
 */
public class SolrSchemaFactory implements SchemaFactory {
	// public constructor, per factory contract
	public SolrSchemaFactory() {
	}

	public Schema create(SchemaPlus parentSchema, String name,
			Map<String, Object> operand) {
		final String directory = (String) operand.get("directory");
		final File base = (File) operand
				.get(ModelHandler.ExtraOperand.BASE_DIRECTORY.camelName);
		File directoryFile = new File(directory);
		if (base != null && !directoryFile.isAbsolute()) {
			directoryFile = new File(base, directory);
		}
		String flavorName = (String) operand.get("flavor");
		SolrTable.Flavor flavor;
		if (flavorName == null) {
			flavor = SolrTable.Flavor.SCANNABLE;
		} else {
			flavor = SolrTable.Flavor.valueOf(flavorName.toUpperCase());
		}
		return new SolrSchema(directoryFile, flavor);
	}
}

// End CsvSchemaFactory.java
