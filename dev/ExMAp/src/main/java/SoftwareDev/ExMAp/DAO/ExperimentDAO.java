package SoftwareDev.ExMAp.DAO;
import java.util.ArrayList;
import java.util.List;

import SoftwareDev.ExMAp.Model.Experiment;
import SoftwareDev.ExMAp.DAO.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;



public class ExperimentDAO {
	private List<Experiment> experiments;

	public List<Experiment> getExperiments() {
		return experiments;
	}

	public void setExperiments(List<Experiment> experiments) {
		this.experiments = experiments;
	}

	public void createExperiment(Experiment Experiment) throws Exception{
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.save(Experiment);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
			throw new Exception("Error during Experiment creation");
		} finally {
			
			session.close();
		}
	}

	public Experiment getExperiment(String id){

		Experiment experiment = null;

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
		 
			experiment = (Experiment) session.get(Experiment.class, new String(id));
		} finally {
			
			session.close();
		}
		 
		return experiment;
	}

	public void deleteExperiment(String ExperimentId) throws Exception{
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			Experiment Experiment = (Experiment) session.load(Experiment.class, new String(ExperimentId));



			session.delete(Experiment);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
			throw new Exception("Error deleting Experiment");
		} finally {
			
			session.close();
		}
	}


	
	public List<Experiment> listExperiments(){
		experiments = new ArrayList<Experiment>();

		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Experiment> criteria = builder.createQuery(Experiment.class);
		Root<Experiment> experimentRoot=criteria.from(Experiment.class);
		criteria.select(experimentRoot); 
		experiments = session.createQuery(criteria).getResultList(); 
		session.close(); 
		try{ 
			
			return experiments;
		} catch (Exception e) {
			return new ArrayList<>();
		}

	}

	//	@SuppressWarnings("unchecked")
	//	public List<Researcher> listResearchers(){
	//		List<Researcher> researches = new ArrayList<Researcher>();
	//
	//		Session session = HibernateUtil.getSessionFactory().openSession();
	//		try {
	//			researches = session.createQuery("from Researcher").list();
	//		} catch (RuntimeException e) {
	//			e.printStackTrace();
	//		} finally {
	//			
	//			session.close();
	//		}
	//		return matrizes;
	//	}

	public void updateExperiment(Experiment experiment) throws Exception{
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		 
		try {
			trns = session.beginTransaction();
			session.saveOrUpdate(experiment);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
			throw new Exception("Error to update Experiment");
		} finally {
			
			session.close();
		}
	}
}
