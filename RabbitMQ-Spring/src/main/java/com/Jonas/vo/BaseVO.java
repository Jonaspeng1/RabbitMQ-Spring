package com.Jonas.vo;

import java.io.Serializable;
import java.util.Date;


public abstract class BaseVO implements Serializable{
   /**
	 * 
	 */
	private static final long serialVersionUID = -4937362280394362378L;
	private String syncType;
	private Date createTime;
	private Boolean success;
	
	public String getSyncType() {
		return syncType;
	}
	public void setSyncType(String syncType) {
		this.syncType = syncType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	
}
