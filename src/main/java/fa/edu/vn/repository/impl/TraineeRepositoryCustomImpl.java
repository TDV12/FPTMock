package fa.edu.vn.repository.impl;

import fa.edu.vn.dto.TraineeSearchDto;
import fa.edu.vn.entites.Faculty;
import fa.edu.vn.entites.Trainee;
import fa.edu.vn.entites.TraineeCandidateProfile;
import fa.edu.vn.entites.University;
import fa.edu.vn.repository.ITraineeRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TraineeRepositoryCustomImpl implements ITraineeRepositoryCustom {

	private final SessionFactory sessionFactory;

	@Override
	public Optional<Trainee> findTraineeByNameAndDobAndPhoneAndEmailCustom(String name, String dob, String phone,
			String email) {
		try (Session session = sessionFactory.openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Trainee> query = builder.createQuery(Trainee.class);
			Root<Trainee> root = query.from(Trainee.class);
			Join<Trainee, TraineeCandidateProfile> join = root.join("traineeCandidateProfile");
			query.select(root);
			List<Predicate> condition = new ArrayList<>();
			if (!ObjectUtils.isEmpty(dob)) {
				condition.add(builder.equal(join.get("dateOfBirth"), LocalDate.parse(dob)));
			}
			Predicate nameCondition = builder.equal(join.get("fullName"), name);
			Predicate phoneCondition = builder.equal(join.get("phone"), phone);
			Predicate emailCondition = builder.equal(join.get("email"), email);
			condition.addAll(Arrays.asList(nameCondition, phoneCondition, emailCondition));
			query.where(condition.toArray(new Predicate[condition.size()]));
			return session.createQuery(query).uniqueResultOptional();
		}
	}

	@SuppressWarnings("unused")
	@Override
	public List<TraineeSearchDto> findTraineeSearch(Integer traineeCandidateId, String account, String fullName,
			String dateOfBirth, String phone, String email) {
		try (Session session = sessionFactory.openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<TraineeSearchDto> query = criteriaBuilder.createQuery(TraineeSearchDto.class);
			Root<Trainee> root = query.from(Trainee.class);
			Join<Trainee, TraineeCandidateProfile> joinCandidate = root.join("traineeCandidateProfile");
			Join<TraineeCandidateProfile, University> joinUniversity = joinCandidate.join("university");
			Join<TraineeCandidateProfile, Faculty> joinFaculty = joinCandidate.join("faculty");

			query.multiselect(root.get("traineeCandidateId"), joinCandidate.get("account"),
					joinCandidate.get("fullName"), joinCandidate.get("dateOfBirth"), joinCandidate.get("fullName"),
					joinUniversity.get("universityName"), joinFaculty.get("facultyName"), joinCandidate.get("phone"),
					joinCandidate.get("email"), root.get("status")

			);
			List<Predicate> conditions = new ArrayList<>();
			if (traineeCandidateId != null && !ObjectUtils.isEmpty(traineeCandidateId)) {
				conditions.add(criteriaBuilder.equal(root.get("traineeCandidateId"), traineeCandidateId));
			}
			if (account != null && !ObjectUtils.isEmpty(account)) {
				conditions.add(criteriaBuilder.like(joinCandidate.get("account"), "%" + account + "%"));
			}
			if (fullName != null && !ObjectUtils.isEmpty(fullName)) {
				conditions.add(criteriaBuilder.like(joinCandidate.get("fullName"), "%" + fullName + "%"));
			}
			if (!ObjectUtils.isEmpty(dateOfBirth)) {
				LocalDate localDateFormat = LocalDate.parse(dateOfBirth);
				conditions.add(criteriaBuilder.equal(joinCandidate.get("dateOfBirth"), localDateFormat));
			}
			if (phone != null && !ObjectUtils.isEmpty(phone)) {
				conditions.add(criteriaBuilder.like(joinCandidate.get("phone"), "%" + phone + "%"));
			}
			if (email != null && !ObjectUtils.isEmpty(email)) {
				conditions.add(criteriaBuilder.like(joinCandidate.get("email"), "%" + email + "%"));
			}

			if (conditions != null) {
				query.where(conditions.toArray(new Predicate[conditions.size()]));

				return session.createQuery(query).getResultList();
			}
			return session.createQuery(query).getResultList();
		}
	}
}
