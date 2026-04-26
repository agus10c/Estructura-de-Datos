package TPO2;
import jerarquicas.ArbolGen;
import lineales.dinamicas.Lista;

/**
 * *********** Autores ***********
 * - Daniel Carrasco, FAI-2840
 * - Agustin Caceres FAI-2993
 * - Jonathan maximiliano cabrera, 108665
 */


public class Test {
    static String sOk = "\u001B[32m OK! \u001B[0m", sErr = " \u001B[31m ERROR \u001B[0m";

    public static void main(String[] args) {
        ArbolGen arbol=arbolPrueba();
        Lista l1;
        Lista l2,l3,l4,l5,l6;
        l1 = listaPrueba();
        l2= new Lista();
        l3= listaPrueba2();
        l4= listaPrueba3();
        l5= listaPrueba4();
        l6= listaPrueba5();
        System.out.println("********************************");
        System.out.println("*      Test sonFrontera        *");
        System.out.println("********************************");
        System.out.println("\n");
        System.out.println("Resultado sonFrontera con lista con todas las hojas, espera true: " + (arbol.sonFrontera(l1)));
        System.out.println("lista l1: "+ l1.toString());
        System.out.println("\n");
        System.out.println("Resultado sonFrontera con lista vacia, espera false: " + (arbol.sonFrontera(l2)));
        System.out.println("lista l2: "+ l2.toString());
        System.out.println("\n");
        System.out.println("Resultado sonFrontera con lista con elementos que no son hojas, espera false: " + (arbol.sonFrontera(l4)));
        System.out.println("lista l4: "+ l4.toString());
        System.out.println("\n");
        System.out.println("Resultado sonFrontera con lista con elementos de mas, espera true: " + (arbol.sonFrontera(l6)));
        System.out.println("lista l6: "+ l6.toString());
        System.out.println("\n");
        arbol.vaciar();
        System.out.println("Resultado con Arbol vacio, espera true: " + (arbol.sonFrontera(l1)));
        System.out.println("lista l1: "+ l1.toString());
        System.out.println("\n");
        l1.vaciar();
        System.out.println("Resultado con Arbol vacio y Lista vacia, espera true: " + (arbol.sonFrontera(l1)));
        System.out.println("lista l1: "+ l1.toString());
        System.out.println("\n");
        System.out.println("Resultado con Arbol con solo raiz y Lista con elemento raiz, espera true: " + (arbol.sonFrontera(l6)));
        System.out.println("lista l1: "+ l6.toString());
        System.out.println("\n");

        ArbolGen a1 = new ArbolGen();
        ArbolGen a2 = new ArbolGen();
        System.out.println("********************************");
        System.out.println("*      Test equals        *");
        System.out.println("********************************");
        System.out.println("\n");
        System.out.println("inserto el elemento 10 en el Arbol1");
        a1.insertar(10, 1);
        System.out.println("Realizo equals para Arbol1 con elementos y Arbol2 nulo, debe dar false --> "+a1.equals(a2));
        System.out.println("Vacio Arbol1");
        a1.vaciar();
        System.out.println("inserto el elemento 10 en el Arbol2");
        a2.insertar(10, 1);
        System.out.println("Realizo equals para Arbol1 nulo y Arbol2 con elementos, debe dar false --> "+a1.equals(a2));
        System.out.println("Vacio Arbol2");
        a2.vaciar();
        System.out.println("Realizo equals para Arbol1 nulo y Arbol2 nulo , debe dar true --> "+a1.equals(a2));
        a1 = crearArbol1();
        a2 = crearArbol1();
        System.out.println("\n");
        System.out.println("Arbol1:"
                + "\n                                10"
                + "\n                +---------------+------------+"
                + "\n                |                            |"
                + "\n                9                            15"
                + "\n            +---+---+              +-------+--+---+-------+"
                + "\n            |       |              |       |      |       |"
                + "\n            7       3              12     20     22       30"
                + "\n                                                     +-----+-----+"
                + "\n                                                     |     |     |"
                + "\n                                                     40    45    55"
        );
        System.out.println("\n");
        System.out.println("Arbol2:"
                + "\n                                10"
                + "\n                +---------------+------------+"
                + "\n                |                            |"
                + "\n                9                            15"
                + "\n            +---+---+              +-------+--+---+-------+"
                + "\n            |       |              |       |      |       |"
                + "\n            7       3              12     20     22       30"
                + "\n                                                     +-----+-----+"
                + "\n                                                     |     |     |"
                + "\n                                                     40    45    55"
        );
        System.out.println("\n");
        System.out.println("Realizo equals para Arbol1 y Arbol2 con los mismos elementos, debe dar true --> "+a1.equals(a2));
        System.out.println("inserto el elemento 1 como hijo de 7 en el Arbol2");
        a2.insertar(1, 7);
        System.out.println("Realizo equals para Arbol2 con mas elementos que Arbol1, debe dar false --> "+a1.equals(a2));
        System.out.println("inserto el elemento 1 como hijo de 7  y 2 como hijo de 12 en el Arbol1");
        a1.insertar(1, 7);
        a1.insertar(1, 12);
        System.out.println("Realizo equals para Arbol2 con menos elementos que Arbol1, debe dar false --> "+a1.equals(a2));
        a1 = crearArbol1();
        a2 = crearArbol4();
        System.out.println("\n");
        System.out.println("Arbol2:"
                + "\n                                10"
                + "\n                +---------------+------------+"
                + "\n                |                            |"
                + "\n                9                            15"
                + "\n            +---+---+              +-------+--+---+-------+"
                + "\n            |       |              |       |      |       |"
                + "\n            7       3              12     20     22       100"
                + "\n                                                     +-----+-----+"
                + "\n                                                     |     |     |"
                + "\n                                                     40    45    55"
        );
        System.out.println("\n");
        System.out.println("Realizo equals para Arbol1 y Arbol2 con elementos diferentes, debe dar false --> "+a1.equals(a2));
        a2 = crearArbol2();
        System.out.println("\n");
        System.out.println("Arbol2:"
                + "\n                                10"
                + "\n                +---------------+------------+"
                + "\n                |                            |"
                + "\n                9                            15"
                + "\n            +---+---+              +-------+--+---+-------+"
                + "\n            |       |              |       |      |       |"
                + "\n            7       3              21     20     22       30"
                + "\n                                                     +-----+-----+"
                + "\n                                                     |     |     |"
                + "\n                                                     40    45    55"
        );
        System.out.println("\n");
        System.out.println("Realizo equals para Arbol1 y Arbol2 con hojas con elementos diferentes, debe dar false --> "+a1.equals(a2));
        a2 = crearArbol3();
        System.out.println("\n");
        System.out.println("Arbol2:"
                + "\n                                11"
                + "\n                +---------------+------------+"
                + "\n                |                            |"
                + "\n                9                            15"
                + "\n            +---+---+              +-------+--+---+-------+"
                + "\n            |       |              |       |      |       |"
                + "\n            7       3              12     20     22       30"
                + "\n                                                     +-----+-----+"
                + "\n                                                     |     |     |"
                + "\n                                                     40    45    55"
        );
        System.out.println("\n");
        System.out.println("Realizo equals para Arbol1 y Arbol2 con raices con diferentes elementos, debe dar false --> "+a1.equals(a2));

    }

