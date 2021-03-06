package com.algaworks.highrisehq.managers;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.algaworks.highrisehq.Highrise;
import com.algaworks.highrisehq.HighriseException;
import com.algaworks.highrisehq.bean.Empty;
import com.algaworks.highrisehq.bean.Errors;
import com.algaworks.highrisehq.bean.Tag;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 *
 * @author duncan
 */
public class TagManager extends HighriseManager {

  public TagManager(final WebResource webResource, final String authorization) {
    super(webResource, authorization);
  }

  public TagResponse createTag(final Tag tag) {
    return create(tag, Highrise.COMPANY_TAG_PATH.replaceAll("#\\{subject-id\\}", tag.getPartyId()));
  }

  protected TagResponse create(final Tag tag, final String path) {
    TagResponse result = null;
    final ClientResponse response = post(path, tag);

    final String s = response.getEntity(String.class);

    if (response.getStatus() == ClientResponse.Status.CREATED.getStatusCode()
        || response.getStatus() == ClientResponse.Status.BAD_REQUEST.getStatusCode()) {
      try {
        final JAXBContext jc = JAXBContext.newInstance(TagResponse.class, Empty.class, Errors.class);
        final Unmarshaller unmarshaller = jc.createUnmarshaller();
        final Object obj = unmarshaller.unmarshal(new StringReader(s));
        if (obj instanceof Errors) {
          final Errors errors = (Errors) obj;
          throw new HighriseException(response.getStatus(), errors.getError());
        } else if (!(obj instanceof Empty)) {
          result = (TagResponse) obj;
        }
      } catch (final JAXBException e) {
        throw new HighriseException("Error unmarshalling Highrise return.", e);
      }
    } else {
      throw new HighriseException(response.getStatus(), "Highrise return not expected ("
          + ClientResponse.Status.fromStatusCode(response.getStatus()) + ").");
    }

    return result;
  }

}
