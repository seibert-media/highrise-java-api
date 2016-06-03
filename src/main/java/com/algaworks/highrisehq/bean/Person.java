package com.algaworks.highrisehq.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author thiagofa
 *
 */
@XmlRootElement(name="person")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
        private String title;
	private String firstName;
	private String lastName;
	private String background;
        private Long companyId;
        private String companyName;
	private ContactData contactData = new ContactData();
        private SubjectDatas subjectDatas = new SubjectDatas();

	@XmlElement(name="first-name")
	public String getFirstName() {
		return firstName;
	}

  public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	
	@XmlElement(name="id")
	public Long getId() {
		return id;
	}
        
  public void setId(final Long id) {
		this.id = id;
	}

        @XmlElement(name="title")
        public String getTitle() {
            return title;
        }

  public void setTitle(final String title) {
            this.title = title;
        }
	
	@XmlElement(name="last-name")
	public String getLastName() {
		return lastName;
	}

  public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

        @XmlElement(name="company-id")
        public Long getCompanyId() {
            return companyId;
        }

  public void setCompanyId(final Long companyId) {
            this.companyId = companyId;
        }

        @XmlElement(name="company-name")
        public String getCompanyName() {
            return companyName;
        }

  public void setCompanyName(final String companyName) {
            this.companyName = companyName;
        }

	@XmlElement(name="contact-data")
	public ContactData getContactData() {
		return contactData;
	}

  public void setContactData(final ContactData contactData) {
		this.contactData = contactData;
	}

        @XmlElement(name="subject_datas")        
        public SubjectDatas getSubjectDatas() {
            return subjectDatas;
        }

  public void setSubjectDatas(final SubjectDatas subjectDatas) {
            this.subjectDatas = subjectDatas;
        }
	
        @XmlElement(name="background")
	public String getBackground() {
		return background;
	}

  public void setBackground(final String background) {
		this.background = background;
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
    final Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", title=" + title + ", firstName=" + firstName + ", lastName=" + lastName + ", background=" + background + ", companyId=" + companyId + "-" + companyName +'}';
    }
	
}
