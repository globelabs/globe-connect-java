/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.com.globe.connect.voice.sample;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ph.com.globe.connect.Voice;
import ph.com.globe.connect.voice.Choices;
import ph.com.globe.connect.voice.Say;
import static ph.com.globe.connect.voice.Key.*;

/**
 *
 * @author charleszamora
 */
@WebServlet(name = "EventTest", urlPatterns = {"/EventTest"})
public class EventTest extends HttpServlet {

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
        
        response.setContentType("application/json;charset=UTF-8");
        
        Voice voice = new Voice();
        
        voice.say("Welcome to my Tropo Web API.");

        Say e1 = new Say(
            VALUE("Sorry, I did not hear anything."),
            EVENT("timeout")
        );
        
        Say e2 = new Say(
            VALUE("Sorry, that was not a valid option."),
            EVENT("nomatch:1")
        );
        
        Say e3 = new Say(
            VALUE("Nope, still not a valid response"),
            EVENT("nomatch:2")
        );
        
        Say say = new Say(
            VALUE("Please enter your 5 digit zip code."),
            ARRAY(e1, e2, e3)
        );
        
        Choices choices = new Choices("[5 DIGITS]");
        
        voice.ask(
            INSTANCE(choices),
            ATTEMPTS(3),
            BARGEIN(false),
            NAME("foo"),
            REQUIRED(true),
            INSTANCE(say),
            TIMEOUT(5)
        );
        
        voice.on(
            EVENT("continue"),
            NEXT("http://somefakehost:8000/"),
            REQUIRED(true)
        );
        
        try (PrintWriter out = response.getWriter()) {
            out.println(voice.render());
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
