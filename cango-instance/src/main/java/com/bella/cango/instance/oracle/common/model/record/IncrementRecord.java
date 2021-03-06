package com.bella.cango.instance.oracle.common.model.record;

import com.bella.cango.instance.oracle.common.db.meta.ColumnValue;
import com.bella.cango.instance.oracle.common.utils.YuGongToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

/**
 * 增量的record对象
 *
 * @author agapple 2013-9-16 下午4:20:25
 */
public class IncrementRecord extends Record {

    private IncrementOpType opType;

    public IncrementRecord() {
        super();
    }

    public IncrementRecord(String schemaName, String tableName, List<ColumnValue> primaryKeys, List<ColumnValue> columns) {
        super(schemaName, tableName, primaryKeys, columns);
    }

    public IncrementOpType getOpType() {
        return opType;
    }

    public void setOpType(IncrementOpType opType) {
        this.opType = opType;
    }

    @Override
    @Deprecated
    public ColumnValue getPrimaryKeyByName(String pkName) {
        if (opType == IncrementOpType.D) {
            return super.getPrimaryKeyByName(pkName, true); // delete的时候可能缺少列,返回null值
        } else {
            return super.getPrimaryKeyByName(pkName);
        }
    }

    @Override
    public ColumnValue getColumnByName(String columnName) {
        if (opType == IncrementOpType.D) {
            return super.getColumnByName(columnName, true); // delete的时候可能缺少列,返回null值
        } else {
            return super.getColumnByName(columnName);
        }
    }

    @Override
    public ColumnValue removeColumnByName(String columnName) {
        if (opType == IncrementOpType.D) {
            return super.removeColumnByName(columnName, true); // delete的时候可能缺少列,返回null值
        } else {
            return super.removeColumnByName(columnName);
        }
    }

    @Override
    public IncrementRecord clone() {
        IncrementRecord record = new IncrementRecord();
        super.clone(record);
        record.setOpType(this.opType);
        return record;
    }

    public void clone(IncrementRecord record) {
        super.clone(record);
        record.setOpType(this.opType);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, YuGongToStringStyle.DEFAULT_STYLE);
    }

}
