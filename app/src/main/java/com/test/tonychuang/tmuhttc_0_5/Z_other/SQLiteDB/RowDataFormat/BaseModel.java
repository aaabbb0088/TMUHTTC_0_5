package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.Check;
import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Ignore;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * Created by TonyChuang on 2016/3/27.
 */
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String ID = "_id";

    // 設置為主鍵,自增
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    // 取名為「_id」,如果此處不重新命名,就採用屬性名稱
    @Column("_id")
    public int id;

    // @Check條件檢測
    @Check("description NOT NULL")
    public String description = "Table 流水號";

    @Ignore
    private String ignore = "標記Ignore,並不會出現在數據庫中";

    @Override
    public String toString() {
        return "BaseModel{" + "description='" + description + '\'' + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
