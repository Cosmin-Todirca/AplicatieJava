/**
 * @author Cosmin TODIRCA
 * @grupa 3133a
 * @nr 2
 */
package ro.usv;

import ro.usv.dao.Dao;
import ro.usv.dao.SerializareDaoComplet;
import ro.usv.dao.SerializareDaoCompletByte;

import java.util.ArrayList;
import java.util.List;

public class AsociatieProprietariServ implements IAsociatieProprietariServ2{
    private Dao ssDao;
    public AsociatieProprietariServ()
    {
        ssDao=new SerializareDaoCompletByte();
    }
    public AsociatieProprietariServ(String FileName)
    {
        ssDao = new SerializareDaoCompletByte(FileName);
    }
    @Override
    public void setStocare(String nume) {
        if(nume==null)
        {
            ssDao=new SerializareDaoCompletByte();
        }
        else
        {
            ssDao=new SerializareDaoCompletByte(nume);
        }
    }

    @Override
    public Apartament getApartamentById(int id) {
        return (Apartament) ssDao.get(id);
    }

    @Override
    public List<Apartament> getApartamentente() {
        return ssDao.getAll();
    }

    @Override
    public void saveApartament(Apartament ap) {
        try {
            if(ap==null)
                throw new NullPointerException("Eroare. save: parametru obj. null");
            ssDao.save(ap);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Eroare. save: obj exista deja id="+ap.getId());
        }
        catch (NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteApartment(int id) {
        ssDao.delete(id);
    }

    @Override
    public void deleteApartmente() {
        ssDao.deleteAll();
    }

    @Override
    public double getAverageSurface(String tip) {
        List<Apartament> apartaments = ssDao.getAll();
        if(tip.compareTo("L")==0)
        {
            double suprafata=0;
            for (Apartament ap:apartaments) {
                if(ap.getTip().compareTo("L")==0)
                  suprafata+=ap.getSuprafata();
            }
            if(suprafata==0)
                return -1;
            else
                return suprafata;
        }
        if(tip.compareTo("SF")==0)
        {
            double suprafata=0;
            for (Apartament ap:apartaments) {
                if(ap.getTip().compareTo("SF")==0)
                    suprafata+=ap.getSuprafata();
            }
            if(suprafata==0)
                return -1;
            else
                return suprafata;
        }
        return -1;
    }

    @Override
    public List<Long> findIdsFloorSmallerThan(int etaj) {
        List<Long> lst = new ArrayList<>();
        List<Apartament> apt = ssDao.getAll();
        for (Apartament ap:apt) {
            if(ap.getEtaj()<=etaj)
            {
                lst.add(Long.valueOf(ap.getId()));
            }
        }
        return lst;
    }

    @Override
    public List<Long> findIDsSurfaceGreaterThan(double smin) {
        List<Long> lst = new ArrayList<>();
        List<Apartament> apt = ssDao.getAll();
        for (Apartament ap:apt) {
            if(ap.getSuprafata()>=smin)
            {
                lst.add(Long.valueOf(ap.getId()));
            }
        }
        return lst;
    }

    public static void main(String[] args) {
        // write your code here
        System.out.println("Proba la partea de business logic");
        AsociatieProprietariServ servicii = new AsociatieProprietariServ();

        Apartament loc1= new Locuinta("L",1,12.2,2000,"Alexandri",10,"A",2,3,5);
        Apartament loc2= new Locuinta("L",2,12.8,2002,"Tomsa",5,"A",3,5,2);
        Apartament sf1 = new SediuFirma("SF",3,12.2,2000,"Alexandri",10,"A",4,3, "S.C. Tester Prim", 11223344);

        System.out.println(servicii.getApartamentById(5));
        servicii.saveApartament(loc1);
        servicii.saveApartament(loc2);
        servicii.saveApartament(sf1);
        System.out.println(servicii.getAverageSurface("SF"));
        System.out.println(servicii.findIdsFloorSmallerThan(3));
        System.out.println(servicii.findIDsSurfaceGreaterThan(12.3));

    }
}
