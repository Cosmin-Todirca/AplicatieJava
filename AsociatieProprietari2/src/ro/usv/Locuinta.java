/**
 * @author Cosmin TODIRCA
 * @grupa 3133a
 * @nr 2
 */
package ro.usv;

import static java.lang.System.exit;

public class Locuinta extends Apartament {

    private String Tip;
    private int nrPersoane;
    public Locuinta(String Tip, int id, double suprafata, int anConstructie, String strada, int nr, String scara, int etaj, int nrApt, int nrPersoane) {
        super(id, suprafata, anConstructie, strada, nr, scara, etaj, nrApt);
        try{
            if(Tip.compareTo("L")!=0)
                throw new IllegalArgumentException("Eroare data de tipul incorect");
            if(nrPersoane<0)
                throw new IllegalArgumentException("Eroare data de tipul incorect");
            this.Tip=Tip;
            this.nrPersoane=nrPersoane;
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
            exit(1);
        }
    }

    @Override
    public String getTip() {
        return Tip;
    }

    @Override
    public String toString() {
        return "{Tip=" + Tip +", "+ super.toString() +
                ", nrPersoane=" + nrPersoane +
                "}";
    }
}
