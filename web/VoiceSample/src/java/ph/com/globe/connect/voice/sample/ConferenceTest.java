/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.com.globe.connect.voice.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import ph.com.globe.connect.Voice;
import ph.com.globe.connect.voice.Conference;
import ph.com.globe.connect.voice.JoinPrompt;
import ph.com.globe.connect.voice.LeavePrompt;
import ph.com.globe.connect.voice.Session;
import static ph.com.globe.connect.voice.Key.*;

/**
 *
 * @author charleszamora
 */
@WebServlet(name = "ConferenceTest", urlPatterns = {"/ConferenceTest"})
public class ConferenceTest extends HttpServlet {

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
        
        voice.say("Welcome to my Tropo Web API Conference Call.");
        
        voice.conference(
            ID("12345"),
            MUTE(false),
            NAME("foo"),
            PLAY_TONES(true),
            TERMINATOR("#"),
            INSTANCE(new JoinPrompt(
                VALUE("http://openovate.com/hold-music.mp3")
            )),
            INSTANCE(new LeavePrompt(
                VALUE("http://openovate.com/hold-music.mp3")
            ))
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
