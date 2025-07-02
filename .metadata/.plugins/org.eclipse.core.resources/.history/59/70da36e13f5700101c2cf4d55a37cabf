package com.novelsbr.backend.utils.htmlsanitizer;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

public class HtmlSanitizerUtil {

	private static final PolicyFactory policy = Sanitizers.FORMATTING
			.and(Sanitizers.BLOCKS);
			
	
	public static String sanitize(String html) {
		return policy.sanitize(html);
	}
}
