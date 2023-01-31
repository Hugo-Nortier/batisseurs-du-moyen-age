package infojeu;

import java.io.Serializable;

public class InfoRepetition extends Info implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 910825074941186598L;
	private boolean prisEcus = false;
	private boolean acheteAction = false;
	private boolean investissement = false;
	protected int repetition = 0;
	
	public void ajouterRepetition(int nbactions) {
		repetition+=nbactions;	
	}

	public int getRepetition() {
		return repetition;
	}

	public void prisEcus() {
		setPrisEcus(true);
	}
	
	public void acheteAction() {
		setAcheteAction(true);
	}
	public void resetGI(){
		repetition = 0;
		setPrisEcus(false);
		setAcheteAction(false);
		setInvestissement(false);
	}

	public void investi() {
		setInvestissement(true);
	}
	public boolean getInv() {
		return isInvestissement();
	}

	public void setInvestissement(boolean investissement) {
		this.investissement = investissement;
	}

	public boolean isAcheteAction() {
		return acheteAction;
	}

	public void setAcheteAction(boolean acheteAction) {
		this.acheteAction = acheteAction;
	}

	public boolean isPrisEcus() {
		return prisEcus;
	}

	public void setPrisEcus(boolean prisEcus) {
		this.prisEcus = prisEcus;
	}

	public boolean isInvestissement() {
		return investissement;
	}
}
