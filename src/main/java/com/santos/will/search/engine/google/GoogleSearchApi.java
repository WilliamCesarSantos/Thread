package com.santos.will.search.engine.google;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.santos.will.search.api.Search;

/**
 * <pre>
 * <a href=
"http://www.journaldev.com/7207/google-search-from-java-program-example">http://www.journaldev.com/7207/google-search-from-java-program-example</a>
 * </pre>
 * 
 * @author William
 */
public class GoogleSearchApi implements Search<String> {

	private static final String GOOGLE_SEARCH_URL = "https://www.google.com/search?q=%s&num=%d";
	private static final Integer numberOfRegister = 50;

	public Collection<String> search(final String query) {
		try {
			final String searchURL = String.format(GOOGLE_SEARCH_URL, query, numberOfRegister);
			Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();
			final Elements results = doc.select("h3.r > a");
			final Collection<String> response = new ArrayList<String>(numberOfRegister);
			for (final Element result : results) {
				final String linkHref = result.attr("href");
				final String realLink = linkHref.substring(7, linkHref.indexOf("&"));
				response.add(realLink);
			}
			return Collections.unmodifiableCollection(response);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

}