/*

 */
package Main; 

import Exceptions.EntidadNoEncontradaException;
import Exceptions.ValidacionException;
import Service.CategoriaService;
import Service.PedidoService;
import Service.ProductoService;
import Service.UsuarioService;
import java.util.Scanner;

public class MainConsola {

    private static Scanner scanner = new Scanner(System.in);
    
    private static CategoriaService catService = new CategoriaService();
    private static ProductoService prodService = new ProductoService();
    private static UsuarioService userService = new UsuarioService();
    private static PedidoService pedidoService = new PedidoService();
    

    public static void main(String[] args) {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n SISTEMA DE PEDIDOS (FOOD STORE) ");
            System.out.println("1. Categorias");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        menuCategorias();
                        break;
                    case 2:
                        menuProductos();
                        break;
                    case 3:
                        menuUsuarios();
                        break;
                    case 4:
                        menuPedidos();
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema... Adios!");
                        break;
                    default:
                        System.out.println("Opcion incorrecta. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un numero valido.");
            }
        }
    }

    // --- SUBMENU DE CATEGORIAS ---
    private static void menuCategorias() {
        int opcionCat = -1;
        while (opcionCat != 0) {
            System.out.println("\n--- GESTION DE CATEGORIAS ---");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver al Menu Principal");
            System.out.print("Seleccione: ");

            try {
                opcionCat = Integer.parseInt(scanner.nextLine());

                switch (opcionCat) {
                    case 1:
                        catService.listarCategorias();
                        break;
                    case 2:
                        System.out.print("Ingrese nombre de la categoria: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese descripcion: ");
                        String desc = scanner.nextLine();
                        catService.crearCategoria(nombre, desc); 
                        break;
                    case 3:
                        catService.listarCategorias();
                        System.out.print("Ingrese el ID de la categoria a editar: ");
                        Long idEditar = Long.parseLong(scanner.nextLine());
                        System.out.print("Ingrese nuevo nombre (o presione Enter para dejar igual): ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Ingrese nueva descripcion (o presione Enter para dejar igual): ");
                        String nuevaDesc = scanner.nextLine();
                        catService.editarCategoria(idEditar, nuevoNombre, nuevaDesc);
                        break;
                    case 4:
                        System.out.print("Ingrese el ID de la categoria a eliminar: ");
                        Long idEliminar = Long.parseLong(scanner.nextLine());
                        System.out.print("Esta seguro que desea eliminarla? (S/N): ");
                        String conf = scanner.nextLine();
                        if (conf.equalsIgnoreCase("S")) {
                            catService.eliminarCategoria(idEliminar);
                        } else {
                            System.out.println("Operacion cancelada.");
                        }
                        break;
                    case 0:
                        System.out.println("Volviendo...");
                        break;
                    default:
                        System.out.println("Opcion incorrecta.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero de opcion o ID valido.");
            } catch (ValidacionException | EntidadNoEncontradaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //
    private static void menuProductos() {
        int opcionProd = -1;
        while (opcionProd != 0) {
            System.out.println("\n--- GESTION DE PRODUCTOS ---");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver al Menu Principal");
            System.out.print("Seleccione: ");

            try {
                opcionProd = Integer.parseInt(scanner.nextLine());

                switch (opcionProd) {
                    case 1:
                        prodService.listarProductos();
                        break;
                    case 2:
                        System.out.print("Ingrese nombre del producto: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese precio: ");
                        double precio = Double.parseDouble(scanner.nextLine());
                        System.out.print("Ingrese descripcion: ");
                        String desc = scanner.nextLine();
                        System.out.print("Ingrese stock inicial: ");
                        int stock = Integer.parseInt(scanner.nextLine());
                        System.out.print("Ingrese nombre o ruta de la imagen: ");
                        String imagen = scanner.nextLine();
                        System.out.print("Esta disponible? (S/N): ");
                        String dispTexto = scanner.nextLine();
                        boolean disponible = dispTexto.equalsIgnoreCase("S");
                        
                        catService.listarCategorias();
                        System.out.print("Ingrese el ID de la categoria para este producto: ");
                        Long catId = Long.parseLong(scanner.nextLine());
                        Entities.Categoria categoriaSeleccionada = catService.buscarCategoriaPorId(catId);
                        
                        prodService.crearProducto(nombre, precio, desc, stock, imagen, disponible, categoriaSeleccionada);
                        break;
                    case 3:
                        prodService.listarProductos();
                        System.out.print("Ingrese el ID del producto a editar: ");
                        Long idEditar = Long.parseLong(scanner.nextLine());
                        System.out.print("Ingrese nuevo nombre (Enter para no cambiar): ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Ingrese nuevo precio (o ingrese 0 si no cambia): ");
                        double nuevoPrecio = Double.parseDouble(scanner.nextLine());
                        System.out.print("Ingrese nuevo stock (o ingrese -1 si no cambia): ");
                        int nuevoStock = Integer.parseInt(scanner.nextLine());
                        System.out.print("Ingrese el ID de la nueva categoria (o 0 para no cambiarla): ");
                        Long idNuevaCat = Long.parseLong(scanner.nextLine());
                        
                        Entities.Categoria nuevaCat = null;
                        if (idNuevaCat != 0) {
                            nuevaCat = catService.buscarCategoriaPorId(idNuevaCat);
                        }
                        
                        prodService.editarProducto(idEditar, nuevoNombre, nuevoPrecio, nuevoStock, nuevaCat);
                        break;
                    case 4:
                        prodService.listarProductos();
                        System.out.print("Ingrese el ID del producto a eliminar: ");
                        Long idEliminar = Long.parseLong(scanner.nextLine());
                        System.out.print("Esta seguro que desea eliminarlo? (S/N): ");
                        String conf = scanner.nextLine();
                        if (conf.equalsIgnoreCase("S")) {
                            prodService.eliminarProducto(idEliminar);
                            System.out.println("Producto eliminado con exito.");
                        } else {
                            System.out.println("Operacion cancelada.");
                        }
                        break;
                    case 0:
                        System.out.println("Volviendo...");
                        break;
                    default:
                        System.out.println("Opcion incorrecta.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Asegurese de ingresar un numero valido.");
            } catch (ValidacionException | EntidadNoEncontradaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // --- SUBMENU DE USUARIOS ---
    private static void menuUsuarios() {
        int opcionUsu = -1;
        while (opcionUsu != 0) {
            System.out.println("\n--- GESTION DE USUARIOS ---");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver al Menu Principal");
            System.out.print("Seleccione: ");

            try {
                opcionUsu = Integer.parseInt(scanner.nextLine());

                switch (opcionUsu) {
                    case 1:
                        userService.listarUsuarios();
                        break;
                    case 2:
                        System.out.print("Ingrese nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese apellido: ");
                        String apellido = scanner.nextLine();
                        System.out.print("Ingrese email: ");
                        String email = scanner.nextLine();
                        System.out.print("Ingrese celular: ");
                        String celular = scanner.nextLine();
                        System.out.print("Ingrese contrasenia: ");
                        String contrasenia = scanner.nextLine();
                        
                        System.out.print("Ingrese Rol (ej. ADMIN, USUARIO): ");
                        String rolStr = scanner.nextLine().toUpperCase();
                        Enums.Rol rol = Enums.Rol.valueOf(rolStr);
                        
                        userService.crearUsuario(nombre, apellido, email, celular, contrasenia, rol); 
                        break;
                    case 3:
                        userService.listarUsuarios();
                        System.out.print("Ingrese el ID del usuario a editar: ");
                        Long idEditar = Long.parseLong(scanner.nextLine());
                        
                        System.out.print("Ingrese nuevo nombre (Enter para dejar igual): ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Ingrese nuevo apellido (Enter para dejar igual): ");
                        String nuevoApellido = scanner.nextLine();
                        System.out.print("Ingrese nuevo email (Enter para dejar igual): ");
                        String nuevoEmail = scanner.nextLine();
                        System.out.print("Ingrese nuevo celular (Enter para dejar igual): ");
                        String nuevoCelular = scanner.nextLine();
                        System.out.print("Ingrese nueva contrasenia (Enter para dejar igual): ");
                        String nuevaContra = scanner.nextLine();
                        
                        System.out.print("Ingrese nuevo Rol (Enter para dejar igual): ");
                        String nuevoRolStr = scanner.nextLine();
                        Enums.Rol nuevoRol = null;
                        if (!nuevoRolStr.trim().isEmpty()) {
                            nuevoRol = Enums.Rol.valueOf(nuevoRolStr.toUpperCase());
                        }

                        userService.editarUsuario(idEditar, nuevoNombre, nuevoApellido, nuevoEmail, nuevoCelular, nuevaContra, nuevoRol);
                        break;
                    case 4:
                        System.out.print("Ingrese el ID del usuario a eliminar: ");
                        Long idEliminar = Long.parseLong(scanner.nextLine());
                        System.out.print("Esta seguro que desea eliminarlo? (S/N): ");
                        String conf = scanner.nextLine();
                        if (conf.equalsIgnoreCase("S")) {
                            userService.eliminarUsuario(idEliminar);
                            System.out.println("Usuario eliminado.");
                        } else {
                            System.out.println("Operacion cancelada.");
                        }
                        break;
                    case 0:
                        System.out.println("Volviendo...");
                        break;
                    default:
                        System.out.println("Opcion incorrecta.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero de opcion o ID valido.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: El rol ingresado no es valido. Verifique como esta escrito en su Enum.");
            } catch (ValidacionException | EntidadNoEncontradaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void menuPedidos() {
        int opcionPed = -1;
        while (opcionPed != 0) {
            System.out.println("\n--- GESTION DE PEDIDOS ---");
            System.out.println("1. Listar Pedidos");
            System.out.println("2. Iniciar Nuevo Pedido");
            System.out.println("3. Agregar Producto a un Pedido");
            System.out.println("4. Cancelar/Eliminar Pedido");
            System.out.println("0. Volver al Menu Principal");
            System.out.print("Seleccione: ");

            try {
                opcionPed = Integer.parseInt(scanner.nextLine());

                switch (opcionPed) {
                    case 1:
                        pedidoService.listarPedidos();
                        break;
                    case 2:
                        userService.listarUsuarios();
                        System.out.print("Ingrese el ID del usuario para el pedido: ");
                        Long userId = Long.parseLong(scanner.nextLine());
                        Entities.Usuario usuarioSeleccionado = userService.buscarUsuarioPorId(userId);
                        
                        System.out.print("Ingrese Forma de Pago (ej. EFECTIVO, TARJETA, TRANSFERENCIA): ");
                        String pagoStr = scanner.nextLine().toUpperCase();
                        Enums.FormaPago formaPago = Enums.FormaPago.valueOf(pagoStr);
                        
                        pedidoService.iniciarPedido(usuarioSeleccionado, formaPago);
                        System.out.println("Pedido iniciado con exito.");
                        break;
                    case 3:
                        pedidoService.listarPedidos();
                        System.out.print("Ingrese el ID del pedido: ");
                        Long idPedido = Long.parseLong(scanner.nextLine());
                        Entities.Pedido pedido = pedidoService.buscarPedidoPorId(idPedido);
                        
                        prodService.listarProductos();
                        System.out.print("Ingrese el ID del producto a agregar: ");
                        Long idProducto = Long.parseLong(scanner.nextLine());
                        Entities.Producto producto = prodService.buscarProductoPorId(idProducto);
                        
                        System.out.print("Ingrese la cantidad: ");
                        int cantidad = Integer.parseInt(scanner.nextLine());
                        
                        pedidoService.agregarDetalle(pedido, producto, cantidad);
                        System.out.println("Detalle agregado con exito.");
                        break;
                    case 4:
                        pedidoService.listarPedidos();
                        System.out.print("Ingrese el ID del pedido a cancelar: ");
                        Long idEliminar = Long.parseLong(scanner.nextLine());
                        System.out.print("Esta seguro que desea cancelar este pedido? (S/N): ");
                        String conf = scanner.nextLine();
                        if (conf.equalsIgnoreCase("S")) {
                            pedidoService.eliminarPedido(idEliminar);
                        } else {
                            System.out.println("Operacion cancelada.");
                        }
                        break;
                    case 0:
                        System.out.println("Volviendo...");
                        break;
                    default:
                        System.out.println("Opcion incorrecta.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Asegurese de ingresar un numero valido.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: La forma de pago ingresada no es valida. Verifique su Enum.");
            } catch (ValidacionException | EntidadNoEncontradaException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}