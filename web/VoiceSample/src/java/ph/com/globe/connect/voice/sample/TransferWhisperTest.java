/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.com.globe.connect.voice.sample;

import ph.com.globe.connect.voice.enums.Mode;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ph.com.globe.connect.Voice;
import ph.com.globe.connect.voice.Ask;
import ph.com.globe.connect.voice.Choices;
import ph.com.globe.connect.voice.Say;
import ph.com.globe.connect.voice.Transfer;
import ph.com.globe.connect.voice.On;
import static ph.com.globe.connect.voice.Key.*;

/**
 *
 * @author charleszamora
 */
@WebServlet(name = "TransferWhisperTest", urlPatterns = {"/TransferWhisperTest", "/TransferWhisperHangupTest"})
public class TransferWhisperTest extends HttpServlet {

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
        
        if("/VoiceSample/TransferWhisperTest".equals(request.getRequestURI())) {
            voice.say("Welcome to my Tropo Web API, please hold while you are being transferred.");

            Say say = new Say("Press 1 to accept this call or any other number to reject");

            Choices choices = new Choices(
                VALUE("1"),
                MODE(Mode.DTMF)
            );

            Ask ask = new Ask(
                INSTANCE(choices),
                NAME("color"),
                INSTANCE(say),
                TIMEOUT(60)
            );

            On connect1 = new On(
                EVENT("connect"),
                INSTANCE(ask)
            );

            On connect2 = new On(
                EVENT("connect"),
                INSTANCE(new Say("You are now being connected."))
            );
            
            On ring = new On(
                EVENT("ring"),
                INSTANCE(new Say("http://openovate.com/hold-music.mp3"))
            );

            On connect = new On(ARRAY(ring, connect1, connect2));

            voice.transfer(
                TO("9054799241"),
                NAME("foo"),
                INSTANCE(connect),
                REQUIRED(true),
                TERMINATOR("*")
            );

            voice.on(
                EVENT("incomplete"),
                NEXT("/VoiceSample/TransferWhisperHangupTest"),
                INSTANCE(new Say("You are now being disconnected."))
            );
        } else if("/VoiceSample/TransferWhisperHangupTest".equals(request.getRequestURI())) {
            voice.hangup();
        }
        
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
