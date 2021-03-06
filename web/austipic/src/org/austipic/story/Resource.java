package org.austipic.story;

public final class Resource {
	public enum Types {
		background, zone
	}
	
	public final String location;
	public final Types type;
	
	public Resource(String location, Types type) {
		this.location = location;
		this.type = type;
	}


	@Override
	public String toString() {
		StringBuilder xml = new StringBuilder();
		
		xml.append("<");  xml.append(type.name().toLowerCase()); xml.append(">");
		xml.append(location);
		xml.append("</"); xml.append(type.name().toLowerCase()); xml.append(">");
		
		return xml.toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Resource other = (Resource) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	

}
