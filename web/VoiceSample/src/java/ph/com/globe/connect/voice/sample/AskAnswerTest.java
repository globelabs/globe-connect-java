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

import ph.com.globe.connect.Voice;
import ph.com.globe.connect.voice.Choices;
import ph.com.globe.connect.voice.Say;
import ph.com.globe.connect.voice.Result;
import static ph.com.globe.connect.voice.Key.*;

import org.json.JSONObject;

/**
 *
 * @author charleszamora
 */
@WebServlet(name = "AskAnswerTest", urlPatterns = {"/AskZipTest", "/AnswerZipTest"})
public class AskAnswerTest extends HttpServlet {

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
        
        if("/VoiceSample/AskZipTest".equals(request.getRequestURI())) {
            Say say = new Say("Please enter your 5 digit zip code.");
            Choices choices = new Choices("[5 DIGITS]");
            
            voice.ask(
                INSTANCE(choices),
                ATTEMPTS(3),
                BARGEIN(false),
                NAME("foo"),
                REQUIRED(true),
                INSTANCE(say),
                TIMEOUT(10)
            );

            voice.on(
                EVENT("continue"),
                NEXT("/VoiceSample/AnswerZipTest"),
                REQUIRED(true)
            );
        } else if("/VoiceSample/AnswerZipTest".equals(request.getRequestURI())) {
            StringBuilder builder = new StringBuilder();
            String line = "";
          
            try {
               BufferedReader reader = request.getReader();
               
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
               
            } catch (IOException e) { }
 
            JSONObject json = new JSONObject(builder.toString());
            
            // parse result as flat data
            JSONObject result = new Result(json).getResult();
            
            // get interpretation
            String interpretation = (String) result.get("interpretation");
            
            voice = new Voice();
            
            voice.say("Your zip code is " + interpretation + ", thank you!");
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
