package sweng.project.evoting.votazione;

import java.sql.Timestamp;
import java.util.Objects;

public abstract class Votazione {
	
    private final String id;			//identificativo della votazione
    private final Timestamp inizio;		//inizio votazione
    private final Timestamp fine;		//fine votazione

    public Votazione(String id, Timestamp inizio, Timestamp fine){
    	if(id.isEmpty() || id.isBlank()) 
    		throw new IllegalArgumentException("Deve essere indicato un id per ogni votazione");
	    this.id = Objects.requireNonNull(id);
	    
	    if(!inizio.before(fine))
	    	throw new IllegalArgumentException("La votazione non può finire prima ancora di iniziare");
	    this.inizio = Objects.requireNonNull(inizio);
	    this.fine = Objects.requireNonNull(fine);
	    
	    assert repOk();
    }

    // restituisce true se la votazione è attiva, false altrimenti
    public boolean isAttiva(){
        return new Timestamp(System.currentTimeMillis()).before(fine);
    }
    
    public String getId() {
    	return this.id;
    }
    
    public Timestamp getInizio() {
    	return this.inizio;
    }
    
    public Timestamp getFine() {
    	return this.fine;
    }

    //restituisce la modalita di votazione
    public abstract String getTipo();
    
    // inserisce la votazione nel database
    public abstract void insertVotazione();
    
    private boolean repOk() {
    	return inizio.before(fine);
    }
    
    @Override
    public String toString() {
    	return String.format("ID votazione: %s\nInizio: %s\tFine: %s", id, inizio.toString(), fine.toString());
    }
    
    @Override
    public int hashCode() {
    	int result = id.hashCode();
    	result = 31 * result + inizio.hashCode();
    	return 31 * result + fine.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Votazione) {
    		Votazione v = (Votazione) obj;
    		return v.id.equals(this.id) && v.inizio.equals(this.inizio) && v.fine.equals(this.fine);
    	}
    	return false;
    }
    
}
