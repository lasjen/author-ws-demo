/**
 * This class is generated by jOOQ
 */
package no.rightcloud.demo.author.jooq.tables;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import no.rightcloud.demo.author.jooq.Devdata;
import no.rightcloud.demo.author.jooq.Keys;
import no.rightcloud.demo.author.jooq.tables.records.PmtInfoRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
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
public class PmtInfo extends TableImpl<PmtInfoRecord> {

    private static final long serialVersionUID = 1673867486;

    /**
     * The reference instance of <code>DEVDATA.PMT_INFO</code>
     */
    public static final PmtInfo PMT_INFO = new PmtInfo();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PmtInfoRecord> getRecordType() {
        return PmtInfoRecord.class;
    }

    /**
     * The column <code>DEVDATA.PMT_INFO.PMT_INFO_ID</code>.
     */
    public final TableField<PmtInfoRecord, BigInteger> PMT_INFO_ID = createField("PMT_INFO_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(20).nullable(false), this, "");

    /**
     * The column <code>DEVDATA.PMT_INFO.FK_PMT_HEAD_ID</code>.
     */
    public final TableField<PmtInfoRecord, BigInteger> FK_PMT_HEAD_ID = createField("FK_PMT_HEAD_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(20), this, "");

    /**
     * The column <code>DEVDATA.PMT_INFO.PMT_METHOD</code>.
     */
    public final TableField<PmtInfoRecord, String> PMT_METHOD = createField("PMT_METHOD", org.jooq.impl.SQLDataType.VARCHAR.length(3), this, "");

    /**
     * The column <code>DEVDATA.PMT_INFO.BATCH_BOOKING</code>.
     */
    public final TableField<PmtInfoRecord, BigDecimal> BATCH_BOOKING = createField("BATCH_BOOKING", org.jooq.impl.SQLDataType.NUMERIC, this, "");

    /**
     * The column <code>DEVDATA.PMT_INFO.NR_OF_TRANS</code>.
     */
    public final TableField<PmtInfoRecord, BigDecimal> NR_OF_TRANS = createField("NR_OF_TRANS", org.jooq.impl.SQLDataType.NUMERIC, this, "");

    /**
     * The column <code>DEVDATA.PMT_INFO.CTRL_SUM</code>.
     */
    public final TableField<PmtInfoRecord, BigDecimal> CTRL_SUM = createField("CTRL_SUM", org.jooq.impl.SQLDataType.NUMERIC, this, "");

    /**
     * The column <code>DEVDATA.PMT_INFO.INSTR_PRTY</code>.
     */
    public final TableField<PmtInfoRecord, String> INSTR_PRTY = createField("INSTR_PRTY", org.jooq.impl.SQLDataType.VARCHAR.length(4), this, "");

    /**
     * The column <code>DEVDATA.PMT_INFO.REQD_EXCTN_DT</code>.
     */
    public final TableField<PmtInfoRecord, Date> REQD_EXCTN_DT = createField("REQD_EXCTN_DT", org.jooq.impl.SQLDataType.DATE, this, "");

    /**
     * The column <code>DEVDATA.PMT_INFO.DEBTOR_NAME</code>.
     */
    public final TableField<PmtInfoRecord, String> DEBTOR_NAME = createField("DEBTOR_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(70), this, "");

    /**
     * The column <code>DEVDATA.PMT_INFO.DBTR_POSTCODE</code>.
     */
    public final TableField<PmtInfoRecord, String> DBTR_POSTCODE = createField("DBTR_POSTCODE", org.jooq.impl.SQLDataType.VARCHAR.length(70), this, "");

    /**
     * The column <code>DEVDATA.PMT_INFO.DBTR_TOWNNAME</code>.
     */
    public final TableField<PmtInfoRecord, String> DBTR_TOWNNAME = createField("DBTR_TOWNNAME", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

    /**
     * The column <code>DEVDATA.PMT_INFO.DBTR_CTRY</code>.
     */
    public final TableField<PmtInfoRecord, String> DBTR_CTRY = createField("DBTR_CTRY", org.jooq.impl.SQLDataType.VARCHAR.length(3), this, "");

    /**
     * The column <code>DEVDATA.PMT_INFO.DBTR_POSTADR1</code>.
     */
    public final TableField<PmtInfoRecord, String> DBTR_POSTADR1 = createField("DBTR_POSTADR1", org.jooq.impl.SQLDataType.VARCHAR.length(70), this, "");

    /**
     * The column <code>DEVDATA.PMT_INFO.DBTR_POSTADR2</code>.
     */
    public final TableField<PmtInfoRecord, String> DBTR_POSTADR2 = createField("DBTR_POSTADR2", org.jooq.impl.SQLDataType.VARCHAR.length(70), this, "");

    /**
     * The column <code>DEVDATA.PMT_INFO.DBTR_ORG_ID</code>.
     */
    public final TableField<PmtInfoRecord, BigDecimal> DBTR_ORG_ID = createField("DBTR_ORG_ID", org.jooq.impl.SQLDataType.NUMERIC, this, "");

    /**
     * The column <code>DEVDATA.PMT_INFO.DBTR_ACCNT</code>.
     */
    public final TableField<PmtInfoRecord, String> DBTR_ACCNT = createField("DBTR_ACCNT", org.jooq.impl.SQLDataType.VARCHAR.length(35), this, "");

    /**
     * The column <code>DEVDATA.PMT_INFO.DBTR_ACCNT_CCY</code>.
     */
    public final TableField<PmtInfoRecord, String> DBTR_ACCNT_CCY = createField("DBTR_ACCNT_CCY", org.jooq.impl.SQLDataType.VARCHAR.length(3), this, "");

    /**
     * The column <code>DEVDATA.PMT_INFO.DBTR_AGENT_BIC</code>.
     */
    public final TableField<PmtInfoRecord, String> DBTR_AGENT_BIC = createField("DBTR_AGENT_BIC", org.jooq.impl.SQLDataType.VARCHAR.length(35), this, "");

    /**
     * Create a <code>DEVDATA.PMT_INFO</code> table reference
     */
    public PmtInfo() {
        this("PMT_INFO", null);
    }

    /**
     * Create an aliased <code>DEVDATA.PMT_INFO</code> table reference
     */
    public PmtInfo(String alias) {
        this(alias, PMT_INFO);
    }

    private PmtInfo(String alias, Table<PmtInfoRecord> aliased) {
        this(alias, aliased, null);
    }

    private PmtInfo(String alias, Table<PmtInfoRecord> aliased, Field<?>[] parameters) {
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
    public UniqueKey<PmtInfoRecord> getPrimaryKey() {
        return Keys.PMT_INFO_PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<PmtInfoRecord>> getKeys() {
        return Arrays.<UniqueKey<PmtInfoRecord>>asList(Keys.PMT_INFO_PK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<PmtInfoRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<PmtInfoRecord, ?>>asList(Keys.PMT_INFO_HEAD_FK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PmtInfo as(String alias) {
        return new PmtInfo(alias, this);
    }

    /**
     * Rename this table
     */
    public PmtInfo rename(String name) {
        return new PmtInfo(name, null);
    }
}
