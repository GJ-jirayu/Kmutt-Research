package th.ac.kmutt.research.mapper;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomObjectMapper extends ObjectMapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4450431497052838439L;
    /*public CustomObjectMapper() {
       SimpleModule module = new SimpleModule("HTML XSS Serializer", new Version(1, 0, 0, "FINAL"));
      module.addSerializer(new JsonHtmlXssSerializer());
      this.registerModule(module);
    }*/
}