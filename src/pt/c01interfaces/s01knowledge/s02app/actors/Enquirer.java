package pt.c01interfaces.s01knowledge.s02app.actors;

import java.util.HashMap;

import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IDeclaracao;
import pt.c01interfaces.s01knowledge.s01base.inter.IEnquirer;
import pt.c01interfaces.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IResponder;


public class Enquirer implements IEnquirer{

	IObjetoConhecimento obj;
	public HashMap<String, String> quests = new HashMap<String, String>();
	
	@Override
	public void connect(IResponder responder) {
		// TODO Auto-generated method stub
		IBaseConhecimento t = new BaseConhecimento();
		String[] v = new String[7];
		v = t.listaNomes();

		for (int i = 0; i < v.length; i++) {			
			obj = t.recuperaObjeto(v[i]);
			
			IDeclaracao decl = obj.primeira();
			
	        boolean animalEsperado = true;
			while (decl != null && animalEsperado) {
				String pergunta = decl.getPropriedade();
				String respostaEsperada = decl.getValor();
				String resposta;				
				
				if (quests.containsKey(pergunta))
					resposta = quests.get(pergunta);					
				
				else {
					resposta = responder.ask(pergunta);
					quests.put(pergunta, resposta);					
				}
																				
				if (resposta.equalsIgnoreCase(respostaEsperada)) 
					decl = obj.proxima();
					
				else
					animalEsperado = false;
			}
			
			boolean acertei = false;
			if(animalEsperado)
				acertei = responder.finalAnswer(v[i]);
			
			if (acertei){
				System.out.println("Oba! Acertei!");
				return;
			} else
				System.out.println("fuem! fuem! fuem!");
			
		}
	}		

}