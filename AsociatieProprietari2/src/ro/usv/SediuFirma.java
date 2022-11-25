/**
 * @author Cosmin TODIRCA
 * @grupa 3133a
 * @nr 2
 */
package ro.usv;

import static java.lang.System.exit;

public class SediuFirma extends Apartament{
    String Tip;
    String denumire;
    int CUI;

    public SediuFirma(String Tip, int id, double suprafata, int anConstructie, String strada, int nr, String scara, int etaj, int nrApt, String denumire, int CUI) {
        super(id, suprafata, anConstructie, strada, nr, scara, etaj, nrApt);
        try{
            if(Tip.compareTo("SF")!=0)
                throw new IllegalArgumentException("Eroare data de tipul incorect");
            this.Tip=Tip;
            this.denumire=denumire;
            this.CUI=CUI;
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
        return "{Tip=" + Tip + ", "+super.toString() +
                ", denumire='" + denumire + '\'' +
                ", CUI=" + CUI +
                "}";
    }
}
