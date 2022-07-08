package com.danisample.mongodbsample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MongodbsampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodbsampleApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
		return args -> {
			Address address = new Address("Argentina", "CABA", "1405");
			String email = "juangomez@mail.com";
			Student student = new Student(
					"Juan",
					"Gomez",
					email,
					Gender.MALE,
					address,
					List.of("Literatura", "Geografía"),
					new BigDecimal(120.5),
					LocalDateTime.now()
			);

			//insertMongoTemplateAndQuery(<repository, mongoTemplate, email, student);
			repository.findStudentByEmail(email).ifPresentOrElse(s -> {
				System.out.println("There is an existing Student with email: " + s.getEmail());
			}, () -> {
				System.out.println("Inserting Student: " + student);
				repository.insert(student);
			});
		};
	}

	private void insertMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));

		List<Student> students = mongoTemplate.find(query, Student.class);

		if (students.size() > 1) {
			throw new IllegalStateException("More than one Student for email: " + email);
		}

		if (students.isEmpty()) {
			System.out.println("Inserting Student: " + student);
			repository.insert(student);
		} else {
			System.out.println("There is an existing Student with email: " + email);
		}
	}
}
