package SoftwareDev.ExMAp.DAO;
import java.util.ArrayList;
import java.util.List;

import SoftwareDev.ExMAp.Model.Experiment;
import SoftwareDev.ExMAp.DAO.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
			session.flush();
			session.close();
		}
	}

	public Experiment getExperiment(String id){

		Experiment Experiment = null;

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Experiment = (Experiment) session.get(Experiment.class, new String(id));
		} finally {
			session.flush();
			session.close();
		}

		return Experiment;
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
			session.flush();
			session.close();
		}
	}

	 
	@SuppressWarnings("unchecked")
	public void listExperiments(){
		experiments = new ArrayList<Experiment>();

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			experiments = session.createQuery("from Experiment").list();

					

		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
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
	//			session.flush();
	//			session.close();
	//		}
	//		return matrizes;
	//	}

	public void updateExperiment(Experiment Experiment) throws Exception{
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.update(Experiment);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
			throw new Exception("Error to update Experiment");
		} finally {
			session.flush();
			session.close();
		}
	}
}
