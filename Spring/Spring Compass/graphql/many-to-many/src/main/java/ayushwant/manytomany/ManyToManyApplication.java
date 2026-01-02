package ayushwant.manytomany;

import ayushwant.manytomany.service.CourseService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class ManyToManyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManyToManyApplication.class, args);
    }
    /**
     * This bean provides the tools for the CourseService class.
     * It uses MethodToolCallbackProvider to automatically discover and register methods annotated with @Tool.
     *
     * @param courseService The CourseService instance containing the methods to be exposed as tools.
     * @return A ToolCallbackProvider that provides access to the CourseService methods as tools.
     */
    @Bean
    public ToolCallbackProvider weatherTools(CourseService courseService) {
        return  MethodToolCallbackProvider.builder().toolObjects(courseService).build();
    }


}
