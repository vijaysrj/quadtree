public class Main {
    public static void main(String[] args) throws Exception {




        Node quadNode = new Node();

        quadNode.add(quadNode,new Point(20,50,"The Coffee Lovers"));
        quadNode.add(quadNode,new Point(50,50,"Snack Point"));
        quadNode.add(quadNode,new Point(50,150,"Delighters"));
        quadNode.add(quadNode,new Point(40,60,"Cafe Coffee Point"));
        quadNode.add(quadNode,new Point(80,50,"The Great Bakes"));
        quadNode.add(quadNode,new Point(70,150,"Tea Corner"));

        quadNode.add(quadNode,new Point(-70,-150,"Tea Corner"));


        quadNode.print(quadNode);

        quadNode.search(quadNode,new Point(50,80));

    }
}