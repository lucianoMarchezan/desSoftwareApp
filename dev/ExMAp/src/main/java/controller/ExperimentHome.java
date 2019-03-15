package controller;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
public class ExperimentHome {
	
	
	
	
	public String getMessa() {
		return "Helle there!";
	}
}
