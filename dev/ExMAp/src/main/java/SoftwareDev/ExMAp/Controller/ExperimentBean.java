package SoftwareDev.ExMAp.Controller;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.apache.log4j.Logger;

import SoftwareDev.ExMAp.DAO.ExperimentDAO;
import SoftwareDev.ExMAp.Model.Experiment;
import SoftwareDev.ExMAp.Model.Researcher;

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

	public ExperimentBean() {
		
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
		log.info("Editing Experiment "+this.getSelectedId());

		
		Experiment Experiment = experimentDAO.getExperiment(this.getSelectedId());

		if(Experiment!=null){
			this.experiment.setId(Experiment.getId());
			this.experiment.setDescription(Experiment.getDescription());
			this.experiment.setName(Experiment.getName()); 
			this.experiment.setDomain(Experiment.getDomain());
			this.experiment.setGoal(Experiment.getGoal());  
			this.experiment.setStartDate(Experiment.getStartDate()); 

		}else{
			this.setMsg("Experiment not found!");
			log.error("Experiment not found");
		}


		return "update";
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

		clearExperiment();

		log.info("Listing Experiment");

		listExperiments = 
				new ArrayList<Experiment>();
		try{
		 
			System.out.println("ECSA");
			experimentDAO.listExperiments();
			listExperiments = experimentDAO.getExperiments();
			System.out.println("XASDASDSADASDASDASDASDXASDASDA");
			System.out.println(experimentDAO.getExperiments());
			

		}catch(Exception e){
			this.setMsg(e.getMessage());
			log.error(e);
		}

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

