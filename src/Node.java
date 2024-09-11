import java.util.ArrayList;
import java.util.List;

public class Node {


    Point topLeft = new Point(90, -180);

    Point bottomRight = new Point(-90, 180);

    Node topLeftChild;

    Node topRightChild;

    Node bottomLeftChild;

    Node bottomRightChild;

    boolean isLeaf = true;

    List<Point> points = new ArrayList<>();


    public String toString() {

        return "(" + topLeft.longitude + "," + topLeft.latitude + ")-(" + bottomRight.longitude + "," + bottomRight.latitude + ")";
    }

    public void add(Node node, Point point) throws Exception {


        float latitude = point.latitude;
        float longitude = point.longitude;

        if (latitude > 90 || latitude < -90 || longitude > 180 || longitude < -180) {

            throw new Exception("Invalid point");
        }

        //find where to add
        while (!node.isLeaf) {


            node = findNodeForPoint(node, point);

        }

        if (node.points.size() == 5) {


            divide(node);


            var points = node.points;

            points.add(point);

            for (Point p : points) {

                assign(node, p);
            }

            node.points.clear();

        } else {

            node.points.add(point);
        }

    }


    private void assign(Node node, Point point) throws Exception {


        Node currentNode = findNodeForPoint(node, point);


        add(currentNode, point);
    }

    private void divide(Node node) {


        Node tl = new Node();

        Node tr = new Node();

        Node bl = new Node();

        Node br = new Node();


        float centreLongitude = (node.topLeft.longitude + node.bottomRight.longitude) / 2;
        float centreLatitude = (node.topLeft.latitude + node.bottomRight.latitude) / 2;

        Point centre = new Point(centreLatitude, centreLongitude);
        Point topMiddle = new Point(node.topLeft.latitude, centreLongitude);
        Point rightMiddle = new Point(centre.latitude, node.bottomRight.longitude);
        Point leftMiddle = new Point(centre.latitude, node.topLeft.longitude);
        Point bottomMiddle = new Point(node.bottomRight.latitude, centreLongitude);

        tl.topLeft = node.topLeft;
        tl.bottomRight = centre;

        tr.topLeft = topMiddle;
        tr.bottomRight = rightMiddle;


        bl.topLeft = leftMiddle;
        bl.bottomRight = bottomMiddle;


        br.topLeft = centre;
        br.bottomRight = node.bottomRight;

        node.topLeftChild = tl;
        node.topRightChild = tr;

        node.bottomLeftChild = bl;
        node.bottomRightChild = br;


        node.isLeaf = false;

    }

    private Node findNodeForPoint(Node node, Point point) {


        float latitude = point.latitude;

        float longitude = point.longitude;


        Point topLeft = node.topLeft;
        Point bottomRight = node.bottomRight;


        Point centre = new Point((topLeft.latitude + bottomRight.latitude) / 2, (topLeft.longitude + bottomRight.longitude) / 2);

        // initial topleft = 90,-180
        //initial  topright = 90,180
        //initial bottomleft = -90,180
        //initial bottomRight = -90,-180

        //check if top left node


        if (longitude >= topLeft.longitude && longitude <= centre.longitude

                && latitude <= topLeft.latitude && latitude >= centre.latitude) {


            return node.topLeftChild;
        }


        //check if right node
        if (longitude > centre.longitude && longitude <= bottomRight.longitude
                && latitude <= topLeft.latitude && latitude >= centre.latitude) {


            return node.topRightChild;
        }

        //check if bottom left node


        if (longitude <= topLeft.longitude && longitude <= centre.longitude

                && latitude > centre.latitude && latitude <= bottomRight.latitude) {


            return node.bottomLeftChild;
        }


        //check if bottom right node

        return node.bottomRightChild;
    }


    public void search(Node node, Point point) {


        while (!node.isLeaf) {
            node = findNodeForPoint(node, point);

        }

        System.out.print(node.points);
    }


    public void print(Node node) {


        if (node.isLeaf) {


            System.out.println(node);
            System.out.println(node.points);
            System.out.println();

            return;
        }

        if (node.topLeftChild != null) {

            print(node.topLeftChild);
        }

        if (node.topRightChild != null) {

            print(node.topRightChild);
        }

        if (node.bottomLeftChild != null) {

            print(node.bottomLeftChild);
        }

        if (node.bottomRightChild != null) {

            print(node.bottomRightChild);
        }
    }
}
