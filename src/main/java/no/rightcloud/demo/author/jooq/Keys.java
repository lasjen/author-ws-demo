/**
 * This class is generated by jOOQ
 */
package no.rightcloud.demo.author.jooq;


import javax.annotation.Generated;

import no.rightcloud.demo.author.jooq.tables.PmtInfo;
import no.rightcloud.demo.author.jooq.tables.records.PmtInfoRecord;
import no.rightcloud.demo.author.jooq.tables.records.PmtInstRecord;
import no.rightcloud.demo.author.jooq.tables.PmtHead;
import no.rightcloud.demo.author.jooq.tables.PmtInst;
import no.rightcloud.demo.author.jooq.tables.SchemaVersion;
import no.rightcloud.demo.author.jooq.tables.records.PmtHeadRecord;
import no.rightcloud.demo.author.jooq.tables.records.SchemaVersionRecord;

import org.jooq.ForeignKey;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling foreign key relationships between tables of the <code>DEVDATA</code> 
 * schema
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<PmtHeadRecord> PMT_HEAD_PK = UniqueKeys0.PMT_HEAD_PK;
    public static final UniqueKey<PmtInfoRecord> PMT_INFO_PK = UniqueKeys0.PMT_INFO_PK;
    public static final UniqueKey<SchemaVersionRecord> SCHEMA_VERSION_PK = UniqueKeys0.SCHEMA_VERSION_PK;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<PmtInfoRecord, PmtHeadRecord> PMT_INFO_HEAD_FK = ForeignKeys0.PMT_INFO_HEAD_FK;
    public static final ForeignKey<PmtInstRecord, PmtInfoRecord> PMT_INST_INFO_FK = ForeignKeys0.PMT_INST_INFO_FK;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 extends AbstractKeys {
        public static final UniqueKey<PmtHeadRecord> PMT_HEAD_PK = createUniqueKey(PmtHead.PMT_HEAD, "PMT_HEAD_PK", PmtHead.PMT_HEAD.PMT_HEAD_ID);
        public static final UniqueKey<PmtInfoRecord> PMT_INFO_PK = createUniqueKey(PmtInfo.PMT_INFO, "PMT_INFO_PK", PmtInfo.PMT_INFO.PMT_INFO_ID);
        public static final UniqueKey<SchemaVersionRecord> SCHEMA_VERSION_PK = createUniqueKey(SchemaVersion.SCHEMA_VERSION, "SCHEMA_VERSION_pk", SchemaVersion.SCHEMA_VERSION.INSTALLED_RANK);
    }

    private static class ForeignKeys0 extends AbstractKeys {
        public static final ForeignKey<PmtInfoRecord, PmtHeadRecord> PMT_INFO_HEAD_FK = createForeignKey(Keys.PMT_HEAD_PK, PmtInfo.PMT_INFO, "PMT_INFO_HEAD_FK", PmtInfo.PMT_INFO.FK_PMT_HEAD_ID);
        public static final ForeignKey<PmtInstRecord, PmtInfoRecord> PMT_INST_INFO_FK = createForeignKey(Keys.PMT_INFO_PK, PmtInst.PMT_INST, "PMT_INST_INFO_FK", PmtInst.PMT_INST.FK_PMT_INFO_ID);
    }
}
