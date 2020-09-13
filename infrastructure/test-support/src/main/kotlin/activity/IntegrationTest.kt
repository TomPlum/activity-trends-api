package activity

import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import java.lang.annotation.Inherited

@Inherited
@SpringBootTest
@MustBeDocumented
@EnableAutoConfiguration
@SpringBootConfiguration
@Target(AnnotationTarget.CLASS)
annotation class IntegrationTest