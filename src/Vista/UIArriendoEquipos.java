package Vista;

import Controlador.ControladorArriendoEquipos;
import Excepciones.ArriendoException;
import Excepciones.ClienteException;
import Excepciones.EquipoException;
import Modelo.Arriendo;
import Modelo.Cliente;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UIArriendoEquipos {
    private static UIArriendoEquipos instance = null;
    private Scanner teclado;

    private UIArriendoEquipos() {
    }

    public static UIArriendoEquipos getInstance() {
        if (instance == null) {
            instance = new UIArriendoEquipos();
        }
        return instance;
    }

    public void menu() {
        int opcion=0;
        System.out.println("******* SISTEMA DE ARRIENDO DE EQUIPOS DE NIEVE ****** ");
        do {
            System.out.println("\n\n\n***MENU DE OPCIONES***");
            System.out.println("1. Crea un nuevo cliente");
            System.out.println("2. Crea un nuevo equipo");
            System.out.println("3. Arrienda equipos");
            System.out.println("4. Devuelve equipos");
            System.out.println("5. Cambia estado de un cliente");
            System.out.println("6. Lista de todos los clientes");
            System.out.println("7. Lista de todos los equipos");
            System.out.println("8. Lista todos los arriendos");
            System.out.println("9. Lista detalles de un arriendo");
            System.out.println("10. Salir");
            System.out.println("\n\n\nIngrese opcion: ");

            try{
                opcion = teclado.nextInt();
                System.out.println("");
            }catch (InputMismatchException e){
                System.out.println("\nError: ");
            }


            switch (opcion) {
                case 1 -> creaCliente();
                case 2 -> creaEquipo();
                case 3 -> arriendaEquipos();
                case 4 -> devuelveEquipos();
                case 5 -> cambiaEstadoCliente();
                case 6 -> listaClientes();
                case 7 -> listaEquipos();
                case 8 -> listaArriendos();
                case 9 -> listaDetallesArriendo();
                case 10 -> System.exit(2);
                default -> System.out.println("\nIngreso no valido");
            }
        } while (opcion != 10);
        teclado.close();
    }

    private void listaClientes() {
        String[][] datosClientes = ControladorArriendoEquipos.getInstance().listaClientes();
        int i = 0;
        if (datosClientes.length > 0) {
            System.out.println("\nLISTADO DE CLIENTES");
            System.out.println("------------");
            System.out.println();
            System.out.printf("%1$-15s%2$-30s%3$-20s%4$-15s%n", "RUT", "Nombre", "Direccion", "Telefono");
            for (String[] listado : datosClientes) {

                System.out.printf("%-25s%-12s%10s%10sn", listado[0], listado[1], listado[2], listado[3], listado[4]);
                i++;
            }
        } else {
            System.out.println("\n No existen clientes");
        }
    }

    private void listaEquipos() {
        String[][] datosEquipos = ControladorArriendoEquipos.getInstance().listaEquipos();
        if (datosEquipos.length > 0) {
            System.out.println("\nLISTADO DE EQUIPOS");
            System.out.println("------------");
            System.out.printf("%1$-15s%2$-30s%3$-20s%4$-15s%n", "Codigo", "Descripcion", "Precio");
            for (String[] listado : datosEquipos) {
                System.out.printf("%1$-15s%2$-30s%3$-20s%4$-15s%n", listado[0], listado[1], listado[2], listado[3]);
            }
        } else {
            System.out.println("\nNo existen equipos");
        }
    }

    private void creaCliente() {
        String rut, nom, dir, tel;
        System.out.println("Creando un nuevo cliente...");
        System.out.print("\nRut: ");
        rut = teclado.next().trim();
        if (rut.isBlank() || rut.isEmpty()) {
            System.out.println("No ha ingresado ningún dato, por favor inténtelo de nuevo");
            return;
        }
        System.out.print("Nombre: ");
        nom = teclado.next().trim();
        if (nom.isBlank() || nom.isEmpty()) {
            System.out.println("No ha ingresado ningún dato, por favor inténtelo de nuevo");
            return;
        }
        System.out.print("Domicilio: ");
        dir = teclado.next().trim();
        if (dir.isBlank() || dir.isEmpty()) {
            System.out.println("No ha ingresado ningún dato, por favor inténtelo de nuevo");
            return;
        }
        System.out.println("Telefono: ");
        tel = teclado.next().trim();
        if (tel.isBlank() || tel.isEmpty()) {
            System.out.println("No ha ingresado ningún dato, por favor inténtelo de nuevo");
            return;
        }
        try {
            ControladorArriendoEquipos.getInstance().creaCliente(rut, nom, dir, tel);
            System.out.println("Usted ha creado satisfactoriamente el cliente");
        } catch (ClienteException e) {
            System.out.println("Error creando el cliente, revise los datos nuevamente");
        }
    }

    private void creaEquipo() {
        String descripcion, code, precio;
        long codigo = 0, precioArriendoDia = 0;
        System.out.print("Creando un nuevo equipo... ");
        System.out.println("\n\n\nCodigo: ");
        code = teclado.next().trim();

        if (code.isBlank() || code.isEmpty()) {
            System.out.println("No ha ingresado ningún dato, por favor inténtelo de nuevo");
            return;
        }
        try {
            codigo = Long.parseLong(code);
        } catch (NumberFormatException e) {
            System.out.println("Por favor ingrese solo numeros");
        }
        System.out.print("Descripcion: ");
        descripcion = teclado.next().trim();
        if (descripcion.isBlank() || descripcion.isEmpty()) {
            System.out.println("No ha ingresado ningún dato, por favor inténtelo de nuevo");
            return;
        }
        System.out.print("Precio arriendo por dia: ");
        precio = teclado.next().trim();
        if (precio.isBlank() || precio.isEmpty()) {
            System.out.println("No ha ingresado ningún dato, por favor inténtelo de nuevo");
            return;
        }
        try {
            precioArriendoDia = Long.parseLong(precio);
            if (precioArriendoDia < 0) {
                System.out.println("Por favor ingrese un precio válido");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor ingrese solo numeros");
        }

        try {
            ControladorArriendoEquipos.getInstance().creaEquipo(codigo, descripcion, precioArriendoDia);
        } catch (EquipoException e) {
            System.out.println("Error creando el equipo, intentelo de nuevo");
        }
    }

    public void arriendaEquipos() throws ClienteException, EquipoException, ArriendoException {



    }

    public void cambiaEstadoCliente() {
        System.out.println("Ingrese rut");
        String rut = teclado.next();
        try {
            if (ControladorArriendoEquipos.getInstance().consultaCliente(rut).length > 0) {
                ControladorArriendoEquipos.getInstance().cambiaEstadoCliente(rut);
                String nombre= ControladorArriendoEquipos.getInstance().consultaCliente(rut)[1];
                System.out.println("\nCambiando el estado a un cliente...");
                System.out.println("Rut cliente:"+rut);
                System.out.println("Se ha cambiado exitosamente el estado del cliente"+ nombre+ "a"
                        +ControladorArriendoEquipos.getInstance().consultaCliente(rut)[4]);
            }

        } catch (ClienteException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void listaDetallesArriendo() {

        System.out.print("Codigo arriendo: ");
        long codigoArriendo = sc.nextLong();

        String[] consultaArriendo = ControladorArriendoEquipos.getInstance().consultaArriendo(codigoArriendo);

        if (consultaArriendo.length == 0) {
            System.err.println("Arriendo no encontrado.");
            return;
        }
        sc.nextLine();

        String[][] listaDetallesArriendos = ControladorArriendoEquipos.getInstance().listaDetallesArriendos(codigoArriendo);

        System.out.println("----------------------------------------------------");
        System.out.print("Codigo:" + consultaArriendo[0]);
        System.out.print("Fecha inicio:" + consultaArriendo[1]);
        System.out.print("Fecha devolucion:" + consultaArriendo[2]);
        System.out.print("Estado :" + consultaArriendo[3]);
        System.out.print("Rut cliente:" + consultaArriendo[4]);
        System.out.print("Nombre cleinte:" + consultaArriendo[5]);
        System.out.print("Monto total:" + consultaArriendo[6]);
        System.out.println("----------------------------------------------------");
        System.out.println("                DETALLE DEL ARRIENDO                ");
        System.out.println("----------------------------------------------------");
        System.out.printf("%-15s %-25s %-25s%n", "Codigo Equipo", "Descripcion equipo", "Precio arriendo por dia");
        for (String[] linea : listaDetallesArriendos) {
            System.out.printf("%-15s %-25s %-25s%n", linea[0], linea[1], linea[2]);
        }
    }

            }