    public static ArbolGen arbolPrueba(){
        ArbolGen a = new ArbolGen();

        System.out.println("********************************");
        System.out.println("*      Insercion basica        *");
        System.out.println("********************************");
        System.out.println("Inserto el 10 en raiz " + ((a.insertar(10, 1)) ? sOk : sErr));
        System.out.println("Inserto el 9 como hijo de 10 " + ((a.insertar(9, 10)) ? sOk : sErr));
        System.out.println("Inserto el 7 como hijo de 9 " + ((a.insertar(7, 9)) ? sOk : sErr));
        System.out.println("Inserto el 3 como hijo de 9 " + ((a.insertar(3, 9)) ? sOk : sErr));
        System.out.println("Inserto el 15 como hijo de 10 " + ((a.insertar(15, 10)) ? sOk : sErr));
        System.out.println("Inserto el 12 como hijo de 15 " + ((a.insertar(12, 15)) ? sOk : sErr));
        System.out.println("Inserto el 20 como hijo de 15 " + ((a.insertar(20, 15)) ? sOk : sErr));
        System.out.println("Inserto el 22 como hijo de 15 " + ((a.insertar(22, 15)) ? sOk : sErr));
        System.out.println("Inserto el 30 como hijo de 15 " + ((a.insertar(30, 15)) ? sOk : sErr));
        System.out.println("Inserto el 40 como hijo de 30 " + ((a.insertar(40, 30)) ? sOk : sErr));
        System.out.println("Inserto el 45 como hijo de 30 " + ((a.insertar(45, 30)) ? sOk : sErr));
        System.out.println("Inserto el 55 como hijo de 30 " + ((a.insertar(55, 30)) ? sOk : sErr));
        System.out.println("\n toString()  deberia dar: \n"
                + "\n                                10"
                + "\n                +---------------+------------+"
                + "\n                |                            |"
                + "\n                9                            15"
                + "\n            +---+---+              +-------+--+---+-------+"
                + "\n            |       |              |       |      |       |"
                + "\n            7       3              12     20     22       30"
                + "\n                                                     +-----+-----+"
                + "\n                                                     |     |     |"
                + "\n                                                     40    45    55"
                + "\n" + a.toString());
        System.out.println("\n");
        return a;
    }
    public static Lista listaPrueba(){
        Lista l=new Lista();
        l.insertar(45,1);
        l.insertar(3,2);
        l.insertar(12,3);
        l.insertar(20,4);
        l.insertar(40,5);
        l.insertar(22,6);
        l.insertar(7,7);
        l.insertar(55,8);
        return l;
    }
    public static Lista listaPrueba2(){
        Lista l3=new Lista();
        l3.insertar(7,1);
        l3.insertar(3,2);
        l3.insertar(12,3);
        l3.insertar(7,4);
        l3.insertar(22,5);
        l3.insertar(40,6);
        l3.insertar(45,7);
        l3.insertar(55,8);
        return l3;
    }
    public static Lista listaPrueba3(){
        Lista l4=new Lista();
        l4.insertar(7,1);
        l4.insertar(3,2);
        l4.insertar(12,3);
        l4.insertar(20,4);
        l4.insertar(30,5);
        l4.insertar(40,6);
        l4.insertar(45,7);
        l4.insertar(55,8);
        return l4;
    }
    public static Lista listaPrueba4(){
        Lista l5=new Lista();
        l5.insertar(7,1);
        l5.insertar(3,2);
        l5.insertar(12,3);
        l5.insertar(20,4);
        l5.insertar(99,5);
        l5.insertar(40,6);
        l5.insertar(45,7);
        l5.insertar(55,8);
        return l5;
    }
    public static Lista listaPrueba5(){
        Lista l6 = new Lista();
        l6.insertar(45,1);
        l6.insertar(3,2);
        l6.insertar(12,3);
        l6.insertar(20,4);
        l6.insertar(40,5);
        l6.insertar(22,6);
        l6.insertar(7,7);
        l6.insertar(55,8);
        l6.insertar(1,9);
        return l6;
    }

