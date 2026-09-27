package jp.co.evo.individual.gene;

import java.util.ArrayList;
import java.util.List;

import ga.framework.logic.common.GaContext;
import ga.framework.logic.core.individual.GeneBase;

public class EvoGene implements GeneBase {

	private List<Integer> gene = new ArrayList<>();
	
	@Override
	public Object getGeneValue() {
		return gene;
	}

	@Override
	public void initGene(GaContext context) {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setGeneValue(Object value) {
		this.gene = (List<Integer>) value;
	}

}
