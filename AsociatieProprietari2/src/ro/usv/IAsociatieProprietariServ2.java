package ro.usv;

import java.util.List;

public interface IAsociatieProprietariServ2 {
    public void setStocare(String nume);
    public Apartament getApartamentById(int id);
    public List<Apartament> getApartamentente();
    public void saveApartament(Apartament ap);
    public void deleteApartment(int id);
    public void deleteApartmente();
    public double getAverageSurface(String tip);
    public List<Long> findIdsFloorSmallerThan(int etaj);
    public List<Long> findIDsSurfaceGreaterThan(double smin);
}
