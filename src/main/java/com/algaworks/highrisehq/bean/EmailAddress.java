package com.algaworks.highrisehq.bean;

import java.io.Serializable;

/**
 * 
 * @author thiagofa
 *
 */
public class EmailAddress implements Serializable {

  private static final long serialVersionUID = 1L;
  public static String LOCATION_WORK = "Work";

  private Long id;
  private String address;
  private String location;

  public EmailAddress() {
  }

  public EmailAddress(final String address, final String location) {
    super();
    this.address = address;
    this.location = location;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(final String location) {
    this.location = location;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final EmailAddress other = (EmailAddress) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
