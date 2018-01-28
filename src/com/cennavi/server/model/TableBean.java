package com.cennavi.server.model;

import java.math.BigInteger;

public class TableBean {

    public String colName;

    public String cloComment;

    public BigInteger len;
    
    public String extra;

    public TableBean(String colName, String cloComment, BigInteger len,String extra) {
        this.colName = colName;
        this.cloComment = cloComment;
        this.len = len;
        this.extra = extra;
    }

    public String getColNameFirstUper() {
        return this.colName.substring(0, 1).toUpperCase() + this.colName.substring(1).toLowerCase();
    }
    public static void main(String[] args) {
    }

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getCloComment() {
		return cloComment;
	}

	public void setCloComment(String cloComment) {
		this.cloComment = cloComment;
	}

	public BigInteger getLen() {
		return len;
	}

	public void setLen(BigInteger len) {
		this.len = len;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
	
}
