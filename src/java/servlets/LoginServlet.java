/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Buyer;
import entity.Product;
import entity.Role;
import entity.User;
import entity.UserRoles;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BuyerFacade;
import session.ProductFacade;
import session.RoleFacade;
import session.UserFacade;
import session.UserRolesFacade;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "LoginServlet", loadOnStartup = 1, urlPatterns = {
    "/loginForm",
    "/login",
    "/logout",
    "/registrationForm",
    "/createUser",
    "/listProducts",
})
public class LoginServlet extends HttpServlet {
@EJB
    private UserFacade userFacade;
@EJB
    private BuyerFacade buyerFacade;
@EJB
    private ProductFacade productFacade;
@EJB private RoleFacade roleFacade;
@EJB private UserRolesFacade userRolesFacade;
public static final ResourceBundle pathToFile = ResourceBundle.getBundle("property.pathToFile");
    @Override
    public void init() throws ServletException {
        
       if(userFacade.findAll().size()>0) return;
        
        Buyer buyer = new Buyer("Yana", "Kurenko", "59823359");
        buyerFacade.create(buyer);
        User user = new User("admin", "admin", buyer);
        userFacade.create(user);
        Role role = new Role("ADMIN");
        roleFacade.create(role);
        UserRoles userRoles = new UserRoles(user, role);
        userRolesFacade.create(userRoles);
        role = new Role("MANAGER");
        roleFacade.create(role);
        userRoles = new UserRoles(user,role);
        userRolesFacade.create(userRoles);
        role = new Role("BUYER");
        roleFacade.create(role);
        userRoles = new UserRoles(user,role);
        userRolesFacade.create(userRoles);
        
    }



    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String path = request.getServletPath();
        switch (path) {
            case "/loginForm":
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("login")).forward(request, response);
                break;
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                if("".equals(login) || login == null
                       || "".equals(password) || password == null){
                    request.setAttribute("info","Заполните все поля");
                    request.getRequestDispatcher("/loginForm").forward(request, response);
                    break;
                }
                User user = userFacade.findByLogin(login);
                if(user == null){
                    request.setAttribute("info","Нет такого пользователя");
                    request.getRequestDispatcher("/loginForm").forward(request, response);
                    break;
                }
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                request.setAttribute("info","Вы вошли как "+ user.getLogin());
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("index")).forward(request, response);
                break;
            case "/logout":
                session = request.getSession(false);
                if(session != null){
                   session.invalidate();
                }
                request.setAttribute("info", "Вы вышли");
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("index")).forward(request, response);
                break;
            case "/registrationForm":
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("registration")).forward(request, response);
                break;
            case "/createUser":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String purse = request.getParameter("purse");
                login = request.getParameter("login");
                password = request.getParameter("password");
                if("".equals(firstname) || firstname == null
                       || "".equals(lastname) || lastname == null
                       || "".equals(purse) || purse == null
                       || "".equals(login) || login == null
                       || "".equals(password) || password == null){
                    request.setAttribute("info","Заполните все поля");
                    request.getRequestDispatcher(LoginServlet.pathToFile.getString("registration")).forward(request, response);
                    break;
                }
                
                Buyer buyer = new Buyer(firstname, lastname, purse);
                buyerFacade.create(buyer);
                user = new User(login, password, buyer);
                userFacade.create(user);
                //Здесь добавим роль пользователю.
                Role roleBuyer = roleFacade.findByName("BUYER");
                UserRoles userRoles = new UserRoles(user, roleBuyer);
                userRolesFacade.create(userRoles);
                request.setAttribute("info", 
                        "Покупатель "+user.getLogin()+" добавлен"     
                );
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("index")).forward(request, response);
                break; 
            case "/listProducts":
                List<Product> listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("listProducts")).forward(request, response);
                break;    
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}


