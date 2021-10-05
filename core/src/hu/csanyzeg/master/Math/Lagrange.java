package hu.csanyzeg.master.Math;


import java.util.ArrayList;


//http://www.inf.u-szeged.hu/~kgelle/sites/default/files/upload/08_polinomok_interpolacio.pdf
public class Lagrange {

    public class Point {

        float x = 0;
        float y = 0;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

    }

    private int range = 3;

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setRangeToMaximum() {
        this.range = points.size();
    }

    public void setRangeToMinimum() {
        this.range = 2;
    }

    ArrayList<Point> points = new ArrayList<Point>();


    public ArrayList<Point> getPoints() {
        return points;
    }


    public void addpoint(float x, float y){
        points.add(new Point(x, y));
        /*
        points.sort(new Comparator<Point>(Point o1, Point o2) {
            @Override
            public int compare(Point point, Point t1) {
                if (o1.x > o2.x) return 1;
                if (o1.x < o2.x) return -1;
                return 0;
            }


        });*/

    }

    public void addpoint(double x, double y){
        points.add(new Point((float)x, (float)y));

    }

    public float getY(float x){
        int count = points.size();
        int startrange = 0;
        while (startrange<points.size() && points.get(startrange).x<x){
            startrange++;
        }
        startrange -= range / 2;
        if (startrange<0){
            startrange = 0;
        }


        int endrange = startrange + range - 1;
        if (endrange>=points.size()){
            endrange = points.size() - 1;
            startrange = endrange - range + 1;
        }
        //System.out.println(startrange + " - " + endrange);
        float sum = 0;
        for (int i = startrange; i <= endrange; i++){
            float mul = 1;
            for(int j = startrange; j<=endrange; j++){
                if (i!=j){
                    mul*=(x-points.get(j).x)/(points.get(i).x - points.get(j).x);
                }
            }
            sum += points.get(i).y*mul;
        }
        return sum;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Lagrange l = new Lagrange();
        l.addpoint(-1, 2);
        l.addpoint(0, 1);
        l.addpoint(1, 2);
        l.addpoint(2, 3);
        l.addpoint(3, 1);
        l.addpoint(4, 0);

        l.setRange(3);
        //l.setRangeToMaximum();
        for(Point p : l.getPoints()){
            System.out.println(" X = " + p.x + " Y = " + l.getY(p.x) + "  Eredeti Y:" + p.y);
        }

        System.out.println("------------");
        for(float x = -1; x<4; x+=0.1f){
            System.out.println(" X = " + x + " Y = " + l.getY(x));
        }
    }
}
