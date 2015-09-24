package org.qxp.ctrl.parse;

import org.dom4j.DocumentException;

public interface DomParse {

	public Object parseXML(String fileName) throws DocumentException;
}
