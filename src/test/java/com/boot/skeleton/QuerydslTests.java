package com.boot.skeleton;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.skeleton.SkeletonApplication;
import com.skeleton.entity.userInfo.QUserInfo;
import com.skeleton.entity.userInfo.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = SkeletonApplication.class)
@Transactional
class QuerydslTests {

	@Autowired
	EntityManager em;
	@Test
	void contextLoads() {

		UserInfo userInfo = new UserInfo();
		userInfo.setUserId("test");
		userInfo.setPassword("test");
		userInfo.setUserName("test");

		em.persist(userInfo);

		JPAQueryFactory jf = new JPAQueryFactory(em);
		QUserInfo QuserInfo = QUserInfo.userInfo;

		List<UserInfo> result = jf
				.selectFrom(QuserInfo)
				.fetch();

		Optional<UserInfo> resultUser = result.stream().filter(user -> user.getUserId().equals("test")).findFirst();

		assertThat(resultUser.get()).isEqualTo(userInfo);
		assertThat(resultUser.get().getUserId()).isEqualTo(userInfo.getUserId());

	}

}
