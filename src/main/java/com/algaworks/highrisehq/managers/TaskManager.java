package com.algaworks.highrisehq.managers;

import com.algaworks.highrisehq.Highrise;
import com.algaworks.highrisehq.bean.Task;
import com.sun.jersey.api.client.WebResource;

/**
 * 
 * @author thiagofa
 *
 */
public class TaskManager extends HighriseManager {

  public TaskManager(final WebResource webResource, final String authorization) {
		super(webResource, authorization);
	}

  public Task create(final Task task) {
		return this.create(task, Highrise.TASKS_PATH);
	}
	
}
