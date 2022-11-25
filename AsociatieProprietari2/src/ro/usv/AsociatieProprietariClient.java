/**
 * @author Cosmin TODIRCA
 * @grupa 3133a
 * @nr 2
 */
package ro.usv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AsociatieProprietariClient {
    //comenzi comune : add, clear, delete, file, list, rem, stop
    //comenzi nr2 avgsurf, floor, surfgt

    public static void main(String[] args) {
        AsociatieProprietariServ proprietariServ = new AsociatieProprietariServ();
        //File fisin = new File("in2.txt");
        Scanner sc;
        String[] comanda;
        String afisComanda;
        try {
            sc = new Scanner(new File( args[0] ) );
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Fisierul nu exista");
            return;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Nu s-au transmis argumente in linia de comanda");
            return;
        }
        while(sc.hasNextLine())
        {
            afisComanda=sc.nextLine();
            System.out.println(afisComanda);

            comanda=afisComanda.trim().split("\\s+");
            if(comanda.length<1)
                System.out.println("Numarul parametrilor nu este corect");
            else//ca sa nu introducem randuri goale
            switch (comanda[0])
            {
                //Eroare. Numarul parametrilor nu este corect
                //Eroare. Format incorect pentru parametru(i)
                case "add":
                    if(comanda.length>=3) {
                        if (comanda[1].compareTo("L") == 0) {
                            if (comanda.length < 11) {
                                System.out.println("Eroare. Numarul parametrilor nu este corect");
                            } else {
                                int deplasareStrada=0;
                                int pozParametruEroare = 2;
                                try {//aici este locuinta. Tratam doar situatia in care, strada e compusa din mai multe cuvinte, adica apar ghilimele
                                    int id = Integer.parseInt(comanda[2]);
                                    pozParametruEroare++;
                                    double suprafata = Double.parseDouble(comanda[3]);
                                    pozParametruEroare++;
                                    int anConstructie = Integer.parseInt(comanda[4]);
                                    String strada;
                                    if(comanda[5].contains("\""))
                                    {
                                        if(comanda[5].charAt(comanda[5].length()-1)=='"')
                                        {
                                            strada=comanda[5].substring(1,comanda[5].length()-1);
                                        }
                                        else
                                        {
                                            strada=comanda[5].substring(1,comanda[5].length());
                                            deplasareStrada++;
                                            while(!comanda[5+deplasareStrada].contains("\""))
                                            {
                                                strada+=" "+comanda[5+deplasareStrada];
                                                deplasareStrada++;
                                            }
                                            strada+=" "+comanda[5+deplasareStrada].substring(0,comanda[5+deplasareStrada].length()-1);
                                        }
                                    }
                                    else
                                    {
                                        strada = comanda[5];
                                    }
                                    pozParametruEroare = 6;
                                    int nr = Integer.parseInt(comanda[6+deplasareStrada]);
                                    pozParametruEroare++;
                                    String scara = comanda[7+deplasareStrada];
                                    pozParametruEroare = 8;
                                    int etaj = Integer.parseInt(comanda[8+deplasareStrada]);
                                    pozParametruEroare++;
                                    int nrApt = Integer.parseInt(comanda[9+deplasareStrada]);
                                    pozParametruEroare++;
                                    int nrPersoane = Integer.parseInt(comanda[10+deplasareStrada]);
                                    proprietariServ.saveApartament(new Locuinta("L", id, suprafata, anConstructie, strada, nr, scara, etaj, nrApt, nrPersoane));
                                } catch (NumberFormatException e) {
                                    System.out.println("Eroare. Format incorect pentru parametru(" + pozParametruEroare + ")");//consider strada ca un parametru, nu ca un grup de parametrii
                                    }
                                catch (ArrayIndexOutOfBoundsException e)
                                {
                                    System.out.println("Eroare. Numarul parametrilor nu este corect");
                                }
                                }
                            }
                         else if (comanda[1].compareTo("SF") == 0) {
                            if (comanda.length <= 11) {
                                System.out.println("Eroare. Numarul parametrilor nu este corect");
                            } else {
                                int pozParametruEroare = 2;
                                int deplasare=0;
                                try {
                                    int id = Integer.parseInt(comanda[2]);
                                    pozParametruEroare++;
                                    double suprafata = Double.parseDouble(comanda[3]);
                                    pozParametruEroare++;
                                    int anConstructie = Integer.parseInt(comanda[4]);
                                    pozParametruEroare++;
                                    String strada;
                                    if(comanda[5].contains("\""))
                                    {
                                        if(comanda[5].charAt(comanda[5].length()-1)=='"')
                                        {
                                            strada=comanda[5].substring(1,comanda[5].length()-1);
                                        }
                                        else
                                        {
                                            strada=comanda[5].substring(1,comanda[5].length());
                                            deplasare++;
                                            while(!comanda[5+deplasare].contains("\""))
                                            {
                                                strada+=" "+comanda[5+deplasare];
                                                deplasare++;
                                            }
                                            strada+=" "+comanda[5+deplasare].substring(0,comanda[5+deplasare].length()-1);
                                        }
                                    }
                                    else
                                    {
                                        strada = comanda[5];
                                    }
                                    pozParametruEroare++;
                                    int nr = Integer.parseInt(comanda[6+deplasare]);
                                    pozParametruEroare++;
                                    String scara = comanda[7+deplasare];
                                    pozParametruEroare ++;
                                    int etaj = Integer.parseInt(comanda[8+deplasare]);
                                    pozParametruEroare++;
                                    int nrApt = Integer.parseInt(comanda[9+deplasare]);
                                    pozParametruEroare++;
                                    String denumire;
                                    if(!comanda[10+deplasare].contains("\""))
                                        denumire=comanda[10+deplasare];
                                    else
                                    {
                                        if(comanda[10+deplasare].charAt(comanda[10+deplasare].length()-1)=='"')
                                            denumire=comanda[10+deplasare].substring(1,comanda[10+deplasare].length()-1);
                                        else
                                        {
                                            denumire=comanda[10+deplasare].substring(1,comanda[10+deplasare].length());
                                            deplasare++;
                                            while (!comanda[10+deplasare].contains("\""))
                                            {
                                                denumire+=" "+comanda[10+deplasare];
                                                deplasare++;
                                            }
                                            denumire+=" "+comanda[10+deplasare].substring(0,comanda[10+deplasare].length()-1);
                                        }
                                    }
                                    pozParametruEroare++;
                                    int CUI = Integer.parseInt(comanda[11+deplasare]);
                                    proprietariServ.saveApartament(new SediuFirma("SF", id, suprafata, anConstructie, strada, nr, scara, etaj, nrApt, denumire, CUI));
                                } catch (NumberFormatException e) {
                                    System.out.println("Eroare. Format incorect pentru parametru(" + pozParametruEroare + ")");
                                }
                                catch (ArrayIndexOutOfBoundsException e)
                                {
                                    System.out.println("Eroare. Numarul parametrilor nu este corect");
                                }
                            }

                        } else
                            System.out.println("Eroare. Format incorect pentru parametru(1)");
                    }
                    else
                        System.out.println("Eroare. Numarul parametrilor nu este corect");
                    break;

                case "clear":
                    System.out.println("S-au eliminat toate apartamentele");
                    proprietariServ.deleteApartmente();
                    break;
                case "delete":
                    if(comanda.length==2)
                    {
                        try {
                            int id= Integer.parseInt(comanda[1]);
                            if(proprietariServ.getApartamentById(id)==null)
                                System.out.println("Eroare. delete: obj cu id "+id+" nu exista");
                            else
                            {
                                proprietariServ.deleteApartment(id);
                                //System.out.println("S-a eliminat apartamentul cu id "+id);
                            }
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Eroare. Format incorect pentru parametru(1)");
                        }
                    }
                    else
                    System.out.println("Eroare. Numarul parametrilor nu este corect");

                    break;
                case "file":
                    if(comanda.length==2)
                    {
                        proprietariServ.setStocare(comanda[1]);
                    }
                    else
                        System.out.println("Eroare. Numarul parametrilor nu este corect");
                    break;
                case "list":
                    if(comanda.length==1)
                    {
                        System.out.print("[");
                        int poz=0;
                        int max=proprietariServ.getApartamentente().size();
                        if(proprietariServ.getApartamentente().size()!=0)
                            System.out.println("");
                        else
                            System.out.println("]");
                        for (Apartament apartament: proprietariServ.getApartamentente()) {
                            if(poz<max-1)
                                System.out.println(apartament+",");
                            else {
                                System.out.print(apartament);//ca sa punem si ] la final ca in exemplu!
                                System.out.println("]");
                            }
                            poz++;
                        }
                    }
                    else
                    if(comanda.length==2)
                    {
                        try
                        {
                            Apartament apartament= proprietariServ.getApartamentById(Integer.parseInt(comanda[1]));
                            if(apartament==null)
                                System.out.println("Eroare. Nu exista apartament cu id="+comanda[1]);
                            else
                                System.out.println(apartament);
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Eroare. Format incorect pentru parametru(1)");
                        }
                    }
                    else
                    {
                        System.out.println("Eroare. Numarul parametrilor nu este corect");
                    }
                    break;
                case "rem":
                    System.out.println(afisComanda);
                    //atat trebuie sa faca
                    break;
                case "stop":
                    System.out.println("La revedere!");
                    return;
                case "avgsurf":
                    double suprafata=0;
                    int impartit=0;
                    if(comanda.length==1)
                    {
                        for (Apartament ap:proprietariServ.getApartamentente()) {
                            {
                                suprafata+=ap.getSuprafata();
                                impartit++;
                            }
                        }
                    }
                    else
                    {
                        if(comanda[1].compareTo("L")==0)
                        {
                            for (Apartament ap:proprietariServ.getApartamentente()) {
                                if(ap.getTip().compareTo("L")==0)
                                {
                                    suprafata+=ap.getSuprafata();
                                    impartit++;
                                }
                            }
                        }
                        else
                            if(comanda[1].compareTo("SF")==0)
                            {
                                for (Apartament ap:proprietariServ.getApartamentente()) {
                                    if(ap.getTip().compareTo("SF")==0)
                                    {
                                        suprafata+=ap.getSuprafata();
                                        impartit++;
                                    }
                                }
                            }
                    }
                    if(suprafata==0)
                    {
                        if(comanda.length==1)
                        {
                            System.out.println("Nu sunt apartamente introduse");
                        }
                        else
                        {
                            System.out.println("Nu sunt apartamente de tipul "+comanda[1]);
                        }
                    }
                    else
                    {
                        if(comanda.length==2)
                        {
                            if(comanda[1].compareTo("L")==0)
                            {
                                System.out.println("Suprafata medie a locuintelor: "+suprafata/impartit);
                            }
                            if(comanda[1].compareTo("SF")==0)
                            {
                                System.out.println("Suprafata medie a sediilor de firme: "+suprafata/impartit);
                            }
                        }
                        else
                        {
                            System.out.println("Suprafata medie a apartamentelor: "+suprafata/impartit);
                        }
                    }
                    break;
                case "floor":
                    try {
                        if (comanda.length != 2) {
                            System.out.println("Eroare. Numarul parametrilor nu este corect");
                        } else {
                            boolean primul = true;
                            if (Integer.parseInt(comanda[1]) == 0) {
                                System.out.print("Ap. situate la parter [");
                                for (Apartament ap : proprietariServ.getApartamentente()) {
                                    if (ap.getEtaj() == 0) {
                                        if (primul) {
                                            System.out.print(ap.getId() + ", ");
                                            primul = false;
                                        } else {
                                            System.out.print(", " + ap.getId());

                                        }
                                    }
                                }
                                System.out.println("]");
                            } else {
                                System.out.print("Ap. situate la un etaj <=" + comanda[1] + " [");
                                for (Apartament ap : proprietariServ.getApartamentente()) {
                                    if (ap.getEtaj() <= Integer.parseInt(comanda[1])) {
                                        if (primul) {
                                            System.out.print(ap.getId());
                                            primul = false;
                                        } else {
                                            System.out.print(", " + ap.getId());

                                        }
                                    }
                                }
                                System.out.println("]");
                            }
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("Eroare. Format incorect pentru parametru(1)");
                    }
                    break;
                case "surfgt":
                    if(comanda.length<2) {
                        System.out.println("Eroare. Numarul parametrilor nu este corect");
                    }
                    else
                    {
                        try
                        {
                            suprafata=Double.parseDouble(comanda[1]);
                            boolean primul=true;
                            System.out.print("Ap. cu suprafata cel putin egala cu "+suprafata+": [");
                            for (Apartament ap:proprietariServ.getApartamentente()) {
                                if(ap.getSuprafata()>=suprafata)
                                {
                                    if(primul)
                                    {
                                        System.out.print(ap.getId());
                                        primul=false;
                                    }
                                    else
                                    {
                                        System.out.print(", "+ap.getId());
                                    }
                                }
                            }
                            System.out.println("]");
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Eroare. Format incorect pentru parametru(1)");
                        }
                    }
                    break;
                default:
                    System.out.println("Eroare. Comanda neimplementata");
                    break;
            }

        }
    }
}