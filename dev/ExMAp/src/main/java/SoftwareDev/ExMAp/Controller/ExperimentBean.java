package SoftwareDev.ExMAp.Controller;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.apache.log4j.Logger;

import SoftwareDev.ExMAp.DAO.ExperimentDAO;
import SoftwareDev.ExMAp.Model.Experiment;

@ManagedBean(name = "experimentBean")
public class ExperimentBean implements java.io.Serializable{

	private static Logger log = Logger.getLogger(ExperimentBean.class);

	private static final long serialVersionUID = 1L;

	private String selectedId;
	private ExperimentDAO experimentDAO;
	private String msg;
	private List<Experiment> listExperiments; 
	private Experiment experiment;
	private List<Experiment> experiments;
	//    private List<Researcher> researchers;

	@PostConstruct
	public void init() {

		experiment = new Experiment();
		experimentDAO= new ExperimentDAO();
	}

	public Experiment getExperiment() {
		return experiment;
	}

	public void setExperiment(Experiment Experiment) {
		this.experiment = Experiment;
	}

	public String getMsg() {
		return msg;
	}

	public List<Experiment> getexperiments() {
		return experiments;
	}

	public void setexperiments(List<Experiment> experiments) {
		this.experiments = experiments;
	}



	public String getSelectedId() { 
		
		return selectedId;

	}
	
	public void setSelectedId(String i) {
		 selectedId = i;;

	}

	private void setMsg(String message) {
		// TODO Auto-generated method stub

	}

	public void clearExperiment() {
		experiment = new Experiment();
		log.info("Cleaning Experiment");

		this.experiment.setId("");
		this.experiment.setName("");
		this.experiment.setDescription("");
		this.experiment.setGoal("");
		this.experiment.setDomain(""); 
		this.experiment.setStartDate(null);
	}

	public String editExperiment() {
		System.out.println("XX");
		System.out.println("Editing Experiment "+ getSelectedId());

		
		Experiment experiment = experimentDAO.getExperiment(getSelectedId());

		if(experiment!=null){
			System.out.println("XX1");
			this.experiment.setId(experiment.getId());
			this.experiment.setDescription(experiment.getDescription());
			this.experiment.setName(experiment.getName()); 
			this.experiment.setDomain(experiment.getDomain());
			this.experiment.setGoal(experiment.getGoal());  
			this.experiment.setStartDate(experiment.getStartDate()); 

		}else{
			this.setMsg("Experiment not found!");
			log.error("Experiment not found");
		}
		System.out.println("DEU?");

		return "update_experiment";
		 
	}

	public String createExperiment() {
		String str= "";
		try{

			experimentDAO.createExperiment(this.experiment); 

			str = "index";
			clearExperiment();
			this.setMsg("Experiment registered!");
		}catch(Exception e){
			this.setMsg(e.getMessage());

			str = "newExperiment";
			log.error(e);
		}

		return str;
	}

	public String deleteExperiment(){

		log.info("Delenting Experiment "+this.getSelectedId());

		String str = "index";

		try{

			experimentDAO.deleteExperiment(this.getSelectedId());

			clearExperiment();
			this.setMsg("Deleted with sucess!");

		}catch(Exception e){
			this.setMsg(e.getMessage());
			log.error(e);
		}      

		return str;
	}


	public List<Experiment> getListExperiments(){

		if(listExperiments==null) {
			clearExperiment();

			log.info("Listing Experiment");

			listExperiments = 
					new ArrayList<Experiment>();
			try{

				listExperiments = experimentDAO.listExperiments(); 
			}catch(Exception e){
				this.setMsg(e.getMessage());
				log.error(e);
			}
			return listExperiments;   
		}else
			return listExperiments;    
	}

	public String updateExperiment(){
		String str = "index";
		try{
			experimentDAO.updateExperiment(this.experiment); 

			clearExperiment();

			this.setMsg("Sucess Updated!");
		}catch(Exception e){
			this.setMsg(e.getMessage());
			str = "deleteUpdate";
			log.error(e);
		}

		return str;
	}

	//  public List<Researcher> getResearcheres() {
	//          return researchers;
	//    }
	//
	//    public void setResearcheres(List<Researcher> Researcheres) {
	//          this.researchers = Researcheres;
	//    }

	//  public List<ExperimentBean> getsResearcheres(){
	//
	//    log.info("Listing Researcheres");
	//     
	//          List<ExperimentBean> experiments = 
	//           new ArrayList<ExperimentBean>();
	//           
	//    try{
	//          ExperimentDAO ExperimentDAO = new ExperimentDAO();
	//           
	//          for(Researcher Researcher:ExperimentDAO.listResearcheres()){
	//                 ExperimentBean bean = new ExperimentBean();
	//                 bean.setExperiment(Researcher);
	//                  
	//                 experiments.add(bean);
	//          }
	//
	//    }catch(Exception e){
	//          this.setMsg(e.getMessage());
	//          log.error(e);
	//    }
	//     
	//          return experiments;
	//    }

}

