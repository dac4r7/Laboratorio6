/*


*/
package lab.laboratorio6;

import Pastas.TipoPasta;
import Pastas.Agnolotis;
import Pastas.FideosalHuevo;
import Pastas.Noquis;
import Pastas.Pastas;
import Pastas.Ravioles;
import java.util.ArrayList;
import java.util.InputMismatchException;

import java.util.List;
import java.util.Scanner;
import lab.laboratorio6.Cliente;


/**
 *
 * @author Diego A. Cesarin
 */
public class Pedido {
    
  private List<Pastas> pastas = new ArrayList<>(); //Todas las pastas del pedido 
  private Cliente cliente ;                        //Todos los datos del cliente
  Scanner numero = new Scanner(System.in);
  private int numeroPedido ;
 
    public Pedido() {
     this.cliente = new Cliente();  //Se crea un cliente al pedido
     seleccionarPasta();            //Se seleccionan las pastas del cliente
    } 

    //da opciones para seleccion de la pasta verifica que sea valida la seleccion 
    private int seleccionarPasta() {
        Scanner dato = new Scanner(System.in);
        boolean respuesta = false, ingresovalido=false;
        String seleccion="N";
        int opcion = 0;
        do{
            System.out.println("=============== SELECCION DE PASTA ==================");
            System.out.println("================ OPCIONES DE PASTA ==================");
            System.out.println("-1.Fideos al huevo -2.Ravioles -3.Noquis -4.Agnolotis");
            System.out.print(">> ");

         try{
                String sel = numero.nextLine();
                              
                 opcion = Integer.parseInt(sel);   //pasamos el String a un numero si tuviera un espacio
                                                   //lanza NumberFormatException
             
                ingresovalido = evaluarSeleccionPasta(opcion);//Se evalua que la opcion sea valida 1 a 4
           
                if (!ingresovalido) {
                    System.out.println("Opciones disponibles->1.Fideos al huevo 2.Ravioles 3.Noquis 4.Agnolotis");
                }
            }catch(InputMismatchException e) {
                System.out.println("Debe ingresar un NUMERO");
                numero.nextLine();
            }catch(NumberFormatException nfe){
                    System.out.println("Debe ingresar un NUMERO(1,2,3 o 4) y no puede contener espacios");
                    
              }                   
       
        System.out.println(">>DESEA AGREGAR MAS PASTA AL PEDIDO ?<<S/N>>");
        respuesta = validarRespuesta();   
      
        }while(respuesta);
        return opcion;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }
    
    //Evalua que la seleccion de la pasta este entre los rangos mostrados, 
    //se suma la cantidad del tipo de pasta o se agrega un nuevo tipo de pasta al pedido
    private boolean evaluarSeleccionPasta(int opcion) {
                   
        if (opcion < 1 || opcion > 4) {
            return false;
        }
        
        switch (opcion) {
            case 1: FideosalHuevo pastaFideos = (FideosalHuevo)buscarPasta(TipoPasta.fideos);
                    if(pastaFideos == null){
                       pastas.add(new FideosalHuevo(7000)); //si no esta el tipo de pasta se agrega al array se pasa el precio
                     }else{ pastaFideos.agregarCantidad();
                       }                 
                break;
            case 2: Ravioles pastaRavioles = (Ravioles)buscarPasta(TipoPasta.ravioles);//se busca si ya existe el tipo de pasta dentro del pedido
                      if(pastaRavioles == null){
                       pastas.add(new Ravioles(6500)); //si no esta el tipo de pasta se agrega al array se pasa el precio
                     }else{ pastaRavioles.agregarCantidad();
                       }
               
                break;
            case 3:  Noquis pastaNoquis = (Noquis)buscarPasta(TipoPasta.noquis);//se busca si ya existe el tipo de pasta dentro del pedido
                      if(pastaNoquis == null){
                       pastas.add(new Noquis(6500)); //si no esta el tipo de pasta se agrega al array se pasa el precio
                     }else{ pastaNoquis.agregarCantidad();
                       }
                
                break;
            case 4:  Agnolotis pastaAgnolotis = (Agnolotis)buscarPasta(TipoPasta.agnolotis);//se busca si ya existe el tipo de pasta dentro del pedido
                     if(pastaAgnolotis == null){
                       pastas.add(new Agnolotis(7500)); //si no esta el tipo de pasta se agrega al array se pasa el precio
                     }else{ pastaAgnolotis.agregarCantidad();
                     }          
                break;
        }
        return true;
    }
    //buscamos la pasta dentro del ArrayList la devolvemos si esta presente , si no esta presente se devuelve null
    public Pastas buscarPasta(TipoPasta tipo){
        int i=0;
        Pastas pastaEncontrada=null;
        while( i < this.pastas.size() && !this.pastas.get(i).equals(tipo) )
        {
            i++;          
        }
        if(i < pastas.size() ){
            pastaEncontrada = pastas.get(i);
        }
        return pastaEncontrada;
    }
    
  public void mostrarResumen(){  //Muestra el resumen de los pedidos del cliente
      System.out.println("| PEDIDO Nro: " + (this.getNumeroPedido()-1) );
      System.out.println("| CLIENTE : "+cliente.getNombre()+" | "+ cliente.getApellido()+ " MEDIO DE VENTA: " +
                         cliente.getMediodeventa().toString());
    System.out.println("================ DETALLE DE COMPRA ================");
      for(Pastas p : pastas){
          p.descripcion();
      }
      calcularCompra();
  } 
  
  public void calcularCompra(){ //Calcula el total de una compra para un cliente
    double total = 0;

    for(Pastas p : pastas){
        total += p.obtenerCantidad() * p.getPrecio();
    }

    System.out.println("--------------------------------------------");
    System.out.println("|  TOTAL A PAGAR: $" + total);           
    System.out.println("--------------------------------------------");
   }

    public Cliente getCliente() {
        return cliente;
    }
    
     public boolean validarRespuesta(){   //Evalua si una respuesta es S o N
         Scanner d = new Scanner(System.in);
       boolean verificar = false, respuesta = false;
       String dato;
       while(!verificar){
       try{
           dato = d.nextLine();
           if(dato.equalsIgnoreCase("s")){
               respuesta= true;
               verificar=true;
           }else if(dato.equalsIgnoreCase("n")){
                respuesta = false;
                verificar = true;
           }else{
               throw new InputMismatchException("Solo puede ingresar s(para SI) o n(para NO)");
           }
       }catch(InputMismatchException ime){
           System.out.println(ime.getMessage());
       }
       
      }
       return respuesta;      
    }
    
}