    public static ArbolGen crearArbol1(){
        ArbolGen a = new ArbolGen();
        a.insertar(10, 1);
        a.insertar(9, 10);
        a.insertar(7, 9);
        a.insertar(3, 9);
        a.insertar(15, 10);
        a.insertar(12, 15);
        a.insertar(20, 15);
        a.insertar(22, 15);
        a.insertar(30, 15);
        a.insertar(40, 30);
        a.insertar(45, 30);
        a.insertar(55, 30);
        return a;
    }

    public static ArbolGen crearArbol2(){
        ArbolGen a = new ArbolGen();
        a.insertar(10, 1);
        a.insertar(9, 10);
        a.insertar(7, 9);
        a.insertar(3, 9);
        a.insertar(15, 10);
        a.insertar(21, 15);
        a.insertar(20, 15);
        a.insertar(22, 15);
        a.insertar(30, 15);
        a.insertar(40, 30);
        a.insertar(45, 30);
        a.insertar(55, 30);
        return a;
    }

    public static ArbolGen crearArbol3(){
        ArbolGen a = new ArbolGen();
        a.insertar(11, 1);
        a.insertar(9, 10);
        a.insertar(7, 9);
        a.insertar(3, 9);
        a.insertar(15, 10);
        a.insertar(12, 15);
        a.insertar(20, 15);
        a.insertar(22, 15);
        a.insertar(30, 15);
        a.insertar(40, 30);
        a.insertar(45, 30);
        a.insertar(55, 30);
        return a;
    }

    public static ArbolGen crearArbol4(){
        ArbolGen a = new ArbolGen();
        a.insertar(10, 1);
        a.insertar(9, 10);
        a.insertar(7, 9);
        a.insertar(3, 9);
        a.insertar(15, 10);
        a.insertar(12, 15);
        a.insertar(20, 15);
        a.insertar(22, 15);
        a.insertar(100, 15);
        a.insertar(40, 30);
        a.insertar(45, 30);
        a.insertar(55, 30);
        return a;
    }

}
