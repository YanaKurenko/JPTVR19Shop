/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Buyer;
import entity.History;
import entity.Product;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BuyerFacade;
import session.HistoryFacade;
import session.ProductFacade;
import session.UserFacade;
import session.UserRolesFacade;

/**
 *
 * @author kasutaja
 */
@WebServlet(name = "BuyerServlet", urlPatterns = {
    "/takeOnProductForm",
    "/takeOnProduct",

    
})
public class BuyerServlet extends HttpServlet {
    @EJB
    private ProductFacade productFacade;
    @EJB
    private BuyerFacade buyerFacade;
    @EJB
    private HistoryFacade historyFacade;
    @EJB
    private UserFacade userFacade;
    @EJB private UserRolesFacade userRolesFacade;
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "У вас нет права использовать этот ресурс. Войдите!");
            request.getRequestDispatcher(LoginServlet.pathToFile.getString("login")).forward(request, response);
            return;
        }
        User user = (User) session.getAttribute("user");
        if (user == null){
            request.setAttribute("info", "У вас нет права использовать этот ресурс. Войдите!");
            request.getRequestDispatcher(LoginServlet.pathToFile.getString("login")).forward(request, response);
            return;
        }
        boolean isRole = userRolesFacade.isRole("BUYER",user);
        if(!isRole){
            request.setAttribute("info", "У вас нет права использовать этот ресурс. Войдите с соответствующими правами!");
            request.getRequestDispatcher(LoginServlet.pathToFile.getString("login")).forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path) {
            case "/takeOnProductForm":
                List<Product> listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("takeOnProduct")).forward(request, response);
                break;
            case "/takeOnProduct":
                String productId = request.getParameter("productId");
               
                if("".equals(productId) || productId == null){
                    request.setAttribute("info", "Выберите книгу или читателя.");
                    request.getRequestDispatcher(LoginServlet.pathToFile.getString("takeOnProduct")).forward(request, response);
                    break;
                }
                Product product = productFacade.find(Long.parseLong(productId));
                Buyer buyer = user.getBuyer();
                History history = new History(product, buyer, new GregorianCalendar().getTime(), null);
                historyFacade.create(history);
                request.setAttribute("info", "Продукт "
                                                +history.getProduct().getName()
                                                +" приобретен покупателем "
                                                +history.getBuyer().getFirstname() 
                                                + " "
                                                +history.getBuyer().getLastname());
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("index")).forward(request, response);
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
