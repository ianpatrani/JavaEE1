package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CookiesServlet")
public class CookiesServlet extends HttpServlet {

    private String mensaje;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //suponemos que visita por 1ra vez el sitio
        boolean nuevoUsuario = true;

        //Obtenemos el arreglo de cookies, obtenemos las cookies del lado del cliente
        Cookie[] cookies = request.getCookies();

        //buscar si existe alguna cookies creada con anterioridad
        //llamada cisitanteRecurrente
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("visitanteRecurrente") && c.getValue().equals("si")) {
                    //si ya existe la cookie es un usuario recurrente
                    nuevoUsuario = false;
                    break;
                }
            }
        }
        if (nuevoUsuario) {
            Cookie visitanteCookie = new Cookie("visitanteRecurrente", "si");
            response.addCookie(visitanteCookie);
            mensaje = "Gracias por visitar nuestro sitio por 1ra vez";
        } else {
            mensaje = "Gracias por visitar NUEVAMENTE el sitio";
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("Mensaje: " + mensaje);
        out.close();
    }

}
