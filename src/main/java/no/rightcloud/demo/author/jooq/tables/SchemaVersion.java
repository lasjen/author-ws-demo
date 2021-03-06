/**
 * This class is generated by jOOQ
 */
package no.rightcloud.demo.author.jooq.tables;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import no.rightcloud.demo.author.jooq.Devdata;
import no.rightcloud.demo.author.jooq.Keys;
import no.rightcloud.demo.author.jooq.tables.records.SchemaVersionRecord;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SchemaVersion extends TableImpl<SchemaVersionRecord> {

    private static final long serialVersionUID = -1007696755;

    /**
     * The reference instance of <code>DEVDATA.SCHEMA_VERSION</code>
     */
    public static final SchemaVersion SCHEMA_VERSION = new SchemaVersion();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SchemaVersionRecord> getRecordType() {
        return SchemaVersionRecord.class;
    }

    /**
     * The column <code>DEVDATA.SCHEMA_VERSION.installed_rank</code>.
     */
    public final TableField<SchemaVersionRecord, BigDecimal> INSTALLED_RANK = createField("installed_rank", org.jooq.impl.SQLDataType.NUMERIC.nullable(false), this, "");

    /**
     * The column <code>DEVDATA.SCHEMA_VERSION.version</code>.
     */
    public final TableField<SchemaVersionRecord, String> VERSION = createField("version", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

    /**
     * The column <code>DEVDATA.SCHEMA_VERSION.description</code>.
     */
    public final TableField<SchemaVersionRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR.length(200).nullable(false), this, "");

    /**
     * The column <code>DEVDATA.SCHEMA_VERSION.type</code>.
     */
    public final TableField<SchemaVersionRecord, String> TYPE = createField("type", org.jooq.impl.SQLDataType.VARCHAR.length(20).nullable(false), this, "");

    /**
     * The column <code>DEVDATA.SCHEMA_VERSION.script</code>.
     */
    public final TableField<SchemaVersionRecord, String> SCRIPT = createField("script", org.jooq.impl.SQLDataType.VARCHAR.length(1000).nullable(false), this, "");

    /**
     * The column <code>DEVDATA.SCHEMA_VERSION.checksum</code>.
     */
    public final TableField<SchemaVersionRecord, BigDecimal> CHECKSUM = createField("checksum", org.jooq.impl.SQLDataType.NUMERIC, this, "");

    /**
     * The column <code>DEVDATA.SCHEMA_VERSION.installed_by</code>.
     */
    public final TableField<SchemaVersionRecord, String> INSTALLED_BY = createField("installed_by", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

    /**
     * The column <code>DEVDATA.SCHEMA_VERSION.installed_on</code>.
     */
    public final TableField<SchemaVersionRecord, Timestamp> INSTALLED_ON = createField("installed_on", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP ", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>DEVDATA.SCHEMA_VERSION.execution_time</code>.
     */
    public final TableField<SchemaVersionRecord, BigDecimal> EXECUTION_TIME = createField("execution_time", org.jooq.impl.SQLDataType.NUMERIC.nullable(false), this, "");

    /**
     * The column <code>DEVDATA.SCHEMA_VERSION.success</code>.
     */
    public final TableField<SchemaVersionRecord, Byte> SUCCESS = createField("success", org.jooq.impl.SQLDataType.TINYINT.nullable(false), this, "");

    /**
     * Create a <code>DEVDATA.SCHEMA_VERSION</code> table reference
     */
    public SchemaVersion() {
        this("SCHEMA_VERSION", null);
    }

    /**
     * Create an aliased <code>DEVDATA.SCHEMA_VERSION</code> table reference
     */
    public SchemaVersion(String alias) {
        this(alias, SCHEMA_VERSION);
    }

    private SchemaVersion(String alias, Table<SchemaVersionRecord> aliased) {
        this(alias, aliased, null);
    }

    private SchemaVersion(String alias, Table<SchemaVersionRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Devdata.DEVDATA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<SchemaVersionRecord> getPrimaryKey() {
        return Keys.SCHEMA_VERSION_PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<SchemaVersionRecord>> getKeys() {
        return Arrays.<UniqueKey<SchemaVersionRecord>>asList(Keys.SCHEMA_VERSION_PK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchemaVersion as(String alias) {
        return new SchemaVersion(alias, this);
    }

    /**
     * Rename this table
     */
    public SchemaVersion rename(String name) {
        return new SchemaVersion(name, null);
    }
}
