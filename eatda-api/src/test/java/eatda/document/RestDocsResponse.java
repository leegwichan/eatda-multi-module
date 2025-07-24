package eatda.document;

import static org.springframework.restdocs.cookies.CookieDocumentation.responseCookies;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

import java.util.LinkedList;
import java.util.List;
import org.springframework.restdocs.cookies.CookieDescriptor;
import org.springframework.restdocs.headers.HeaderDescriptor;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.snippet.Snippet;

public class RestDocsResponse {

    private final List<Snippet> snippets;

    public RestDocsResponse() {
        this.snippets = new LinkedList<>();
    }

    public RestDocsResponse responseHeader(HeaderDescriptor... descriptors) {
        snippets.add(responseHeaders(descriptors));
        return this;
    }

    public RestDocsResponse responseCookie(CookieDescriptor... descriptors) {
        snippets.add(responseCookies(descriptors));
        return this;
    }

    public RestDocsResponse responseBodyField(FieldDescriptor... descriptors) {
        snippets.add(responseFields(descriptors));
        return this;
    }

    public List<Snippet> getSnippets() {
        return List.copyOf(snippets);
    }
}
