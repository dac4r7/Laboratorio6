/*

   

 
   

 */
package lab.laboratorio6;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



/**
 *
 * @author Diego Adrian Cesarin
 */
public class Menu {

    Scanner dato = new Scanner(System.in);
    Scanner numero = new Scanner(System.in);
    private Pedido pedidoActual = null;  //Se utiliza para guardar y crear un pedido actual
    private Pedido pedidoBuscado = null; //Se guarda un pedido que es buscado
     private int datoNumerico;
       
    private ArrayList<Pedido> pedidos = new ArrayList<>();//Listado pedidos 

    public Menu(ArrayList<Pedido> pedidos) { 
        this.pedidos = pedidos;//se pasan los pedidos , cada pedido tiene su propio cliente y pasta
    }
    
    public void menuDeUsuario(){
       int optionumber =0;
       boolean opcioncorrecta = false; 
       
      do{ 
       while(!opcioncorrecta){
       
       System.out.println("====== MAT-NOODLES SRL ======");    
       System.out.println(" 1. Agregar pedido");   
       System.out.println(" 2. Buscar pedido");   
       System.out.println(" 3. Listar pedidos");   
       System.out.println(" 4. Salir");   
       System.out.println("==== INGRESE UNA OPCION ====");   
       
       try{
           optionumber = numero.nextInt();       
           if( optionumber > 0 && optionumber < 5){      
           opcioncorrecta = true; 
           }else{
               System.out.println("Las opciones van del 1 al 4.Reingrese una Opcion");
           }                             
          }catch(Exception e){
            System.out.println("Solo puede Ingresar un Numero del 1 al 4 !");  
            numero.nextLine();
        }
       }
       opcionesDeUsuario(optionumber);  //opciones del usuario
       opcioncorrecta=false;
       
      }while( optionumber > 0 && optionumber < 4);
       
    }
    //
   private void opcionesDeUsuario(int opcion) {
       
        switch (opcion) {
            case 1:
                pedidoActual = new Pedido();
                this.pedidos.add(pedidoActual);
                break;
            case 2:           
                pedidoBuscado = buscarPedido(validarNumero());
                if(pedidoBuscado != null){
                    System.out.println("=|Se encontro el pedido|=");
                    pedidoBuscado.mostrarResumen();
                }else{ System.out.println("====== NO SE ENCONTRO EL PEDIDO ======"); }
                break;
            case 3:
                 if(pedidos.size()==0){
                     System.out.println("====== NO HAY PEDIDOS AUN ======");
                 }else{
                     for(Pedido p : pedidos){
                         p.mostrarResumen();
                     }
                 }
                break;
            case 4:
                  System.out.println("Saliendo");
                break;
        }     
    }
  //buscamos el pedido dentro del ArrayList la devolvemos si esta presente si no devuelve un null
    public Pedido buscarPedido(int numDePedido){
        
        Pedido pedidoABuscar=null;
         
       if(numDePedido <= pedidos.size() -1 )
        {
            pedidoABuscar = pedidos.get(numDePedido);
        }
        return pedidoABuscar;
    }
   // pide ingresar un numero y valida que es un numero , lo retorna 
private int validarNumero() { 
       boolean ingresovalido = false, condicion=false; 
       int num = 0;
        System.out.print("Ingrese el Numero de Pedido : ");
       
    while (!ingresovalido ) {
       try{
         num = numero.nextInt();
              ingresovalido = true;    
        }catch(InputMismatchException e){
            System.out.println("= Debe Ingresar un NUMERO =");
             numero.nextLine();
        }     
      }
        return num;
    }
}
