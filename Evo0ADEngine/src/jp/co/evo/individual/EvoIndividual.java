package jp.co.evo.individual;

import ga.framework.logic.core.individual.GeneBase;
import ga.framework.logic.core.individual.IndividualBase;
import jp.co.evo.individual.gene.EvoGene;

public class EvoIndividual implements IndividualBase {

	private GeneBase gene = new EvoGene();

	@Override
	public void calcFitness(double objectiveValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getFitness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GeneBase getGene() {
		return this.gene;
	}

	@Override
	public double getObjectiveValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double objectiveFunction(Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFitness(double fitness) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setGene(GeneBase gene) {
		this.gene = gene;
	}

	@Override
	public void setObjectiveValue(double objectiveValue) {
		// TODO Auto-generated method stub

	}

}
