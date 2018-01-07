package com.Jonas.common;


public interface SyncConsumeService {
	public String getType();
	public void process(String message);
}
