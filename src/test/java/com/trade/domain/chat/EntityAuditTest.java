package com.trade.domain.chat;

import com.trade.security.auth.AuthUserDto;
import com.trade.security.auth.AuthenticationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/com/trade/domain/chat/init.sql")
public class EntityAuditTest {

	@Autowired
	private EntityManager entityManager;
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	@Autowired
	private AuthenticationService authenticationService;

	@Before
	public void setUp() {
		AuthUserDto authUserDto = new AuthUserDto();
		authUserDto.setPassword("123456");
		authUserDto.setPhone("0993848428");
		authenticationService.authenticate(authUserDto);
	}

	@Test
	@Transactional
	public void auditInformationShouldBeStoredWithEntity() {
		Message message = new Message();
		message.setContent("content");
		entityManager.persist(message);

		Message message1 = entityManager.find(Message.class, 1L);

		Date createdDate = message1.getCreatedDate();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(createdDate);

		assertThat(createdDate, is(notNullValue()));
		assertThat(calendar.getTimeZone(), is(equalTo(TimeZone.getTimeZone("UTC"))));
		assertThat(message1.getCreatedBy(), is(equalTo(1L)));
	}

	@Test
	@Transactional
//	@Ignore
	public void auditInformationShouldBeUpdatedDuringEntityUpdate() {
		Message message = new Message();
		message.setContent("content");
		entityManager.persist(message);

		Message message1 = entityManager.find(Message.class, 1L);
		message1.setContent("content2");

		entityManager.persist(message1);

		Message message2 = entityManager.find(Message.class, 1L);
		Date updatedDate = message2.getUpdatedDate();

		assertThat(updatedDate, is(notNullValue()));
		assertThat(message2.getUpdatedBy(), is(equalTo(1L)));
	}
}