package com.algaworks.highrisehq;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

import com.algaworks.highrisehq.managers.CompanyManager;
import com.algaworks.highrisehq.managers.DealManager;
import com.algaworks.highrisehq.managers.NoteManager;
import com.algaworks.highrisehq.managers.PeopleManager;
import com.algaworks.highrisehq.managers.TagManager;
import com.algaworks.highrisehq.managers.TaskManager;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.ClientFilter;

/**
 * 
 * @author thiagofa
 *
 */
public class Highrise {

  private static final String BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
      + "abcdefghijklmnopqrstuvwxyz" + "0123456789+/";

  public static final String PEOPLE_SEARCH_PATH = "people/search.xml";
  public static final String PEOPLE_PATH = "people.xml";
  public static final String PEOPLE_UPDATE_PATH = "people/#{id}.xml";
  public static final String PEOPLE_NOTES_PATH = "people/#{person-id}/notes.xml";
  public static final String COMPANY_PATH = "companies.xml";
  public static final String COMPANY_SEARCH_PATH = "companies/search.xml";
  public static final String COMPANY_UPDATE_PATH = "companies/#{id}.xml";
  public static final String COMPANY_NOTES_PATH = "companies/#{subject-id}/notes.xml";
  public static final String TASKS_PATH = "/tasks.xml";
  public static final String DEALS_PATH = "/deals.xml";
  public static final String DEAL_UPDATE_PATH = "deals/#{id}.xml";
  public static final String COMPANY_TAG_PATH = "/companies/#{subject-id}/tags.xml";

  private final Client client;
  private final String authorization;
  private final WebResource webResource;

  public Highrise(final String accountName, final String token) {
    this.authorization = "Basic " + encodeCredentialsBasic(token, "x");
    this.client = Client.create();
    this.webResource = client.resource("https://" + accountName + ".highrisehq.com");
  }

  public Highrise(final String accountName, final String token, final ClientFilter filter) {
    this(accountName, token);
    this.client.addFilter(filter);
  }

  public PeopleManager getPeopleManager() {
    return new PeopleManager(this.webResource, this.authorization);
  }

  public CompanyManager getCompanyManager() {
    return new CompanyManager(this.webResource, this.authorization);
  }

  public NoteManager getNoteManager() {
    return new NoteManager(this.webResource, this.authorization);
  }

  public TaskManager getTaskManager() {
    return new TaskManager(this.webResource, this.authorization);
  }

  public DealManager getDealManager() {
    return new DealManager(this.webResource, this.authorization);
  }

  public TagManager getTagManager() {
    return new TagManager(this.webResource, this.authorization);
  }

  private static String encodeCredentialsBasic(final String username, final String password) {
    String encode = username + ":" + password;
    final int paddingCount = (3 - (encode.length() % 3)) % 3;
    encode += "\0\0".substring(0, paddingCount);
    final StringBuilder encoded = new StringBuilder();

    for (int i = 0; i < encode.length(); i += 3) {
      final int j = (encode.charAt(i) << 16) + (encode.charAt(i + 1) << 8)
          + encode.charAt(i + 2);
      encoded.append(BASE64_CHARS.charAt((j >> 18) & 0x3f));
      encoded.append(BASE64_CHARS.charAt((j >> 12) & 0x3f));
      encoded.append(BASE64_CHARS.charAt((j >> 6) & 0x3f));
      encoded.append(BASE64_CHARS.charAt(j & 0x3f));
    }
    return encoded.toString();
  }

  @XmlEnum
  public enum SubjectType {
    @XmlEnumValue("Deal") DEAL, @XmlEnumValue("Kase") KASE, @XmlEnumValue("Party") PARTY
  }

}
