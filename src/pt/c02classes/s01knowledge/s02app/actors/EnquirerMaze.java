package pt.c02classes.s01knowledge.s02app.actors;


import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerMaze implements IEnquirer {

	private IResponder responder;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	//responder.move--->string
	public boolean discover() {
		
		String norte = responder.ask("norte");
		String sul = responder.ask("sul");
		String leste = responder.ask("leste");
		String oeste = responder.ask("oeste");
		String aqui = responder.ask("aqui");

		
		
		while(aqui != "saida"){
			if(leste != "parede"){
				responder.move("leste");
			}
			else if( leste == "parede" && norte != "parede"){
				responder.move("norte");
			}
			else if( leste == "parede" && norte == "parede" && oeste != "parede"){
				responder.move("oeste");
			}
			else if(leste == "parede" && norte == "parede" && oeste == "parede" && sul !="parede" ){
				
				responder.move("sul");
			}
		
			norte = responder.ask("norte");
			sul = responder.ask("sul");
			leste = responder.ask("leste");
			oeste = responder.ask("oeste");
			aqui = responder.ask("aqui");
		}
		
		if (responder.finalAnswer("cheguei"))
			System.out.println("Voce encontrou a saida!");
		else
			System.out.println("Fuem fuem fuem!");
		
		
		return true;
	}
	
}
