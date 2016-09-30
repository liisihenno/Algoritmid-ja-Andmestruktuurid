import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liisi on 26.09.2016.
 */
/*Programmeerida funktsioon, mis etteantud raamatute hindu sisaldava
järjendi korral leiab kõikide selliste raamatukomplektide arvu, mille koguhind jääb
lõiku [50,100] eurot.
*/
public class AAPraktikum34 {

    public static <T> int alamhulgad(List<T> hulk) {
        return alamhulgad(hulk, new ArrayList<T>());
    }

    private static <T> int alamhulgad(List<T> t88tlemata, List<T> t88deldud) {
        int koguarv = 0;
        if (t88tlemata.size() == 0) {
            int summa = 0;
            //System.out.print("{ ");
            for(int i = 0; i < t88deldud.size(); i++) {
                if(i == t88deldud.size() - 1) {
                    //System.out.print(t88deldud.get(i) + " ");
                    summa += (Integer)t88deldud.get(i);
                } else {
                    //System.out.print(t88deldud.get(i) + ", ");
                    summa += (Integer)t88deldud.get(i);
                }
            }
            //System.out.print("}\n");
            if (summa >= 50 && summa <= 100){
                koguarv += 1; //ma ei tea kuidas seda jäädvustada ilma globaalse muutujata
            }
            //System.out.println(koguarv);
            //System.out.println(summa);
            return koguarv;

        } else {
            //Loome t88tlemata listist koopia ja eemaldame sealt esimese elemendi
            List<T> t88tlemataKoopia = new ArrayList<T>(t88tlemata);
            T element = t88tlemataKoopia.remove(0);

            //Vasak haru - siia elementi ei lisa
            //Võime t88deldud listi koopiata edasi anda, sest
            //vasakus harus t88deldud listi kunagi ei muudeta (vt. joonist)
            //if ((Integer)t88tlemataKoopia.get(0) >= 50 && (Integer)t88tlemataKoopia.get(0) <= 100){
            int vahearv1 = 0;
            for (int i = 0; i < t88deldud.size(); i++){
                vahearv1 += (Integer)t88deldud.get(i);
            }
            int summa1 = 0;
            if (vahearv1 <= 100){
                summa1 = alamhulgad(t88tlemataKoopia, t88deldud);
            }
            //Parem haru - lisame t88deldud elementidele eemaldatud elemendi

            List<T> t88deldudKoopia = new ArrayList<T>(t88deldud);
            t88deldudKoopia.add(element);
            int vahearv2 = 0;
            for (int i = 0; i < t88deldudKoopia.size(); i++){
                vahearv2 += (Integer)t88deldudKoopia.get(i);
            }
            int summa2 = 0;
            if (vahearv2 <= 100){
                summa2 = alamhulgad(t88tlemataKoopia, t88deldudKoopia);
            }

            //System.out.println(koguarv);

            return summa1+summa2;
        }
        //System.out.println(koguarv);
        //return koguarv;

    }
    public static int test1(){
        List<Integer> testid = new ArrayList<>();
        testid.add(20);
        testid.add(30);
        testid.add(50);

        return alamhulgad(testid); //
    }
    public static int test2(){
        List<Integer> testid = new ArrayList<>();

        return alamhulgad(testid);
    }
    public static int test3(){
        List<Integer> testid = new ArrayList<>();
        testid.add(200);
        testid.add(300);
        testid.add(500);

        return alamhulgad(testid);
    }


   public static void main(String[] args){//test, kui ükski komplekt ei sobi
       /*List<Integer> hulk = new ArrayList<>();
       hulk.add(30);
       hulk.add(50);
       hulk.add(120);
       alamhulgad(hulk);*/


        System.out.println("Algoritmid ja andmestruktuurid kodutöö 2. Autor: Liisi Henno");
        System.out.println("Ülesanne nr 4.9. Ülesanne: Programmeerida funktsioon, mis etteantud raamatute hindu sisaldava\n" +
                "järjendi korral leiab kõikide selliste raamatukomplektide arvu, mille koguhind jääb\n" +
                "lõiku [50,100] eurot.");

        System.out.print("Mitu komplekti testandmeid soovite sisestada? (0, et andmed automaatselt genereeritaks): ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Sisendit loetakse vaikimisi sõnedena, numbriteks peate selle ise teisendama.
        try{
            int komplekte = Integer.parseInt(reader.readLine());

            if(komplekte < 0) {
                System.err.println("Komplektide arv peab olema mittenegatiivne.");
            } else if(komplekte == 0) {
                //genereeriTestAndmed(); kui teie ülesandes on seda nõutud
                List<Integer> hulk = new ArrayList<Integer>();
                for (int i = 0; i < 3; i++) {
                    hulk.add((int) (Math.random() * 100 + 1));
                    System.out.println(hulk.get(i));
                }
                alamhulgad(hulk);
            } else {
                for(int i = 0; i < komplekte; i++) {
                    System.out.print("Sisestage komplekti " + (i +1) + " jaoks nimi:" );
                    String nimi = reader.readLine();
                    List<Integer> hulk = new ArrayList<Integer>();
                    for (int j = 0; j < 4; j++) {
                        System.out.print("Sisestage komplekti " + (i +1) + " jaoks arv:" );
                        int arv = Integer.parseInt(reader.readLine());
                        hulk.add(arv);

                    }
                    alamhulgad(hulk);
                }
            }

        } catch(NumberFormatException nfe){
            System.err.println("Sisestasite ebasobivas formaadis sisendi.");
        } catch(IOException ioe) {
            System.err.println("Sisendi lugemine ebaõnnestus.");
        }
       //int vastus1 = test1();
       System.out.println("Oodatav vastus: 5, kontrollmeetodi vastus: " + test1());
       System.out.println("Oodatav vastus: 0, kontrollmeetodi vastus: " + test2());
       System.out.println("Oodatav vastus: 0, kontrollmeetodi vastus: " + test3());

    }
}
