package fa.edu.vn.repository.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import fa.edu.vn.dto.CandidateSearchDto;
import fa.edu.vn.entites.Candidate;
import fa.edu.vn.entites.Faculty;
import fa.edu.vn.entites.TraineeCandidateProfile;
import fa.edu.vn.entites.University;
import fa.edu.vn.repository.ICandidateRepositoryCustom;

@Service
public class CandidateRepositoryCustomImpl implements ICandidateRepositoryCustom {
	

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unused")
	@Override
	public List<CandidateSearchDto> findCandidateSearch(Integer emplId, String account, String fullName,
			String dateOfBirth, String phone, String email) {

		try (Session session = sessionFactory.openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<CandidateSearchDto> query = criteriaBuilder.createQuery(CandidateSearchDto.class);
			Root<Candidate> root = query.from(Candidate.class);
			Join<Candidate, TraineeCandidateProfile> joinCandidate = root.join("traineeCandidateProfile");
			Join<TraineeCandidateProfile, University> joinUniversity = joinCandidate.join("university");
			Join<TraineeCandidateProfile, Faculty> joinFaculty = joinCandidate.join("faculty");

			query.multiselect(root.get("emplId"), joinCandidate.get("account"), joinCandidate.get("fullName"),joinCandidate.get("dateOfBirth"),
					joinCandidate.get("fullName"),joinUniversity.get("universityName"),joinFaculty.get("facultyName"),joinCandidate.get("phone"), joinCandidate.get("email"),root.get("status")

			);
			List<Predicate> conditions = new ArrayList<>();
			if (emplId != null && !ObjectUtils.isEmpty(emplId)) {
				conditions.add(criteriaBuilder.equal(root.get("emplId"),emplId));
			}
			if (account != null && !ObjectUtils.isEmpty(account)) {
				conditions.add(criteriaBuilder.like(joinCandidate.get("account"),"%"+account+"%"));
			}
			if (fullName != null && !ObjectUtils.isEmpty(fullName)) {
				conditions.add(criteriaBuilder.like(joinCandidate.get("fullName"), "%"+fullName+"%"));
			}
			if (!ObjectUtils.isEmpty(dateOfBirth)) {
				LocalDate localDateFormat = LocalDate.parse(dateOfBirth);
				conditions.add(criteriaBuilder.equal(joinCandidate.get("dateOfBirth"),localDateFormat));
			}
			if (phone != null && !ObjectUtils.isEmpty(phone)) {
				conditions.add(criteriaBuilder.like(joinCandidate.get("phone"),"%"+phone+"%"));
			}
			if (email != null && !ObjectUtils.isEmpty(email)) {
				conditions.add(criteriaBuilder.like(joinCandidate.get("email"),"%"+email+"%"));
			}
			
			if (conditions != null) {
				query.where(conditions.toArray(new Predicate[conditions.size()]));
				return session.createQuery(query).getResultList();
			}
			return session.createQuery(query).getResultList();
		}
	}

	
}
