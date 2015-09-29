package org.qxp.ctrl.io;

import java.util.List;

public interface BasicWriter {

	public boolean writerTemplate(List<?> list);
	
	public boolean writerConfig(List<?> list);
}
