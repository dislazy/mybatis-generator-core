/**
 *    Copyright 2006-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.internal.types;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.JavaTypeResolver;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * 
 * @author Jeff Butler
 */
public class JavaTypeResolverDefaultImpl implements JavaTypeResolver {

    protected List<String> warnings;

    protected Properties properties;

    protected Context context;

    protected boolean forceBigDecimals;

    protected Map<Integer, JdbcTypeInformation> typeMap;
    
    public JavaTypeResolverDefaultImpl() {
        super();
        properties = new Properties();
        typeMap = new HashMap<Integer, JdbcTypeInformation>();

        typeMap.put(Types.ARRAY, new JdbcTypeInformation("ARRAY",
                new FullyQualifiedJavaType(Object.class.getName())));
        typeMap.put(Types.BIGINT, new JdbcTypeInformation("BIGINT",
                new FullyQualifiedJavaType(Long.class.getName())));
        typeMap.put(Types.BINARY, new JdbcTypeInformation("BINARY",
                new FullyQualifiedJavaType("byte[]")));
        typeMap.put(Types.BIT, new JdbcTypeInformation("BIT",
                new FullyQualifiedJavaType(Boolean.class.getName())));
        typeMap.put(Types.BLOB, new JdbcTypeInformation("BLOB",
                new FullyQualifiedJavaType("byte[]")));
        typeMap.put(Types.BOOLEAN, new JdbcTypeInformation("BOOLEAN",
                new FullyQualifiedJavaType(Boolean.class.getName())));
        typeMap.put(Types.CHAR, new JdbcTypeInformation("CHAR",
                new FullyQualifiedJavaType(String.class.getName())));
        typeMap.put(Types.CLOB, new JdbcTypeInformation("CLOB",
                new FullyQualifiedJavaType(String.class.getName())));
        typeMap.put(Types.DATALINK, new JdbcTypeInformation("DATALINK",
                new FullyQualifiedJavaType(Object.class.getName())));
        typeMap.put(Types.DATE, new JdbcTypeInformation("DATE",
                new FullyQualifiedJavaType(LocalDate.class.getName())));
        typeMap.put(Types.DISTINCT, new JdbcTypeInformation("DISTINCT",
                new FullyQualifiedJavaType(Object.class.getName())));
        typeMap.put(Types.DOUBLE, new JdbcTypeInformation("DOUBLE",
                new FullyQualifiedJavaType(Double.class.getName())));
        typeMap.put(Types.FLOAT, new JdbcTypeInformation("FLOAT",
                new FullyQualifiedJavaType(Double.class.getName())));
        typeMap.put(Types.INTEGER, new JdbcTypeInformation("INTEGER",
                new FullyQualifiedJavaType(Integer.class.getName())));
        typeMap.put(Types.JAVA_OBJECT, new JdbcTypeInformation("JAVA_OBJECT",
                new FullyQualifiedJavaType(Object.class.getName())));
        typeMap.put(Types.LONGNVARCHAR, new JdbcTypeInformation("LONGNVARCHAR",
                new FullyQualifiedJavaType(String.class.getName())));
        typeMap.put(Types.LONGVARBINARY, new JdbcTypeInformation(
                "LONGVARBINARY",
                new FullyQualifiedJavaType("byte[]")));
        typeMap.put(Types.LONGVARCHAR, new JdbcTypeInformation("LONGVARCHAR",
                new FullyQualifiedJavaType(String.class.getName())));
        typeMap.put(Types.NCHAR, new JdbcTypeInformation("NCHAR",
                new FullyQualifiedJavaType(String.class.getName())));
        typeMap.put(Types.NCLOB, new JdbcTypeInformation("NCLOB",
                new FullyQualifiedJavaType(String.class.getName())));
        typeMap.put(Types.NVARCHAR, new JdbcTypeInformation("NVARCHAR",
                new FullyQualifiedJavaType(String.class.getName())));
        typeMap.put(Types.NULL, new JdbcTypeInformation("NULL",
                new FullyQualifiedJavaType(Object.class.getName())));
        typeMap.put(Types.OTHER, new JdbcTypeInformation("OTHER",
                new FullyQualifiedJavaType(Object.class.getName())));
        typeMap.put(Types.REAL, new JdbcTypeInformation("REAL",
                new FullyQualifiedJavaType(Float.class.getName())));
        typeMap.put(Types.REF, new JdbcTypeInformation("REF",
                new FullyQualifiedJavaType(Object.class.getName())));
        typeMap.put(Types.SMALLINT, new JdbcTypeInformation("SMALLINT",
                new FullyQualifiedJavaType(Integer.class.getName())));
        typeMap.put(Types.STRUCT, new JdbcTypeInformation("STRUCT",
                new FullyQualifiedJavaType(Object.class.getName())));
        typeMap.put(Types.TIME, new JdbcTypeInformation("TIME",
                new FullyQualifiedJavaType(LocalTime.class.getName())));
        typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("TIMESTAMP",
                new FullyQualifiedJavaType(LocalDateTime.class.getName())));
        typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT",
                new FullyQualifiedJavaType(Integer.class.getName())));
        typeMap.put(Types.VARBINARY, new JdbcTypeInformation("VARBINARY",
                new FullyQualifiedJavaType("byte[]")));
        typeMap.put(Types.VARCHAR, new JdbcTypeInformation("VARCHAR",
                new FullyQualifiedJavaType(String.class.getName())));
        
    }
    @Override
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
        forceBigDecimals = StringUtility
                .isTrue(properties
                        .getProperty(PropertyRegistry.TYPE_RESOLVER_FORCE_BIG_DECIMALS));
    }
    @Override
    public FullyQualifiedJavaType calculateJavaType(
            IntrospectedColumn introspectedColumn) {
        FullyQualifiedJavaType answer;
        JdbcTypeInformation jdbcTypeInformation = typeMap
                .get(introspectedColumn.getJdbcType());

        if (jdbcTypeInformation == null) {
            switch (introspectedColumn.getJdbcType()) {
            case Types.DECIMAL:
            case Types.NUMERIC:
                if (introspectedColumn.getScale() > 0
                        || introspectedColumn.getLength() > 18
                        || forceBigDecimals) {
                    answer = new FullyQualifiedJavaType(BigDecimal.class
                            .getName());
                } else if (introspectedColumn.getLength() > 9) {
                    answer = new FullyQualifiedJavaType(Long.class.getName());
                } else if (introspectedColumn.getLength() > 4) {
                    answer = new FullyQualifiedJavaType(Integer.class.getName());
                } else {
                    answer = new FullyQualifiedJavaType(Short.class.getName());
                }
                break;

            default:
                answer = null;
                break;
            }
        } else {
            answer = jdbcTypeInformation.getFullyQualifiedJavaType();
        }

        return answer;
    }
    @Override
    public String calculateJdbcTypeName(IntrospectedColumn introspectedColumn) {
        String answer;
        JdbcTypeInformation jdbcTypeInformation = typeMap
                .get(introspectedColumn.getJdbcType());

        if (jdbcTypeInformation == null) {
            switch (introspectedColumn.getJdbcType()) {
            case Types.DECIMAL:
                answer = "DECIMAL";
                break;
            case Types.NUMERIC:
                answer = "NUMERIC";
                break;
            default:
                answer = null;
                break;
            }
        } else {
            answer = jdbcTypeInformation.getJdbcTypeName();
        }

        return answer;
    }
    @Override
    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }
    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    public static class JdbcTypeInformation {
        private String jdbcTypeName;

        private FullyQualifiedJavaType fullyQualifiedJavaType;

        public JdbcTypeInformation(String jdbcTypeName,
                FullyQualifiedJavaType fullyQualifiedJavaType) {
            this.jdbcTypeName = jdbcTypeName;
            this.fullyQualifiedJavaType = fullyQualifiedJavaType;
        }

        private String getJdbcTypeName() {
            return jdbcTypeName;
        }

        public FullyQualifiedJavaType getFullyQualifiedJavaType() {
            return fullyQualifiedJavaType;
        }
    }
}
