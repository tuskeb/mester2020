package hu.csanyzeg.master.Math;

public class main {


    public static void main(String[] args) {
        try {
            // Az 1-es osztállyal meghatározok egy pontot (7,15), amelyen a 20 m/s sebességgel, (2,3) pontból elhajított test áthalad. Itt a szöget kell kiszámolni.
            Ballistics1 ball = new Ballistics1(70, 15, 30,0,0);
            // Ennek két megoldása van:
            System.out.println("\n\nSzögek, mellyel a fenti pontot érinteni lehet. A valasztottszog értéke 0 vagy 1 lehet.");
            int valasztottszog = 0;
            System.out.println(ball.getAnglesByDeg()[0]);
            System.out.println(ball.getAnglesByDeg()[1]);

            // Az egyik szöget (0) felhasználva kezdőszögként és a kiindulási pontot ugyan úgy megadva (2,3) ugyan azt a hajítást kapom.
            // De itt a feladatnak megfelelő a bemenet, azaz a sebesség, szög és kiinduló pozíció.
            Ballistics2 ball2 = new Ballistics2(30, ball.getAnglesByRad()[valasztottszog], 0, 0);

            //Meg kell határozni, hogy a kért magasságot, azaz vízfelszínt mikor éri el. Ennek szintén két megoldása van. A 0. megoldás valószínűleg negatív időbe esik (ekkor lesz jó a horgászós program)
            //Az 1-es megoldás pedig a távolabbi pont. A példában az ... magasságba eső pontokat keresi.
            float magassag = 1f;
            System.out.println("\n\nEzeken az X koordinátákon érinti az " + magassag + " magasságot a pálya");
            System.out.println(ball2.getX(magassag)[0]);
            System.out.println(ball2.getX(magassag)[1]);
            System.out.println("\n\nIdőben ekkor történik meg:");
            System.out.println(ball2.getElapsedTimeFromY(magassag)[0] + " sec");
            System.out.println(ball2.getElapsedTimeFromY(magassag)[1] + " sec");

            System.out.println("\n\nAz első módszerrel létrehozott hajítás koordinátái szimulációval");
            System.out.println(ball);
            //ball.testFlight(valasztottszog, 60f);

            System.out.println("\n\nA második módszerrel létrehozott hajítás koordinátái szimulációval");
            System.out.println(ball2);
            //ball2.testFlight(1f, 60f);

            for(float t = 0; t< 3; t+=0.1f){
                System.out.println(ball.getPosition(t,0).toString());
                System.out.println(ball2.getPosition(t).toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
