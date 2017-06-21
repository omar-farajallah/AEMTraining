package com.slalom.core.workflows;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.felix.scr.annotations.*;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.*;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component
@Service
@Properties({
	@Property(name = "process.description", value = "Adds a property to a newly created page"),
	@Property(name = "process.label", value = "Slalom Page Proprety")
})
public class PagePropertyWorkflowProcess implements WorkflowProcess {

	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
		Session session = workflowSession.adaptTo(Session.class);
		WorkflowData data = workItem.getWorkflowData();
		String path = null;
		String type = data.getPayloadType();
		try {
			if (type.equals("JCR_PATH") && data.getPayload() != null) {
				String payloadData = (String) data.getPayload();
				if (session.itemExists(payloadData)) {
					path = payloadData;
				}
			}
			if (path != null) {
				Node payloadNode = (Node) session.getItem(path);
				payloadNode.setProperty("workflow", "created");
				session.save();
			}
		} catch (Exception e) {
			
		} finally {
			if (session != null && session.isLive()) {
				session.logout();
			}
		}
	}
	
}