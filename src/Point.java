public class Point {


    float latitude;

    float longitude;

    String name;

    Point(float latitude,float longitude){

        this.latitude = latitude;
        this.longitude = longitude;
    }

    Point(float latitude,float longitude,String name){

        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;

    }

    public String toString(){

        return name+"("+latitude+","+longitude+")";
    }
}
