package com.dy.learn.learn.bean.Query;

import java.io.Serializable;

public class PageQuery implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = -8266369736035588788L;
	/** 当前页 */
	private Integer page = 1;
	/** 查询时页面数据条数 */
	private Integer rows = 20;
	/** 重置页面数据条数 */
	private Integer numPerPage;
	/** 上次查询时页面数据条数 */
	private Integer numPerNum;

	/** 是否分页 */
	private Boolean flag = true;

	public Integer getPage() {
		if (null == page || 0 == page) {
			return 1;
		}
		return page;
	}

	public void setPage(Integer pageNum) {
		if (null != numPerNum && 0 != numPerNum && this.rows != numPerNum) {
			this.page = 1;
		} else {
			this.page = pageNum;
		}
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Integer getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(Integer numPerPage) {
		if (null != numPerNum && 0 != numPerNum && this.rows != numPerNum) {
			setPage(1);
		}
		if (null != numPerPage && 0 != numPerPage) {// 当重置页数据条数不为0或null时，重置查询页面大小
			this.rows = numPerPage;
		}
		this.numPerPage = numPerPage;
	}

	public Integer getNumPerNum() {
		return numPerNum;
	}

	public void setNumPerNum(Integer numPerNum) {
		if (null != numPerNum && 0 != numPerNum && this.rows != numPerNum) {
			setPage(1);
		}
		this.numPerNum = numPerNum;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
}
