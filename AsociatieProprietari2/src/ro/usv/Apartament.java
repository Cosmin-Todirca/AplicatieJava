/**
 * @author Cosmin TODIRCA
 * @grupa 3133a
 * @nr 2
 */
package ro.usv;

import ro.usv.dao.Entitate;
import java.util.Calendar;
import static java.lang.System.exit;

public abstract class Apartament extends Entitate<Integer> {
    private int Id;
    private double suprafata;
    private int anConstructie,nr,etaj,nrApt;
    private String strada, scara;

    public Apartament(int id, double suprafata, int anConstructie, String strada, int nr, String scara, int etaj, int nrApt) {
        try {
            if(suprafata<=0)
                throw new IllegalArgumentException("Suprafata trebuie sa fie pozitiva") ;
            int an = Calendar.getInstance().get(Calendar.YEAR);
            if(anConstructie>an)
                throw new IllegalArgumentException("Anul trebuie sa fie anterior anului curent sau cel mult anul curent") ;
            if(nr<=0)
                throw new IllegalArgumentException("Numarul trebuie sa fie pozitiv") ;
            if(etaj<=0)
                throw new IllegalArgumentException("Etajul trebuie sa fie pozitiv") ;
            if(nrApt<=0)
                throw new IllegalArgumentException("Numarul apartamentului trebuie sa fie pozitiv") ;


            Id = id;
            this.suprafata = suprafata;             //tratat
            this.anConstructie = anConstructie;     //tratat
            this.nr = nr;                           //tratat
            this.etaj = etaj;                       //tratat
            this.nrApt = nrApt;                     //tratat
            this.strada = strada;
            this.scara = scara;
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
            exit(1);
        }
    }
    public int getVechime()
    {
        int an = Calendar.getInstance().get(Calendar.YEAR);
        return an-anConstructie;
    }
    public String getTip()
    {
        return "Fara tip";
    }

    public double getSuprafata() {
        return suprafata;
    }

    @Override//asta e entitatea din DAO
    public Integer getId() {
        return Id;
    }

    public int getEtaj() {
        return etaj;
    }

    @Override
    public String toString() {
        return
                "id=" + Id +
                ", suprafata=" + suprafata +
                ", anConstructie=" + anConstructie +
                ", strada='" + strada + '\'' +
                ", nr=" + nr +
                ", scara=" + scara +
                ", etaj=" + etaj +
                ", nrApt=" + nrApt;
    }

    public static void main(String[] args) {
        // write your code here
        Apartament loc1= new Locuinta("L",2,12.2,2000,"Alexandri",10,"A",2,3,5);
        System.out.println(loc1);
        Apartament sf1 = new SediuFirma("SF",2,12.2,2000,"Alexandri",10,"A",2,3, "S.C. Tester Prim", 11223344);
        System.out.println(sf1);
    }
}
