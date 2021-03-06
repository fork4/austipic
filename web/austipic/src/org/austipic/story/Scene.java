package org.austipic.story;

import java.util.List;

import com.google.common.base.Joiner;

public final class Scene {
	public final Integer  sequenceNumber;
	public final String   text;
	public final List<Resource> resources;
	
	public Scene(Integer sequenceNumber, String text, List<Resource> resources) {
		super();
		this.sequenceNumber = sequenceNumber;
		this.text = text;
		this.resources = resources;
	}

	@Override
	public String toString() {
		StringBuilder xml = new StringBuilder();
		
		xml.append("<scene sequenceNumber=\""); xml.append(sequenceNumber); xml.append("\">");
		xml.append("<text><![CDATA[");
		xml.append(text);
		xml.append("]]></text>");
		
		xml.append("<resources>");
		xml.append(Joiner.on("").join(resources));
		xml.append("</resources>");
		xml.append("</scene>");
		
		return xml.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((resources == null) ? 0 : resources.hashCode());
		result = prime * result
				+ ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Scene other = (Scene) obj;
		if (resources == null) {
			if (other.resources != null)
				return false;
		} else if (!resources.equals(other.resources))
			return false;
		if (sequenceNumber == null) {
			if (other.sequenceNumber != null)
				return false;
		} else if (!sequenceNumber.equals(other.sequenceNumber))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
}
